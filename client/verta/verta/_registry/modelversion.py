# -*- coding: utf-8 -*-

from __future__ import print_function

from .._internal_utils._utils import NoneProtoResponse
from .._tracking.entity import _ModelDBEntity

from .._protos.public.registry import RegistryService_pb2 as _ModelVersionService
from .._protos.public.common import CommonService_pb2 as _CommonCommonService

from .._internal_utils import _utils, _artifact_utils, importer

from ..external import six
import requests
import time
import os
import pickle


class RegisteredModelVersion(_ModelDBEntity):
    def __init__(self, conn, conf, msg):
        super(RegisteredModelVersion, self).__init__(conn, conf, _ModelVersionService, "registered_model_version", msg)

    def __repr__(self):
        raise NotImplementedError

    @property
    def name(self):
        self._refresh_cache()
        return self._msg.version

    @property
    def has_model(self):
        return bool(self._msg.model) and bool(self._msg.model.key)

    @classmethod
    def _generate_default_name(cls):
        return "ModelVersion {}".format(_utils.generate_default_name())

    @classmethod
    def _get_proto_by_id(cls, conn, id):
        Message = _ModelVersionService.GetModelVersionRequest
        endpoint = "/api/v1/registry/registered_model_versions/{}".format(id)
        response = conn.make_proto_request("GET", endpoint)

        return conn.maybe_proto_response(response, Message.Response).model_version

    @classmethod
    def _get_proto_by_name(cls, conn, name, registered_model_id):
        Message = _ModelVersionService.FindModelVersionRequest
        predicates = [
            _CommonCommonService.KeyValueQuery(key="version",
                                               value=_utils.python_to_val_proto(name),
                                               operator=_CommonCommonService.OperatorEnum.EQ)
        ]
        endpoint = "/api/v1/registry/{}/versions/find".format(registered_model_id)
        msg = Message(predicates=predicates)

        proto_response = conn.make_proto_request("POST", endpoint, body=msg)
        response = conn.maybe_proto_response(proto_response, Message.Response)

        if not response.model_versions:
            return None

        # should only have 1 entry here, as name/version is unique
        return response.model_versions[0]

    @classmethod
    def _create_proto_internal(cls, conn, ctx, name, desc=None, tags=None, labels=None, attrs=None, date_created=None):
        ModelVersionMessage = _ModelVersionService.ModelVersion
        SetModelVersionMessage = _ModelVersionService.SetModelVersion
        registered_model_id = ctx.registered_model.id

        model_version_msg = ModelVersionMessage(version=name, description=desc, registered_model_id=registered_model_id,
                                                labels=labels, time_created=date_created, time_updated=date_created)
        endpoint = "/api/v1/registry/{}/versions".format(registered_model_id)
        response = conn.make_proto_request("POST", endpoint, body=model_version_msg)
        model_version = conn.must_proto_response(response, SetModelVersionMessage.Response).model_version

        print("Created new ModelVersion: {}".format(model_version.version))
        return model_version

    def log_model(self, model, overwrite=False):
        self._refresh_cache()
        if self.has_model and not overwrite:
            raise ValueError("model already exists; consider setting overwrite=True")

        if isinstance(model, six.string_types):  # filepath
            serialized_model = open(model, 'rb')  # file handle
        else:
            serialized_model, method, _ = _artifact_utils.serialize_model(model)  # bytestream

        try:
            extension = _artifact_utils.get_file_ext(serialized_model)
        except (TypeError, ValueError):
            extension = _artifact_utils.ext_from_method(method)

        # Create artifact message and update ModelVersion's message:
        self._msg.model.CopyFrom(self._create_artifact_msg("model", serialized_model, artifact_type=_CommonCommonService.ArtifactTypeEnum.MODEL, extension=extension))
        self._update_model_version()

        # Upload the artifact to ModelDB:
        self._upload_artifact(
            "model", serialized_model,
            _CommonCommonService.ArtifactTypeEnum.MODEL,
        )

    def get_model(self):
        return _artifact_utils.deserialize_model(self._get_artifact("model", _CommonCommonService.ArtifactTypeEnum.MODEL))

    def log_artifact(self, key, asset, overwrite=False):
        if key == "model":
            raise ValueError("The key `model` is reserved for model. Please use `set_model`")

        self._refresh_cache()
        same_key_ind = -1

        for i in range(len(self._msg.artifacts)):
            artifact = self._msg.artifacts[i]
            if artifact.key == key:
                if not overwrite:
                    raise ValueError("The key has been set; consider setting overwrite=True")
                else:
                    same_key_ind = i
                break

        artifact_type = _CommonCommonService.ArtifactTypeEnum.BLOB

        if isinstance(asset, six.string_types):  # filepath
            artifact_stream = open(asset, 'rb')  # file handle
        else:
            artifact_stream, method = _artifact_utils.ensure_bytestream(asset)  # bytestream

        try:
            extension = _artifact_utils.get_file_ext(artifact_stream)
        except (TypeError, ValueError):
            extension = _artifact_utils.ext_from_method(method)

        artifact_msg = self._create_artifact_msg(key, artifact_stream, artifact_type=artifact_type, extension=extension)
        if same_key_ind == -1:
            self._msg.artifacts.append(artifact_msg)
        else:
            self._msg.artifacts[same_key_ind].CopyFrom(artifact_msg)

        self._update_model_version()
        self._upload_artifact(key, artifact_stream, artifact_type=artifact_type)

    def get_artifact(self, key):
        artifact = self._get_artifact(key, _CommonCommonService.ArtifactTypeEnum.BLOB)
        artifact_stream = six.BytesIO(artifact)

        torch = importer.maybe_dependency("torch")
        if torch is not None:
            try:
                obj = torch.load(artifact_stream)
            except:  # not something torch can deserialize
                artifact_stream.seek(0)
            else:
                artifact_stream.close()
                return obj

        try:
            obj = pickle.load(artifact_stream)
        except:  # not something pickle can deserialize
            artifact_stream.seek(0)
        else:
            artifact_stream.close()
            return obj

        return artifact_stream

    def del_artifact(self, key):
        self._refresh_cache()

        ind = -1
        for i in range(len(self._msg.artifacts)):
            artifact = self._msg.artifacts[i]
            if artifact.key == key:
                ind = i
                break

        if ind == -1:
            raise KeyError("no artifact found with key {}".format(key))

        del self._msg.artifacts[ind]
        self._update_model_version()

    def set_environment(self, env):
        # Env must be an EnvironmentBlob. Let's re-use the functionality from there
        raise NotImplementedError

    def del_environment(self):
        raise NotImplementedError

    def _get_url_for_artifact(self, key, method, artifact_type, part_num=0):
        if method.upper() not in ("GET", "PUT"):
            raise ValueError("`method` must be one of {'GET', 'PUT'}")

        Message = _ModelVersionService.GetUrlForArtifact
        msg = Message(
            model_version_id=self.id,
            key=key,
            method=method,
            artifact_type=artifact_type,
            part_number=part_num
        )
        data = _utils.proto_to_json(msg)
        endpoint = "{}://{}/api/v1/registry/versions/{}/getUrlForArtifact".format(
            self._conn.scheme,
            self._conn.socket,
            self.id
        )
        response = _utils.make_request("POST", endpoint, self._conn, json=data)
        _utils.raise_for_http_error(response)
        return _utils.json_to_proto(response.json(), Message.Response)

    def _upload_artifact(self, key, file_handle, artifact_type, part_size=64*(10**6)):
        file_handle.seek(0)

        # check if multipart upload ok
        url_for_artifact = self._get_url_for_artifact(key, "PUT", artifact_type, part_num=1)

        print("uploading {} to ModelDB".format(key))
        if url_for_artifact.multipart_upload_ok:
            # TODO: parallelize this
            file_parts = iter(lambda: file_handle.read(part_size), b'')
            for part_num, file_part in enumerate(file_parts, start=1):
                print("uploading part {}".format(part_num), end='\r')

                # get presigned URL
                url = self._get_url_for_artifact(key, "PUT", artifact_type, part_num=part_num).url

                # wrap file part into bytestream to avoid OverflowError
                #     Passing a bytestring >2 GB (num bytes > max val of int32) directly to
                #     ``requests`` will overwhelm CPython's SSL lib when it tries to sign the
                #     payload. But passing a buffered bytestream instead of the raw bytestring
                #     indicates to ``requests`` that it should perform a streaming upload via
                #     HTTP/1.1 chunked transfer encoding and avoid this issue.
                #     https://github.com/psf/requests/issues/2717
                part_stream = six.BytesIO(file_part)

                # upload part
                #     Retry connection errors, to make large multipart uploads more robust.
                for _ in range(3):
                    try:
                        response = _utils.make_request("PUT", url, self._conn, data=part_stream)
                    except requests.ConnectionError:  # e.g. broken pipe
                        time.sleep(1)
                        continue  # try again
                    else:
                        break
                _utils.raise_for_http_error(response)

                # commit part
                url = "{}://{}/api/v1/registry/versions/{}/commitArtifactPart".format(
                    self._conn.scheme,
                    self._conn.socket,
                    self.id
                )
                msg = _ModelVersionService.CommitArtifactPart(
                    model_version_id=self.id,
                    key=key
                )
                msg.artifact_part.part_number = part_num
                msg.artifact_part.etag = response.headers['ETag']
                data = _utils.proto_to_json(msg)
                response = _utils.make_request("POST", url, self._conn, json=data)
                _utils.raise_for_http_error(response)
            print()

            # complete upload
            url = "{}://{}/api/v1/registry/versions/{}/commitMultipartArtifact".format(
                self._conn.scheme,
                self._conn.socket,
                self.id
            )
            msg = _ModelVersionService.CommitMultipartArtifact(
                model_version_id=self.id,
                key=key
            )
            data = _utils.proto_to_json(msg)
            response = _utils.make_request("POST", url, self._conn, json=data)
            _utils.raise_for_http_error(response)
        else:
            # upload full artifact
            response = _utils.make_request("PUT", url_for_artifact.url, self._conn, data=file_handle)
            _utils.raise_for_http_error(response)

        print("upload complete")

    def _update_model_version(self):
        Message = _ModelVersionService.SetModelVersion
        endpoint = "/api/v1/registry/{}/versions/{}".format(self._msg.registered_model_id, self.id)

        response = self._conn.make_proto_request("PUT", endpoint, body=self._msg)
        self._conn.must_proto_response(response, Message.Response)
        self._clear_cache()

    def _create_artifact_msg(self, key, artifact_stream, artifact_type, extension=None):
        # calculate checksum
        artifact_hash = _artifact_utils.calc_sha256(artifact_stream)
        artifact_stream.seek(0)

        # determine basename
        #     The key might already contain the file extension, thanks to our hard-coded deployment
        #     keys e.g. "model.pkl" and "model_api.json".
        if extension is None:
            basename = key
        elif key.endswith(os.extsep + extension):
            basename = key
        else:
            basename = key + os.extsep + extension

        # build upload path from checksum and basename
        artifact_path = os.path.join(artifact_hash, basename)

        # TODO: support VERTA_ARTIFACT_DIR

        # log key to ModelDB
        artifact_msg = _CommonCommonService.Artifact(key=key,
                                               path=artifact_path,
                                               path_only=False,
                                               artifact_type=artifact_type,
                                               filename_extension=extension)

        return artifact_msg

    def _get_artifact(self, key, artifact_type):
        # check to see if key exists
        self._refresh_cache()
        if key == "model":
            # get model artifact
            if not self.has_model:
                raise KeyError("no model associated with this version")
        elif len(filter(lambda artifact: artifact.key == key, self._msg.assets)) == 0:
            raise KeyError("no artifact found with key {}".format(key))

        # download artifact from artifact store
        url = self._get_url_for_artifact(key, "GET", artifact_type).url

        response = _utils.make_request("GET", url, self._conn)
        _utils.raise_for_http_error(response)

        return response.content

    def add_label(self, label):
        if label is None:
            raise ValueError("label is not specified")
        self._clear_cache()
        self._refresh_cache()
        if label not in self._msg.labels:
            self._msg.labels.append(label)
            self._update()

    def del_label(self, label):
        if label is None:
            raise ValueError("label is not specified")
        self._clear_cache()
        self._refresh_cache()
        if label in self._msg.labels:
            self._msg.labels.remove(label)
            self._update()

    def get_labels(self):
        self._clear_cache()
        self._refresh_cache()
        return self._msg.labels

    def _update(self):
        response = self._conn.make_proto_request("PUT", "/api/v1/registry/{}/versions/{}".format(self._msg.registered_model_id, self.id),
                                                 body=self._msg)
        Message = _ModelVersionService.SetModelVersion
        if isinstance(self._conn.maybe_proto_response(response, Message.Response), NoneProtoResponse):
            raise ValueError("Model not found")

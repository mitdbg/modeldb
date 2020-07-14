# -*- coding: utf-8 -*-

from __future__ import print_function

from .entity import _ModelDBEntity

from .._protos.public.registry import RegistryService_pb2 as _ModelVersionService
from .._protos.public.common import CommonService_pb2 as _CommonCommonService

from .._internal_utils import (
    _utils,
)


class ModelVersion(_ModelDBEntity):
    def __init__(self, conn, conf, msg):
        super(ModelVersion, self).__init__(conn, conf, _ModelVersionService, "model_version", msg)

    def __repr__(self):
        raise NotImplementedError

    @property
    def name(self):
        self._refresh_cache()
        return self._msg.name

    @classmethod
    def _generate_default_name(cls):
        return "ModelVersion {}".format(_utils.generate_default_name())

    @classmethod
    def _get_proto_by_id(cls, conn, id):
        # Message = _ModelVersionService.GetModelVersionRequest
        # response = conn.make_proto_request("GET",
        #                                    "/api/v1/v1/registry/{}/versions/{}".format(registered_model_id, id))
        # return conn.maybe_proto_response(response, Message.Response).model_version
        # TODO: check/update the url of protos
        raise NotImplementedError

    @classmethod
    def _get_proto_by_name(cls, conn, name, registered_model):
        Message = _ModelVersionService.FindModelVersionRequest

        predicates = [
            _CommonCommonService.KeyValueQuery(key="version",
                                               value=_utils.python_to_val_proto(name),
                                               operator=_CommonCommonService.OperatorEnum.EQ)
        ]
        endpoint = "/api/v1/registry/workspaces/{}/registered_models/{}/versions/find".format(registered_model.workspace, registered_model.name)
        msg = Message(predicates=predicates)

        proto_response = conn.make_proto_request("POST", endpoint, msg)

        response = conn.must_proto_response(proto_response, Message.Response)
        if response.total_records == 0:
            raise ValueError("ModelVersion with name {} does not exists".format(name))

        return response.model_versions[0] # should only have 1 entry here, as name/version is unique

    @classmethod
    def _create_proto_internal(cls, conn, ctx, name, desc=None, tags=None, attrs=None, date_created=None, **kwargs):
        ModelVersionMessage = _ModelVersionService.ModelVersion
        SetModelVersionMessage = _ModelVersionService.SetModelVersion
        registered_model_id = ctx.registered_model.id

        msg = ModelVersionMessage(version=name, description=desc, registered_model_id=registered_model_id,
                                            time_created=date_created, time_updated=date_created)

        response = conn.make_proto_request("POST",
                                           "/api/v1/registry/{}/versions".format(registered_model_id),
                                           body=msg)

        model_version = conn.must_proto_response(response, SetModelVersionMessage.Response).model_version

        print("Created new ModelVersion: {}".format(model_version.name))
        return model_version

    def set_model(self, model, overwrite=False):
        # similar to ExperimentRun.log_artifact
        raise NotImplementedError

    def add_asset(self, key, asset, overwrite=False):
        # similar to ExperimentRun.log_artifact
        raise NotImplementedError

    def del_asset(self, key):
        raise NotImplementedError

    def set_environment(self, env):
        # Env must be an EnvironmentBlob. Let's re-use the functionality from there
        raise NotImplementedError

    def del_environment(self):
        raise NotImplementedError

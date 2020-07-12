# -*- coding: utf-8 -*-

from __future__ import print_function

import requests
import warnings

from .context import _Context
from .entity import _ModelDBEntity
from .experimentruns import ExperimentRuns

from .._protos.public.common import CommonService_pb2 as _CommonCommonService
from .._protos.public.modeldb import ExperimentService_pb2 as _ExperimentService

from ..external import six

from .._internal_utils import (
    _utils,
)


class Experiment(_ModelDBEntity):
    """
    Object representing a machine learning Experiment.

    This class provides read/write functionality for Experiment metadata and access to its Experiment
    Runs.

    There should not be a need to instantiate this class directly; please use
    :meth:`Client.set_experiment`.

    Attributes
    ----------
    id : str
        ID of this Experiment.
    name : str
        Name of this Experiment.
    expt_runs : :class:`ExperimentRuns`
        Experiment Runs under this Experiment.

    """
    def __init__(self, conn, conf,
                 proj_id=None, expt_name=None,
                 desc=None, tags=None, attrs=None,
                 _expt_id=None):
        if expt_name is not None and _expt_id is not None:
            raise ValueError("cannot specify both `expt_name` and `_expt_id`")

        ctx = _Context()
        ctx.proj_id = proj_id

        if _expt_id is not None:
            expt = Experiment._get(conn, _expt_id=_expt_id)
            if expt is not None:
                print("set existing Experiment: {}".format(expt.name))
            else:
                raise ValueError("Experiment with ID {} not found".format(_expt_id))
        elif proj_id is not None:
            if expt_name is None:
                expt_name = Experiment._generate_default_name()
            try:
                expt = Experiment._create(conn, ctx, expt_name, desc=desc, tags=tags, attrs=attrs)
            except requests.HTTPError as e:
                if e.response.status_code == 409:  # already exists
                    if any(param is not None for param in (desc, tags, attrs)):
                        warnings.warn("Experiment with name {} already exists;"
                                      " cannot initialize `desc`, `tags`, or `attrs`".format(expt_name))
                    expt = Experiment._get(conn, proj_id, expt_name)
                    if expt is not None:
                        print("set existing Experiment: {}".format(expt.name))
                    else:
                        raise RuntimeError("unable to retrieve Experiment {};"
                                           " please notify the Verta development team".format(expt_name))
                else:
                    raise e
            else:
                print("created new Experiment: {}".format(expt.name))
        else:
            raise ValueError("insufficient arguments")

        super(Experiment, self).__init__(conn, conf, _ExperimentService, "experiment", expt.id)

    def __repr__(self):
        return "<Experiment \"{}\">".format(self.name)

    @property
    def name(self):
        Message = _ExperimentService.GetExperimentById
        msg = Message(id=self.id)
        data = _utils.proto_to_json(msg)
        response = _utils.make_request("GET",
                                       "{}://{}/api/v1/modeldb/experiment/getExperimentById".format(self._conn.scheme, self._conn.socket),
                                       self._conn, params=data)
        _utils.raise_for_http_error(response)

        response_msg = _utils.json_to_proto(_utils.body_to_json(response), Message.Response)
        return response_msg.experiment.name

    @property
    def expt_runs(self):
        # get runs in this Experiment
        runs = ExperimentRuns(self._conn, self._conf)
        runs._msg.experiment_id = self.id
        return runs

    @staticmethod
    def _generate_default_name():
        return "Expt {}".format(_utils.generate_default_name())

    @classmethod
    def _get_by_id(cls, conn, id):
        Message = _ExperimentService.GetExperimentById
        msg = Message(id=id)
        response = conn.make_proto_request("GET",
                                           "/api/v1/modeldb/experiment/getExperimentById",
                                           params=msg)

        return conn.maybe_proto_response(response, Message.Response).experiment

    @classmethod
    def _get_by_name(cls, conn, name, proj_id):
        Message = _ExperimentService.GetExperimentByName
        msg = Message(project_id=proj_id, name=name)
        response = conn.make_proto_request("GET",
                                           "/api/v1/modeldb/experiment/getExperimentByName",
                                           params=msg)

        return conn.maybe_proto_response(response, Message.Response).experiment

    @staticmethod
    def _get(conn, proj_id=None, expt_name=None, _expt_id=None):
        if _expt_id is not None:
            return Experiment._get_by_id(conn, _expt_id)
        elif None not in (proj_id, expt_name):
            return Experiment._get_by_name(conn, expt_name, proj_id)
        else:
            raise ValueError("insufficient arguments")

    @classmethod
    def _create_internal(cls, conn, ctx, name, desc=None, tags=None, attrs=None, date_created=None):
        Message = _ExperimentService.CreateExperiment
        msg = Message(project_id=ctx.proj_id, name=name,
                      description=desc, tags=tags, attributes=attrs)
        response = conn.make_proto_request("POST",
                                           "/api/v1/modeldb/experiment/createExperiment",
                                           body=msg)
        return conn.must_proto_response(response, Message.Response).experiment

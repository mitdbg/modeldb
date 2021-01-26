import abc

from ..external import six

from .._protos.public.uac import Collaborator_pb2
from .._protos.public.common import CommonService_pb2

from .._internal_utils import _utils


@six.add_metaclass(abc.ABCMeta)
class _Visibility(object):
    @staticmethod
    def _from_public_within_org(public_within_org):
        # imports here to avoid circular import in Python 2
        from . import (
            OrgDefault,
            Private,
            _WorkspaceDefault,
        )

        if public_within_org is None:
            return _WorkspaceDefault()
        elif public_within_org:
            return OrgDefault()
        else:
            return Private()

    @abc.abstractmethod
    def _to_public_within_org(self):
        raise NotImplementedError

    @property
    def _custom_permission(self):
        return Collaborator_pb2.CollaboratorPermissions()

    @abc.abstractproperty
    def _visibility(self):
        raise NotImplementedError

    @property
    def _collaborator_type_str(self):
        """Mainly for endpoint."""
        return CommonService_pb2.CollaboratorTypeEnum.CollaboratorType.Name(
            self._custom_permission.collaborator_type
        )

    @property
    def _visibility_str(self):
        """Mainly for endpoint."""
        return Collaborator_pb2.ResourceVisibility.Name(
            self._visibility
        )

# -*- coding: utf-8 -*-

import abc

from verta.external import six

from verta._protos.public.monitoring import Alert_pb2 as _AlertService


@six.add_metaclass(abc.ABCMeta)
class _Alert(object):
    _TYPE = _AlertService.AlerterTypeEnum.UNKNOWN

    @abc.abstractmethod
    def _as_proto(self):
        raise NotImplementedError


class FixedAlert(_Alert):
    _TYPE = _AlertService.AlerterTypeEnum.FIXED

    def __init__(self, threshold):
        self._threshold = threshold

    def _as_proto(self):
        return _AlertService.AlertFixed(
            threshold=self._threshold,
        )


class ReferenceAlert(_Alert):
    _TYPE = _AlertService.AlerterTypeEnum.REFERENCE

    def __init__(self, threshold, reference_sample):
        self._threshold = threshold
        self._reference_sample = reference_sample

    def _as_proto(self):
        return _AlertService.AlertReference(
            threshold=self._threshold,
            reference_sample_id=self._reference_sample.id,
        )
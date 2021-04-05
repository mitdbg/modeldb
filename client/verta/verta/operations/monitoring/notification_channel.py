# -*- coding: utf-8 -*-

import abc

from verta.external import six

from verta._protos.public.monitoring import Alert_pb2 as _AlertService


@six.add_metaclass(abc.ABCMeta)
class _NotificationChannel(object):
    _TYPE = _AlertService.NotificationChannelTypeEnum.UNKNOWN

    @abc.abstractmethod
    def _as_proto(self):
        raise NotImplementedError


class SlackNotificationChannel(_NotificationChannel):
    _TYPE = _AlertService.NotificationChannelTypeEnum.SLACK

    def __init__(self, url):
        self._url = url

    def _as_proto(self):
        return _AlertService.NotificationChannelSlackWebhook(
            url=self._url,
        )
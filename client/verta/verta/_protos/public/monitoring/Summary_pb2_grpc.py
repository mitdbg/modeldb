# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
import grpc

from ..monitoring import Summary_pb2 as monitoring_dot_Summary__pb2


class SummaryServiceStub(object):
  # missing associated documentation comment in .proto file
  pass

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.createSummary = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/createSummary',
        request_serializer=monitoring_dot_Summary__pb2.CreateSummaryRequest.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.Summary.FromString,
        )
    self.findSummary = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/findSummary',
        request_serializer=monitoring_dot_Summary__pb2.FindSummaryRequest.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.FindSummaryRequest.Response.FromString,
        )
    self.deleteSummary = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/deleteSummary',
        request_serializer=monitoring_dot_Summary__pb2.DeleteSummaryRequest.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.Empty.FromString,
        )
    self.createValue = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/createValue',
        request_serializer=monitoring_dot_Summary__pb2.CreateSummaryValue.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.SummaryValue.FromString,
        )
    self.createSample = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/createSample',
        request_serializer=monitoring_dot_Summary__pb2.CreateSummarySample.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.SummarySample.FromString,
        )
    self.createSampleBatch = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/createSampleBatch',
        request_serializer=monitoring_dot_Summary__pb2.CreateSummarySampleBatch.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.Empty.FromString,
        )
    self.findSample = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/findSample',
        request_serializer=monitoring_dot_Summary__pb2.FindSummarySampleRequest.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.FindSummarySampleRequest.Response.FromString,
        )
    self.deleteSample = channel.unary_unary(
        '/ai.verta.monitoring.SummaryService/deleteSample',
        request_serializer=monitoring_dot_Summary__pb2.DeleteSummarySampleRequest.SerializeToString,
        response_deserializer=monitoring_dot_Summary__pb2.Empty.FromString,
        )


class SummaryServiceServicer(object):
  # missing associated documentation comment in .proto file
  pass

  def createSummary(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def findSummary(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def deleteSummary(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def createValue(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def createSample(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def createSampleBatch(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def findSample(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def deleteSample(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_SummaryServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'createSummary': grpc.unary_unary_rpc_method_handler(
          servicer.createSummary,
          request_deserializer=monitoring_dot_Summary__pb2.CreateSummaryRequest.FromString,
          response_serializer=monitoring_dot_Summary__pb2.Summary.SerializeToString,
      ),
      'findSummary': grpc.unary_unary_rpc_method_handler(
          servicer.findSummary,
          request_deserializer=monitoring_dot_Summary__pb2.FindSummaryRequest.FromString,
          response_serializer=monitoring_dot_Summary__pb2.FindSummaryRequest.Response.SerializeToString,
      ),
      'deleteSummary': grpc.unary_unary_rpc_method_handler(
          servicer.deleteSummary,
          request_deserializer=monitoring_dot_Summary__pb2.DeleteSummaryRequest.FromString,
          response_serializer=monitoring_dot_Summary__pb2.Empty.SerializeToString,
      ),
      'createValue': grpc.unary_unary_rpc_method_handler(
          servicer.createValue,
          request_deserializer=monitoring_dot_Summary__pb2.CreateSummaryValue.FromString,
          response_serializer=monitoring_dot_Summary__pb2.SummaryValue.SerializeToString,
      ),
      'createSample': grpc.unary_unary_rpc_method_handler(
          servicer.createSample,
          request_deserializer=monitoring_dot_Summary__pb2.CreateSummarySample.FromString,
          response_serializer=monitoring_dot_Summary__pb2.SummarySample.SerializeToString,
      ),
      'createSampleBatch': grpc.unary_unary_rpc_method_handler(
          servicer.createSampleBatch,
          request_deserializer=monitoring_dot_Summary__pb2.CreateSummarySampleBatch.FromString,
          response_serializer=monitoring_dot_Summary__pb2.Empty.SerializeToString,
      ),
      'findSample': grpc.unary_unary_rpc_method_handler(
          servicer.findSample,
          request_deserializer=monitoring_dot_Summary__pb2.FindSummarySampleRequest.FromString,
          response_serializer=monitoring_dot_Summary__pb2.FindSummarySampleRequest.Response.SerializeToString,
      ),
      'deleteSample': grpc.unary_unary_rpc_method_handler(
          servicer.deleteSample,
          request_deserializer=monitoring_dot_Summary__pb2.DeleteSummarySampleRequest.FromString,
          response_serializer=monitoring_dot_Summary__pb2.Empty.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'ai.verta.monitoring.SummaryService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))

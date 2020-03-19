# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
import grpc

from ....public.modeldb.versioning import VersioningService_pb2 as protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2


class VersioningServiceStub(object):
  # missing associated documentation comment in .proto file
  pass

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.ListRepositories = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListRepositories',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListRepositoriesRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListRepositoriesRequest.Response.FromString,
        )
    self.GetRepository = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/GetRepository',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetRepositoryRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetRepositoryRequest.Response.FromString,
        )
    self.CreateRepository = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/CreateRepository',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.Response.FromString,
        )
    self.UpdateRepository = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/UpdateRepository',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.Response.FromString,
        )
    self.DeleteRepository = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/DeleteRepository',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteRepositoryRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteRepositoryRequest.Response.FromString,
        )
    self.ListCommits = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListCommits',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitsRequest.Response.FromString,
        )
    self.GetCommit = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/GetCommit',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitRequest.Response.FromString,
        )
    self.CreateCommit = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/CreateCommit',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.CreateCommitRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.CreateCommitRequest.Response.FromString,
        )
    self.DeleteCommit = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/DeleteCommit',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteCommitRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteCommitRequest.Response.FromString,
        )
    self.ListCommitBlobs = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListCommitBlobs',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitBlobsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitBlobsRequest.Response.FromString,
        )
    self.ListCommitExperimentRuns = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListCommitExperimentRuns',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitExperimentRunsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitExperimentRunsRequest.Response.FromString,
        )
    self.ListBlobExperimentRuns = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListBlobExperimentRuns',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBlobExperimentRunsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBlobExperimentRunsRequest.Response.FromString,
        )
    self.GetCommitComponent = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/GetCommitComponent',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitComponentRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitComponentRequest.Response.FromString,
        )
    self.ComputeRepositoryDiff = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ComputeRepositoryDiff',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ComputeRepositoryDiffRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ComputeRepositoryDiffRequest.Response.FromString,
        )
    self.MergeRepositoryCommits = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/MergeRepositoryCommits',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.MergeRepositoryCommitsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.MergeRepositoryCommitsRequest.Response.FromString,
        )
    self.ListBranches = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListBranches',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchesRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchesRequest.Response.FromString,
        )
    self.GetBranch = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/GetBranch',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetBranchRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetBranchRequest.Response.FromString,
        )
    self.SetBranch = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/SetBranch',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetBranchRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetBranchRequest.Response.FromString,
        )
    self.DeleteBranch = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/DeleteBranch',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteBranchRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteBranchRequest.Response.FromString,
        )
    self.ListBranchCommits = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListBranchCommits',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchCommitsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchCommitsRequest.Response.FromString,
        )
    self.ListTags = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/ListTags',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListTagsRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListTagsRequest.Response.FromString,
        )
    self.GetTag = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/GetTag',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetTagRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetTagRequest.Response.FromString,
        )
    self.SetTag = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/SetTag',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetTagRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetTagRequest.Response.FromString,
        )
    self.DeleteTag = channel.unary_unary(
        '/ai.verta.modeldb.versioning.VersioningService/DeleteTag',
        request_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteTagRequest.SerializeToString,
        response_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteTagRequest.Response.FromString,
        )


class VersioningServiceServicer(object):
  # missing associated documentation comment in .proto file
  pass

  def ListRepositories(self, request, context):
    """CRUD for repositories
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def GetRepository(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def CreateRepository(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def UpdateRepository(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def DeleteRepository(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListCommits(self, request, context):
    """CRUD for commits
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def GetCommit(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def CreateCommit(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def DeleteCommit(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListCommitBlobs(self, request, context):
    """Getting blobs and folders in a commit
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListCommitExperimentRuns(self, request, context):
    """Getting ExperimentRuns linked to a commit
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListBlobExperimentRuns(self, request, context):
    """Getting ExperimentRuns linked to a blob
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def GetCommitComponent(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ComputeRepositoryDiff(self, request, context):
    """Git-like operations
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def MergeRepositoryCommits(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListBranches(self, request, context):
    """CRUD for Branches
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def GetBranch(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def SetBranch(self, request, context):
    """Unlike tags, branches can be updated. The API must verify that the new commit is a child of the current branch.
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def DeleteBranch(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListBranchCommits(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListTags(self, request, context):
    """CRUD for tags
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def GetTag(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def SetTag(self, request, context):
    """Can't update a tag. If someone tries to set one that exists, we should error.
    """
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def DeleteTag(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_VersioningServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'ListRepositories': grpc.unary_unary_rpc_method_handler(
          servicer.ListRepositories,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListRepositoriesRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListRepositoriesRequest.Response.SerializeToString,
      ),
      'GetRepository': grpc.unary_unary_rpc_method_handler(
          servicer.GetRepository,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetRepositoryRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetRepositoryRequest.Response.SerializeToString,
      ),
      'CreateRepository': grpc.unary_unary_rpc_method_handler(
          servicer.CreateRepository,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.Response.SerializeToString,
      ),
      'UpdateRepository': grpc.unary_unary_rpc_method_handler(
          servicer.UpdateRepository,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetRepository.Response.SerializeToString,
      ),
      'DeleteRepository': grpc.unary_unary_rpc_method_handler(
          servicer.DeleteRepository,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteRepositoryRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteRepositoryRequest.Response.SerializeToString,
      ),
      'ListCommits': grpc.unary_unary_rpc_method_handler(
          servicer.ListCommits,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitsRequest.Response.SerializeToString,
      ),
      'GetCommit': grpc.unary_unary_rpc_method_handler(
          servicer.GetCommit,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitRequest.Response.SerializeToString,
      ),
      'CreateCommit': grpc.unary_unary_rpc_method_handler(
          servicer.CreateCommit,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.CreateCommitRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.CreateCommitRequest.Response.SerializeToString,
      ),
      'DeleteCommit': grpc.unary_unary_rpc_method_handler(
          servicer.DeleteCommit,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteCommitRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteCommitRequest.Response.SerializeToString,
      ),
      'ListCommitBlobs': grpc.unary_unary_rpc_method_handler(
          servicer.ListCommitBlobs,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitBlobsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitBlobsRequest.Response.SerializeToString,
      ),
      'ListCommitExperimentRuns': grpc.unary_unary_rpc_method_handler(
          servicer.ListCommitExperimentRuns,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitExperimentRunsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListCommitExperimentRunsRequest.Response.SerializeToString,
      ),
      'ListBlobExperimentRuns': grpc.unary_unary_rpc_method_handler(
          servicer.ListBlobExperimentRuns,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBlobExperimentRunsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBlobExperimentRunsRequest.Response.SerializeToString,
      ),
      'GetCommitComponent': grpc.unary_unary_rpc_method_handler(
          servicer.GetCommitComponent,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitComponentRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetCommitComponentRequest.Response.SerializeToString,
      ),
      'ComputeRepositoryDiff': grpc.unary_unary_rpc_method_handler(
          servicer.ComputeRepositoryDiff,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ComputeRepositoryDiffRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ComputeRepositoryDiffRequest.Response.SerializeToString,
      ),
      'MergeRepositoryCommits': grpc.unary_unary_rpc_method_handler(
          servicer.MergeRepositoryCommits,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.MergeRepositoryCommitsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.MergeRepositoryCommitsRequest.Response.SerializeToString,
      ),
      'ListBranches': grpc.unary_unary_rpc_method_handler(
          servicer.ListBranches,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchesRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchesRequest.Response.SerializeToString,
      ),
      'GetBranch': grpc.unary_unary_rpc_method_handler(
          servicer.GetBranch,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetBranchRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetBranchRequest.Response.SerializeToString,
      ),
      'SetBranch': grpc.unary_unary_rpc_method_handler(
          servicer.SetBranch,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetBranchRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetBranchRequest.Response.SerializeToString,
      ),
      'DeleteBranch': grpc.unary_unary_rpc_method_handler(
          servicer.DeleteBranch,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteBranchRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteBranchRequest.Response.SerializeToString,
      ),
      'ListBranchCommits': grpc.unary_unary_rpc_method_handler(
          servicer.ListBranchCommits,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchCommitsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListBranchCommitsRequest.Response.SerializeToString,
      ),
      'ListTags': grpc.unary_unary_rpc_method_handler(
          servicer.ListTags,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListTagsRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.ListTagsRequest.Response.SerializeToString,
      ),
      'GetTag': grpc.unary_unary_rpc_method_handler(
          servicer.GetTag,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetTagRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.GetTagRequest.Response.SerializeToString,
      ),
      'SetTag': grpc.unary_unary_rpc_method_handler(
          servicer.SetTag,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetTagRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.SetTagRequest.Response.SerializeToString,
      ),
      'DeleteTag': grpc.unary_unary_rpc_method_handler(
          servicer.DeleteTag,
          request_deserializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteTagRequest.FromString,
          response_serializer=protos_dot_public_dot_modeldb_dot_versioning_dot_VersioningService__pb2.DeleteTagRequest.Response.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'ai.verta.modeldb.versioning.VersioningService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))

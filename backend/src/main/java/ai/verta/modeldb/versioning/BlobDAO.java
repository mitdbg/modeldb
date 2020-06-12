package ai.verta.modeldb.versioning;

import ai.verta.modeldb.DatasetVersion;
import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.entities.versioning.RepositoryEntity;
import ai.verta.modeldb.metadata.MetadataDAO;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenBlobDiff;
import ai.verta.modeldb.versioning.blob.container.BlobContainer;
import com.google.protobuf.ProtocolStringList;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

public interface BlobDAO {

  String setBlobs(Session session, List<BlobContainer> blobsList, FileHasher fileHasher)
      throws NoSuchAlgorithmException, ModelDBException;

  GetCommitComponentRequest.Response getCommitComponent(
      RepositoryFunction repositoryFunction, String commitHash, ProtocolStringList locationList)
      throws NoSuchAlgorithmException, ModelDBException;

  ListCommitBlobsRequest.Response getCommitBlobsList(
      RepositoryFunction repositoryFunction, String commitHash, List<String> locationList)
      throws NoSuchAlgorithmException, ModelDBException;

  DatasetVersion convertToDatasetVersion(
      MetadataDAO metadataDAO, RepositoryEntity repositoryEntity, String commitHash)
      throws ModelDBException;

  Map<String, BlobExpanded> getCommitBlobMap(
      Session session, String folderHash, List<String> locationList) throws ModelDBException;

  Map<String, Map.Entry<BlobExpanded, String>> getCommitBlobMapWithHash(
      Session session, String folderHash, List<String> locationList, List<BlobType> blobTypeList)
      throws ModelDBException;

  ComputeRepositoryDiffRequest.Response computeRepositoryDiff(
      RepositoryDAO repositoryDAO, ComputeRepositoryDiffRequest request) throws ModelDBException;

  List<BlobContainer> convertBlobDiffsToBlobs(
      List<AutogenBlobDiff> blobDiffs,
      RepositoryFunction repositoryFunction,
      CommitFunction commitFunction)
      throws ModelDBException;

  MergeRepositoryCommitsRequest.Response mergeCommit(
      RepositoryDAO repositoryDAO, MergeRepositoryCommitsRequest request)
      throws ModelDBException, NoSuchAlgorithmException;

  RevertRepositoryCommitsRequest.Response revertCommit(
      RepositoryDAO repositoryDAO, RevertRepositoryCommitsRequest request)
      throws ModelDBException, NoSuchAlgorithmException;

  FindRepositoriesBlobs.Response findRepositoriesBlobs(FindRepositoriesBlobs request)
      throws ModelDBException;
}

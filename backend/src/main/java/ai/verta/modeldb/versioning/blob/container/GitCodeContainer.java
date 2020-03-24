package ai.verta.modeldb.versioning.blob.container;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenGitCodeBlob;
import io.grpc.Status.Code;

public class GitCodeContainer implements BlobContainerBase {

  private final AutogenGitCodeBlob autogenGitCodeBlob;

  public GitCodeContainer(AutogenGitCodeBlob autogenGitCodeBlob) {
    this.autogenGitCodeBlob = autogenGitCodeBlob;
  }

  @Override
  public void validate() throws ModelDBException {
    if (autogenGitCodeBlob.getRepo().isEmpty()) {
      throw new ModelDBException("Code repository path should not be empty", Code.INVALID_ARGUMENT);
    }
  }

}

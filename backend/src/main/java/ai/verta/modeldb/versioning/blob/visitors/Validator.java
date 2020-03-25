package ai.verta.modeldb.versioning.blob.visitors;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenBlobDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenCodeBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenConfigBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenContinuousHyperparameterSetConfigBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenDatasetBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenDiffStatusEnumDiffStatus;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenDiscreteHyperparameterSetConfigBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenDockerEnvironmentBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenDockerEnvironmentDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenEnvironmentBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenEnvironmentDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenEnvironmentVariablesBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenEnvironmentVariablesDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenGitCodeBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenHyperparameterSetConfigBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenHyperparameterSetConfigDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenHyperparameterValuesConfigBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenNotebookCodeBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPathDatasetBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPathDatasetComponentBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPathDatasetComponentDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPathDatasetDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPythonEnvironmentBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPythonEnvironmentDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPythonRequirementEnvironmentBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenPythonRequirementEnvironmentDiff;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenS3DatasetBlob;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.AutogenS3DatasetComponentBlob;
import ai.verta.modeldb.versioning.blob.diff.ProtoType;
import io.grpc.Status;
import io.grpc.Status.Code;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator extends Visitor {

  private static final String HAS_A_STRING_VALUE_WHICH_IS_NOT_IN_A_VALID_NUMERIC_NOTATION =
      "has a STRING_VALUE which is not in a valid numeric notation";

  public Validator() {}

  private boolean isNull(ProtoType proto) {
    return proto == null;
  }

  public void validate(AutogenBlobDiff autogenBlobDiff) throws ModelDBException {
    if (isNull(autogenBlobDiff)) {
      return;
    }
    if (autogenBlobDiff.getLocation().isEmpty()) {
      throw new ModelDBException("Blob diff location is empty", Status.Code.INVALID_ARGUMENT);
    }
    autogenBlobDiff.preVisitDeep(this);
  }

  public void validate(AutogenBlob autogenBlob) throws ModelDBException {
    preVisitDeep(autogenBlob);
  }

  private void preVisitDeep(ProtoType proto) throws ModelDBException {
    if (isNull(proto)) {
      return;
    }
    proto.preVisitDeep(this);
  }

  @Override
  public void preVisitDeepAutogenCodeBlob(AutogenCodeBlob blob) throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenConfigBlob(AutogenConfigBlob blob) throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenContinuousHyperparameterSetConfigBlob(
      AutogenContinuousHyperparameterSetConfigBlob autogenContinuousHyperparameterSetConfigBlob)
      throws ModelDBException {
    if (isNull(autogenContinuousHyperparameterSetConfigBlob)) {
      return;
    }

    AutogenHyperparameterValuesConfigBlob beginSetConfigBlob =
        autogenContinuousHyperparameterSetConfigBlob.getIntervalBegin();
    AutogenHyperparameterValuesConfigBlob endSetConfigBlob =
        autogenContinuousHyperparameterSetConfigBlob.getIntervalEnd();
    AutogenHyperparameterValuesConfigBlob stepSetConfigBlob =
        autogenContinuousHyperparameterSetConfigBlob.getIntervalStep();
    if (autogenContinuousHyperparameterSetConfigBlob.getIntervalBegin() == null) {
      throw new ModelDBException(
          "Hyperparameter set doesn't have interval begin", Code.INVALID_ARGUMENT);
    }
    if (autogenContinuousHyperparameterSetConfigBlob.getIntervalEnd() == null) {
      throw new ModelDBException(
          "Hyperparameter set doesn't have interval end", Code.INVALID_ARGUMENT);
    }
    if (autogenContinuousHyperparameterSetConfigBlob.getIntervalStep() == null) {
      throw new ModelDBException(
          "Hyperparameter set doesn't have interval step", Code.INVALID_ARGUMENT);
    }

    if (isNull(beginSetConfigBlob) || isNull(endSetConfigBlob) || isNull(stepSetConfigBlob)) {
      throw new ModelDBException(
          "Hyperparameter continuous set doesn't have one of the INT_VALUE, FLOAT_VALUE, STRING_VALUE",
          Code.INVALID_ARGUMENT);
    }

    if (beginSetConfigBlob.getStringValue() != null) {
      try {
        Double.parseDouble(beginSetConfigBlob.getStringValue());
      } catch (Exception ex) {
        throw new ModelDBException(
            "beginSetConfigBlob " + HAS_A_STRING_VALUE_WHICH_IS_NOT_IN_A_VALID_NUMERIC_NOTATION,
            Code.INVALID_ARGUMENT);
      }
    }
    if (endSetConfigBlob.getStringValue() != null) {
      try {
        Double.parseDouble(endSetConfigBlob.getStringValue());
      } catch (Exception ex) {
        throw new ModelDBException(
            "endSetConfigBlob " + HAS_A_STRING_VALUE_WHICH_IS_NOT_IN_A_VALID_NUMERIC_NOTATION,
            Code.INVALID_ARGUMENT);
      }
    }
    if (stepSetConfigBlob.getStringValue() != null) {
      try {
        Double.parseDouble(stepSetConfigBlob.getStringValue());
      } catch (Exception ex) {
        throw new ModelDBException(
            "stepSetConfigBlob " + HAS_A_STRING_VALUE_WHICH_IS_NOT_IN_A_VALID_NUMERIC_NOTATION,
            Code.INVALID_ARGUMENT);
      }
    }
    autogenContinuousHyperparameterSetConfigBlob.preVisitDeep(this);
  }

  @Override
  public void preVisitDeepAutogenDatasetBlob(AutogenDatasetBlob blob) throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenDiscreteHyperparameterSetConfigBlob(
      AutogenDiscreteHyperparameterSetConfigBlob autogenDiscreteHyperparameterSetConfigBlob)
      throws ModelDBException {
    if (isNull(autogenDiscreteHyperparameterSetConfigBlob)) {
      return;
    }
    if (autogenDiscreteHyperparameterSetConfigBlob.getValues() == null
        || autogenDiscreteHyperparameterSetConfigBlob.getValues().size() == 0) {
      throw new ModelDBException("No values for set found");
    }
    autogenDiscreteHyperparameterSetConfigBlob.preVisitDeep(this);
  }

  @Override
  public void preVisitDeepAutogenDockerEnvironmentBlob(AutogenDockerEnvironmentBlob blob)
      throws ModelDBException {
    if (isNull(blob)) {
      return;
    }
    if (blob.getRepository().isEmpty()) {
      throw new ModelDBException(
          "Environment repository path should not be empty", Code.INVALID_ARGUMENT);
    }
  }

  @Override
  public void preVisitDeepAutogenDockerEnvironmentDiff(AutogenDockerEnvironmentDiff blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenEnvironmentBlob(AutogenEnvironmentBlob blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenEnvironmentDiff(AutogenEnvironmentDiff blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepListOfAutogenEnvironmentVariablesBlob(
      List<AutogenEnvironmentVariablesBlob> lst) throws ModelDBException {
    Set<String> variableNames = new HashSet<>();
    for (AutogenEnvironmentVariablesBlob blob : lst) {
      blob.preVisitDeep(this);
      variableNames.add(blob.getName());
    }
    if (variableNames.size() != lst.size()) {
      throw new ModelDBException("There are recurring variables", Code.INVALID_ARGUMENT);
    }
  }

  private static final String PATTERN = "[a-zA-Z0-9_-]+";

  @Override
  public void preVisitAutogenEnvironmentVariablesBlob(AutogenEnvironmentVariablesBlob blob)
      throws ModelDBException {
    if (isNull(blob)) {
      return;
    }
    if (!Pattern.compile(PATTERN).matcher(blob.getName()).matches()) {
      throw new ModelDBException(
          "Environment variable name: "
              + blob.getName()
              + " should be not empty, should contain only alphanumeric or underscore",
          Code.INVALID_ARGUMENT);
    }
  }

  @Override
  public void preVisitDeepAutogenEnvironmentVariablesDiff(AutogenEnvironmentVariablesDiff blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenGitCodeBlob(AutogenGitCodeBlob blob) throws ModelDBException {
    if (isNull(blob)) {
      return;
    }
    if (blob.getRepo().isEmpty()) {
      throw new ModelDBException("Code repository path should not be empty", Code.INVALID_ARGUMENT);
    }
  }

  @Override
  public void preVisitDeepAutogenHyperparameterSetConfigBlob(
      AutogenHyperparameterSetConfigBlob blob) throws ModelDBException {
    if (isNull(blob)) {
      return;
    }
    final String name = blob.getName();
    if (name.isEmpty()) {
      throw new ModelDBException("Hyperparameter set name is empty", Code.INVALID_ARGUMENT);
    }
    blob.preVisitDeep(this);
  }

  @Override
  public void preVisitDeepAutogenHyperparameterSetConfigDiff(
      AutogenHyperparameterSetConfigDiff blob) throws ModelDBException {
    preVisitDeep(blob);
  }

  private static boolean isNull(AutogenHyperparameterValuesConfigBlob beginSetConfigBlob) {
    return beginSetConfigBlob == null
        || beginSetConfigBlob.getFloatValue() == null
            && beginSetConfigBlob.getIntValue() == null
            && beginSetConfigBlob.getStringValue() == null;
  }

  @Override
  public void preVisitDeepAutogenHyperparameterValuesConfigBlob(
      AutogenHyperparameterValuesConfigBlob blob) throws ModelDBException {
    if (isNull(blob)) {
      throw new ModelDBException("Hyperparameter value has unknown type");
    }
    blob.preVisitDeep(this);
  }

  @Override
  public void preVisitDeepAutogenNotebookCodeBlob(AutogenNotebookCodeBlob blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenPathDatasetBlob(AutogenPathDatasetBlob blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenPathDatasetComponentBlob(AutogenPathDatasetComponentBlob blob)
      throws ModelDBException {
    if (blob.getPath().isEmpty()) {
      throw new ModelDBException("Dataset path is empty", Code.INVALID_ARGUMENT);
    }
  }

  @Override
  public void preVisitDeepAutogenPathDatasetComponentDiff(AutogenPathDatasetComponentDiff blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenPathDatasetDiff(AutogenPathDatasetDiff blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenPythonEnvironmentBlob(AutogenPythonEnvironmentBlob blob)
      throws ModelDBException {
    preVisitDeep(blob);
    Set<AutogenPythonRequirementEnvironmentBlob> pythonRequirementHash =
        new HashSet<>(blob.getRequirements());
    if (pythonRequirementHash.size() != blob.getRequirements().size()) {
      throw new ModelDBException("There are recurring requirements", Code.INVALID_ARGUMENT);
    }
    Set<AutogenPythonRequirementEnvironmentBlob> pythonConstraintHash =
        new HashSet<>(blob.getConstraints());
    if (pythonConstraintHash.size() != blob.getConstraints().size()) {
      throw new ModelDBException("There are recurring constraints", Code.INVALID_ARGUMENT);
    }
  }

  @Override
  public void preVisitDeepAutogenPythonEnvironmentDiff(AutogenPythonEnvironmentDiff blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenPythonRequirementEnvironmentBlob(
      AutogenPythonRequirementEnvironmentBlob blob) throws ModelDBException {
    if (blob.getLibrary().isEmpty()) {
      throw new ModelDBException(
          "Requirement or constraint library name should not be empty", Code.INVALID_ARGUMENT);
    }
  }

  @Override
  public void preVisitDeepAutogenPythonRequirementEnvironmentDiff(
      AutogenPythonRequirementEnvironmentDiff blob) throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenS3DatasetBlob(AutogenS3DatasetBlob blob) throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenS3DatasetComponentBlob(AutogenS3DatasetComponentBlob blob)
      throws ModelDBException {
    preVisitDeep(blob);
  }

  @Override
  public void preVisitDeepAutogenDiffStatusEnumDiffStatus(AutogenDiffStatusEnumDiffStatus blob)
      throws ModelDBException {
    if (blob == null) {
      throw new ModelDBException("Unknown type", Status.Code.INVALID_ARGUMENT);
    }
    switch (blob.toProto()) {
      case DELETED:
      case ADDED:
      case MODIFIED:
        break;
      default:
        throw new ModelDBException("Unknown type", Status.Code.INVALID_ARGUMENT);
    }
  }
}

package ai.verta.modeldb.versioning.blob.diff;

import static ai.verta.modeldb.versioning.blob.diff.DiffComputer.toMap;

import ai.verta.modeldb.versioning.DiffStatusEnum;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: handle collisions instead of just overwriting? It will be useful for mergingit
public class DiffMerger {
  public static <B, D, F, DF, L> F merge(
      B a,
      D d,
      Function<B, F> getterA,
      Function<D, DF> getterD,
      Function4<F, DF, L, F> merger,
      L conflictKeys) {
    if (d == null || Utils.getOrNull(d, getterD) == null) {
      return Utils.getOrNull(a, getterA);
    }
    return merger.apply(Utils.getOrNull(a, getterA), Utils.getOrNull(d, getterD), conflictKeys);
  }

  public static <B, D, F extends ProtoType, DF> List<F> mergeList(
      B a,
      D d,
      Function<B, List<F>> getterA,
      Function<D, List<DF>> getterD,
      Function<F, String> hasherA,
      Function<DF, String> hasherD,
      Function<DF, AutogenDiffStatusEnumDiffStatus> status,
      Function<DF, F> getA,
      Function<DF, F> getB,
      Function3<Set<F>, DF, F> merger,
      HashSet<String> conflictKeys) {
    Map<String, HashSet<F>> mapA = a == null ? new HashMap<>() : toMap(getterA.apply(a), hasherA);
    Map<String, HashSet<DF>> mapD = d == null ? new HashMap<>() : toMap(getterD.apply(d), hasherD);

    HashSet<String> keys = new HashSet<>();
    keys.addAll(mapA.keySet());
    keys.addAll(mapD.keySet());

    List<F> ret =
        keys.stream()
            .flatMap(
                key -> {
                  HashSet<F> elA = mapA.getOrDefault(key, new HashSet<>());
                  HashSet<DF> elD = mapD.get(key);

                  if (elD != null) {
                    for (DF el : elD) {
                      AutogenDiffStatusEnumDiffStatus elStatus = status.apply(el);
                      F diffA = getA.apply(el);
                      F diffB = getB.apply(el);
                      if (elStatus == null
                          || elStatus.Status == DiffStatusEnum.DiffStatus.DELETED) {
                        if (diffA != null) {
                          elA.remove(diffA);
                        }
                      } else if (elStatus.Status == DiffStatusEnum.DiffStatus.ADDED) {
                        if (diffB != null) {
                          if (elA.isEmpty()) elA.add(diffB);
                          else if (!elA.contains(diffB)) {
                            conflictKeys.add(key);
                          }
                        }
                      } else if (elStatus.Status == DiffStatusEnum.DiffStatus.MODIFIED) {
                        // TODO: error otherwise
                        if (diffA != null && diffB != null) {
                          if (!elA.contains(diffA)) { // diff is applied on a state different from A
                            if (!elA.contains(
                                diffB)) { // post diff the end state is different from A
                              conflictKeys.add(key);
                            }
                          } else {
                            elA.remove(diffA);
                            // Send the current set of keys colliding in case the merger wants to
                            // handle in a special way
                            // The default behavior should be just to replace A with B
                            if (merger != null) {
                              F merged = merger.apply(elA, el);
                              elA.add(merged);
                            } else {
                              elA.add(diffB);
                            }
                          }
                        }
                      }
                    }
                  }
                  if (elA == null || elA.isEmpty()) return null;
                  return elA.stream();
                })
            .map(Utils::removeEmpty)
            .filter(
                Objects::nonNull) // Remove elements that became null in the process of applying the
            // diff for some reason
            .collect(Collectors.toList());
    if (ret.isEmpty()) {
      return null;
    }
    return ret;
  }

  public static <T, T2> T mergeLast(
      T a,
      T2 d,
      Function<T2, T> getA,
      Function<T2, T> getB,
      Function<T2, AutogenDiffStatusEnumDiffStatus> getStatus,
      Function<T, String> hasherA,
      HashSet<String> conflictKeys) {
    if (d == null) {
      return a;
    }

    AutogenDiffStatusEnumDiffStatus status = getStatus.apply(d);
    if (status.Status == DiffStatusEnum.DiffStatus.ADDED
        || status.Status == DiffStatusEnum.DiffStatus.MODIFIED) {
      T dA = getA.apply(d);
      T dB = getB.apply(d);
      if (a != null && !(a.equals(dB) || a.equals(dA)) && hasherA != null) {
        conflictKeys.add(hasherA.apply(a));
        return null;
      }

      return dB;
    }
    if (status.Status == DiffStatusEnum.DiffStatus.DELETED) {
      return null;
    }
    return a;
  }

  private static Boolean diffStatusDeleted = false;

  public static AutogenBlob mergeBlob(
      AutogenBlob a, AutogenBlobDiff d, HashSet<String> conflictKeys) {
    diffStatusDeleted = d != null && d.getStatus() != null && d.getStatus().isDeleted();
    if (a != null
        && d != null
        && !diffStatusDeleted
        && !a.toProto()
            .getContentCase()
            .name()
            .equalsIgnoreCase(d.toProto().getContentCase().name())) {
      conflictKeys.add(a.toString());
    }
    return Utils.removeEmpty(
        new AutogenBlob()
            .setCode(
                merge(
                    a,
                    d,
                    AutogenBlob::getCode,
                    AutogenBlobDiff::getCode,
                    DiffMerger::mergeCode,
                    conflictKeys))
            .setConfig(
                merge(
                    a,
                    d,
                    AutogenBlob::getConfig,
                    AutogenBlobDiff::getConfig,
                    DiffMerger::mergeConfig,
                    conflictKeys))
            .setDataset(
                merge(
                    a,
                    d,
                    AutogenBlob::getDataset,
                    AutogenBlobDiff::getDataset,
                    DiffMerger::mergeDataset,
                    conflictKeys))
            .setEnvironment(
                merge(
                    a,
                    d,
                    AutogenBlob::getEnvironment,
                    AutogenBlobDiff::getEnvironment,
                    DiffMerger::mergeEnvironment,
                    conflictKeys)));
  }

  public static AutogenCodeBlob mergeCode(
      AutogenCodeBlob a, AutogenCodeDiff d, HashSet<String> conflictKeys) {
    if (a != null
        && d != null
        && !diffStatusDeleted
        && a.toProto().getContentCase().getNumber() != d.toProto().getContentCase().getNumber()) {
      conflictKeys.add(a.toString());
    }
    return Utils.removeEmpty(
        new AutogenCodeBlob()
            .setGit(
                merge(
                    a,
                    d,
                    AutogenCodeBlob::getGit,
                    AutogenCodeDiff::getGit,
                    DiffMerger::mergeGitCode,
                    conflictKeys))
            .setNotebook(
                merge(
                    a,
                    d,
                    AutogenCodeBlob::getNotebook,
                    AutogenCodeDiff::getNotebook,
                    DiffMerger::mergeNotebookCode,
                    conflictKeys)));
  }

  public static AutogenGitCodeBlob mergeGitCode(
      AutogenGitCodeBlob a, AutogenGitCodeDiff d, HashSet<String> conflictKeys) {
    if (a == null && d == null) return null;
    if (d == null) return a;
    if (d.getStatus().isDeleted()) return null;
    return Utils.removeEmpty(
        mergeLast(
            a,
            d,
            x -> d.getA(),
            x -> d.getB(),
            AutogenGitCodeDiff::getStatus,
            AutogenGitCodeBlob::getRepo,
            conflictKeys));
  }

  public static AutogenNotebookCodeBlob mergeNotebookCode(
      AutogenNotebookCodeBlob a, AutogenNotebookCodeDiff d, HashSet<String> conflictKeys) {
    return Utils.removeEmpty(
        new AutogenNotebookCodeBlob()
            .setGitRepo(
                merge(
                    a,
                    d,
                    AutogenNotebookCodeBlob::getGitRepo,
                    AutogenNotebookCodeDiff::getGitRepo,
                    DiffMerger::mergeGitCode,
                    conflictKeys))
            .setPath(
                merge(
                    a,
                    d,
                    AutogenNotebookCodeBlob::getPath,
                    AutogenNotebookCodeDiff::getPath,
                    DiffMerger::mergePathDatasetComponent,
                    conflictKeys)));
  }

  public static AutogenConfigBlob mergeConfig(
      AutogenConfigBlob a, AutogenConfigDiff d, HashSet<String> conflictKeys) {
    return Utils.removeEmpty(
        new AutogenConfigBlob()
            .setHyperparameters(
                mergeList(
                    a,
                    d,
                    AutogenConfigBlob::getHyperparameters,
                    AutogenConfigDiff::getHyperparameters,
                    AutogenHyperparameterConfigBlob::getName,
                    x -> Utils.either(x.getA(), x.getB(), AutogenHyperparameterConfigBlob::getName),
                    AutogenHyperparameterConfigDiff::getStatus,
                    AutogenHyperparameterConfigDiff::getA,
                    AutogenHyperparameterConfigDiff::getB,
                    null,
                    conflictKeys))
            .setHyperparameterSet(
                mergeList(
                    a,
                    d,
                    AutogenConfigBlob::getHyperparameterSet,
                    AutogenConfigDiff::getHyperparameterSet,
                    AutogenHyperparameterSetConfigBlob::getName,
                    x ->
                        Utils.either(
                            x.getA(), x.getB(), AutogenHyperparameterSetConfigBlob::getName),
                    AutogenHyperparameterSetConfigDiff::getStatus,
                    AutogenHyperparameterSetConfigDiff::getA,
                    AutogenHyperparameterSetConfigDiff::getB,
                    null,
                    conflictKeys)));
  }

  public static AutogenDatasetBlob mergeDataset(
      AutogenDatasetBlob a, AutogenDatasetDiff d, HashSet<String> conflictKeys) {
    if (a != null
        && d != null
        && !diffStatusDeleted
        && a.toProto().getContentCase().getNumber() != d.toProto().getContentCase().getNumber()) {
      conflictKeys.add(a.toString());
    }
    return Utils.removeEmpty(
        new AutogenDatasetBlob()
            .setPath(
                merge(
                    a,
                    d,
                    AutogenDatasetBlob::getPath,
                    AutogenDatasetDiff::getPath,
                    DiffMerger::mergePathDataset,
                    conflictKeys))
            .setS3(
                merge(
                    a,
                    d,
                    AutogenDatasetBlob::getS3,
                    AutogenDatasetDiff::getS3,
                    DiffMerger::mergeS3Dataset,
                    conflictKeys))
            .setQuery(
                merge(
                    a,
                    d,
                    AutogenDatasetBlob::getQuery,
                    AutogenDatasetDiff::getQuery,
                    DiffMerger::mergeQueryDataset,
                    conflictKeys)));
  }

  public static AutogenPathDatasetBlob mergePathDataset(
      AutogenPathDatasetBlob a, AutogenPathDatasetDiff d, HashSet<String> conflictKeys) {
    return Utils.removeEmpty(
        new AutogenPathDatasetBlob()
            .setComponents(
                mergeList(
                    a,
                    d,
                    AutogenPathDatasetBlob::getComponents,
                    AutogenPathDatasetDiff::getComponents,
                    AutogenPathDatasetComponentBlob::getPath,
                    x -> Utils.either(x.getA(), x.getB(), AutogenPathDatasetComponentBlob::getPath),
                    AutogenPathDatasetComponentDiff::getStatus,
                    AutogenPathDatasetComponentDiff::getA,
                    AutogenPathDatasetComponentDiff::getB,
                    null,
                    conflictKeys)));
  }

  public static AutogenPathDatasetComponentBlob mergePathDatasetComponent(
      AutogenPathDatasetComponentBlob a,
      AutogenPathDatasetComponentDiff d,
      HashSet<String> conflictKeys) {
    return d.getB();
  }

  public static AutogenQueryDatasetBlob mergeQueryDataset(
      AutogenQueryDatasetBlob a, AutogenQueryDatasetDiff d, HashSet<String> conflictKeys) {
    return Utils.removeEmpty(
        new AutogenQueryDatasetBlob()
            .setComponents(
                mergeList(
                    a,
                    d,
                    AutogenQueryDatasetBlob::getComponents,
                    AutogenQueryDatasetDiff::getComponents,
                    AutogenQueryDatasetComponentBlob::toString,
                    x ->
                        Utils.either(
                            x.getA(), x.getB(), AutogenQueryDatasetComponentBlob::toString),
                    AutogenQueryDatasetComponentDiff::getStatus,
                    AutogenQueryDatasetComponentDiff::getA,
                    AutogenQueryDatasetComponentDiff::getB,
                    null,
                    conflictKeys)));
  }
  /*
  * B AutogenQueryDatasetBlob
  * D AutogenQueryDatasetDiff
  * DF AutogenQueryDatasetComponentDiff
  * F AutogenQueryDatasetComponentBlob
  *
                    mergeList(
                        a,
                        d,
                        AutogenQueryDatasetBlob::getComponents,
                        AutogenQueryDatasetDiff::getComponents,
                        AutogenQueryDatasetComponentBlob::ToString,
                        x -> Utils.either(x.getA(), x.getB(), AutogenQueryDatasetComponentBlob::ToString),
                        AutogenQueryDatasetComponentDiff::getStatus,
                        AutogenQueryDatasetComponentDiff::getA,
                        AutogenQueryDatasetComponentDiff::getB,
                        null,
                        conflictKeys)*/
  /*
  	 * (
  B a,
  D d,
  Function<B, List<F>> getterA,
  Function<D, List<DF>> getterD,
  Function<F, String> hasherA,
  Function<DF, String> hasherD,
  Function<DF, AutogenDiffStatusEnumDiffStatus> status,
  Function<DF, F> getA,
  Function<DF, F> getB,
  Function3<Set<F>, DF, F> merger,
  HashSet<String> conflictKeys)*/

  public static AutogenQueryDatasetComponentBlob mergeQueryDatasetComponent(
      AutogenQueryDatasetComponentBlob a,
      AutogenQueryDatasetComponentDiff d,
      HashSet<String> conflictKeys) {
    return d.getB();
  }

  public static AutogenS3DatasetBlob mergeS3Dataset(
      AutogenS3DatasetBlob a, AutogenS3DatasetDiff d, HashSet<String> conflictKeys) {
    return Utils.removeEmpty(
        new AutogenS3DatasetBlob()
            .setComponents(
                mergeList(
                    a,
                    d,
                    AutogenS3DatasetBlob::getComponents,
                    AutogenS3DatasetDiff::getComponents,
                    x ->
                        Utils.getOrNull(
                            Utils.getOrNull(x, AutogenS3DatasetComponentBlob::getPath),
                            AutogenPathDatasetComponentBlob::getPath),
                    x ->
                        Utils.getOrNull(
                            Utils.either(
                                x.getA(), x.getB(), AutogenS3DatasetComponentBlob::getPath),
                            AutogenPathDatasetComponentBlob::getPath),
                    x -> Utils.getOrNull(x, AutogenS3DatasetComponentDiff::getStatus),
                    x ->
                        new AutogenS3DatasetComponentBlob()
                            .setPath(
                                Utils.getOrNull(
                                    Utils.getOrNull(x, AutogenS3DatasetComponentDiff::getA),
                                    AutogenS3DatasetComponentBlob::getPath))
                            .setS3VersionId(
                                Utils.getOrNull(
                                    Utils.getOrNull(x, AutogenS3DatasetComponentDiff::getA),
                                    AutogenS3DatasetComponentBlob::getS3VersionId)),
                    x ->
                        new AutogenS3DatasetComponentBlob()
                            .setPath(
                                Utils.getOrNull(
                                    Utils.getOrNull(x, AutogenS3DatasetComponentDiff::getB),
                                    AutogenS3DatasetComponentBlob::getPath))
                            .setS3VersionId(
                                Utils.getOrNull(
                                    Utils.getOrNull(x, AutogenS3DatasetComponentDiff::getB),
                                    AutogenS3DatasetComponentBlob::getS3VersionId)),
                    null,
                    conflictKeys)));
  }

  public static AutogenEnvironmentBlob mergeEnvironment(
      AutogenEnvironmentBlob a, AutogenEnvironmentDiff d, HashSet<String> conflictKeys) {
    if (a != null
        && d != null
        && !diffStatusDeleted
        && a.toProto().getContentCase().getNumber() != d.toProto().getContentCase().getNumber()) {
      conflictKeys.add(a.toString());
    }
    return Utils.removeEmpty(
        new AutogenEnvironmentBlob()
            .setPython(
                merge(
                    a,
                    d,
                    AutogenEnvironmentBlob::getPython,
                    AutogenEnvironmentDiff::getPython,
                    DiffMerger::mergePythonEnvironment,
                    conflictKeys))
            .setDocker(
                merge(
                    a,
                    d,
                    AutogenEnvironmentBlob::getDocker,
                    AutogenEnvironmentDiff::getDocker,
                    DiffMerger::mergeDockerEnvironment,
                    conflictKeys))
            .setEnvironmentVariables(
                mergeList(
                    a,
                    d,
                    AutogenEnvironmentBlob::getEnvironmentVariables,
                    AutogenEnvironmentDiff::getEnvironmentVariables,
                    AutogenEnvironmentVariablesBlob::getName,
                    x -> Utils.either(x.getA(), x.getB(), AutogenEnvironmentVariablesBlob::getName),
                    AutogenEnvironmentVariablesDiff::getStatus,
                    AutogenEnvironmentVariablesDiff::getA,
                    AutogenEnvironmentVariablesDiff::getB,
                    null,
                    conflictKeys))
            .setCommandLine(
                merge(
                    a,
                    d,
                    AutogenEnvironmentBlob::getCommandLine,
                    AutogenEnvironmentDiff::getCommandLine,
                    DiffMerger::mergeCommandLine,
                    conflictKeys)));
  }

  public static List<String> mergeCommandLine(
      List<String> a, AutogenCommandLineEnvironmentDiff d, HashSet<String> conflictKeys) {
    if (a == null && d == null) return null;
    if (d == null) return a;
    if (d.getStatus().isDeleted()) return null;
    return Utils.removeEmpty(
        mergeLast(
            a,
            d,
            AutogenCommandLineEnvironmentDiff::getA,
            AutogenCommandLineEnvironmentDiff::getB,
            AutogenCommandLineEnvironmentDiff::getStatus,
            List::toString,
            conflictKeys));
  }

  public static AutogenPythonEnvironmentBlob mergePythonEnvironment(
      AutogenPythonEnvironmentBlob a,
      AutogenPythonEnvironmentDiff d,
      HashSet<String> conflictKeys) {
    return Utils.removeEmpty(
        new AutogenPythonEnvironmentBlob()
            .setVersion(
                merge(
                    a,
                    d,
                    AutogenPythonEnvironmentBlob::getVersion,
                    AutogenPythonEnvironmentDiff::getVersion,
                    DiffMerger::mergeVersionEnvironment,
                    conflictKeys))
            .setConstraints(
                mergeList(
                    a,
                    d,
                    AutogenPythonEnvironmentBlob::getConstraints,
                    AutogenPythonEnvironmentDiff::getConstraints,
                    e -> e.getLibrary() + e.getConstraint(),
                    x -> Utils.either(x.getA(), x.getB(), e -> e.getLibrary() + e.getConstraint()),
                    AutogenPythonRequirementEnvironmentDiff::getStatus,
                    AutogenPythonRequirementEnvironmentDiff::getA,
                    AutogenPythonRequirementEnvironmentDiff::getB,
                    null,
                    conflictKeys))
            .setRequirements(
                mergeList(
                    a,
                    d,
                    AutogenPythonEnvironmentBlob::getRequirements,
                    AutogenPythonEnvironmentDiff::getRequirements,
                    e -> e.getLibrary() + e.getConstraint(),
                    x -> Utils.either(x.getA(), x.getB(), e -> e.getLibrary() + e.getConstraint()),
                    AutogenPythonRequirementEnvironmentDiff::getStatus,
                    AutogenPythonRequirementEnvironmentDiff::getA,
                    AutogenPythonRequirementEnvironmentDiff::getB,
                    null,
                    conflictKeys)));
  }

  public static AutogenVersionEnvironmentBlob mergeVersionEnvironment(
      AutogenVersionEnvironmentBlob a,
      AutogenVersionEnvironmentDiff d,
      HashSet<String> conflictKeys) {

    if (a == null && d == null) return null;
    if (d == null) return a;
    if (d.getStatus().isDeleted()) return null;
    return Utils.removeEmpty(
        mergeLast(
            a,
            d,
            AutogenVersionEnvironmentDiff::getA,
            AutogenVersionEnvironmentDiff::getB,
            AutogenVersionEnvironmentDiff::getStatus,
            AutogenVersionEnvironmentBlob::toString,
            conflictKeys));
  }

  public static AutogenDockerEnvironmentBlob mergeDockerEnvironment(
      AutogenDockerEnvironmentBlob a,
      AutogenDockerEnvironmentDiff d,
      HashSet<String> conflictKeys) {
    if (a == null && d == null) return null;
    if (d == null) return a;
    if (d.getStatus().isDeleted()) return null;
    return Utils.removeEmpty(
        mergeLast(
            a,
            d,
            AutogenDockerEnvironmentDiff::getA,
            AutogenDockerEnvironmentDiff::getB,
            AutogenDockerEnvironmentDiff::getStatus,
            AutogenDockerEnvironmentBlob::getRepository,
            conflictKeys));
  }
}

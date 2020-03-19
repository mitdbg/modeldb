// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PathDatasetBlob implements ProtoType {
  public List<PathDatasetComponentBlob> Components;

  public PathDatasetBlob() {
    this.Components = null;
  }

  public Boolean isEmpty() {
    if (this.Components != null && !this.Components.equals(null) && !this.Components.isEmpty()) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": \"PathDatasetBlob\", \"fields\": {");
    boolean first = true;
    if (this.Components != null && !this.Components.equals(null) && !this.Components.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"Components\": " + Components);
      first = false;
    }
    sb.append("}}");
    return sb.toString();
  }

  // TODO: actually hash
  public String getSHA() {
    StringBuilder sb = new StringBuilder();
    sb.append("PathDatasetBlob");
    if (this.Components != null && !this.Components.equals(null) && !this.Components.isEmpty()) {
      sb.append("::Components::").append(Components);
    }

    return sb.toString();
  }

  // TODO: not consider order on lists
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof PathDatasetBlob)) return false;
    PathDatasetBlob other = (PathDatasetBlob) o;

    {
      Function3<List<PathDatasetComponentBlob>, List<PathDatasetComponentBlob>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<PathDatasetComponentBlob, PathDatasetComponentBlob, Boolean> f2 =
                            (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.Components != null || other.Components != null) {
        if (this.Components == null && other.Components != null) return false;
        if (this.Components != null && other.Components == null) return false;
        if (!f.apply(this.Components, other.Components)) return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.Components);
  }

  public PathDatasetBlob setComponents(List<PathDatasetComponentBlob> value) {
    this.Components = Utils.removeEmpty(value);
    return this;
  }

  public static PathDatasetBlob fromProto(ai.verta.modeldb.versioning.PathDatasetBlob blob) {
    if (blob == null) {
      return null;
    }

    PathDatasetBlob obj = new PathDatasetBlob();
    {
      Function<ai.verta.modeldb.versioning.PathDatasetBlob, List<PathDatasetComponentBlob>> f =
          x ->
              blob.getComponentsList().stream()
                  .map(PathDatasetComponentBlob::fromProto)
                  .collect(Collectors.toList());
      obj.Components = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.PathDatasetBlob.Builder toProto() {
    ai.verta.modeldb.versioning.PathDatasetBlob.Builder builder =
        ai.verta.modeldb.versioning.PathDatasetBlob.newBuilder();
    {
      if (this.Components != null && !this.Components.equals(null) && !this.Components.isEmpty()) {
        Function<ai.verta.modeldb.versioning.PathDatasetBlob.Builder, Void> f =
            x -> {
              builder.addAllComponents(
                  this.Components.stream()
                      .map(y -> y.toProto().build())
                      .collect(Collectors.toList()));
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitPathDatasetBlob(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepListOfPathDatasetComponentBlob(this.Components);
  }

  public PathDatasetBlob postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitPathDatasetBlob(this);
  }

  public PathDatasetBlob postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setComponents(visitor.postVisitDeepListOfPathDatasetComponentBlob(this.Components));
    return this.postVisitShallow(visitor);
  }
}

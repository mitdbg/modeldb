// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.common.exceptions.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.codec.binary.Hex;

public class AutogenQueryDatasetDiff implements ProtoType {
  private List<AutogenQueryDatasetComponentDiff> Components;

  public AutogenQueryDatasetDiff() {
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
    sb.append("{\"class\": \"AutogenQueryDatasetDiff\", \"fields\": {");
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
  public String getSHA() throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(this.toString().getBytes(StandardCharsets.UTF_8));
    return new String(new Hex().encode(hash));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.toString());
  }

  // TODO: not consider order on lists
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof AutogenQueryDatasetDiff)) return false;
    AutogenQueryDatasetDiff other = (AutogenQueryDatasetDiff) o;

    {
      Function3<
              List<AutogenQueryDatasetComponentDiff>,
              List<AutogenQueryDatasetComponentDiff>,
              Boolean>
          f =
              (x2, y2) ->
                  IntStream.range(0, Math.min(x2.size(), y2.size()))
                      .mapToObj(
                          i -> {
                            Function3<
                                    AutogenQueryDatasetComponentDiff,
                                    AutogenQueryDatasetComponentDiff,
                                    Boolean>
                                f2 = (x, y) -> x.equals(y);
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

  public AutogenQueryDatasetDiff setComponents(List<AutogenQueryDatasetComponentDiff> value) {
    this.Components = Utils.removeEmpty(value);
    if (this.Components != null) {
      this.Components.sort(Comparator.comparingInt(AutogenQueryDatasetComponentDiff::hashCode));
    }
    return this;
  }

  public List<AutogenQueryDatasetComponentDiff> getComponents() {
    return this.Components;
  }

  public static AutogenQueryDatasetDiff fromProto(
      ai.verta.modeldb.versioning.QueryDatasetDiff blob) {
    if (blob == null) {
      return null;
    }

    AutogenQueryDatasetDiff obj = new AutogenQueryDatasetDiff();
    {
      Function<ai.verta.modeldb.versioning.QueryDatasetDiff, List<AutogenQueryDatasetComponentDiff>>
          f =
              x ->
                  blob.getComponentsList().stream()
                      .map(AutogenQueryDatasetComponentDiff::fromProto)
                      .collect(Collectors.toList());
      obj.setComponents(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.QueryDatasetDiff.Builder toProto() {
    ai.verta.modeldb.versioning.QueryDatasetDiff.Builder builder =
        ai.verta.modeldb.versioning.QueryDatasetDiff.newBuilder();
    {
      if (this.Components != null && !this.Components.equals(null) && !this.Components.isEmpty()) {
        Function<ai.verta.modeldb.versioning.QueryDatasetDiff.Builder, Void> f =
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
    visitor.preVisitAutogenQueryDatasetDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepListOfAutogenQueryDatasetComponentDiff(this.Components);
  }

  public AutogenQueryDatasetDiff postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitAutogenQueryDatasetDiff(this);
  }

  public AutogenQueryDatasetDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setComponents(
        visitor.postVisitDeepListOfAutogenQueryDatasetComponentDiff(this.Components));

    return this.postVisitShallow(visitor);
  }
}

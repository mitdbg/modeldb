// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
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

public class AutogenCommandLineEnvironmentDiff implements ProtoType {
  private List<String> A;
  private List<String> B;
  private List<String> C;
  private AutogenDiffStatusEnumDiffStatus Status;

  public AutogenCommandLineEnvironmentDiff() {
    this.A = null;
    this.B = null;
    this.C = null;
    this.Status = null;
  }

  public Boolean isEmpty() {
    if (this.A != null && !this.A.equals(null) && !this.A.isEmpty()) {
      return false;
    }
    if (this.B != null && !this.B.equals(null) && !this.B.isEmpty()) {
      return false;
    }
    if (this.C != null && !this.C.equals(null) && !this.C.isEmpty()) {
      return false;
    }
    if (this.Status != null && !this.Status.equals(null)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": \"AutogenCommandLineEnvironmentDiff\", \"fields\": {");
    boolean first = true;
    if (this.A != null && !this.A.equals(null) && !this.A.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"A\": " + A);
      first = false;
    }
    if (this.B != null && !this.B.equals(null) && !this.B.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"B\": " + B);
      first = false;
    }
    if (this.C != null && !this.C.equals(null) && !this.C.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"C\": " + C);
      first = false;
    }
    if (this.Status != null && !this.Status.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"Status\": " + Status);
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
    if (!(o instanceof AutogenCommandLineEnvironmentDiff)) return false;
    AutogenCommandLineEnvironmentDiff other = (AutogenCommandLineEnvironmentDiff) o;

    {
      Function3<List<String>, List<String>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<String, String, Boolean> f2 = (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.A != null || other.A != null) {
        if (this.A == null && other.A != null) return false;
        if (this.A != null && other.A == null) return false;
        if (!f.apply(this.A, other.A)) return false;
      }
    }
    {
      Function3<List<String>, List<String>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<String, String, Boolean> f2 = (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.B != null || other.B != null) {
        if (this.B == null && other.B != null) return false;
        if (this.B != null && other.B == null) return false;
        if (!f.apply(this.B, other.B)) return false;
      }
    }
    {
      Function3<List<String>, List<String>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<String, String, Boolean> f2 = (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.C != null || other.C != null) {
        if (this.C == null && other.C != null) return false;
        if (this.C != null && other.C == null) return false;
        if (!f.apply(this.C, other.C)) return false;
      }
    }
    {
      Function3<AutogenDiffStatusEnumDiffStatus, AutogenDiffStatusEnumDiffStatus, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.Status != null || other.Status != null) {
        if (this.Status == null && other.Status != null) return false;
        if (this.Status != null && other.Status == null) return false;
        if (!f.apply(this.Status, other.Status)) return false;
      }
    }
    return true;
  }

  public AutogenCommandLineEnvironmentDiff setA(List<String> value) {
    this.A = Utils.removeEmpty(value);
    return this;
  }

  public List<String> getA() {
    return this.A;
  }

  public AutogenCommandLineEnvironmentDiff setB(List<String> value) {
    this.B = Utils.removeEmpty(value);
    return this;
  }

  public List<String> getB() {
    return this.B;
  }

  public AutogenCommandLineEnvironmentDiff setC(List<String> value) {
    this.C = Utils.removeEmpty(value);
    return this;
  }

  public List<String> getC() {
    return this.C;
  }

  public AutogenCommandLineEnvironmentDiff setStatus(AutogenDiffStatusEnumDiffStatus value) {
    this.Status = Utils.removeEmpty(value);
    return this;
  }

  public AutogenDiffStatusEnumDiffStatus getStatus() {
    return this.Status;
  }

  public static AutogenCommandLineEnvironmentDiff fromProto(
      ai.verta.modeldb.versioning.CommandLineEnvironmentDiff blob) {
    if (blob == null) {
      return null;
    }

    AutogenCommandLineEnvironmentDiff obj = new AutogenCommandLineEnvironmentDiff();
    {
      Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff, List<String>> f =
          x -> blob.getAList();
      obj.setA(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff, List<String>> f =
          x -> blob.getBList();
      obj.setB(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff, List<String>> f =
          x -> blob.getCList();
      obj.setC(f.apply(blob));
    }
    {
      Function<
              ai.verta.modeldb.versioning.CommandLineEnvironmentDiff,
              AutogenDiffStatusEnumDiffStatus>
          f = x -> AutogenDiffStatusEnumDiffStatus.fromProto(blob.getStatus());
      obj.setStatus(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.Builder toProto() {
    ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.Builder builder =
        ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.newBuilder();
    {
      if (this.A != null && !this.A.equals(null) && !this.A.isEmpty()) {
        Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.Builder, Void> f =
            x -> {
              builder.addAllA(this.A);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.B != null && !this.B.equals(null) && !this.B.isEmpty()) {
        Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.Builder, Void> f =
            x -> {
              builder.addAllB(this.B);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.C != null && !this.C.equals(null) && !this.C.isEmpty()) {
        Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.Builder, Void> f =
            x -> {
              builder.addAllC(this.C);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Status != null && !this.Status.equals(null)) {
        Function<ai.verta.modeldb.versioning.CommandLineEnvironmentDiff.Builder, Void> f =
            x -> {
              builder.setStatus(this.Status.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitAutogenCommandLineEnvironmentDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepListOfString(this.A);

    visitor.preVisitDeepListOfString(this.B);

    visitor.preVisitDeepListOfString(this.C);

    visitor.preVisitDeepAutogenDiffStatusEnumDiffStatus(this.Status);
  }

  public AutogenCommandLineEnvironmentDiff postVisitShallow(Visitor visitor)
      throws ModelDBException {
    return visitor.postVisitAutogenCommandLineEnvironmentDiff(this);
  }

  public AutogenCommandLineEnvironmentDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setA(visitor.postVisitDeepListOfString(this.A));

    this.setB(visitor.postVisitDeepListOfString(this.B));

    this.setC(visitor.postVisitDeepListOfString(this.C));

    this.setStatus(visitor.postVisitDeepAutogenDiffStatusEnumDiffStatus(this.Status));
    return this.postVisitShallow(visitor);
  }
}

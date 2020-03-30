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

public class AutogenHyperparameterSetConfigDiff implements ProtoType {
  private AutogenHyperparameterSetConfigBlob A;
  private AutogenHyperparameterSetConfigBlob B;
  private AutogenDiffStatusEnumDiffStatus Status;

  public AutogenHyperparameterSetConfigDiff() {
    this.A = null;
    this.B = null;
    this.Status = null;
  }

  public Boolean isEmpty() {
    if (this.A != null && !this.A.equals(null)) {
      return false;
    }
    if (this.B != null && !this.B.equals(null)) {
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
    sb.append("{\"class\": \"AutogenHyperparameterSetConfigDiff\", \"fields\": {");
    boolean first = true;
    if (this.A != null && !this.A.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"A\": " + A);
      first = false;
    }
    if (this.B != null && !this.B.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"B\": " + B);
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
  public String getSHA() {
    StringBuilder sb = new StringBuilder();
    sb.append("AutogenHyperparameterSetConfigDiff");
    if (this.A != null && !this.A.equals(null)) {
      sb.append("::A::").append(A);
    }
    if (this.B != null && !this.B.equals(null)) {
      sb.append("::B::").append(B);
    }
    if (this.Status != null && !this.Status.equals(null)) {
      sb.append("::Status::").append(Status);
    }

    return sb.toString();
  }

  // TODO: not consider order on lists
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof AutogenHyperparameterSetConfigDiff)) return false;
    AutogenHyperparameterSetConfigDiff other = (AutogenHyperparameterSetConfigDiff) o;

    {
      Function3<AutogenHyperparameterSetConfigBlob, AutogenHyperparameterSetConfigBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.A != null || other.A != null) {
        if (this.A == null && other.A != null) return false;
        if (this.A != null && other.A == null) return false;
        if (!f.apply(this.A, other.A)) return false;
      }
    }
    {
      Function3<AutogenHyperparameterSetConfigBlob, AutogenHyperparameterSetConfigBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.B != null || other.B != null) {
        if (this.B == null && other.B != null) return false;
        if (this.B != null && other.B == null) return false;
        if (!f.apply(this.B, other.B)) return false;
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

  @Override
  public int hashCode() {
    return Objects.hash(this.A, this.B, this.Status);
  }

  public AutogenHyperparameterSetConfigDiff setA(AutogenHyperparameterSetConfigBlob value) {
    this.A = Utils.removeEmpty(value);
    return this;
  }

  public AutogenHyperparameterSetConfigBlob getA() {
    return this.A;
  }

  public AutogenHyperparameterSetConfigDiff setB(AutogenHyperparameterSetConfigBlob value) {
    this.B = Utils.removeEmpty(value);
    return this;
  }

  public AutogenHyperparameterSetConfigBlob getB() {
    return this.B;
  }

  public AutogenHyperparameterSetConfigDiff setStatus(AutogenDiffStatusEnumDiffStatus value) {
    this.Status = Utils.removeEmpty(value);
    return this;
  }

  public AutogenDiffStatusEnumDiffStatus getStatus() {
    return this.Status;
  }

  public static AutogenHyperparameterSetConfigDiff fromProto(
      ai.verta.modeldb.versioning.HyperparameterSetConfigDiff blob) {
    if (blob == null) {
      return null;
    }

    AutogenHyperparameterSetConfigDiff obj = new AutogenHyperparameterSetConfigDiff();
    {
      Function<
              ai.verta.modeldb.versioning.HyperparameterSetConfigDiff,
              AutogenHyperparameterSetConfigBlob>
          f = x -> AutogenHyperparameterSetConfigBlob.fromProto(blob.getA());
      obj.A = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<
              ai.verta.modeldb.versioning.HyperparameterSetConfigDiff,
              AutogenHyperparameterSetConfigBlob>
          f = x -> AutogenHyperparameterSetConfigBlob.fromProto(blob.getB());
      obj.B = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<
              ai.verta.modeldb.versioning.HyperparameterSetConfigDiff,
              AutogenDiffStatusEnumDiffStatus>
          f = x -> AutogenDiffStatusEnumDiffStatus.fromProto(blob.getStatus());
      obj.Status = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.HyperparameterSetConfigDiff.Builder toProto() {
    ai.verta.modeldb.versioning.HyperparameterSetConfigDiff.Builder builder =
        ai.verta.modeldb.versioning.HyperparameterSetConfigDiff.newBuilder();
    {
      if (this.A != null && !this.A.equals(null)) {
        Function<ai.verta.modeldb.versioning.HyperparameterSetConfigDiff.Builder, Void> f =
            x -> {
              builder.setA(this.A.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.B != null && !this.B.equals(null)) {
        Function<ai.verta.modeldb.versioning.HyperparameterSetConfigDiff.Builder, Void> f =
            x -> {
              builder.setB(this.B.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Status != null && !this.Status.equals(null)) {
        Function<ai.verta.modeldb.versioning.HyperparameterSetConfigDiff.Builder, Void> f =
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
    visitor.preVisitAutogenHyperparameterSetConfigDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepAutogenHyperparameterSetConfigBlob(this.A);
    visitor.preVisitDeepAutogenHyperparameterSetConfigBlob(this.B);
    visitor.preVisitDeepAutogenDiffStatusEnumDiffStatus(this.Status);
  }

  public AutogenHyperparameterSetConfigDiff postVisitShallow(Visitor visitor)
      throws ModelDBException {
    return visitor.postVisitAutogenHyperparameterSetConfigDiff(this);
  }

  public AutogenHyperparameterSetConfigDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setA(visitor.postVisitDeepAutogenHyperparameterSetConfigBlob(this.A));
    this.setB(visitor.postVisitDeepAutogenHyperparameterSetConfigBlob(this.B));
    this.setStatus(visitor.postVisitDeepAutogenDiffStatusEnumDiffStatus(this.Status));
    return this.postVisitShallow(visitor);
  }
}

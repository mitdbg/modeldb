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
import org.apache.commons.codec.binary.Hex;

public class AutogenS3DatasetComponentDiff implements ProtoType {
  private AutogenS3DatasetComponentBlob A;
  private AutogenS3DatasetComponentBlob B;
  private AutogenS3DatasetComponentBlob C;
  private AutogenDiffStatusEnumDiffStatus Status;

  public AutogenS3DatasetComponentDiff() {
    this.A = null;
    this.B = null;
    this.C = null;
    this.Status = null;
  }

  public Boolean isEmpty() {
    if (this.A != null && !this.A.equals(null)) {
      return false;
    }
    if (this.B != null && !this.B.equals(null)) {
      return false;
    }
    if (this.C != null && !this.C.equals(null)) {
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
    sb.append("{\"class\": \"AutogenS3DatasetComponentDiff\", \"fields\": {");
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
    if (this.C != null && !this.C.equals(null)) {
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
    if (!(o instanceof AutogenS3DatasetComponentDiff)) return false;
    AutogenS3DatasetComponentDiff other = (AutogenS3DatasetComponentDiff) o;

    {
      Function3<AutogenS3DatasetComponentBlob, AutogenS3DatasetComponentBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.A != null || other.A != null) {
        if (this.A == null && other.A != null) return false;
        if (this.A != null && other.A == null) return false;
        if (!f.apply(this.A, other.A)) return false;
      }
    }
    {
      Function3<AutogenS3DatasetComponentBlob, AutogenS3DatasetComponentBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.B != null || other.B != null) {
        if (this.B == null && other.B != null) return false;
        if (this.B != null && other.B == null) return false;
        if (!f.apply(this.B, other.B)) return false;
      }
    }
    {
      Function3<AutogenS3DatasetComponentBlob, AutogenS3DatasetComponentBlob, Boolean> f =
          (x, y) -> x.equals(y);
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

  public AutogenS3DatasetComponentDiff setA(AutogenS3DatasetComponentBlob value) {
    this.A = Utils.removeEmpty(value);
    return this;
  }

  public AutogenS3DatasetComponentBlob getA() {
    return this.A;
  }

  public AutogenS3DatasetComponentDiff setB(AutogenS3DatasetComponentBlob value) {
    this.B = Utils.removeEmpty(value);
    return this;
  }

  public AutogenS3DatasetComponentBlob getB() {
    return this.B;
  }

  public AutogenS3DatasetComponentDiff setC(AutogenS3DatasetComponentBlob value) {
    this.C = Utils.removeEmpty(value);
    return this;
  }

  public AutogenS3DatasetComponentBlob getC() {
    return this.C;
  }

  public AutogenS3DatasetComponentDiff setStatus(AutogenDiffStatusEnumDiffStatus value) {
    this.Status = Utils.removeEmpty(value);
    return this;
  }

  public AutogenDiffStatusEnumDiffStatus getStatus() {
    return this.Status;
  }

  public static AutogenS3DatasetComponentDiff fromProto(
      ai.verta.modeldb.versioning.S3DatasetComponentDiff blob) {
    if (blob == null) {
      return null;
    }

    AutogenS3DatasetComponentDiff obj = new AutogenS3DatasetComponentDiff();
    {
      Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff, AutogenS3DatasetComponentBlob>
          f = x -> AutogenS3DatasetComponentBlob.fromProto(blob.getA());
      obj.setA(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff, AutogenS3DatasetComponentBlob>
          f = x -> AutogenS3DatasetComponentBlob.fromProto(blob.getB());
      obj.setB(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff, AutogenS3DatasetComponentBlob>
          f = x -> AutogenS3DatasetComponentBlob.fromProto(blob.getC());
      obj.setC(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff, AutogenDiffStatusEnumDiffStatus>
          f = x -> AutogenDiffStatusEnumDiffStatus.fromProto(blob.getStatus());
      obj.setStatus(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.S3DatasetComponentDiff.Builder toProto() {
    ai.verta.modeldb.versioning.S3DatasetComponentDiff.Builder builder =
        ai.verta.modeldb.versioning.S3DatasetComponentDiff.newBuilder();
    {
      if (this.A != null && !this.A.equals(null)) {
        Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff.Builder, Void> f =
            x -> {
              builder.setA(this.A.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.B != null && !this.B.equals(null)) {
        Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff.Builder, Void> f =
            x -> {
              builder.setB(this.B.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.C != null && !this.C.equals(null)) {
        Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff.Builder, Void> f =
            x -> {
              builder.setC(this.C.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Status != null && !this.Status.equals(null)) {
        Function<ai.verta.modeldb.versioning.S3DatasetComponentDiff.Builder, Void> f =
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
    visitor.preVisitAutogenS3DatasetComponentDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepAutogenS3DatasetComponentBlob(this.A);
    visitor.preVisitDeepAutogenS3DatasetComponentBlob(this.B);
    visitor.preVisitDeepAutogenS3DatasetComponentBlob(this.C);
    visitor.preVisitDeepAutogenDiffStatusEnumDiffStatus(this.Status);
  }

  public AutogenS3DatasetComponentDiff postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitAutogenS3DatasetComponentDiff(this);
  }

  public AutogenS3DatasetComponentDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setA(visitor.postVisitDeepAutogenS3DatasetComponentBlob(this.A));
    this.setB(visitor.postVisitDeepAutogenS3DatasetComponentBlob(this.B));
    this.setC(visitor.postVisitDeepAutogenS3DatasetComponentBlob(this.C));
    this.setStatus(visitor.postVisitDeepAutogenDiffStatusEnumDiffStatus(this.Status));
    return this.postVisitShallow(visitor);
  }
}

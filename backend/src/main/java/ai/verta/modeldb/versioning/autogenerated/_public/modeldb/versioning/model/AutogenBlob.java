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

public class AutogenBlob implements ProtoType {
  private List<AutogenCommonKeyValue> Attributes;
  private AutogenCodeBlob Code;
  private AutogenConfigBlob Config;
  private AutogenDatasetBlob Dataset;
  private AutogenEnvironmentBlob Environment;

  public AutogenBlob() {
    this.Attributes = null;
    this.Code = null;
    this.Config = null;
    this.Dataset = null;
    this.Environment = null;
  }

  public Boolean isEmpty() {
    if (this.Attributes != null && !this.Attributes.equals(null) && !this.Attributes.isEmpty()) {
      return false;
    }
    if (this.Code != null && !this.Code.equals(null)) {
      return false;
    }
    if (this.Config != null && !this.Config.equals(null)) {
      return false;
    }
    if (this.Dataset != null && !this.Dataset.equals(null)) {
      return false;
    }
    if (this.Environment != null && !this.Environment.equals(null)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": \"AutogenBlob\", \"fields\": {");
    boolean first = true;
    if (this.Attributes != null && !this.Attributes.equals(null) && !this.Attributes.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"Attributes\": " + Attributes);
      first = false;
    }
    if (this.Code != null && !this.Code.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"Code\": " + Code);
      first = false;
    }
    if (this.Config != null && !this.Config.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"Config\": " + Config);
      first = false;
    }
    if (this.Dataset != null && !this.Dataset.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"Dataset\": " + Dataset);
      first = false;
    }
    if (this.Environment != null && !this.Environment.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"Environment\": " + Environment);
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
    if (!(o instanceof AutogenBlob)) return false;
    AutogenBlob other = (AutogenBlob) o;

    {
      Function3<List<AutogenCommonKeyValue>, List<AutogenCommonKeyValue>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<AutogenCommonKeyValue, AutogenCommonKeyValue, Boolean> f2 =
                            (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.Attributes != null || other.Attributes != null) {
        if (this.Attributes == null && other.Attributes != null) return false;
        if (this.Attributes != null && other.Attributes == null) return false;
        if (!f.apply(this.Attributes, other.Attributes)) return false;
      }
    }
    {
      Function3<AutogenCodeBlob, AutogenCodeBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Code != null || other.Code != null) {
        if (this.Code == null && other.Code != null) return false;
        if (this.Code != null && other.Code == null) return false;
        if (!f.apply(this.Code, other.Code)) return false;
      }
    }
    {
      Function3<AutogenConfigBlob, AutogenConfigBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Config != null || other.Config != null) {
        if (this.Config == null && other.Config != null) return false;
        if (this.Config != null && other.Config == null) return false;
        if (!f.apply(this.Config, other.Config)) return false;
      }
    }
    {
      Function3<AutogenDatasetBlob, AutogenDatasetBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Dataset != null || other.Dataset != null) {
        if (this.Dataset == null && other.Dataset != null) return false;
        if (this.Dataset != null && other.Dataset == null) return false;
        if (!f.apply(this.Dataset, other.Dataset)) return false;
      }
    }
    {
      Function3<AutogenEnvironmentBlob, AutogenEnvironmentBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Environment != null || other.Environment != null) {
        if (this.Environment == null && other.Environment != null) return false;
        if (this.Environment != null && other.Environment == null) return false;
        if (!f.apply(this.Environment, other.Environment)) return false;
      }
    }
    return true;
  }

  public AutogenBlob setAttributes(List<AutogenCommonKeyValue> value) {
    this.Attributes = Utils.removeEmpty(value);
    if (this.Attributes != null) {
      this.Attributes.sort(Comparator.comparingInt(AutogenCommonKeyValue::hashCode));
    }
    return this;
  }

  public List<AutogenCommonKeyValue> getAttributes() {
    return this.Attributes;
  }

  public AutogenBlob setCode(AutogenCodeBlob value) {
    this.Code = Utils.removeEmpty(value);
    return this;
  }

  public AutogenCodeBlob getCode() {
    return this.Code;
  }

  public AutogenBlob setConfig(AutogenConfigBlob value) {
    this.Config = Utils.removeEmpty(value);
    return this;
  }

  public AutogenConfigBlob getConfig() {
    return this.Config;
  }

  public AutogenBlob setDataset(AutogenDatasetBlob value) {
    this.Dataset = Utils.removeEmpty(value);
    return this;
  }

  public AutogenDatasetBlob getDataset() {
    return this.Dataset;
  }

  public AutogenBlob setEnvironment(AutogenEnvironmentBlob value) {
    this.Environment = Utils.removeEmpty(value);
    return this;
  }

  public AutogenEnvironmentBlob getEnvironment() {
    return this.Environment;
  }

  public static AutogenBlob fromProto(ai.verta.modeldb.versioning.Blob blob) {
    if (blob == null) {
      return null;
    }

    AutogenBlob obj = new AutogenBlob();
    {
      Function<ai.verta.modeldb.versioning.Blob, List<AutogenCommonKeyValue>> f =
          x ->
              blob.getAttributesList().stream()
                  .map(AutogenCommonKeyValue::fromProto)
                  .collect(Collectors.toList());
      obj.setAttributes(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, AutogenCodeBlob> f =
          x -> AutogenCodeBlob.fromProto(blob.getCode());
      obj.setCode(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, AutogenConfigBlob> f =
          x -> AutogenConfigBlob.fromProto(blob.getConfig());
      obj.setConfig(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, AutogenDatasetBlob> f =
          x -> AutogenDatasetBlob.fromProto(blob.getDataset());
      obj.setDataset(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, AutogenEnvironmentBlob> f =
          x -> AutogenEnvironmentBlob.fromProto(blob.getEnvironment());
      obj.setEnvironment(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.Blob.Builder toProto() {
    ai.verta.modeldb.versioning.Blob.Builder builder =
        ai.verta.modeldb.versioning.Blob.newBuilder();
    {
      if (this.Attributes != null && !this.Attributes.equals(null) && !this.Attributes.isEmpty()) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.addAllAttributes(
                  this.Attributes.stream()
                      .map(y -> y.toProto().build())
                      .collect(Collectors.toList()));
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Code != null && !this.Code.equals(null)) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setCode(this.Code.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Config != null && !this.Config.equals(null)) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setConfig(this.Config.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Dataset != null && !this.Dataset.equals(null)) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setDataset(this.Dataset.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Environment != null && !this.Environment.equals(null)) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setEnvironment(this.Environment.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitAutogenBlob(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepListOfAutogenCommonKeyValue(this.Attributes);

    visitor.preVisitDeepAutogenCodeBlob(this.Code);
    visitor.preVisitDeepAutogenConfigBlob(this.Config);
    visitor.preVisitDeepAutogenDatasetBlob(this.Dataset);
    visitor.preVisitDeepAutogenEnvironmentBlob(this.Environment);
  }

  public AutogenBlob postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitAutogenBlob(this);
  }

  public AutogenBlob postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setAttributes(visitor.postVisitDeepListOfAutogenCommonKeyValue(this.Attributes));

    this.setCode(visitor.postVisitDeepAutogenCodeBlob(this.Code));
    this.setConfig(visitor.postVisitDeepAutogenConfigBlob(this.Config));
    this.setDataset(visitor.postVisitDeepAutogenDatasetBlob(this.Dataset));
    this.setEnvironment(visitor.postVisitDeepAutogenEnvironmentBlob(this.Environment));
    return this.postVisitShallow(visitor);
  }
}

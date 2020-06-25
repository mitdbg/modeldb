// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class AutogenHyperparameterValuesConfigBlob implements ProtoType {
    private Float
 FloatValue;
    private Long
 IntValue;
    private String
 StringValue;

    public AutogenHyperparameterValuesConfigBlob() {
        this.FloatValue = 0.f;
        this.IntValue = 0l;
        this.StringValue = "";
    }

    public Boolean isEmpty() {
        if (this.FloatValue != null && !this.FloatValue.equals(0.f) ) {
            return false;
        }
        if (this.IntValue != null && !this.IntValue.equals(0l) ) {
            return false;
        }
        if (this.StringValue != null && !this.StringValue.equals("") ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"class\": \"AutogenHyperparameterValuesConfigBlob\", \"fields\": {");
        boolean first = true;
        if (this.FloatValue != null && !this.FloatValue.equals(0.f) ) {
            if (!first) sb.append(", ");
            sb.append("\"FloatValue\": " + FloatValue);
            first = false;
        }
        if (this.IntValue != null && !this.IntValue.equals(0l) ) {
            if (!first) sb.append(", ");
            sb.append("\"IntValue\": " + IntValue);
            first = false;
        }
        if (this.StringValue != null && !this.StringValue.equals("") ) {
            if (!first) sb.append(", ");
            sb.append("\"StringValue\": " + "\"" + StringValue + "\"");
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
        if (!(o instanceof AutogenHyperparameterValuesConfigBlob)) return false;
        AutogenHyperparameterValuesConfigBlob other = (AutogenHyperparameterValuesConfigBlob) o;

        {
            Function3<Float
,Float
,Boolean> f = (x, y) -> x.equals(y);
            if (this.FloatValue != null || other.FloatValue != null) {
                if (this.FloatValue == null && other.FloatValue != null)
                    return false;
                if (this.FloatValue != null && other.FloatValue == null)
                    return false;
                if (!f.apply(this.FloatValue, other.FloatValue))
                    return false;
            }
        }
        {
            Function3<Long
,Long
,Boolean> f = (x, y) -> x.equals(y);
            if (this.IntValue != null || other.IntValue != null) {
                if (this.IntValue == null && other.IntValue != null)
                    return false;
                if (this.IntValue != null && other.IntValue == null)
                    return false;
                if (!f.apply(this.IntValue, other.IntValue))
                    return false;
            }
        }
        {
            Function3<String
,String
,Boolean> f = (x, y) -> x.equals(y);
            if (this.StringValue != null || other.StringValue != null) {
                if (this.StringValue == null && other.StringValue != null)
                    return false;
                if (this.StringValue != null && other.StringValue == null)
                    return false;
                if (!f.apply(this.StringValue, other.StringValue))
                    return false;
            }
        }
        return true;
    }

    public AutogenHyperparameterValuesConfigBlob setFloatValue(Float
 value) {
        this.FloatValue = Utils.removeEmpty(value);
        return this;
    }
    public Float
 getFloatValue() {
        return this.FloatValue;
    }
    public AutogenHyperparameterValuesConfigBlob setIntValue(Long
 value) {
        this.IntValue = Utils.removeEmpty(value);
        return this;
    }
    public Long
 getIntValue() {
        return this.IntValue;
    }
    public AutogenHyperparameterValuesConfigBlob setStringValue(String
 value) {
        this.StringValue = Utils.removeEmpty(value);
        return this;
    }
    public String
 getStringValue() {
        return this.StringValue;
    }

    static public AutogenHyperparameterValuesConfigBlob fromProto(ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob blob) {
        if (blob == null) {
            return null;
        }

        AutogenHyperparameterValuesConfigBlob obj = new AutogenHyperparameterValuesConfigBlob();
        {
            Function<ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob,Float
> f = x -> (blob.getFloatValue());
            obj.setFloatValue(f.apply(blob));
        }
        {
            Function<ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob,Long
> f = x -> (blob.getIntValue());
            obj.setIntValue(f.apply(blob));
        }
        {
            Function<ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob,String
> f = x -> (blob.getStringValue());
            obj.setStringValue(f.apply(blob));
        }
        return obj;
    }

    public ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob.Builder toProto() {
        ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob.Builder builder = ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob.newBuilder();
        {
            if (this.FloatValue != null && !this.FloatValue.equals(0.f) ) {
                Function<ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob.Builder,Void> f = x -> { builder.setFloatValue(this.FloatValue); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.IntValue != null && !this.IntValue.equals(0l) ) {
                Function<ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob.Builder,Void> f = x -> { builder.setIntValue(this.IntValue); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.StringValue != null && !this.StringValue.equals("") ) {
                Function<ai.verta.modeldb.versioning.HyperparameterValuesConfigBlob.Builder,Void> f = x -> { builder.setStringValue(this.StringValue); return null; };
                f.apply(builder);
            }
        }
        return builder;
    }

    public void preVisitShallow(Visitor visitor) throws ModelDBException {
        visitor.preVisitAutogenHyperparameterValuesConfigBlob(this);
    }

    public void preVisitDeep(Visitor visitor) throws ModelDBException {
        this.preVisitShallow(visitor);
        visitor.preVisitDeepFloat
(this.FloatValue);
        visitor.preVisitDeepLong
(this.IntValue);
        visitor.preVisitDeepString
(this.StringValue);
    }

    public AutogenHyperparameterValuesConfigBlob postVisitShallow(Visitor visitor) throws ModelDBException {
        return visitor.postVisitAutogenHyperparameterValuesConfigBlob(this);
    }

    public AutogenHyperparameterValuesConfigBlob postVisitDeep(Visitor visitor) throws ModelDBException {
        this.setFloatValue(visitor.postVisitDeepFloat
(this.FloatValue));
        this.setIntValue(visitor.postVisitDeepLong
(this.IntValue));
        this.setStringValue(visitor.postVisitDeepString
(this.StringValue));
        return this.postVisitShallow(visitor);
    }
}

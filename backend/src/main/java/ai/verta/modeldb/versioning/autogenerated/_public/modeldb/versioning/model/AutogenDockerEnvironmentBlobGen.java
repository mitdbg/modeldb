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
import com.pholser.junit.quickcheck.generator.java.util.*;
import com.pholser.junit.quickcheck.random.*;

public class AutogenDockerEnvironmentBlobGen extends Generator<AutogenDockerEnvironmentBlob> {
    public AutogenDockerEnvironmentBlobGen() {
        super(AutogenDockerEnvironmentBlob.class);
    }

    @Override public AutogenDockerEnvironmentBlob generate(
            SourceOfRandomness r,
            GenerationStatus status) {
                // if (r.nextBoolean())
                //     return null;

                AutogenDockerEnvironmentBlob obj = new AutogenDockerEnvironmentBlob();
                if (r.nextBoolean()) {
                    obj.setRepository(Utils.removeEmpty(new StringGenerator().generate(r, status)));
                }
                if (r.nextBoolean()) {
                    obj.setSha(Utils.removeEmpty(new StringGenerator().generate(r, status)));
                }
                if (r.nextBoolean()) {
                    obj.setTag(Utils.removeEmpty(new StringGenerator().generate(r, status)));
                }
                return obj;
    }
}

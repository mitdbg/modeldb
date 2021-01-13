// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated.{{package}}.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.verta.modeldb.common.exceptions.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.generator.java.util.*;
import com.pholser.junit.quickcheck.random.*;

public class Autogen{{class_name}}Gen extends Generator<Autogen{{class_name}}> {
    public Autogen{{class_name}}Gen() {
        super(Autogen{{class_name}}.class);
    }

    @Override public Autogen{{class_name}} generate(
            SourceOfRandomness r,
            GenerationStatus status) {
                // if (r.nextBoolean())
                //     return null;

                Autogen{{class_name}} obj = new Autogen{{class_name}}();
                {{#properties}}
                {{^required}}
                {{#type}}
                {{^is_list}}
                if (r.nextBoolean()) {
                    {{^string}}
                    obj.set{{name}}(Utils.removeEmpty(gen().type({{>type}}.class).generate(r, status)));
                    {{/string}}
                    {{#string}}
                    obj.set{{name}}(Utils.removeEmpty(new StringGenerator().generate(r, status)));
                    {{/string}}
                }
                {{/is_list}}
                {{#is_list}}
                {{#list_type}}
                if (r.nextBoolean()) {
                    int size = r.nextInt(0, 10);
                    List<{{>type}}> ret = new ArrayList(size);
                    for (int i = 0; i < size; i++) {
                        {{^string}}
                        ret.add(gen().type({{>type}}.class).generate(r, status));
                        {{/string}}
                        {{#string}}
                        ret.add(new StringGenerator().generate(r, status));
                        {{/string}}
                    }
                    obj.set{{name}}(Utils.removeEmpty(ret));
                }
                {{/list_type}}
                {{/is_list}}
                {{/type}}
                {{/required}}
                {{/properties}}
                return obj;
    }
}

package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> schemas;

    public MapSchema() {
        addCheck("isInstance", x -> ((x instanceof Map) || Objects.isNull(x)));
    }

    public MapSchema required() {
        Predicate<Object> mapRequired = input -> (!isNull(input));
        addCheck("mapRequired", mapRequired);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = input -> (isNull(input) || ((Map) input).size() == size);
        addCheck("sizeof", sizeof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> validationSchemas) {
        Predicate<Object> shape = input -> {
            if (Objects.nonNull(input)) {
                this.schemas = validationSchemas;
                var schemasKeys = (schemas.keySet());
                for (String key : schemasKeys) {
                    if (!((Map) input).containsKey(key)) {
                        return false;
                    }
                    if (!schemas.get(key).isValid(((Map) input).get(key))) {
                        return false;
                    }
                }
            }
            return true;
        };

        addCheck("shape", shape);
        return this;
    }

    public boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}



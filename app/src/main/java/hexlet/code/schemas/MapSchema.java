package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        Predicate<Object> mapRequired = input -> (Objects.nonNull(input) && (input instanceof Map));
        addCheck("mapRequired", mapRequired);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = input -> (((Map) input).size() == size);
        addCheck("sizeof", sizeof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> validationSchemas) {
        Predicate<Object> shape = input -> {
            if (Objects.nonNull(input)) {
                if (!(input instanceof Map)) {
                    return false;
                }
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

}

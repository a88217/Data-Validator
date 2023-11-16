package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        checks.put("mapRequired", true);
        return this;
    }

    public MapSchema sizeof(int size) {
        checks.put("sizeOf", size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> validationSchemas) {
        checks.put("shape", validationSchemas);
        return this;
    }

}

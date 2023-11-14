package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    private boolean isRequired = false;
    private boolean isSizeof = false;
    private boolean isShape = false;
    private int sizeOf;
    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        isSizeof = true;
        this.sizeOf = size;
        return this;
    }

    @Override
    public boolean isValid(Object inputMap) {
        if (isRequired) {
            if (Objects.isNull(inputMap) || !(inputMap instanceof Map)) {
                return false;
            }
        }
        if (isSizeof) {
            if (((Map) inputMap).size() != sizeOf) {
                return false;
            }
        }
        if (isShape) {
            if (Objects.nonNull(inputMap)) {
                if (!(inputMap instanceof Map)) {
                    return false;
                }
                Map input = (Map) inputMap;
                var schemasKeys = schemas.keySet();
                for (String key : schemasKeys) {
                    if (!((Map) inputMap).containsKey(key)) {
                        return false;
                    }
                    if (!schemas.get(key).isValid(input.get(key))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        isShape = true;
        this.schemas = schemas;
    }
}

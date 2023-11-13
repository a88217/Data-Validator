package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema{

    private boolean isRequired = false;
    private boolean isSizeOf = false;
    private boolean isShape = false;
    private int sizeOf;
    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int sizeOf) {
        isSizeOf = true;
        this.sizeOf = sizeOf;
        return this;
    }

    @Override
    public boolean isValid(Object inputMap) {
        if (isRequired) {
            if (Objects.isNull(inputMap) || !(inputMap instanceof Map)) {
                return false;
            }
        }
        if (isSizeOf) {
            if (((Map) inputMap).size() != sizeOf) {
                return false;
            }
        }
        if (isShape) {
            Map input = (Map) inputMap;
            var schemasKeys = schemas.keySet();
            for (String key : schemasKeys) {
                return schemas.get(key).isValid(input.get(key));
            }
        }
        return true;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        isShape = true;
        this.schemas = schemas;
    }
}

package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema{

    private boolean isRequired = false;
    private boolean isSizeof = false;
    private boolean isShape = false;
    private int sizeof;
    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        isSizeof = true;
        this.sizeof = sizeof;
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
            if (((Map) inputMap).size() != sizeof) {
                return false;
            }
        }
        if (isShape) {
            if (Objects.isNull(inputMap)) {return false;}
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

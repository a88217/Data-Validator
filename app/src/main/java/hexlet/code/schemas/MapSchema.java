package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema{

    private boolean isRequired = false;
    private boolean isSizeOf = false;
    private int sizeOf;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeOf(int sizeOf) {
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
        return true;
    }
}

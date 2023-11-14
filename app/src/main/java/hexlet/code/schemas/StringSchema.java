package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema {

    private boolean isRequired = false;
    private boolean isMinLength = false;
    private boolean isContains = false;
    private int minLength;
    private String contains;
    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        isMinLength = true;
        this.minLength = minLength;
        return this;
    }
    public StringSchema contains(String substring) {
        isContains = true;
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(Object inputString) {
        if (isRequired) {
            if (Objects.isNull(inputString) || !(inputString instanceof String) || inputString.equals("")) {
                return false;
            }
        }
        if (isMinLength) {
            if (inputString.toString().length() < minLength) {
                return false;
            }
        }
        if (isContains) {
            if (!inputString.toString().contains(contains)) {
                return false;
            }
        }
        return true;
    }
}

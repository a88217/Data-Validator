package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        checks.put("stringRequired", true);
        return this;
    }

    public StringSchema minLength(int min) {
        checks.put("minLength", min);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put("contains", substring);
        return this;
    }

}

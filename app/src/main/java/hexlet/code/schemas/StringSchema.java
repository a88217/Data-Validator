package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        Predicate<Object> stringRequired
                = input -> (Objects.nonNull(input) && (input instanceof String) && !input.equals(""));
        addCheck("stringRequired", stringRequired);
        return this;
    }

    public StringSchema minLength(int min) {
        Predicate<Object> minLength = input -> (input.toString().length() >= min);
        addCheck("minLength", minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<Object> contains = input -> (input.toString().contains(substring));
        addCheck("contains", contains);
        return this;
    }

}

package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        Predicate<Object> numberRequired = input -> (Objects.nonNull(input) && (input instanceof Integer));
        addCheck("numberRequired", numberRequired);
        return this;
    }
    public NumberSchema positive() {
        Predicate<Object> positive
                = input -> (Objects.isNull(input) || ((input instanceof Integer) && (Integer) input > 0));
        addCheck("positive", positive);
        return this;
    }
    public NumberSchema range(int min, int max) {
        Predicate<Object> range = input -> ((Integer) input >= min && (Integer) input <= max);
        addCheck("range", range);
        return this;
    }

}

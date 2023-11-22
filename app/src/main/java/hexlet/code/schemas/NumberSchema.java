package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck("isInstance", x -> ((x instanceof Integer) || Objects.isNull(x)));
    }

    public NumberSchema required() {
        Predicate<Object> numberRequired = input -> (!isNull(input));
        addCheck("numberRequired", numberRequired);
        return this;
    }
    public NumberSchema positive() {
        Predicate<Object> positive = input -> (isNull(input) || (Integer) input > 0);
        addCheck("positive", positive);
        return this;
    }
    public NumberSchema range(int min, int max) {
        Predicate<Object> range = input -> (isNull(input) || ((Integer) input >= min && (Integer) input <= max));
        addCheck("range", range);
        return this;
    }

    public boolean isNull(Object obj) {
        return Objects.isNull(obj);
        }
}

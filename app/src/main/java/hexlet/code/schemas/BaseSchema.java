package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private Map<String, Predicate> checks = new LinkedHashMap<>();

    public final void addCheck(String checkName, Predicate check) {
        checks.put(checkName, check);
    }

    /**
     * Method can be safely overridden.
     * @param input Validated object
     @return some boolean value.
     * */
    public boolean isValid(Object input) {
        for (var check : checks.keySet()) {
            if (!checks.get(check).test(input)) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean isNull(Object obj);
}

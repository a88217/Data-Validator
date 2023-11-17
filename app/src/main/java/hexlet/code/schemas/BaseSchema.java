package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private Map<String, Predicate> checks = new HashMap();

    public void addCheck(String checkName, Predicate check) {
        checks.put(checkName, check);
    }

    /**
     * Method can be safely overridden.
     * @param input Validated object
     @return some boolean value.
     * */
    public boolean isValid(Object input) {
        boolean result = true;
        for (var check : checks.keySet()) {
            if (!checks.get(check).test(input)) {
                result = false;
            }
        }
        return result;
    }
}

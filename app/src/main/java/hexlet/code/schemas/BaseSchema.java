package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
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
        /*    if (checks.containsKey("mapRequired")) {
            if (Objects.isNull(input) || !(input instanceof Map)) {
                return false;
            }
        }
        if (checks.containsKey("numberRequired")) {
            if (Objects.isNull(input) || !(input instanceof Integer)) {
                return false;
            }
        }
        if (checks.containsKey("stringRequired")) {
            if (Objects.isNull(input) || !(input instanceof String) || input.equals("")) {
                return false;
            }
        }
        if (checks.containsKey("minLength")) {
            if (input.toString().length() < (Integer) checks.get("minLength")) {
                return false;
            }
        }
        if (checks.containsKey("contains")) {
            if (!input.toString().contains(checks.get("contains").toString())) {
                return false;
            }
        }
        if (checks.containsKey("positive")) {
            if (Objects.nonNull(input) && (Integer) input <= 0) {
                return false;
            }
        }
        if (checks.containsKey("rangeMin")) {
            if ((Integer) input
                    < (Integer) checks.get("rangeMin") || (Integer) input > (Integer) checks.get("rangeMax")) {
                return false;
            }
        }
        if (checks.containsKey("sizeOf")) {
            if (((Map) input).size() != (Integer) checks.get("sizeOf")) {
                return false;
            }
        }
        if (checks.containsKey("shape")) {
            if (Objects.nonNull(input)) {
                if (!(input instanceof Map)) {
                    return false;
                }
                Map<String, BaseSchema> schemas = (Map<String, BaseSchema>) checks.get("shape");
                var schemasKeys = (schemas.keySet());
                for (String key : schemasKeys) {
                    if (!((Map) input).containsKey(key)) {
                        return false;
                    }
                    if (!schemas.get(key).isValid(((Map) input).get(key))) {
                        return false;
                    }
                }
            }
        }
        return true;

         */
        return result;
    }
}

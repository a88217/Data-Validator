package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        checks.put("numberRequired", true);
        return this;
    }
    public NumberSchema positive() {
        checks.put("positive", true);
        return this;
    }
    public NumberSchema range(int min, int max) {
        checks.put("rangeMin", min);
        checks.put("rangeMax", max);
        return this;
    }

}

package hexlet.code.schemas;

public abstract class BaseSchema {

    /**
     * Method can be safely overridden.
     * @param input Validated object
     @return some boolean value.
     * */
    public boolean isValid(Object input) {
        return true;
    }
}

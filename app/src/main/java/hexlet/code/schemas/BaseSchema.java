package hexlet.code.schemas;

public abstract class BaseSchema {

    /**Method can be safely overridden*/
    public boolean isValid(Object input) {
        return true;
    }
}

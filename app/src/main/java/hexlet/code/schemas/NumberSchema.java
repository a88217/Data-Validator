package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    private boolean isRequired = false;
    private boolean isPositive = false;
    private boolean isRange = false;
    private int minNumber;
    private int maxNumber;

    public NumberSchema required() {
        isRequired = true;

        return this;
    }
    public NumberSchema positive() {
        isPositive = true;
        return this;
    }
    public NumberSchema range(int min, int max) {
        isRange = true;
        minNumber = min;
        maxNumber = max;
        return this;
    }

    @Override
    public boolean isValid(Object inputNumber) {
        if (isRequired) {
            if (Objects.isNull(inputNumber) || !(inputNumber instanceof Integer)) {
                return false;
            }
        }
        if (isPositive) {
            if (Objects.nonNull(inputNumber) && (Integer) inputNumber <= 0) {
                return false;
            }
        }
        if (isRange) {
            if ((Integer) inputNumber < minNumber || (Integer) inputNumber > maxNumber) {
                return false;
            }
        }
        return true;
    }

}

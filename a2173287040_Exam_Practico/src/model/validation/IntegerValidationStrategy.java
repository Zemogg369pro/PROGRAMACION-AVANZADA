package model.validation;

/**
 * Validation strategy for Integer values.
 * Implements the Strategy pattern.
 */
public class IntegerValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(Object value, String attributeName) throws IllegalArgumentException {
        // Check if the value is an Integer
        if (!(value instanceof Integer)) {
            throw new IllegalArgumentException("Attribute " + attributeName + " must be an Integer");
        }

        return true;
    }
}
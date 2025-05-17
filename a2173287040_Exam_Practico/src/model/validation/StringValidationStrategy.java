package model.validation;

/**
 * Validation strategy for String values.
 * Implements the Strategy pattern.
 */
public class StringValidationStrategy implements ValidationStrategy {
    private final boolean isUniqueIdentifier;

    /**
     * Constructor for StringValidationStrategy.
     *
     * @param isUniqueIdentifier Whether the attribute is a unique identifier
     */
    public StringValidationStrategy(boolean isUniqueIdentifier) {
        this.isUniqueIdentifier = isUniqueIdentifier;
    }

    @Override
    public boolean validate(Object value, String attributeName) throws IllegalArgumentException {
        // Check if the value is a String
        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Attribute " + attributeName + " must be a String");
        }

        String stringValue = (String) value;

        // If the attribute is a unique identifier, check if it's not empty
        if (isUniqueIdentifier && stringValue.isEmpty()) {
            throw new IllegalArgumentException("Unique identifier " + attributeName + " cannot be empty");
        }

        return true;
    }
}
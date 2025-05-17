package model.validation;

/**
 * Interface for validation strategies.
 * Implements the Strategy pattern.
 */
public interface ValidationStrategy {
    /**
     * Validates a value.
     *
     * @param value The value to validate
     * @param attributeName The name of the attribute being validated
     * @return true if the value is valid, false otherwise
     * @throws IllegalArgumentException If the value is invalid
     */
    boolean validate(Object value, String attributeName) throws IllegalArgumentException;
}
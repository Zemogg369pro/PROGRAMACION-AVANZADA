package model.validation;

import model.AttributeDefinition;
import model.AttributeType;

/**
 * Factory for creating validation strategies.
 * Implements the Factory Method pattern.
 */
public class ValidationStrategyFactory {
    /**
     * Creates a validation strategy for the given attribute definition.
     *
     * @param attributeDefinition The attribute definition
     * @return The validation strategy
     */
    public static ValidationStrategy createValidationStrategy(AttributeDefinition attributeDefinition) {
        AttributeType type = attributeDefinition.getType();
        boolean isUniqueIdentifier = attributeDefinition.isUniqueIdentifier();

        if (type == AttributeType.STRING) {
            return new StringValidationStrategy(isUniqueIdentifier);
        } else if (type == AttributeType.INTEGER) {
            return new IntegerValidationStrategy();
        } else {
            throw new IllegalArgumentException("Unsupported attribute type: " + type);
        }
    }
}
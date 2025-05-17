package model.factory;

import model.AttributeDefinition;
import model.AttributeType;
import model.Entity;

import java.util.List;
import java.util.Map;

/**
 * Factory for creating Entity objects.
 * Implements the Factory Method pattern.
 */
public class EntityFactory {
    /**
     * Creates an entity with the given attribute values.
     *
     * @param attributeValues The attribute values
     * @param attributeDefinitions The attribute definitions
     * @return The created entity
     * @throws IllegalArgumentException If the attribute values are invalid
     */
    public static Entity createEntity(Map<String, Object> attributeValues, List<AttributeDefinition> attributeDefinitions) throws IllegalArgumentException {
        // Validate the attribute values
        validateAttributeValues(attributeValues, attributeDefinitions);

        // Create the entity
        Entity entity = new Entity();
        for (Map.Entry<String, Object> entry : attributeValues.entrySet()) {
            entity.setAttribute(entry.getKey(), entry.getValue());
        }

        return entity;
    }

    /**
     * Validates the attribute values.
     *
     * @param attributeValues The attribute values
     * @param attributeDefinitions The attribute definitions
     * @throws IllegalArgumentException If the attribute values are invalid
     */
    private static void validateAttributeValues(Map<String, Object> attributeValues, List<AttributeDefinition> attributeDefinitions) throws IllegalArgumentException {
        // Check if all required attributes are present
        for (AttributeDefinition definition : attributeDefinitions) {
            if (!attributeValues.containsKey(definition.getName())) {
                throw new IllegalArgumentException("Missing attribute: " + definition.getName());
            }
        }

        // Check if the attribute values have the correct type
        for (AttributeDefinition definition : attributeDefinitions) {
            String attributeName = definition.getName();
            Object attributeValue = attributeValues.get(attributeName);

            if (attributeValue == null) {
                continue;
            }

            // Validate the attribute value type
            if (definition.getType() == AttributeType.STRING && !(attributeValue instanceof String)) {
                throw new IllegalArgumentException("Attribute " + attributeName + " must be a String");
            } else if (definition.getType() == AttributeType.INTEGER && !(attributeValue instanceof Integer)) {
                throw new IllegalArgumentException("Attribute " + attributeName + " must be an Integer");
            }
        }

        // Check if the unique identifier is present and not empty
        for (AttributeDefinition definition : attributeDefinitions) {
            if (definition.isUniqueIdentifier()) {
                String attributeName = definition.getName();
                Object attributeValue = attributeValues.get(attributeName);

                if (attributeValue == null || (attributeValue instanceof String && ((String) attributeValue).isEmpty())) {
                    throw new IllegalArgumentException("Unique identifier " + attributeName + " cannot be empty");
                }
            }
        }
    }
}
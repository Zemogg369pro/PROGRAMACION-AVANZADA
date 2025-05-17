package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a generic entity with dynamic attributes.
 * The attributes are stored in a map where the key is the attribute name
 * and the value is the attribute value.
 */
public class Entity implements Serializable {
    private Map<String, Object> attributes;

    /**
     * Constructor for Entity.
     */
    public Entity() {
        this.attributes = new HashMap<>();
    }

    /**
     * Gets the value of an attribute.
     *
     * @param attributeName The name of the attribute
     * @return The value of the attribute
     */
    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    /**
     * Sets the value of an attribute.
     *
     * @param attributeName The name of the attribute
     * @param value The value of the attribute
     */
    public void setAttribute(String attributeName, Object value) {
        attributes.put(attributeName, value);
    }

    /**
     * Gets all attributes.
     *
     * @return A map of all attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Sets all attributes.
     *
     * @param attributes A map of all attributes
     */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets the unique identifier value for this entity.
     *
     * @param attributeDefinitions The list of attribute definitions
     * @return The value of the unique identifier attribute
     */
    public String getUniqueIdentifierValue(List<AttributeDefinition> attributeDefinitions) {
        for (AttributeDefinition definition : attributeDefinitions) {
            if (definition.isUniqueIdentifier()) {
                Object value = attributes.get(definition.getName());
                return value != null ? value.toString() : null;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Entity{");
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }
        if (!attributes.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
}
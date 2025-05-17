package model;

import java.io.Serializable;

/**
 * Represents the definition of an attribute for an entity.
 * Contains the name, type, and whether it's a unique identifier.
 */
public class AttributeDefinition implements Serializable {
    private String name;
    private AttributeType type;
    private boolean isUniqueIdentifier;

    /**
     * Constructor for AttributeDefinition.
     *
     * @param name The name of the attribute
     * @param type The type of the attribute (STRING or INTEGER)
     * @param isUniqueIdentifier Whether this attribute is a unique identifier
     */
    public AttributeDefinition(String name, AttributeType type, boolean isUniqueIdentifier) {
        this.name = name;
        this.type = type;
        this.isUniqueIdentifier = isUniqueIdentifier;
    }

    /**
     * Default constructor for serialization.
     */
    public AttributeDefinition() {
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public boolean isUniqueIdentifier() {
        return isUniqueIdentifier;
    }

    public void setUniqueIdentifier(boolean uniqueIdentifier) {
        isUniqueIdentifier = uniqueIdentifier;
    }

    @Override
    public String toString() {
        return name + " (" + type + (isUniqueIdentifier ? ", Unique Identifier" : "") + ")";
    }
}
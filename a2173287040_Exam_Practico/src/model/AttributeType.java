package model;

/**
 * Enum representing the possible types of attributes for entities.
 * Currently supports String and Integer types.
 */
public enum AttributeType {
    STRING,
    INTEGER;
    
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
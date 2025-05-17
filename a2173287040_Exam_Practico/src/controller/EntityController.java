package controller;

import model.AttributeDefinition;
import model.Entity;
import model.EntityList;
import model.observer.EntityObserver;
import util.JsonPersistenceService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for Entity objects.
 * Implements the Controller part of the MVC pattern.
 */
public class EntityController {
    private EntityList entityList;
    private final String ENTITIES_FILE = "entities.json";

    /**
     * Constructor for EntityController.
     */
    public EntityController() {
        loadEntities();
    }

    /**
     * Loads the list of entities from the JSON file.
     */
    private void loadEntities() {
        try {
            entityList = JsonPersistenceService.getInstance().loadFromJson();
        } catch (IOException e) {
            e.printStackTrace();
            entityList = new EntityList();
        }
    }

    /**
     * Saves the list of entities to the JSON file.
     */
    public void saveEntities() {
        try {
            JsonPersistenceService.getInstance().saveToJson(entityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the attribute definitions.
     *
     * @param attributeDefinitions The attribute definitions
     */
    public void setAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
        entityList.setAttributeDefinitions(attributeDefinitions);
        saveEntities();
    }

    /**
     * Gets the attribute definitions.
     *
     * @return The attribute definitions
     */
    public List<AttributeDefinition> getAttributeDefinitions() {
        return entityList.getAttributeDefinitions();
    }

    /**
     * Adds an entity.
     *
     * @param entity The entity to add
     * @return true if the entity was added successfully, false otherwise
     */
    public boolean addEntity(Entity entity) {
        boolean added = entityList.add(entity);
        if (added) {
            saveEntities();
        }
        return added;
    }

    /**
     * Removes an entity.
     *
     * @param uniqueId The unique identifier of the entity to remove
     * @return true if the entity was removed successfully, false otherwise
     */
    public boolean removeEntity(String uniqueId) {
        boolean removed = entityList.remove(uniqueId);
        if (removed) {
            saveEntities();
        }
        return removed;
    }

    /**
     * Updates an entity.
     *
     * @param uniqueId The unique identifier of the entity to update
     * @param entity The new entity
     * @return true if the entity was updated successfully, false otherwise
     */
    public boolean updateEntity(String uniqueId, Entity entity) {
        boolean updated = entityList.update(uniqueId, entity);
        if (updated) {
            saveEntities();
        }
        return updated;
    }

    /**
     * Finds an entity.
     *
     * @param uniqueId The unique identifier of the entity to find
     * @return The entity if found, null otherwise
     */
    public Entity findEntity(String uniqueId) {
        return entityList.find(uniqueId);
    }

    /**
     * Gets all entities.
     *
     * @return A list of all entities
     */
    public List<Entity> getAllEntities() {
        return entityList.getAll();
    }

    /**
     * Registers an observer.
     *
     * @param observer The observer to register
     */
    public void addObserver(EntityObserver observer) {
        entityList.addObserver(observer);
    }

    /**
     * Unregisters an observer.
     *
     * @param observer The observer to unregister
     */
    public void removeObserver(EntityObserver observer) {
        entityList.removeObserver(observer);
    }
}
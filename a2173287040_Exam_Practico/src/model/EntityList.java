package model;

import model.observer.EntityObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GenericList for Entity objects.
 * Uses the unique identifier attribute of entities as the key.
 * Implements the Observable part of the Observer pattern.
 */
public class EntityList implements GenericList<Entity, String>, Serializable {
    private List<Entity> entities;
    private List<AttributeDefinition> attributeDefinitions;
    private transient List<EntityObserver> observers;

    /**
     * Constructor for EntityList.
     */
    public EntityList() {
        this.entities = new ArrayList<>();
        this.attributeDefinitions = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Gets the attribute definitions.
     *
     * @return The attribute definitions
     */
    public List<AttributeDefinition> getAttributeDefinitions() {
        return attributeDefinitions;
    }

    /**
     * Sets the attribute definitions.
     *
     * @param attributeDefinitions The attribute definitions
     */
    public void setAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
        this.attributeDefinitions = attributeDefinitions;
    }

    /**
     * Registers an observer.
     *
     * @param observer The observer to register
     */
    public void addObserver(EntityObserver observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    /**
     * Unregisters an observer.
     *
     * @param observer The observer to unregister
     */
    public void removeObserver(EntityObserver observer) {
        if (observers != null) {
            observers.remove(observer);
        }
    }

    /**
     * Notifies all observers that an entity has been added.
     *
     * @param entity The added entity
     */
    private void notifyEntityAdded(Entity entity) {
        if (observers != null) {
            for (EntityObserver observer : observers) {
                observer.onEntityAdded(entity);
            }
        }
    }

    /**
     * Notifies all observers that an entity has been updated.
     *
     * @param oldEntity The old entity
     * @param newEntity The new entity
     */
    private void notifyEntityUpdated(Entity oldEntity, Entity newEntity) {
        if (observers != null) {
            for (EntityObserver observer : observers) {
                observer.onEntityUpdated(oldEntity, newEntity);
            }
        }
    }

    /**
     * Notifies all observers that an entity has been removed.
     *
     * @param entity The removed entity
     */
    private void notifyEntityRemoved(Entity entity) {
        if (observers != null) {
            for (EntityObserver observer : observers) {
                observer.onEntityRemoved(entity);
            }
        }
    }

    /**
     * Notifies all observers that the list of entities has been cleared.
     */
    private void notifyEntitiesCleared() {
        if (observers != null) {
            for (EntityObserver observer : observers) {
                observer.onEntitiesCleared();
            }
        }
    }

    @Override
    public boolean add(Entity entity) {
        // Check if the entity has a unique identifier
        String uniqueId = entity.getUniqueIdentifierValue(attributeDefinitions);
        if (uniqueId == null || uniqueId.isEmpty()) {
            return false;
        }

        // Check if an entity with the same unique identifier already exists
        if (find(uniqueId) != null) {
            return false;
        }

        // Add the entity
        entities.add(entity);

        // Notify observers
        notifyEntityAdded(entity);

        return true;
    }

    @Override
    public boolean remove(String uniqueId) {
        Entity entity = find(uniqueId);
        if (entity == null) {
            return false;
        }

        boolean removed = entities.remove(entity);

        // Notify observers if the entity was removed
        if (removed) {
            notifyEntityRemoved(entity);
        }

        return removed;
    }

    @Override
    public boolean update(String uniqueId, Entity newEntity) {
        // Check if the new entity has a unique identifier
        String newUniqueId = newEntity.getUniqueIdentifierValue(attributeDefinitions);
        if (newUniqueId == null || newUniqueId.isEmpty()) {
            return false;
        }

        // If the unique identifier has changed, check if the new one already exists
        if (!uniqueId.equals(newUniqueId) && find(newUniqueId) != null) {
            return false;
        }

        // Find the entity to update
        Entity oldEntity = find(uniqueId);
        if (oldEntity == null) {
            return false;
        }

        // Update the entity
        int index = entities.indexOf(oldEntity);
        entities.set(index, newEntity);

        // Notify observers
        notifyEntityUpdated(oldEntity, newEntity);

        return true;
    }

    @Override
    public Entity find(String uniqueId) {
        for (Entity entity : entities) {
            String entityUniqueId = entity.getUniqueIdentifierValue(attributeDefinitions);
            if (entityUniqueId != null && entityUniqueId.equals(uniqueId)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(entities);
    }

    @Override
    public void clear() {
        entities.clear();

        // Notify observers
        notifyEntitiesCleared();
    }

    @Override
    public int size() {
        return entities.size();
    }
}
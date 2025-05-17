package model.observer;

import model.Entity;

/**
 * Observer interface for Entity objects.
 * Implements the Observer pattern.
 */
public interface EntityObserver {
    /**
     * Called when an entity is added.
     *
     * @param entity The added entity
     */
    void onEntityAdded(Entity entity);

    /**
     * Called when an entity is updated.
     *
     * @param oldEntity The old entity
     * @param newEntity The new entity
     */
    void onEntityUpdated(Entity oldEntity, Entity newEntity);

    /**
     * Called when an entity is removed.
     *
     * @param entity The removed entity
     */
    void onEntityRemoved(Entity entity);

    /**
     * Called when all entities are cleared.
     */
    void onEntitiesCleared();
}
package model;

import java.util.List;

/**
 * Generic interface for a list of entities.
 * Provides methods for CRUD operations.
 *
 * @param <T> The type of entity
 * @param <K> The type of key used to identify entities
 */
public interface GenericList<T, K> {
    /**
     * Adds an entity to the list.
     *
     * @param entity The entity to add
     * @return true if the entity was added successfully, false otherwise
     */
    boolean add(T entity);

    /**
     * Removes an entity from the list.
     *
     * @param key The key of the entity to remove
     * @return true if the entity was removed successfully, false otherwise
     */
    boolean remove(K key);

    /**
     * Updates an entity in the list.
     *
     * @param key The key of the entity to update
     * @param entity The new entity
     * @return true if the entity was updated successfully, false otherwise
     */
    boolean update(K key, T entity);

    /**
     * Finds an entity in the list.
     *
     * @param key The key of the entity to find
     * @return The entity if found, null otherwise
     */
    T find(K key);

    /**
     * Gets all entities in the list.
     *
     * @return A list of all entities
     */
    List<T> getAll();

    /**
     * Clears the list.
     */
    void clear();

    /**
     * Gets the number of entities in the list.
     *
     * @return The number of entities
     */
    int size();
}
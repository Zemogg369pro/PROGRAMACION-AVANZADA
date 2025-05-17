package model.observer;

import model.Materia;

/**
 * Observer interface for Materia objects.
 * Implements the Observer pattern.
 */
public interface MateriaObserver {
    /**
     * Called when a materia is added.
     *
     * @param materia The added materia
     */
    void onMateriaAdded(Materia materia);

    /**
     * Called when a materia is updated.
     *
     * @param oldMateria The old materia
     * @param newMateria The new materia
     */
    void onMateriaUpdated(Materia oldMateria, Materia newMateria);

    /**
     * Called when a materia is removed.
     *
     * @param materia The removed materia
     */
    void onMateriaRemoved(Materia materia);

    /**
     * Called when all materias are cleared.
     */
    void onMateriasCleared();
}
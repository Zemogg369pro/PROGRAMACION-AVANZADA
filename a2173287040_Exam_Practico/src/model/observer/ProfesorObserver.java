package model.observer;

import model.Profesor;

/**
 * Observer interface for Profesor objects.
 * Implements the Observer pattern.
 */
public interface ProfesorObserver {
    /**
     * Called when a profesor is added.
     *
     * @param profesor The added profesor
     */
    void onProfesorAdded(Profesor profesor);

    /**
     * Called when a profesor is updated.
     *
     * @param oldProfesor The old profesor
     * @param newProfesor The new profesor
     */
    void onProfesorUpdated(Profesor oldProfesor, Profesor newProfesor);

    /**
     * Called when a profesor is removed.
     *
     * @param profesor The removed profesor
     */
    void onProfesorRemoved(Profesor profesor);

    /**
     * Called when all profesores are cleared.
     */
    void onProfesoresCleared();
}
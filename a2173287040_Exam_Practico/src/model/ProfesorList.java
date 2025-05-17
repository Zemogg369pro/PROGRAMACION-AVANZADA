package model;

import model.observer.ProfesorObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GenericList for Profesor objects.
 * Uses the ID of profesores as the key.
 * Implements the Observable part of the Observer pattern.
 */
public class ProfesorList implements GenericList<Profesor, String>, Serializable {
    private List<Profesor> profesores;
    private transient List<ProfesorObserver> observers;

    /**
     * Constructor for ProfesorList.
     */
    public ProfesorList() {
        this.profesores = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Registers an observer.
     *
     * @param observer The observer to register
     */
    public void addObserver(ProfesorObserver observer) {
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
    public void removeObserver(ProfesorObserver observer) {
        if (observers != null) {
            observers.remove(observer);
        }
    }

    /**
     * Notifies all observers that a profesor has been added.
     *
     * @param profesor The added profesor
     */
    private void notifyProfesorAdded(Profesor profesor) {
        if (observers != null) {
            for (ProfesorObserver observer : observers) {
                observer.onProfesorAdded(profesor);
            }
        }
    }

    /**
     * Notifies all observers that a profesor has been updated.
     *
     * @param oldProfesor The old profesor
     * @param newProfesor The new profesor
     */
    private void notifyProfesorUpdated(Profesor oldProfesor, Profesor newProfesor) {
        if (observers != null) {
            for (ProfesorObserver observer : observers) {
                observer.onProfesorUpdated(oldProfesor, newProfesor);
            }
        }
    }

    /**
     * Notifies all observers that a profesor has been removed.
     *
     * @param profesor The removed profesor
     */
    private void notifyProfesorRemoved(Profesor profesor) {
        if (observers != null) {
            for (ProfesorObserver observer : observers) {
                observer.onProfesorRemoved(profesor);
            }
        }
    }

    /**
     * Notifies all observers that the list of profesores has been cleared.
     */
    private void notifyProfesoresCleared() {
        if (observers != null) {
            for (ProfesorObserver observer : observers) {
                observer.onProfesoresCleared();
            }
        }
    }

    @Override
    public boolean add(Profesor profesor) {
        // Check if the profesor has an ID
        if (profesor.getId() == null || profesor.getId().isEmpty()) {
            return false;
        }

        // Check if a profesor with the same ID already exists
        if (find(profesor.getId()) != null) {
            return false;
        }

        // Add the profesor
        profesores.add(profesor);

        // Notify observers
        notifyProfesorAdded(profesor);

        return true;
    }

    @Override
    public boolean remove(String id) {
        Profesor profesor = find(id);
        if (profesor == null) {
            return false;
        }

        boolean removed = profesores.remove(profesor);

        // Notify observers if the profesor was removed
        if (removed) {
            notifyProfesorRemoved(profesor);
        }

        return removed;
    }

    @Override
    public boolean update(String id, Profesor newProfesor) {
        // Check if the new profesor has an ID
        if (newProfesor.getId() == null || newProfesor.getId().isEmpty()) {
            return false;
        }

        // If the ID has changed, check if the new one already exists
        if (!id.equals(newProfesor.getId()) && find(newProfesor.getId()) != null) {
            return false;
        }

        // Find the profesor to update
        Profesor oldProfesor = find(id);
        if (oldProfesor == null) {
            return false;
        }

        // Update the profesor
        int index = profesores.indexOf(oldProfesor);
        profesores.set(index, newProfesor);

        // Notify observers
        notifyProfesorUpdated(oldProfesor, newProfesor);

        return true;
    }

    @Override
    public Profesor find(String id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() != null && profesor.getId().equals(id)) {
                return profesor;
            }
        }
        return null;
    }

    @Override
    public List<Profesor> getAll() {
        return new ArrayList<>(profesores);
    }

    @Override
    public void clear() {
        profesores.clear();

        // Notify observers
        notifyProfesoresCleared();
    }

    @Override
    public int size() {
        return profesores.size();
    }
}
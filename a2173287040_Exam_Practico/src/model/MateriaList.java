package model;

import model.observer.MateriaObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GenericList for Materia objects.
 * Uses the ID of materias as the key.
 * Implements the Observable part of the Observer pattern.
 */
public class MateriaList implements GenericList<Materia, String>, Serializable {
    private List<Materia> materias;
    private transient List<MateriaObserver> observers;

    /**
     * Constructor for MateriaList.
     */
    public MateriaList() {
        this.materias = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Registers an observer.
     *
     * @param observer The observer to register
     */
    public void addObserver(MateriaObserver observer) {
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
    public void removeObserver(MateriaObserver observer) {
        if (observers != null) {
            observers.remove(observer);
        }
    }

    /**
     * Notifies all observers that a materia has been added.
     *
     * @param materia The added materia
     */
    private void notifyMateriaAdded(Materia materia) {
        if (observers != null) {
            for (MateriaObserver observer : observers) {
                observer.onMateriaAdded(materia);
            }
        }
    }

    /**
     * Notifies all observers that a materia has been updated.
     *
     * @param oldMateria The old materia
     * @param newMateria The new materia
     */
    private void notifyMateriaUpdated(Materia oldMateria, Materia newMateria) {
        if (observers != null) {
            for (MateriaObserver observer : observers) {
                observer.onMateriaUpdated(oldMateria, newMateria);
            }
        }
    }

    /**
     * Notifies all observers that a materia has been removed.
     *
     * @param materia The removed materia
     */
    private void notifyMateriaRemoved(Materia materia) {
        if (observers != null) {
            for (MateriaObserver observer : observers) {
                observer.onMateriaRemoved(materia);
            }
        }
    }

    /**
     * Notifies all observers that the list of materias has been cleared.
     */
    private void notifyMateriasCleared() {
        if (observers != null) {
            for (MateriaObserver observer : observers) {
                observer.onMateriasCleared();
            }
        }
    }

    @Override
    public boolean add(Materia materia) {
        // Check if the materia has an ID
        if (materia.getId() == null || materia.getId().isEmpty()) {
            return false;
        }

        // Check if a materia with the same ID already exists
        if (find(materia.getId()) != null) {
            return false;
        }

        // Add the materia
        materias.add(materia);

        // Notify observers
        notifyMateriaAdded(materia);

        return true;
    }

    @Override
    public boolean remove(String id) {
        Materia materia = find(id);
        if (materia == null) {
            return false;
        }

        boolean removed = materias.remove(materia);

        // Notify observers if the materia was removed
        if (removed) {
            notifyMateriaRemoved(materia);
        }

        return removed;
    }

    @Override
    public boolean update(String id, Materia newMateria) {
        // Check if the new materia has an ID
        if (newMateria.getId() == null || newMateria.getId().isEmpty()) {
            return false;
        }

        // If the ID has changed, check if the new one already exists
        if (!id.equals(newMateria.getId()) && find(newMateria.getId()) != null) {
            return false;
        }

        // Find the materia to update
        Materia oldMateria = find(id);
        if (oldMateria == null) {
            return false;
        }

        // Update the materia
        int index = materias.indexOf(oldMateria);
        materias.set(index, newMateria);

        // Notify observers
        notifyMateriaUpdated(oldMateria, newMateria);

        return true;
    }

    @Override
    public Materia find(String id) {
        for (Materia materia : materias) {
            if (materia.getId() != null && materia.getId().equals(id)) {
                return materia;
            }
        }
        return null;
    }

    @Override
    public List<Materia> getAll() {
        return new ArrayList<>(materias);
    }

    @Override
    public void clear() {
        materias.clear();

        // Notify observers
        notifyMateriasCleared();
    }

    @Override
    public int size() {
        return materias.size();
    }
}
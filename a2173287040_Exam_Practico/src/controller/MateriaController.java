package controller;

import model.Materia;
import model.MateriaList;
import util.JsonPersistenceService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for Materia objects.
 * Implements the Controller part of the MVC pattern.
 */
public class MateriaController {
    private MateriaList materiaList;
    private final String MATERIAS_FILE = "materias.json";

    /**
     * Constructor for MateriaController.
     */
    public MateriaController() {
        loadMaterias();
    }

    /**
     * Loads the list of materias from the JSON file.
     */
    private void loadMaterias() {
        try {
            materiaList = (MateriaList) JsonPersistenceService.getInstance().loadFromJson(MATERIAS_FILE, new MateriaList());
        } catch (IOException e) {
            e.printStackTrace();
            materiaList = new MateriaList();
        }
    }

    /**
     * Saves the list of materias to the JSON file.
     */
    public void saveMaterias() {
        try {
            JsonPersistenceService.getInstance().saveToJson(materiaList, MATERIAS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a materia.
     *
     * @param materia The materia to add
     * @return true if the materia was added successfully, false otherwise
     */
    public boolean addMateria(Materia materia) {
        boolean added = materiaList.add(materia);
        if (added) {
            saveMaterias();
        }
        return added;
    }

    /**
     * Removes a materia.
     *
     * @param id The ID of the materia to remove
     * @return true if the materia was removed successfully, false otherwise
     */
    public boolean removeMateria(String id) {
        boolean removed = materiaList.remove(id);
        if (removed) {
            saveMaterias();
        }
        return removed;
    }

    /**
     * Updates a materia.
     *
     * @param id The ID of the materia to update
     * @param materia The new materia
     * @return true if the materia was updated successfully, false otherwise
     */
    public boolean updateMateria(String id, Materia materia) {
        boolean updated = materiaList.update(id, materia);
        if (updated) {
            saveMaterias();
        }
        return updated;
    }

    /**
     * Finds a materia.
     *
     * @param id The ID of the materia to find
     * @return The materia if found, null otherwise
     */
    public Materia findMateria(String id) {
        return materiaList.find(id);
    }

    /**
     * Gets all materias.
     *
     * @return A list of all materias
     */
    public List<Materia> getAllMaterias() {
        return materiaList.getAll();
    }

    /**
     * Registers an observer.
     *
     * @param observer The observer to register
     */
    public void addObserver(model.observer.MateriaObserver observer) {
        materiaList.addObserver(observer);
    }

    /**
     * Unregisters an observer.
     *
     * @param observer The observer to unregister
     */
    public void removeObserver(model.observer.MateriaObserver observer) {
        materiaList.removeObserver(observer);
    }
}
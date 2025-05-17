package controller;

import model.Profesor;
import model.ProfesorList;
import util.JsonPersistenceService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for Profesor objects.
 * Implements the Controller part of the MVC pattern.
 */
public class ProfesorController {
    private ProfesorList profesorList;
    private final String PROFESORES_FILE = "profesores.json";

    /**
     * Constructor for ProfesorController.
     */
    public ProfesorController() {
        loadProfesores();
    }

    /**
     * Loads the list of profesores from the JSON file.
     */
    private void loadProfesores() {
        try {
            profesorList = (ProfesorList) JsonPersistenceService.getInstance().loadFromJson(PROFESORES_FILE, new ProfesorList());
        } catch (IOException e) {
            e.printStackTrace();
            profesorList = new ProfesorList();
        }
    }

    /**
     * Saves the list of profesores to the JSON file.
     */
    public void saveProfesores() {
        try {
            JsonPersistenceService.getInstance().saveToJson(profesorList, PROFESORES_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a profesor.
     *
     * @param profesor The profesor to add
     * @return true if the profesor was added successfully, false otherwise
     */
    public boolean addProfesor(Profesor profesor) {
        boolean added = profesorList.add(profesor);
        if (added) {
            saveProfesores();
        }
        return added;
    }

    /**
     * Removes a profesor.
     *
     * @param id The ID of the profesor to remove
     * @return true if the profesor was removed successfully, false otherwise
     */
    public boolean removeProfesor(String id) {
        boolean removed = profesorList.remove(id);
        if (removed) {
            saveProfesores();
        }
        return removed;
    }

    /**
     * Updates a profesor.
     *
     * @param id The ID of the profesor to update
     * @param profesor The new profesor
     * @return true if the profesor was updated successfully, false otherwise
     */
    public boolean updateProfesor(String id, Profesor profesor) {
        boolean updated = profesorList.update(id, profesor);
        if (updated) {
            saveProfesores();
        }
        return updated;
    }

    /**
     * Finds a profesor.
     *
     * @param id The ID of the profesor to find
     * @return The profesor if found, null otherwise
     */
    public Profesor findProfesor(String id) {
        return profesorList.find(id);
    }

    /**
     * Gets all profesores.
     *
     * @return A list of all profesores
     */
    public List<Profesor> getAllProfesores() {
        return profesorList.getAll();
    }

    /**
     * Registers an observer.
     *
     * @param observer The observer to register
     */
    public void addObserver(model.observer.ProfesorObserver observer) {
        profesorList.addObserver(observer);
    }

    /**
     * Unregisters an observer.
     *
     * @param observer The observer to unregister
     */
    public void removeObserver(model.observer.ProfesorObserver observer) {
        profesorList.removeObserver(observer);
    }
}
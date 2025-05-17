package model;

import java.io.Serializable;

/**
 * Represents a Materia (Subject) entity with predefined attributes.
 */
public class Materia implements Serializable {
    private String id;
    private String nombre;
    private String salon;
    private String horario;
    private String profesorId;
    private int creditos;
    private String descripcion;

    /**
     * Default constructor for serialization.
     */
    public Materia() {
    }

    /**
     * Constructor for Materia.
     *
     * @param id El identificador único de la materia
     * @param nombre El nombre de la materia
     * @param salon El salón donde se imparte la materia
     * @param horario El horario de la materia
     * @param profesorId El ID del profesor que imparte la materia
     * @param creditos Los créditos de la materia
     * @param descripcion La descripción de la materia
     */
    public Materia(String id, String nombre, String salon, String horario, String profesorId, int creditos, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.salon = salon;
        this.horario = horario;
        this.profesorId = profesorId;
        this.creditos = creditos;
        this.descripcion = descripcion;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(String profesorId) {
        this.profesorId = profesorId;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", salon='" + salon + '\'' +
                ", horario='" + horario + '\'' +
                ", profesorId='" + profesorId + '\'' +
                ", creditos=" + creditos +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
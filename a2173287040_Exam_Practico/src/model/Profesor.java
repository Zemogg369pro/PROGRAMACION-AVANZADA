package model;

import java.io.Serializable;

/**
 * Represents a Profesor (Teacher) entity with predefined attributes.
 */
public class Profesor implements Serializable {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String departamento;
    private String especialidad;
    private int edad;

    /**
     * Default constructor for serialization.
     */
    public Profesor() {
    }

    /**
     * Constructor for Profesor.
     *
     * @param id El identificador único del profesor
     * @param nombre El nombre del profesor
     * @param apellido El apellido del profesor
     * @param email El email del profesor
     * @param telefono El teléfono del profesor
     * @param departamento El departamento del profesor
     * @param especialidad La especialidad del profesor
     */
    public Profesor(String id, String nombre, String apellido, String email, String telefono, String departamento, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.departamento = departamento;
        this.especialidad = especialidad;
    }

    /**
     * Constructor for Profesor with edad.
     *
     * @param id El identificador único del profesor
     * @param nombre El nombre del profesor
     * @param apellido El apellido del profesor
     * @param especialidad La especialidad del profesor
     * @param edad La edad del profesor
     * @param email El email del profesor
     */
    public Profesor(String id, String nombre, String apellido, String especialidad, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.edad = edad;
        this.email = email;
        this.telefono = "";
        this.departamento = "";
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", departamento='" + departamento + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", edad=" + edad +
                '}';
    }
}

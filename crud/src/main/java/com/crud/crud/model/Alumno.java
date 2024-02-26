package com.crud.crud.model;

public class Alumno {
    private int id;
    private String nombre;
    private String apellidos;
    private String correo;
    private Grado grado;

    // Constructor sin parámetros
    public Alumno() {
    }

    // Constructor con parámetros
    public Alumno(int id, String nombre, String apellidos, String correo, Grado grado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.grado = grado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    // Enum para el grado
    public enum Grado {
        DAW, DAM, ASIR
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Alumno{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", correo='" + correo + '\'' +
               ", grado=" + grado +
               '}';
    }
}

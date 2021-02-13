package com.proventaja.tiendit.petagram;

public class Mascota {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mascota(int id) {
        this.id = id;
    }

    private int id;
    private String nombre;
    private int imagen;

    public Mascota() {

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    private int calificacion;

    public Mascota(int imagen, String nombre, int calificacion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.calificacion = calificacion;
    }
}

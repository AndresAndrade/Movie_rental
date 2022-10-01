package cts.movie_rental.beans;

import java.sql.Date;

public class Alquiler {
    private  int id;
    private String username;
    private Date fechaAlquiler;
    private boolean novedad;
    private String genero;
    private String titulo;

    public Alquiler(int id, String username, Date fechaAlquiler, boolean novedad, String genero) {
        this.id = id;
        this.username = username;
        this.fechaAlquiler = fechaAlquiler;
        this.novedad = novedad;
        this.genero = genero;
    }

    public Alquiler(int id, Date fechaAlquiler, boolean novedad, String genero, String titulo) {
        this.id = id;
        this.fechaAlquiler = fechaAlquiler;
        this.novedad = novedad;
        this.genero = genero;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "id=" + id +
                ", username=" + username +
                ", fechaAlquiler=" + fechaAlquiler +
                ", novedad=" + novedad +
                ", genero='" + genero + '\'' +
                '}';
    }
}

package cts.movie_rental.controller;

public interface IPeliculaController {
    String listarPeliculas(boolean ordenar, String orden);
    String alquilar(int id, String username);
    String modificar(int id);
}

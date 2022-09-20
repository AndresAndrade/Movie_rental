package cts.movie_rental.controller;

public interface IUsuarioController {
     String login(String username, String contrasena);
     String register(String username, String contrasena, String nombre, String apellido, String email, double saldo, boolean premium);
     String pedir(String username);
     String restarDinero(String username, double nuevoSaldo);
}
package cts.movie_rental.controller;

import java.util.Map;

public interface IUsuarioController {
     String login(String username, String contrasena);
     String register(String username, String contrasena, String nombre, String apellido, String email, double saldo, boolean premium);
     String pedir(String username);
     String restarDinero(String username, double nuevoSaldo);
     String modificar(String username, String nuevaContrasena, String nuevoNombre, String nuevoApellido, String nuevoEmail, double nuevoSaldo, boolean nuevoPremium);
     String verCopias(String username);
     String devolverPeliculas(String username, Map<Integer, Integer> copias);
     String eliminar(String username);
}
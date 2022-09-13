package cts.movie_rental.controller;

import com.google.gson.Gson;
import cts.movie_rental.beans.Usuario;
import cts.movie_rental.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioController implements IUsuarioController{

    @Override
    public String login(String username, String contrasena) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM usuarios WHERE username = '" + username + "' AND contrasena = '" + contrasena + "'";

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                boolean premium = rs.getBoolean("premium");

                Usuario usuario = new Usuario(username, contrasena, nombre, apellido, email, saldo, premium);
                return gson.toJson(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String register(String username, String contrasena, String nombre, String apellido, String email, double saldo, boolean premium) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "INSERT INTO usuarios VALUES('" + username + "', '" + contrasena +"', '" + nombre + "', '" + apellido + "', '" + email + "', " + saldo + ", " + premium + ")";

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);
            Usuario usuario = new Usuario(username, contrasena, nombre, apellido, email, saldo, premium);
            stm.close();
            return gson.toJson(usuario);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}

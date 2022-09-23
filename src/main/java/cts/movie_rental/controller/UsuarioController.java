package cts.movie_rental.controller;

import com.google.gson.Gson;
import cts.movie_rental.beans.Usuario;
import cts.movie_rental.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String pedir(String username) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM usuarios WHERE username = '" + username + "'";

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
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
    public String restarDinero(String username, double nuevoSaldo) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE usuarios SET saldo = " + nuevoSaldo + " WHERE username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String modificar(String username, String nuevaContrasena, String nuevoNombre, String nuevoApellido, String nuevoEmail, double nuevoSaldo, boolean nuevoPremium) {

        DBConnection con = new DBConnection();

        String sql = "UPDATE usuarios SET contrasena = '" + nuevaContrasena +
                "', nombre = '" + nuevoNombre + "', "
                + "apellido = '" + nuevoApellido + "', email = '"
                + nuevoEmail + "', saldo = " + nuevoSaldo + ", premium = ";

        if (nuevoPremium == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }

        sql += " WHERE username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String verCopias(String username) {

        DBConnection con = new DBConnection();
        String sql = "SELECT id, COUNT(*) AS num_copias FROM alquiler WHERE username = '"
                + username + "' GROUP BY id;";

        Map<Integer, Integer> copias = new HashMap<>();

        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int num_copias = rs.getInt("num_copias");

                copias.put(id, num_copias);
            }

            devolverPeliculas(username, copias);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String devolverPeliculas(String username, Map<Integer, Integer> copias) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> pelicula : copias.entrySet()) {
                int id = pelicula.getKey();
                int num_copias = pelicula.getValue();

                String sql = "UPDATE peliculas SET copias = (SELECT copias + " + num_copias +
                        " FROM peliculas WHERE id = " + id + ") WHERE id = " + id;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);
            }
            this.eliminar(username);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    public String eliminar(String username) {

        DBConnection con = new DBConnection();

        String sql1 = "DELETE FROM alquiler WHERE username = '" + username + "'";
        String sql2 = "DELETE FROM usuarios WHERE username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }
}

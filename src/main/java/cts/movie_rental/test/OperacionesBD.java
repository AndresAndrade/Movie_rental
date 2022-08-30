package cts.movie_rental.test;

import cts.movie_rental.beans.Peliculas;
import cts.movie_rental.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesBD {
    public static void main(String[] args) throws SQLException {
        listarPeliculas();
    }

    public static void actualizarPelicula(int id, String genero) throws SQLException {
        DBConnection conn = new DBConnection();
        String sql = "UPDATE peliculas SET genero = '" + genero + "' WHERE id = " + id;

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            conn.desconectar();
        }
    }

    public static void listarPeliculas() throws SQLException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM peliculas";

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String autor = rs.getString("autor");
                int copias = rs.getInt("copias");
                boolean novedad = rs.getBoolean("novedad");

                Peliculas peliculas = new Peliculas(id, titulo, genero, autor, copias, novedad);
                System.out.println(peliculas);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            conn.desconectar();
        }
    }
}

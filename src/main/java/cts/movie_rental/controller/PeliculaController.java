package cts.movie_rental.controller;

import com.google.gson.Gson;
import cts.movie_rental.beans.Pelicula;
import cts.movie_rental.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeliculaController implements IPeliculaController{
    @Override
    public String listarPeliculas(boolean ordenar, String orden) {
        Gson gson = new Gson();

        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM peliculas";

        if (ordenar == true) {
            sql += " ORDER BY genero " + orden;
        }

        List<String> peliculas = new ArrayList<>();


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

                Pelicula pelicula = new Pelicula(id, titulo, genero, autor, copias, novedad);
                peliculas.add(gson.toJson(pelicula));
            }
        } catch (SQLException e) {
            System.out.println("ERROR " + e.getMessage());;
        } finally {
            conn.desconectar();
        }

        return gson.toJson(peliculas);
    }
}

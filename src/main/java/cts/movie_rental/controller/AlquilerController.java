package cts.movie_rental.controller;

import com.google.gson.Gson;
import cts.movie_rental.beans.Alquiler;
import cts.movie_rental.connection.DBConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlquilerController implements IAlquilerController {

    @Override
    public String listarAlquileres(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "SELECT p.id, titulo, genero, novedad, fecha FROM peliculas p "
                + "INNER JOIN alquiler a USING(id) INNER JOIN usuarios u USING(username) "
                + "WHERE a.username = '" + username + "'";

        List<String> alquileres = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                boolean novedad = rs.getBoolean("novedad");
                Date fechaAlquiler = rs.getDate("fecha");

                Alquiler alquiler = new Alquiler(id,fechaAlquiler, novedad, genero, titulo);

                alquileres.add(gson.toJson(alquiler));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(alquileres);
    }
}

package cts.movie_rental.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String bd = "movies_rental";
    static String port = "3307";
    static String login = "root";
    static String password = "admin";
    static String url = "jdbc:mysql://localhost:" + port + "/" + bd;

    Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
            if (connection == null) {
                System.out.println("La conexión a " + bd + " ha fallado");
            } else {
                System.out.println("La conexión a " + bd + " ha sido exitosa");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        connection = null;
    }
}

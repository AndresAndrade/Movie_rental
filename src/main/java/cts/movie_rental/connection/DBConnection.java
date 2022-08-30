package cts.movie_rental.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String db = "movies_rental";
    static String port = "3307";
    static String login = "root";
    static String password = "admin";

    Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + this.port + "/" + this.db;
            connection = DriverManager.getConnection(url, this.login, this.password);
            if (connection == null) {
                System.out.println("Conexión fallida");
            } else {
                System.out.println("Conexión exitosa");
            }
        } catch (Exception ex) {
            System.err.println("ERROR");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void desconectar() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }
}

package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3312/JUANITA";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/JUANITA",
                    "root",
                    "tu_password"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

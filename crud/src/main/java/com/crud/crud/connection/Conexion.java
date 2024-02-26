package com.crud.crud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection con = null;

    public static Connection getConexion() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/alumnos?useSSL=false";
                String user = "user"; // Actualiza con tu usuario
                String password = ""; // Actualiza con tu contraseña
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
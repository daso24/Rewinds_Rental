package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD
{
    private static final String URL = "jdbc:mysql://localhost:3306/rewinds_rental_db";
    private static final String USER = "rewinds_app";
    private static final String PASSWORD = "rewinds123";

    public Connection conectar()
    {
        Connection conexion = null;
        try
        {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        }
        catch (SQLException e)
        {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}
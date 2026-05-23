package models;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexionBD
{
    public Connection conectar()
    {
        Connection conexion = null;
        Properties propiedadesConfiguracion = new Properties();

        try (FileInputStream flujoEntrada = new FileInputStream("config.properties"))
        {
            propiedadesConfiguracion.load(flujoEntrada);

            String urlBaseDatos = propiedadesConfiguracion.getProperty("db.url");
            String usuarioBaseDatos = propiedadesConfiguracion.getProperty("db.user");
            String contrasenaBaseDatos = propiedadesConfiguracion.getProperty("db.password");

            conexion = DriverManager.getConnection(urlBaseDatos, usuarioBaseDatos, contrasenaBaseDatos);
            System.out.println("Conexión exitosa");
        }
        catch (Exception e)
        {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        
        return conexion;
    }
}
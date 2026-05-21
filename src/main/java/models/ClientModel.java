package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientModel
{
    private ConexionBD conexionBD;

    public ClientModel()
    {
        this.conexionBD = new ConexionBD();
    }

    public boolean registrarCliente(String correo, String contrasena, String nombreCompleto, String telefono, String genero)
    {
        String[] partesNombre = nombreCompleto.split(" ", 2);
        String nombres = partesNombre[0];
        String apellidos = partesNombre.length > 1 ? partesNombre[1] : ""; 

        String sql = "INSERT INTO CLIENTE (correo_electronico, contrasena, nombres, apellidos, nivel_fidelidad, telefono, genero) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = conexionBD.conectar();
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ps.setString(3, nombres);
            ps.setString(4, apellidos);
            ps.setString(5, "Basico"); // Nivel por defecto
            ps.setString(6, telefono);
            ps.setString(7, genero);
            
            ps.execute();
            return true;
        }
        catch (SQLException e)
        {
            System.err.println("Error en el registro: " + e.getMessage());
            return false;
        }
        finally
        {
            try { if (con != null) con.close(); } catch (SQLException e) { }
        }
    }
}
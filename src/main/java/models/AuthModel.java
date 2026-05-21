package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthModel
{
    private ConexionBD conexionBD;

    public AuthModel()
    {
        this.conexionBD = new ConexionBD();
    }

    public String validarLogin(String correo, String contrasena)
    {
        String sql = "SELECT nombres FROM CLIENTE WHERE correo_electronico = ? AND contrasena = ?";
        Connection con = conexionBD.conectar();
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
            {
                return rs.getString("nombres"); 
            }
            
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if (con != null)
                {
                    con.close();
                }
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
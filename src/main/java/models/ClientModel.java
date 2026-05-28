package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientModel
{
    public boolean registrarCliente(String nombres, String apellidos, String telefono, String correo, String rutaFoto, String fechaNacimiento)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "INSERT INTO cliente (nombres, apellidos, telefono, correo_electronico, foto, contrasena, nivel_fidelidad, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setString(5, rutaFoto);
            ps.setString(6, "1234");
            ps.setString(7, "Basico");
            ps.setString(8, fechaNacimiento);

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println("❌ ERROR SQL AL GUARDAR CLIENTE:");
            e.printStackTrace();
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }

    public boolean registrarUsuario(String correo, String pass, String usuario, String telefono, String genero)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "INSERT INTO cliente (nombres, apellidos, telefono, correo_electronico, contrasena, nivel_fidelidad, fecha_nacimiento) VALUES (?, 'Prueba', ?, ?, ?, 'Basico', '2000-01-01')";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, telefono);
            ps.setString(3, correo);
            ps.setString(4, pass);

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println("ERROR SQL AL REGISTRAR USUARIO:");
            e.printStackTrace();
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }

    public List<Object[]> obtenerClientes()
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try 
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT id_cliente, nombres, apellidos, foto FROM cliente";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next())
            {
                String nombreCompleto = rs.getString("nombres") + " " + rs.getString("apellidos");
                String rutaFoto = rs.getString("foto");
                lista.add(new Object[]{rs.getInt("id_cliente"), nombreCompleto, rutaFoto});
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR SQL AL OBTENER CLIENTES:");
            e.printStackTrace();
        }
        finally
        {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }

        return lista;
    }

    public Object[] obtenerDetalleCliente(int idCliente)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try 
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT nombres, apellidos, telefono, foto, fecha_nacimiento FROM cliente WHERE id_cliente = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next())
            {
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String rutaFoto = rs.getString("foto");
                String fechaNac = rs.getString("fecha_nacimiento");
                return new Object[]{nombres, apellidos, telefono, rutaFoto, fechaNac};
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR SQL AL OBTENER DETALLE DEL CLIENTE:");
            e.printStackTrace();
        }
        finally
        {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
        return null;
    }

    public boolean actualizarCliente(int idCliente, String nombres, String apellidos, String telefono, String fechaNac, String rutaFoto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "UPDATE cliente SET nombres = ?, apellidos = ?, telefono = ?, fecha_nacimiento = ?, foto = ? WHERE id_cliente = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, telefono);
            ps.setString(4, fechaNac);
            ps.setString(5, rutaFoto);
            ps.setInt(6, idCliente);
            
            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println("❌ ERROR SQL AL ACTUALIZAR CLIENTE:");
            e.printStackTrace();
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }

    public boolean eliminarCliente(int idCliente)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println("❌ ERROR SQL AL ELIMINAR CLIENTE:");
            e.printStackTrace();
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }
    
    public int obtenerTotalClientes()
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT COUNT(*) FROM cliente";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR SQL AL OBTENER TOTAL DE CLIENTES:");
            e.printStackTrace();
        }
        finally
        {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
        return 0;
    }
}
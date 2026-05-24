package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoModel
{
    public List<Object[]> obtenerVideojuegos()
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT id_videojuego, titulo, plataforma, precio_renta, foto FROM videojuego";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("id_videojuego");
                String titulo = rs.getString("titulo");
                String plataforma = rs.getString("plataforma");
                String precioRenta = "$" + rs.getString("precio_renta");
                String rutaFoto = rs.getString("foto");

                lista.add(new Object[]{id, titulo, plataforma, precioRenta, rutaFoto});
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
        return lista;
    }

    public boolean registrarVideojuego(String titulo, String plataforma, double pVenta, double pRenta, int descuento, int sVenta, int sRenta, String clasificacion, int anio, String foto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "INSERT INTO videojuego (titulo, plataforma, precio_venta, precio_renta, descuento, stock_venta, stock_renta, clasificacion, anio, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, plataforma);
            ps.setDouble(3, pVenta);
            ps.setDouble(4, pRenta);
            ps.setInt(5, descuento);
            ps.setInt(6, sVenta);
            ps.setInt(7, sRenta);
            ps.setString(8, clasificacion);
            ps.setInt(9, anio);
            ps.setString(10, foto);

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }

    public boolean eliminarVideojuego(int idVideojuego)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "DELETE FROM videojuego WHERE id_videojuego = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idVideojuego);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }

    public Object[] obtenerVideojuegoPorId(int idVideojuego)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT * FROM videojuego WHERE id_videojuego = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idVideojuego);
            rs = ps.executeQuery();

            if (rs.next())
            {
                return new Object[]
                {
                    rs.getInt("id_videojuego"),
                    rs.getString("titulo"),
                    rs.getString("plataforma"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_renta"),
                    rs.getInt("descuento"),
                    rs.getInt("stock_venta"),
                    rs.getInt("stock_renta"),
                    rs.getString("clasificacion"),
                    rs.getInt("anio"),
                    rs.getString("foto")
                };
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
        return null;
    }

    public boolean actualizarVideojuego(int id, String titulo, String plataforma, double pVenta, double pRenta, int descuento, int sVenta, int sRenta, String clasificacion, int anio, String foto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "UPDATE videojuego SET titulo = ?, plataforma = ?, precio_venta = ?, precio_renta = ?, descuento = ?, stock_venta = ?, stock_renta = ?, clasificacion = ?, anio = ?, foto = ? WHERE id_videojuego = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, plataforma);
            ps.setDouble(3, pVenta);
            ps.setDouble(4, pRenta);
            ps.setInt(5, descuento);
            ps.setInt(6, sVenta);
            ps.setInt(7, sRenta);
            ps.setString(8, clasificacion);
            ps.setInt(9, anio);
            ps.setString(10, foto);
            ps.setInt(11, id);

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }
}
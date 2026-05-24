package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeliculaModel
{
    public List<Object[]> obtenerPeliculas()
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT id_pelicula, titulo, formato, precio_renta, foto FROM pelicula";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("id_pelicula");
                String titulo = rs.getString("titulo");
                String formato = rs.getString("formato");
                String precioRenta = "$" + rs.getString("precio_renta");
                String rutaFoto = rs.getString("foto");

                lista.add(new Object[]{id, titulo, formato, precioRenta, rutaFoto});
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

    public boolean registrarPelicula(String titulo, String formato, double pVenta, double pRenta, int descuento, int sVenta, int sRenta, String clasificacion, int anio, String foto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "INSERT INTO pelicula (titulo, formato, precio_venta, precio_renta, descuento, stock_venta, stock_renta, clasificacion, anio, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, formato);
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

    public boolean eliminarPelicula(int idPelicula)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "DELETE FROM pelicula WHERE id_pelicula = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPelicula);
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

    public Object[] obtenerPeliculaPorId(int idPelicula)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT * FROM pelicula WHERE id_pelicula = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPelicula);
            rs = ps.executeQuery();

            if (rs.next())
            {
                return new Object[]
                {
                    rs.getInt("id_pelicula"),
                    rs.getString("titulo"),
                    rs.getString("formato"),
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

    public boolean actualizarPelicula(int id, String titulo, String formato, double pVenta, double pRenta, int descuento, int sVenta, int sRenta, String clasificacion, int anio, String foto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "UPDATE pelicula SET titulo = ?, formato = ?, precio_venta = ?, precio_renta = ?, descuento = ?, stock_venta = ?, stock_renta = ?, clasificacion = ?, anio = ?, foto = ? WHERE id_pelicula = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, formato);
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
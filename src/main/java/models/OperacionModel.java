package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OperacionModel
{
    public List<Object[]> obtenerOperaciones()
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT r.id_renta, c.id_cliente, c.nombres, c.apellidos, c.foto AS c_foto, r.tipo_operacion, r.tipo_producto, r.id_producto, " +
                         "r.fecha_operacion, r.fecha_devolucion, r.monto_total, r.estado, " +
                         "v.titulo AS v_titulo, v.foto AS v_foto, v.plataforma AS v_plat, " +
                         "p.titulo AS p_titulo, p.foto AS p_foto, p.formato AS p_plat " +
                         "FROM renta r " +
                         "INNER JOIN cliente c ON r.id_cliente = c.id_cliente " +
                         "LEFT JOIN videojuego v ON r.tipo_producto = 'Videojuego' AND r.id_producto = v.id_videojuego " +
                         "LEFT JOIN pelicula p ON (r.tipo_producto = 'Película' OR r.tipo_producto = 'Pelicula') AND r.id_producto = p.id_pelicula";
            
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int idRenta = rs.getInt("id_renta");
                int idCliente = rs.getInt("id_cliente");
                String cliente = rs.getString("nombres") + " " + rs.getString("apellidos");
                String tipoOp = rs.getString("tipo_operacion");
                String tipoProd = rs.getString("tipo_producto");
                int idProd = rs.getInt("id_producto");
                String fechaOp = rs.getString("fecha_operacion");
                String fechaDev = rs.getString("fecha_devolucion");
                double monto = rs.getDouble("monto_total");
                String estado = rs.getString("estado");

                String titulo = tipoProd.equals("Videojuego") ? rs.getString("v_titulo") : rs.getString("p_titulo");
                String foto = tipoProd.equals("Videojuego") ? rs.getString("v_foto") : rs.getString("p_foto");
                String plataforma = tipoProd.equals("Videojuego") ? rs.getString("v_plat") : rs.getString("p_plat");
                
                String fotoCliente = rs.getString("c_foto");

                if (titulo == null) titulo = "Producto Eliminado";
                if (plataforma == null) plataforma = "-";

                lista.add(new Object[]{idRenta, cliente, tipoOp, tipoProd, titulo, foto, plataforma, idProd, fechaOp, fechaDev, monto, idCliente, estado, fotoCliente});
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

    public List<Object[]> obtenerTodosLosProductos()
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT id_videojuego AS id, titulo, 'Videojuego' AS tipo, plataforma, precio_venta, precio_renta, foto, stock_venta, stock_renta FROM videojuego " +
                         "UNION ALL " +
                         "SELECT id_pelicula AS id, titulo, 'Película' AS tipo, formato AS plataforma, precio_venta, precio_renta, foto, stock_venta, stock_renta FROM pelicula";
            
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                String plataforma = rs.getString("plataforma");
                double pVenta = rs.getDouble("precio_venta");
                double pRenta = rs.getDouble("precio_renta");
                String foto = rs.getString("foto");
                int sVenta = rs.getInt("stock_venta");
                int sRenta = rs.getInt("stock_renta");

                lista.add(new Object[]{id, titulo, tipo, plataforma, pVenta, pRenta, foto, sVenta, sRenta});
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

    public boolean registrarOperacion(int idCliente, String tipoOp, String tipoProd, int idProd, String fechaOp, String fechaDev, double monto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;

        try
        {
            conexion = conexionBD.conectar();
            conexion.setAutoCommit(false); 

            String sqlInsert = "INSERT INTO renta (id_cliente, tipo_operacion, tipo_producto, id_producto, fecha_operacion, fecha_devolucion, monto_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psInsert = conexion.prepareStatement(sqlInsert);
            psInsert.setInt(1, idCliente);
            psInsert.setString(2, tipoOp);
            psInsert.setString(3, tipoProd);
            psInsert.setInt(4, idProd);
            psInsert.setString(5, fechaOp);
            
            if (fechaDev == null || fechaDev.trim().isEmpty() || fechaDev.equals("No aplica"))
            {
                psInsert.setNull(6, java.sql.Types.DATE);
            }
            else
            {
                psInsert.setString(6, fechaDev);
            }
            
            psInsert.setDouble(7, monto);
            psInsert.executeUpdate();

            String tabla = tipoProd.equals("Videojuego") ? "videojuego" : "pelicula";
            String campoStock = tipoOp.equals("Venta") ? "stock_venta" : "stock_renta";
            String campoId = tipoProd.equals("Videojuego") ? "id_videojuego" : "id_pelicula";

            String sqlUpdate = "UPDATE " + tabla + " SET " + campoStock + " = " + campoStock + " - 1 WHERE " + campoId + " = ? AND " + campoStock + " > 0";
            psUpdate = conexion.prepareStatement(sqlUpdate);
            psUpdate.setInt(1, idProd);
            int filasAfectadas = psUpdate.executeUpdate();

            if (filasAfectadas == 0)
            {
                conexion.rollback(); 
                return false;
            }

            conexion.commit(); 
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try { if (conexion != null) conexion.rollback(); } catch (Exception ex) {}
            return false;
        }
        finally
        {
            try { if (psInsert != null) psInsert.close(); if (psUpdate != null) psUpdate.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }

    public boolean actualizarOperacion(int idRenta, int idCliente, String tipoOp, String tipoProd, int idProd, String fechaOp, String fechaDev, double monto)
    {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "UPDATE renta SET id_cliente = ?, tipo_operacion = ?, tipo_producto = ?, id_producto = ?, fecha_operacion = ?, fecha_devolucion = ?, monto_total = ? WHERE id_renta = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.setString(2, tipoOp);
            ps.setString(3, tipoProd);
            ps.setInt(4, idProd);
            ps.setString(5, fechaOp);
            ps.setString(6, (fechaDev == null || fechaDev.equals("No aplica") || fechaDev.trim().isEmpty()) ? null : fechaDev);
            ps.setDouble(7, monto);
            ps.setInt(8, idRenta);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
    }
    
    public List<Object[]> obtenerHistorialRentas(int idCliente)
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT " +
                         "COALESCE(v.titulo, p.titulo, 'Producto Eliminado') AS titulo, " +
                         "COALESCE(v.plataforma, p.formato, '-') AS plataforma, " +
                         "r.monto_total, r.fecha_operacion, '0%' AS descuento " +
                         "FROM renta r " +
                         "LEFT JOIN videojuego v ON r.tipo_producto = 'Videojuego' AND r.id_producto = v.id_videojuego " +
                         "LEFT JOIN pelicula p ON (r.tipo_producto = 'Película' OR r.tipo_producto = 'Pelicula') AND r.id_producto = p.id_pelicula " +
                         "WHERE r.id_cliente = ? AND r.tipo_operacion = 'Renta' " +
                         "ORDER BY r.fecha_operacion DESC";
            
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next())
            {
                String titulo = rs.getString("titulo");
                String plataforma = rs.getString("plataforma");
                String monto = "$" + rs.getDouble("monto_total");
                String fechaOp = rs.getString("fecha_operacion");
                String descuento = rs.getString("descuento");

                lista.add(new Object[]{titulo, plataforma, monto, fechaOp, descuento});
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

    public List<Object[]> obtenerHistorialVentas(int idCliente)
    {
        List<Object[]> lista = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            String sql = "SELECT " +
                         "COALESCE(v.titulo, p.titulo, 'Producto Eliminado') AS titulo, " +
                         "COALESCE(v.plataforma, p.formato, '-') AS plataforma, " +
                         "r.monto_total, r.fecha_operacion, '0%' AS descuento " +
                         "FROM renta r " +
                         "LEFT JOIN videojuego v ON r.tipo_producto = 'Videojuego' AND r.id_producto = v.id_videojuego " +
                         "LEFT JOIN pelicula p ON (r.tipo_producto = 'Película' OR r.tipo_producto = 'Pelicula') AND r.id_producto = p.id_pelicula " +
                         "WHERE r.id_cliente = ? AND r.tipo_operacion = 'Venta' " +
                         "ORDER BY r.fecha_operacion DESC";
            
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next())
            {
                String titulo = rs.getString("titulo");
                String plataforma = rs.getString("plataforma");
                String monto = "$" + rs.getDouble("monto_total");
                String fechaOp = rs.getString("fecha_operacion");
                String descuento = rs.getString("descuento");

                lista.add(new Object[]{titulo, plataforma, monto, fechaOp, descuento});
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

    public Object[] obtenerTop(String tipo, String tipoProducto)
    {
        Object[] resultado = {0, "N/A"}; 
        String sql = "SELECT r.id_producto, COALESCE(v.titulo, p.titulo) as titulo " +
                     "FROM renta r " +
                     "LEFT JOIN videojuego v ON r.tipo_producto = 'Videojuego' AND r.id_producto = v.id_videojuego " +
                     "LEFT JOIN pelicula p ON (r.tipo_producto = 'Película' OR r.tipo_producto = 'Pelicula') AND r.id_producto = p.id_pelicula " +
                     "WHERE r.tipo_operacion = ? AND r.tipo_producto = ? " +
                     "GROUP BY r.id_producto ORDER BY COUNT(*) DESC LIMIT 1";
        
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conexion = conexionBD.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, tipo);
            ps.setString(2, tipoProducto);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                resultado[0] = rs.getInt("id_producto");
                resultado[1] = rs.getString("titulo");
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
        return resultado;
    }
    
    public int obtenerTotalOperaciones()
    {
        String sql = "SELECT COUNT(*) FROM renta";
        try (Connection con = new ConexionBD().conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            if (rs.next()) return rs.getInt(1);
        }
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }
        return 0;
    }
    
    public boolean eliminarOperacion(int idRenta) {
        ConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = conexionBD.conectar();
            String sql = "DELETE FROM renta WHERE id_renta = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRenta);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps != null) ps.close(); if (conexion != null) conexion.close(); } catch (Exception ex) {}
        }
    }
}
package controller;

import view.*;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InfoJuegoController
{
    private InfoJuego v;
    private int id;
    private VideojuegoModel modelo;

    public InfoJuegoController(InfoJuego v, int id)
    {
        this.v = v;
        this.id = id;
        this.modelo = new VideojuegoModel();
        
        initEvents();
    }

    private void initEvents()
    {
        configurarMenuLateral(v.btnInicio, v.btnOperacion, v.btnClientes, v.btnVideojuegos, v.btnPeliculas, v);

        v.btnAtras.addActionListener(e ->
        {
            v.dispose();
            videojuegos vVid = new videojuegos();
            new VideojuegosController(vVid);
            vVid.setVisible(true);
        });

        v.btnDescargar.addActionListener(e ->
        {
            utils.PDFGenerator.generarFichaJuego(
                String.valueOf(id), 
                v.txtNombreProd.getText(), 
                v.txtPlataforma.getText(), 
                v.txtPrecioVenta.getText()
            );
        });

        v.btnEditar.addActionListener(e ->
        {
            if (!v.enModoEdicion)
            {
                v.txtNombreProd.setEditable(true);
                v.txtPlataforma.setEditable(true);
                v.txtPrecioVenta.setEditable(true);
                v.txtPrecioRenta.setEditable(true);
                v.txtDescuento.setEditable(true);
                v.txtStock.setEditable(true);
                v.txtStockRenta.setEditable(true);
                v.txtClasificacion.setEditable(true);
                v.txtAnio.setEditable(true);
                
                v.btnEditar.setText("Guardar cambios");
                v.btnEditar.setBackground(new Color(50, 180, 50));
                v.setModoEdicionActivo(true);
            }
            else
            {
                try
                {
                    double pVenta = Double.parseDouble(v.txtPrecioVenta.getText().replace("$", "").trim());
                    double pRenta = Double.parseDouble(v.txtPrecioRenta.getText().replace("$", "").trim());
                    int desc = Integer.parseInt(v.txtDescuento.getText().replace("%", "").trim());
                    int sVenta = Integer.parseInt(v.txtStock.getText().trim());
                    int sRenta = Integer.parseInt(v.txtStockRenta.getText().trim());
                    int anio = Integer.parseInt(v.txtAnio.getText().trim());
                    
                    boolean exito = modelo.actualizarVideojuego(
                        id, 
                        v.txtNombreProd.getText().trim(), 
                        v.txtPlataforma.getText().trim(), 
                        pVenta, 
                        pRenta, 
                        desc, 
                        sVenta, 
                        sRenta, 
                        v.txtClasificacion.getText().trim(), 
                        anio, 
                        v.rutaFotoNueva.isEmpty() ? "" : v.rutaFotoNueva
                    );

                    if (exito)
                    {
                        v.mostrarConfirmacion("¡Cambios guardados con éxito!", e2 ->
                        {
                            v.btnEditar.setText("Editar info de videojuego");
                            v.btnEditar.setBackground(new Color(45, 59, 72));
                            v.setModoEdicionActivo(false);
                            
                            v.txtNombreProd.setEditable(false);
                            v.txtPlataforma.setEditable(false);
                            v.txtPrecioVenta.setEditable(false);
                            v.txtPrecioRenta.setEditable(false);
                            v.txtDescuento.setEditable(false);
                            v.txtStock.setEditable(false);
                            v.txtStockRenta.setEditable(false);
                            v.txtClasificacion.setEditable(false);
                            v.txtAnio.setEditable(false);
                        });
                    }
                }
                catch (Exception ex)
                {
                    v.mostrarConfirmacion("Error: Verifica que los campos numéricos sean correctos.", e2 -> {});
                }
            }
        });
    }

    private void configurarMenuLateral(JComponent inicio, JComponent op, JComponent cli, JComponent vid, JComponent pel, JFrame ventanaActual)
    {
        if (inicio != null) inicio.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); principal v = new principal(); new PrincipalController(v); v.setVisible(true); } });
        if (op != null) op.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); operaciones v = new operaciones(); new OperacionesController(v); v.setVisible(true); } });
        if (cli != null) cli.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); clientes v = new clientes(); new ClienteController(v); v.setVisible(true); } });
        if (vid != null) vid.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { if (!(ventanaActual instanceof videojuegos)) { ventanaActual.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true); } } });
        if (pel != null) pel.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); peliculas v = new peliculas(); new PeliculasController(v); v.setVisible(true); } });
    }
}
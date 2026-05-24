package controller;

import view.*;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InfoPeliculaController
{
    private InfoPelicula v;
    private int id;
    private String fotoActual;
    private PeliculaModel modelo;

    public InfoPeliculaController(InfoPelicula v, int id, String fotoActual)
    {
        this.v = v;
        this.id = id;
        this.fotoActual = fotoActual;
        this.modelo = new PeliculaModel();
        
        initEvents();
    }

    private void initEvents()
    {
        configurarMenuLateral(v.btnInicio, v.btnOperacion, v.btnClientes, v.btnVideojuegos, v.btnPeliculas, v);

        v.btnAtras.addActionListener(e ->
        {
            v.dispose();
            peliculas vPel = new peliculas();
            new PeliculasController(vPel);
            vPel.setVisible(true);
        });

        v.btnDescargar.addActionListener(e ->
        {
            utils.PDFGenerator.generarFichaPelicula(
                String.valueOf(id), 
                v.txtNomProd.getText(), 
                v.txtFormato.getText(), 
                v.txtMonto.getText()
            );
        });

        v.btnEditar.addActionListener(e ->
        {
            if (!v.enModoEdicion)
            {
                v.txtNomProd.setEditable(true);
                v.txtFormato.setEditable(true);
                v.txtMonto.setEditable(true);
                v.txtFechaOp.setEditable(true);
                v.txtDescuento.setEditable(true);
                v.txtStockVenta.setEditable(true);
                v.txtStockRenta.setEditable(true);
                v.txtClasif.setEditable(true);
                v.txtAnio.setEditable(true);
                
                v.btnEditar.setText("Guardar cambios");
                v.btnEditar.setBackground(new Color(50, 180, 50));
                v.setModoEdicionActivo(true);
            }
            else
            {
                try
                {
                    boolean exito = modelo.actualizarPelicula(
                        id, 
                        v.txtNomProd.getText(), 
                        v.txtFormato.getText(), 
                        Double.parseDouble(v.txtMonto.getText().replace("$", "")),
                        Double.parseDouble(v.txtFechaOp.getText().replace("$", "")), 
                        Integer.parseInt(v.txtDescuento.getText().replace("%", "")), 
                        Integer.parseInt(v.txtStockVenta.getText()), 
                        Integer.parseInt(v.txtStockRenta.getText()), 
                        v.txtClasif.getText(), 
                        Integer.parseInt(v.txtAnio.getText()), 
                        v.rutaFotoNueva.isEmpty() ? fotoActual : v.rutaFotoNueva
                    );

                    if (exito)
                    {
                        v.mostrarAlerta("Cambios guardados correctamente", false);
                        v.btnEditar.setText("Editar info de la película");
                        v.btnEditar.setBackground(new Color(45, 59, 72));
                        v.setModoEdicionActivo(false);
                        
                        v.txtNomProd.setEditable(false);
                        v.txtFormato.setEditable(false);
                        v.txtMonto.setEditable(false);
                        v.txtFechaOp.setEditable(false);
                        v.txtDescuento.setEditable(false);
                        v.txtStockVenta.setEditable(false);
                        v.txtStockRenta.setEditable(false);
                        v.txtClasif.setEditable(false);
                        v.txtAnio.setEditable(false);
                    }
                }
                catch (Exception ex)
                {
                    v.mostrarAlerta("Error al guardar: Revisa los campos numéricos", true);
                }
            }
        });
    }

    private void configurarMenuLateral(JComponent inicio, JComponent op, JComponent cli, JComponent vid, JComponent pel, JFrame vActual)
    {
        if (inicio != null) inicio.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vActual.dispose(); principal v = new principal(); new PrincipalController(v); v.setVisible(true); } });
        if (op != null) op.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vActual.dispose(); operaciones v = new operaciones(); new OperacionesController(v); v.setVisible(true); } });
        if (cli != null) cli.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vActual.dispose(); clientes v = new clientes(); new ClienteController(v); v.setVisible(true); } });
        if (vid != null) vid.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vActual.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true); } });
        if (pel != null) pel.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { if (!(vActual instanceof peliculas)) { vActual.dispose(); peliculas v = new peliculas(); new PeliculasController(v); v.setVisible(true); } } });
    }
}
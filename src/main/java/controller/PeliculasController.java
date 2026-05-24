package controller;

import models.PeliculaModel;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PeliculasController
{
    private peliculas vista;
    private AñadirPelicula vistaAdd;
    private PeliculaModel modelo;

    public PeliculasController(peliculas vista)
    {
        this.vista = vista;
        this.modelo = new PeliculaModel();
        initEvents();
        cargarTabla();
    }

    public PeliculasController(AñadirPelicula vistaAdd)
    {
        this.vistaAdd = vistaAdd;
        this.modelo = new PeliculaModel();
        initEventsAdd();
    }

    public void cargarTabla()
    {
        if (vista != null)
        {
            vista.modelo.setRowCount(0);
            List<Object[]> lista = modelo.obtenerPeliculas();
            Icon iconoPeli = getImg("/img/fluent_movies-and-tv-16-filled.png", 40, 40);
            Icon iconoVector = getImg("/img/Vector.png", 20, 20);

            for (Object[] peli : lista)
            {
                int id = (int) peli[0];
                String titulo = (String) peli[1];
                String formato = (String) peli[2];
                String rutaFoto = (String) peli[4];
                Icon foto = (rutaFoto != null && !rutaFoto.isEmpty()) ? getImg(rutaFoto, 50, 60) : null;
                vista.modelo.addRow(new Object[]{ false, foto, titulo, id, new Object[]{iconoPeli, "Película"}, formato, new Object[]{iconoVector, "Ver info"} });
            }
        }
    }

    private ImageIcon getImg(String ruta, int w, int h)
    {
        try
        {
            java.net.URL url = getClass().getResource(ruta);
            if (url != null) return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            java.io.File archivo = new java.io.File(ruta);
            if (archivo.exists()) return new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        }
        catch (Exception e) {}
        return null;
    }

    private void initEvents()
    {
        vista.btnAtras.addActionListener(e -> 
        { 
            vista.dispose(); 
            principal vPrin = new principal();
            new PrincipalController(vPrin);
            vPrin.setVisible(true);
        });
        
        vista.btnAgregar.addActionListener(e -> 
        { 
            vista.dispose(); 
            AñadirPelicula vAdd = new AñadirPelicula();
            new PeliculasController(vAdd);
            vAdd.setVisible(true);
        });

        configurarMenuLateral(vista.btnInicio, vista.btnOperacion, vista.btnClientes, vista.btnVideojuegos, vista.btnPeliculas, vista);
        
        vista.tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int filaVisual = vista.tabla.rowAtPoint(e.getPoint());
                if (filaVisual != -1 && vista.tabla.columnAtPoint(e.getPoint()) == 6)
                {
                    int id = (int) vista.tabla.getValueAt(filaVisual, 3);
                    Object[] datos = modelo.obtenerPeliculaPorId(id);
                    if (datos != null)
                    {
                        InfoPelicula vInfo = new InfoPelicula();
                        vInfo.setDatosPelicula((String)datos[1], String.valueOf(id), "Película", (String)datos[2], "$"+datos[3], datos[5]+"%", String.valueOf(datos[6]), String.valueOf(datos[7]), "$"+datos[4], (String)datos[8], String.valueOf(datos[9]), "General", getImg((String)datos[10], 260, 280));
                        new InfoPeliculaController(vInfo, id, (String)datos[10]);
                        vista.dispose();
                        vInfo.setVisible(true);
                    }
                }
            }
        });
    }

    private void initEventsAdd()
    {
        vistaAdd.btnAtras.addActionListener(e -> 
        { 
            vistaAdd.dispose(); 
            peliculas vPel = new peliculas();
            new PeliculasController(vPel);
            vPel.setVisible(true);
        });

        configurarMenuLateral(vistaAdd.btnInicio, vistaAdd.btnOperacion, vistaAdd.btnClientes, vistaAdd.btnVideojuegos, vistaAdd.btnPeliculas, vistaAdd);

        vistaAdd.btnAgregar.addActionListener(e -> 
        {
            try
            {
                if (modelo.registrarPelicula(vistaAdd.txtNombre.getText(), vistaAdd.cbPlataforma.getSelectedItem().toString(), 
                   Double.parseDouble(vistaAdd.txtVenta.getText().replace("$", "")), Double.parseDouble(vistaAdd.txtRenta.getText().replace("$", "")), 
                   Integer.parseInt(vistaAdd.txtDescuento.getText().replace("%", "")), Integer.parseInt(vistaAdd.cbStockVenta.getSelectedItem().toString()), 
                   Integer.parseInt(vistaAdd.cbStockRenta.getSelectedItem().toString()), vistaAdd.cbClasif.getSelectedItem().toString(), 
                   Integer.parseInt(vistaAdd.cbAnio.getSelectedItem().toString()), "")) 
                {
                    vistaAdd.mostrarAlerta("Registrado con éxito", false);
                    vistaAdd.dispose();
                    peliculas vPel = new peliculas();
                    new PeliculasController(vPel);
                    vPel.setVisible(true);
                }
            }
            catch (Exception ex)
            {
                vistaAdd.mostrarAlerta("Error en datos", true);
            }
        });
    }

    private void configurarMenuLateral(JComponent inicio, JComponent operacion, JComponent clientes, JComponent videojuegosLbl, JComponent peliculasLbl, JFrame ventanaActual)
    {
        if (inicio != null)
        {
            inicio.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    principal vPrin = new principal();
                    new PrincipalController(vPrin); 
                    vPrin.setVisible(true);
                }
            });
        }
        if (operacion != null)
        {
            operacion.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    operaciones vOp = new operaciones();
                    new OperacionesController(vOp); 
                    vOp.setVisible(true);
                }
            });
        }
        if (clientes != null)
        {
            clientes.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    clientes vCli = new clientes();
                    new ClienteController(vCli); 
                    vCli.setVisible(true);
                }
            });
        }
        if (videojuegosLbl != null)
        {
            videojuegosLbl.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    videojuegos vVid = new videojuegos();
                    new VideojuegosController(vVid); 
                    vVid.setVisible(true);
                }
            });
        }
        if (peliculasLbl != null)
        {
            peliculasLbl.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    if (!(ventanaActual instanceof peliculas))
                    {
                        ventanaActual.dispose();
                        peliculas vPel = new peliculas();
                        new PeliculasController(vPel); 
                        vPel.setVisible(true);
                    }
                }
            });
        }
    }

    private class InfoPeliculaInternalController
    {
        private InfoPelicula v;
        private int id;
        private String foto;

        public InfoPeliculaInternalController(InfoPelicula v, int id, String foto)
        {
            this.v = v;
            this.id = id;
            this.foto = foto;
            
            configurarMenuLateral(v.btnInicio, v.btnOperacion, v.btnClientes, v.btnVideojuegos, v.btnPeliculas, v);

            v.btnAtras.addActionListener(e ->
            {
                v.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel);
                vPel.setVisible(true);
            });

            v.btnDescargar.addActionListener(e -> {
                String idFicha = String.valueOf(this.id);
                String titulo = v.txtNomProd.getText();
                String director = v.txtFormato.getText(); 
                String duracion = v.txtMonto.getText();   

                utils.PDFGenerator.generarFichaPelicula(idFicha, titulo, director, duracion);
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
                        if (modelo.actualizarPelicula(id, v.txtNomProd.getText(), v.txtFormato.getText(), Double.parseDouble(v.txtMonto.getText().replace("$", "")), 
                           Double.parseDouble(v.txtFechaOp.getText().replace("$", "")), Integer.parseInt(v.txtDescuento.getText().replace("%", "")), Integer.parseInt(v.txtStockVenta.getText()), 
                           Integer.parseInt(v.txtStockRenta.getText()), v.txtClasif.getText(), Integer.parseInt(v.txtAnio.getText()), v.rutaFotoNueva.isEmpty() ? foto : v.rutaFotoNueva))
                        {
                            v.mostrarAlerta("Guardado correctamente", false);
                            v.dispose();
                            peliculas vPel = new peliculas();
                            new PeliculasController(vPel);
                            vPel.setVisible(true);
                        }
                    }
                    catch (Exception ex)
                    {
                        v.mostrarAlerta("Revisa los campos numéricos", true);
                    }
                }
            });
        }
    }
}
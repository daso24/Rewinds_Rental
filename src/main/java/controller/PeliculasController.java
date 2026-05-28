package controller;

import models.PeliculaModel;
import models.ArbolBinarioBusqueda;
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
    private ArbolBinarioBusqueda arbolPeliculas;

    public PeliculasController(peliculas vista)
    {
        this.vista = vista;
        this.modelo = new PeliculaModel();
        this.arbolPeliculas = new ArbolBinarioBusqueda();
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
            this.arbolPeliculas = new ArbolBinarioBusqueda();
            
            Icon iconoPeli = getImg("/img/fluent_movies-and-tv-16-filled.png", 40, 40);
            Icon iconoVector = getImg("/img/Vector.png", 20, 20);

            for (Object[] peli : lista)
            {
                int id = (int) peli[0];
                String titulo = (String) peli[1];
                String formato = (String) peli[2];
                String rutaFoto = (String) peli[4];
                
                arbolPeliculas.insertar(titulo, peli);
                
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
            if (url != null) 
            {
                return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            }
            java.io.File archivo = new java.io.File(ruta);
            if (archivo.exists()) 
            {
                return new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            }
        }
        catch (Exception e) 
        {
        }
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

        vista.btnBuscar.addActionListener(e -> 
        {
            String texto = vista.buscador.getText().trim();
            if (texto.isEmpty()) 
            {
                cargarTabla();
            } 
            else 
            {
                List<Object[]> pelisEncontradas = arbolPeliculas.buscarParcial(texto);
                vista.modelo.setRowCount(0);
                
                if (!pelisEncontradas.isEmpty()) 
                {
                    Icon iconoPeli = getImg("/img/fluent_movies-and-tv-16-filled.png", 40, 40);
                    Icon iconoVector = getImg("/img/Vector.png", 20, 20);
                    
                    for (Object[] peliEncontrada : pelisEncontradas) 
                    {
                        int id = (int) peliEncontrada[0];
                        String titulo = (String) peliEncontrada[1];
                        String formato = (String) peliEncontrada[2];
                        String rutaFoto = (String) peliEncontrada[4];
                        Icon foto = (rutaFoto != null && !rutaFoto.isEmpty()) ? getImg(rutaFoto, 50, 60) : null;
                        vista.modelo.addRow(new Object[]{ false, foto, titulo, id, new Object[]{iconoPeli, "Película"}, formato, new Object[]{iconoVector, "Ver info"} });
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(vista, "No se encontró ninguna película con ese nombre.");
                }
            }
        });

        vista.btnEliminar.addActionListener(e ->
        {
            boolean haySeleccion = false;

            for (int i = 0; i < vista.tabla.getRowCount(); i++)
            {
                Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                if (isSelected != null && isSelected)
                {
                    haySeleccion = true;
                    break;
                }
            }

            if (!haySeleccion)
            {
                vista.mostrarAlerta("Selecciona al menos una película de la lista.");
                return;
            }

            vista.mostrarConfirmacion("¿Seguro que deseas eliminar las películas seleccionadas?", evt ->
            {
                for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--)
                {
                    Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                    if (isSelected != null && isSelected)
                    {
                        int idPelicula = (int) vista.tabla.getValueAt(i, 3);
                        modelo.eliminarPelicula(idPelicula);
                    }
                }
                cargarTabla();
            });
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
                @Override 
                public void mouseClicked(MouseEvent e) 
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
                @Override 
                public void mouseClicked(MouseEvent e) 
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
                @Override 
                public void mouseClicked(MouseEvent e) 
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
                @Override 
                public void mouseClicked(MouseEvent e) 
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
                @Override 
                public void mouseClicked(MouseEvent e) 
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
}
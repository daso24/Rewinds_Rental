package controller;

import view.*;
import models.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class PrincipalController
{
    private principal vista;
    private OperacionModel modelo;
    private PeliculaModel modeloPeli;
    private VideojuegoModel modeloJuego;
    private ClientModel modeloCliente;
    private int idPeliComprado, idPeliRentado, idJuegoComprado, idJuegoRentado;

    public PrincipalController(principal vista)
    {
        this.vista = vista;
        this.modelo = new OperacionModel();
        this.modeloPeli = new PeliculaModel();
        this.modeloJuego = new VideojuegoModel();
        this.modeloCliente = new ClientModel();

        initEvents();
        actualizarDashboard();
    }

    private void initEvents()
    {
        vista.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                confirmarCierreSesion();
            }
        });

        vista.btnInicio.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            }
        });

        vista.btnOperacion.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                abrirOperaciones();
            }
        });

        vista.btnClientes.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                abrirClientes();
            }
        });

        vista.btnVideojuegos.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                abrirVideojuegos();
            }
        });

        vista.btnPeliculas.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                abrirPeliculas();
            }
        });

        vista.logoutBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                confirmarCierreSesion();
            }
        });

        vista.btnVerClientes.addActionListener(e -> abrirClientes());
        vista.btnVerRentas.addActionListener(e -> abrirOperaciones());
        
        vista.btnVerJuegosRentados.addActionListener(e -> abrirDetalleJuego(idJuegoRentado));
        vista.btnVerJuegosComprados.addActionListener(e -> abrirDetalleJuego(idJuegoComprado));
        vista.btnVerPelisRentadas.addActionListener(e -> abrirDetallePeli(idPeliRentado));
        vista.btnVerPelisCompradas.addActionListener(e -> abrirDetallePeli(idPeliComprado));
    }

    private void actualizarDashboard()
    {
        try {
            if (vista.lblJuegoMasComprado != null) {
                Object[] juegoComp = modelo.obtenerTop("Venta", "Videojuego");
                idJuegoComprado = (int) juegoComp[0];
                vista.lblJuegoMasComprado.setText("<html><center>Más Comprado:<br><b>" + juegoComp[1] + "</b></center></html>");

                Object[] juegoRent = modelo.obtenerTop("Renta", "Videojuego");
                idJuegoRentado = (int) juegoRent[0];
                vista.lblJuegoMasRentado.setText("<html><center>Más Rentado:<br><b>" + juegoRent[1] + "</b></center></html>");

                Object[] peliComp = modelo.obtenerTop("Venta", "Película");
                idPeliComprado = (int) peliComp[0];
                vista.lblPeliMasComprado.setText("<html><center>Más Comprado:<br><b>" + peliComp[1] + "</b></center></html>");

                Object[] peliRent = modelo.obtenerTop("Renta", "Película");
                idPeliRentado = (int) peliRent[0];
                vista.lblPeliMasRentado.setText("<html><center>Más Rentado:<br><b>" + peliRent[1] + "</b></center></html>");
            }

   
            int totalC = modeloCliente.obtenerTotalClientes();
            int totalO = modelo.obtenerTotalOperaciones();
            
            if (vista.lblTotalClientes != null) {
                vista.lblTotalClientes.setText(String.valueOf(totalC));
            }
            if (vista.lblTotalOperaciones != null) {
                vista.lblTotalOperaciones.setText(String.valueOf(totalO));
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void abrirOperaciones()
    {
        vista.dispose();
        operaciones vOp = new operaciones();
        new OperacionesController(vOp);
        vOp.setVisible(true);
    }

    private void abrirClientes()
    {
        vista.dispose();
        clientes vCli = new clientes();
        new ClienteController(vCli);
        vCli.setVisible(true);
    }

    private void abrirVideojuegos()
    {
        vista.dispose();
        videojuegos vVid = new videojuegos();
        new VideojuegosController(vVid);
        vVid.setVisible(true);
    }

    private void abrirPeliculas()
    {
        vista.dispose();
        peliculas vPel = new peliculas();
        new PeliculasController(vPel);
        vPel.setVisible(true);
    }

    private void abrirDetalleJuego(int id)
    {
        if (id == 0) return;
        vista.dispose();
        
        InfoJuego vJuego = new InfoJuego();
        Object[] datos = modeloJuego.obtenerVideojuegoPorId(id);
        
        if (datos != null)
        {
            vJuego.cargarDatos(datos);
            new InfoJuegoController(vJuego, id);
        }
        
        vJuego.setVisible(true);
    }

    private void abrirDetallePeli(int id)
    {
        if (id == 0) return;
        vista.dispose();
        InfoPelicula vPeli = new InfoPelicula();
        
        Object[] datos = modeloPeli.obtenerPeliculaPorId(id);
        if (datos != null)
        {
        	vPeli.cargarDatos(datos);
        	new InfoPeliculaController(vPeli, id, (String)datos[10]);
        }
        
        vPeli.setVisible(true);
    }

    private void volverAlMenu(javax.swing.JFrame vistaActual)
    {
        vistaActual.dispose();
        principal p = new principal();
        new PrincipalController(p);
        p.setVisible(true);
    }

    private void confirmarCierreSesion()
    {
        vista.mostrarConfirmacionSalir(
            eSi ->
            {
                vista.dispose();
                Rewinds_Rental.Main.main(null);
            },
            eNo ->
            {
            }
        );
    }
}
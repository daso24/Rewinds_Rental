package controller;

import view.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PrincipalController {
    private principal vista;

    public PrincipalController(principal vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarCierreSesion();
            }
        });

        vista.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { }
        });

        vista.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { abrirOperaciones(); }
        });

        vista.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { abrirClientes(); }
        });

        vista.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { abrirVideojuegos(); }
        });

        vista.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { abrirPeliculas(); }
        });

        vista.logoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { confirmarCierreSesion(); }
        });

        vista.btnVerClientes.addActionListener(e -> abrirClientes());
        vista.btnVerRentas.addActionListener(e -> abrirOperaciones());
        vista.btnVerJuegosRentados.addActionListener(e -> abrirDetalleJuego());
        vista.btnVerJuegosComprados.addActionListener(e -> abrirDetalleJuego());
        vista.btnVerPelisRentadas.addActionListener(e -> abrirDetallePeli());
        vista.btnVerPelisCompradas.addActionListener(e -> abrirDetallePeli());
    }

    private void abrirOperaciones() {
        vista.dispose();
        operaciones vOp = new operaciones();
        new OperacionesController(vOp);
        vOp.setVisible(true);
    }

    private void abrirClientes() {
        vista.dispose();
        clientes vCli = new clientes();
        new ClienteController(vCli);
        vCli.setVisible(true);
    }

    private void abrirVideojuegos() {
        vista.dispose();
        videojuegos vVid = new videojuegos();
        new VideojuegosController(vVid);
        vVid.setVisible(true);
    }

    private void abrirPeliculas() {
        vista.dispose();
        peliculas vPel = new peliculas();
        new PeliculasController(vPel);
        vPel.setVisible(true);
    }

    private void abrirDetalleJuego() {
        vista.dispose();
        juegomas_rentado vJuego = new juegomas_rentado();
        configurarEventosJuegoRentado(vJuego);
        vJuego.setVisible(true);
    }

    private void abrirDetallePeli() {
        vista.dispose();
        reze vReze = new reze();
        configurarEventosReze(vReze);
        vReze.setVisible(true);
    }

    private void volverAlMenu(javax.swing.JFrame vistaActual) {
        vistaActual.dispose();
        principal p = new principal();
        new PrincipalController(p);
        p.setVisible(true);
    }

    public void configurarEventosJuegoRentado(juegomas_rentado v) {
        v.btnAtras.addActionListener(e -> volverAlMenu(v));
        
        v.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { volverAlMenu(v); }
        });
        v.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirOperaciones(); }
        });
        v.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirClientes(); }
        });
        v.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirVideojuegos(); }
        });
        v.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirPeliculas(); }
        });

        v.btnEditar.addActionListener(e -> {
            v.mostrarConfirmacion("¿Desea habilitar la edición<br>de este videojuego?", eSi -> {
                System.out.println("Modo edición activado.");
            });
        });

        v.btnDescargar.addActionListener(e -> {
            v.mostrarConfirmacion("¿Desea generar el reporte PDF?", eSi -> {
                System.out.println("Generando reporte...");
            });
        });
    }

    public void configurarEventosReze(reze v) {
        v.btnAtras.addActionListener(e -> volverAlMenu(v));

        v.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { volverAlMenu(v); }
        });
        v.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirOperaciones(); }
        });
        v.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirClientes(); }
        });
        v.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirVideojuegos(); }
        });
        v.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { v.dispose(); abrirPeliculas(); }
        });

        v.btnEditar.addActionListener(e -> {
            v.mostrarConfirmacion("¿Desea habilitar la edición?", eSi -> {
                System.out.println("Editando...");
            });
        });

        v.btnDescargar.addActionListener(e -> {
            v.mostrarConfirmacion("¿Desea generar el reporte PDF?", eSi -> {
                System.out.println("Reporte generado.");
            });
        });
    }

    private void confirmarCierreSesion() {
        vista.mostrarConfirmacionSalir(
            eSi -> {
                vista.dispose();
                Rewinds_Rental.Main.main(null);
            },
            eNo -> { }
        );
    }
}
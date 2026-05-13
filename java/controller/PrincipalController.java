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
            public void mouseClicked(MouseEvent e) {
                confirmarCierreSesion();
            }
        });

        vista.btnVerClientes.addActionListener(e -> abrirClientes());
        
        vista.btnVerRentas.addActionListener(e -> abrirOperaciones());

        vista.btnVerJuegosRentados.addActionListener(e -> {
            vista.dispose();
            juegomas_rentado vJuego = new juegomas_rentado();
            configurarEventosJuegoRentado(vJuego);
            vJuego.setVisible(true);
        });

        vista.btnVerPelisRentadas.addActionListener(e -> {
            vista.dispose();
            reze vReze = new reze();
            configurarEventosReze(vReze);
            vReze.setVisible(true);
        });
        
        vista.btnVerJuegosComprados.addActionListener(e -> {
            vista.dispose();
            juegomas_rentado vJuego = new juegomas_rentado();
            configurarEventosJuegoRentado(vJuego);
            vJuego.setVisible(true);
        });

        vista.btnVerPelisCompradas.addActionListener(e -> {
            vista.dispose();
            reze vReze = new reze();
            configurarEventosReze(vReze);
            vReze.setVisible(true);
        });
    }

    public void configurarEventosJuegoRentado(juegomas_rentado vistaJuego) {
        
        vistaJuego.btnAtras.addActionListener(e -> {
            vistaJuego.dispose();
            principal p = new principal();
            new PrincipalController(p);
            p.setVisible(true);
        });

        vistaJuego.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaJuego.dispose();
                principal p = new principal();
                new PrincipalController(p);
                p.setVisible(true);
            }
        });

        vistaJuego.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaJuego.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp);
                vOp.setVisible(true);
            }
        });

        vistaJuego.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaJuego.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli);
                vCli.setVisible(true);
            }
        });

        vistaJuego.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaJuego.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
            }
        });

        vistaJuego.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaJuego.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel);
                vPel.setVisible(true);
            }
        });

        vistaJuego.btnEditar.addActionListener(e -> {
            vistaJuego.mostrarConfirmacion("¿Desea habilitar la edición<br>de este videojuego?", eSi -> {
                System.out.println("Modo edición activado en Videojuegos.");
            });
        });

        vistaJuego.btnDescargar.addActionListener(e -> {
            vistaJuego.mostrarConfirmacion("¿Desea generar el reporte PDF<br>de este videojuego?", eSi -> {
                System.out.println("Generando PDF de videojuego...");
            });
        });
    }

    public void configurarEventosReze(reze vistaReze) {
        
        vistaReze.btnAtras.addActionListener(e -> {
            vistaReze.dispose();
            principal p = new principal();
            new PrincipalController(p);
            p.setVisible(true);
        });

        vistaReze.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaReze.dispose();
                principal p = new principal();
                new PrincipalController(p);
                p.setVisible(true);
            }
        });

        vistaReze.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaReze.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp);
                vOp.setVisible(true);
            }
        });

        vistaReze.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaReze.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli);
                vCli.setVisible(true);
            }
        });

        vistaReze.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaReze.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
            }
        });

        vistaReze.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaReze.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel);
                vPel.setVisible(true);
            }
        });

        vistaReze.btnEditar.addActionListener(e -> {
            vistaReze.mostrarConfirmacion("¿Desea habilitar la edición<br>de esta película?", eSi -> {
                System.out.println("Modo edición activado en Reze.");
            });
        });

        vistaReze.btnDescargar.addActionListener(e -> {
            vistaReze.mostrarConfirmacion("¿Desea generar el reporte PDF<br>de este producto?", eSi -> {
                System.out.println("Generando PDF...");
            });
        });
    }

    private void confirmarCierreSesion() {
        vista.mostrarConfirmacionSalir(
            eSi -> {
                vista.dispose();
                Rewinds_Rental.Main.main(null); 
            }, 
            eNo -> {
                System.out.println("Permaneciendo en la aplicación.");
            }
        );
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
}
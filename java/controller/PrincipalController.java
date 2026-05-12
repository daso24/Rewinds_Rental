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
            new juegomas_rentado().setVisible(true); 
        });

        vista.btnVerPelisRentadas.addActionListener(e -> {
            vista.dispose();
            new peliculamas_rentada().setVisible(true);
        });
        
   
        vista.btnVerJuegosComprados.addActionListener(e -> {
            vista.dispose();
            new juegomas_rentado().setVisible(true); 
        });

        vista.btnVerPelisCompradas.addActionListener(e -> {
            vista.dispose();
            new peliculamas_rentada().setVisible(true);
        });
    }

    
    private void confirmarCierreSesion() {
        vista.mostrarConfirmacionSalir(
            eSi -> {
                vista.dispose();
                // Volver al inicio de la aplicación
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
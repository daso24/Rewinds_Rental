package controller;

import view.*;
import javax.swing.*;
import java.awt.event.*;

public class OperacionesController {
    private operaciones vista;

    public OperacionesController(operaciones vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        // Botón Inicio
        vista.btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                vista.dispose();
                principal vPrin = new principal();
                new PrincipalController(vPrin); 
                vPrin.setVisible(true);
            }
        });

        // Botón Operación
        vista.btnOperacion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp); 
                vOp.setVisible(true);
            }
        });

        // Botón Clientes
        vista.btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli); 
                vCli.setVisible(true);
            }
        });

        // Botón Videojuegos
        vista.btnVideojuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid); 
                vVid.setVisible(true);
            }
        });

        // Botón Películas
        vista.btnPeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel); 
                vPel.setVisible(true);
            }
        });

     
        vista.btnAgregar.addActionListener(e -> {
            vista.dispose();
            AñadirOperacion vAdd = new AñadirOperacion();
            new AñadirOperacionController(vAdd);
            vAdd.setVisible(true);
        });

        vista.btnBuscar.addActionListener(e -> {
            String texto = vista.buscador.getText().trim();
            if (texto.isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                try {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                } catch (java.util.regex.PatternSyntaxException ex) {
                    System.err.println("Error en la búsqueda: " + ex.getMessage());
                }
            }
        });

        vista.btnEliminar.addActionListener(e -> {
            vista.mostrarConfirmacionEliminar("¿Seguro que quieres eliminar<br>esta operación?", eSi -> {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    vista.modeloTabla.removeRow(vista.tabla.convertRowIndexToModel(fila));
                } else {
                   
                    System.out.println("Selecciona una fila primero");
                }
            });
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = vista.tabla.getSelectedRow();
                    if (fila != -1) {
                        abrirVentanaEditar(vista.tabla.convertRowIndexToModel(fila));
                    }
                }
            }
        });
    }

    private void abrirVentanaEditar(int fila) {
     
    }

 
    private class AñadirOperacionController {
        private AñadirOperacion vAdd;

        public AñadirOperacionController(AñadirOperacion vAdd) {
            this.vAdd = vAdd;
            initAddEvents();
        }

        private void initAddEvents() {
            vAdd.btnAtras.addActionListener(e -> regresar());
            vAdd.btnGuardar.addActionListener(e -> validarYGuardar());
            vAdd.btnDescargar.addActionListener(e -> vAdd.mostrarAlerta("Generando ficha PDF...", false));

      
            vAdd.lblInicio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });

            vAdd.lblClientes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    clientes c = new clientes();
                    new ClienteController(c);
                    c.setVisible(true);
                }
            });

            vAdd.lblVideojuegos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    videojuegos v = new videojuegos();
                    new VideojuegosController(v);
                    v.setVisible(true);
                }
            });

            vAdd.lblPeliculas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    peliculas p = new peliculas();
                    new PeliculasController(p);
                    p.setVisible(true);
                }
            });
            
            vAdd.lblOperacion.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    regresar(); 
                }
            });
        }

        private void validarYGuardar() {
            
            if (vAdd.txtNombreCli.getText().trim().isEmpty() || vAdd.txtIdOp.getText().trim().isEmpty()) {
                vAdd.mostrarAlerta("Error: Existen campos obligatorios vacíos.", true);
            } else {
                vAdd.mostrarAlerta("Operación guardada con éxito.", false);
                regresar();
            }
        }

        private void regresar() {
            vAdd.dispose();
            operaciones op = new operaciones();
            new OperacionesController(op);
            op.setVisible(true);
        }
    }
}
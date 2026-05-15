package controller;

import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperacionesController {
    private operaciones vista;

    public OperacionesController(operaciones vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        
        vista.btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                principal vPrin = new principal();
                new PrincipalController(vPrin); 
                vPrin.setVisible(true);
            }
        });
   
        vista.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                principal vPrin = new principal();
                new PrincipalController(vPrin); 
                vPrin.setVisible(true);
            }
        });

        vista.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp); 
                vOp.setVisible(true);
            }
        });

        vista.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli); 
                vCli.setVisible(true);
            }
        });

        vista.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid); 
                vVid.setVisible(true);
            }
        });

        vista.btnPeliculas.addMouseListener(new MouseAdapter() {
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
                    System.err.println("Error de búsqueda: " + ex.getMessage());
                }
            }
        });

     
        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila != -1) {
                vista.mostrarConfirmacion("¿Seguro que quieres eliminar<br>esta operación?", eSi -> {
                    vista.modeloTabla.removeRow(vista.tabla.convertRowIndexToModel(fila));
                });
            } else {
             
            }
        });

        
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                
                if (fila != -1) {
                 
                    if (columna == 6) {
                        abrirInfoOperacion();
                    }

              
                    if (e.getClickCount() == 2) {
                        abrirVentanaEditar(vista.tabla.convertRowIndexToModel(fila));
                    }
                }
            }
        });
    }

    private void abrirInfoOperacion() {
        vista.dispose();
        InfoOperacion vInfo = new InfoOperacion();
        
       
        vInfo.btnAtras.addActionListener(e -> {
            vInfo.dispose();
            operaciones op = new operaciones();
            new OperacionesController(op);
            op.setVisible(true);
        });

       
        vInfo.lblInicio.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vInfo.dispose(); principal p = new principal(); new PrincipalController(p); p.setVisible(true);
            }
        });

        vInfo.lblClientes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vInfo.dispose(); clientes c = new clientes(); new ClienteController(c); c.setVisible(true);
            }
        });

        vInfo.lblVideojuegos.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vInfo.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true);
            }
        });

        vInfo.lblPeliculas.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vInfo.dispose(); peliculas p = new peliculas(); new PeliculasController(p); p.setVisible(true);
            }
        });

        vInfo.lblOperacion.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vInfo.dispose(); operaciones op = new operaciones(); new OperacionesController(op); op.setVisible(true);
            }
        });

        vInfo.btnGuardar.addActionListener(e -> mostrarAlertaGris(vInfo, "Cambios guardados con éxito"));
        vInfo.btnDescargar.addActionListener(e -> mostrarAlertaGris(vInfo, "Generando ficha técnica..."));

        vInfo.setVisible(true);
    }

    private void mostrarAlertaGris(JFrame frame, String mensaje) {
        JDialog dialogo = new JDialog(frame, "Alerta", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 200);
        dialogo.setLocationRelativeTo(frame);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(209, 209, 209));
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(30));
        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblMsg);

        panel.add(Box.createVerticalGlue());
        
        JButton btnOk = new JButton("Aceptar");
        btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOk.setBackground(new Color(0, 51, 102));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(e -> dialogo.dispose());
        
        panel.add(btnOk);
        panel.add(Box.createVerticalStrut(20));
        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    private void abrirVentanaEditar(int filaModel) {
      
        System.out.println("Editando fila: " + filaModel);
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
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose(); principal p = new principal(); new PrincipalController(p); p.setVisible(true);
                }
            });

            vAdd.lblClientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose(); clientes c = new clientes(); new ClienteController(c); c.setVisible(true);
                }
            });

            vAdd.lblVideojuegos.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true);
                }
            });

            vAdd.lblPeliculas.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose(); peliculas p = new peliculas(); new PeliculasController(p); p.setVisible(true);
                }
            });
            
            vAdd.lblOperacion.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { regresar(); }
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
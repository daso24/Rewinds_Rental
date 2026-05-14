package controller;

import view.*;
import javax.swing.*;
import java.awt.event.*;

public class VideojuegosController {
    private videojuegos vista;
    private AgregarJuego vistaAdd;

    public VideojuegosController(videojuegos vista) {
        this.vista = vista;
        initEvents();
    }

    public VideojuegosController(AgregarJuego vistaAdd) {
        this.vistaAdd = vistaAdd;
        initEventsAdd();
    }

    private void initEvents() {
        vista.btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                vista.dispose();
                principal vPrin = new principal();
                new PrincipalController(vPrin); 
                vPrin.setVisible(true);
            }
        });

        vista.btnOperacion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp); 
                vOp.setVisible(true);
            }
        });

        vista.btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli); 
                vCli.setVisible(true);
            }
        });

        vista.btnVideojuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid); 
                vVid.setVisible(true);
            }
        });

        vista.btnPeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel); 
                vPel.setVisible(true);
            }
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
            vista.mostrarConfirmacionEliminar("¿Está seguro de borrar los<br>juegos seleccionados?", eSi -> {
                for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                    Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        int modelIndex = vista.tabla.convertRowIndexToModel(i);
                        vista.modelo.removeRow(modelIndex);
                    }
                }
            });
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                
                if (fila >= 0 && vista.tabla.getValueAt(fila, columna) != null) {
                    String valorCelda = vista.tabla.getValueAt(fila, columna).toString();
                    
                    if (valorCelda.equalsIgnoreCase("Ver info")) {
                        vista.dispose();
                        InfoJuego vInfo = new InfoJuego(); // Cambio a InfoJuego
                        new InfoJuegoInternalController(vInfo); // Cambio a InfoJuegoInternalController
                        vInfo.setVisible(true);
                    }
                }
            }
        });

        vista.btnAgregar.addActionListener(e -> {
            vista.dispose();
            AgregarJuego vAdd = new AgregarJuego();
            new VideojuegosController(vAdd);
            vAdd.setVisible(true);
        });
    }

    private void initEventsAdd() {
        vistaAdd.btnAtras.addActionListener(e -> {
            videojuegos vVid = new videojuegos();
            new VideojuegosController(vVid);
            vVid.setVisible(true);
            vistaAdd.dispose();
        });

        vistaAdd.btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarYMostrarPopUp();
            }
        });
    }

    private void validarYMostrarPopUp() {
        if (vistaAdd.txtNombre.getText().isEmpty() || vistaAdd.txtId.getText().isEmpty() || vistaAdd.cbPlataforma.getSelectedIndex() == 0) {
            vistaAdd.mostrarError("Error: Debes ingresar el nombre,<br>ID y seleccionar una plataforma.");
            return;
        }

        try {
            Double.parseDouble(vistaAdd.txtVenta.getText().replace("$", "").trim());
            Double.parseDouble(vistaAdd.txtRenta.getText().replace("$", "").trim());
        } catch (NumberFormatException ex) {
            vistaAdd.mostrarError("Error: Los precios deben<br>ser valores numéricos.");
            return;
        }

        vistaAdd.mostrarExito("¡Videojuego registrado con éxito!");
        
        videojuegos vVid = new videojuegos();
        new VideojuegosController(vVid);
        vVid.setVisible(true);
        vistaAdd.dispose();
    }

    private class InfoJuegoInternalController { 
        private InfoJuego vistaInfo; 

        public InfoJuegoInternalController(InfoJuego vistaInfo) {
            this.vistaInfo = vistaInfo;
            initEventsInfo();
        }

        private void initEventsInfo() {
            vistaInfo.btnAtras.addActionListener(e -> {
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
                vistaInfo.dispose();
            });

            vistaInfo.btnInicio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vistaInfo.dispose(); // Ajustado para cerrar la vista actual
                    principal v = new principal();
                    new PrincipalController(v);
                    v.setVisible(true);
                }
            });

            vistaInfo.btnVideojuegos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vistaInfo.dispose();
                    videojuegos v = new videojuegos();
                    new VideojuegosController(v);
                    v.setVisible(true);
                }
            });

            vistaInfo.btnEditar.addActionListener(e -> {
                vistaInfo.mostrarConfirmacion("¿Deseas guardar los cambios realizados?", ev -> {
                    System.out.println("Cambios guardados");
                });
            });
        }
    }
}
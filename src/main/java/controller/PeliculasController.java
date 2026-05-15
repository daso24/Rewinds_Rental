package controller;

import view.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PeliculasController {
    private peliculas vista;

    public PeliculasController(peliculas vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                if (columna == 6 && fila != -1) {
                    InfoPelicula vInfo = new InfoPelicula();
                    new InfoPeliculaController(vInfo);
                    vInfo.setVisible(true);
                    vista.dispose();
                }
            }
        });

        vista.btnAtras.addActionListener(e -> {
            vista.dispose();
            principal v = new principal();
            new PrincipalController(v);
            v.setVisible(true);
        });

        vista.btnInicio.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vista.dispose();
                principal v = new principal();
                new PrincipalController(v);
                v.setVisible(true);
            }
        });

        vista.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones v = new operaciones();
                new OperacionesController(v);
                v.setVisible(true);
            }
        });

        vista.btnClientes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vista.dispose();
                clientes v = new clientes();
                new ClienteController(v);
                v.setVisible(true);
            }
        });

        vista.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos v = new videojuegos();
                new VideojuegosController(v);
                v.setVisible(true);
            }
        });

        vista.btnAgregar.addActionListener(e -> {
            AñadirPelicula vAdd = new AñadirPelicula();
            new InternoAñadirPeliculaController(vAdd);
            vAdd.setVisible(true);
            vista.dispose();
        });

        vista.btnBuscar.addActionListener(e -> {
            String texto = vista.buscador.getText();
            if (texto.trim().isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        });

        vista.btnEliminar.addActionListener(e -> {
            boolean haySeleccion = false;
            for (int i = 0; i < vista.tabla.getRowCount(); i++) {
                Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                if (isSelected != null && isSelected) {
                    haySeleccion = true;
                    break;
                }
            }
            if (haySeleccion) {
                vista.mostrarConfirmacionEliminar("¿Está seguro de borrar las<br>películas seleccionadas?", eSi -> {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                        Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                        if (isSelected != null && isSelected) {
                            int modelIndex = vista.tabla.convertRowIndexToModel(i);
                            vista.modelo.removeRow(modelIndex);
                        }
                    }
                });
            } else {
                vista.mostrarAlerta("Selecciona al menos una película de la lista.", true);
            }
        });
    }

    private class InternoAñadirPeliculaController {
        private AñadirPelicula vAdd;
        public InternoAñadirPeliculaController(AñadirPelicula vAdd) {
            this.vAdd = vAdd;
            initAddEvents();
        }
        private void initAddEvents() {
            vAdd.btnAtras.addActionListener(e -> regresarAPeliculas(vAdd));
            vAdd.btnInicio.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });
            vAdd.btnOperacion.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    operaciones op = new operaciones();
                    new OperacionesController(op);
                    op.setVisible(true);
                }
            });
            vAdd.btnClientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    clientes c = new clientes();
                    new ClienteController(c);
                    c.setVisible(true);
                }
            });
            vAdd.btnPeliculas.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { regresarAPeliculas(vAdd); }
            });
            vAdd.btnAgregar.addActionListener(e -> {
                if (vAdd.txtNombre.getText().trim().isEmpty() || vAdd.txtId.getText().trim().isEmpty()) {
                    vAdd.mostrarAlerta("Error: Todos los campos obligatorios deben estar llenos.", true);
                } else {
                    vAdd.mostrarAlerta("¡Película registrada con éxito!", false);
                    regresarAPeliculas(vAdd);
                }
            });
        }
    }

    private class InfoPeliculaController {
        private InfoPelicula vInfo;
        public InfoPeliculaController(InfoPelicula vInfo) {
            this.vInfo = vInfo;
            initInfoEvents();
        }
        private void initInfoEvents() {
            vInfo.btnAtras.addActionListener(e -> regresarAPeliculas(vInfo));
            
            vInfo.btnInicio.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vInfo.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });
            vInfo.btnOperacion.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vInfo.dispose();
                    operaciones op = new operaciones();
                    new OperacionesController(op);
                    op.setVisible(true);
                }
            });
            vInfo.btnClientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vInfo.dispose();
                    clientes c = new clientes();
                    new ClienteController(c);
                    c.setVisible(true);
                }
            });
            vInfo.btnVideojuegos.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vInfo.dispose();
                    videojuegos v = new videojuegos();
                    new VideojuegosController(v);
                    v.setVisible(true);
                }
            });
            vInfo.btnPeliculas.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { regresarAPeliculas(vInfo); }
            });

            vInfo.btnEditar.addActionListener(e -> {
                vInfo.mostrarAlerta("¡Información editada con éxito!", false);
            });

            vInfo.btnDescargar.addActionListener(e -> {
                vInfo.mostrarAlerta("Ficha descargada correctamente.", false);
            });
        }
    }

    private void regresarAPeliculas(JFrame actual) {
        actual.dispose();
        peliculas p = new peliculas();
        new PeliculasController(p);
        p.setVisible(true);
    }
}
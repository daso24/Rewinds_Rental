package controller;

import view.*;
import javax.swing.*;
import java.awt.event.*;

public class ClienteController {
    private clientes vista;

    public ClienteController(clientes vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        vista.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                principal v = new principal();
                new PrincipalController(v);
                v.setVisible(true);
            }
        });

        vista.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones v = new operaciones();
                new OperacionesController(v);
                v.setVisible(true);
            }
        });

        vista.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos v = new videojuegos();
                new VideojuegosController(v);
                v.setVisible(true);
            }
        });

        vista.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                peliculas v = new peliculas();
                new PeliculasController(v);
                v.setVisible(true);
            }
        });

        vista.btnAgregar.addActionListener(e -> {
            AñadirClientes vAdd = new AñadirClientes();
            new InternoAñadirController(vAdd);
            vAdd.setVisible(true);
            vista.dispose();
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
                vista.mostrarConfirmacionEliminar("¿Está seguro de borrar los<br>clientes seleccionados?", eSi -> {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                        Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                        if (isSelected != null && isSelected) {
                            int modelIndex = vista.tabla.convertRowIndexToModel(i);
                            vista.modelo.removeRow(modelIndex);
                        }
                    }
                });
            } else {
                JOptionPane.showMessageDialog(vista, "Selecciona al menos un cliente de la lista.");
            }
        });

        vista.btnBuscar.addActionListener(e -> {
            String texto = vista.buscador.getText();
            if (texto.trim().isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                if (columna == 6 && fila != -1) {
                    InfoCliente vInfo = new InfoCliente();
                    new InternoInfoController(vInfo);
                    vInfo.setVisible(true);
                    vista.dispose();
                }
            }
        });
    }

    private class InternoInfoController {
        private InfoCliente vInfo;

        public InternoInfoController(InfoCliente vInfo) {
            this.vInfo = vInfo;
            initInfoEvents();
        }

        private void initInfoEvents() {
            vInfo.btnAtras.addActionListener(e -> regresarAClientes(vInfo));
            vInfo.btnEditar.addActionListener(e -> vInfo.mostrarExito("¡Cliente editado con éxito!"));
            
            vInfo.btnHistoVentas.addActionListener(e -> {
                HistorialVentas vHist = new HistorialVentas();
                new HistorialVentasController(vHist);
                vHist.setVisible(true);
                vInfo.dispose();
            });

            vInfo.btnHistoRentas.addActionListener(e -> {
                HistorialRentas vRent = new HistorialRentas();
                new HistorialRentasController(vRent);
                vRent.setVisible(true);
                vInfo.dispose();
            });

            vInfo.lblInicio.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vInfo.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });

            vInfo.lblClientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { regresarAClientes(vInfo); }
            });
        }
    }

    private class InternoAñadirController {
        private AñadirClientes vAdd;
        public InternoAñadirController(AñadirClientes vAdd) {
            this.vAdd = vAdd;
            initAddEvents();
        }
        private void initAddEvents() {
            vAdd.btnAtras.addActionListener(e -> regresarAClientes(vAdd));
            vAdd.btnEditar.addActionListener(e -> {
                if (vAdd.txtId.getText().isEmpty()) {
                    vAdd.mostrarError("Error: Todos los campos deben estar llenos.");
                } else {
                    vAdd.mostrarExito("¡Cliente registrado con éxito!");
                    regresarAClientes(vAdd);
                }
            });
        }
    }

    private class HistorialVentasController {
        private HistorialVentas vHist;
        public HistorialVentasController(HistorialVentas vHist) {
            this.vHist = vHist;
            initHistEvents();
        }
        private void initHistEvents() {
            vHist.btnAtras.addActionListener(e -> regresarAClientes(vHist));
            vHist.lblInicio.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vHist.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });
            vHist.lblClientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { regresarAClientes(vHist); }
            });
        }
    }

    private class HistorialRentasController {
        private HistorialRentas vRent;
        public HistorialRentasController(HistorialRentas vRent) {
            this.vRent = vRent;
            initRentEvents();
        }
        private void initRentEvents() {
            vRent.btnAtras.addActionListener(e -> regresarAClientes(vRent));
            vRent.lblInicio.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    vRent.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });
            vRent.lblClientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { regresarAClientes(vRent); }
            });
        }
    }

    private void regresarAClientes(JFrame actual) {
        actual.dispose();
        clientes c = new clientes();
        new ClienteController(c);
        c.setVisible(true);
    }
}
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
        vista.btnAtras.addActionListener(e -> {
            vista.dispose();
            principal vPrin = new principal();
            new PrincipalController(vPrin); 
            vPrin.setVisible(true);
        });

        configurarMenuLateral(vista.btnInicio, vista.btnOperacion, vista.btnClientes, vista.btnVideojuegos, vista.btnPeliculas, vista);

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
                    Object valor = vista.tabla.getValueAt(fila, columna);
                    String valorCelda = (valor instanceof Object[]) ? ((Object[]) valor)[1].toString() : valor.toString();
                    
                    if (valorCelda.equalsIgnoreCase("Ver info")) {
                        vista.dispose();
                        InfoJuego vInfo = new InfoJuego(); 
                        new InfoJuegoInternalController(vInfo);
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
        configurarMenuLateral(vistaAdd.btnInicio, vistaAdd.btnOperacion, vistaAdd.btnClientes, vistaAdd.btnVideojuegos, vistaAdd.btnPeliculas, vistaAdd);

        vistaAdd.btnAtras.addActionListener(e -> {
            videojuegos vVid = new videojuegos();
            new VideojuegosController(vVid);
            vVid.setVisible(true);
            vistaAdd.dispose();
        });

        vistaAdd.btnAgregar.addActionListener(e -> validarYMostrarPopUp());
    }

    private void configurarMenuLateral(JComponent inicio, JComponent operacion, JComponent clientes, JComponent videojuegos, JComponent peliculas, JFrame ventanaActual) {
        if (inicio != null) {
            inicio.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    ventanaActual.dispose();
                    principal v = new principal();
                    new PrincipalController(v); 
                    v.setVisible(true);
                }
            });
        }
        if (operacion != null) {
            operacion.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    ventanaActual.dispose();
                    operaciones v = new operaciones();
                    new OperacionesController(v); 
                    v.setVisible(true);
                }
            });
        }
        if (clientes != null) {
            clientes.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    ventanaActual.dispose();
                    clientes v = new clientes();
                    new ClienteController(v); 
                    v.setVisible(true);
                }
            });
        }
        if (videojuegos != null) {
            videojuegos.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    if (!(ventanaActual instanceof videojuegos)) {
                        ventanaActual.dispose();
                        videojuegos v = new videojuegos();
                        new VideojuegosController(v); 
                        v.setVisible(true);
                    }
                }
            });
        }
        if (peliculas != null) {
            peliculas.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    ventanaActual.dispose();
                    peliculas v = new peliculas();
                    new PeliculasController(v); 
                    v.setVisible(true);
                }
            });
        }
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
            configurarMenuLateral(vistaInfo.btnInicio, vistaInfo.btnOperacion, vistaInfo.btnClientes, vistaInfo.btnVideojuegos, vistaInfo.btnPeliculas, vistaInfo);

            vistaInfo.btnAtras.addActionListener(e -> {
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
                vistaInfo.dispose();
            });

            vistaInfo.btnEditar.addActionListener(e -> {
                vistaInfo.mostrarConfirmacion("¿Deseas guardar los cambios realizados?", ev -> {
                    System.out.println("Cambios guardados");
                });
            });
        }
    }
}
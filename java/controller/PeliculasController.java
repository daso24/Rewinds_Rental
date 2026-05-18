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
                int filaVisual = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                if (columna == 6 && filaVisual != -1) {
                    int filaModelo = vista.tabla.convertRowIndexToModel(filaVisual);
                    
                    String nombre = vista.modelo.getValueAt(filaModelo, 2).toString();
                    String id = vista.modelo.getValueAt(filaModelo, 3).toString();
                    String plataforma = vista.modelo.getValueAt(filaModelo, 5).toString();
                    
                    ImageIcon iconoCaratula = null;
                    Object caratulaObj = vista.modelo.getValueAt(filaModelo, 1);
                    if (caratulaObj == null) {
                        caratulaObj = vista.modelo.getValueAt(filaModelo, 0);
                    }
                    if (caratulaObj instanceof ImageIcon) {
                        iconoCaratula = (ImageIcon) caratulaObj;
                    }
                    
                    String tipo = "Película";
                    String precioVenta = "$ 250.00";
                    String descuento = "0%";
                    String stockVenta = "50";
                    String stockRenta = "20";
                    String precioRenta = "$ 100.00";
                    String clasificacion = "B-15";
                    String anio = "2025";
                    String genero = "Acción";

                    vista.dispose();
                    InfoPelicula vInfo = new InfoPelicula();
                    vInfo.setDatosPelicula(nombre, id, tipo, plataforma, precioVenta, descuento, stockVenta, stockRenta, precioRenta, clasificacion, anio, genero, iconoCaratula);
                    
                    new InfoPeliculaController(vInfo);
                    vInfo.setVisible(true);
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
            String texto = vista.buscador.getText().trim();
            if (texto.isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        });

        vista.btnFiltrar.addActionListener(e -> {
            abrirDialogoFiltrar();
        });

        vista.btnEliminar.addActionListener(e -> {
            boolean haySeleccion = false;
            for (int i = 0; i < vista.tabla.getRowCount(); i++) {
                int modelIndex = vista.tabla.convertRowIndexToModel(i);
                Boolean isSelected = (Boolean) vista.modelo.getValueAt(modelIndex, 0);
                if (isSelected != null && isSelected) {
                    haySeleccion = true;
                    break;
                }
            }
            if (haySeleccion) {
                vista.mostrarConfirmacion("¿Está seguro de borrar las<br>películas seleccionadas?", eSi -> {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                        int modelIndex = vista.tabla.convertRowIndexToModel(i);
                        Boolean isSelected = (Boolean) vista.modelo.getValueAt(modelIndex, 0);
                        if (isSelected != null && isSelected) {
                            vista.modelo.removeRow(modelIndex);
                        }
                    }
                });
            } else {
                vista.mostrarAlerta("Selecciona al menos una película<br>de la lista para continuar.");
            }
        });
    }

    private void abrirDialogoFiltrar() {
        JDialog dialogo = new JDialog(vista, "Filtro Películas", true);
        dialogo.setSize(320, 200);
        dialogo.setLocationRelativeTo(vista);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblColumna = new JLabel("Filtrar por columna:");
        String[] opcionesColumnas = {"Título", "Id", "Plataforma"};
        JComboBox<String> comboColumnas = new JComboBox<>(opcionesColumnas);
        
        JLabel lblCriterio = new JLabel("Texto a buscar:");
        JTextField txtCriterio = new JTextField();

        panelContenido.add(lblColumna);
        panelContenido.add(comboColumnas);
        panelContenido.add(lblCriterio);
        panelContenido.add(txtCriterio);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAplicar = new JButton("Aplicar");
        JButton btnLimpiar = new JButton("Quitar Filtros");

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnAplicar);

        btnAplicar.addActionListener(evt -> {
            String criterio = txtCriterio.getText().trim();
            int columnaSeleccionada = comboColumnas.getSelectedIndex();
            int indexColumnaTabla = 2;
            if (columnaSeleccionada == 1) indexColumnaTabla = 3;
            if (columnaSeleccionada == 2) indexColumnaTabla = 5;

            if (criterio.isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                try {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + criterio, indexColumnaTabla));
                } catch (Exception ex) {
                    vista.mostrarAlerta("Error en la expresión de filtrado.");
                }
            }
            dialogo.dispose();
        });

        btnLimpiar.addActionListener(evt -> {
            vista.sorter.setRowFilter(null);
            vista.buscador.setText("");
            dialogo.dispose();
        });

        dialogo.add(panelContenido, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
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
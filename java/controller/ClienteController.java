package controller;

import view.*;
import javax.swing.*;

import sun.jvm.hotspot.utilities.RBColor;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.net.URL;

public class ClienteController {
    private clientes vista;

    public ClienteController(clientes vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        configurarMenuLateral(vista.btnInicio, vista.btnOperacion, vista.btnClientes, vista.btnVideojuegos, vista.btnPeliculas, vista);

        vista.btnAtras.addActionListener(e -> {
            vista.dispose();
            principal v = new principal();
            new PrincipalController(v);
            v.setVisible(true);
        });

        vista.btnAgregar.addActionListener(e -> {
            AñadirClientes vAdd = new AñadirClientes();
            configurarMenuLateral(vAdd.btnInicio, vAdd.btnOperacion, vAdd.btnClientes, vAdd.btnVideojuegos, vAdd.btnPeliculas, vAdd);
            
            JButton btnAtrasAdd = buscarBotonPorTexto(vAdd, "Atrás");
            if (btnAtrasAdd == null) btnAtrasAdd = obtenerCampoBoton(vAdd, "btnAtras");
            
            if (btnAtrasAdd != null) {
                btnAtrasAdd.addActionListener(eAtras -> {
                    vAdd.dispose();
                    clientes vClientes = new clientes();
                    new ClienteController(vClientes);
                    vClientes.setVisible(true);
                });
            }

            JButton btnGuardarAdd = buscarBotonPorTexto(vAdd, "Guardar");
            if (btnGuardarAdd == null) btnGuardarAdd = obtenerCampoBoton(vAdd, "btnGuardar");
            if (btnGuardarAdd == null) btnGuardarAdd = obtenerCampoBoton(vAdd, "btnAgregar");

            if (btnGuardarAdd != null) {
                btnGuardarAdd.addActionListener(eGuardar -> {
                    JTextField txtNombre = obtenerCampoTexto(vAdd, "txtNombre");
                    JTextField txtId = obtenerCampoTexto(vAdd, "txtId");
                    
                    String nombre = (txtNombre != null) ? txtNombre.getText() : "";
                    String id = (txtId != null) ? txtId.getText() : "";
                    
                    if (nombre.trim().isEmpty() || id.trim().isEmpty()) {
                        vAdd.mostrarError("Todos los campos son obligatorios.", "/img/mingcute_warning-fill.png");
                    } else {
                        vAdd.mostrarExito("Cliente añadido correctamente.", "/img/palomitaverde.png");
                        vAdd.dispose();
                        
                        clientes vClientes = new clientes();
                        new ClienteController(vClientes);
                        vClientes.setVisible(true);
                    }
                });
            }

            vAdd.setVisible(true);
            vista.dispose();
        });

        vista.btnFiltrar.addActionListener(e -> {
            abrirDialogoFiltrar();
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
                mostrarAvisoOriginal("Selecciona al menos un cliente de la lista.", "/img/mingcute_warning-fill.png", new Color(130, 130, 130));
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
                int filaVisual = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                
                if (columna == 6 && filaVisual != -1) {
                    int filaModelo = vista.tabla.convertRowIndexToModel(filaVisual);
                    
                    String nombre = vista.modelo.getValueAt(filaModelo, 1).toString();
                    String id = vista.modelo.getValueAt(filaModelo, 2).toString().trim();
                    String ultimaRenta = vista.modelo.getValueAt(filaModelo, 5).toString();
                    String telefonoDummy = "555-01" + id.substring(0, Math.min(2, id.length())); 

                    InfoCliente vInfo = new InfoCliente();
                    vInfo.setDatosCliente(nombre, id, telefonoDummy, ultimaRenta);
                    
                    cargarImagenPorId(vInfo, id);
                    asignarEventosFichaCliente(vInfo, nombre, id, telefonoDummy, ultimaRenta);
                    
                    vInfo.setVisible(true);
                    vista.dispose();
                }
            }
        });
    }

    private void cargarImagenPorId(InfoCliente vInfo, String id) {
        String rutaImagen = "";
        switch (id.trim()) {
            case "482915":
                rutaImagen = "/img/bc439453f09041eded2b58747ef63acc.jpg";
                break;
            case "730642":
                rutaImagen = "/img/Miranda_cosgrove.jpg";
                break;
            case "105422":
                rutaImagen = "/img/tobey-maguire-pic.jpg";
                break;
            case "627104":
                rutaImagen = "/img/Robert Pattinson.jpg";
                break;
            case "195873":
                rutaImagen = "/img/Emma_mayers.jpg";
                break;
        }
        if (!rutaImagen.isEmpty()) {
            vInfo.setImagenCliente(rutaImagen);
            vInfo.lblFoto.revalidate();
            vInfo.lblFoto.repaint();
        }
    }

    private void abrirDialogoFiltrar() {
        JDialog dialogo = new JDialog(vista, "Filtro Clientes", true);
        dialogo.setSize(320, 200);
        dialogo.setLocationRelativeTo(vista);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblColumna = new JLabel("Filtrar por columna:");
        String[] opcionesColumnas = {"Nombre", "ID"};
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
            int indexColumnaTabla = 1;
            if (columnaSeleccionada == 1) indexColumnaTabla = 2;

            if (criterio.isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                try {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + criterio, indexColumnaTabla));
                } catch (Exception ex) {
                    mostrarAvisoOriginal("Error en la expresión de filtrado.", "/img/mingcute_warning-fill.png", new Color(130, 130, 130));
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

    private void asignarEventosFichaCliente(InfoCliente vInfo, String nombre, String id, String telefono, String renta) {
        configurarMenuLateral(vInfo.lblInicio, vInfo.lblOperacion, vInfo.lblClientes, vInfo.lblVideojuegos, vInfo.lblPeliculas, vInfo);

        if (vInfo.btnAtras != null) {
            vInfo.btnAtras.addActionListener(eAtras -> {
                vInfo.dispose();
                clientes vClientes = new clientes();
                new ClienteController(vClientes);
                vClientes.setVisible(true);
            });
        }

        if (vInfo.btnEditar != null) {
            vInfo.btnEditar.addActionListener(eEdit -> {
                vInfo.mostrarExito("Se han modificado correctamente<br>los datos del cliente.");
            });
        }

        if (vInfo.btnDescargar != null) {
            vInfo.btnDescargar.addActionListener(ePDF -> {
                vInfo.mostrarExito("Descargando ficha de cliente en PDF...");
            });
        }

        if (vInfo.btnGenerar != null) {
            vInfo.btnGenerar.addActionListener(eCard -> {
                vInfo.mostrarExito("Generating tarjeta de cliente en PDF...");
            });
        }

        if (vInfo.btnHistoVentas != null) {
            vInfo.btnHistoVentas.addActionListener(eVentas -> {
                HistorialVentas vVentas = new HistorialVentas();
                configurarMenuLateral(vVentas.btnInicio, vVentas.btnOperacion, vVentas.btnClientes, vVentas.btnVideojuegos, vVentas.btnPeliculas, vVentas);
                
                if (vVentas.btnAtras != null) {
                    vVentas.btnAtras.addActionListener(eBack -> {
                        vVentas.dispose();
                        InfoCliente vInfoVolver = new InfoCliente();
                        vInfoVolver.setDatosCliente(nombre, id, telefono, renta);
                        cargarImagenPorId(vInfoVolver, id);
                        asignarEventosFichaCliente(vInfoVolver, nombre, id, telefono, renta);
                        vInfoVolver.setVisible(true);
                    });
                }
                vVentas.setVisible(true);
                vInfo.dispose();
            });
        }

        if (vInfo.btnHistoRentas != null) {
            vInfo.btnHistoRentas.addActionListener(eHisto -> {
                HistorialRentas vHisto = new HistorialRentas();
                configurarMenuLateral(vHisto.btnInicio, vHisto.btnOperacion, vHisto.btnClientes, vHisto.btnVideojuegos, vHisto.btnPeliculas, vHisto);
                
                if (vHisto.btnAtras != null) {
                    vHisto.btnAtras.addActionListener(eBack -> {
                        vHisto.dispose();
                        InfoCliente vInfoVolver = new InfoCliente();
                        vInfoVolver.setDatosCliente(nombre, id, telefono, renta);
                        cargarImagenPorId(vInfoVolver, id);
                        asignarEventosFichaCliente(vInfoVolver, nombre, id, telefono, renta);
                        vInfoVolver.setVisible(true);
                    });
                }
                vHisto.setVisible(true);
                vInfo.dispose();
            });
        }
    }

    private void configurarMenuLateral(JLabel inicio, JLabel operacion, JLabel clientes, JLabel videojuegos, JLabel peliculas, JFrame ventanaActual) {
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
                    if (!(ventanaActual instanceof clientes)) {
                        ventanaActual.dispose();
                        clientes v = new clientes();
                        new ClienteController(v);
                        v.setVisible(true);
                    }
                }
            });
        }
        if (videojuegos != null) {
            videojuegos.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    ventanaActual.dispose();
                    videojuegos v = new videojuegos();
                    new VideojuegosController(v);
                    v.setVisible(true);
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

    private JButton buscarBotonPorTexto(Container contenedor, String texto) {
        for (Component comp : contenedor.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if (btn.getText() != null && btn.getText().toLowerCase().contains(texto.toLowerCase())) {
                    return btn;
                }
            } else if (comp instanceof Container) {
                JButton btn = buscarBotonPorTexto((Container) comp, texto);
                if (btn != null) return btn;
            }
        }
        return null;
    }

    private JButton obtenerCampoBoton(Object objeto, String nombreCampo) {
        try {
            Field campo = objeto.getClass().getField(nombreCampo);
            return (JButton) campo.get(objeto);
        } catch (Exception e) {
            try {
                Field campo = objeto.getClass().getDeclaredField(nombreCampo);
                campo.setAccessible(true);
                return (JButton) campo.get(objeto);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    private JTextField obtenerCampoTexto(Object objeto, String nombreCampo) {
        try {
            Field campo = objeto.getClass().getField(nombreCampo);
            return (JTextField) campo.get(objeto);
        } catch (Exception e) {
            try {
                Field campo = objeto.getClass().getDeclaredField(nombreCampo);
                campo.setAccessible(true);
                return (JTextField) campo.get(objeto);
            } catch (Exception ex) {
                return buscarTextFieldInterno((Container) objeto, nombreCampo);
            }
        }
    }

    private JTextField buscarTextFieldInterno(Container contenedor, String pista) {
        for (Component comp : contenedor.getComponents()) {
            if (comp instanceof JTextField) {
                return (JTextField) comp;
            } else if (comp instanceof Container) {
                JTextField txt = buscarTextFieldInterno((Container) comp, pista);
                if (txt != null) return txt;
            }
        }
        return null;
    }

    private void mostrarAvisoOriginal(String mensaje, String rutaIcono, Color colorBoton) {
        JDialog dialogo = new JDialog(vista, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(vista);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(5));
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        panelContenido.add(Box.createVerticalGlue());
        
        try {
            if (rutaIcono != null && !rutaIcono.isEmpty()) {
              
                String rutaLimpia = rutaIcono.startsWith("/") ? rutaIcono.substring(1) : rutaIcono;
                URL u = getClass().getClassLoader().getResource(rutaLimpia);
                if (u != null) {
                    ImageIcon img = new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                    JLabel iconoCentro = new JLabel(img);
                    iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panelContenido.add(iconoCentro);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        panelContenido.add(Box.createVerticalGlue());
        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(120, 35));
        btnOk.setBackground(new Color(0,51,102));
        btnOk.setForeground(Color.WHITE);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialogo.dispose());
        JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pBotones.setOpaque(false);
        pBotones.add(btnOk);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }
}
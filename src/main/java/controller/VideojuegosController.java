package controller;

import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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

    private ImageIcon evaluarYObtenerIcono(Object celdaObjeto) {
        if (celdaObjeto == null) return null;
        if (celdaObjeto instanceof ImageIcon) {
            return (ImageIcon) celdaObjeto;
        }
        if (celdaObjeto instanceof Object[]) {
            Object[] data = (Object[]) celdaObjeto;
            if (data.length > 0 && data[0] instanceof ImageIcon) {
                return (ImageIcon) data[0];
            }
        }
        return null;
    }

    private ImageIcon evaluarIconoDesdeComponente(Object celdaObjeto) {
        if (celdaObjeto instanceof JLabel) {
            Icon icon = ((JLabel) celdaObjeto).getIcon();
            if (icon instanceof ImageIcon) return (ImageIcon) icon;
        }
        return null;
    }

    private ImageIcon evaluarImagenHD(Image srcImg, int targetW, int targetH) {
        if (srcImg == null) return null;
        
        int type = (srcImg instanceof BufferedImage) && ((BufferedImage) srcImg).getType() != BufferedImage.TYPE_CUSTOM 
                   ? ((BufferedImage) srcImg).getType() : BufferedImage.TYPE_INT_ARGB;
                   
        BufferedImage img = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), type);
        Graphics2D gNormal = img.createGraphics();
        gNormal.drawImage(srcImg, 0, 0, null);
        gNormal.dispose();

        int w = img.getWidth();
        int h = img.getHeight();

        do {
            if (w > targetW) {
                w /= 2;
                if (w < targetW) w = targetW;
            } else {
                w = targetW;
            }

            if (h > targetH) {
                h /= 2;
                if (h < targetH) h = targetH;
            } else {
                h = targetH;
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();

            img = tmp;
        } while (w != targetW || h != targetH);

        return new ImageIcon(img);
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
                vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
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
                vista.mostrarConfirmacionEliminar("¿Está seguro de borrar los<br>juegos seleccionados?", eSi -> {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                        Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                        if (isSelected != null && isSelected) {
                            int modelIndex = vista.tabla.convertRowIndexToModel(i);
                            vista.modelo.removeRow(modelIndex);
                        }
                    }
                });
            } else {
                mostrarAvisoGris("Selecciona al menos un juego de la lista.");
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaVisual = vista.tabla.rowAtPoint(e.getPoint());
                int columnaVisual = vista.tabla.columnAtPoint(e.getPoint());
                
                if (filaVisual != -1 && vista.tabla.getValueAt(filaVisual, columnaVisual) != null) {
                    int columnaModelo = vista.tabla.convertColumnIndexToModel(columnaVisual);
                    Object valor = vista.tabla.getValueAt(filaVisual, columnaVisual);
                    String valorCelda = (valor instanceof Object[]) ? ((Object[]) valor)[1].toString() : valor.toString();
                    
                    if (columnaModelo == 7 && valorCelda.equalsIgnoreCase("Ver info")) {
                        int filaModelo = vista.tabla.convertRowIndexToModel(filaVisual);
                        
                        String nombre = vista.modelo.getValueAt(filaModelo, 2).toString();
                        String id = vista.modelo.getValueAt(filaModelo, 3).toString();
                        String plataforma = vista.modelo.getValueAt(filaModelo, 5).toString();
                        String precioRenta = vista.modelo.getValueAt(filaModelo, 6).toString();
                        
                        Object cellCaratulaModelo = vista.modelo.getValueAt(filaModelo, 1);
                        String nombreCaratula = "";
                        try {
                            nombreCaratula = cellCaratulaModelo.toString();
                        } catch (Exception ex) {
                            nombreCaratula = id;
                        }
                        
                        String tipo = "Videojuego";
                        String precioVenta = "$799.00";
                        String descuento = "0%";
                        String stockVenta = "15";
                        String stockRenta = "5";
                        String clasificacion = "TEEN";
                        String anio = "2023";
                        String genero = "Acción / Aventura";

                        vista.dispose();
                        InfoJuego vInfo = new InfoJuego(); 
                        vInfo.setDatosJuego(nombre, id, tipo, plataforma, precioVenta, descuento, stockVenta, stockRenta, precioRenta, clasificacion, anio, genero, nombreCaratula);
                        
                        ImageIcon imagenProducto = evaluarYObtenerIcono(cellCaratulaModelo);
                        if (imagenProducto == null) {
                            Object cellCaratulaVista = vista.tabla.getValueAt(filaVisual, 1);
                            imagenProducto = evaluarYObtenerIcono(cellCaratulaVista);
                            if (imagenProducto == null) {
                                imagenProducto = evaluarIconoDesdeComponente(cellCaratulaVista);
                            }
                        }

                        if (imagenProducto != null && vInfo.lblImg != null) {
                            try {
                                ImageIcon imagenEscalada = evaluarImagenHD(imagenProducto.getImage(), 260, 280);
                                vInfo.lblImg.setIcon(imagenEscalada);
                                vInfo.lblImg.setText("");
                            } catch (Exception eEx) {}
                        }

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

    private void mostrarAvisoGris(String mensaje) {
        JDialog dialogo = new JDialog(vista, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 240);
        dialogo.setLocationRelativeTo(vista);
        
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        dialogo.setContentPane(contenedor);

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(25));

        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        panelContenido.add(Box.createVerticalGlue());

        try {
            JLabel iconoCentro = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}
        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setOpaque(false);
        
        JButton btnOk = new JButton("OK") {
            @Override 
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(130, 130, 130));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnOk.setPreferredSize(new Dimension(110, 35));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 13));
        btnOk.setContentAreaFilled(false);
        btnOk.setBorderPainted(false);
        btnOk.setFocusPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialogo.dispose());

        panelBotones.add(btnOk);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void abrirDialogoFiltrar() {
        JDialog dialogo = new JDialog(vista, "Filtro Videojuegos", true);
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
                    mostrarAvisoGris("Error en la expresión de filtrado.");
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

            vistaInfo.btnDescargar.addActionListener(e -> {
                vistaInfo.mostrarAvisoDescarga("Descargando .....");
            });
        }
    }
}
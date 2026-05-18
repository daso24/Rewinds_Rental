package controller;

import view.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class OperacionesController {
    private operaciones vista;
    private AñadirOperacion vAdd;

    public OperacionesController(operaciones vista) {
        this.vista = vista;
        configurarRenderizadoTabla();
        initEvents();
    }

    public OperacionesController(AñadirOperacion vAdd) {
        this.vAdd = vAdd;
        initAddEvents();
    }

    private ImageIcon escalarImagenHD(Image srcImg, int targetW, int targetH) {
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

    private void configurarRenderizadoTabla() {
        if (vista == null || vista.tabla == null) return;
        
        vista.tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            private final JPanel panel = new JPanel(new BorderLayout(5, 2));
            private final JLabel lblImagen = new JLabel();
            private final JLabel lblTexto = new JLabel();

            {
                panel.setOpaque(true);
                lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
                lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
                lblTexto.setFont(new Font("Inter", Font.PLAIN, 11));
                panel.add(lblImagen, BorderLayout.CENTER);
                panel.add(lblTexto, BorderLayout.SOUTH);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (isSelected) {
                    panel.setBackground(table.getSelectionBackground());
                    lblTexto.setForeground(table.getSelectionForeground());
                } else {
                    panel.setBackground(table.getBackground());
                    lblTexto.setForeground(table.getForeground());
                }

                if (value instanceof Object[]) {
                    Object[] data = (Object[]) value;
                    if (data[0] instanceof ImageIcon) {
                        lblImagen.setIcon(escalarImagenHD(((ImageIcon) data[0]).getImage(), 60, 50));
                    } else {
                        lblImagen.setIcon(null);
                    }
                    lblTexto.setText(data.length > 1 && data[1] != null ? data[1].toString() : "");
                } else if (value instanceof ImageIcon) {
                    lblImagen.setIcon(escalarImagenHD(((ImageIcon) value).getImage(), 60, 50));
                    lblTexto.setText(mapearNombreString(value));
                } else {
                    lblImagen.setIcon(null);
                    lblTexto.setText(value != null ? value.toString() : "");
                }

                int alturaDeseada = 75;
                if (table.getRowHeight(row) != alturaDeseada) {
                    table.setRowHeight(row, alturaDeseada);
                }

                return panel;
            }
        });
    }

    private void initEvents() {
        vista.btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { abrirMenuPrincipalDesdeVista(); }
        });
   
        vista.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { abrirMenuPrincipalDesdeVista(); }
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
            AñadirOperacion addVista = new AñadirOperacion();
            new OperacionesController(addVista);
            addVista.setVisible(true);
        });

        vista.btnFiltrar.addActionListener(e -> abrirDialogoFiltrar());

        ActionListener accionFiltrar = e -> aplicarFiltroRegex(vista.buscador.getText().trim(), null);
        vista.btnBuscar.addActionListener(accionFiltrar);

        vista.buscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                aplicarFiltroRegex(vista.buscador.getText().trim(), null);
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
                vista.mostrarConfirmacion("¿Está seguro de borrar las<br>operaciones seleccionadas?", eSi -> {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                        Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                        if (isSelected != null && isSelected) {
                            int modelIndex = vista.tabla.convertRowIndexToModel(i);
                            vista.modeloTabla.removeRow(modelIndex);
                        }
                    }
                });
            } else {
                mostrarAlertaGris(vista, "Selecciona al menos una operación de la lista.");
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());
                
                if (fila != -1) {
                    int modelRow = vista.tabla.convertRowIndexToModel(fila);
                    if (columna == 6) {
                        abrirInfoOperacion(modelRow);
                    }
                    if (e.getClickCount() == 2) {
                        abrirVentanaEditar(modelRow);
                    }
                }
            }
        });
    }

    private void initAddEvents() {
        vAdd.btnAtras.addActionListener(e -> regresarAOperaciones());
        vAdd.btnGuardar.addActionListener(e -> validarYGuardar());
        
        vAdd.btnSeleccionarProd.addActionListener(e -> {
            vAdd.setVisible(false);
            SeleccionarProducto vSel = new SeleccionarProducto();
            configurarVentanaSeleccionarProducto(vSel);
            vSel.setVisible(true);
        });

        vAdd.lblInicio.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vAdd.dispose(); principal p = new principal(); new PrincipalController(p); p.setVisible(true); }
        });
        vAdd.lblClientes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vAdd.dispose(); clientes c = new clientes(); new ClienteController(c); c.setVisible(true); }
        });
        vAdd.lblVideojuegos.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vAdd.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true); }
        });
        vAdd.lblPeliculas.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vAdd.dispose(); peliculas p = new peliculas(); new PeliculasController(p); p.setVisible(true); }
        });
        vAdd.lblOperacion.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { regresarAOperaciones(); }
        });
    }

    private void configurarVentanaSeleccionarProducto(SeleccionarProducto vSel) {
        vSel.btnAtras.addActionListener(ev -> {
            vSel.dispose();
            vAdd.setVisible(true);
        });
        
        Runnable filtrar = () -> {
            String texto = vSel.buscador.getText().trim();
            if (texto.isEmpty()) vSel.sorter.setRowFilter(null);
            else vSel.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        };

        vSel.btnBuscar.addActionListener(ev -> filtrar.run());
        vSel.buscador.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent ev) { filtrar.run(); }
        });

        vSel.btnEliminar.addActionListener(ev -> {
            int fila = vSel.tabla.getSelectedRow();
            if (fila != -1) {
                vSel.mostrarConfirmacion("¿Seguro que quieres eliminar este producto?", eSi -> {
                    vSel.modeloTabla.removeRow(vSel.tabla.convertRowIndexToModel(fila));
                });
            }
        });

        vSel.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                int fila = vSel.tabla.rowAtPoint(ev.getPoint());
                if (fila != -1 && ev.getClickCount() == 2) {
                    procesarSeleccionFila(vSel, vSel.tabla.convertRowIndexToModel(fila));
                }
            }
        });
    }

    private void procesarSeleccionFila(SeleccionarProducto vSel, int modelRow) {
        String nombreCliente = vSel.modeloTabla.getValueAt(modelRow, 1) != null ? vSel.modeloTabla.getValueAt(modelRow, 1).toString() : "";
        String tipoOperacion = vSel.modeloTabla.getValueAt(modelRow, 2) != null ? vSel.modeloTabla.getValueAt(modelRow, 2).toString() : "";
        
        Object cellProducto = vSel.modeloTabla.getValueAt(modelRow, 3);
        String nombreProducto = mapearNombreString(cellProducto);
        
        String tipoProducto = "Película";
        String lowerProd = nombreProducto.toLowerCase();
        if (lowerProd.contains("forza") || lowerProd.contains("spider-man") || lowerProd.contains("ps5") || lowerProd.contains("xbox") || lowerProd.contains("horizon") || lowerProd.contains("game")) {
            tipoProducto = "Videojuego";
        }

        vAdd.txtNombreCli.setText(nombreCliente); 
        vAdd.txtNomProd.setText(nombreProducto);   
        vAdd.txtTipoProd.setText(tipoProducto); 

        if (vAdd.txtIdProd != null) {
            vAdd.txtIdProd.setText(""); 
        }

        if (tipoOperacion.equalsIgnoreCase("Renta")) {
            vAdd.rbRenta.setSelected(true);
        } else {
            vAdd.rbVenta.setSelected(true);
        }
        
        String[] camposAdd = {"txtProducto", "txtNombreProducto", "txtTipo", "txtIdCliente", "txtIdCli", "lblCaratula", "panelFoto", "lblFoto"};
        String[] valoresAdd = {nombreProducto, nombreProducto, tipoProducto, "", "", "", "", ""};
        
        for (int i = 0; i < camposAdd.length; i++) {
            try {
                java.lang.reflect.Field f = vAdd.getClass().getDeclaredField(camposAdd[i]);
                f.setAccessible(true);
                Object comp = f.get(vAdd);
                if (comp instanceof JTextField) {
                    ((JTextField) comp).setText(valoresAdd[i]);
                }
            } catch (Exception eEx) {}
        }

        ImageIcon imagenProducto = null;
        if (cellProducto instanceof ImageIcon) {
            imagenProducto = (ImageIcon) cellProducto;
        } else if (cellProducto instanceof Object[]) {
            Object[] data = (Object[]) cellProducto;
            if (data[0] instanceof ImageIcon) {
                imagenProducto = (ImageIcon) data[0];
            }
        }

        if (imagenProducto != null) {
            try {
                java.lang.reflect.Field panelFotoField = null;
                String[] opcionesFotos = {"lblCaratula", "panelFoto", "lblFoto", "lblImagen"};
                for (String opt : opcionesFotos) {
                    try {
                        panelFotoField = vAdd.getClass().getDeclaredField(opt);
                        break;
                    } catch (Exception e) {}
                }
                
                if (panelFotoField != null) {
                    panelFotoField.setAccessible(true);
                    Object componenteFoto = panelFotoField.get(vAdd);
                    if (componenteFoto instanceof JLabel) {
                        JLabel lbl = (JLabel) componenteFoto;
                        lbl.setIcon(escalarImagenHD(imagenProducto.getImage(), 310, 260));
                        lbl.setText("");
                    } else if (componenteFoto instanceof JPanel) {
                        JPanel pnl = (JPanel) componenteFoto;
                        pnl.removeAll();
                        pnl.setLayout(new BorderLayout());
                        JLabel lbl = new JLabel();
                        lbl.setIcon(escalarImagenHD(imagenProducto.getImage(), 310, 260));
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        pnl.add(lbl, BorderLayout.CENTER);
                        pnl.revalidate();
                        pnl.repaint();
                    }
                }
            } catch (Exception eEx) {}
        }

        vSel.dispose();
        vAdd.setVisible(true);
    }

    private String mapearNombreString(Object value) {
        if (value == null) return "";
        if (value instanceof Object[]) {
            Object[] data = (Object[]) value;
            return data.length > 1 && data[1] != null ? data[1].toString() : (data[0] != null ? data[0].toString() : "");
        }
        if (value instanceof ImageIcon) {
            String iconStr = value.toString();
            if (iconStr.contains("forza") || iconStr.contains("horizon") || iconStr.contains("game")) {
                return "Forza Horizon 6";
            } else if (iconStr.contains("71fw9QnEQUL")) {
                return "Spider-Man 2 PS5";
            } else if (iconStr.contains("71w58zkWnfL")) {
                return "Oppenheimer";
            } else if (iconStr.contains("51gz5Gfjl8L")) {
                return "Interstellar";
            } else if (iconStr.contains("71MZBMmOXtL")) {
                return "Avatar: The Way of Water";
            } else {
                return "Producto Multimedia";
            }
        }
        return value.toString();
    }

    private void validarYGuardar() {
        if (vAdd.txtNombreCli.getText().trim().isEmpty() || vAdd.txtIdOp.getText().trim().isEmpty()) {
            vAdd.mostrarAlerta("Error: Existen campos obligatorios vacíos.", true);
        } else {
            vAdd.mostrarAlerta("Operación guardada con éxito.", false);
            regresarAOperaciones();
        }
    }

    private void regresarAOperaciones() {
        vAdd.dispose();
        operaciones op = new operaciones();
        new OperacionesController(op);
        op.setVisible(true);
    }

    private void aplicarFiltroRegex(String texto, Integer columna) {
        if (texto.isEmpty()) {
            vista.sorter.setRowFilter(null);
        } else {
            try {
                if (columna == null) {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                } else {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
                }
            } catch (java.util.regex.PatternSyntaxException ex) {
                System.err.println("Error de búsqueda: " + ex.getMessage());
            }
        }
    }

    private void abrirMenuPrincipalDesdeVista() {
        vista.dispose();
        principal vPrin = new principal();
        new PrincipalController(vPrin); 
        vPrin.setVisible(true);
    }

    private void abrirVentanaEditar(int filaModel) {
        System.out.println("Editando fila: " + filaModel);
    }

    private void abrirDialogoFiltrar() {
        JDialog dialogo = new JDialog(vista, "Filtro Operaciones", true);
        dialogo.setSize(320, 200);
        dialogo.setLocationRelativeTo(vista);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JComboBox<String> comboColumnas = new JComboBox<>(new String[]{"ID Operación", "Cliente", "Tipo", "Producto", "Fecha", "Costo"});
        JTextField txtCriterio = new JTextField();

        panelContenido.add(new JLabel("Filtrar por columna:"));
        panelContenido.add(comboColumnas);
        panelContenido.add(new JLabel("Texto a buscar:"));
        panelContenido.add(txtCriterio);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAplicar = new JButton("Aplicar");
        JButton btnLimpiar = new JButton("Quitar Filtros");
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnAplicar);

        btnAplicar.addActionListener(evt -> {
            aplicarFiltroRegex(txtCriterio.getText().trim(), comboColumnas.getSelectedIndex());
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

    private void abrirInfoOperacion(int modelRow) {
        vista.dispose();
        InfoOperacion vInfo = new InfoOperacion();

        String idOp = vista.modeloTabla.getValueAt(modelRow, 0) != null ? vista.modeloTabla.getValueAt(modelRow, 0).toString() : "";
        String nombreCliente = vista.modeloTabla.getValueAt(modelRow, 1) != null ? vista.modeloTabla.getValueAt(modelRow, 1).toString() : "";
        String tipoOp = vista.modeloTabla.getValueAt(modelRow, 2) != null ? vista.modeloTabla.getValueAt(modelRow, 2).toString() : "";
        
        Object cellProducto = vista.modeloTabla.getValueAt(modelRow, 3);
        String nombreProducto = mapearNombreString(cellProducto);
        
        String tipoProducto = "Película";
        String lowerProd = nombreProducto.toLowerCase();
        if (lowerProd.contains("forza") || lowerProd.contains("spider-man") || lowerProd.contains("ps5") || lowerProd.contains("xbox") || lowerProd.contains("horizon") || lowerProd.contains("game")) {
            tipoProducto = "Videojuego";
        }

        String fechaOp = vista.modeloTabla.getValueAt(modelRow, 5) != null ? vista.modeloTabla.getValueAt(modelRow, 5).toString() : "";
        String costoOp = vista.modeloTabla.getValueAt(modelRow, 6) != null ? vista.modeloTabla.getValueAt(modelRow, 6).toString() : "";

        java.lang.reflect.Field[] fields = vInfo.getClass().getDeclaredFields();
        for (java.lang.reflect.Field f : fields) {
            if (f.getType() == JTextField.class) {
                f.setAccessible(true);
                String name = f.getName().toLowerCase();
                try {
                    JTextField txt = (JTextField) f.get(vInfo);
                    if (txt == null) continue;

                    if (name.contains("idop")) {
                        txt.setText(idOp);
                    } else if (name.equals("txtidcliente") || name.equals("txtidcli") || name.contains("idcliente")) {
                        txt.setText(""); 
                    } else if (name.contains("cliente") || name.equals("txtnombrecli") || name.contains("cli")) {
                        txt.setText(nombreCliente); 
                    } else if (name.contains("prod") || name.contains("nomprod") || name.equals("txtnombreproducto")) {
                        txt.setText(nombreProducto); 
                    } else if (name.equals("txttipo") || name.equals("txttipoprod") || name.contains("tipoproducto")) {
                        txt.setText(tipoProducto); 
                    } else if (name.contains("fecha")) {
                        txt.setText(fechaOp); 
                    } else if (name.contains("costo") || name.contains("monto") || name.contains("pagado")) {
                        txt.setText(costoOp);
                    }
                } catch (Exception ex) {
                    System.err.println("Error asignando campo: " + f.getName());
                }
            } else if (f.getType() == JRadioButton.class) {
                f.setAccessible(true);
                try {
                    JRadioButton rb = (JRadioButton) f.get(vInfo);
                    if (rb != null) {
                        if (rb.getText().equalsIgnoreCase("Renta") && tipoOp.equalsIgnoreCase("Renta")) {
                            rb.setSelected(true);
                        } else if (rb.getText().equalsIgnoreCase("Venta") && tipoOp.equalsIgnoreCase("Venta")) {
                            rb.setSelected(true);
                        }
                    }
                } catch (Exception e) {}
            }
        }

        vInfo.setImagenProducto(cellProducto);

        vInfo.btnAtras.addActionListener(e -> {
            vInfo.dispose();
            operaciones op = new operaciones();
            new OperacionesController(op);
            op.setVisible(true);
        });

        vInfo.lblInicio.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vInfo.dispose(); principal p = new principal(); new PrincipalController(p); p.setVisible(true); }
        });
        vInfo.lblClientes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vInfo.dispose(); clientes c = new clientes(); new ClienteController(c); c.setVisible(true); }
        });
        vInfo.lblVideojuegos.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vInfo.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true); }
        });
        vInfo.lblPeliculas.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vInfo.dispose(); peliculas p = new peliculas(); new PeliculasController(p); p.setVisible(true); }
        });
        vInfo.lblOperacion.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { vInfo.dispose(); operaciones op = new operaciones(); new OperacionesController(op); op.setVisible(true); }
        });

        vInfo.btnGuardar.addActionListener(e -> mostrarAlertaGris(vInfo, "Cambios guardados con éxito"));
        vInfo.btnDescargar.addActionListener(e -> mostrarAlertaGris(vInfo, "Generando ficha técnica..."));

        vInfo.setVisible(true);
    }

    private void mostrarAlertaGris(JFrame frame, String mensaje) {
        JDialog dialogo = new JDialog(frame, "Alerta", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280);
        dialogo.setLocationRelativeTo(frame);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(209, 209, 209));
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(25));
        
        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblMsg);

        panel.add(Box.createVerticalGlue());

        String rutaIcono = "";
        if (mensaje.toLowerCase().contains("éxito") || mensaje.toLowerCase().contains("guardados")) {
            rutaIcono = "/img/palomitaverde.png";
        } else if (mensaje.toLowerCase().contains("ficha") || mensaje.toLowerCase().contains("descargar") || mensaje.toLowerCase().contains("generando") || mensaje.toLowerCase().contains("lista")) {
            rutaIcono = "/img/simbolopdfblanco.png";
        }

        if (mensaje.toLowerCase().contains("selecciona")) {
            rutaIcono = "/img/mingcute_warning-fill.png";
        }

        if (!rutaIcono.isEmpty()) {
            try {
                java.net.URL urlImg = getClass().getResource(rutaIcono);
                if (urlImg != null) {
                    ImageIcon img = escalarImagenHD(new ImageIcon(urlImg).getImage(), 70, 70);
                    JLabel lblIcono = new JLabel(img);
                    lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.add(lblIcono);
                }
            } catch (Exception e) {
                System.err.println("Error cargando icono del pop-up: " + e.getMessage());
            }
        }

        panel.add(Box.createVerticalGlue());
        
        JButton btnOk = new JButton("Aceptar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 51, 102));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnOk.setPreferredSize(new Dimension(120, 35));
        btnOk.setMaximumSize(new Dimension(120, 35));
        btnOk.setMinimumSize(new Dimension(120, 35));
        btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 13));
        btnOk.setContentAreaFilled(false);
        btnOk.setBorderPainted(false);
        btnOk.setFocusPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialogo.dispose());
        
        panel.add(btnOk);
        panel.add(Box.createVerticalStrut(20));
        dialogo.add(panel);
        dialogo.setVisible(true);
    }
}
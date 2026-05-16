package controller;

import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

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
            public void mouseClicked(MouseEvent e) {}
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

        vista.btnFiltrar.addActionListener(e -> {
            abrirDialogoFiltrar();
        });

        ActionListener accionFiltrar = e -> {
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
        };

        vista.btnBuscar.addActionListener(accionFiltrar);

        vista.buscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
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
            }
        });

        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila != -1) {
                vista.mostrarConfirmacion("¿Seguro que quieres eliminar<br>esta operación?", eSi -> {
                    vista.modeloTabla.removeRow(vista.tabla.convertRowIndexToModel(fila));
                });
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

    private void abrirDialogoFiltrar() {
        JDialog dialogo = new JDialog(vista, "Filtro Operaciones", true);
        dialogo.setSize(320, 200);
        dialogo.setLocationRelativeTo(vista);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblColumna = new JLabel("Filtrar por columna:");
        String[] opcionesColumnas = {"ID Operación", "Cliente", "Tipo", "Producto", "Fecha", "Costo"};
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
            int indexColumnaTabla = comboColumnas.getSelectedIndex();

            if (criterio.isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                try {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + criterio, indexColumnaTabla));
                } catch (Exception ex) {
                    System.err.println("Error en la expresión de filtrado.");
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

    private void abrirInfoOperacion(int modelRow) {
        vista.dispose();
        InfoOperacion vInfo = new InfoOperacion();
        
        String[] componentesVista = {"txtIdOp", "txtCliente", "txtTipo", "txtProducto", "txtFecha", "txtCosto"};
        
        for (int i = 0; i < componentesVista.length; i++) {
            try {
                Object cellValue = vista.modeloTabla.getValueAt(modelRow, i);
                if (cellValue == null) {
                    continue;
                }
                
                String valorCelda = cellValue.toString();
                Field[] fields = vInfo.getClass().getDeclaredFields();
                boolean mapeado = false;
                
                for (Field f : fields) {
                    if (f.getType() == JTextField.class) {
                        String nombreCampo = f.getName().toLowerCase();
                        String buscado = componentesVista[i].toLowerCase().replace("txt", "");
                        
                        if (nombreCampo.equals(componentesVista[i].toLowerCase()) || 
                            nombreCampo.contains(buscado) || 
                            (buscado.equals("cliente") && nombreCampo.contains("cli"))) {
                            
                            f.setAccessible(true);
                            JTextField txt = (JTextField) f.get(vInfo);
                            if (txt != null) {
                                txt.setText(valorCelda);
                                mapeado = true;
                                break;
                            }
                        }
                    }
                }
                
                if (!mapeado) {
                    try {
                        Field fFallback = vInfo.getClass().getDeclaredField(componentesVista[i]);
                        fFallback.setAccessible(true);
                        JTextField txt = (JTextField) fFallback.get(vInfo);
                        if (txt != null) {
                            txt.setText(valorCelda);
                        }
                    } catch (NoSuchFieldException exFields) {
                        for (Field f : fields) {
                            if (f.getType() == JTextField.class && f.getName().toLowerCase().endsWith(componentesVista[i].toLowerCase().replace("txt", ""))) {
                                f.setAccessible(true);
                                JTextField txt = (JTextField) f.get(vInfo);
                                if (txt != null) {
                                    txt.setText(valorCelda);
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (Exception exM) {
                System.err.println("No se pudo mapear el campo: " + componentesVista[i]);
            }
        }

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
            
            vAdd.btnSeleccionarProd.addActionListener(e -> {
                vAdd.setVisible(false);
                
                SeleccionarProducto vSel = new SeleccionarProducto();
                
                vSel.btnAtras.addActionListener(ev -> {
                    vSel.dispose();
                    vAdd.setVisible(true);
                });
                
                vSel.btnInicio.addMouseListener(new MouseAdapter() {
                    @Override public void mouseClicked(MouseEvent ev) {
                        vSel.dispose(); vAdd.dispose(); principal p = new principal(); new PrincipalController(p); p.setVisible(true);
                    }
                });
                vSel.btnClientes.addMouseListener(new MouseAdapter() {
                    @Override public void mouseClicked(MouseEvent ev) {
                        vSel.dispose(); vAdd.dispose(); clientes c = new clientes(); new ClienteController(c); c.setVisible(true);
                    }
                });
                vSel.btnVideojuegos.addMouseListener(new MouseAdapter() {
                    @Override public void mouseClicked(MouseEvent ev) {
                        vSel.dispose(); vAdd.dispose(); videojuegos v = new videojuegos(); new VideojuegosController(v); v.setVisible(true);
                    }
                });
                vSel.btnPeliculas.addMouseListener(new MouseAdapter() {
                    @Override public void mouseClicked(MouseEvent ev) {
                        vSel.dispose(); vAdd.dispose(); peliculas p = new peliculas(); new PeliculasController(p); p.setVisible(true);
                    }
                });
                vSel.btnOperacion.addMouseListener(new MouseAdapter() {
                    @Override public void mouseClicked(MouseEvent ev) {
                        vSel.dispose(); vAdd.dispose(); operaciones op = new operaciones(); new OperacionesController(op); op.setVisible(true);
                    }
                });

                vSel.btnAgregar.addActionListener(ev -> {
                    vSel.dispose();
                    vAdd.dispose();
                    AñadirOperacion nuevaAdd = new AñadirOperacion();
                    new AñadirOperacionController(nuevaAdd);
                    nuevaAdd.setVisible(true);
                });

                ActionListener filtrarSeleccion = ev -> {
                    String texto = vSel.buscador.getText().trim();
                    if (texto.isEmpty()) {
                        vSel.sorter.setRowFilter(null);
                    } else {
                        try {
                            vSel.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                        } catch (java.util.regex.PatternSyntaxException ex) {
                            System.err.println("Error de búsqueda: " + ex.getMessage());
                        }
                    }
                };

                vSel.btnBuscar.addActionListener(filtrarSeleccion);

                vSel.buscador.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent ev) {
                        String texto = vSel.buscador.getText().trim();
                        if (texto.isEmpty()) {
                            vSel.sorter.setRowFilter(null);
                        } else {
                            try {
                                vSel.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                            } catch (java.util.regex.PatternSyntaxException ex) {
                                System.err.println("Error de búsqueda: " + ex.getMessage());
                            }
                        }
                    }
                });

                vSel.btnEliminar.addActionListener(ev -> {
                    int fila = vSel.tabla.getSelectedRow();
                    if (fila != -1) {
                        vSel.mostrarConfirmacion("¿Seguro que quieres eliminar<br>este producto?", eSi -> {
                            vSel.modeloTabla.removeRow(vSel.tabla.convertRowIndexToModel(fila));
                        });
                    }
                });

                vSel.tabla.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent ev) {
                        int fila = vSel.tabla.rowAtPoint(ev.getPoint());
                        
                        if (fila != -1) {
                            if (ev.getClickCount() == 2) {
                                int modelRow = vSel.tabla.convertRowIndexToModel(fila);
                                
                                String idProducto = vSel.modeloTabla.getValueAt(modelRow, 0).toString();
                                String cliente = vSel.modeloTabla.getValueAt(modelRow, 1).toString();
                                String tipoOperacion = vSel.modeloTabla.getValueAt(modelRow, 2).toString();
                                String plataforma = vSel.modeloTabla.getValueAt(modelRow, 5).toString();
                                
                                String nombreProducto = "";
                                ImageIcon imagenProducto = null;
                                
                                Object valueCaratula = vSel.modeloTabla.getValueAt(modelRow, 0);
                                if (valueCaratula instanceof ImageIcon) {
                                    imagenProducto = (ImageIcon) valueCaratula;
                                }
                                
                                Object value = vSel.modeloTabla.getValueAt(modelRow, 3);
                                if (value instanceof ImageIcon) {
                                    if (imagenProducto == null) {
                                        imagenProducto = (ImageIcon) value;
                                    }
                                    String iconStr = value.toString();
                                    if (iconStr.contains("forza") || iconStr.contains("horizon") || iconStr.contains("game")) {
                                        nombreProducto = "Forza Horizon 6";
                                    } else if (iconStr.contains("71fw9QnEQUL")) {
                                        nombreProducto = "Spider-Man 2 PS5";
                                    } else if (iconStr.contains("71w58zkWnfL")) {
                                        nombreProducto = "Oppenheimer";
                                    } else if (iconStr.contains("51gz5Gfjl8L")) {
                                        nombreProducto = "Interstellar";
                                    } else if (iconStr.contains("71MZBMmOXtL")) {
                                        nombreProducto = "Avatar: The Way of Water";
                                    } else {
                                        nombreProducto = "Producto Multimedia";
                                    }
                                } else if (value instanceof Object[]) {
                                    Object[] data = (Object[]) value;
                                    if (data[0] instanceof ImageIcon && imagenProducto == null) {
                                        imagenProducto = (ImageIcon) data[0];
                                    }
                                    nombreProducto = (String) data[1];
                                } else if (value != null) {
                                    nombreProducto = value.toString();
                                }

                                String tipoProd = "";
                                Object tipoValue = vSel.modeloTabla.getValueAt(modelRow, 4);
                                if (tipoValue instanceof Object[]) {
                                    Object[] dataTipo = (Object[]) tipoValue;
                                    tipoProd = dataTipo.length > 1 ? dataTipo[1].toString() : dataTipo[0].toString();
                                } else if (tipoValue != null) {
                                    tipoProd = tipoValue.toString();
                                }

                                vAdd.txtNombreCli.setText(cliente);
                                vAdd.txtNomProd.setText(nombreProducto);
                                vAdd.txtIdProd.setText(idProducto);
                                vAdd.txtTipoProd.setText(tipoProd);

                                if (vAdd.txtPlataforma != null) {
                                    vAdd.txtPlataforma.setText(plataforma);
                                }

                                if (tipoOperacion.equalsIgnoreCase("Renta")) {
                                    vAdd.rbRenta.setSelected(true);
                                } else {
                                    vAdd.rbVenta.setSelected(true);
                                }
                                
                                String[] camposAdd = {"txtProducto", "txtNombreProducto", "txtTipo"};
                                String[] valoresAdd = {nombreProducto, nombreProducto, tipoOperacion};
                                
                                for (int i = 0; i < camposAdd.length; i++) {
                                    try {
                                        Field f = vAdd.getClass().getDeclaredField(camposAdd[i]);
                                        f.setAccessible(true);
                                        ((JTextField) f.get(vAdd)).setText(valoresAdd[i]);
                                    } catch (Exception eEx) {}
                                }

                                try {
                                    Field panelFotoField = null;
                                    String[] opcionesFotos = {"panelFoto", "lblFoto", "lblImagen"};
                                    for (String opt : opcionesFotos) {
                                        try {
                                            panelFotoField = vAdd.getClass().getDeclaredField(opt);
                                            break;
                                        } catch (Exception e) {}
                                    }
                                    
                                    if (panelFotoField != null) {
                                        panelFotoField.setAccessible(true);
                                        Object componenteFoto = panelFotoField.get(vAdd);
                                        if (componenteFoto instanceof JLabel && imagenProducto != null) {
                                            JLabel lbl = (JLabel) componenteFoto;
                                            Image imgEscalag = imagenProducto.getImage().getScaledInstance(310, 260, Image.SCALE_SMOOTH);
                                            lbl.setIcon(new ImageIcon(imgEscalag));
                                            lbl.setText("");
                                        } else if (componenteFoto instanceof JPanel && imagenProducto != null) {
                                            JPanel pnl = (JPanel) componenteFoto;
                                            pnl.removeAll();
                                            pnl.setLayout(new BorderLayout());
                                            JLabel lbl = new JLabel();
                                            Image imgEscalag = imagenProducto.getImage().getScaledInstance(310, 260, Image.SCALE_SMOOTH);
                                            lbl.setIcon(new ImageIcon(imgEscalag));
                                            lbl.setHorizontalAlignment(SwingConstants.CENTER);
                                            pnl.add(lbl, BorderLayout.CENTER);
                                            pnl.revalidate();
                                            pnl.repaint();
                                        }
                                    }
                                } catch (Exception eEx) {}

                                vSel.dispose();
                                vAdd.setVisible(true);
                            }
                        }
                    }
                });

                vSel.setVisible(true);
            });

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
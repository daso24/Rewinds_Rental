package controller;

import models.OperacionModel;
import models.ArbolBinarioBusqueda;
import view.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OperacionesController
{
    private operaciones vista;
    private AñadirOperacion vistaAdd;
    private SeleccionarProducto vistaSel;
    private OperacionModel modelo;
    private Object[] productoSeleccionado = null;
    private List<Object[]> listaOperaciones = null;
    private ArbolBinarioBusqueda arbolOperaciones;

    public OperacionesController(operaciones vista)
    {
        this.vista = vista;
        this.modelo = new OperacionModel();
        this.arbolOperaciones = new ArbolBinarioBusqueda();
        configurarRenderizadoTabla();
        initEvents();
        cargarTablaOperaciones();
    }

    public OperacionesController(AñadirOperacion vistaAdd)
    {
        this.vistaAdd = vistaAdd;
        this.modelo = new OperacionModel();
        initEventsAdd();
        configurarFechas();
    }

    public OperacionesController(SeleccionarProducto vistaSel, AñadirOperacion vistaAddPrevia)
    {
        this.vistaSel = vistaSel;
        this.vistaAdd = vistaAddPrevia;
        this.modelo = new OperacionModel();
        initEventsSel();
        cargarTablaProductos();
    }

    private void cargarTablaOperaciones()
    {
        if (vista != null)
        {
            vista.modeloTabla.setRowCount(0);
            listaOperaciones = modelo.obtenerOperaciones();
            this.arbolOperaciones = new ArbolBinarioBusqueda();
            
            Icon iconInfo = getImgPequeño("/img/Vector.png");
            Icon iconGame = getImgPequeño("/img/carbon_game-console.png");
            Icon iconMovie = getImgPequeño("/img/fluent_movies-and-tv-16-filled.png");
            Image defaultUserImage = new ImageIcon(getClass().getResource("/img/placeholder_usuario.png")).getImage();

            for (Object[] op : listaOperaciones)
            {
                String cliente = (String) op[1];
                arbolOperaciones.insertar(cliente, op); 
                
                String tipoOp = (String) op[2];
                String tipoProd = (String) op[3];
                String titulo = (String) op[4];
                int idProd = (int) op[7];
                String rutaFotoCliente = (op.length > 13) ? (String) op[13] : null;

                Icon iconTipo = tipoProd.equals("Videojuego") ? iconGame : iconMovie;
                Icon iconUser = null;

                if (rutaFotoCliente != null && !rutaFotoCliente.trim().isEmpty())
                {
                    try {
                        ImageIcon iconOriginal = rutaFotoCliente.startsWith("/") ? new ImageIcon(getClass().getResource(rutaFotoCliente)) : new ImageIcon(rutaFotoCliente);
                        iconUser = escalarImagenHD(iconOriginal.getImage(), 35, 35);
                    } catch (Exception e) { iconUser = escalarImagenHD(defaultUserImage, 35, 35); }
                } else { iconUser = escalarImagenHD(defaultUserImage, 35, 35); }

                vista.modeloTabla.addRow(new Object[]{ false, new Object[]{iconUser, cliente}, tipoOp, titulo, new Object[]{iconTipo, tipoProd}, "ID: " + idProd, new Object[]{iconInfo, "Ver info"} });
            }
        }
    }

    private void cargarTablaProductos()
    {
        if (vistaSel != null)
        {
            vistaSel.modeloTabla.setRowCount(0);
            List<Object[]> lista = modelo.obtenerTodosLosProductos();

            for (Object[] prod : lista)
            {
                int id = (int) prod[0];
                String titulo = (String) prod[1];
                String tipo = (String) prod[2];
                String plataforma = (String) prod[3];
                String fotoRuta = (String) prod[6];
                Icon iconPortada = (fotoRuta != null && !fotoRuta.isEmpty()) ? getImg(fotoRuta) : null;
                vistaSel.modeloTabla.addRow(new Object[]{ false, "Producto ID: " + id, "-", iconPortada, tipo, plataforma, titulo, "Elegir" });
            }
        }
    }

    private void initEvents()
    {
        vista.btnAtras.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { abrirMenuPrincipalDesdeVista(); }});
        vista.btnInicio.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { abrirMenuPrincipalDesdeVista(); }});

        vista.btnClientes.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vista.dispose(); clientes vCli = new clientes(); new ClienteController(vCli); vCli.setVisible(true); }});
        vista.btnVideojuegos.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vista.dispose(); videojuegos vVid = new videojuegos(); new VideojuegosController(vVid); vVid.setVisible(true); }});
        vista.btnPeliculas.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { vista.dispose(); peliculas vPel = new peliculas(); new PeliculasController(vPel); vPel.setVisible(true); }});

        vista.btnAgregar.addActionListener(e -> {
            vista.dispose();
            AñadirOperacion addVista = new AñadirOperacion();
            new OperacionesController(addVista);
            addVista.setVisible(true);
        });

        vista.btnBuscar.addActionListener(e -> {
            String texto = vista.buscador.getText().trim();
            if (texto.isEmpty()) {
                cargarTablaOperaciones();
            } else {
                List<Object[]> opsEncontradas = arbolOperaciones.buscarParcial(texto);
                vista.modeloTabla.setRowCount(0);
                
                if (!opsEncontradas.isEmpty()) {
                    Icon iconInfo = getImgPequeño("/img/Vector.png");
                    Icon iconGame = getImgPequeño("/img/carbon_game-console.png");
                    Icon iconMovie = getImgPequeño("/img/fluent_movies-and-tv-16-filled.png");
                    Image defaultUserImage = new ImageIcon(getClass().getResource("/img/placeholder_usuario.png")).getImage();
                    
                    for (Object[] opEncontrada : opsEncontradas) {
                        String cliente = (String) opEncontrada[1];
                        String tipoOp = (String) opEncontrada[2];
                        String tipoProd = (String) opEncontrada[3];
                        String titulo = (String) opEncontrada[4];
                        int idProd = (int) opEncontrada[7];
                        String rutaFotoCliente = (opEncontrada.length > 13) ? (String) opEncontrada[13] : null;

                        Icon iconTipo = tipoProd.equals("Videojuego") ? iconGame : iconMovie;
                        Icon iconUser = null;

                        if (rutaFotoCliente != null && !rutaFotoCliente.trim().isEmpty()) {
                            try {
                                ImageIcon iconOriginal = rutaFotoCliente.startsWith("/") ? new ImageIcon(getClass().getResource(rutaFotoCliente)) : new ImageIcon(rutaFotoCliente);
                                iconUser = escalarImagenHD(iconOriginal.getImage(), 35, 35);
                            } catch (Exception ex) { iconUser = escalarImagenHD(defaultUserImage, 35, 35); }
                        } else { iconUser = escalarImagenHD(defaultUserImage, 35, 35); }
                        
                        vista.modeloTabla.addRow(new Object[]{ false, new Object[]{iconUser, cliente}, tipoOp, titulo, new Object[]{iconTipo, tipoProd}, "ID: " + idProd, new Object[]{iconInfo, "Ver info"} });
                    }
                } else {
                    mostrarAlertaFigma(vista, "No se encontró ninguna operación.", false);
                }
            }
        });

        vista.buscador.addKeyListener(new KeyAdapter() { 
            @Override public void keyReleased(KeyEvent e) { 
                if (vista.buscador.getText().trim().isEmpty()) cargarTablaOperaciones(); 
            }
        });

        vista.btnEliminar.addActionListener(e -> {
            boolean haySeleccion = false;
            for (int i = 0; i < vista.tabla.getRowCount(); i++) {
                Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                if (isSelected != null && isSelected) { haySeleccion = true; break; }
            }
            if (haySeleccion) {
                mostrarConfirmacionFigma(vista, "¿Está seguro de borrar las<br>operaciones seleccionadas?", "Esta acción no se puede deshacer", eSi -> {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                        Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                        if (isSelected != null && isSelected) {
                            int modelIndex = vista.tabla.convertRowIndexToModel(i);
                            Object cellVal = vista.tabla.getValueAt(i, 1);
                            String cliente = (cellVal instanceof Object[]) ? ((Object[]) cellVal)[1].toString() : cellVal.toString();
                            vista.modeloTabla.removeRow(modelIndex);
                            arbolOperaciones.eliminar(cliente);
                        }
                    }
                });
            } else {
                mostrarAlertaFigma(vista, "Selecciona al menos una operación de la lista.", false);
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int col = vista.tabla.columnAtPoint(e.getPoint());
                if (fila != -1 && vista.tabla.convertColumnIndexToModel(col) == 6) {
                    abrirInfoOperacion(vista.tabla.convertRowIndexToModel(fila));
                }
            }
        });
    }

    private void initEventsAdd()
    {
        vistaAdd.btnAtras.addActionListener(e -> regresarAOperaciones());
        vistaAdd.btnSeleccionarProd.addActionListener(e -> {
            vistaAdd.setVisible(false);
            SeleccionarProducto vSel = new SeleccionarProducto();
            new OperacionesController(vSel, vistaAdd);
            vSel.setVisible(true);
        });
        vistaAdd.rbRenta.addActionListener(e -> recalcularPrecios());
        vistaAdd.rbVenta.addActionListener(e -> recalcularPrecios());
        vistaAdd.btnGuardar.addActionListener(e -> {
            if (vistaAdd.txtIdCli.getText().isEmpty() || vistaAdd.txtIdProd.getText().isEmpty()) {
                mostrarAlertaFigma(vistaAdd, "Existen campos obligatorios vacíos.", false);
                return;
            }
            try {
                int idCli = Integer.parseInt(vistaAdd.txtIdCli.getText().trim());
                String tipoOp = vistaAdd.rbVenta.isSelected() ? "Venta" : "Renta";
                String tipoProd = vistaAdd.txtTipoProd.getText();
                int idProd = Integer.parseInt(vistaAdd.txtIdProd.getText().trim());
                String fechaOp = vistaAdd.txtFechaOp.getText();
                String fechaDev = vistaAdd.txtFechaDev.getText();
                double monto = Double.parseDouble(vistaAdd.txtMonto.getText().replace("$", "").trim());

                if (modelo.registrarOperacion(idCli, tipoOp, tipoProd, idProd, fechaOp, fechaDev, monto)) {
                    mostrarAlertaFigma(vistaAdd, "Operación guardada con éxito.", true);
                    regresarAOperaciones();
                } else {
                    mostrarAlertaFigma(vistaAdd, "Error de registro o stock insuficiente.", false);
                }
            } catch (Exception ex) {
                mostrarAlertaFigma(vistaAdd, "El ID de cliente debe ser numérico.", false);
            }
        });
    }

    private void initEventsSel() {
        vistaSel.btnAtras.addActionListener(e -> { vistaSel.dispose(); vistaAdd.setVisible(true); });
        vistaSel.btnBuscar.addActionListener(e -> {
            String texto = vistaSel.buscador.getText().trim();
            if (texto.isEmpty()) vistaSel.sorter.setRowFilter(null);
            else vistaSel.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        });
        vistaSel.tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (vistaSel.tabla.columnAtPoint(e.getPoint()) == 7) {
                    Object[] prodElegido = modelo.obtenerTodosLosProductos().get(vistaSel.tabla.convertRowIndexToModel(vistaSel.tabla.rowAtPoint(e.getPoint())));
                    vistaAdd.txtIdProd.setText(String.valueOf(prodElegido[0]));
                    vistaAdd.txtNomProd.setText((String)prodElegido[1]);
                    vistaAdd.txtTipoProd.setText((String)prodElegido[2]);
                    try { vistaAdd.setImagenProducto(prodElegido[6]); } catch (Exception ex) {}
                    productoSeleccionado = prodElegido;
                    recalcularPrecios();
                    vistaSel.dispose();
                    vistaAdd.setVisible(true);
                }
            }
        });
    }

    private void recalcularPrecios() {
        if (productoSeleccionado != null) {
            double pVenta = (double) productoSeleccionado[4];
            double pRenta = (double) productoSeleccionado[5];
            vistaAdd.txtMonto.setText(vistaAdd.rbVenta.isSelected() ? "$" + pVenta : "$" + pRenta);
        }
    }

    private void configurarFechas() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        vistaAdd.txtFechaOp.setText(sdf.format(new Date()));
        vistaAdd.txtFechaDev.setText("No aplica");
    }

    private void regresarAOperaciones() {
        vistaAdd.dispose();
        operaciones op = new operaciones();
        new OperacionesController(op);
        op.setVisible(true);
    }

    private void abrirMenuPrincipalDesdeVista() {
        vista.dispose();
        principal vPrin = new principal();
        new PrincipalController(vPrin); 
        vPrin.setVisible(true);
    }

    private void abrirInfoOperacion(int modelRow) {
        if (listaOperaciones == null || modelRow < 0 || modelRow >= listaOperaciones.size()) return;
        Object[] op = listaOperaciones.get(modelRow);
        vista.dispose();
        InfoOperacion vInfo = new InfoOperacion();
        try {
            vInfo.txtIdOp.setText(String.valueOf(op[0]));
            vInfo.txtNombreCli.setText((String)op[1]);
            vInfo.txtIdCli.setText(String.valueOf(op[11])); 
            vInfo.txtNombreProd.setText((String)op[4]);
            vInfo.txtIdProd.setText(String.valueOf(op[7]));
            vInfo.txtTipoProd.setText((String)op[3]);
            vInfo.txtFechaOp.setText((String)op[8]);
            vInfo.txtFechaDev.setText(op[9] != null ? (String)op[9] : "No aplica"); 
            vInfo.txtMonto.setText("$" + op[10]); 
            if (op[2].toString().equalsIgnoreCase("Renta")) vInfo.rbRenta.setSelected(true);
            else vInfo.rbVenta.setSelected(true);
            if (op[5] != null) vInfo.setImagenProducto((String)op[5]);
            vInfo.setModoEdicion(false);
            
            vInfo.btnDescargar.addActionListener(e -> {
                utils.PDFGenerator.generarFichaOperacion(vInfo.txtIdOp.getText(), vInfo.txtNombreCli.getText(), vInfo.rbRenta.isSelected() ? "Renta" : "Venta", vInfo.txtMonto.getText());
            });
        } catch (Exception ex) {}

        vInfo.btnEditar.addActionListener(e -> vInfo.setModoEdicion(true));
        vInfo.btnGuardar.addActionListener(e -> {
            if (!vInfo.enModoEdicion) { vInfo.setModoEdicion(true); }
            else {
                mostrarConfirmacionFigma(vInfo, "¿Estás seguro que quieres<br>modificar esta operación?", "Esta acción no se puede deshacer", eSi -> {
                    try {
                        int idRenta = Integer.parseInt(vInfo.txtIdOp.getText());
                        int idCli = Integer.parseInt(vInfo.txtIdCli.getText());
                        int idProd = Integer.parseInt(vInfo.txtIdProd.getText());
                        double monto = Double.parseDouble(vInfo.txtMonto.getText().replace("$", "").trim());
                        String tipo = vInfo.rbRenta.isSelected() ? "Renta" : "Venta";
                        if (modelo.actualizarOperacion(idRenta, idCli, tipo, vInfo.txtTipoProd.getText(), idProd, vInfo.txtFechaOp.getText(), vInfo.txtFechaDev.getText(), monto)) {
                            mostrarAlertaFigma(vInfo, "Se ha modificado correctamente.", true);
                            vInfo.setModoEdicion(false);
                        }
                    } catch (Exception ex) { mostrarAlertaFigma(vInfo, "Datos inválidos", false); }
                });
            }
        });
        vInfo.btnAtras.addActionListener(e -> { vInfo.dispose(); operaciones o = new operaciones(); new OperacionesController(o); o.setVisible(true); });
        vInfo.setVisible(true);
    }

    private void configurarRenderizadoTabla() {
        if (vista != null && vista.tabla != null) {
            vista.tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
                @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                    setText(v != null ? v.toString() : ""); return this;
                }
            });
        }
    }

    private ImageIcon getImg(String ruta) {
        try {
            java.net.URL url = getClass().getResource(ruta);
            if (url != null) return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
            java.io.File archivo = new java.io.File(ruta);
            if (archivo.exists()) return new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        } catch (Exception e) {}
        return null;
    }

    private ImageIcon getImgPequeño(String ruta) {
        try {
            java.net.URL url = getClass().getResource(ruta);
            if (url != null) return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } catch (Exception e) {}
        return null;
    }

    private ImageIcon escalarImagenHD(Image srcImg, int targetW, int targetH) {
        if (srcImg == null) return null;
        BufferedImage img = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gNormal = img.createGraphics();
        gNormal.drawImage(srcImg, 0, 0, null);
        gNormal.dispose();
        return new ImageIcon(img.getScaledInstance(targetW, targetH, Image.SCALE_SMOOTH));
    }

    private void mostrarAlertaFigma(JFrame frame, String mensaje, boolean exito) {
        JDialog d = new JDialog(frame, true);
        d.setUndecorated(true); d.setSize(350, 240); d.setLocationRelativeTo(frame);
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        JPanel pCenter = new JPanel(); pCenter.setOpaque(false); pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
        pCenter.add(Box.createVerticalStrut(20));
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(exito ? "/img/palomitaverde.png" : "/img/mingcute_warning-fill.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
            JLabel lblIcon = new JLabel(icon); lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT); pCenter.add(lblIcon);
        } catch(Exception e) {}
        pCenter.add(Box.createVerticalStrut(15));
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>"); lblMsg.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT); pCenter.add(lblMsg);
        p.add(pCenter, BorderLayout.CENTER);
        JPanel pSouth = new JPanel(); pSouth.setOpaque(false); pSouth.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        JButton btnOk = new JButton("Aceptar"); btnOk.setPreferredSize(new Dimension(150, 35)); btnOk.setForeground(Color.WHITE);
        btnOk.setBackground(new Color(0, 180, 255)); btnOk.addActionListener(e -> d.dispose()); pSouth.add(btnOk); p.add(pSouth, BorderLayout.SOUTH);
        d.add(p); d.setVisible(true);
    }

    private void mostrarConfirmacionFigma(JFrame frame, String mensaje, String submensaje, ActionListener accionSi) {
        JDialog d = new JDialog(frame, true); d.setUndecorated(true); d.setSize(380, 240); d.setLocationRelativeTo(frame);
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(Color.WHITE); p.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        JPanel pCenter = new JPanel(); pCenter.setOpaque(false); pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
        pCenter.add(Box.createVerticalStrut(20));
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>"); lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT); pCenter.add(lblMsg); pCenter.add(Box.createVerticalStrut(15));
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            JLabel lblIcon = new JLabel(icon); lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT); pCenter.add(lblIcon);
        } catch(Exception e) {}
        pCenter.add(Box.createVerticalStrut(15));
        JLabel lblSub = new JLabel(submensaje); lblSub.setFont(new Font("Inter", Font.PLAIN, 12)); lblSub.setAlignmentX(Component.CENTER_ALIGNMENT); pCenter.add(lblSub);
        p.add(pCenter, BorderLayout.CENTER);
        JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15)); pSouth.setOpaque(false);
        JButton btnCancelar = new JButton("Cancelar"); btnCancelar.setPreferredSize(new Dimension(120, 35)); btnCancelar.addActionListener(e -> d.dispose());
        JButton btnAceptar = new JButton("Aceptar"); btnAceptar.setPreferredSize(new Dimension(120, 35)); btnAceptar.setBackground(new Color(255, 87, 34));
        btnAceptar.addActionListener(e -> { d.dispose(); accionSi.actionPerformed(e); });
        pSouth.add(btnCancelar); pSouth.add(btnAceptar); p.add(pSouth, BorderLayout.SOUTH);
        d.add(p); d.setVisible(true);
    }
}
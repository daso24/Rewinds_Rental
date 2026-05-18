package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class InfoJuego extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAtras, btnDescargar, btnEditar;
    public JTextField txtNombreProd, txtIdProd, txtPlataforma, txtGenero, txtStock;
    public JTextField txtTipoProd, txtPrecioVenta, txtPrecioRenta, txtDescuento, txtClasificacion, txtAnio, txtStockRenta;
    public JLabel lblImg;

    public InfoJuego() {
        setTitle("Información de Videojuego");
        setMinimumSize(new Dimension(1000, 700));
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo3.png")));
        } catch (Exception e) {}

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(160, 0));
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(sidebar, BorderLayout.WEST);

        btnInicio = Menu(sidebar, "Inicio", "/img/casaazul.png");
        btnOperacion = Menu(sidebar, "Operación", "/img/simbolomasazul.png");
        btnClientes = Menu(sidebar, "Clientes", "/img/simboloclientesazul.png");
        btnVideojuegos = Menu(sidebar, "Videojuegos", "/img/simbolovideojuegosazul.png");
        btnPeliculas = Menu(sidebar, "Peliculas", "/img/simbolopeliculasazul.png");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        btnAtras = new RoundedButton("Atrás  ", 20);
        btnAtras.setPreferredSize(new Dimension(120, 35));
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAtras.setBackground(new Color(225, 225, 225));
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        header.add(btnAtras, BorderLayout.WEST);

        JLabel titulo = new JLabel("Información de videojuego", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        header.add(titulo, BorderLayout.CENTER);

        JLabel logoDerecha = new JLabel();
        logoDerecha.setPreferredSize(new Dimension(80, 60));
        cargarIconoLabel(logoDerecha, "/img/logo3.png", 80, 60);
        header.add(logoDerecha, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.0;
        mainPanel.add(header, gbc);

        JPanel panelGris = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

                g2.dispose();
            }
        };

        panelGris.setOpaque(false);
        panelGris.setPreferredSize(new Dimension(800, 520));
        panelGris.setMinimumSize(new Dimension(800, 520));
        
        txtNombreProd = crearCampo(panelGris, "Nombre del producto:", 30, 20, 180);
        txtIdProd = crearCampo(panelGris, "ID del producto:", 30, 80, 180);
        txtTipoProd = crearCampo(panelGris, "Tipo de producto:", 30, 140, 180);
        txtPlataforma = crearCampo(panelGris, "Plataforma:", 30, 200, 180);
        txtPrecioVenta = crearCampo(panelGris, "Precio de venta:", 30, 260, 180);
        txtDescuento = crearCampo(panelGris, "Descuento:", 30, 320, 180);
        txtStock = crearCampo(panelGris, "Disponibles para venta:", 30, 390, 180);
        txtStockRenta = crearCampo(panelGris, "Disponibles para renta:", 250, 390, 180);
        txtPrecioRenta = crearCampo(panelGris, "Precio de renta (por 14 días):", 250, 260, 180);

        JLabel lblProductoImg = new JLabel("Producto:");
        lblProductoImg.setBounds(500, 20, 100, 20);
        lblProductoImg.setFont(new Font("Arial", Font.BOLD, 14));
        panelGris.add(lblProductoImg);

        lblImg = new JLabel();
        lblImg.setBounds(500, 45, 260, 280);
        lblImg.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        lblImg.setBackground(Color.LIGHT_GRAY);
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        lblImg.setOpaque(true);
        panelGris.add(lblImg);

        txtClasificacion = crearCampo(panelGris, "Clasificación:", 500, 335, 120);
        txtAnio = crearCampo(panelGris, "Año de lanzamiento:", 640, 335, 120);
        txtGenero = crearCampo(panelGris, "Género:", 500, 390, 260);

        btnEditar = new RoundedButton("Editar info de videojuego", 10);
        btnEditar.setBounds(30, 465, 230, 35);
        btnEditar.setBackground(new Color(45, 59, 72));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelGris.add(btnEditar);

        btnDescargar = new RoundedButton("Descargar ficha  ", 10);
        btnDescargar.setBounds(340, 465, 200, 35);
        btnDescargar.setBackground(new Color(0, 180, 255));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cargarIconoBoton(btnDescargar, "/img/simbolopdfblanco.png", 22, 22);
        panelGris.add(btnDescargar);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(-5, 10, 60, 10);
        mainPanel.add(panelGris, gbc);
    }

    public InfoJuego setDatosJuego(String nombre, String id, String tipo, String plataforma, String precioVenta, String descuento, String stockVenta, String stockRenta, String precioRenta, String clasificacion, String anio, String genero, String caratula) {
        txtNombreProd.setText(nombre);
        txtIdProd.setText(id);
        txtTipoProd.setText(tipo);
        txtPlataforma.setText(plataforma);
        txtPrecioVenta.setText(precioVenta);
        txtDescuento.setText(descuento);
        txtStock.setText(stockVenta);
        txtStockRenta.setText(stockRenta);
        txtPrecioRenta.setText(precioRenta);
        txtClasificacion.setText(clasificacion);
        txtAnio.setText(anio);
        txtGenero.setText(genero);
        
        cargarImagenPortada(caratula);
        return this;
    }

    private void cargarImagenPortada(String caratula) {
        try {
            if (caratula == null || caratula.trim().isEmpty()) {
                lblImg.setIcon(null);
                lblImg.setText("Sin portada disponible");
                return;
            }

            String nombreArchivo = caratula.trim();
            
            if (nombreArchivo.contains("/img/")) {
                int index = nombreArchivo.indexOf("/img/");
                nombreArchivo = nombreArchivo.substring(index + 5);
            } else if (nombreArchivo.contains("img/")) {
                int index = nombreArchivo.indexOf("img/");
                nombreArchivo = nombreArchivo.substring(index + 4);
            }
            
            nombreArchivo = nombreArchivo.replace("\"", "")
                                         .replace("'", "")
                                         .trim();

            String rutaFinal = "/img/" + nombreArchivo;
            URL urlImg = getClass().getResource(rutaFinal);
            
            if (urlImg != null) {
                ImageIcon portada = new ImageIcon(new ImageIcon(urlImg).getImage().getScaledInstance(260, 280, Image.SCALE_SMOOTH));
                lblImg.setIcon(portada);
                lblImg.setText("");
            } else {
                lblImg.setIcon(null);
                lblImg.setText("<html><center>No se encontró:<br>" + nombreArchivo + "</center></html>");
                lblImg.setFont(new Font("Arial", Font.ITALIC, 11));
            }
        } catch (Exception e) {
            lblImg.setIcon(null);
            lblImg.setText("Error al cargar imagen");
        }
    }

    public void mostrarAvisoDescarga(String mensaje) {
        mostrarPopUpGris(mensaje, new Color(130, 130, 130), "/img/mingcute_warning-fill.png", null);
    }

    public void mostrarExito(String mensaje) {
        mostrarPopUpGris(mensaje, new Color(50, 180, 50), "/img/palomitaverde.png", null);
    }

    public void mostrarConfirmacion(String mensaje, ActionListener accionSi) {
        mostrarPopUpGris(mensaje, new Color(0, 51, 102), "/img/palomitaverde.png", accionSi);
    }

    private void mostrarPopUpGris(String mensaje, Color colorBoton, String rutaIcono, ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280);
        dialogo.setLocationRelativeTo(this);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        
        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.add(Box.createVerticalStrut(25));
        
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblMsg);
        contenido.add(Box.createVerticalGlue());
        
        try {
            ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel lblIcono = new JLabel(img);
            lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
            contenido.add(lblIcono);
        } catch (Exception e) {}
        
        contenido.add(Box.createVerticalGlue());
        
        JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        pBotones.setOpaque(false);

        if (accionSi != null) {
            JButton btnSi = new JButton("Confirmar");
            btnSi.setPreferredSize(new Dimension(110, 35));
            btnSi.setBackground(colorBoton);
            btnSi.setForeground(Color.WHITE);
            btnSi.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnSi.addActionListener(e -> {
                accionSi.actionPerformed(e);
                dialogo.dispose();
            });
            
            JButton btnNo = new JButton("Cancelar");
            btnNo.setPreferredSize(new Dimension(110, 35));
            btnNo.setBackground(new Color(150, 150, 150));
            btnNo.setForeground(Color.WHITE);
            btnNo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnNo.addActionListener(e -> dialogo.dispose());
            
            pBotones.add(btnSi);
            pBotones.add(btnNo);
        } else {
            JButton btnOk = new JButton("Aceptar");
            btnOk.setPreferredSize(new Dimension(120, 35));
            btnOk.setBackground(colorBoton);
            btnOk.setForeground(Color.WHITE);
            btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnOk.addActionListener(e -> dialogo.dispose());
            pBotones.add(btnOk);
        }
        
        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    public JLabel Menu(JPanel panel, String texto, String ruta) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));
        JLabel iconLabel = new JLabel();
        try { 
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH))); 
        } catch (Exception e) {}
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(new Color(4, 180, 255)); 
        label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.add(iconLabel); 
        item.add(Box.createVerticalStrut(5)); 
        item.add(label);
        panel.add(item);
        return label;
    }

    private JTextField crearCampo(JPanel p, String titulo, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo);
        lbl.setBounds(x, y, w + 50, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        p.add(lbl);
        JTextField tf = new JTextField();
        tf.setBounds(x, y + 20, w, 30);
        tf.setEditable(false);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(tf);
        return tf;
    }

    private void cargarIconoLabel(JLabel l, String p, int w, int h) {
        try {
            URL u = getClass().getResource(p);
            if (u != null) l.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        } catch (Exception e) {}
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        try {
            URL u = getClass().getResource(p);
            if (u != null) {
                b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
                b.setHorizontalTextPosition(SwingConstants.LEADING);
            }
        } catch (Exception e) {}
    }

    class RoundedButton extends JButton {
        private int radius;
        public RoundedButton(String label, int radius) {
            super(label);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
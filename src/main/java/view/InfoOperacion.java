package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class InfoOperacion extends JFrame {

    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;
    public JButton btnAtras, btnGuardar, btnDescargar;
    public JTextField txtNombreCli, txtIdCli, txtIdOp, txtFechaOp, txtFechaDev, txtMonto, txtDescuento;
    public JTextField txtNombreProd, txtIdProd, txtTipoProd, txtPlataforma;
    public JRadioButton rbRenta, rbVenta;

    public InfoOperacion() {
        setTitle("Información de Operación");
        setMinimumSize(new Dimension(1000, 700));
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo3.png")));
        } catch (Exception e) {}

        // --- SIDEBAR ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(160, 0));
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(sidebar, BorderLayout.WEST);

        lblInicio = Menu(sidebar, "Inicio", "/img/casaazul.png");
        lblOperacion = Menu(sidebar, "Operación", "/img/simbolomasazul.png");
        lblClientes = Menu(sidebar, "Clientes", "/img/simboloclientesazul.png");
        lblVideojuegos = Menu(sidebar, "Videojuegos", "/img/simbolovideojuegosazul.png");
        lblPeliculas = Menu(sidebar, "Peliculas", "/img/simbolopeliculasazul.png");

        // --- MAIN PANEL ADAPTABLE ---
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        btnAtras = new RoundedButton("  Atrás", 20);
        btnAtras.setPreferredSize(new Dimension(120, 35));
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAtras.setBackground(new Color(225, 225, 225));
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        header.add(btnAtras, BorderLayout.WEST);

        JLabel titulo = new JLabel("Operación", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        header.add(titulo, BorderLayout.CENTER);

        JLabel cassette = new JLabel();
        cassette.setPreferredSize(new Dimension(90, 60));
        cargarIconoLabel(cassette, "/img/logo3.png", 90, 60);
        header.add(cassette, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.0;
        mainPanel.add(header, gbc);

        // --- PANEL GRIS ---
        JPanel panelGris = new JPanel(null);
        panelGris.setPreferredSize(new Dimension(800, 480));
        panelGris.setMinimumSize(new Dimension(800, 480));
        panelGris.setBackground(new Color(209, 209, 209));
        panelGris.setBorder(new LineBorder(Color.GRAY, 1));

        txtNombreCli = crearCampo(panelGris, "Nombre del cliente:", 25, 15, 260, "Paulina Rendón Galván");
        txtIdCli = crearCampo(panelGris, "ID de cliente:", 25, 80, 150, "285736");
        txtIdOp = crearCampo(panelGris, "ID de la operación:", 25, 145, 150, "00045621");

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(25, 205, 100, 20);
        lblTipo.setFont(new Font("Arial", Font.BOLD, 14));
        panelGris.add(lblTipo);

        JPanel bgTipo = new JPanel(null);
        bgTipo.setBounds(25, 225, 175, 30);
        bgTipo.setBackground(Color.WHITE);
        bgTipo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        rbRenta = new JRadioButton("Renta", true);
        rbRenta.setBounds(5, 0, 80, 30);
        rbRenta.setOpaque(false);
        rbRenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        rbVenta = new JRadioButton("Venta");
        rbVenta.setBounds(85, 0, 80, 30);
        rbVenta.setOpaque(false);
        rbVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ButtonGroup bgGroup = new ButtonGroup();
        bgGroup.add(rbRenta); bgGroup.add(rbVenta);
        bgTipo.add(rbRenta); bgTipo.add(rbVenta);
        panelGris.add(bgTipo);

        txtFechaOp = crearCampo(panelGris, "Fecha de operación:", 25, 260, 155, "24 / 09 / 2026");
        txtFechaDev = crearCampo(panelGris, "Fecha de devolución:", 25, 325, 155, "08 / 10 / 2026");
        txtMonto = crearCampo(panelGris, "Monto pagado:", 25, 390, 155, "$ 80.00");
        txtDescuento = crearCampo(panelGris, "Descuento:", 210, 390, 150, "0%");

        JLabel lblImg = new JLabel();
        lblImg.setBounds(460, 25, 310, 260);
        lblImg.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cargarIconoLabel(lblImg, "/img/chainsaw_man.png", 310, 260);
        panelGris.add(lblImg);

        txtNombreProd = crearCampoSimple(panelGris, "Nombre del producto:", 460, 290, 310, "Chainsaw Man - Arco de Reze");
        txtIdProd = crearCampoSimple(panelGris, "ID del producto:", 460, 350, 150, "PEL-10024");
        txtTipoProd = crearCampoSimple(panelGris, "Tipo:", 620, 350, 150, "Película");

        btnDescargar = new RoundedButton("Descargar Ficha  ", 15);
        btnDescargar.setBounds(580, 425, 190, 35);
        btnDescargar.setBackground(new Color(0, 180, 255));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setHorizontalTextPosition(SwingConstants.LEFT);
        btnDescargar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cargarIconoBoton(btnDescargar, "/img/pdf_icon.png", 22, 22);
        panelGris.add(btnDescargar);

        gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 1.0;
        mainPanel.add(panelGris, gbc);

        // --- BOTÓN GUARDAR ---
        btnGuardar = new RoundedButton("Confirmar Edición", 10);
        btnGuardar.setPreferredSize(new Dimension(180, 40));
        btnGuardar.setBackground(Color.BLACK);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        gbc.gridy = 2; gbc.weighty = 0.0; gbc.insets = new Insets(10, 20, 20, 20);
        mainPanel.add(btnGuardar, gbc);
    }

    public JLabel Menu(JPanel panel, String texto, String ruta) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));

        JLabel iconLabel = new JLabel();
        try {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        // Cambio de color solicitado: #04B4FF
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

    private JTextField crearCampo(JPanel p, String titulo, int x, int y, int w, String texto) {
        JLabel lbl = new JLabel(titulo);
        lbl.setBounds(x, y, 200, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl);
        JTextField tf = new JTextField(texto);
        tf.setBounds(x, y + 20, w, 30);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        p.add(tf);
        return tf;
    }

    private JTextField crearCampoSimple(JPanel p, String titulo, int x, int y, int w, String texto) {
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setBounds(x, y, w, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl);
        JTextField tf = new JTextField(texto);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setBounds(x, y + 20, w, 30);
        tf.setEditable(false);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(tf);
        return tf;
    }

    private void cargarIconoLabel(JLabel l, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) l.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
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
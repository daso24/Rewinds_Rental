package view;

import javax.swing.*; 
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AgregarJuego extends JFrame {
    
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas, lblLogoDerecha;
    public JButton btnAgregar, btnAtras;
    public JTextField txtNombre, txtId, txtVenta, txtRenta, txtDescuento;
    public JComboBox<String> cbPlataforma, cbStockVenta, cbStockRenta, cbClasif, cbAnio;

    private final Font INTER_BOLD_22 = new Font("Inter", Font.BOLD, 22);
    private final Font INTER_BOLD_16 = new Font("Inter", Font.BOLD, 16);
    private final Font INTER_BOLD_14 = new Font("Inter", Font.BOLD, 14);
    private final Font INTER_BOLD_13 = new Font("Inter", Font.BOLD, 13);
    private final Font INTER_REGULAR_13 = new Font("Inter", Font.PLAIN, 13);

    public AgregarJuego() {
        setTitle("Añadir videojuego");
        setMinimumSize(new Dimension(1000, 700)); 
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        btnAtras = crearBotonRedondeado(" Atrás", new Color(210, 210, 210), new Color(45, 59, 72), 20);
        try {
            btnAtras.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
        btnAtras.setPreferredSize(new Dimension(110, 35));
        headerPanel.add(btnAtras, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("Registrar Nuevo Videojuego", SwingConstants.CENTER);
        lblTitulo.setFont(INTER_BOLD_22);
        headerPanel.add(lblTitulo, BorderLayout.CENTER);

        try {
            lblLogoDerecha = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/logo3.png"))
                    .getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            lblLogoDerecha.setPreferredSize(new Dimension(60, 60));
            headerPanel.add(lblLogoDerecha, BorderLayout.EAST);
        } catch(Exception e) {}

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.0;
        mainPanel.add(headerPanel, gbc);

        JPanel contentPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(210, 210, 210));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };

        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(800, 480));

        crearLabel(contentPanel, "Nombre del producto:", 30, 20);
        txtNombre = crearField(contentPanel, "", 30, 45, 250);

        crearLabel(contentPanel, "ID del producto:", 30, 90);
        txtId = crearField(contentPanel, "", 30, 115, 180);

        crearLabel(contentPanel, "Plataforma:", 30, 160);
        cbPlataforma = crearCombo(contentPanel, new String[]{"Seleccionar...", "Playstation 5", "Xbox Series", "Nintendo Switch", "PC"}, 30, 185, 180);

        crearLabel(contentPanel, "Precio de venta:", 30, 230);
        txtVenta = crearField(contentPanel, "$ 0.00", 30, 255, 150);

        crearLabel(contentPanel, "Precio de renta (14 días):", 210, 230);
        txtRenta = crearField(contentPanel, "$ 0.00", 210, 255, 150);

        crearLabel(contentPanel, "Descuento:", 30, 300);
        txtDescuento = crearField(contentPanel, "0%", 30, 325, 100);

        crearLabel(contentPanel, "Stock Venta:", 30, 370);
        cbStockVenta = crearCombo(contentPanel, new String[]{"0", "1", "5", "10", "20"}, 30, 395, 160);

        crearLabel(contentPanel, "Stock Renta:", 210, 370);
        cbStockRenta = crearCombo(contentPanel, new String[]{"0", "1", "5", "10", "20"}, 210, 395, 160);

        JLabel lblFotoTit = new JLabel("Imagen del Producto:", SwingConstants.CENTER);
        lblFotoTit.setFont(INTER_BOLD_14);
        lblFotoTit.setBounds(510, 20, 240, 20);
        contentPanel.add(lblFotoTit);

        JPanel panelFoto = new JPanel(new BorderLayout());
        panelFoto.setBounds(510, 45, 240, 300);
        panelFoto.setBackground(new Color(240, 240, 240));
        panelFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel iconSubir = new JLabel("Click para subir foto", SwingConstants.CENTER);
        iconSubir.setFont(new Font("Inter", Font.ITALIC, 12));
        iconSubir.setForeground(Color.GRAY);
        panelFoto.add(iconSubir, BorderLayout.CENTER);
        contentPanel.add(panelFoto);

        crearLabel(contentPanel, "Clasificación:", 510, 360);
        cbClasif = crearCombo(contentPanel, new String[]{"C", "E", "T", "M"}, 510, 385, 110);

        crearLabel(contentPanel, "Año:", 640, 360);
        cbAnio = crearCombo(contentPanel, new String[]{"2024", "2025", "2026"}, 640, 385, 110);

        gbc.gridy = 1; gbc.weighty = 1.0;
        mainPanel.add(contentPanel, gbc);

        btnAgregar = new JButton("Agregar Videojuego") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 170, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnAgregar.setPreferredSize(new Dimension(240, 45));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(INTER_BOLD_14);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        footerPanel.add(btnAgregar);

        gbc.gridy = 2; gbc.weighty = 0.0;
        mainPanel.add(footerPanel, gbc);
    }

    public void mostrarError(String mensaje) {
        mostrarPopUpGris(mensaje, new Color(220, 50, 50), "/img/mingcute_warning-fill.png");
    }

    public void mostrarExito(String mensaje) {
        mostrarPopUpGris(mensaje, new Color(0, 51, 102), "/img/palomitaverde.png");
    }

    private void mostrarPopUpGris(String mensaje, Color colorBoton, String rutaIcono) {
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
        lblMsg.setFont(INTER_BOLD_16);
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
        
        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(120, 35));
        btnOk.setBackground(colorBoton);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(INTER_BOLD_13);
        btnOk.addActionListener(e -> dialogo.dispose());
        
        JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pBotones.setOpaque(false);
        pBotones.add(btnOk);
        
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
        try { iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH))); } catch(Exception e) {}
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(new Color(4, 180, 255)); label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.add(iconLabel); item.add(Box.createVerticalStrut(5)); item.add(label);
        panel.add(item);
        return label;
    }

    private JButton crearBotonRedondeado(String texto, Color bg, Color fg, int radio) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setForeground(fg);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(INTER_BOLD_14);
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 30);
        f.setFont(INTER_REGULAR_13);
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        p.add(f);
        return f;
    }

    private JComboBox<String> crearCombo(JPanel p, String[] items, int x, int y, int w) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBounds(x, y, w, 30);
        c.setFont(INTER_REGULAR_13);
        c.setBackground(Color.WHITE);
        p.add(c);
        return c;
    }
}
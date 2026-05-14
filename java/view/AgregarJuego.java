package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AgregarJuego extends JFrame {

    public JTextField txtNombre, txtId, txtVenta, txtRenta, txtDescuento;
    public JComboBox<String> cbPlataforma, cbStockVenta, cbStockRenta, cbClasif, cbAnio;
    public JButton btnAgregar, btnAtras;
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas, lblLogoDerecha;

    public AgregarJuego() {
        setTitle("Añadir videojuego");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 700);
        barraLat.setLayout(null);
        add(barraLat);

        lblInicio = Menu(barraLat, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        lblOperacion = Menu(barraLat, "Operación", 150, "/img/ic_baseline-plus.png");
        lblClientes = Menu(barraLat, "Clientes", 260, "/img/material-symbols_person.png");
        lblVideojuegos = Menu(barraLat, "Videojuegos", 370, "/img/carbon_game-console.png");
        lblPeliculas = Menu(barraLat, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245)); 
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(210, 210, 210)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        try {
            ImageIcon iconAtras = new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            btnAtras.setIcon(iconAtras);
        } catch(Exception e) {}

        btnAtras.setBounds(20, 20, 110, 35);
        btnAtras.setFont(new Font("Inter", Font.PLAIN, 14));
        btnAtras.setContentAreaFilled(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setBorder(new LineBorder(Color.GRAY, 1, true));
        mainPanel.add(btnAtras);

        try {
            lblLogoDerecha = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/logo3.png"))
                    .getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            lblLogoDerecha.setBounds(750, 10, 60, 60);
            mainPanel.add(lblLogoDerecha);
        } catch(Exception e) {}

        JLabel lblTitulo = new JLabel("Registrar Nuevo Videojuego", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(20, 80, 800, 500);
        contentPanel.setBackground(new Color(210, 210, 210));
        contentPanel.setBorder(new LineBorder(Color.GRAY, 1));
        contentPanel.setLayout(null);
        mainPanel.add(contentPanel);

        crearLabel(contentPanel, "Nombre del producto:", 30, 20);
        txtNombre = crearField(contentPanel, "", 30, 45, 250);

        crearLabel(contentPanel, "ID del producto:", 30, 90);
        txtId = crearField(contentPanel, "", 30, 115, 180);

        crearLabel(contentPanel, "Plataforma:", 30, 160);
        String[] plataformas = {"Seleccionar...", "Playstation 5", "Xbox Series", "Nintendo Switch", "PC"};
        cbPlataforma = crearCombo(contentPanel, plataformas, 30, 185, 180);

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
        lblFotoTit.setFont(new Font("Inter", Font.BOLD, 14));
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
        btnAgregar.setBounds(300, 600, 240, 45);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Inter", Font.BOLD, 15));
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        mainPanel.add(btnAgregar);
    }

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Inter", Font.BOLD, 14));
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 30);
        f.setFont(new Font("Inter", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        p.add(f);
        return f;
    }

    private JComboBox<String> crearCombo(JPanel p, String[] items, int x, int y, int w) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBounds(x, y, w, 30);
        c.setFont(new Font("Inter", Font.PLAIN, 13));
        c.setBackground(Color.WHITE);
        p.add(c);
        return c;
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(iconLabel); panel.add(label);
        return label;
    }

    public void mostrarError(String mensaje) {
        mostrarPopUpGris(mensaje, new Color(220, 50, 50), "/img/mingcute_warning-fill.png");
    }

    public void mostrarExito(String mensaje) {
        mostrarPopUpGris(mensaje, new Color(50, 180, 50), "/img/ic_baseline-plus.png");
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
        JLabel lblMsg = new JLabel("<html><div style='text-align: center; width: 250px;'>" + mensaje + "</div></html>", SwingConstants.CENTER);
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
        JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pBotones.setOpaque(false);
        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(120, 35));
        btnOk.setBackground(colorBoton);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 13));
        btnOk.addActionListener(e -> dialogo.dispose());
        pBotones.add(btnOk);
        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }
}
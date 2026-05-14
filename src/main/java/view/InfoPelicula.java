package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.net.URL;

public class InfoPelicula extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JTextField txtNomProd, txtIdProd, txtFormato, txtGenero, txtMonto, txtDescuento, txtFechaOp;
    public JButton btnAtras, btnDescargar, btnEditar;
    public JLabel lblImagenPelicula;

    public InfoPelicula() {
        setTitle("Información de Película / Operación");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo3.png")));
        } catch (Exception e) {}

        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 700);
        barraLat.setLayout(null);
        add(barraLat);

        btnInicio = crearLabelMenu(barraLat, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        btnOperacion = crearLabelMenu(barraLat, "Operación", 150, "/img/ic_baseline-plus.png");
        btnClientes = crearLabelMenu(barraLat, "Clientes", 260, "/img/material-symbols_person.png");
        btnVideojuegos = crearLabelMenu(barraLat, "Videojuegos", 370, "/img/carbon_game-console.png");
        btnPeliculas = crearLabelMenu(barraLat, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE); 
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(230, 230, 230)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnAtras.setBounds(20, 20, 120, 35);
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAtras.setContentAreaFilled(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setBorder(new LineBorder(Color.GRAY, 1, true));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Información de película", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);
        
        JLabel iconoDerecha = new JLabel();
        iconoDerecha.setBounds(730, 5, 80, 60);
        cargarIconoLabel(iconoDerecha, "/img/cassette_icon.png", 80, 60); 
        mainPanel.add(iconoDerecha);

        JPanel panelGris = new JPanel();
        panelGris.setBounds(20, 70, 800, 550);
        panelGris.setBackground(new Color(209, 209, 209));
        panelGris.setLayout(null);
        mainPanel.add(panelGris);

        crearLabel(panelGris, "Nombre del producto:", 30, 20);
        txtNomProd = crearField(panelGris, "", 30, 45, 200);

        crearLabel(panelGris, "ID del producto:", 30, 85);
        txtIdProd = crearField(panelGris, "", 30, 110, 180);

        crearLabel(panelGris, "Tipo de producto:", 30, 150);
        JTextField txtTipo = crearField(panelGris, "Pelicula", 30, 175, 180);
        txtTipo.setEditable(false);

        crearLabel(panelGris, "Plataforma:", 30, 215);
        txtFormato = crearField(panelGris, "", 30, 240, 180);

        crearLabel(panelGris, "Precio de venta:", 30, 280);
        txtMonto = crearField(panelGris, "$ 250.00", 30, 305, 180);

        crearLabel(panelGris, "Descuento:", 30, 345);
        txtDescuento = crearField(panelGris, "0%", 30, 370, 180);

        crearLabel(panelGris, "Stock", 30, 415);
        JLabel lblDisVenta = new JLabel("Disponibles para venta:");
        lblDisVenta.setBounds(30, 440, 150, 20);
        panelGris.add(lblDisVenta);
        JTextField txtStockVenta = crearField(panelGris, "50", 30, 460, 180);
        
        crearLabel(panelGris, "Precio de renta (por 14 días):", 250, 280);
        txtFechaOp = crearField(panelGris, "$ 100.00", 250, 305, 180);

        JLabel lblDisRenta = new JLabel("Disponibles para renta:");
        lblDisRenta.setBounds(250, 440, 150, 20);
        panelGris.add(lblDisRenta);
        JTextField txtStockRenta = crearField(panelGris, "20", 250, 460, 180);

        JLabel lblProdTit = new JLabel("Producto:");
        lblProdTit.setBounds(500, 20, 100, 20);
        lblProdTit.setFont(new Font("Arial", Font.BOLD, 14));
        panelGris.add(lblProdTit);

        lblImagenPelicula = new JLabel();
        lblImagenPelicula.setBounds(500, 45, 260, 280); 
        lblImagenPelicula.setBackground(Color.DARK_GRAY);
        lblImagenPelicula.setOpaque(true);
        lblImagenPelicula.setBorder(new LineBorder(Color.WHITE, 2));
        panelGris.add(lblImagenPelicula);

        crearLabel(panelGris, "Clasificación:", 500, 335);
        JTextField txtClasif = crearField(panelGris, "B-15", 500, 355, 120);

        crearLabel(panelGris, "Año de lanzamiento:", 640, 335);
        JTextField txtAnio = crearField(panelGris, "2025", 640, 355, 120);

        crearLabel(panelGris, "Género:", 500, 395);
        txtGenero = crearField(panelGris, "Acción", 500, 415, 260);

        btnEditar = new JButton("Editar info de la película") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(45, 59, 72));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnEditar.setBounds(30, 505, 230, 35);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setBorderPainted(false);
        panelGris.add(btnEditar);

        btnDescargar = new JButton("Descargar ficha") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 180, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnDescargar.setBounds(340, 505, 200, 35);
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setContentAreaFilled(false);
        btnDescargar.setFocusPainted(false);
        btnDescargar.setBorderPainted(false);
        cargarIconoBoton(btnDescargar, "/img/pdf_icon.png", 20, 20);
        panelGris.add(btnDescargar);
    }

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Arial", Font.BOLD, 13));
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 30);
        f.setHorizontalAlignment(JTextField.CENTER);
        f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(f);
        return f;
    }

    private JLabel crearLabelMenu(JPanel panel, String texto, int y, String rutaIcono) {
        try {
            URL url = getClass().getResource(rutaIcono);
            if (url != null) {
                JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
                iconLabel.setBounds(15, y, 25, 30);
                panel.add(iconLabel);
            }
        } catch (Exception e) {}
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label);
        return label;
    }

    private void cargarIconoLabel(JLabel l, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) l.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }
}
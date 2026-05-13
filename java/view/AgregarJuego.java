package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AgregarJuego extends JFrame {

    public JTextField txtNombre, txtId, txtVenta, txtRenta, txtDescuento;
    public JComboBox<String> cbPlataforma, cbStockVenta, cbStockRenta, cbClasif, cbAnio;
    public JButton btnAgregar, btnAtras;
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;

    public AgregarJuego() {
        setTitle("Añadir videojuego");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // BARRA LATERAL AZUL
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

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(210, 210, 210));
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        // CABECERA 
        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Añadir videojuego", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);

        // FORMULARIO ORIGINAL
        crearLabel(mainPanel, "Nombre del producto:", 30, 80);
        txtNombre = crearField(mainPanel, "", 30, 105, 200);

        crearLabel(mainPanel, "ID del producto:", 30, 150);
        txtId = crearField(mainPanel, "", 30, 175, 200);

        crearLabel(mainPanel, "Tipo de producto:", 30, 220);
        JTextField txtTipo = crearField(mainPanel, "Videojuego", 30, 245, 200);
        txtTipo.setEditable(false);

        crearLabel(mainPanel, "Plataforma:", 30, 290);
        String[] plataformas = {"Seleccionar...", "Playstation 5", "Xbox Series", "Nintendo Switch", "PC"};
        cbPlataforma = crearCombo(mainPanel, plataformas, 30, 315, 200);

        crearLabel(mainPanel, "Precio de venta:", 30, 370);
        txtVenta = crearField(mainPanel, "$ 0.00", 30, 395, 180);

        crearLabel(mainPanel, "Descuento:", 30, 450);
        txtDescuento = crearField(mainPanel, "0%", 30, 475, 180);

        crearLabel(mainPanel, "Precio de renta (por 14 días):", 250, 345);
        txtRenta = crearField(mainPanel, "$ 0.00", 250, 375, 180);

        crearLabel(mainPanel, "Stock:", 30, 520);
        cbStockVenta = crearCombo(mainPanel, new String[]{"Disponibles para Venta", "1", "5", "10"}, 30, 545, 180);
        cbStockRenta = crearCombo(mainPanel, new String[]{"Disponibles para Renta", "1", "5", "10"}, 250, 545, 180);

        crearLabel(mainPanel, "Clasificación:", 450, 430);
        cbClasif = crearCombo(mainPanel, new String[]{"C", "E", "T", "M"}, 450, 455, 160);

        crearLabel(mainPanel, "Año de lanzamiento:", 650, 430);
        cbAnio = crearCombo(mainPanel, new String[]{"2024", "2025", "2026"}, 650, 455, 150);

     
        JLabel lblProducto = new JLabel("Producto:", SwingConstants.CENTER);
        lblProducto.setFont(new Font("Arial", Font.BOLD, 14));
        lblProducto.setBounds(500, 80, 220, 20);
        mainPanel.add(lblProducto);

        JPanel panelFoto = new JPanel();
        panelFoto.setBounds(500, 105, 220, 300);
        panelFoto.setBackground(new Color(240, 240, 240));
        panelFoto.setLayout(new BorderLayout());
        panelFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JLabel iconSubir = new JLabel("Subir foto de videojuego", SwingConstants.CENTER);
        iconSubir.setForeground(Color.GRAY);
        panelFoto.add(iconSubir, BorderLayout.CENTER);
        mainPanel.add(panelFoto);

        // BOTÓN AGREGAR
        btnAgregar = new JButton("Agregar Videojuego");
        btnAgregar.setBounds(330, 610, 200, 40);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(btnAgregar);
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
        lblMsg.setFont(new Font("Arial", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblMsg);

        contenido.add(Box.createVerticalGlue());

        try {
            ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
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
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(e -> dialogo.dispose());
        pBotones.add(btnOk);

        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 30);
        f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(f);
        return f;
    }

    private JComboBox<String> crearCombo(JPanel p, String[] items, int x, int y, int w) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBounds(x, y, w, 30);
        c.setBackground(Color.WHITE);
        p.add(c);
        return c;
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(iconLabel); panel.add(label);
        return label;
    }
}
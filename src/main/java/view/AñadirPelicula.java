package view;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class AñadirPelicula extends JFrame {

    private JTextField txtNombre, txtId, txtVenta, txtRenta, txtDescuento;
    private JComboBox<String> cbPlataforma, cbStockVenta, cbStockRenta, cbClasif, cbAnio;

    public AñadirPelicula() {
        setTitle("Añadir Pelicula");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // BARRA LATERAL AZUL
        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 700);
        barraLat.setLayout(null);
        add(barraLat);

        // Iconos 
        Menu(barraLat, "Inicio", 80, crearIcono("/img/gravity-ui_house-fill.png"));
        Menu(barraLat, "Operación", 150, crearIcono("/img/ic_baseline-plus.png"));
        Menu(barraLat, "Clientes", 260, crearIcono("/img/material-symbols_person.png"));
        Menu(barraLat, "Videojuegos", 370, crearIcono("/img/carbon_game-console.png"));
        Menu(barraLat, "Peliculas", 480, crearIcono("/img/fluent_movies-and-tv-16-filled.png"));

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(210, 210, 210));
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        // CABECERA 
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        btnAtras.addActionListener(e -> {
            new videojuegos(); 
            dispose();
        });
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Añadir Pelicula", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);

        // FORMULARIO 
        crearLabel(mainPanel, "Nombre del producto:", 30, 80);
        txtNombre = crearField(mainPanel, "", 30, 105, 200);

        crearLabel(mainPanel, "ID del producto:", 30, 150);
        txtId = crearField(mainPanel, "", 30, 175, 200);

        crearLabel(mainPanel, "Tipo de producto:", 30, 220);
        JTextField txtTipo = crearField(mainPanel, "Pelicula", 30, 245, 200);
        txtTipo.setEditable(false);

        crearLabel(mainPanel, "Plataforma:", 30, 290);
        String[] plataformas = {"Seleccionar...", "Blue-ray", "CD", "DVD"};
        cbPlataforma = crearCombo(mainPanel, plataformas, 30, 315, 200);

        crearLabel(mainPanel, "Precio de venta:", 30, 370);
        txtVenta = crearField(mainPanel, "$ 0.00", 30, 395, 180);

        crearLabel(mainPanel, "Descuento:", 30, 450);
        txtDescuento = crearField(mainPanel, "0%", 30, 475, 180);

        // FORMULARIO 
        crearLabel(mainPanel, "Precio de renta (por 14 días):", 250, 345);
        txtRenta = crearField(mainPanel, "$ 0.00", 250, 375, 180);

        crearLabel(mainPanel, "Stock:", 30, 520);
        String[] stockVentaItems = {"Disponibles para Venta", "1", "5", "10", "20"};
        cbStockVenta = crearCombo(mainPanel, stockVentaItems, 30, 545, 180);

        String[] stockRentaItems = {"Disponibles para Renta", "1", "5", "10", "20"};
        cbStockRenta = crearCombo(mainPanel, stockRentaItems, 250, 545, 180);

        // FORMULARIO
        crearLabel(mainPanel, "Clasificación:", 450, 430);
        String[] clasifItems = {"C", "E", "E+10", "T", "M"};
        cbClasif = crearCombo(mainPanel, clasifItems, 450, 455, 160);

        crearLabel(mainPanel, "Año de lanzamiento:", 650, 430);
        String[] anioItems = {"2024", "2025", "2026"};
        cbAnio = crearCombo(mainPanel, anioItems, 650, 455, 150);

        // ÁREA DE IMAGEN 
        JLabel lblProducto = new JLabel("Producto:", SwingConstants.CENTER);
        lblProducto.setFont(new Font("Arial", Font.BOLD, 14));
        lblProducto.setBounds(500, 80, 220, 20);
        mainPanel.add(lblProducto);

        JPanel panelFoto = new JPanel();
        panelFoto.setBounds(500, 105, 220, 300);
        panelFoto.setBackground(new Color(240, 240, 240));
        panelFoto.setLayout(new BorderLayout());
        
        JLabel iconSubir = new JLabel("Subir foto de la Pelicula", SwingConstants.CENTER);
        iconSubir.setForeground(Color.GRAY);
        panelFoto.add(iconSubir, BorderLayout.CENTER);
        mainPanel.add(panelFoto);

        // BOTÓN 
        JButton btnAgregar = new JButton("Agregar Pelicula");
        btnAgregar.setBounds(330, 610, 200, 40);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarYMostrarPopUp();
            }
        });
        
        mainPanel.add(btnAgregar);

        setVisible(true);
    }

    private void validarYMostrarPopUp() {
        if (txtNombre.getText().isEmpty() || txtId.getText().isEmpty() || cbPlataforma.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Error: Debes ingresar el nombre, ID y seleccionar una plataforma.", 
                "Error de Registro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double.parseDouble(txtVenta.getText().replace("$", "").trim());
            Double.parseDouble(txtRenta.getText().replace("$", "").trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: Los precios deben ser valores numéricos.", 
                "Formato Inválido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, 
            "¡Videojuego registrado con éxito!", 
            "Operación Exitosa", 
            JOptionPane.INFORMATION_MESSAGE);
        
        new videojuegos();
        dispose();
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

    private ImageIcon crearIcono(String ruta) {
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                    .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } catch (Exception e) { return null; }
    }

    // MENÚ LATERAL
    public void Menu(JPanel panel, String texto, int y, Icon icono) {
        JLabel iconLabel = new JLabel(icono);
        iconLabel.setBounds(15, y, 25, 30);

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame ventana = null;
                switch (texto) {
                    case "Inicio": ventana = new principal(); break;
                    case "Videojuegos": ventana = new videojuegos(); break;
                    case "Clientes": ventana = new clientes(); break;
                    case "Operación": ventana = new operaciones(); break;
                    case "Peliculas": ventana = new peliculas(); break;
                }
                if (ventana != null) {
                    ventana.setVisible(true);
                    dispose();
                }
            }
        });

        panel.add(iconLabel);
        panel.add(label);
    }

    public static void main(String[] args) {
        new AñadirPelicula();
    }
}
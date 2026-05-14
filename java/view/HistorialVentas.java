package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class HistorialVentas extends JFrame {

    public JButton btnAtras;
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;
    public JTable tabla;
    public DefaultTableModel modelo;

    public HistorialVentas() {
        setTitle("Historial de ventas del cliente");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 700);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        lblInicio = Menu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        lblOperacion = Menu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        lblClientes = Menu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        lblVideojuegos = Menu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        lblPeliculas = Menu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setBackground(new Color(200, 200, 200)); 
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Historial de ventas del cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(160, 20, 520, 30);
        mainPanel.add(lblTitulo);

        String[] columnas = {"Producto", "Plataforma", "Monto de pago", "Fecha de compra", "Descuento"};
        Object[][] datos = {
            {"Dragon Ball Sparking Zero", "Playstation 5", "$1000.00", "08 / 07 / 2025", "0%"},
            {"Spider-man Into the Spiderverse", "Blu-Ray", "$250.00", "02 / 02 / 2026", "0%"},
            {"Volver al Futuro", "DVD", "$250.00", "20 / 01 / 2026", "30%"},
            {"Superman (James Gunn)", "Blu-Ray", "$500.00", "08 / 07 / 2025", "0%"},
            {"God of War Ragnarok", "Playstation 5", "$800.00", "08 / 11 / 2025", "0%"}
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(60);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setShowVerticalLines(true);
        tabla.setShowHorizontalLines(true);

        JTableHeader header = tabla.getTableHeader();
        header.setPreferredSize(new Dimension(100, 50));
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(230, 230, 230));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 80, 790, 500);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(scroll);
    }

    private JLabel Menu(JPanel panel, String texto, int y, String ruta) {
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
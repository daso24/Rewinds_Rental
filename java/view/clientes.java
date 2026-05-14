package view;

import javax.swing.*; 
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class clientes extends JFrame {
  
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnEliminar;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modelo;
    public TableRowSorter<DefaultTableModel> sorter;

    public clientes() {
        setTitle("Gestión de Clientes");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // BARRA LATERAL
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 650);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);
        
        btnInicio = Menu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        btnOperacion = Menu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        btnClientes = Menu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        btnVideojuegos = Menu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        btnPeliculas = Menu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 650);
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAgregar = new JButton("+ Añadir cliente") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 170, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnAgregar.setBounds(590, 20, 220, 35);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Inter", Font.BOLD, 14));
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        mainPanel.add(btnAgregar);

        JLabel titulo = new JLabel("Clientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 26));
        titulo.setBounds(0, 20, 840, 35);
        mainPanel.add(titulo);

        // BUSCADOR
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(20, 80, 790, 60);
        searchPanel.setLayout(null);
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(new RoundedBorder(20));
        mainPanel.add(searchPanel);
        
        JLabel lupa = new JLabel("Buscar:");
        lupa.setFont(new Font("Inter", Font.BOLD, 13));
        lupa.setBounds(15, 15, 60, 30);
        searchPanel.add(lupa);
        
        buscador = new JTextField();
        buscador.setBounds(80, 15, 550, 30);
        buscador.setFont(new Font("Inter", Font.PLAIN, 13));
        searchPanel.add(buscador);

        btnBuscar = new JButton("Buscar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(45, 59, 72));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnBuscar.setBounds(650, 15, 110, 30);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Inter", Font.BOLD, 13));
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        searchPanel.add(btnBuscar);

        // TABLA CON DATOS
        String[] columnas = {"", "Nombre", "ID", "Rentas activas", "Última compra", "Última renta", "Acción"};

        Object[][] datos = {
            {false, "Mateo Valeriano Soler", "482915", "3", "08 / 07 / 2025", "19 / 03 / 2026", "Ver info"},
            
            {false, "Lucía Fernanda Mondragón", "730642", "5", "02 / 02 / 2026", "23 / 03 / 2026", "Ver info"},
            
            {false, "Adrián Celis Olavarría", "105422", "2", "20 / 01 / 2026", "20 / 03 / 2026", "Ver info"},
            
            {false, "Elena Beatriz Iturbide", "195873", "1", "08 / 07 / 2025", "22 / 03 / 2026", "Ver info"},
            
            {false, "Tobías Martínez", "627104", "2", "08 / 11 / 2025", "24 / 03 / 2026", "Ver info"}
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override public Class<?> getColumnClass(int c) {
                return (c == 0) ? Boolean.class : String.class;
            }
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(40);
        tabla.setFont(new Font("Inter", Font.PLAIN, 13));
        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 340);
        mainPanel.add(scroll);

        btnEliminar = new JButton("Eliminar seleccionado") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 50, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnEliminar.setBounds(320, 540, 240, 40);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Inter", Font.BOLD, 14));
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        mainPanel.add(btnEliminar);
    }

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280); 
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        dialogo.setContentPane(contenedor);

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(25));

        JLabel lblMsg = new JLabel("<html><div style='text-align: center; width: 250px;'>" + mensaje + "</div></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);

        panelContenido.add(Box.createVerticalGlue());

        try {
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel iconoCentro = new JLabel(imagenAlerta);
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}

        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setOpaque(false);

        JButton btnSi = new JButton("Eliminar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 50, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnSi.setPreferredSize(new Dimension(110, 35));
        btnSi.setForeground(Color.WHITE);
        btnSi.setFont(new Font("Inter", Font.BOLD, 13));
        btnSi.setContentAreaFilled(false);
        btnSi.setFocusPainted(false);
        btnSi.setBorderPainted(false);
        btnSi.addActionListener(e -> {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });

        JButton btnNo = new JButton("Cancelar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(130, 130, 130));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnNo.setPreferredSize(new Dimension(110, 35));
        btnNo.setForeground(Color.WHITE);
        btnNo.setFont(new Font("Inter", Font.BOLD, 13));
        btnNo.setContentAreaFilled(false);
        btnNo.setFocusPainted(false);
        btnNo.setBorderPainted(false);
        btnNo.addActionListener(e -> dialogo.dispose());

        panelBotones.add(btnSi); panelBotones.add(btnNo);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
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

    class RoundedBorder implements Border {
        int r; RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r, r, r, r); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) { 
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, w - 1, h - 1, r, r); 
        }
    }
}
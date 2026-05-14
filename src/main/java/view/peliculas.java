package view;

import javax.swing.*; 
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class peliculas extends JFrame {
  
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnEliminar, btnFiltrar;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modelo;
    public TableRowSorter<DefaultTableModel> sorter;

    public peliculas() {

        setTitle("Catálogo de Películas");
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

        btnAgregar = new JButton("+ Añadir una película") {
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

        JLabel titulo = new JLabel("Películas", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 26));
        titulo.setBounds(0, 20, 840, 35);
        mainPanel.add(titulo);

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
        buscador.setBounds(80, 15, 450, 30);
        buscador.setFont(new Font("Inter", Font.PLAIN, 13));
        buscador.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
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

        btnBuscar.setBounds(540, 15, 110, 30);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Inter", Font.BOLD, 13));
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        searchPanel.add(btnBuscar);

        btnFiltrar = new JButton("Filtrar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(100, 100, 100));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        btnFiltrar.setBounds(660, 15, 100, 30);
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFont(new Font("Inter", Font.BOLD, 13));
        btnFiltrar.setContentAreaFilled(false);
        btnFiltrar.setFocusPainted(false);
        btnFiltrar.setBorderPainted(false);
        searchPanel.add(btnFiltrar);

        // TABLA
        String[] columnas = {"", "Carátula", "Nombre", "ID", "Tipo", "Plataforma", "Info"};

        Object[][] datos = {
            {false, getImg("/img/811xdZfsUqL.jpg"), "Avengers Infinity War", "PEL-00017", getImg("/img/fluent_movies-and-tv-16-filled.png"), "Blu-Ray", "Ver info"},

            {false, getImg("/img/71w58zkWnfL.jpg"), "Chainsaw Man - La película: Arco de Reze", "PEL-10024", getImg("/img/fluent_movies-and-tv-16-filled.png"), "Blu-Ray", "Ver info"},

            {false, getImg("/img/61MZdcVwQFL.jpg"), "Rocky", "PEL-03085", getImg("/img/fluent_movies-and-tv-16-filled.png"), "DVD", "Ver info"},

            {false, getImg("/img/20504502.jpg"), "Shrek 2", "PEL-00547", getImg("/img/fluent_movies-and-tv-16-filled.png"), "DVD", "Ver info"},

            {false, getImg("/img/71MZBMmOXtL._AC_UF894,1000_QL80_.jpg"), "Dragon Ball Super Broly", "PEL-00723", getImg("/img/fluent_movies-and-tv-16-filled.png"), "Blu-Ray", "Ver info"}
        };

        modelo = new DefaultTableModel(datos, columnas) {

            @Override
            public Class<?> getColumnClass(int c) {

                if (c == 0) return Boolean.class;

                if (c == 1 || c == 4) return Icon.class;

                return String.class;
            }

            @Override
            public boolean isCellEditable(int r, int c) {
                return c == 0;
            }
        };

        tabla = new JTable(modelo);

        tabla.setRowHeight(60);

        tabla.setFont(new Font("Inter", Font.PLAIN, 13));

        sorter = new TableRowSorter<>(modelo);

        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        mainPanel.add(scroll);

        btnEliminar = new JButton("Eliminar película") {

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

    private ImageIcon getImg(String ruta) {

        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));

        Image img = icon.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);

        return new ImageIcon(img);
    }

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {

        JDialog dialogo = new JDialog(this, "Confirmar", true);

        dialogo.setUndecorated(true);

        dialogo.setSize(350, 280);

        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());

        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));

        contenedor.setBackground(new Color(209, 209, 209));

        dialogo.setContentPane(contenedor);

        dialogo.setVisible(true);
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {

        JLabel iconLabel = new JLabel(new ImageIcon(
            new ImageIcon(getClass().getResource(ruta))
            .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));

        iconLabel.setBounds(15, y, 25, 30);

        JLabel label = new JLabel(texto);

        label.setForeground(Color.WHITE);

        label.setFont(new Font("Inter", Font.PLAIN, 15));

        label.setBounds(50, y, 120, 30);

        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(iconLabel);

        panel.add(label);

        return label;
    }

    class RoundedBorder implements Border {

        int r;

        RoundedBorder(int r) {
            this.r = r;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(r, r, r, r);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {

            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }
}
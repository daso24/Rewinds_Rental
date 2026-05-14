package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class operaciones extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnFiltrar, btnEliminar;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modeloTabla;
    public TableRowSorter<DefaultTableModel> sorter;

    public operaciones() {
        setTitle("Operaciones");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

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

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 650);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAgregar = new JButton("+ Añadir operación") {
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

        JLabel titulo = new JLabel("Operaciones", SwingConstants.CENTER);
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

        String[] columnas = {"", "Cliente", "Tipo", "Producto", "Tipo Producto", "Plataforma", "Info"};

        Object[][] datos = {
            {false, "Mateo Valeriano Soler", "Renta", getImg("/img/forza_horizon_6-6006996.jpg"), getImg("/img/carbon_game-console.png"), "Xbox Series X", "Ver info"},
            
            {false, "Lucía Fernanda Mondragón", "Venta", getImg("/img/71fw9QnEQUL.jpg"), getImg("/img/carbon_game-console.png"), "PS5", "Ver info"},
            
            {false, "Adrián Celis Olavarría", "Renta", getImg("/img/71w58zkWnfL.jpg"), getImg("/img/fluent_movies-and-tv-16-filled.png"), "Blue_ray", "Ver info"},
            
            {false, "Elena Beatriz Iturbide", "Venta", getImg("/img/51gz5Gfjl8L._AC_UF894,1000_QL80_.jpg"), getImg("/img/fluent_movies-and-tv-16-filled.png"), "DVD", "Ver info"},
            
            {false, "Tobías Martínez", "Renta", getImg("/img/71MZBMmOXtL._AC_UF894,1000_QL80_.jpg"), getImg("/img/fluent_movies-and-tv-16-filled.png"), "Blue-Ray", "Ver info"}
        };

        modeloTabla = new DefaultTableModel(datos, columnas) {
            @Override public Class<?> getColumnClass(int c) { 
                if(c == 0) return Boolean.class;
                if(c == 3 || c == 4) return Icon.class;
                return Object.class;
            }
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(70);
        tabla.setFont(new Font("Inter", Font.PLAIN, 13));
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        mainPanel.add(scroll);

        btnEliminar = new JButton("Eliminar operación") {
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
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            return null;
        }
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        try {
            JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            iconLabel.setBounds(15, y, 25, 30);
            panel.add(iconLabel);
        } catch(Exception e) {}
        
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label);
        return label;
    }

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280); 
        dialogo.setLocationRelativeTo(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(209, 209, 209));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        dialogo.setContentPane(panelPrincipal);

        panelPrincipal.add(Box.createVerticalStrut(20));

        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblMsg);

        panelPrincipal.add(Box.createVerticalGlue());

        try {
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel iconoCentro = new JLabel(imagenAlerta);
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelPrincipal.add(iconoCentro);
        } catch (Exception e) {}

        panelPrincipal.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
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

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(20));

        dialogo.setVisible(true);
    }

    public class RoundedBorder implements Border {
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
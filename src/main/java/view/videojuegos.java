package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class videojuegos extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnEliminar, btnFiltrar;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modelo;
    public TableRowSorter<DefaultTableModel> sorter;

    public videojuegos() {
        setTitle("Catálogo de Videojuegos");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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

        btnInicio = crearItemMenu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        btnOperacion = crearItemMenu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        btnClientes = crearItemMenu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        btnVideojuegos = crearItemMenu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        btnPeliculas = crearItemMenu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 650);
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAgregar = new JButton("+ Añadir Videojuego");
        btnAgregar.setBounds(600, 20, 210, 35);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        mainPanel.add(btnAgregar);

        JLabel titulo = new JLabel("Videojuegos");
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
        titulo.setBounds(20, 20, 300, 30);
        mainPanel.add(titulo);

        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(20, 80, 790, 60);
        searchPanel.setLayout(null);
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(new RoundedBorder(20));
        mainPanel.add(searchPanel);
        
        JLabel lupa = new JLabel("Buscar:");
        lupa.setBounds(15, 15, 60, 30);
        searchPanel.add(lupa);

        buscador = new JTextField();
        buscador.setBounds(80, 15, 450, 30);
        buscador.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchPanel.add(buscador);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(540, 15, 110, 30);
        searchPanel.add(btnBuscar);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(660, 15, 100, 30);
        searchPanel.add(btnFiltrar);

        String[] columnas = {"", "Título", "Plataforma", "Género", "Stock", "Precio Renta", "Info"};
        Object[][] datos = {
            {false, "Halo Infinite", "Xbox Series X", "FPS", "5", "$50.00", "Ver info"},
            {false, "God of War Ragnarök", "PS5", "Acción", "3", "$60.00", "Ver info"},
            {false, "Elden Ring", "Multi", "RPG", "7", "$55.00", "Ver info"}
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override public Class<?> getColumnClass(int c) { return (c == 0) ? Boolean.class : String.class; }
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(50);
        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        tabla.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                setForeground(Color.BLACK);
                setFont(new Font("Arial", Font.PLAIN, 13));
                setHorizontalAlignment(SwingConstants.CENTER);
                
                if (isSelected) {
                    c.setForeground(table.getSelectionForeground());
                }
                
                return c;
            }
        });

        tabla.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int col = tabla.columnAtPoint(e.getPoint());
                if (col == 6) {
                    tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    tabla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        mainPanel.add(scroll);

        btnEliminar = new JButton("Eliminar Seleccionados");
        btnEliminar.setBounds(320, 540, 200, 35);
        btnEliminar.setBackground(new Color(255, 87, 34));
        btnEliminar.setForeground(Color.WHITE);
        mainPanel.add(btnEliminar);
    }

    private JLabel crearItemMenu(JPanel panel, String texto, int y, String ruta) {
        JLabel iconLabel = new JLabel();
        try {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
        iconLabel.setBounds(15, y, 25, 30);
        
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panel.add(iconLabel); 
        panel.add(label);
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

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, "Confirmar", true);
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

        JButton btnSi = new JButton("Eliminar");
        btnSi.setPreferredSize(new Dimension(100, 35));
        btnSi.setBackground(new Color(220, 50, 50));
        btnSi.setForeground(Color.WHITE);
        btnSi.addActionListener(e -> {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });

        JButton btnNo = new JButton("Cancelar");
        btnNo.setPreferredSize(new Dimension(100, 35));
        btnNo.setBackground(new Color(150, 150, 150));
        btnNo.setForeground(Color.WHITE);
        btnNo.addActionListener(e -> dialogo.dispose());

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);

        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
}
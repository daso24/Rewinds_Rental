package view;

import javax.swing.*; 
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;


public class clientes extends JFrame {
  
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnFiltrar, btnEliminar, btnAtras;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modelo;
    public TableRowSorter<DefaultTableModel> sorter;

    private final Font INTER_BOLD_26 = new Font("Inter", Font.BOLD, 26);
    private final Font INTER_BOLD_16 = new Font("Inter", Font.BOLD, 16);
    private final Font INTER_BOLD_14 = new Font("Inter", Font.BOLD, 14);
    private final Font INTER_BOLD_13 = new Font("Inter", Font.BOLD, 13);
    private final Font INTER_REGULAR_13 = new Font("Inter", Font.PLAIN, 13);

    public clientes() {
        setTitle("Gestión de Clientes");
        setMinimumSize(new Dimension(1000, 650)); 
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

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

        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        btnAtras = new JButton(" Atrás") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 220, 220)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        try {
            btnAtras.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
        btnAtras.setPreferredSize(new Dimension(110, 35));
        btnAtras.setFont(INTER_BOLD_13);
        btnAtras.setForeground(new Color(45, 59, 72));
        btnAtras.setContentAreaFilled(false);
        btnAtras.setBorderPainted(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("Clientes", SwingConstants.CENTER);
        titulo.setFont(INTER_BOLD_26);

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
        btnAgregar.setPreferredSize(new Dimension(200, 40));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(INTER_BOLD_14);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerPanel.add(btnAtras, BorderLayout.WEST);
        headerPanel.add(titulo, BorderLayout.CENTER);
        headerPanel.add(btnAgregar, BorderLayout.EAST);

        JPanel centerContent = new JPanel(new BorderLayout(0, 15));
        centerContent.setOpaque(false);
        
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridx = 0;
        searchPanel.add(new JLabel("Buscar:") {{ setFont(INTER_BOLD_13); }}, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buscador = new JTextField();
        buscador.setFont(INTER_REGULAR_13);
        searchPanel.add(buscador, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        btnBuscar = crearBotonSimple("Buscar", new Color(45, 59, 72));
        btnBuscar.setPreferredSize(new Dimension(100, 30));
        searchPanel.add(btnBuscar, gbc);
        gbc.gridx = 3; 
        btnFiltrar = crearBotonSimple("Filtrar", new Color(0, 170, 255));
        btnFiltrar.setPreferredSize(new Dimension(100, 30));
        searchPanel.add(btnFiltrar, gbc);

        centerContent.add(searchPanel, BorderLayout.NORTH);

        String[] columnas = {"", "Nombre", "ID", "Rentas activas", "Última compra", "Última renta", "Acción"};
        Object infoIcon = getImg("/img/Vector.png"); 

        Object[][] datos = {
            {false, "Mateo Valeriano Soler", "482915", "3", "08 / 07 / 2025", "19 / 03 / 2026", new Object[]{infoIcon, "Ver info"}},
            {false, "Lucía Fernanda Mondragón", "730642", "5", "02 / 02 / 2026", "23 / 03 / 2026", new Object[]{infoIcon, "Ver info"}},
            {false, "Adrián Celis Olavarría", "105422", "2", "20 / 01 / 2026", "20 / 03 / 2026", new Object[]{infoIcon, "Ver info"}},
            {false, "Elena Beatriz Iturbide", "195873", "1", "08 / 07 / 2025", "22 / 03 / 2026", new Object[]{infoIcon, "Ver info"}},
            {false, "Tobías Martínez", "627104", "2", "08 / 11 / 2025", "24 / 03 / 2026", new Object[]{infoIcon, "Ver info"}}
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override public Class<?> getColumnClass(int c) {
                return (c == 0) ? Boolean.class : Object.class;
            }
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(50);
        tabla.setFont(INTER_REGULAR_13);
        tabla.getTableHeader().setFont(INTER_BOLD_13);
        tabla.getColumnModel().getColumn(6).setCellRenderer(new IconTextHorizontalRenderer());
        
        tabla.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int col = tabla.columnAtPoint(e.getPoint());
                if (col == 6) tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
                else tabla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        centerContent.add(scroll, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);

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
        btnEliminar.setPreferredSize(new Dimension(240, 40));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(INTER_BOLD_14);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerPanel.add(btnEliminar);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerContent, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    public void mostrarConfirmacionEliminar(String mensaje, ActionListener accionSi) {
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
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(INTER_BOLD_16);
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        panelContenido.add(Box.createVerticalGlue());
        try {
            JLabel iconoCentro = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}
        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setOpaque(false);
        JButton btnSi = crearBotonDialogo("Eliminar", new Color(220, 50, 50));
        btnSi.addActionListener(e -> { dialogo.dispose(); accionSi.actionPerformed(e); });
        JButton btnNo = crearBotonDialogo("Cancelar", new Color(130, 130, 130));
        btnNo.addActionListener(e -> dialogo.dispose());
        panelBotones.add(btnSi); panelBotones.add(btnNo);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private JButton crearBotonSimple(String texto, Color color) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setForeground(Color.WHITE);
        btn.setFont(INTER_BOLD_13);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton crearBotonDialogo(String texto, Color color) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setPreferredSize(new Dimension(110, 35));
        btn.setForeground(Color.WHITE);
        btn.setFont(INTER_BOLD_13);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public JLabel Menu(JPanel panel, String texto, String ruta) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));
        JLabel iconLabel = new JLabel();
        try {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                    .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(new Color(4, 180, 255));
        label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.add(iconLabel);
        item.add(Box.createVerticalStrut(5));
        item.add(label);
        panel.add(item);
        return label;
    }

    private ImageIcon getImg(String ruta) {
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } catch (Exception e) { return null; }
    }

    class IconTextHorizontalRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIconTextGap(8);
            label.setFont(INTER_REGULAR_13);
            label.setForeground(new Color(70, 70, 70));
            if (value instanceof Object[]) {
                Object[] data = (Object[]) value;
                label.setIcon((Icon) data[0]);
                label.setText((String) data[1]);
            }
            return label;
        }
    }

    class RoundedBorder implements Border {
        int r; RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(5, 10, 5, 10); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) { 
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, w - 1, h - 1, r, r); 
        }
    }
}
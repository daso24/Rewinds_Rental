package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class videojuegos extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnEliminar, btnFiltrar, btnAtras; 
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modelo;
    public TableRowSorter<DefaultTableModel> sorter;

    private final Font INTER_BOLD_26 = new Font("Inter", Font.BOLD, 26);
    private final Font INTER_BOLD_16 = new Font("Inter", Font.BOLD, 16);
    private final Font INTER_BOLD_14 = new Font("Inter", Font.BOLD, 14);
    private final Font INTER_BOLD_13 = new Font("Inter", Font.BOLD, 13);
    private final Font INTER_REGULAR_13 = new Font("Inter", Font.PLAIN, 13);

    public videojuegos() {
        setTitle("Catálogo de Videojuegos");
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

        JLabel titulo = new JLabel("Videojuegos", SwingConstants.CENTER);
        titulo.setFont(INTER_BOLD_26);

        btnAgregar = new JButton("+ Añadir Videojuego") {
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
        btnAgregar.setPreferredSize(new Dimension(220, 40));
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
        gbc.gridx = 0; searchPanel.add(new JLabel("Buscar:") {{ setFont(INTER_BOLD_13); }}, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        buscador = new JTextField(); buscador.setFont(INTER_REGULAR_13); searchPanel.add(buscador, gbc);
        gbc.gridx = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        btnBuscar = crearBotonSimple("Buscar", new Color(45, 59, 72));
        btnBuscar.setPreferredSize(new Dimension(100, 30)); searchPanel.add(btnBuscar, gbc);
        gbc.gridx = 3; btnFiltrar = crearBotonSimple("Filtrar", new Color(0, 170, 255));
        btnFiltrar.setPreferredSize(new Dimension(100, 30)); searchPanel.add(btnFiltrar, gbc);

        centerContent.add(searchPanel, BorderLayout.NORTH);

        String[] columnas = {"", "Carátula", "Título", "Id", "Tipo", "Plataforma", "Precio Renta", "Info"};
        Icon iconoConsola = getImg("/img/carbon_game-console.png", 40, 40);
        Icon iconoVector = getImg("/img/Vector.png", 20, 20);

        Object[][] datos = {
            {false, getImg("/img/71fw9QnEQUL.jpg", 50, 60), "Marvel's Spider-man 2", "JUE-00064", new Object[]{iconoConsola, "Videojuego"}, "Playstation 5", "$50.00", new Object[]{iconoVector, "Ver info"}},
            {false, getImg("/img/The_Legend_of_Zelda_Tears_of_the_Kingdom_cover.jpg", 50, 60), "The Legend of Zelda: TotK", "JUE-00024", new Object[]{iconoConsola, "Videojuego"}, "Nintendo Switch", "$60.00", new Object[]{iconoVector, "Ver info"}},
            {false, getImg("/img/Tapa-GTA-V.jpg", 50, 60), "GTA V", "JUE-00044", new Object[]{iconoConsola, "Videojuego"}, "Xbox", "$55.00", new Object[]{iconoVector, "Ver info"}},
            {false, getImg("/img/forza_horizon_6-6006996.jpg", 50, 60), "Forza Horizon 6", "JUE-00084", new Object[]{iconoConsola, "Videojuego"}, "Xbox", "$79.00", new Object[]{iconoVector, "Ver info"}},
            {false, getImg("/img/resident.jpg", 50, 60), "Resident Evil Requiem", "JUE-00087", new Object[]{iconoConsola, "Videojuego"}, "Playstation 5", "$79.00", new Object[]{iconoVector, "Ver info"}}
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int c) {
                if(c == 0) return Boolean.class;
                if(c == 1) return Icon.class;
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(95);
        tabla.getColumnModel().getColumn(4).setCellRenderer(new IconArribaRenderer());
        tabla.getColumnModel().getColumn(7).setCellRenderer(new IconTextHorizontalRenderer());

        tabla.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int col = tabla.columnAtPoint(e.getPoint());
                if (col == 7) tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
                else tabla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        centerContent.add(scroll, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        btnEliminar = new JButton("Eliminar Seleccionados") {
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

    class IconArribaRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setHorizontalTextPosition(JLabel.CENTER); 
            label.setVerticalTextPosition(JLabel.BOTTOM);    
            label.setIconTextGap(5);
            if (value instanceof Object[]) {
                Object[] data = (Object[]) value;
                label.setIcon((Icon) data[0]);
                label.setText((String) data[1]);
            }
            return label;
        }
    }

    class IconTextHorizontalRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIconTextGap(10);
            if (value instanceof Object[]) {
                Object[] data = (Object[]) value;
                label.setIcon((Icon) data[0]);
                label.setText((String) data[1]);
            }
            return label;
        }
    }

    public JLabel Menu(JPanel panel, String texto, String ruta) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));
        JLabel iconLabel = new JLabel();
        try { 
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH))); 
        } catch(Exception e) {}
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(new Color(4, 180, 255)); label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.add(iconLabel); item.add(Box.createVerticalStrut(5)); item.add(label);
        panel.add(item);
        return label;
    }

    private ImageIcon getImg(String ruta, int w, int h) {
        try { return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)); } 
        catch (Exception e) { return null; }
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
        btn.setForeground(Color.WHITE); btn.setFont(INTER_BOLD_13);
        btn.setContentAreaFilled(false); btn.setBorderPainted(false);
        btn.setFocusPainted(false); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205); 
        dialogo.setLocationRelativeTo(this);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        dialogo.setContentPane(contenedor);

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(5));
        
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(INTER_BOLD_16);
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        
        try {
            JLabel iconoCentro = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(Box.createVerticalGlue());
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}
        
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
        btn.setForeground(Color.WHITE); btn.setFont(INTER_BOLD_13);
        btn.setContentAreaFilled(false); btn.setBorderPainted(false);
        btn.setFocusPainted(false); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    class RoundedBorder implements Border {
        int r;
        RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(5, 10, 5, 10); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }
}
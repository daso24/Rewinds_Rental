package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.net.URL;

public class AñadirPelicula extends JFrame {

    public JTextField txtNombre, txtId, txtVenta, txtRenta, txtDescuento;
    public JComboBox<String> cbPlataforma, cbStockVenta, cbStockRenta, cbClasif, cbAnio;
    public JButton btnAgregar, btnAtras;
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;

    public AñadirPelicula() {
        setTitle("Añadir Película");
        setMinimumSize(new Dimension(1100, 700));
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true); 
        getContentPane().setBackground(Color.WHITE);

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo3.png")));
        } catch (Exception e) {}

        setLayout(new BorderLayout());

        // Sidebar lateral
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

        
        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setOpaque(false);
        add(mainContainer, BorderLayout.CENTER);

        
        JPanel panelGris = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panelGris.setOpaque(false);
        panelGris.setPreferredSize(new Dimension(850, 600));

       
        mainContainer.add(panelGris, new GridBagConstraints());

        
        btnAtras = crearBotonRedondo("  Atrás", new Color(225, 225, 225), new Color(45, 59, 72));
        btnAtras.setBounds(20, 20, 110, 35);
        btnAtras.setFont(new Font("Inter", Font.BOLD, 13));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        panelGris.add(btnAtras);

        JLabel lblTitulo = new JLabel("Añadir Película", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 24));
        lblTitulo.setBounds(300, 20, 250, 35);
        panelGris.add(lblTitulo);

        crearLabel(panelGris, "Nombre del producto:", 50, 80);
        txtNombre = crearField(panelGris, "", 50, 105, 300);

        crearLabel(panelGris, "ID del producto:", 50, 150);
        txtId = crearField(panelGris, "", 50, 175, 200);

        crearLabel(panelGris, "Plataforma:", 50, 220);
        cbPlataforma = crearCombo(panelGris, new String[]{"Seleccionar...", "Blu-ray", "CD", "DVD"}, 50, 245, 200);

        crearLabel(panelGris, "Precio de venta:", 50, 290);
        txtVenta = crearField(panelGris, "$ 0.00", 50, 315, 140);

        crearLabel(panelGris, "Precio de renta (14 días):", 210, 290);
        txtRenta = crearField(panelGris, "$ 0.00", 210, 315, 140);

        crearLabel(panelGris, "Descuento:", 50, 360);
        txtDescuento = crearField(panelGris, "0%", 50, 385, 100);

        crearLabel(panelGris, "Stock:", 50, 430);
        cbStockVenta = crearCombo(panelGris, new String[]{"Venta", "1", "5", "10", "20"}, 50, 455, 110);
        cbStockRenta = crearCombo(panelGris, new String[]{"Renta", "1", "5", "10", "20"}, 170, 455, 110);

        JPanel panelFoto = new JPanel(new BorderLayout());
        panelFoto.setBounds(500, 105, 280, 330);
        panelFoto.setBackground(new Color(220, 220, 220));
        panelFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel iconSubir = new JLabel("Subir foto", SwingConstants.CENTER);
        iconSubir.setForeground(Color.DARK_GRAY);
        panelFoto.add(iconSubir, BorderLayout.CENTER);
        panelGris.add(panelFoto);

        crearLabel(panelGris, "Clasificación:", 500, 450);
        cbClasif = crearCombo(panelGris, new String[]{"C", "E", "E+10", "T", "M"}, 500, 475, 110);

        crearLabel(panelGris, "Año:", 670, 450);
        cbAnio = crearCombo(panelGris, new String[]{"2024", "2025", "2026"}, 670, 475, 110);

        btnAgregar = crearBotonRedondo("Agregar Película", new Color(0, 170, 255), Color.WHITE);
        btnAgregar.setBounds(315, 530, 220, 45);
        btnAgregar.setFont(new Font("Inter", Font.BOLD, 15));
        panelGris.add(btnAgregar);
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

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Inter", Font.BOLD, 14));
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 35);
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        p.add(f);
        return f;
    }

    private JComboBox<String> crearCombo(JPanel p, String[] items, int x, int y, int w) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBounds(x, y, w, 35);
        c.setBackground(Color.WHITE);
        p.add(c);
        return c;
    }

    private JButton crearBotonRedondo(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setForeground(fg);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        try {
            URL u = getClass().getResource(p);
            if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
    }

  
    public void mostrarAlerta(String mensaje, boolean esError) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280);
        dialogo.setLocationRelativeTo(this);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(230, 230, 230));
        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.add(Box.createVerticalStrut(30));
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblMsg);
        contenido.add(Box.createVerticalGlue());
        JButton btnOk = crearBotonRedondo("Aceptar", esError ? new Color(220, 50, 50) : new Color(0, 170, 255), Color.WHITE);
        btnOk.setPreferredSize(new Dimension(120, 38));
        btnOk.addActionListener(e -> dialogo.dispose());
        JPanel pBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pBot.setOpaque(false);
        pBot.add(btnOk);
        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.add(pBot, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }
}
package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class AñadirOperacion extends JFrame {

    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;
    public JButton btnAtras, btnGuardar, btnDescargar;
    public JTextField txtNombreCli, txtIdCli, txtIdOp, txtFechaOp, txtFechaDev, txtMonto, txtDescuento;
    public JTextField txtNomProd, txtIdProd, txtTipoProd, txtPlataforma;
    public JRadioButton rbRenta, rbVenta;

    public AñadirOperacion() {
        setTitle("Añadir Operación");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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

        lblInicio = Menu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        lblOperacion = Menu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        lblClientes = Menu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        lblVideojuegos = Menu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        lblPeliculas = Menu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel fondoBlanco = new JPanel();
        fondoBlanco.setBounds(160, 0, 840, 650);
        fondoBlanco.setBackground(Color.WHITE);
        fondoBlanco.setLayout(null);
        add(fondoBlanco);

        btnAtras = new RoundedButton("  Atrás", 20);
        btnAtras.setBounds(20, 15, 120, 35);
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAtras.setBackground(new Color(225, 225, 225));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        fondoBlanco.add(btnAtras);

        JLabel lblTitulo = new JLabel("Generar Operación", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(300, 15, 240, 35);
        fondoBlanco.add(lblTitulo);

        JLabel logo = new JLabel();
        logo.setBounds(720, 5, 90, 60);
        cargarIconoLabel(logo, "/img/logo3.png", 90, 60);
        fondoBlanco.add(logo);

        JPanel panelGris = new JPanel();
        panelGris.setBounds(20, 70, 800, 480);
        panelGris.setBackground(new Color(209, 209, 209));
        panelGris.setLayout(null);
        fondoBlanco.add(panelGris);

        txtNombreCli = crearCampo(panelGris, "Nombre del cliente:", 25, 15, 260, "Adrián Celis Olavarría");
        txtIdCli = crearCampo(panelGris, "ID de cliente:", 25, 80, 150, "105422");
        txtIdOp = crearCampo(panelGris, "ID de la operación:", 25, 145, 150, "00045621");

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(25, 205, 100, 20);
        lblTipo.setFont(new Font("Arial", Font.BOLD, 14));
        panelGris.add(lblTipo);

        JPanel bgTipo = new JPanel();
        bgTipo.setBounds(25, 225, 175, 30);
        bgTipo.setBackground(Color.WHITE);
        bgTipo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        bgTipo.setLayout(null);
        panelGris.add(bgTipo);

        rbRenta = new JRadioButton("Renta", false);
        rbRenta.setBounds(5, 0, 80, 30);
        rbRenta.setOpaque(false);
        rbVenta = new JRadioButton("Venta", true);
        rbVenta.setBounds(85, 0, 80, 30);
        rbVenta.setOpaque(false);
        ButtonGroup bgGroup = new ButtonGroup();
        bgGroup.add(rbRenta); bgGroup.add(rbVenta);
        bgTipo.add(rbRenta); bgTipo.add(rbVenta);

        txtFechaOp = crearCampo(panelGris, "Fecha de operación:", 25, 260, 155, "24 / 09 / 2026");
        txtFechaDev = crearCampo(panelGris, "Fecha de devolución:", 25, 325, 155, "No aplica");
        txtMonto = crearCampo(panelGris, "Monto pagado:", 25, 390, 155, "$ 250.00");
        txtDescuento = crearCampo(panelGris, "Descuento:", 210, 390, 150, "0%");

        JLabel lblImg = new JLabel();
        lblImg.setBounds(460, 25, 310, 260);
        lblImg.setBackground(Color.DARK_GRAY);
        lblImg.setOpaque(true); 
        lblImg.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelGris.add(lblImg);

        txtNomProd = crearCampoSimple(panelGris, "Nombre del producto:", 460, 290, 310, "Chainsaw Man - La película");
        txtIdProd = crearCampoSimple(panelGris, "ID del producto:", 460, 350, 150, "PEL-10024");
        txtTipoProd = crearCampoSimple(panelGris, "Tipo:", 620, 350, 150, "Película");

        btnDescargar = new RoundedButton("Descargar Ficha PDF  ", 15);
        btnDescargar.setBounds(580, 425, 190, 35);
        btnDescargar.setBackground(new Color(0, 180, 255));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setHorizontalTextPosition(SwingConstants.LEFT);
        cargarIconoBoton(btnDescargar, "/img/pdf_icon.png", 22, 22);
        panelGris.add(btnDescargar);

        btnGuardar = new RoundedButton("Guardar Operación", 10);
        btnGuardar.setBounds(330, 560, 200, 40);
        btnGuardar.setBackground(Color.BLACK);
        btnGuardar.setForeground(Color.WHITE);
        fondoBlanco.add(btnGuardar);
    }

    public void mostrarAlerta(String mensaje, boolean esError) {
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

        try {
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel iconoCentro = new JLabel(imagenAlerta);
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(Box.createVerticalGlue());
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}

        panelContenido.add(Box.createVerticalGlue());

        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(120, 35));
        btnOk.setBackground(esError ? new Color(220, 50, 50) : new Color(0, 170, 255));
        btnOk.setForeground(Color.WHITE);
        btnOk.addActionListener(e -> dialogo.dispose());
        
        JPanel pBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pBot.setOpaque(false);
        pBot.add(btnOk);

        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(pBot, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            if (url != null) {
                JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
                iconLabel.setBounds(15, y, 25, 30);
                panel.add(iconLabel);
            }
        } catch(Exception e) {}
        
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label);
        return label;
    }

    private JTextField crearCampo(JPanel p, String titulo, int x, int y, int w, String texto) {
        JLabel lbl = new JLabel(titulo);
        lbl.setBounds(x, y, 200, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl);
        JTextField tf = new JTextField(texto);
        tf.setBounds(x, y + 20, w, 30);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        p.add(tf);
        return tf;
    }

    private JTextField crearCampoSimple(JPanel p, String titulo, int x, int y, int w, String texto) {
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setBounds(x, y, w, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl);
        JTextField tf = new JTextField(texto);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setBounds(x, y + 20, w, 30);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(tf);
        return tf;
    }

    private void cargarIconoLabel(JLabel l, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) l.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    class RoundedButton extends JButton {
        private int radius;
        public RoundedButton(String label, int radius) {
            super(label);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
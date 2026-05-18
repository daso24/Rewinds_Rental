package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

public class AñadirClientes extends JFrame {

    public JTextField txtId, txtNombre, txtFecha, txtTelefono;
    public JButton btnAtras, btnAgregar;
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;

    public AñadirClientes() {
        setTitle("Información de Cliente");
        setMinimumSize(new Dimension(1100, 650));
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch (Exception e) {}

        setLayout(new BorderLayout());

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

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(850, 550));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        mainContainer.add(panel, gbc);

        JLabel titulo = new JLabel("Información de cliente");
        titulo.setFont(new Font("Inter", Font.BOLD, 22));
        titulo.setBounds(315, 20, 350, 30);
        panel.add(titulo);

        btnAtras = crearBotonRedondo(" Atrás", new Color(220, 220, 220), new Color(45, 59, 72));
        try {
            btnAtras.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception e) {}
        btnAtras.setBounds(30, 20, 110, 35);
        panel.add(btnAtras);

        JPanel marcoFoto = new JPanel();
        marcoFoto.setBounds(365, 80, 120, 130);
        marcoFoto.setBackground(Color.DARK_GRAY);
        marcoFoto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(marcoFoto);

        txtId = CampoEditable(panel, "ID del Cliente:", "12345", 50, 230, 170);
        txtNombre = CampoEditable(panel, "Nombre Completo:", "Juan Pérez", 240, 230, 380);
        txtFecha = CampoEditable(panel, "Fecha de nacimiento:", "01/01/2000", 640, 230, 160);
        txtTelefono = CampoEditable(panel, "Teléfono:", "1234567891", 310, 320, 230);

        btnAgregar = crearBotonRedondo("Agregar Cliente", new Color(0, 170, 255), Color.WHITE);
        btnAgregar.setFont(new Font("Inter", Font.BOLD, 14));
        btnAgregar.setBounds(325, 450, 200, 45);
        panel.add(btnAgregar);
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

    private JTextField CampoEditable(JPanel p, String titulo, String valor, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 14));
        lbl.setBounds(x, y, w, 20);
        p.add(lbl);
        JTextField txt = new JTextField(valor);
        txt.setBounds(x, y + 25, w, 30);
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        p.add(txt);
        return txt;
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

    public void mostrarExito(String mensaje, String rutaImagen) {
        mostrarAlerta(mensaje, rutaImagen);
    }

    public void mostrarError(String mensaje, String rutaImagen) {
        mostrarAlerta(mensaje, rutaImagen);
    }

    private void mostrarAlerta(String mensaje, String rutaImagen) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(380, 205);
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(225, 225, 225));

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setOpaque(false);
        cuerpo.add(Box.createVerticalStrut(30));

        JLabel texto = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        texto.setFont(new Font("Inter", Font.BOLD, 16));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        cuerpo.add(texto);

        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            cuerpo.add(Box.createVerticalStrut(5));
            JLabel lblIcono = new JLabel();
            lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
            try {
                URL url = getClass().getResource(rutaImagen);
                if (url != null) {
                    lblIcono.setIcon(new ImageIcon(new ImageIcon(url)
                        .getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
                }
            } catch (Exception e) {}
            cuerpo.add(lblIcono);
        }

        cuerpo.add(Box.createVerticalGlue());

        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelBtns.setOpaque(false);

        JButton btnAceptar = crearBotonRedondo("Aceptar", new Color(0, 51, 102), Color.WHITE);
        btnAceptar.setPreferredSize(new Dimension(120, 38));
        btnAceptar.addActionListener(e -> dialogo.dispose());
        
        panelBtns.add(btnAceptar);

        contenedor.add(cuerpo, BorderLayout.CENTER);
        contenedor.add(panelBtns, BorderLayout.SOUTH);

        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    class RoundedBorder implements Border {
        private int r;
        RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r/2, r/2, r/2, r/2); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(180, 180, 180));
            g2.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }
}
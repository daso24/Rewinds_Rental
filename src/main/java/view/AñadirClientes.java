package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AñadirClientes extends JFrame
{
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;
    public JButton btnAtras, btnAgregarCliente;
    public JTextField txtNombres, txtApellidos, txtFechaNac, txtTelefono, txtCorreo;
    public JLabel lblFoto;
    public String rutaFoto = "/img/agregar-contacto.png";

    public AñadirClientes()
    {
        setTitle("Agregar Cliente");
        setMinimumSize(new Dimension(1000, 650));
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        try
        {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        }
        catch(Exception e)
        {
        }

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 2000);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        add(sidebar);

        sidebar.add(Box.createVerticalStrut(30));

        lblInicio = Menu(sidebar, "Inicio", "/img/casaazul.png");
        lblOperacion = Menu(sidebar, "Operación", "/img/simbolomasazul.png");
        lblClientes = Menu(sidebar, "Clientes", "/img/simboloclientesazul.png");
        lblVideojuegos = Menu(sidebar, "Videojuegos", "/img/simbolovideojuegosazul.png");
        lblPeliculas = Menu(sidebar, "Peliculas", "/img/simbolopeliculasazul.png");

        JPanel contenedorPrincipal = new JPanel(null);
        contenedorPrincipal.setBackground(Color.WHITE);
        add(contenedorPrincipal);

        JPanel contenido = new JPanel(null);
        contenido.setOpaque(false);
        contenido.setSize(840, 650);
        contenedorPrincipal.add(contenido);

        btnAtras = new RoundedButton("  Atrás", 20);
        btnAtras.setBounds(20, 15, 120, 35);
        btnAtras.setFont(new Font("Inter", Font.PLAIN, 15));
        btnAtras.setBackground(new Color(225, 225, 225));
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        contenido.add(btnAtras);

        JLabel lblTitulo = new JLabel("Información de cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(280, 15, 280, 35);
        contenido.add(lblTitulo);

        JPanel panelGris = new JPanel(null)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };
        panelGris.setOpaque(false);
        panelGris.setBounds(20, 70, 800, 480);
        contenido.add(panelGris);

        lblFoto = new JLabel();
        lblFoto.setBounds(300, 30, 200, 200);
        lblFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try
        {
            ImageIcon imagenOriginal = new ImageIcon(getClass().getResource(rutaFoto));
            Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(imagenEscalada));
        }
        catch (Exception e)
        {
            lblFoto.setBackground(Color.DARK_GRAY);
            lblFoto.setOpaque(true);
        }
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblFoto.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                JFileChooser selector = new JFileChooser();
                selector.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));
                if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    rutaFoto = selector.getSelectedFile().getAbsolutePath();
                    ImageIcon icon = new ImageIcon(rutaFoto);
                    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    lblFoto.setIcon(new ImageIcon(img));
                }
            }
        });
        panelGris.add(lblFoto);

        txtNombres = crearCampoSimple(panelGris, "Nombres:", 60, 260, 320, "");
        txtApellidos = crearCampoSimple(panelGris, "Apellidos:", 420, 260, 320, "");
        txtFechaNac = crearCampoSimple(panelGris, "Fecha de nacimiento (YYYY-MM-DD):", 60, 330, 220, "");
        txtTelefono = crearCampoSimple(panelGris, "Teléfono:", 310, 330, 200, "");
        txtCorreo = crearCampoSimple(panelGris, "Correo electrónico:", 540, 330, 200, "");

        btnAgregarCliente = new RoundedButton("Agregar Cliente", 15);
        btnAgregarCliente.setBounds(300, 410, 200, 45);
        btnAgregarCliente.setBackground(new Color(0, 170, 255));
        btnAgregarCliente.setForeground(Color.WHITE);
        btnAgregarCliente.setFont(new Font("Inter", Font.BOLD, 15));
        btnAgregarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelGris.add(btnAgregarCliente);

        this.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                int w = getWidth();
                int h = getHeight();
                sidebar.setBounds(0, 0, 160, h);
                contenedorPrincipal.setBounds(160, 0, w - 160, h);
                int xCentral = (contenedorPrincipal.getWidth() - contenido.getWidth()) / 2;
                int yCentral = (contenedorPrincipal.getHeight() - contenido.getHeight()) / 2;
                contenido.setLocation(Math.max(0, xCentral), Math.max(0, yCentral));
            }
        });
    }

    public JLabel Menu(JPanel panel, String texto, String ruta)
    {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(150, 95));
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel iconLabel = new JLabel();
        try
        {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        catch(Exception e){}
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(new Color(4, 180, 255));
        label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(Box.createVerticalStrut(10));
        item.add(iconLabel);
        item.add(Box.createVerticalStrut(5));
        item.add(label);
        panel.add(item);
        return label;
    }

    private JTextField crearCampoSimple(JPanel p, String titulo, int x, int y, int w, String texto)
    {
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setBounds(x, y, w, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl);
        JTextField tf = new JTextField(texto);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setBounds(x, y + 20, w, 35);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(tf);
        return tf;
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h)
    {
        URL u = getClass().getResource(p);
        if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    public void mostrarAlerta(String mensaje, boolean esError)
    {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(this);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(230, 230, 230));
        dialogo.setContentPane(contenedor);
        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(15));
        JLabel lblMsg = new JLabel("<html><div style='text-align: center; width: 250px;'>" + mensaje + "</div></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        try
        {
            String rutaIcono = esError ? "/img/mingcute_warning-fill.png" : "/img/palomitaverde.png";
            ImageIcon imgAlerta = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel icono = new JLabel(imgAlerta);
            icono.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(Box.createVerticalGlue());
            panelContenido.add(icono);
        }
        catch (Exception e){}
        panelContenido.add(Box.createVerticalGlue());
        JButton btnOk = new RoundedButton("Aceptar", 15);
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

    class RoundedButton extends JButton
    {
        private int radius;
        public RoundedButton(String label, int radius)
        {
            super(label);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
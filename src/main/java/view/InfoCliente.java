package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.net.URL;

public class InfoCliente extends JFrame {

    public JTextField txtId, txtFecha, txtTelefono;
    public JLabel lblNombreValor, lblFoto; 
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas, lblLogoDerecha;
    public JButton btnAtras, btnEditar, btnHistoVentas, btnHistoRentas, btnDescargar, btnGenerar;

    private final Font INTER_BOLD_20 = new Font("Inter", Font.BOLD, 20);
    private final Font INTER_BOLD_16 = new Font("Inter", Font.BOLD, 16);
    private final Font INTER_BOLD_14 = new Font("Inter", Font.BOLD, 14);
    private final Font INTER_REGULAR_13 = new Font("Inter", Font.PLAIN, 13);

    public InfoCliente() {
        setTitle("Información de Cliente");
        setMinimumSize(new Dimension(1000, 750));
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            URL urlLogo = obtenerRecurso("/img/logo3.png");
            if (urlLogo != null) setIconImage(Toolkit.getDefaultToolkit().getImage(urlLogo));
        } catch(Exception e) {}

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(160, 0));
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(sidebar, BorderLayout.WEST);

        lblInicio = Menu(sidebar, "Inicio", "/img/casaazul.png");
        lblOperacion = Menu(sidebar, "Operación", "/img/simbolomasazul.png");
        lblClientes = Menu(sidebar, "Clientes", "/img/simboloclientesazul.png");
        lblVideojuegos = Menu(sidebar, "Videojuegos", "/img/simbolovideojuegosazul.png");
        lblPeliculas = Menu(sidebar, "Peliculas", "/img/simbolopeliculasazul.png");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        btnAtras = crearBotonRedondeado(" Atrás", new Color(210, 210, 210), new Color(45, 59, 72), 20);
        btnAtras.setPreferredSize(new Dimension(110, 35));
        btnAtras.setFont(INTER_REGULAR_13);
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 20, 20);
        header.add(btnAtras, BorderLayout.WEST);

        JLabel lblTituloSuperior = new JLabel("Información de cliente", SwingConstants.CENTER);
        lblTituloSuperior.setFont(INTER_BOLD_20);
        
        lblTituloSuperior.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); 
        
        header.add(lblTituloSuperior, BorderLayout.CENTER);
        
        header.add(lblTituloSuperior, BorderLayout.CENTER);

        lblLogoDerecha = new JLabel();
        lblLogoDerecha.setPreferredSize(new Dimension(60, 60));
        cargarIconoLabel(lblLogoDerecha, "/img/logo3.png", 60, 60);
        header.add(lblLogoDerecha, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        mainPanel.add(header, gbc);

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setPreferredSize(new Dimension(120, 120));
        lblFoto.setBackground(Color.DARK_GRAY);
        lblFoto.setOpaque(true);
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
        gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblFoto, gbc);

        JPanel card = new JPanel(null);
        card.setPreferredSize(new Dimension(780, 360));
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));

        lblNombreValor = new JLabel("", SwingConstants.CENTER);
        lblNombreValor.setFont(INTER_BOLD_16);
        lblNombreValor.setBounds(0, 35, 780, 20);
        card.add(lblNombreValor);

        txtId = CampoEditable(card, "ID del Cliente:", "", 140, 70, 180);
        txtFecha = CampoEditable(card, "Fecha de nacimiento:", "", 460, 70, 180);
        txtTelefono = CampoEditable(card, "Teléfono:", "", 300, 145, 180);

        btnHistoVentas = crearBotonRedondeado("Historial de ventas", new Color(45, 62, 80), Color.WHITE, 15);
        btnHistoVentas.setBounds(50, 225, 180, 35);
        card.add(btnHistoVentas);

        btnHistoRentas = crearBotonRedondeado("Historial de rentas", new Color(45, 62, 80), Color.WHITE, 15);
        btnHistoRentas.setBounds(550, 225, 180, 35);
        card.add(btnHistoRentas);

        btnDescargar = crearBotonRedondeado("Descargar Ficha [PDF]", new Color(0, 170, 255), Color.WHITE, 15);
        btnDescargar.setBounds(80, 295, 280, 35);
        card.add(btnDescargar);

        btnGenerar = crearBotonRedondeado("Generar tarjeta [PDF]", new Color(0, 170, 255), Color.WHITE, 15);
        btnGenerar.setBounds(430, 295, 280, 35);
        card.add(btnGenerar);

        gbc.gridy = 2; gbc.weighty = 0.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(card, gbc);

        btnEditar = crearBotonRedondeado("Editar Cliente", new Color(0, 170, 255), Color.WHITE, 25);
        btnEditar.setPreferredSize(new Dimension(160, 40));
        btnEditar.setFont(INTER_BOLD_14);

        gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; gbc.insets = new Insets(20, 20, 20, 20);
        mainPanel.add(btnEditar, gbc);
    }

    private URL obtenerRecurso(String ruta) {
        URL url = getClass().getResource(ruta);
        if (url == null) {
            String limpia = ruta.startsWith("/") ? ruta.substring(1) : ruta;
            url = getClass().getClassLoader().getResource(limpia);
        }
        return url;
    }

    public void setDatosCliente(String nombre, String id, String telefono, String fechaNacimiento) {
        this.lblNombreValor.setText(nombre);
        this.txtId.setText(id);
        this.txtTelefono.setText(telefono);
        this.txtFecha.setText(fechaNacimiento);
    }

    public void setImagenCliente(String ruta) {
        try {
            URL url = obtenerRecurso(ruta);
            if (url != null) {
                ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage()
                        .getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                this.lblFoto.setIcon(icon);
            } else {
                this.lblFoto.setIcon(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JTextField CampoEditable(JPanel panel, String titulo, String valor, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setBounds(x, y, w, 20);
        lbl.setFont(INTER_BOLD_14);
        panel.add(lbl);

        JTextField txt = new JTextField(valor);
        txt.setBounds(x, y + 25, w, 35);
        txt.setEditable(false); 
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setFont(INTER_REGULAR_13);
        txt.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(180, 180, 180), 1, true), BorderFactory.createEmptyBorder(0,5,0,5)));
        panel.add(txt);
        return txt;
    }

    private JLabel Menu(JPanel panel, String texto, String ruta) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));

        JLabel iconLabel = new JLabel();
        try {
            URL url = obtenerRecurso(ruta);
            if (url != null) {
                iconLabel.setIcon(new ImageIcon(new ImageIcon(url)
                    .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            }
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

    private JButton crearBotonRedondeado(String texto, Color bg, Color fg, int radio) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
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

    private void cargarIconoLabel(JLabel l, String p, int w, int h) {
        try {
            URL u = obtenerRecurso(p);
            if (u != null) l.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        try {
            URL u = obtenerRecurso(p);
            if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        } catch(Exception e) {}
    }

    public void mostrarError(String mensaje) { VentanaAlerta(mensaje, "/img/simbolotachaverde.png", new Color(220, 50, 50)); }
    public void mostrarExito(String mensaje) { VentanaAlerta(mensaje, "/img/palomitaverde.png", new Color(0, 51, 102)); }

    private void VentanaAlerta(String mensaje, String rutaIcono, Color colorBoton) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(this);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(230, 230, 230));
        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(5));
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(INTER_BOLD_16);
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        panelContenido.add(Box.createVerticalGlue());
        try {
            URL u = obtenerRecurso(rutaIcono);
            if (u != null) {
                ImageIcon img = new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
                JLabel iconoCentro = new JLabel(img);
                iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelContenido.add(iconoCentro);
            }
        } catch (Exception e) {}
        panelContenido.add(Box.createVerticalGlue());
        JButton btnOk = crearBotonRedondeado("Aceptar", colorBoton, Color.WHITE, 20);
        btnOk.setPreferredSize(new Dimension(120, 38));
        btnOk.setFont(INTER_BOLD_14);
        btnOk.addActionListener(e -> dialogo.dispose());
        JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pBotones.setOpaque(false);
        pBotones.add(btnOk);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }
}
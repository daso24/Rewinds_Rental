package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.net.URL;

public class InfoPelicula extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JTextField txtNomProd, txtIdProd, txtFormato, txtGenero, txtMonto, txtDescuento, txtFechaOp;
    public JButton btnAtras, btnDescargar, btnEditar;
    public JLabel lblImagenPelicula;
    public JTextField txtTipo, txtStockVenta, txtStockRenta, txtClasif, txtAnio;

    public InfoPelicula() {
        setTitle("Información de Película / Operación");
        setMinimumSize(new Dimension(1000, 750));
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());
        
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo3.png")));
        } catch (Exception e) {}

        JPanel barraLat = new JPanel();
        barraLat.setPreferredSize(new Dimension(160, 0));
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(barraLat, BorderLayout.WEST);

        btnInicio = crearLabelMenu(barraLat, "Inicio", "/img/casaazul.png");
        btnOperacion = crearLabelMenu(barraLat, "Operación", "/img/simbolomasazul.png");
        btnClientes = crearLabelMenu(barraLat, "Clientes", "/img/simboloclientesazul.png");
        btnVideojuegos = crearLabelMenu(barraLat, "Videojuegos", "/img/simbolovideojuegosazul.png");
        btnPeliculas = crearLabelMenu(barraLat, "Peliculas", "/img/simbolopeliculasazul.png");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        btnAtras = crearBotonRedondeado("Atrás", new Color(230, 230, 230), Color.BLACK, 20);
        btnAtras.setPreferredSize(new Dimension(110, 35));
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAtras.setBorder(new LineBorder(Color.GRAY, 1, true));
        cargarIconoBoton(btnAtras, "/img/lets-icons_back.png", 18, 18);
        header.add(btnAtras, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("Información de película", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        header.add(lblTitulo, BorderLayout.CENTER);
        
        JLabel iconoDerecha = new JLabel();
        iconoDerecha.setPreferredSize(new Dimension(80, 60));
        cargarIconoLabel(iconoDerecha, "/img/logo3.png", 80, 60); 
        header.add(iconoDerecha, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        mainPanel.add(header, gbc);

        JPanel panelGris = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };

        panelGris.setOpaque(false);
        panelGris.setPreferredSize(new Dimension(800, 550));
        panelGris.setMinimumSize(new Dimension(800, 550));

        crearLabel(panelGris, "Nombre del producto:", 30, 20);
        txtNomProd = crearField(panelGris, "Chainsaw Man - Arco de Reze", 30, 45, 200);

        crearLabel(panelGris, "ID del producto:", 30, 85);
        txtIdProd = crearField(panelGris, "PEL-10024", 30, 110, 180);

        crearLabel(panelGris, "Tipo de producto:", 30, 150);
        txtTipo = crearField(panelGris, "Película", 30, 175, 180);
        txtTipo.setEditable(false);

        crearLabel(panelGris, "Plataforma:", 30, 215);
        txtFormato = crearField(panelGris, "Blu-ray", 30, 240, 180);

        crearLabel(panelGris, "Precio de venta:", 30, 280);
        txtMonto = crearField(panelGris, "$ 250.00", 30, 305, 180);

        crearLabel(panelGris, "Descuento:", 30, 345);
        txtDescuento = crearField(panelGris, "0%", 30, 370, 180);

        crearLabel(panelGris, "Stock", 30, 415);
        JLabel lblDisVenta = new JLabel("Disponibles para venta:");
        lblDisVenta.setBounds(30, 440, 150, 20);
        panelGris.add(lblDisVenta);
        txtStockVenta = crearField(panelGris, "50", 30, 460, 180);
        
        crearLabel(panelGris, "Precio de renta (por 14 días):", 250, 280);
        txtFechaOp = crearField(panelGris, "$ 100.00", 250, 305, 180);

        JLabel lblDisRenta = new JLabel("Disponibles para renta:");
        lblDisRenta.setBounds(250, 440, 150, 20);
        panelGris.add(lblDisRenta);
        txtStockRenta = crearField(panelGris, "20", 250, 460, 180);

        JLabel lblProdTit = new JLabel("Producto:");
        lblProdTit.setBounds(500, 20, 100, 20);
        lblProdTit.setFont(new Font("Arial", Font.BOLD, 14));
        panelGris.add(lblProdTit);

        lblImagenPelicula = new JLabel();
        lblImagenPelicula.setBounds(500, 45, 260, 280); 
        lblImagenPelicula.setBackground(Color.DARK_GRAY);
        lblImagenPelicula.setOpaque(true);
        lblImagenPelicula.setBorder(new LineBorder(Color.WHITE, 2));
        cargarIconoLabel(lblImagenPelicula, "/img/chainsaw_man.png", 260, 280);
        panelGris.add(lblImagenPelicula);

        crearLabel(panelGris, "Clasificación:", 500, 335);
        txtClasif = crearField(panelGris, "B-15", 500, 355, 120);

        crearLabel(panelGris, "Año de lanzamiento:", 640, 335);
        txtAnio = crearField(panelGris, "2025", 640, 355, 120);

        crearLabel(panelGris, "Género:", 500, 395);
        txtGenero = crearField(panelGris, "Acción", 500, 415, 260);

        btnEditar = crearBotonRedondeado("Editar info de la película", new Color(45, 59, 72), Color.WHITE, 25);
        btnEditar.setBounds(30, 505, 230, 35);
        panelGris.add(btnEditar);

        btnDescargar = crearBotonRedondeado("Descargar ficha", new Color(0, 180, 255), Color.WHITE, 25);
        btnDescargar.setBounds(340, 505, 200, 35);
        cargarIconoBoton(btnDescargar, "/img/simbolopdfblanco.png", 20, 20);
        panelGris.add(btnDescargar);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 20, 10, 20);
        gbc.weighty = 1.0;

        mainPanel.add(panelGris, gbc);
    }

    public void setDatosPelicula(String nombre, String id, String tipo, String plataforma, String precioVenta, String descuento, String stockVenta, String stockRenta, String precioRenta, String clasificacion, String anio, String genero, ImageIcon caratula) {
        this.txtNomProd.setText(nombre);
        this.txtIdProd.setText(id);
        this.txtTipo.setText(tipo);
        this.txtFormato.setText(plataforma);
        this.txtMonto.setText(precioVenta);
        this.txtDescuento.setText(descuento);
        this.txtStockVenta.setText(stockVenta);
        this.txtStockRenta.setText(stockRenta);
        this.txtFechaOp.setText(precioRenta);
        this.txtClasif.setText(clasificacion);
        this.txtAnio.setText(anio);
        this.txtGenero.setText(genero);
        
        if (caratula != null) {
            Image img = caratula.getImage().getScaledInstance(this.lblImagenPelicula.getWidth(), this.lblImagenPelicula.getHeight(), Image.SCALE_SMOOTH);
            this.lblImagenPelicula.setIcon(new ImageIcon(img));
        }
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
        contenido.add(Box.createVerticalStrut(25));

        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblMsg);
        
        contenido.add(Box.createVerticalGlue());

        String rutaIcono = "";
        if (esError) {
            rutaIcono = "/img/simbolotachaverde.png"; 
        } else if (mensaje.toLowerCase().contains("ficha") || mensaje.toLowerCase().contains("descargar") || mensaje.toLowerCase().contains("generando")) {
            rutaIcono = "/img/simbolopdfblanco.png";
        } else {
            rutaIcono = "/img/palomitaverde.png";
        }

        if (!rutaIcono.isEmpty()) {
            try {
                URL urlImg = getClass().getResource(rutaIcono);
                if (urlImg != null) {
                    ImageIcon img = new ImageIcon(new ImageIcon(urlImg).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
                    JLabel lblIcono = new JLabel(img);
                    lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
                    contenido.add(lblIcono);
                }
            } catch (Exception e) {}
        }

        contenido.add(Box.createVerticalGlue());
        
        JButton btnOk = crearBotonRedondeado("Aceptar", esError ? new Color(220, 50, 50) : new Color(0, 170, 255), Color.WHITE, 20);
        btnOk.setPreferredSize(new Dimension(120, 38));
        btnOk.setFont(new Font("Inter", Font.BOLD, 13));
        btnOk.addActionListener(e -> dialogo.dispose());
        
        JPanel pBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pBot.setOpaque(false);
        pBot.add(btnOk);

        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.add(pBot, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Inter", Font.BOLD, 13));
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 30);
        f.setHorizontalAlignment(JTextField.CENTER);
        f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(f);
        return f;
    }

    private JLabel crearLabelMenu(JPanel panel, String texto, String rutaIcono) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));

        JLabel iconLabel = new JLabel();
        try {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono))
                .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
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
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void cargarIconoLabel(JLabel l, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) l.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    private void cargarIconoBoton(JButton b, String p, int w, int h) {
        URL u = getClass().getResource(p);
        if (u != null) b.setIcon(new ImageIcon(new ImageIcon(u).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }
}
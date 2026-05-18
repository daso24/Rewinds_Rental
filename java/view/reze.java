package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class reze extends JFrame {

    public JButton btnAtras, btnEditar, btnDescargar;
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JTextField nombreTxt, idTxt, tipoTxt, plataformaTxt, ventaTxt, rentaTxt, ventaStock, rentaStock, clasificacionTxt, añoTxt, generoTxt;

    public reze() {
        setTitle("Información del producto");
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

        // Sidebar
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

        // Contenedor
        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setOpaque(false);
        add(mainContainer, BorderLayout.CENTER);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(850, 550));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        mainContainer.add(panel, gbc);

        JLabel titulo = new JLabel("Información de la película");
        titulo.setFont(new Font("Inter", Font.BOLD, 22));
        titulo.setBounds(300, 20, 350, 30);
        panel.add(titulo);

        // Boton atrás 
        btnAtras = crearBotonRedondo(" Atrás", new Color(220, 220, 220), new Color(45, 59, 72));
        try {
            btnAtras.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception e) {}
        btnAtras.setFont(new Font("Inter", Font.BOLD, 13));
        btnAtras.setBounds(30, 20, 110, 35);
        panel.add(btnAtras);

        // Campos de información
        nombreTxt = crearCampo(panel, "Nombre de la película:", 50, 80, 250, "Chainsaw-man Arco de Reze");
        idTxt = crearCampo(panel, "ID del producto:", 50, 155, 250, "PEL-10024");
        tipoTxt = crearCampo(panel, "Tipo de producto:", 50, 230, 250, "Película");
        plataformaTxt = crearCampo(panel, "Plataforma:", 50, 305, 250, "Blu-ray");
        ventaTxt = crearCampo(panel, "Precio Venta:", 50, 385, 110, "$ 250.00");
        rentaTxt = crearCampo(panel, "Precio Renta:", 190, 385, 110, "$ 100.00");

        JLabel stockLbl = new JLabel("Stock Disponible (V/R):");
        stockLbl.setFont(new Font("Inter", Font.BOLD, 15));
        stockLbl.setBounds(340, 385, 200, 20);
        panel.add(stockLbl);

        ventaStock = new JTextField("V: 50");
        ventaStock.setBounds(340, 410, 80, 30);
        ventaStock.setEditable(false);
        panel.add(ventaStock);

        rentaStock = new JTextField("R: 20");
        rentaStock.setBounds(430, 410, 80, 30);
        rentaStock.setEditable(false);
        panel.add(rentaStock);

        clasificacionTxt = crearCampoCentro(panel, "Clasificación:", 350, 80, 130, "B-15");
        añoTxt = crearCampoCentro(panel, "Lanzamiento:", 350, 155, 130, "2025");
        generoTxt = crearCampoCentro(panel, "Género:", 350, 230, 130, "Acción");

        JLabel imagen = new JLabel();
        try {
            ImageIcon portada = new ImageIcon(getClass().getResource("/img/71w58zkWnfL.jpg"));
            Image imgScale = portada.getImage().getScaledInstance(200, 280, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(imgScale));
        } catch(Exception e) {}
        imagen.setBounds(550, 105, 200, 280);
        panel.add(imagen);

        btnEditar = crearBotonRedondo("Editar Película", new Color(52, 73, 94), Color.WHITE);
        btnEditar.setBounds(50, 480, 200, 40);
        btnEditar.setFont(new Font("Inter", Font.BOLD, 14));
        panel.add(btnEditar);

        btnDescargar = crearBotonRedondo("Descargar Reporte PDF", new Color(0, 170, 255), Color.WHITE);
        btnDescargar.setBounds(280, 480, 200, 40);
        btnDescargar.setFont(new Font("Inter", Font.BOLD, 14));
        panel.add(btnDescargar);
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

    private JTextField crearCampo(JPanel p, String titulo, int x, int y, int w, String texto) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 15));
        lbl.setBounds(x, y, 200, 20);
        p.add(lbl);
        JTextField txt = new JTextField(texto);
        txt.setBounds(x, y + 25, w, 30);
        txt.setEditable(false);
        txt.setFont(new Font("Inter", Font.PLAIN, 14));
        p.add(txt);
        return txt;
    }

    private JTextField crearCampoCentro(JPanel p, String titulo, int x, int y, int w, String texto) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 15));
        lbl.setBounds(x, y, 200, 20);
        p.add(lbl);
        JTextField txt = new JTextField(texto);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBounds(x, y + 25, w, 30);
        txt.setEditable(false);
        txt.setFont(new Font("Inter", Font.PLAIN, 14));
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

    public void mostrarConfirmacion(String mensaje, ActionListener accionSi) {

        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(380, 300);
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(225, 225, 225));

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setOpaque(false);

        cuerpo.add(Box.createVerticalStrut(40));

        JLabel texto = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        texto.setFont(new Font("Inter", Font.BOLD, 16));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        cuerpo.add(texto);

        // ESPACIO
        cuerpo.add(Box.createVerticalStrut(20));

        // IMAGEN
        ImageIcon icono = new ImageIcon(getClass().getResource("/img/simbolomasazul.png"));
        Image img = icono.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        JLabel imagen = new JLabel(new ImageIcon(img));
        imagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        cuerpo.add(imagen);

        cuerpo.add(Box.createVerticalGlue());

        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 20));
        panelBtns.setOpaque(false);

        JButton btnSi = crearBotonRedondo("Confirmar", new Color(0, 51, 102), Color.WHITE);
        btnSi.setPreferredSize(new Dimension(120, 38));
        btnSi.addActionListener(e -> {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });

        JButton btnNo = crearBotonRedondo("Cancelar", new Color(130, 130, 130), Color.WHITE);
        btnNo.setPreferredSize(new Dimension(120, 38));
        btnNo.addActionListener(e -> dialogo.dispose());

        panelBtns.add(btnSi);
        panelBtns.add(btnNo);

        contenedor.add(cuerpo, BorderLayout.CENTER);
        contenedor.add(panelBtns, BorderLayout.SOUTH);

        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }
}
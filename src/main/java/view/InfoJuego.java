package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class InfoJuego extends JFrame {

    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;
    public JButton btnAtras, btnDescargar, btnEditar;
    public JTextField txtNombreProd, txtIdProd, txtPlataforma, txtGenero, txtEstado, txtStock;
    public JTextField txtTipoProd, txtPrecioVenta, txtPrecioRenta, txtDescuento, txtClasificacion, txtAnio, txtStockRenta;
    public JLabel lblImg; 

    public InfoJuego() {
        setTitle("Información de Videojuego");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo3.png")));
        
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

        JLabel titulo = new JLabel("Información de videojuego", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(250, 15, 350, 35);
        fondoBlanco.add(titulo);
        
        JLabel logoDerecha = new JLabel();
        logoDerecha.setBounds(730, 5, 80, 60);
        cargarIconoLabel(logoDerecha, "/img/logo3.png", 80, 60);
        fondoBlanco.add(logoDerecha);

        JPanel panelGris = new JPanel();
        panelGris.setBounds(20, 70, 800, 520);
        panelGris.setBackground(new Color(209, 209, 209));
        panelGris.setLayout(null);
        fondoBlanco.add(panelGris);

        txtNombreProd = crearCampo(panelGris, "Nombre del producto:", 30, 20, 180);
        txtIdProd = crearCampo(panelGris, "ID del producto:", 30, 80, 180);
        txtTipoProd = crearCampo(panelGris, "Tipo de producto:", 30, 140, 180);
        txtPlataforma = crearCampo(panelGris, "Plataforma:", 30, 200, 180);
        txtPrecioVenta = crearCampo(panelGris, "Precio de venta:", 30, 260, 180);
        txtDescuento = crearCampo(panelGris, "Descuento:", 30, 320, 180);
        
        txtStock = crearCampo(panelGris, "Disponibles para venta:", 30, 390, 180);
        txtStockRenta = crearCampo(panelGris, "Disponibles para renta:", 250, 390, 180);
        
        txtPrecioRenta = crearCampo(panelGris, "Precio de renta (por 14 días):", 250, 260, 180);

        JLabel lblProductoImg = new JLabel("Producto:");
        lblProductoImg.setBounds(500, 20, 100, 20);
        lblProductoImg.setFont(new Font("Arial", Font.BOLD, 14));
        panelGris.add(lblProductoImg);

        lblImg = new JLabel();
        lblImg.setBounds(500, 45, 260, 280);
        lblImg.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        lblImg.setBackground(Color.LIGHT_GRAY);
        lblImg.setOpaque(true);
        panelGris.add(lblImg);

        txtClasificacion = crearCampo(panelGris, "Clasificación:", 500, 335, 120);
        txtAnio = crearCampo(panelGris, "Año de lanzamiento:", 640, 335, 120);
        txtGenero = crearCampo(panelGris, "Género:", 500, 390, 260);

        btnEditar = new RoundedButton("Editar info de videojuego", 10);
        btnEditar.setBounds(30, 465, 230, 35);
        btnEditar.setBackground(new Color(45, 59, 72));
        btnEditar.setForeground(Color.WHITE);
        panelGris.add(btnEditar);

        btnDescargar = new RoundedButton("Descargar ficha", 10);
        btnDescargar.setBounds(340, 465, 200, 35);
        btnDescargar.setBackground(new Color(0, 180, 255));
        btnDescargar.setForeground(Color.WHITE);
        cargarIconoBoton(btnDescargar, "/img/teenyicons_pdf-solid.png", 22, 22);
        btnDescargar.addActionListener(e -> mostrarAlertaPersonalizada("Generando el archivo PDF..."));
        panelGris.add(btnDescargar);
    }

    public void mostrarAlertaPersonalizada(String mensaje) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 200);
        dialogo.setLocationRelativeTo(this);

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        p.setBackground(new Color(209, 209, 209));

        JLabel msg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnOk = new JButton("Aceptar");
        btnOk.setBackground(new Color(0, 180, 255));
        btnOk.setForeground(Color.WHITE);
        btnOk.addActionListener(e -> dialogo.dispose());

        JPanel pBot = new JPanel(new FlowLayout());
        pBot.setOpaque(false);
        pBot.add(btnOk);

        p.add(msg, BorderLayout.CENTER);
        p.add(pBot, BorderLayout.SOUTH);

        dialogo.add(p);
        dialogo.setVisible(true);
    }

    public void mostrarConfirmacion(String mensaje, ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, "Confirmación", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 200);
        dialogo.setLocationRelativeTo(this);

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        p.setBackground(new Color(209, 209, 209));

        JLabel msg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel pBotones = new JPanel(new FlowLayout());
        pBotones.setOpaque(false);

        JButton si = new JButton("Confirmar");
        JButton no = new JButton("Cancelar");
        si.setBackground(new Color(0, 51, 102));
        si.setForeground(Color.WHITE);
        no.setBackground(Color.WHITE);

        si.addActionListener(e -> {
            dialogo.dispose();
            if (accionSi != null) accionSi.actionPerformed(e);
        });

        no.addActionListener(e -> dialogo.dispose());

        pBotones.add(si); 
        pBotones.add(no);
        p.add(msg, BorderLayout.CENTER);
        p.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(p);
        dialogo.setVisible(true);
    }

    private JTextField crearCampo(JPanel p, String titulo, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo);
        lbl.setBounds(x, y, w + 50, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        p.add(lbl);
        JTextField tf = new JTextField();
        tf.setBounds(x, y + 20, w, 30);
        tf.setEditable(false);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(tf);
        return tf;
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
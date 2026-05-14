package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class AñadirClientes extends JFrame {

    public JTextField txtId, txtFecha, txtTelefono;
    public JButton btnAtras, btnEditar;
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;

    public AñadirClientes() {
        setTitle("Información de Cliente");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 700);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        lblInicio = crearLabelMenu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        lblOperacion = crearLabelMenu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        lblClientes = crearLabelMenu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        lblVideojuegos = crearLabelMenu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        lblPeliculas = crearLabelMenu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setBackground(new Color(220, 220, 220));
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        mainPanel.add(btnAtras);

        JLabel lblTituloSuperior = new JLabel("Información de cliente", SwingConstants.CENTER);
        lblTituloSuperior.setFont(new Font("Inter", Font.BOLD, 20));
        lblTituloSuperior.setBounds(160, 20, 520, 30);
        mainPanel.add(lblTituloSuperior);

        JPanel marcoFoto = new JPanel();
        marcoFoto.setBounds(360, 85, 120, 130);
        marcoFoto.setBackground(Color.DARK_GRAY); 
        marcoFoto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        mainPanel.add(marcoFoto);

        JPanel card = new JPanel();
        card.setBounds(30, 230, 780, 360);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        mainPanel.add(card);

        txtId = CampoEditable(card, "ID del Cliente:", "12345", 50, 70, 180);
        txtFecha = CampoEditable(card, "Fecha de nacimiento:", "01/01/2000", 300, 70, 180);
        txtTelefono = CampoEditable(card, "Teléfono:", "1234567891", 550, 70, 180);

        btnEditar = new JButton("Agregar Cliente");
        btnEditar.setBounds(340, 610, 160, 35);
        btnEditar.setBackground(new Color(0, 170, 255));
        btnEditar.setForeground(Color.WHITE);
        mainPanel.add(btnEditar);
    }

    private JTextField CampoEditable(JPanel panel, String titulo, String valor, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setBounds(x, y, w, 20);
        panel.add(lbl);

        JTextField txt = new JTextField(valor);
        txt.setBounds(x, y + 25, w, 35);
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
   
        txt.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15), 
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        panel.add(txt);
        return txt;
    }

    private JLabel crearLabelMenu(JPanel panel, String texto, int y, String rutaIcono) {
        try {
            ImageIcon icono = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono))
                    .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            JLabel iconLabel = new JLabel(icono);
            iconLabel.setBounds(15, y, 25, 30);
            panel.add(iconLabel);
        } catch (Exception e) {}

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label);
        return label;
    }

    public void mostrarError(String mensaje) {
        VentanaAlerta(mensaje, "/img/mingcute_warning-fill.png", new Color(220, 50, 50));
    }

    public void mostrarExito(String mensaje) {
        VentanaAlerta(mensaje, "/img/mingcute_warning-fill.png", new Color(0, 170, 255));
    }

    private void VentanaAlerta(String mensaje, String rutaIcono, Color colorBoton) {
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

        panelContenido.add(Box.createVerticalGlue());

        try {
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel iconoCentro = new JLabel(imagenAlerta);
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}

        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBotones.setOpaque(false);

        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(120, 35));
        btnOk.setBackground(colorBoton);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(e -> dialogo.dispose());
        panelBotones.add(btnOk);

        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);

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
            g2.setColor(new Color(150, 150, 150)); 
            g2.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }
}
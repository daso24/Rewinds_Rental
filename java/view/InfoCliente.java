package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfoCliente extends JFrame {

    public JTextField txtId, txtFecha, txtTelefono;
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;
    public JButton btnAtras, btnEditar, btnHistoVentas, btnHistoRentas, btnDescargar, btnGenerar;

    public InfoCliente() {
        setTitle("Información de Cliente");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // BARRA LATERAL
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 700);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        lblInicio = Menu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        lblOperacion = Menu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        lblClientes = Menu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        lblVideojuegos = Menu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        lblPeliculas = Menu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setBackground(new Color(220, 220, 220));
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        mainPanel.add(btnAtras);

        JLabel lblTituloSuperior = new JLabel("Información de cliente", SwingConstants.CENTER);
        lblTituloSuperior.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloSuperior.setBounds(160, 20, 520, 30);
        mainPanel.add(lblTituloSuperior);

        JPanel marcoFoto = new JPanel();
        marcoFoto.setBounds(360, 85, 120, 130);
        marcoFoto.setBackground(Color.DARK_GRAY); 
        marcoFoto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        mainPanel.add(marcoFoto);

        // TARJETA CENTRAL
        JPanel card = new JPanel();
        card.setBounds(30, 230, 780, 360);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        mainPanel.add(card);

        JLabel lblNombreValor = new JLabel("Tobias Martinez", SwingConstants.CENTER);
        lblNombreValor.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombreValor.setBounds(0, 35, 780, 20);
        card.add(lblNombreValor);

        txtId = CampoEditable(card, "ID del Cliente:", "123", 50, 70, 180);
        txtFecha = CampoEditable(card, "Fecha de nacimiento:", "27/08/2000", 300, 70, 180);
        txtTelefono = CampoEditable(card, "Teléfono:", "1234567891", 550, 70, 180);

        btnHistoVentas = new JButton("Historial de ventas");
        btnHistoVentas.setBounds(50, 210, 180, 35);
        btnHistoVentas.setBackground(new Color(45, 62, 80));
        btnHistoVentas.setForeground(Color.WHITE);
        card.add(btnHistoVentas);

        btnHistoRentas = new JButton("Historial de rentas");
        btnHistoRentas.setBounds(550, 210, 180, 35);
        btnHistoRentas.setBackground(new Color(45, 62, 80));
        btnHistoRentas.setForeground(Color.WHITE);
        card.add(btnHistoRentas);

        btnDescargar = new JButton("Descargar Ficha [PDF]");
        btnDescargar.setBounds(80, 290, 280, 30);
        btnDescargar.setBackground(new Color(0, 170, 255));
        btnDescargar.setForeground(Color.WHITE);
        card.add(btnDescargar);

        btnGenerar = new JButton("Generar tarjeta [PDF]");
        btnGenerar.setBounds(430, 290, 280, 30);
        btnGenerar.setBackground(new Color(0, 170, 255));
        btnGenerar.setForeground(Color.WHITE);
        card.add(btnGenerar);

        btnEditar = new JButton("Editar Cliente");
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
        txt.setBorder(new RoundedBorder(15));
        panel.add(txt);
        return txt;
    }

    private JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(iconLabel); panel.add(label);
        return label;
    }

    class RoundedBorder implements Border {
        int r; RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r/2, r, r/2, r); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.setColor(new Color(180, 180, 180));
            g.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }

    public void mostrarError(String mensaje) {
        VentanaAlerta(mensaje, "/img/mingcute_warning-fill.png", new Color(220, 50, 50));
    }

    public void mostrarExito(String mensaje) {
        VentanaAlerta(mensaje, "/img/gravity-ui_circle-check-fill.png", new Color(0, 170, 255));
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
        } catch (Exception e) {
            System.err.println("No se pudo cargar el icono: " + rutaIcono);
        }

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
}
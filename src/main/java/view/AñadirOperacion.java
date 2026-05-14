package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AñadirOperacion extends JFrame {

    public JTextField txtNombreCli, txtIdCli, txtIdOp, txtFechaOp, txtFechaDev, txtMonto, txtDescuento, txtNomProd, txtIdProd, txtTipoProd, txtPlataforma;
    public JRadioButton rbRenta, rbVenta;
    public JButton btnAtras, btnDescargar, btnGuardar;
    public JLabel lblInicio, lblOperacion, lblClientes, lblVideojuegos, lblPeliculas;

    public AñadirOperacion() {
        setTitle("Añadir Operación");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // BARRA LATERAL
        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 700);
        barraLat.setLayout(null);
        add(barraLat);

        lblInicio = crearLabelMenu(barraLat, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        lblOperacion = crearLabelMenu(barraLat, "Operación", 150, "/img/ic_baseline-plus.png");
        lblClientes = crearLabelMenu(barraLat, "Clientes", 260, "/img/material-symbols_person.png");
        lblVideojuegos = crearLabelMenu(barraLat, "Videojuegos", 370, "/img/carbon_game-console.png");
        lblPeliculas = crearLabelMenu(barraLat, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(210, 210, 210));
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Generar Operación", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);

        // FORMULARIO
        crearLabel(mainPanel, "Nombre del cliente:", 30, 70);
        txtNombreCli = crearField(mainPanel, "Adrián Celis Olavarría", 30, 95, 300);

        crearLabel(mainPanel, "ID de cliente:", 30, 145);
        txtIdCli = crearField(mainPanel, "105422", 30, 170, 200);

        crearLabel(mainPanel, "ID de la operación:", 30, 220);
        txtIdOp = crearField(mainPanel, "00045621", 30, 245, 200);

        crearLabel(mainPanel, "Tipo", 30, 295);
        rbRenta = new JRadioButton("Renta");
        rbRenta.setBounds(30, 320, 70, 30);
        rbRenta.setOpaque(false);
        rbVenta = new JRadioButton("Venta", true);
        rbVenta.setBounds(100, 320, 70, 30);
        rbVenta.setOpaque(false);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbRenta);
        grupo.add(rbVenta);
        mainPanel.add(rbRenta);
        mainPanel.add(rbVenta);

        crearLabel(mainPanel, "Fecha de operación:", 30, 370);
        txtFechaOp = crearField(mainPanel, "24 / 09 / 2026", 30, 395, 200);

        crearLabel(mainPanel, "Fecha de devolución:", 30, 445);
        txtFechaDev = crearField(mainPanel, "No aplica", 30, 470, 200);

        // PRODUCTO
        JPanel panelImg = new JPanel(new BorderLayout());
        panelImg.setBounds(550, 95, 250, 270); 
        panelImg.setBackground(Color.DARK_GRAY);
        mainPanel.add(panelImg);

        crearLabel(mainPanel, "Nombre del producto:", 550, 380);
        txtNomProd = crearField(mainPanel, "Chainsaw Man - La película", 550, 405, 250);

        crearLabel(mainPanel, "ID del producto:", 550, 445);
        txtIdProd = crearField(mainPanel, "PEL-10024", 550, 470, 200);

        crearLabel(mainPanel, "Monto pagado:", 30, 515);
        txtMonto = crearField(mainPanel, "$ 250.00", 30, 540, 150);

        crearLabel(mainPanel, "Descuento:", 220, 515);
        txtDescuento = crearField(mainPanel, "0%", 220, 540, 120);

        crearLabel(mainPanel, "Tipo de producto:", 400, 515);
        txtTipoProd = crearField(mainPanel, "Película", 400, 540, 150);

        crearLabel(mainPanel, "Plataforma:", 600, 515);
        txtPlataforma = crearField(mainPanel, "Blu-Ray", 600, 540, 150);

        btnDescargar = new JButton("Descargar Ficha PDF");
        btnDescargar.setBounds(550, 590, 200, 35);
        btnDescargar.setBackground(new Color(0, 170, 255));
        btnDescargar.setForeground(Color.WHITE);
        mainPanel.add(btnDescargar);

        btnGuardar = new JButton("Guardar Operación");
        btnGuardar.setBounds(320, 600, 200, 40); 
        btnGuardar.setBackground(Color.BLACK);
        btnGuardar.setForeground(Color.WHITE);
        mainPanel.add(btnGuardar);
    }

    private void crearLabel(JPanel p, String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        l.setBounds(x, y, 250, 20);
        p.add(l);
    }

    private JTextField crearField(JPanel p, String v, int x, int y, int w) {
        JTextField f = new JTextField(v);
        f.setBounds(x, y, w, 30);
        f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.add(f);
        return f;
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
            String icono = esError ? "/img/mingcute_warning-fill.png" : "/img/mingcute_warning-fill.png";
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource(icono))
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
}
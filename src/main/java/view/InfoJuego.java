package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoJuego extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JTextField txtNombreCli, txtIdCli, txtIdOp, txtFechaOp, txtMonto, txtDescuento;
    public JTextField txtNomProd, txtIdProd, txtPlataforma;
    public JRadioButton rbRenta, rbVenta;
    public JButton btnAtras, btnDescargar, btnGuardar, btnEditar;
    public JLabel lblImagenProducto;

    public InfoJuego() {
        setTitle("Información de Operación");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 700);
        barraLat.setLayout(null);
        add(barraLat);

        btnInicio = crearLabelMenu(barraLat, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        btnOperacion = crearLabelMenu(barraLat, "Operación", 150, "/img/ic_baseline-plus.png");
        btnClientes = crearLabelMenu(barraLat, "Clientes", 260, "/img/material-symbols_person.png");
        btnVideojuegos = crearLabelMenu(barraLat, "Videojuegos", 370, "/img/carbon_game-console.png");
        btnPeliculas = crearLabelMenu(barraLat, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(210, 210, 210));
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Detalles de renta o compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);

        crearLabel(mainPanel, "Nombre del cliente:", 30, 75);
        txtNombreCli = crearField(mainPanel, "Adrián Celis Olavarría", 30, 100, 280);

        crearLabel(mainPanel, "ID de cliente:", 30, 145);
        txtIdCli = crearField(mainPanel, "105422", 30, 170, 180);

        crearLabel(mainPanel, "ID de la operación:", 30, 215);
        txtIdOp = crearField(mainPanel, "00045621", 30, 240, 180);


        crearLabel(mainPanel, "Fecha de operación:", 30, 360);
        txtFechaOp = crearField(mainPanel, "24 / 09 / 2026", 30, 385, 180);

        crearLabel(mainPanel, "Monto:", 30, 435);
        txtMonto = crearField(mainPanel, "$ 250.00", 30, 460, 120);

        crearLabel(mainPanel, "Descuento:", 170, 435);
        txtDescuento = crearField(mainPanel, "0%", 170, 460, 100);

        lblImagenProducto = new JLabel();
        lblImagenProducto.setBounds(550, 85, 230, 260); 
        lblImagenProducto.setBackground(Color.DARK_GRAY);
        lblImagenProducto.setOpaque(true);
        lblImagenProducto.setBorder(new LineBorder(Color.GRAY, 2));
        mainPanel.add(lblImagenProducto);

        crearLabel(mainPanel, "Nombre del producto:", 530, 360);
        txtNomProd = crearField(mainPanel, "halo", 530, 385, 270);

        crearLabel(mainPanel, "ID Producto:", 530, 430);
        txtIdProd = crearField(mainPanel, "PEL-10024", 530, 455, 130);

        crearLabel(mainPanel, "Plataforma:", 675, 430);
        txtPlataforma = crearField(mainPanel, "Xbox", 675, 455, 125);

        btnGuardar = new JButton("Guardar Operación");
        btnGuardar.setBounds(50, 560, 220, 45);
        btnGuardar.setBackground(Color.BLACK);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(btnGuardar);

        btnEditar = new JButton("Editar Producto");
        btnEditar.setBounds(300, 560, 200, 45);
        btnEditar.setBackground(new Color(60, 60, 60));
        btnEditar.setForeground(Color.WHITE);
        mainPanel.add(btnEditar);

        btnDescargar = new JButton("Descargar Ficha PDF");
        btnDescargar.setBounds(530, 560, 250, 45);
        btnDescargar.setBackground(new Color(0, 170, 255));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(btnDescargar);
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
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(0, 5, 0, 5)));
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
        dialogo.setSize(350, 250);
        dialogo.setLocationRelativeTo(this);
        
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        p.setBackground(new Color(230, 230, 230));

        JLabel msg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 15));
        
        JButton btn = new JButton("Aceptar");
        btn.setBackground(esError ? new Color(200, 50, 50) : new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> dialogo.dispose());

        p.add(msg, BorderLayout.CENTER);
        p.add(btn, BorderLayout.SOUTH);
        dialogo.add(p);
        dialogo.setVisible(true);
    }

    public void mostrarConfirmacion(String mensaje, ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 200);
        dialogo.setLocationRelativeTo(this);

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        p.setBackground(new Color(240, 240, 240));

        JLabel msg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel pBotones = new JPanel(new FlowLayout());
        JButton si = new JButton("Confirmar");
        JButton no = new JButton("Cancelar");
        
        si.addActionListener(e -> { dialogo.dispose(); accionSi.actionPerformed(e); });
        no.addActionListener(e -> dialogo.dispose());

        pBotones.add(si); pBotones.add(no);
        p.add(msg, BorderLayout.CENTER);
        p.add(pBotones, BorderLayout.SOUTH);
        dialogo.add(p);
        dialogo.setVisible(true);
    }
}
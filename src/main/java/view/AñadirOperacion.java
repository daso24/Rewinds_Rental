package view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AñadirOperacion extends JFrame {

    private JTextField txtNombreCli, txtIdCli, txtIdOp, txtFechaOp, txtFechaDev, txtMonto, txtDescuento, txtNomProd, txtIdProd, txtTipoProd, txtPlataforma;
    private JRadioButton rbRenta, rbVenta;

    public AñadirOperacion() {
        setTitle("Añadir Operación");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // BARRA LATERAL AZUL
        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 700);
        barraLat.setLayout(null);
        add(barraLat);

        Menu(barraLat, "Inicio", 80, crearIcono("/img/gravity-ui_house-fill.png"));
        Menu(barraLat, "Operación", 150, crearIcono("/img/ic_baseline-plus.png"));
        Menu(barraLat, "Clientes", 260, crearIcono("/img/material-symbols_person.png"));
        Menu(barraLat, "Videojuegos", 370, crearIcono("/img/carbon_game-console.png"));
        Menu(barraLat, "Peliculas", 480, crearIcono("/img/fluent_movies-and-tv-16-filled.png"));

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(210, 210, 210));
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setLayout(null);
        add(mainPanel);

        // CABECERA
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        btnAtras.addActionListener(e -> {
            new principal(); 
            dispose();
        });
        mainPanel.add(btnAtras);

        JLabel lblTitulo = new JLabel("Generar Operación", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 440, 30);
        mainPanel.add(lblTitulo);

        // FORMULARIO IZQUIERDA
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
        JLabel lblProdTit = new JLabel("Producto:", SwingConstants.CENTER);
        lblProdTit.setFont(new Font("Arial", Font.BOLD, 14));
        lblProdTit.setBounds(550, 70, 250, 20);
        mainPanel.add(lblProdTit);

        JPanel panelImg = new JPanel(new BorderLayout());
        panelImg.setBounds(550, 95, 250, 270); 
        panelImg.setBackground(Color.DARK_GRAY);
        mainPanel.add(panelImg);

        crearLabel(mainPanel, "Nombre del producto:", 550, 380);
        txtNomProd = crearField(mainPanel, "Chainsaw Man - La película", 550, 405, 250);

        crearLabel(mainPanel, "ID del producto:", 550, 445);
        txtIdProd = crearField(mainPanel, "PEL-10024", 550, 470, 200);

        // FILA INFERIOR 
        crearLabel(mainPanel, "Monto pagado:", 30, 515);
        txtMonto = crearField(mainPanel, "$ 250.00", 30, 540, 150);

        crearLabel(mainPanel, "Descuento:", 220, 515);
        txtDescuento = crearField(mainPanel, "0%", 220, 540, 120);

        crearLabel(mainPanel, "Tipo de producto:", 400, 515);
        txtTipoProd = crearField(mainPanel, "Película", 400, 540, 150);

        crearLabel(mainPanel, "Plataforma:", 600, 515);
        txtPlataforma = crearField(mainPanel, "Blu-Ray", 600, 540, 150);

        // BOTONES ACCIÓN 
        JButton btnDescargar = new JButton("Descargar Ficha PDF");
        btnDescargar.setBounds(550, 590, 200, 35);
        btnDescargar.setBackground(new Color(0, 170, 255));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Descargando ficha...", "Éxito", JOptionPane.INFORMATION_MESSAGE));
        mainPanel.add(btnDescargar);

        // AQUÍ ESTABA EL ERROR DEL BOTÓN
        JButton btnGuardar = new JButton("Guardar Operación");
        btnGuardar.setBounds(320, 600, 200, 40); 
        btnGuardar.setBackground(Color.BLACK);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> validarYGuardar());
        mainPanel.add(btnGuardar);

        setVisible(true);
    }

    private void validarYGuardar() {
        if (txtNombreCli.getText().trim().isEmpty() || txtIdOp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Existen campos obligatorios vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Operación guardada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            new principal();
            dispose();
        }
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

    private ImageIcon crearIcono(String ruta) {
        try { return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)); } 
        catch (Exception e) { return null; }
    }

    public void Menu(JPanel panel, String texto, int y, Icon icono) {
        JLabel iconLabel = new JLabel(icono);
        iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame v = null;
                switch (texto) {
                    case "Inicio": v = new principal(); break;
                    case "Videojuegos": v = new videojuegos(); break;
                    case "Clientes": v = new clientes(); break;
                    case "Operación": v = new operaciones(); break;
                    case "Peliculas": v = new peliculas(); break;
                }
                if (v != null) { v.setVisible(true); dispose(); }
            }
        });
        panel.add(iconLabel);
        panel.add(label);
    }

    public static void main(String[] args) { new AñadirOperacion(); }
}
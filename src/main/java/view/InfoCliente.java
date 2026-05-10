package view;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class InfoCliente extends JFrame {

    private JTextField txtId, txtFecha, txtTelefono;

    public InfoCliente() {
        setTitle("Información de Cliente");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

        // BARRA LATERAL
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 700);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        // Carga de iconos
        ImageIcon inicioIcono = crearIcono("/img/gravity-ui_house-fill.png");
        ImageIcon operacionesIcono = crearIcono("/img/ic_baseline-plus.png");
        ImageIcon clientesIcono = crearIcono("/img/material-symbols_person.png");
        ImageIcon videojuegosIcono = crearIcono("/img/carbon_game-console.png");
        ImageIcon peliculasIcono = crearIcono("/img/fluent_movies-and-tv-16-filled.png");

        Menu(sidebar, "Inicio", 80, inicioIcono);
        Menu(sidebar, "Operación", 150, operacionesIcono);
        Menu(sidebar, "Clientes", 260, clientesIcono);
        Menu(sidebar, "Videojuegos", 370, videojuegosIcono);
        Menu(sidebar, "Peliculas", 480, peliculasIcono);

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 700);
        mainPanel.setBackground(new Color(220, 220, 220));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Botón Atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(20, 20, 100, 30);
        btnAtras.addActionListener(e -> {
            new clientes(); 
            dispose();
        });
        mainPanel.add(btnAtras);

        JLabel lblTituloSuperior = new JLabel("Información de cliente", SwingConstants.CENTER);
        lblTituloSuperior.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloSuperior.setBounds(160, 20, 520, 30);
        mainPanel.add(lblTituloSuperior);

        // FOTO DEL CLIENTE EXTERNA
        JLabel lblFotoTexto = new JLabel("Foto de cliente", SwingConstants.CENTER);
        lblFotoTexto.setBounds(330, 60, 180, 20);
        mainPanel.add(lblFotoTexto);

        JPanel marcoFoto = new JPanel();
        marcoFoto.setBounds(360, 85, 120, 130);
        marcoFoto.setBackground(Color.DARK_GRAY); 
        marcoFoto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        mainPanel.add(marcoFoto);

        // TARJETA BLANCA CENTRAL
        JPanel card = new JPanel();
        card.setBounds(30, 230, 780, 360);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        mainPanel.add(card);

        JLabel lblNombreLabel = new JLabel("Nombre del cliente:", SwingConstants.CENTER);
        lblNombreLabel.setBounds(0, 15, 780, 20);
        card.add(lblNombreLabel);

        JLabel lblNombreValor = new JLabel("Tobias Martinez", SwingConstants.CENTER);
        lblNombreValor.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombreValor.setBounds(0, 35, 780, 20);
        card.add(lblNombreValor);

        // Inicialización de campos con validación 
        txtId = CampoEditable(card, "ID del Cliente:", "", 50, 70, 180);
        txtFecha = CampoEditable(card, "Fecha de nacimiento:", "", 300, 70, 180);
        txtTelefono = CampoEditable(card, "Teléfono:", "1234567891", 550, 70, 180);

        // FIDELIDAD Y CORREO
        JLabel lblFid = new JLabel("Nivel de fidelidad:", SwingConstants.CENTER);
        lblFid.setBounds(50, 140, 180, 20);
        card.add(lblFid);
        JLabel lblFidV = new JLabel("VIP", SwingConstants.CENTER);
        lblFidV.setFont(new Font("Arial", Font.BOLD, 13));
        lblFidV.setBounds(50, 165, 180, 20);
        card.add(lblFidV);

        JLabel lblEmail = new JLabel("Correo electrónico:", SwingConstants.CENTER);
        lblEmail.setBounds(300, 140, 200, 20);
        card.add(lblEmail);
        JLabel lblEmailV = new JLabel("ejemplo@gmail.com", SwingConstants.CENTER);
        lblEmailV.setFont(new Font("Arial", Font.BOLD, 13));
        lblEmailV.setBounds(300, 165, 200, 20);
        card.add(lblEmailV);

        // BOTONES DE HISTORIAL (CORREGIDOS)
        JButton btnHistoVentas = new JButton("Historial de ventas");
        btnHistoVentas.setBounds(50, 210, 180, 35);
        btnHistoVentas.setBackground(new Color(45, 62, 80));
        btnHistoVentas.setForeground(Color.WHITE);
        btnHistoVentas.addActionListener(e -> {
            new HistorialVentas(); 
            dispose();
        });
        card.add(btnHistoVentas);

        JButton btnHistoRentas = new JButton("Historial de rentas");
        btnHistoRentas.setBounds(550, 210, 180, 35);
        btnHistoRentas.setBackground(new Color(45, 62, 80));
        btnHistoRentas.setForeground(Color.WHITE);
        btnHistoRentas.addActionListener(e -> {
            new HistorialRentas(); 
            dispose();
        });
        card.add(btnHistoRentas);

        // BOTONES DE DESCARGA PDF
        JButton btnDescargar = new JButton("Descargar Ficha de cliente [PDF]");
        btnDescargar.setBounds(80, 290, 280, 30);
        btnDescargar.setBackground(new Color(0, 170, 255));
        btnDescargar.setForeground(Color.WHITE);
        card.add(btnDescargar);

        JButton btnGenerar = new JButton("Generar tarjeta de cliente [PDF]");
        btnGenerar.setBounds(430, 290, 280, 30);
        btnGenerar.setBackground(new Color(0, 170, 255));
        btnGenerar.setForeground(Color.WHITE);
        card.add(btnGenerar);

        // BOTÓN EDITAR CLIENTE 
        JButton btnEditar = new JButton("Editar Cliente");
        btnEditar.setBounds(340, 610, 160, 35);
        btnEditar.setBackground(new Color(0, 170, 255));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.addActionListener(e -> validarEdicion());
        mainPanel.add(btnEditar);

        setVisible(true);
    }

    private void validarEdicion() {
        String id = txtId.getText().trim();
        String fecha = txtFecha.getText().trim();
        String tel = txtTelefono.getText().trim();

        if (id.isEmpty() || fecha.isEmpty() || tel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos deben estar llenos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!id.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Error: El ID del cliente debe ser numérico.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "¡Cliente editado con éxito!", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private JTextField CampoEditable(JPanel panel, String titulo, String valor, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setBounds(x, y, w, 20);
        panel.add(lbl);

        JTextField txt = new JTextField(valor);
        txt.setBounds(x, y + 25, w, 35);
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setEditable(true); 
        txt.setBackground(Color.WHITE);
        txt.setBorder(new RoundedBorder(15));
        panel.add(txt);
        return txt;
    }

    private ImageIcon crearIcono(String ruta) {
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                    .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            return null; 
        }
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
                JFrame ventana = null;
                switch (texto) {
                    case "Inicio": ventana = new principal(); break;
                    case "Videojuegos": ventana = new videojuegos(); break;
                    case "Clientes": ventana = new clientes(); break;
                    case "Operación": ventana = new operaciones(); break;
                    case "Peliculas": ventana = new peliculas(); break;
                }
                if (ventana != null) {
                    ventana.setVisible(true);
                    dispose();
                }
            }
        });
        panel.add(iconLabel);
        panel.add(label);
    }

    class RoundedBorder implements Border {
        int r;
        RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r/2, r, r/2, r); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.setColor(new Color(180, 180, 180));
            g.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }

    public static void main(String[] args) {
        new InfoCliente();
    }
}
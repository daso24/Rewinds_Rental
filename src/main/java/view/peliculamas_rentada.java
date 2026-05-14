package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class peliculamas_rentada extends JFrame {

    public peliculamas_rentada() {
        setTitle("Información del producto");
        setSize(820, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}
        
        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 130, 620);
        barraLat.setLayout(null);
        
        Menu(barraLat, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        Menu(barraLat, "Operación", 150, "/img/ic_baseline-plus.png");
        Menu(barraLat, "Clientes", 260, "/img/material-symbols_person.png");
        Menu(barraLat, "Videojuegos", 370, "/img/carbon_game-console.png");
        Menu(barraLat, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(235, 235, 235));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panel.setOpaque(false);
        panel.setBounds(150, 20, 630, 540);
        panel.setLayout(null);

        JLabel titulo = new JLabel("Información del producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 22));
        titulo.setBounds(150, 15, 330, 30);

        JButton atras = crearBotonRedondo("← Atrás", new Color(200, 200, 200), Color.BLACK);
        atras.setBounds(20, 15, 100, 30);
        atras.addActionListener(e -> {
            dispose();
            new principal().setVisible(true);
        });

        JLabel nombre = new JLabel("Nombre del producto:");
        nombre.setFont(new Font("Inter", Font.BOLD, 13));
        nombre.setBounds(30, 70, 180, 20);
        JTextField nombreTxt = new JTextField("Chainsaw-man la pelicula:arco de Reze");
        nombreTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        nombreTxt.setBounds(30, 95, 240, 30);

        JLabel id = new JLabel("ID del producto:");
        id.setFont(new Font("Inter", Font.BOLD, 13));
        id.setBounds(30, 135, 180, 20);
        JTextField idTxt = new JTextField("PEL_10024");
        idTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        idTxt.setBounds(30, 160, 240, 30);

        JLabel tipo = new JLabel("Tipo de producto:");
        tipo.setFont(new Font("Inter", Font.BOLD, 13));
        tipo.setBounds(30, 200, 180, 20);
        JTextField tipoTxt = new JTextField("Pelicula");
        tipoTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        tipoTxt.setBounds(30, 225, 240, 30);

        JLabel plataforma = new JLabel("Plataforma:");
        plataforma.setFont(new Font("Inter", Font.BOLD, 13));
        plataforma.setBounds(30, 265, 180, 20);
        JTextField plataformaTxt = new JTextField("Blue-ray");
        plataformaTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        plataformaTxt.setBounds(30, 290, 240, 30);

        JLabel venta = new JLabel("Precio de venta:");
        venta.setFont(new Font("Inter", Font.BOLD, 13));
        venta.setBounds(30, 330, 120, 20);
        JTextField ventaTxt = new JTextField("$ 250.00");
        ventaTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        ventaTxt.setBounds(30, 355, 110, 30);

        JLabel renta = new JLabel("Precio de renta:");
        renta.setFont(new Font("Inter", Font.BOLD, 13));
        renta.setBounds(160, 330, 120, 20);
        JTextField rentaTxt = new JTextField("$ 100.00");
        rentaTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        rentaTxt.setBounds(160, 355, 110, 30);

        JLabel descuento = new JLabel("Descuento:");
        descuento.setFont(new Font("Inter", Font.BOLD, 13));
        descuento.setBounds(30, 395, 100, 20);
        JTextField descuentoTxt = new JTextField("0%");
        descuentoTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        descuentoTxt.setBounds(30, 420, 110, 30);

        JLabel stock = new JLabel("Stock (Venta / Renta)");
        stock.setFont(new Font("Inter", Font.BOLD, 13));
        stock.setBounds(160, 395, 150, 20);
        JTextField ventaStock = new JTextField("50");
        ventaStock.setBounds(160, 420, 50, 30);
        JTextField rentaStock = new JTextField("20");
        rentaStock.setBounds(220, 420, 50, 30);

        try {
            ImageIcon portada = new ImageIcon(getClass().getResource("/img/71w58zkWnfL.jpg"));
            Image img = portada.getImage().getScaledInstance(150, 210, Image.SCALE_SMOOTH);
            JLabel imagen = new JLabel(new ImageIcon(img));
            imagen.setBounds(420, 95, 150, 210);
            panel.add(imagen);
        } catch(Exception e) {}

        JLabel clasificacion = new JLabel("Clasificación:");
        clasificacion.setFont(new Font("Inter", Font.BOLD, 13));
        clasificacion.setBounds(430, 320, 120, 20);
        JTextField clasificacionTxt = new JTextField("B-15");
        clasificacionTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        clasificacionTxt.setHorizontalAlignment(SwingConstants.CENTER);
        clasificacionTxt.setBounds(430, 345, 140, 30);

        JLabel año = new JLabel("Lanzamiento:");
        año.setFont(new Font("Inter", Font.BOLD, 13));
        año.setBounds(430, 385, 140, 20);
        JTextField añoTxt = new JTextField("2025");
        añoTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        añoTxt.setHorizontalAlignment(SwingConstants.CENTER);
        añoTxt.setBounds(430, 410, 140, 30);

        JLabel genero = new JLabel("Género:");
        genero.setFont(new Font("Inter", Font.BOLD, 13));
        genero.setBounds(430, 450, 120, 20);
        JTextField generoTxt = new JTextField("Acción");
        generoTxt.setFont(new Font("Inter", Font.PLAIN, 12));
        generoTxt.setHorizontalAlignment(SwingConstants.CENTER);
        generoTxt.setBounds(430, 475, 140, 30);

        JButton editar = crearBotonRedondo("Editar Pelicula", new Color(52, 73, 94), Color.WHITE);
        editar.setBounds(30, 485, 170, 35);

        JButton descargar = crearBotonRedondo("Descargar PDF", new Color(0, 120, 215), Color.WHITE);
        descargar.setBounds(220, 485, 170, 35);

        add(barraLat);
        add(panel);
        panel.add(titulo); panel.add(atras);
        panel.add(nombre); panel.add(nombreTxt);
        panel.add(id); panel.add(idTxt);
        panel.add(tipo); panel.add(tipoTxt);
        panel.add(plataforma); panel.add(plataformaTxt);
        panel.add(venta); panel.add(ventaTxt);
        panel.add(renta); panel.add(rentaTxt);
        panel.add(descuento); panel.add(descuentoTxt);
        panel.add(stock); panel.add(ventaStock); panel.add(rentaStock);
        panel.add(clasificacion); panel.add(clasificacionTxt);
        panel.add(año); panel.add(añoTxt);
        panel.add(genero); panel.add(generoTxt);
        panel.add(editar); panel.add(descargar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton crearBotonRedondo(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(fg);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setFont(new Font("Inter", Font.BOLD, 12));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void Menu(JPanel panel, String texto, int y, String rutaIcono) {
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBounds(15, y, 25, 30);
            panel.add(iconLabel);
        } catch(Exception e) {}

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 14));
        label.setBounds(50, y, 100, 30);
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
        panel.add(label);
    }
}
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class principal extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas, logoutBtn;
    public JButton btnVerClientes, btnVerRentas, btnVerJuegosComprados, btnVerJuegosRentados, btnVerPelisCompradas, btnVerPelisRentadas;

    public principal() {
        setTitle("Principal");
        setSize(1000, 600);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // BARRA LATERAL
        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 160, 600);
        barraLat.setLayout(null);
        
        btnInicio = Menu(barraLat, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        btnOperacion = Menu(barraLat, "Operación", 150, "/img/ic_baseline-plus.png");
        btnClientes = Menu(barraLat, "Clientes", 260, "/img/material-symbols_person.png");
        btnVideojuegos = Menu(barraLat, "Videojuegos", 370, "/img/carbon_game-console.png");
        btnPeliculas = Menu(barraLat, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        // HEADER
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(200, 200, 200));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 45, 45);
            }
        };
        header.setOpaque(false);
        header.setBounds(170, 10, 800, 100); 
        header.setLayout(null);

        JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/logo0.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        logoLabel.setBounds(30, 10, 80, 80); 

        JLabel user = new JLabel("Bienvenido Usuario", SwingConstants.CENTER); 
        user.setFont(new Font("Inter", Font.BOLD, 18)); 
        user.setBounds(0, 35, 800, 30); 

        logoutBtn = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/material-symbols_logout-sharp.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        logoutBtn.setBounds(720, 30, 40, 40);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(logoLabel); header.add(user); header.add(logoutBtn);

        // CARDS
        JPanel card1 = Card("Clientes", "1000"); card1.setBounds(260, 160, 220, 140);
        btnVerClientes = (JButton) card1.getComponent(2);

        JPanel card2 = Card("Rentas y Ventas", "1500"); card2.setBounds(550, 160, 220, 140);
        btnVerRentas = (JButton) card2.getComponent(2);

        JPanel card3 = crearCardDoble("Peliculas"); card3.setBounds(260, 340, 220, 150);
        btnVerPelisCompradas = (JButton) card3.getComponent(3);
        btnVerPelisRentadas = (JButton) card3.getComponent(4);

        JPanel card4 = crearCardDoble("Videojuegos"); card4.setBounds(550, 340, 220, 150);
        btnVerJuegosComprados = (JButton) card4.getComponent(3);
        btnVerJuegosRentados = (JButton) card4.getComponent(4);

        add(barraLat); add(header); add(card1); add(card2); add(card3); add(card4);
        setLocationRelativeTo(null);
    }

    
    public void mostrarConfirmacionSalir(ActionListener accionSi, ActionListener accionNo) {
        JDialog dialogo = new JDialog(this, "Confirmar", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280); 
        dialogo.setLocationRelativeTo(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(209, 209, 209));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        dialogo.setContentPane(panelPrincipal);

        panelPrincipal.add(Box.createVerticalStrut(20));

        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>¿Estás seguro que quieres<br>cerrar sesión?</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblMsg);

        panelPrincipal.add(Box.createVerticalGlue());

        ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        
        JLabel iconoCentro = new JLabel(imagenAlerta);
        iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(iconoCentro);

        panelPrincipal.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);
        panelBotones.setMaximumSize(new Dimension(350, 50));

        JButton btnSi = new JButton("Sí, Salir");
        btnSi.setBackground(new Color(220, 50, 50));
        btnSi.setForeground(Color.WHITE);
        btnSi.setFocusPainted(false);
        btnSi.addActionListener(e -> {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });

        JButton btnNo = new JButton("Cancelar");
        btnNo.setBackground(new Color(150, 150, 150));
        btnNo.setForeground(Color.WHITE);
        btnNo.setFocusPainted(false);
        btnNo.addActionListener(e -> {
            dialogo.dispose();
            accionNo.actionPerformed(e);
        });

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);
        panelPrincipal.add(panelBotones);

        panelPrincipal.add(Box.createVerticalStrut(20));

        dialogo.setVisible(true);
    }

    public JLabel Menu(JPanel panel, String texto, int y, String rutaIcono) {
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JLabel iconLabel = new JLabel(icon); iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE); label.setFont(new Font("Inter", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30); label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(iconLabel); panel.add(label);
        return label;
    }

    private JPanel Card(String titulo, String numero) {
        JPanel panel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(210, 210, 210));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
            }
        };
        panel.setOpaque(false);
        JLabel t = new JLabel(titulo, SwingConstants.CENTER); t.setFont(new Font("Inter", Font.BOLD, 18)); t.setBounds(0, 15, 220, 25);
        JLabel n = new JLabel(numero, SwingConstants.CENTER); n.setFont(new Font("Inter", Font.BOLD, 26)); n.setBounds(0, 45, 220, 35);
        JButton btn = new JButton("Ver"); btn.setBounds(70, 95, 80, 30);
        panel.add(t); panel.add(n); panel.add(btn);
        return panel;
    }

    private JPanel crearCardDoble(String titulo) {
        JPanel panel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(210, 210, 210));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
            }
        }; 
        panel.setOpaque(false);
        JLabel t = new JLabel(titulo, SwingConstants.CENTER); t.setFont(new Font("Inter", Font.BOLD, 18)); t.setBounds(0, 10, 220, 25);
        JLabel m1 = new JLabel("Más comprado"); m1.setFont(new Font("Inter", Font.PLAIN, 12)); m1.setBounds(20, 50, 100, 20);
        JLabel m2 = new JLabel("Más rentado"); m2.setFont(new Font("Inter", Font.PLAIN, 12)); m2.setBounds(120, 50, 100, 20);
        JButton b1 = new JButton("Ver"); b1.setBounds(20, 90, 80, 30);
        JButton b2 = new JButton("Ver"); b2.setBounds(120, 90, 80, 30);
        panel.add(t); panel.add(m1); panel.add(m2); panel.add(b1); panel.add(b2);
        return panel;
    }
}
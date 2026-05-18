package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;

public class login extends JFrame {

    public JTextField userField;
    public JPasswordField passField;
    public JButton loginButton;
    public JLabel registerLink;
    private JPanel topPanel, bottomPanel, card;

    public login() {
        this.setTitle("Rewind Rental");
        this.setMinimumSize(new Dimension(700, 500));
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        JLayeredPane layeredPane = new JLayeredPane();
        this.add(layeredPane);

        topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));
        
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));

        card = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(280, 380));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 20, 8, 20);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Inicio de sesión", SwingConstants.CENTER);
        title.setFont(new Font("Inter", Font.BOLD, 20));
        c.gridy = 0;
        card.add(title, c);

        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img/logo0.png"));
            Image img = logo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(img));
            c.gridy = 1;
            card.add(logoLabel, c);
        } catch(Exception e) {}

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Inter", Font.BOLD, 13));
        c.gridy = 2;
        card.add(lblUsuario, c);

        userField = new JTextField();
        userField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 3;
        card.add(userField, c);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Inter", Font.BOLD, 13));
        c.gridy = 4;
        card.add(lblPass, c);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 5;
        card.add(passField, c);

        loginButton = new JButton("Iniciar Sesión") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        loginButton.setBackground(new Color(4, 180, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Inter", Font.BOLD, 14));
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.gridy = 6;
        c.insets = new Insets(15, 20, 10, 20);
        card.add(loginButton, c);

        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        registerPanel.setOpaque(false);
        JLabel registerText = new JLabel("¿No tienes cuenta?");
        registerText.setFont(new Font("Inter", Font.PLAIN, 12));
        registerLink = new JLabel("<HTML><U>Regístrate</U></HTML>");
        registerLink.setFont(new Font("Inter", Font.BOLD, 12));
        registerLink.setForeground(new Color(0, 51, 153));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerPanel.add(registerText);
        registerPanel.add(registerLink);
        c.gridy = 7;
        c.insets = new Insets(0, 20, 10, 20);
        card.add(registerPanel, c);

        layeredPane.add(topPanel, Integer.valueOf(0));
        layeredPane.add(bottomPanel, Integer.valueOf(0));
        layeredPane.add(card, Integer.valueOf(1));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarComponentes();
            }
        });

      
        SwingUtilities.invokeLater(() -> ajustarComponentes());
    }

    private void ajustarComponentes() {
        int w = getWidth();
        int h = getHeight();
        topPanel.setBounds(0, 0, w, (int)(h * 0.4));
        bottomPanel.setBounds(0, (int)(h * 0.4), w, (int)(h * 0.6));
        int cardW = card.getPreferredSize().width;
        int cardH = card.getPreferredSize().height;
        card.setBounds((w - cardW) / 2, (h - cardH) / 2, cardW, cardH);
    }

    public void mostrarMensajeError(String mensaje) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 227);
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));

        JLabel tituloError = new JLabel("ERROR", SwingConstants.CENTER);
        tituloError.setFont(new Font("Inter", Font.BOLD, 20));
        tituloError.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        contenedor.add(tituloError, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(Box.createVerticalStrut(15));

        JLabel iconoX = new JLabel("×", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(242, 78, 30));
                g2.fill(new Ellipse2D.Double(0, 0, 65, 65));
                g2.dispose();
                super.paintComponent(g);
            }
        };

        iconoX.setPreferredSize(new Dimension(65, 65));
        iconoX.setMaximumSize(new Dimension(65, 65));
        iconoX.setForeground(Color.WHITE);
        iconoX.setFont(new Font("Inter", Font.PLAIN, 50));
        iconoX.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMsg.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        centro.add(iconoX);
        centro.add(lblMsg);

        JButton btnOk = new JButton("Aceptar");
        btnOk.setBackground(new Color(0, 179, 255));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 14));
        btnOk.addActionListener(e -> dialogo.dispose());

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        sur.setOpaque(false);
        sur.add(btnOk);

        contenedor.add(centro, BorderLayout.CENTER);
        contenedor.add(sur, BorderLayout.SOUTH);

        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    public void mostrarLoginExitoso(String mensaje) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 215);
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));

        JLabel tituloExito = new JLabel("ÉXITO", SwingConstants.CENTER);
        tituloExito.setFont(new Font("Inter", Font.BOLD, 20));
        tituloExito.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        contenedor.add(tituloExito, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(Box.createVerticalStrut(15));

        try {
            ImageIcon icon = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/palomitaverde.png"))
                .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)
            );

            JLabel lblIcono = new JLabel(icon);
            lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelIcono.setOpaque(false);
            panelIcono.add(lblIcono);

            centro.add(panelIcono);

        } catch(Exception e) {}

        centro.add(Box.createVerticalStrut(15));

        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMsg.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));

        centro.add(lblMsg);

        JButton btnOk = new JButton("Continuar");
        btnOk.setBackground(new Color(0, 179, 255));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 14));
        btnOk.addActionListener(e -> dialogo.dispose());

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        sur.setOpaque(false);
        sur.add(btnOk);

        contenedor.add(centro, BorderLayout.CENTER);
        contenedor.add(sur, BorderLayout.SOUTH);

        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }
}
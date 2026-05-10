package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class login extends JFrame {

    public JTextField userField;
    public JPasswordField passField;
    public JButton loginButton;
    public JLabel registerLink;

    public login() {
        
        this.setTitle("Rewind Rental");
        this.setSize(700, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0, 0, 700, 450);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));
        topPanel.setBounds(0, 0, 700, 180);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(0, 180, 700, 270);

        JPanel card = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setBounds(220, 75, 260, 300);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Inicio de sesión", SwingConstants.CENTER);
        title.setFont(new Font("Inter", Font.BOLD, 18));
        c.gridy = 0; card.add(title, c);

        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img/logo0.png"));
            Image img = logo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(img));
            c.gridy = 1; card.add(logoLabel, c);
        } catch(Exception e) {}

        userField = new JTextField();
        userField.setPreferredSize(new Dimension(200, 25));
        c.gridy = 3; card.add(userField, c);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(200, 25));
        c.gridy = 5; card.add(passField, c);

        loginButton = new JButton("Iniciar Sesion");
        loginButton.setBackground(new Color(4, 180, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        c.gridy = 6; card.add(loginButton, c);

        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        registerPanel.setOpaque(false);
        JLabel registerText = new JLabel("¿No tienes cuenta?");
        registerLink = new JLabel("<HTML><U>Regístrate</U></HTML>");
        registerLink.setForeground(Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerPanel.add(registerText);
        registerPanel.add(registerLink);
        c.gridy = 8; card.add(registerPanel, c);

        layeredPane.add(topPanel, Integer.valueOf(0));
        layeredPane.add(bottomPanel, Integer.valueOf(0));
        layeredPane.add(card, Integer.valueOf(1));

        this.add(layeredPane);
    }

    // POP-UP 
    public void mostrarMensajeError(String mensaje) {
        JDialog dialogo = new JDialog(this, "Error", true);
        dialogo.setUndecorated(true); 
        dialogo.setSize(350, 240);
        dialogo.setLocationRelativeTo(this);
       
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(209, 209, 209)); 
     
        dialogo.setContentPane(panelPrincipal);

        JLabel lblTitulo = new JLabel("ERROR", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

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
        iconoX.setFont(new Font("Arial", Font.PLAIN, 50));
        iconoX.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Arial", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMsg.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        centro.add(iconoX);
        centro.add(lblMsg);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(0, 179, 255)); 
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Arial", Font.BOLD, 14));
        btnOk.addActionListener(e -> dialogo.dispose());

        JPanel sur = new JPanel(new BorderLayout());
        sur.setOpaque(false);
        sur.setBorder(BorderFactory.createEmptyBorder(0, 45, 20, 45));
        sur.add(btnOk);

        panelPrincipal.add(sur, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarMensajeExito(String mensaje) {
        JDialog dialogo = new JDialog(this, "Éxito", true);
        dialogo.setUndecorated(true); 
        dialogo.setSize(350, 240);
        dialogo.setLocationRelativeTo(this);
       
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(209, 209, 209)); 
        dialogo.setContentPane(panelPrincipal);

        JLabel lblTitulo = new JLabel("ÉXITO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        // Icono de Check (✓) en lugar de X
        JLabel iconoCheck = new JLabel("✓", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(40, 167, 69)); // Color Verde
                g2.fill(new Ellipse2D.Double(0, 0, 65, 65));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        iconoCheck.setPreferredSize(new Dimension(65, 65));
        iconoCheck.setMaximumSize(new Dimension(65, 65));
        iconoCheck.setForeground(Color.WHITE);
        iconoCheck.setFont(new Font("Arial", Font.PLAIN, 40));
        iconoCheck.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Arial", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMsg.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        centro.add(iconoCheck);
        centro.add(lblMsg);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        JButton btnOk = new JButton("Continuar");
        btnOk.setBackground(new Color(40, 167, 69)); 
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Arial", Font.BOLD, 14));
        btnOk.addActionListener(e -> dialogo.dispose());

        JPanel sur = new JPanel(new BorderLayout());
        sur.setOpaque(false);
        sur.setBorder(BorderFactory.createEmptyBorder(0, 45, 20, 45));
        sur.add(btnOk);

        panelPrincipal.add(sur, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
}
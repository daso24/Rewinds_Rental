package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class login extends JFrame {

    public JTextField userField;
    public JPasswordField passField;
    public JButton loginButton;
    public JLabel registerLink;
    public JPanel popup; 

    public login() {
        // ventana
        this.setTitle("Rewind Rental");
        this.setSize(700, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

        Font inter = new Font("Inter", Font.PLAIN, 14);
        Font interBold = new Font("Inter", Font.BOLD, 14);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0, 0, 700, 450);

        // Fondos
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));
        topPanel.setBounds(0, 0, 700, 180);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(0, 180, 700, 270);

        // Card de diseño
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

        // Titulo y Logo
        JLabel title = new JLabel("Inicio de sesión", SwingConstants.CENTER);
        title.setFont(new Font("Inter", Font.BOLD, 18));
        c.gridy = 0; card.add(title, c);

        ImageIcon logo = new ImageIcon(login.class.getResource("/img/logo0.png"));
        Image img = logo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        c.gridy = 1; card.add(logoLabel, c);

        // Inputs
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

        // Registro
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        registerPanel.setOpaque(false);
        JLabel registerText = new JLabel("¿No tienes cuenta?");
        registerLink = new JLabel("<HTML><U>Regístrate</U></HTML>");
        registerLink.setForeground(Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerPanel.add(registerText);
        registerPanel.add(registerLink);
        c.gridy = 8; card.add(registerPanel, c);

        // Popup Error
        popup = new JPanel(); 
        popup.setBounds(170, 80, 360, 180);
        popup.setBackground(Color.LIGHT_GRAY);
        popup.setVisible(false);
        JButton ok = new JButton("ok");
        ok.addActionListener(e -> popup.setVisible(false));
        popup.add(ok);

        layeredPane.add(topPanel, Integer.valueOf(0));
        layeredPane.add(bottomPanel, Integer.valueOf(0));
        layeredPane.add(card, Integer.valueOf(1));
        layeredPane.add(popup, Integer.valueOf(2));

        this.add(layeredPane);
    }

    public String getUsuario() { return userField.getText(); }
    public String getPassword() { return new String(passField.getPassword()); }
}
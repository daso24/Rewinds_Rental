package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class registro extends JFrame {

    public JTextField userField, phoneField, emailField;
    public JPasswordField passField, confirmField;
    public JRadioButton masculino, femenino, otro;
    public JButton registerBtn, backBtn;

    public registro() {
        setTitle("Registro");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch (Exception e) {}

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));
        topPanel.setBounds(0, 0, 900, 200);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(0, 200, 900, 400);

        JPanel card = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        card.setOpaque(false);
        card.setBounds(100, 80, 700, 440);

        try {
            ImageIcon logoIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/logo2.png")) 
                .getImage()
                .getScaledInstance(180, 70, Image.SCALE_SMOOTH)
            );
            JLabel logoLabel = new JLabel(logoIcono);
            logoLabel.setBounds(500, 10, 180, 70); 
            card.add(logoLabel);
        } catch (Exception e) {}

        backBtn = new JButton("Atrás");
        backBtn.setBounds(20, 20, 90, 30);
        backBtn.setFocusPainted(false);
        backBtn.setBackground(Color.WHITE);
        card.add(backBtn);

        userField = crearCampo(card, "Usuario:", 50, 85);
        phoneField = crearCampo(card, "Teléfono:", 380, 85);
        emailField = crearCampo(card, "Correo:", 50, 155);
        
        masculino = new JRadioButton("Masculino"); masculino.setBounds(380, 155, 90, 25);
        femenino = new JRadioButton("Femenino"); femenino.setBounds(470, 155, 90, 25);
        otro = new JRadioButton("Otro"); otro.setBounds(560, 155, 90, 25);
        ButtonGroup bg = new ButtonGroup(); bg.add(masculino); bg.add(femenino); bg.add(otro);
        card.add(masculino); card.add(femenino); card.add(otro);

        passField = (JPasswordField) crearCampo(card, "Contraseña:", 50, 225, true);
        confirmField = (JPasswordField) crearCampo(card, "Confirmar:", 50, 295, true);

        registerBtn = new JButton("Registrar");
        registerBtn.setBounds(250, 370, 200, 40);
        registerBtn.setBackground(new Color(4, 180, 255));
        registerBtn.setForeground(Color.WHITE);
        card.add(registerBtn);

        layeredPane.add(topPanel, Integer.valueOf(0));
        layeredPane.add(bottomPanel, Integer.valueOf(0));
        layeredPane.add(card, Integer.valueOf(1));

        setContentPane(layeredPane);
    }

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

    private JTextField crearCampo(JPanel p, String txt, int x, int y) {
        return crearCampo(p, txt, x, y, false);
    }

    private JTextField crearCampo(JPanel p, String txt, int x, int y, boolean isPass) {
        JLabel l = new JLabel(txt); l.setBounds(x, y - 25, 150, 20);
        JTextField t = isPass ? new JPasswordField() : new JTextField();
        t.setBounds(x, y, 250, 30);
        p.add(l); p.add(t);
        return t;
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

        JLabel iconoCheck = new JLabel("✓", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(40, 167, 69)); 
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

        JButton btnOk = new JButton("OK");
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
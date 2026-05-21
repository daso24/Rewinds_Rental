package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;

public class registro extends JFrame {

    public JTextField userField, phoneField, emailField;
    public JPasswordField passField, confirmField;
    public JRadioButton masculino, femenino, otro;
    public JButton registerBtn, backBtn;
    
    private JLayeredPane layeredPane;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel card;
    private JLabel logoLabel;

    public registro() {
        setTitle("Registro");
        setSize(900, 600);
        setMinimumSize(new Dimension(850, 580));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch (Exception e) {}

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));

        card = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(217, 217, 217));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        card.setOpaque(false);

        logoLabel = new JLabel();
        try {
            ImageIcon logoIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/logo2.png")) 
                .getImage()
                .getScaledInstance(180, 70, Image.SCALE_SMOOTH)
            );
            logoLabel.setIcon(logoIcono);
            card.add(logoLabel);
        } catch (Exception e) {}

        backBtn = new JButton("Atrás");
        try {
            ImageIcon backIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
            backBtn.setIcon(backIcon);
            backBtn.setIconTextGap(8);
        } catch (Exception e) {}
        
        backBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        backBtn.setVerticalTextPosition(SwingConstants.CENTER);
        backBtn.setFocusPainted(false);
        backBtn.setBackground(Color.WHITE);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(backBtn);

        userField = crearCampo(card, "Usuario:", 50, 120);
        phoneField = crearCampo(card, "Teléfono:", 380, 120);
        emailField = crearCampo(card, "Correo:", 50, 190);
        
        masculino = new JRadioButton("Masculino"); masculino.setBounds(380, 190, 90, 25);
        femenino = new JRadioButton("Femenino"); femenino.setBounds(470, 190, 90, 25);
        otro = new JRadioButton("Otro"); otro.setBounds(560, 190, 90, 25);
        ButtonGroup bg = new ButtonGroup(); bg.add(masculino); bg.add(femenino); bg.add(otro);
        card.add(masculino); card.add(femenino); card.add(otro);

        passField = (JPasswordField) crearCampo(card, "Contraseña:", 50, 260, true);
        confirmField = (JPasswordField) crearCampo(card, "Confirmar contraseña:", 50, 330, true);

        registerBtn = new JButton("Registrar");
        registerBtn.setBounds(250, 405, 200, 40);
        registerBtn.setBackground(new Color(4, 180, 255));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(registerBtn);

        layeredPane.add(topPanel, Integer.valueOf(0));
        layeredPane.add(bottomPanel, Integer.valueOf(0));
        layeredPane.add(card, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reubicarComponentes();
            }
        });
    }

    private void reubicarComponentes() {
        int frameWidth = getContentPane().getWidth();
        int frameHeight = getContentPane().getHeight();

        int topHeight = (int) (frameHeight * 0.33);
        topPanel.setBounds(0, 0, frameWidth, topHeight);
        bottomPanel.setBounds(0, topHeight, frameWidth, frameHeight - topHeight);

        int cardWidth = 700;
        int cardHeight = 470;
        int cardX = (frameWidth - cardWidth) / 2;
        int cardY = (frameHeight - cardHeight) / 2;
        card.setBounds(cardX, cardY, cardWidth, cardHeight);

        Dimension sizeBtn = backBtn.getPreferredSize();
        int anchoBtnAtras = Math.max(100, sizeBtn.width + 15);
        backBtn.setBounds(20, 20, anchoBtnAtras, 30);

        logoLabel.setBounds(500, 10, 180, 70);

        layeredPane.setBounds(0, 0, frameWidth, frameHeight);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public void mostrarMensajeError(String mensaje) {
        JDialog dialogo = new JDialog(this, "Error", true);
        dialogo.setUndecorated(true); 
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(this);
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(209, 209, 209)); 
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        dialogo.setContentPane(panelPrincipal);
        JLabel lblTitulo = new JLabel("ERROR", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
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
        iconoX.setFont(new Font("Inter", Font.PLAIN, 50));
        iconoX.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMsg.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        centro.add(iconoX);
        centro.add(lblMsg);
        panelPrincipal.add(centro, BorderLayout.CENTER);
        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(0, 179, 255)); 
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 14));
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
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(this);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(209, 209, 209));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        dialogo.setContentPane(panelPrincipal);

        JLabel lblTitulo = new JLabel("ÉXITO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        // IMAGEN 
        ImageIcon icono = new ImageIcon(getClass().getResource("/img/palomitaverde.png"));

        Image img = icono.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);

        JLabel iconoCheck = new JLabel(new ImageIcon(img));
        iconoCheck.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMsg = new JLabel(
            "<html><body style='text-align: center;'>" + mensaje + "</body></html>",
            SwingConstants.CENTER
        );

        lblMsg.setFont(new Font("Iner", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMsg.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        centro.add(iconoCheck);
        centro.add(lblMsg);

        panelPrincipal.add(centro, BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(40, 167, 69));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 14));

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
}
package view;

import javax.swing.*;
import java.awt.*;

public class registro extends JFrame {

    // Componentes publicos para el controlador
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
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        // Fondos
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));
        topPanel.setBounds(0, 0, 900, 200);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(0, 200, 900, 400);

        // Card Blanca/Gris central
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

        // imagen logo
            ImageIcon logoIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/logo2.png")) 
                .getImage()
                .getScaledInstance(180, 70, Image.SCALE_SMOOTH)
            );
            JLabel logoLabel = new JLabel(logoIcono);
            logoLabel.setBounds(500, 10, 180, 70); 
            card.add(logoLabel);

        // Botón Atras 
        backBtn = new JButton("Atrás");
        backBtn.setBounds(20, 20, 90, 30);
        backBtn.setFocusPainted(false);
        backBtn.setBackground(Color.WHITE);
        card.add(backBtn);

        // Campos 
        userField = crearCampo(card, "Usuario:", 50, 85);
        phoneField = crearCampo(card, "Teléfono:", 380, 85);
        emailField = crearCampo(card, "Correo:", 50, 155);
        
        // RadioButtons 
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

    // Metodos auxiliares para no repetir codigo
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
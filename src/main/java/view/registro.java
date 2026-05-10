package view;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class registro {

    public static void main(String[] args) {

        // VENTANA PRINCIPAL
        JFrame frame = new JFrame();
        frame.setTitle("Registro");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fuentes
        Font inter = new Font("Inter", Font.PLAIN, 14);
        Font interBold = new Font("Inter", Font.BOLD, 14);

        // PANEL POR CAPAS
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0, 0, 900, 600);

        // PANEL SUPERIOR AZUL
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 51, 102));
        topPanel.setBounds(0, 0, 900, 200);

        // PANEL INFERIOR GRIS
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(0, 200, 900, 400);

        // PANEL CENTRAL CON BORDES REDONDOS
        JPanel card = new JPanel(null) {

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
        card.setBounds(100, 90, 700, 420);

        // BORDES
        Border grayBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);

        // CAMPOS RECTANGULARES
        JTextField userField = new JTextField();
        userField.setBounds(50, 115, 250, 35);
        userField.setBorder(grayBorder);
        userField.setFont(inter);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(380, 115, 250, 35);
        phoneField.setBorder(grayBorder);
        phoneField.setFont(inter);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 185, 250, 35);
        emailField.setBorder(grayBorder);
        emailField.setFont(inter);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(50, 255, 250, 35);
        passField.setBorder(grayBorder);
        passField.setFont(inter);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(50, 325, 250, 35);
        confirmField.setBorder(grayBorder);
        confirmField.setFont(inter);

        // ETIQUETAS
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(50, 90, 200, 20);
        userLabel.setFont(inter);

        JLabel phoneLabel = new JLabel("Teléfono:");
        phoneLabel.setBounds(380, 90, 200, 20);
        phoneLabel.setFont(inter);

        JLabel emailLabel = new JLabel("Correo:");
        emailLabel.setBounds(50, 160, 200, 20);
        emailLabel.setFont(inter);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(50, 230, 200, 20);
        passLabel.setFont(inter);

        JLabel confirmLabel = new JLabel("Confirmar contraseña:");
        confirmLabel.setBounds(50, 300, 200, 20);
        confirmLabel.setFont(inter);

        // BOTÓN REGISTRAR
        JButton registerBtn = new JButton("Registrar");
        registerBtn.setBounds(250, 370, 200, 40);
        registerBtn.setBackground(new Color(4,180,255));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBorderPainted(false);
        registerBtn.setFont(interBold);

        // BOTÓN ATRÁS
        JButton backBtn = new JButton("← Atrás");
        backBtn.setBounds(20, 20, 100, 30);
        backBtn.setFont(inter);

        // LOGO
        ImageIcon logo2 = new ImageIcon(login.class.getResource("/img/logo2.png"));

        Image img = logo2.getImage().getScaledInstance(160, 90, Image.SCALE_SMOOTH);

        JLabel logoLabel = new JLabel(new ImageIcon(img));
        logoLabel.setBounds(525, 5, 160, 90);

        // SEXO
        JLabel sexoLabel = new JLabel("Sexo:");
        sexoLabel.setBounds(380, 160, 200, 20);
        sexoLabel.setFont(inter);

        JRadioButton masculino = new JRadioButton("Masculino");
        masculino.setBounds(380, 185, 100, 20);
        masculino.setFont(inter);

        JRadioButton femenino = new JRadioButton("Femenino");
        femenino.setBounds(480, 185, 100, 20);
        femenino.setFont(inter);

        JRadioButton otro = new JRadioButton("Otro");
        otro.setBounds(580, 185, 80, 20);
        otro.setFont(inter);

        masculino.setOpaque(false);
        femenino.setOpaque(false);
        otro.setOpaque(false);

        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(masculino);
        grupoSexo.add(femenino);
        grupoSexo.add(otro);

        // POPUP ERROR
        JPanel popup = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(210, 210, 210));

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                super.paintComponent(g);
            }
        };

        popup.setLayout(null);
        popup.setOpaque(false);
        popup.setBounds(270, 180, 360, 180);
        popup.setVisible(false);

        // TÍTULO ERROR
        JLabel error = new JLabel("ERROR", SwingConstants.CENTER);
        error.setFont(new Font("Inter", Font.BOLD, 28));
        error.setBounds(0, 15, 360, 30);
        popup.add(error);

        // CÍRCULO ROJO
        JPanel circulo = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(255, 80, 40));

                g2.fillOval(0, 0, 50, 50);
            }
        };

        circulo.setOpaque(false);
        circulo.setBounds(155, 60, 50, 50);
        popup.add(circulo);

        // X BLANCA
        JLabel xLabel = new JLabel("×", SwingConstants.CENTER);
        xLabel.setForeground(Color.WHITE);
        xLabel.setFont(new Font("Inter", Font.BOLD, 34));
        xLabel.setBounds(155, 55, 50, 50);
        popup.add(xLabel);

        // MENSAJE
        JLabel mensaje = new JLabel("", SwingConstants.CENTER);
        mensaje.setFont(inter);
        mensaje.setBounds(20, 120, 320, 20);
        popup.add(mensaje);

        // BOTÓN OK
        JButton ok = new JButton("OK");
        ok.setBounds(105, 145, 150, 25);
        ok.setBackground(new Color(30, 170, 240));
        ok.setForeground(Color.WHITE);
        ok.setBorderPainted(false);
        ok.setFont(interBold);
        popup.add(ok);

        ok.addActionListener(e -> popup.setVisible(false));

        // ACCIÓN REGISTRAR
        registerBtn.addActionListener(e -> {

            userField.setBorder(grayBorder);
            phoneField.setBorder(grayBorder);
            emailField.setBorder(grayBorder);
            passField.setBorder(grayBorder);
            confirmField.setBorder(grayBorder);

            String user = userField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String pass = new String(passField.getPassword());
            String confirm = new String(confirmField.getPassword());

            if (user.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {

                mensaje.setText("Campos vacíos");
                popup.setVisible(true);

            } else if (!email.contains("@")) {

                emailField.setBorder(redBorder);
                mensaje.setText("Correo no válido");
                popup.setVisible(true);

            } else if (!phone.matches("\\d{10}")) {

                phoneField.setBorder(redBorder);
                mensaje.setText("Teléfono inválido");
                popup.setVisible(true);

            } else if (!pass.equals(confirm)) {

                passField.setBorder(redBorder);
                confirmField.setBorder(redBorder);
                mensaje.setText("Contraseñas no coinciden");
                popup.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(frame, "Registro exitoso");
                frame.dispose();
                Rewinds_Rental.Main.main(null);
            }
        });

        // BOTÓN ATRÁS
        backBtn.addActionListener(e -> {
            frame.dispose();
            Rewinds_Rental.Main.main(null);
        });

        // AGREGAR COMPONENTES
        card.add(userLabel);
        card.add(userField);

        card.add(phoneLabel);
        card.add(phoneField);

        card.add(emailLabel);
        card.add(emailField);

        card.add(passLabel);
        card.add(passField);

        card.add(confirmLabel);
        card.add(confirmField);

        card.add(sexoLabel);
        card.add(masculino);
        card.add(femenino);
        card.add(otro);

        card.add(registerBtn);
        card.add(backBtn);
        card.add(logoLabel);

        layeredPane.add(topPanel, Integer.valueOf(0));
        layeredPane.add(bottomPanel, Integer.valueOf(0));
        layeredPane.add(card, Integer.valueOf(1));
        layeredPane.add(popup, Integer.valueOf(2));

        frame.setContentPane(layeredPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
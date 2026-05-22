package view;

import models.Sesion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class principal extends JFrame
{
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas, logoutBtn, lblUsuario;
    public JButton btnVerClientes, btnVerRentas, btnVerJuegosComprados, btnVerJuegosRentados, btnVerPelisCompradas, btnVerPelisRentadas;

    public principal()
    {
        setTitle("Principal");
        setSize(1000, 600);
        setMinimumSize(new Dimension(850, 550));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        try
        {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        }
        catch(Exception e)
        {
        }

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setPreferredSize(new Dimension(160, 0));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));

        btnInicio = crearItemMenu(sidebar, "Inicio", "/img/casaazul.png");
        btnOperacion = crearItemMenu(sidebar, "Operación", "/img/simbolomasazul.png");
        btnClientes = crearItemMenu(sidebar, "Clientes", "/img/simboloclientesazul.png");
        btnVideojuegos = crearItemMenu(sidebar, "Videojuegos", "/img/simbolovideojuegosazul.png");
        btnPeliculas = crearItemMenu(sidebar, "Peliculas", "/img/simbolopeliculasazul.png");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel header = new JPanel(new GridBagLayout())
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(225, 225, 225));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 45, 45);
            }
        };
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(0, 100));
        GridBagConstraints gbcH = new GridBagConstraints();

        JLabel logoLabel = new JLabel();
        try
        {
            logoLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/logo0.png"))
                .getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
        }
        catch(Exception e)
        {
        }

        gbcH.gridx = 0; gbcH.weightx = 0.1; gbcH.anchor = GridBagConstraints.WEST; gbcH.insets = new Insets(0, 20, 0, 0);
        header.add(logoLabel, gbcH);

        JLabel lblBienvenidoCentro = new JLabel("Bienvenido", SwingConstants.CENTER);
        lblBienvenidoCentro.setFont(new Font("Inter", Font.BOLD, 24));
        lblBienvenidoCentro.setForeground(new Color(0, 51, 102));

        gbcH.gridx = 1;
        gbcH.weightx = 1.0;
        gbcH.anchor = GridBagConstraints.CENTER;
        gbcH.insets = new Insets(0, 50, 0, 0);
        header.add(lblBienvenidoCentro, gbcH);

        String nombreActual = Sesion.getNombreUsuario();
        lblUsuario = new JLabel("<html>Bienvenido<br>" + nombreActual + "</html>", SwingConstants.LEFT);
        lblUsuario.setFont(new Font("Inter", Font.BOLD, 14));

        try
        {
            ImageIcon userIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/simboloclientesazul.png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            lblUsuario.setIcon(userIcon);
            lblUsuario.setIconTextGap(5);
        }
        catch(Exception e)
        {
        }

        gbcH.gridx = 2; gbcH.weightx = 0.1; gbcH.anchor = GridBagConstraints.EAST; gbcH.insets = new Insets(0, 0, 0, 20);
        header.add(lblUsuario, gbcH);

        logoutBtn = new JLabel();
        try
        {
            logoutBtn.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/simbolo_logoutrojo.png"))
                .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        }
        catch(Exception e)
        {
        }
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbcH.gridx = 3; gbcH.weightx = 0; gbcH.anchor = GridBagConstraints.EAST; gbcH.insets = new Insets(0, 0, 0, 20);
        header.add(logoutBtn, gbcH);

        JPanel cardsContent = new JPanel(new GridBagLayout());
        cardsContent.setOpaque(false);
        GridBagConstraints gbcC = new GridBagConstraints();
        gbcC.insets = new Insets(15, 15, 15, 15);

        JPanel card1 = Card("Clientes", "1000");
        btnVerClientes = (JButton) ((JPanel)card1.getComponent(2)).getComponent(0);

        JPanel card2 = Card("Rentas y Ventas", "1500");
        btnVerRentas = (JButton) ((JPanel)card2.getComponent(2)).getComponent(0);

        JPanel card3 = crearCardDoble("Películas");
        btnVerPelisCompradas = (JButton) ((JPanel)card3.getComponent(2)).getComponent(0);
        btnVerPelisRentadas = (JButton) ((JPanel)card3.getComponent(2)).getComponent(1);

        JPanel card4 = crearCardDoble("Videojuegos");
        btnVerJuegosComprados = (JButton) ((JPanel)card4.getComponent(2)).getComponent(0);
        btnVerJuegosRentados = (JButton) ((JPanel)card4.getComponent(2)).getComponent(1);

        gbcC.gridx = 0; gbcC.gridy = 0; cardsContent.add(card1, gbcC);
        gbcC.gridx = 1; gbcC.gridy = 0; cardsContent.add(card2, gbcC);
        gbcC.gridx = 0; gbcC.gridy = 1; cardsContent.add(card3, gbcC);
        gbcC.gridx = 1; gbcC.gridy = 1; cardsContent.add(card4, gbcC);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(cardsContent, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public void mostrarConfirmacionSalir(ActionListener accionSi, ActionListener accionNo)
    {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(20));
        JLabel lblMsg = new JLabel("<html><center>¿Estás seguro que<br>deseas cerrar sesión?</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblMsg);

        panel.add(Box.createVerticalGlue());
        try
        {
            JLabel icono = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                .getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            icono.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(icono);
        }
        catch (Exception e)
        {
        }
        panel.add(Box.createVerticalGlue());

        JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pBotones.setOpaque(false);

        JButton btnSi = crearBotonRedondo("Sí, salir", new Color(220, 50, 50), 110, 35);
        btnSi.addActionListener(e ->
        {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });

        JButton btnNo = crearBotonRedondo("Cancelar", new Color(130, 130, 130), 110, 35);
        btnNo.addActionListener(e ->
        {
            dialogo.dispose();
            if (accionNo != null) accionNo.actionPerformed(e);
        });

        pBotones.add(btnSi);
        pBotones.add(btnNo);
        panel.add(pBotones);
        panel.add(Box.createVerticalStrut(5));

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    private JLabel crearItemMenu(JPanel panel, String texto, String ruta)
    {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));

        JLabel iconLabel = new JLabel();
        try
        {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        catch(Exception e)
        {
        }
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(new Color(4, 180, 255));
        label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        item.add(iconLabel);
        item.add(Box.createVerticalStrut(5));
        item.add(label);
        panel.add(item);
        return label;
    }

    private JPanel Card(String titulo, String numero)
    {
        JPanel panel = crearContenedorRedondeado(220, 140);
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel t = new JLabel(titulo, SwingConstants.CENTER);
        t.setFont(new Font("Inter", Font.BOLD, 18));
        JLabel n = new JLabel(numero, SwingConstants.CENTER);
        n.setForeground(new Color(4, 180, 255));
        n.setFont(new Font("Inter", Font.BOLD, 26));

        JButton btn = crearBotonRedondo("Ver", new Color(4, 180, 255), 80, 30);
        JPanel pBtn = new JPanel(new FlowLayout());
        pBtn.setOpaque(false);
        pBtn.add(btn);

        panel.add(t, BorderLayout.NORTH);
        panel.add(n, BorderLayout.CENTER);
        panel.add(pBtn, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearCardDoble(String titulo)
    {
        JPanel panel = crearContenedorRedondeado(220, 150);
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel t = new JLabel(titulo, SwingConstants.CENTER);
        t.setFont(new Font("Inter", Font.BOLD, 18));

        JPanel pLabels = new JPanel(new GridLayout(1, 2));
        pLabels.setOpaque(false);
        JLabel m1 = new JLabel("Más Comprado", SwingConstants.CENTER);
        m1.setFont(new Font("Inter", Font.PLAIN, 11));
        JLabel m2 = new JLabel("Más Rentado", SwingConstants.CENTER);
        m2.setFont(new Font("Inter", Font.PLAIN, 11));
        pLabels.add(m1); pLabels.add(m2);

        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pBtns.setOpaque(false);
        pBtns.add(crearBotonRedondo("Ver", new Color(4, 180, 255), 75, 30));
        pBtns.add(crearBotonRedondo("Ver", new Color(4, 180, 255), 75, 30));

        panel.add(t, BorderLayout.NORTH);
        panel.add(pLabels, BorderLayout.CENTER);
        panel.add(pBtns, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearContenedorRedondeado(int w, int h)
    {
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(215, 215, 215));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
            }
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(w, h));
        return panel;
    }

    private JButton crearBotonRedondo(String texto, Color color, int w, int h)
    {
        JButton btn = new JButton(texto)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setPreferredSize(new Dimension(w, h));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Inter", Font.BOLD, 12));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void setNombreUsuario(String nombre)
    {
        lblUsuario.setText("<html>Bienvenido<br>" + nombre + "</html>");
    }
}
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HistorialVentas extends JFrame 
{
    public JButton btnAtras;
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JTable tabla;
    public DefaultTableModel modelo;
    private JPanel panelFondo;
    private JScrollPane scroll;
    private JLabel lblTitulo;

    public HistorialVentas() 
    {
        setTitle("Historial de ventas del cliente");
        setMinimumSize(new Dimension(1100, 700));
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        try 
        {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } 
        catch(Exception e) {}

        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(160, 0));
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(sidebar, BorderLayout.WEST);

        btnInicio = Menu(sidebar, "Inicio", "/img/casaazul.png");
        btnOperacion = Menu(sidebar, "Operación", "/img/simbolomasazul.png");
        btnClientes = Menu(sidebar, "Clientes", "/img/simboloclientesazul.png");
        btnVideojuegos = Menu(sidebar, "Videojuegos", "/img/simbolovideojuegosazul.png");
        btnPeliculas = Menu(sidebar, "Peliculas", "/img/simbolopeliculasazul.png");

        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setOpaque(false);
        add(mainContainer, BorderLayout.CENTER);

        panelFondo = new JPanel(null) 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panelFondo.setOpaque(false);
        panelFondo.setPreferredSize(new Dimension(880, 620));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        mainContainer.add(panelFondo, gbc);

        btnAtras = crearBotonRedondo("  Atrás", new Color(225, 225, 225), new Color(45, 59, 72));
        btnAtras.setFont(new Font("Inter", Font.BOLD, 13));
        try 
        {
            btnAtras.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } 
        catch (Exception e) {}
        panelFondo.add(btnAtras);

        lblTitulo = new JLabel("Historial de ventas del cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        panelFondo.add(lblTitulo);

        String[] columnas = {"Producto", "Plataforma", "Monto de pago", "Fecha de compra", "Descuento"};

        modelo = new DefaultTableModel(null, columnas) 
        {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(55);
        tabla.setFont(new Font("Inter", Font.PLAIN, 14));
        tabla.setSelectionBackground(new Color(0, 170, 255, 50));
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setShowVerticalLines(false);

        JTableHeader header = tabla.getTableHeader();
        header.setPreferredSize(new Dimension(0, 45));
        header.setFont(new Font("Inter", Font.BOLD, 14));
        header.setBackground(new Color(0, 51, 102));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.WHITE);
        panelFondo.add(scroll);

        this.addComponentListener(new ComponentAdapter() 
        {
            @Override
            public void componentResized(ComponentEvent e) 
            {
                ajustarComponentes();
            }
        });

        SwingUtilities.invokeLater(() -> ajustarComponentes());
    }

    private void ajustarComponentes() 
    {
        int w = panelFondo.getWidth();
        int h = panelFondo.getHeight();
        if (w <= 0 || h <= 0) return;
        
        btnAtras.setBounds(25, 20, 110, 35);
        lblTitulo.setBounds((w - 400) / 2, 20, 400, 35);
        scroll.setBounds(25, 80, w - 50, h - 120);
    }

    public void mostrarAlerta(String mensaje, boolean esError) 
    {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280);
        dialogo.setLocationRelativeTo(this);
        
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));

        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.add(Box.createVerticalStrut(30));

        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblMsg);
        contenido.add(Box.createVerticalGlue());
        
        JButton btnOk = crearBotonRedondo("Aceptar", esError ? new Color(220, 50, 50) : new Color(0, 170, 255), Color.WHITE);
        btnOk.setPreferredSize(new Dimension(120, 38));
        btnOk.setFont(new Font("Inter", Font.BOLD, 13));
        btnOk.addActionListener(e -> dialogo.dispose());
        
        JPanel pBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pBot.setOpaque(false);
        pBot.add(btnOk);

        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.add(pBot, BorderLayout.SOUTH);
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    public JLabel Menu(JPanel panel, String texto, String ruta) 
    {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setOpaque(false);
        item.setPreferredSize(new Dimension(140, 90));
        JLabel iconLabel = new JLabel();
        try 
        {
            iconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        } 
        catch(Exception e) {}
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

    private JButton crearBotonRedondo(String texto, Color bg, Color fg) 
    {
        JButton btn = new JButton(texto) 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setForeground(fg);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
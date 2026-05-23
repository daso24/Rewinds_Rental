package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class operaciones extends JFrame
{
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnEliminar, btnFiltrar, btnAtras;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modeloTabla;
    public TableRowSorter<DefaultTableModel> sorter;
    
    private final Font INTER_BOLD_26 = new Font("Inter", Font.BOLD, 26);
    private final Font INTER_BOLD_16 = new Font("Inter", Font.BOLD, 16);
    private final Font INTER_BOLD_14 = new Font("Inter", Font.BOLD, 14);
    private final Font INTER_BOLD_13 = new Font("Inter", Font.BOLD, 13);
    private final Font INTER_REGULAR_14 = new Font("Inter", Font.PLAIN, 14);
    private final Font INTER_REGULAR_13 = new Font("Inter", Font.PLAIN, 13);

    public operaciones()
    {
        setTitle("Operaciones - Rewinds Rental");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try
        {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        }
        catch(Exception e)
        {
        }

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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(0, 80));
        
        btnAtras = new JButton(" Atrás")
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 220, 220)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        try
        {
            btnAtras.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lets-icons_back.png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        }
        catch(Exception e)
        {
        }
        btnAtras.setPreferredSize(new Dimension(110, 35));
        btnAtras.setFont(INTER_BOLD_13);
        btnAtras.setForeground(new Color(45, 59, 72));
        btnAtras.setContentAreaFilled(false);
        btnAtras.setBorderPainted(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel atrasContenedor = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 22));
        atrasContenedor.setOpaque(false);
        atrasContenedor.add(btnAtras);
        header.add(atrasContenedor, BorderLayout.WEST);

        JLabel titulo = new JLabel("Operaciones", SwingConstants.CENTER);
        titulo.setFont(INTER_BOLD_26);
        header.add(titulo, BorderLayout.CENTER);

        btnAgregar = new JButton("+ Añadir Operación")
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 170, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnAgregar.setPreferredSize(new Dimension(190, 35));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(INTER_BOLD_13);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel btnContenedor = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 20));
        btnContenedor.setOpaque(false);
        btnContenedor.add(btnAgregar);
        header.add(btnContenedor, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        JPanel centerContent = new JPanel(new BorderLayout(0, 15));
        centerContent.setOpaque(false);

        JPanel searchPanel = new JPanel(new BorderLayout(15, 0));
        searchPanel.setPreferredSize(new Dimension(0, 60)); 
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(25), 
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        JLabel lupa = new JLabel("Buscar:");
        lupa.setFont(INTER_BOLD_14);
        searchPanel.add(lupa, BorderLayout.WEST);

        buscador = new JTextField();
        buscador.setFont(INTER_REGULAR_14);
        buscador.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        searchPanel.add(buscador, BorderLayout.CENTER);

        JPanel panelBotonesDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotonesDerecha.setOpaque(false);
        btnBuscar = crearBotonSimple("Buscar", new Color(45, 59, 72));
        btnFiltrar = crearBotonSimple("Filtrar",  new Color(0, 170, 255));
        panelBotonesDerecha.add(btnBuscar);
        panelBotonesDerecha.add(btnFiltrar);
        searchPanel.add(panelBotonesDerecha, BorderLayout.EAST);

        centerContent.add(searchPanel, BorderLayout.NORTH);

        String[] columnas = {"", "Cliente", "Tipo", "Producto", "Tipo Producto", "Plataforma", "Info"};
        Object infoIcon = getImgPequeño("/img/Vector.png"); 
        Object userIcon = getAvatar("/img/placeholder_usuario.png");

        Object[][] datos = {
            {false, new Object[]{userIcon, "Mateo Valeriano Soler"}, "Renta", new Object[]{getImg("/img/forza_horizon_6-6006996.jpg"), "Forza Horizon 6"}, new Object[]{getImgPequeño("/img/carbon_game-console.png"), "Consola"}, "Xbox Series X", new Object[]{infoIcon, "Ver info"}},
            {false, new Object[]{userIcon, "Lucía Fernanda Mondragón"}, "Venta", new Object[]{getImg("/img/71fw9QnEQUL.jpg"), "Spider-Man 2 "}, new Object[]{getImgPequeño("/img/carbon_game-console.png"), "Consola"}, "PS5", new Object[]{infoIcon, "Ver info"}},
            {false, new Object[]{userIcon, "Adrián Celis Olavarría"}, "Renta", new Object[]{getImg("/img/71w58zkWnfL.jpg"), "Chainsaw-man"}, new Object[]{getImgPequeño("/img/fluent_movies-and-tv-16-filled.png"), "Pelicula"}, "Blue_ray", new Object[]{infoIcon, "Ver info"}},
            {false, new Object[]{userIcon, "Elena Beatriz Iturbide"}, "Venta", new Object[]{getImg("/img/51gz5Gfjl8L._AC_UF894,1000_QL80_.jpg"), "Rocky IV"}, new Object[]{getImgPequeño("/img/fluent_movies-and-tv-16-filled.png"), "Pelicula"}, "DVD", new Object[]{infoIcon, "Ver info"}},
            {false, new Object[]{userIcon, "Tobías Martínez"}, "Renta", new Object[]{getImg("/img/71MZBMmOXtL._AC_UF894,1000_QL80_.jpg"), "Dragon ball super Broly"}, new Object[]{getImgPequeño("/img/fluent_movies-and-tv-16-filled.png"), "Pelicula"}, "Blue-Ray", new Object[]{infoIcon, "Ver info"}}
        };

        modeloTabla = new DefaultTableModel(datos, columnas)
        {
            @Override
            public Class<?> getColumnClass(int c)
            {
                if(c == 0) return Boolean.class;
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int r, int c)
            {
                return c == 0;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(85);
        tabla.setFont(INTER_REGULAR_13);
        tabla.getTableHeader().setFont(INTER_BOLD_13);
        tabla.getColumnModel().getColumn(1).setCellRenderer(new IconTextHorizontalRenderer());
        tabla.getColumnModel().getColumn(3).setCellRenderer(new IconTextVerticalRenderer());
        tabla.getColumnModel().getColumn(4).setCellRenderer(new IconTextVerticalRenderer());
        tabla.getColumnModel().getColumn(6).setCellRenderer(new IconTextHorizontalRenderer());
        
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        centerContent.add(scroll, BorderLayout.CENTER);

        btnEliminar = new JButton("Eliminar seleccionado")
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 50, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnEliminar.setPreferredSize(new Dimension(220, 38));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(INTER_BOLD_14);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.add(btnEliminar);
        centerContent.add(footer, BorderLayout.SOUTH);

        mainPanel.add(centerContent, BorderLayout.CENTER);
    }

    public void mostrarConfirmacion(String mensaje, ActionListener accionSi)
    {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205); 
        dialogo.setLocationRelativeTo(this);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        dialogo.setContentPane(contenedor);

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(15));
        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(INTER_BOLD_16);
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        panelContenido.add(Box.createVerticalGlue());
        try
        {
            JLabel iconoCentro = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        }
        catch (Exception e)
        {
        }
        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setOpaque(false);
        JButton btnSi = crearBotonDialogo("Eliminar", new Color(220, 50, 50));
        btnSi.addActionListener(e ->
        {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });
        JButton btnNo = crearBotonDialogo("Cancelar", new Color(130, 130, 130));
        btnNo.addActionListener(e -> dialogo.dispose());
        panelBotones.add(btnSi);
        panelBotones.add(btnNo);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private JButton crearBotonSimple(String texto, Color color)
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
        btn.setPreferredSize(new Dimension(110, 32));
        btn.setForeground(Color.WHITE);
        btn.setFont(INTER_BOLD_13);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton crearBotonDialogo(String texto, Color color)
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
        btn.setPreferredSize(new Dimension(110, 35));
        btn.setForeground(Color.WHITE);
        btn.setFont(INTER_BOLD_13);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
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

    private ImageIcon getAvatar(String ruta)
    {
        try
        {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private ImageIcon getImg(String ruta)
    {
        try
        {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private ImageIcon getImgPequeño(String ruta)
    {
        try
        {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    class IconTextVerticalRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c)
        {
            JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, c);
            l.setHorizontalAlignment(JLabel.CENTER);
            l.setVerticalAlignment(JLabel.CENTER);
            l.setVerticalTextPosition(JLabel.BOTTOM);
            l.setHorizontalTextPosition(JLabel.CENTER);
            if (v instanceof Object[])
            {
                Object[] data = (Object[]) v;
                l.setIcon((Icon) data[0]);
                l.setText((String) data[1]);
            }
            return l;
        }
    }

    class IconTextHorizontalRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c)
        {
            JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, c);
            l.setHorizontalAlignment(JLabel.LEFT);
            l.setVerticalAlignment(JLabel.CENTER);
            l.setIconTextGap(10);
            l.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            if (v instanceof Object[])
            {
                Object[] data = (Object[]) v;
                l.setIcon((Icon) data[0]);
                l.setText((String) data[1]);
            }
            else
            {
                l.setIcon(null);
            }
            return l;
        }
    }

    class RoundedBorder implements Border
    {
        int r;
        RoundedBorder(int r)
        {
            this.r = r;
        }
        public Insets getBorderInsets(Component c)
        {
            return new Insets(5, 10, 5, 10);
        }
        public boolean isBorderOpaque()
        {
            return false;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }
}
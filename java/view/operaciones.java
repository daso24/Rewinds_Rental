package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class operaciones extends JFrame {

    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnFiltrar, btnEliminar;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modeloTabla;
    public TableRowSorter<DefaultTableModel> sorter;

    public operaciones() {
        setTitle("Operaciones");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // PANEL LATERAL
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 650);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        btnInicio = Menu(sidebar, "Inicio", 80, "/img/gravity-ui_house-fill.png");
        btnOperacion = Menu(sidebar, "Operación", 150, "/img/ic_baseline-plus.png");
        btnClientes = Menu(sidebar, "Clientes", 260, "/img/material-symbols_person.png");
        btnVideojuegos = Menu(sidebar, "Videojuegos", 370, "/img/carbon_game-console.png");
        btnPeliculas = Menu(sidebar, "Peliculas", 480, "/img/fluent_movies-and-tv-16-filled.png");

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 650);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAgregar = new JButton("+ Añadir operación");
        btnAgregar.setBounds(590, 20, 220, 35);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        mainPanel.add(btnAgregar);

        JLabel titulo = new JLabel("Operaciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(340, 20, 200, 30);
        mainPanel.add(titulo);

     
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(20, 80, 790, 60);
        searchPanel.setLayout(null);
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(new RoundedBorder(20));
        mainPanel.add(searchPanel);
        
        JLabel lupa = new JLabel("Buscar:");
        lupa.setBounds(15, 15, 60, 30);
        searchPanel.add(lupa);


        buscador = new JTextField();
        buscador.setBounds(80, 15, 450, 30);
      
        buscador.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchPanel.add(buscador);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(540, 15, 110, 30);
        searchPanel.add(btnBuscar);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(660, 15, 100, 30);
        searchPanel.add(btnFiltrar);

        // CONFIGURACIÓN DE TABLA
        String[] columnas = {"", "Cliente", "Tipo", "Producto", "Tipo producto", "Plataforma", "Info"};
        Object[][] datos = {
            {false, "Mateo Valeriano Soler", "Renta", getImg("/img/forza_horizon_6-6006996.jpg"), getImg("/img/carbon_game-console.png"), "Xbox Series X", "Ver info"},
            {false, "Lucía Fernanda Mondragón", "Venta", getImg("/img/71fw9QnEQUL.jpg"), getImg("/img/carbon_game-console.png"), "PS5", "Ver info"}
        };

        modeloTabla = new DefaultTableModel(datos, columnas) {
            @Override public Class<?> getColumnClass(int c) { 
                if(c == 0) return Boolean.class;
                if(c == 3 || c == 4) return Icon.class;
                return Object.class;
            }
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(70);
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        mainPanel.add(scroll);

        btnEliminar = new JButton("Eliminar operación");
        btnEliminar.setBounds(320, 540, 200, 35);
        btnEliminar.setBackground(new Color(255, 87, 34));
        btnEliminar.setForeground(Color.WHITE);
        mainPanel.add(btnEliminar);
    }

    private ImageIcon getImg(String ruta) {
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            return null;
        }
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        try {
            JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            iconLabel.setBounds(15, y, 25, 30);
            panel.add(iconLabel);
        } catch(Exception e) {}
        
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label);
        return label;
    }

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, "Confirmar", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280); 
        dialogo.setLocationRelativeTo(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(209, 209, 209));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        dialogo.setContentPane(panelPrincipal);

        panelPrincipal.add(Box.createVerticalStrut(20));

        JLabel lblMsg = new JLabel("<html><body style='text-align: center;'>" + mensaje + "</body></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblMsg);

        panelPrincipal.add(Box.createVerticalGlue());

        try {
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel iconoCentro = new JLabel(imagenAlerta);
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelPrincipal.add(iconoCentro);
        } catch (Exception e) {}

        panelPrincipal.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);

        JButton btnSi = new JButton("Eliminar");
        btnSi.setBackground(new Color(220, 50, 50));
        btnSi.setForeground(Color.WHITE);
        btnSi.setFocusPainted(false);
        btnSi.addActionListener(e -> {
            dialogo.dispose();
            accionSi.actionPerformed(e); 
        });

        JButton btnNo = new JButton("Cancelar");
        btnNo.setBackground(new Color(150, 150, 150));
        btnNo.setForeground(Color.WHITE);
        btnNo.setFocusPainted(false);
        btnNo.addActionListener(e -> dialogo.dispose());

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(20));

        dialogo.setVisible(true);
    }

    public class RoundedBorder implements Border {
        int r; RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r, r, r, r); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }
}
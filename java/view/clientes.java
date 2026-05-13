package view;

import javax.swing.*; 
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class clientes extends JFrame {
  
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;
    public JButton btnAgregar, btnBuscar, btnEliminar;
    public JTextField buscador;
    public JTable tabla;
    public DefaultTableModel modelo;
    public TableRowSorter<DefaultTableModel> sorter;

    public clientes() {
        setTitle("Gestión de Clientes");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try {
            Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
            this.setIconImage(icono);
        } catch(Exception e) {}

        // BARRA LATERAL
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
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(null);
        add(mainPanel);

        btnAgregar = new JButton("+ Añadir cliente");
        btnAgregar.setBounds(590, 20, 220, 35);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        mainPanel.add(btnAgregar);

        JLabel titulo = new JLabel("Clientes");
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
        titulo.setBounds(340, 20, 200, 30);
        mainPanel.add(titulo);

        // BUSCADOR
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
        buscador.setBounds(80, 15, 550, 30);
        searchPanel.add(buscador);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(650, 15, 110, 30);
        searchPanel.add(btnBuscar);

        // TABLA CON DATOS
        String[] columnas = {"", "Nombre", "Cédula/ID", "Teléfono", "Correo", "Estado", "Acción"};
        Object[][] datos = {
            {false, "Juan Pérez", "12345678", "099887766", "juan@mail.com", "Activo", "Ver info"},
            {false, "María López", "87654321", "099112233", "maria@mail.com", "Activo", "Ver info"}
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override public Class<?> getColumnClass(int c) {
                return (c == 0) ? Boolean.class : String.class;
            }
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(40);
        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 340);
        mainPanel.add(scroll);

        btnEliminar = new JButton("Eliminar seleccionado");
        btnEliminar.setBounds(320, 540, 200, 35);
        btnEliminar.setBackground(new Color(255, 87, 34));
        btnEliminar.setForeground(Color.WHITE);
        mainPanel.add(btnEliminar);
    }

    public void mostrarConfirmacionEliminar(String mensaje, java.awt.event.ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, "Confirmar", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 280); 
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        dialogo.setContentPane(contenedor);

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(25));

        JLabel lblMsg = new JLabel("<html><div style='text-align: center; width: 250px;'>" + mensaje + "</div></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 16));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);

        panelContenido.add(Box.createVerticalGlue());

        try {
            ImageIcon imagenAlerta = new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png"))
                    .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            JLabel iconoCentro = new JLabel(imagenAlerta);
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        } catch (Exception e) {}

        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setOpaque(false);

        JButton btnSi = new JButton("Eliminar");
        btnSi.setPreferredSize(new Dimension(100, 35));
        btnSi.setBackground(new Color(220, 50, 50));
        btnSi.setForeground(Color.WHITE);
        btnSi.addActionListener(e -> {
            dialogo.dispose();
            accionSi.actionPerformed(e);
        });

        JButton btnNo = new JButton("Cancelar");
        btnNo.setPreferredSize(new Dimension(100, 35));
        btnNo.setBackground(new Color(150, 150, 150));
        btnNo.setForeground(Color.WHITE);
        btnNo.addActionListener(e -> dialogo.dispose());

        panelBotones.add(btnSi); panelBotones.add(btnNo);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    public JLabel Menu(JPanel panel, String texto, int y, String ruta) {
        JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(iconLabel); panel.add(label);
        return label;
    }

    class RoundedBorder implements Border {
        int r; RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r, r, r, r); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) { g.drawRoundRect(x, y, w - 1, h - 1, r, r); }
    }
}
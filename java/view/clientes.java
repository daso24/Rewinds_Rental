package view;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import view.AñadirClientes;
import view.principal;

import java.awt.*;
import java.awt.event.*;

public class clientes extends JFrame {

    public clientes() {

        setTitle("Clientes");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); 
        setLocationRelativeTo(null);
        setLayout(null);
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

        // panel lateral
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 650);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        // Carga de iconos para el menu
        ImageIcon inicioIcono = new ImageIcon(new ImageIcon(getClass().getResource("/img/gravity-ui_house-fill.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        ImageIcon operacionesIcono = new ImageIcon(new ImageIcon(getClass().getResource("/img/ic_baseline-plus.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        ImageIcon clientesIcono = new ImageIcon(new ImageIcon(getClass().getResource("/img/material-symbols_person.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        ImageIcon videojuegosIcono = new ImageIcon(new ImageIcon(getClass().getResource("/img/carbon_game-console.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        ImageIcon peliculasIcono = new ImageIcon(new ImageIcon(getClass().getResource("/img/fluent_movies-and-tv-16-filled.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        
        Menu(sidebar, "Inicio", 80, inicioIcono);
        Menu(sidebar, "Operación", 150, operacionesIcono);
        Menu(sidebar, "Clientes", 260, clientesIcono);
        Menu(sidebar, "Videojuegos", 370, videojuegosIcono);
        Menu(sidebar, "Peliculas", 480, peliculasIcono);

        // panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 650);
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(null);
        add(mainPanel);

        // botones superiores
        JButton btnAgregar = new JButton("+ Añadir cliente");
        btnAgregar.setBounds(600, 20, 210, 35);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.addActionListener(e -> {
            new AñadirClientes().setVisible(true);
            dispose(); 
        });
        mainPanel.add(btnAgregar);

        JLabel titulo = new JLabel("Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(340, 20, 200, 30);
        mainPanel.add(titulo);

        // barra de buesqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(20, 80, 790, 60);
        searchPanel.setLayout(null);
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(new RoundedBorder(20));
        mainPanel.add(searchPanel);

        JLabel lupa = new JLabel("Buscar:");
        lupa.setBounds(15, 15, 60, 30);
        searchPanel.add(lupa);

        JTextField buscador = new JTextField();
        buscador.setBounds(80, 15, 470, 30);
        buscador.setBackground(Color.WHITE);
        buscador.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchPanel.add(buscador);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(540, 15, 110, 30);
        searchPanel.add(btnBuscar);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(660, 15, 100, 30);
        searchPanel.add(btnFiltrar);

        // TABLA CON TUS DATOS ORIGINALES
        String[] columnas = {"", "Cliente", "Id cliente", "Rentas activas", "Ultima compra", "Ultima renta", "Info"};
        Object[][] datos = {
                {false, "Mateo Valeriano Soler", "482915", "3", "08/07/2025", "19/03/2026", "Ver info"},
                {false, "Lucía Fernanda Mondragón", "730642", "5", "02/02/2026", "23/05/2026", "Ver info"},
                {false, "Adrián Celis Olavarría", "105422", "2", "20/01/2026", "23/03/2026", "Ver info"},
                {false, "Elena Beatriz Iturbide", "195873", "1", "08/07/2025", "23/03/2026", "Ver info"},
                {false, "Javier Amador Vizcaíno", "627104", "2", "08/11/2025", "24/03/2026", "Ver info"}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int column) { return (column == 0) ? Boolean.class : String.class; }
            @Override
            public boolean isCellEditable(int row, int column) { return column == 0; }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(55);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        mainPanel.add(scroll);

        // BOTÓN ELIMINAR
        JButton btnEliminar = new JButton("Eliminar cliente");
        btnEliminar.setBounds(320, 540, 200, 35);
        btnEliminar.setBackground(new Color(255, 87, 34));
        btnEliminar.setForeground(Color.WHITE);
        mainPanel.add(btnEliminar);

        // LÓGICA DE BÚSQUEDA
        btnBuscar.addActionListener(e -> {
            String texto = buscador.getText().trim();
            if (texto.isEmpty()) sorter.setRowFilter(null);
            else sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        });

        // LÓGICA DE ELIMINACIÓN
        btnEliminar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar los clientes seleccionados?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                for (int i = tabla.getRowCount() - 1; i >= 0; i--) {
                    Boolean seleccionado = (Boolean) tabla.getValueAt(i, 0);
                    if (seleccionado != null && seleccionado) {
                        modelo.removeRow(tabla.convertRowIndexToModel(i));
                    }
                }
            }
        });

        setVisible(true);
    }

    public void Menu(JPanel panel, String texto, int y, Icon icono) {
        JLabel iconLabel = new JLabel(icono);
        iconLabel.setBounds(15, y, 25, 30);
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(50, y, 120, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame ventana = null;
                switch (texto) {
                    case "Inicio": ventana = new principal(); break;
                    case "Videojuegos": ventana = new videojuegos(); break;
                    case "Clientes": ventana = new clientes(); break;
                    case "Operación": ventana = new operaciones(); break;
                    case "Peliculas": ventana = new peliculas(); break;
                }
                if (ventana != null) { ventana.setVisible(true); dispose(); }
            }
        });
        panel.add(iconLabel);
        panel.add(label);
    }

    class RoundedBorder implements Border {
        int r;
        RoundedBorder(int r) { this.r = r; }
        public Insets getBorderInsets(Component c) { return new Insets(r, r, r, r); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) { g.drawRoundRect(x, y, w - 1, h - 1, r, r); }
    }

    public static void main(String[] args) { new clientes(); }
}
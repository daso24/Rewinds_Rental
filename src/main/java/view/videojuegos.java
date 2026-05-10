package view;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import view.AgregarJuego;
import view.principal;

import java.awt.*;
import java.awt.event.*;

public class videojuegos extends JFrame {

    public videojuegos() {

        setTitle("Videojuegos");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // BARRA LATERAL

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 160, 650);
        sidebar.setBackground(new Color(0, 51, 102));
        sidebar.setLayout(null);
        add(sidebar);

        ImageIcon inicioIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/gravity-ui_house-fill.png"))
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        );

        ImageIcon operacionesIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/ic_baseline-plus.png"))
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        );

        ImageIcon clientesIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/material-symbols_person.png"))
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        );

        ImageIcon videojuegosIcono = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/carbon_game-console.png"))
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        );

        ImageIcon peliculasIcono= new ImageIcon(
                new ImageIcon(getClass().getResource("/img/fluent_movies-and-tv-16-filled.png"))
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        );
        
        Menu(sidebar, "Inicio", 80, inicioIcono);
        Menu(sidebar, "Operación", 150, operacionesIcono);
        Menu(sidebar, "Clientes", 260, clientesIcono);
        Menu(sidebar, "Videojuegos", 370, videojuegosIcono);
        Menu(sidebar, "Peliculas", 480, peliculasIcono);

        // PANEL PRINCIPAL

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 0, 840, 650);
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(null);
        add(mainPanel);

        JButton btnAgregar = new JButton("+ Añadir un videojuego");
        btnAgregar.setBounds(570, 20, 240, 35);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        mainPanel.add(btnAgregar);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new AgregarJuego().setVisible(true);
                dispose();
            }
        });

        JLabel titulo = new JLabel("Videojuegos");
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

        JTextField buscador = new JTextField();
        buscador.setBounds(80, 15, 470, 30);
        buscador.setBackground(Color.WHITE);
        buscador.setOpaque(true);

        buscador.setBorder(
                BorderFactory.createCompoundBorder(
                        new RoundedBorder(15),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                )
        );

        buscador.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                buscador.setBackground(new Color(235, 245, 255));
                buscador.setBorder(
                        BorderFactory.createCompoundBorder(
                                new LineBorder(new Color(0, 170, 255), 2, true),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        )
                );
            }

            @Override
            public void focusLost(FocusEvent e) {
                buscador.setBackground(Color.WHITE);
                buscador.setBorder(
                        BorderFactory.createCompoundBorder(
                                new RoundedBorder(15),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        )
                );
            }
        });

        searchPanel.add(buscador);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(540, 15, 110, 30);
        searchPanel.add(btnBuscar);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(660, 15, 100, 30);
        searchPanel.add(btnFiltrar);
        
        ImageIcon img1 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/EGS_Octopus_InsomniacGamesNixxesSoftware_S1_2560x1440-f27da78f484626718d1e22e7d6950ca5.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        
        ImageIcon img2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/The_Legend_of_Zelda_Tears_of_the_Kingdom_cover.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        ImageIcon img3 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/Tapa-GTA-V.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        ImageIcon img4 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/forza_horizon_6-6006996.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        ImageIcon img5 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/resident.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        // TABLA 

        String[] columnas = {
                "", "Carátula", "Nombre", "ID", "Tipo", "Plataforma", "Info"
        };

        Object[][] datos = {
                {false, img1, "Spider-Man 2", "JUE-00064", "Videojuego", "PS5", "Ver info"},
                {false, img2, "Zelda", "JUE-00024", "Videojuego", "Switch", "Ver info"},
                {false, img3, "GTA 5", "JUE-00034", "Videojuego", "PS5", "Ver info"},
                {false, img4, "Forza", "JUE-00084", "Videojuego", "Xbox", "Ver info"},
                {false, img5, "Resident Evil", "JUE-00087", "Videojuego", "PS5", "Ver info"}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {

            @Override
            public Class<?> getColumnClass(int column) {

                if (column == 0) {
                    return Boolean.class;
                }

                if (column == 1) {
                    return Icon.class;
                }

                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {

                return column == 0;
            }
        };

        JTable tabla = new JTable(modelo);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        // TABLA CUADRADA 

        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(true);
        tabla.setGridColor(new Color(180, 180, 180));
        tabla.setIntercellSpacing(new Dimension(0, 0));

        tabla.setRowHeight(70);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.WHITE);
        header.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)
        );

        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        mainPanel.add(scroll);

        JButton btnEliminar = new JButton("Eliminar videojuego");
        btnEliminar.setBounds(320, 540, 200, 35);
        btnEliminar.setBackground(new Color(255, 87, 34));
        btnEliminar.setForeground(Color.WHITE);
        mainPanel.add(btnEliminar);

        // BUSQUEDA 

        btnBuscar.addActionListener(e -> {
            String texto = buscador.getText().trim();

            if (texto.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        });
        // ELIMINAR FILAS
        btnEliminar.addActionListener(e -> {

            JPanel panel = new JPanel();
            panel.setBackground(new Color(220, 220, 220));
            panel.setLayout(new BorderLayout());

            JLabel mensaje = new JLabel("¿Está seguro de borrar?", SwingConstants.CENTER);
            mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
            mensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            panel.add(mensaje, BorderLayout.CENTER);

            UIManager.put("OptionPane.background", new Color(220, 220, 220));
            UIManager.put("Panel.background", new Color(220, 220, 220));

            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if(opcion == JOptionPane.YES_OPTION) {

                boolean eliminado = false;

                for (int i = tabla.getRowCount() - 1; i >= 0; i--) {

                    Boolean seleccionado = (Boolean) tabla.getValueAt(i, 0);

                    if (seleccionado != null && seleccionado) {

                        modelo.removeRow(tabla.convertRowIndexToModel(i));
                        eliminado = true;
                    }
                }

                if(eliminado) {

                    JPanel panelExito = new JPanel();
                    panelExito.setBackground(new Color(220, 220, 220));
                    panelExito.setLayout(new BorderLayout());

                    JLabel mensajeExito = new JLabel(
                            "Los elementos se han eliminado con éxito",
                            SwingConstants.CENTER
                    );

                    mensajeExito.setFont(new Font("Arial", Font.PLAIN, 16));
                    mensajeExito.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                    panelExito.add(mensajeExito, BorderLayout.CENTER);

                    JOptionPane.showMessageDialog(
                            null,
                            panelExito,
                            "Éxito",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        });
        setVisible(true);
    }

    // MENU

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

                    case "Inicio":
                        ventana = new principal();
                        break;

                    case "Videojuegos":
                        ventana = new videojuegos();
                        break;

                    case "Clientes":
                        ventana = new clientes();
                        break;

                    case "Operación":
                        ventana = new operaciones();
                        break;

                    case "Peliculas":
                        ventana = new peliculas();
                        break;
                }

                if (ventana != null) {
                    ventana.setVisible(true);
                    dispose();
                }
            }
        });

        panel.add(iconLabel);
        panel.add(label);
    }

    // BORDE

    class RoundedBorder implements Border {

        int r;

        RoundedBorder(int r) {
            this.r = r;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(r, r, r, r);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.drawRoundRect(x, y, w - 1, h - 1, r, r);
        }
    }

    public static void main(String[] args) {
        new videojuegos();
    }
}
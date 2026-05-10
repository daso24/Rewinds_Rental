package view;
import javax.swing.*; 
import javax.swing.border.*;
import javax.swing.table.*;

import view.AñadirPelicula;
import view.principal;

import java.awt.*;
import java.awt.event.*;

public class peliculas extends JFrame {

    public peliculas() {

        setTitle("Peliculas");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

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

        JButton btnAgregar = new JButton("+ Añadir una película");
        btnAgregar.setBounds(590, 20, 220, 35);
        btnAgregar.setBackground(new Color(0, 170, 255));
        btnAgregar.setForeground(Color.WHITE);
        mainPanel.add(btnAgregar);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new AñadirPelicula().setVisible(true);
                dispose();
            }
        });

        JLabel titulo = new JLabel("Peliculas");
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
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
                new ImageIcon(getClass().getResource("/img/811xdZfsUqL.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        
        ImageIcon img2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/71w58zkWnfL.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        ImageIcon img3 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/61MZdcVwQFL.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        ImageIcon img4 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/20504502.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );

        ImageIcon img5 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/71MZBMmOXtL._AC_UF894,1000_QL80_.jpg"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        
        ImageIcon img6 = new ImageIcon(
                new ImageIcon(getClass().getResource("/img/fluent_movies-and-tv-16-filled.png"))
                        .getImage()
                        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );

        // TABLA 

        String[] columnas = {
                "", "Carátula", "Nombre", "ID", "Tipo", "Plataforma", "Info"
        };

        Object[][] datos = {
                {false, img1, "Avengers Infinity War", "PEL-00017", img6, "Blue-Ray", "Ver info"},
                {false, img2, "Chainsaw Man - La película: Arco de Reze", "PEL-10024", img6, "Blue-Ray", "Ver info"},
                {false, img3, "Rocky", "PEL-03085", img6, "Blue-Ray", "Ver info"},
                {false, img4, "Sherk 2", "PEL-00547", img6, "Blue-Ray", "Ver info"},
                {false, img5, "Dragon Ball Super Broly", "PEL-00723", img6, "Blue-Ray", "Ver info"}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {

            @Override
            public Class<?> getColumnClass(int column) {

                if(column == 0) {
                    return Boolean.class;
                }

                if(column == 1 || column == 4 ) {
                    return Icon.class;
                }

                return super.getColumnClass(column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {

                return column == 0;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(70);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        // TABLA CUADRADA 

        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(true);
        tabla.setGridColor(new Color(180, 180, 180));
        tabla.setIntercellSpacing(new Dimension(0, 0));

        tabla.setRowHeight(70);
        tabla.setFont(new Font("Inter", Font.PLAIN, 14));

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Inter", Font.BOLD, 14));
        header.setBackground(Color.WHITE);
        header.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)
        );

        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 170, 790, 350);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        mainPanel.add(scroll);

        JButton btnEliminar = new JButton("Eliminar película");
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

            for (int i = tabla.getRowCount() - 1; i >= 0; i--) {

                Boolean seleccionado = (Boolean) tabla.getValueAt(i, 0);

                if (seleccionado != null && seleccionado) {
                    modelo.removeRow(tabla.convertRowIndexToModel(i));
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
        new peliculas();
    }
}
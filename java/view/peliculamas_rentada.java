package view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class peliculamas_rentada extends JFrame {

    public peliculamas_rentada() {

        setTitle("Información del producto");
        setSize(820, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);
        
        // BARRA AZUL
        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 120, 600);
        barraLat.setLayout(null);
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
        
        Menu(barraLat, "Inicio", 80, inicioIcono);
        Menu(barraLat, "Operación", 150, operacionesIcono);
        Menu(barraLat, "Clientes", 260, clientesIcono);
        Menu(barraLat, "Videojuegos", 370, videojuegosIcono);
        Menu(barraLat, "Peliculas", 480, peliculasIcono);


        // PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setBounds(150, 20, 620, 540);
        panel.setLayout(null);

        // TITULO
        JLabel titulo = new JLabel("Información del producto");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(220, 15, 300, 30);

        // BOTON ATRAS
        JButton atras = new JButton("←  Atrás");
        atras.setBounds(20, 15, 115, 28);

        atras.addActionListener(e -> {

            dispose();

            SwingUtilities.invokeLater(() -> new principal());

        });

        // LABELS IZQUIERDA
        JLabel nombre = new JLabel("Nombre del producto:");
        nombre.setFont(new Font("Arial", Font.BOLD, 14));
        nombre.setBounds(30, 70, 180, 20);

        JTextField nombreTxt = new JTextField("Chainsaw-man la pelicula:arco de Reze");
        nombreTxt.setBounds(30, 95, 180, 28);

        JLabel id = new JLabel("ID del producto:");
        id.setFont(new Font("Arial", Font.BOLD, 14));
        id.setBounds(30, 135, 180, 20);

        JTextField idTxt = new JTextField("PEL_10024");
        idTxt.setBounds(30, 160, 180, 28);

        JLabel tipo = new JLabel("Tipo de producto:");
        tipo.setFont(new Font("Arial", Font.BOLD, 14));
        tipo.setBounds(30, 200, 180, 20);

        JTextField tipoTxt = new JTextField("Pelicula");
        tipoTxt.setBounds(30, 225, 180, 28);

        JLabel plataforma = new JLabel("Plataforma:");
        plataforma.setFont(new Font("Arial", Font.BOLD, 14));
        plataforma.setBounds(30, 265, 180, 20);

        JTextField plataformaTxt = new JTextField("Blue-ray");
        plataformaTxt.setBounds(30, 290, 180, 28);

        JLabel venta = new JLabel("Precio de venta:");
        venta.setFont(new Font("Arial", Font.BOLD, 14));
        venta.setBounds(30, 330, 180, 20);

        JTextField ventaTxt = new JTextField("$ 250.00");
        ventaTxt.setBounds(30, 355, 180, 28);

        JLabel renta = new JLabel("Precio de renta:");
        renta.setFont(new Font("Arial", Font.BOLD, 14));
        renta.setBounds(250, 330, 180, 20);

        JTextField rentaTxt = new JTextField("$ 100.00");
        rentaTxt.setBounds(250, 355, 180, 28);

        JLabel descuento = new JLabel("Descuento:");
        descuento.setFont(new Font("Arial", Font.BOLD, 14));
        descuento.setBounds(30, 395, 180, 20);

        JTextField descuentoTxt = new JTextField("0%");
        descuentoTxt.setBounds(30, 420, 180, 28);

        // STOCK
        JLabel stock = new JLabel("Stock");
        stock.setFont(new Font("Arial", Font.BOLD, 14));
        stock.setBounds(250, 395, 180, 20);

        JTextField ventaStock = new JTextField("Venta: 50");
        ventaStock.setBounds(250, 420, 80, 28);

        JTextField rentaStock = new JTextField("Renta: 20");
        rentaStock.setBounds(350, 420, 80, 28);

        // IMAGEN PRODUCTO
        JLabel producto = new JLabel("Producto:");
        producto.setFont(new Font("Arial", Font.BOLD, 14));
        producto.setBounds(430, 70, 100, 20);

        ImageIcon portada = new ImageIcon(
                peliculamas_rentada.class.getResource("/img/71w58zkWnfL.jpg")
        );

        Image img = portada.getImage().getScaledInstance(
                150,
                210,
                Image.SCALE_SMOOTH
        );

        JLabel imagen = new JLabel(new ImageIcon(img));
        imagen.setBounds(420, 95, 150, 210);

        // INFORMACION DERECHA
        JLabel clasificacion = new JLabel("Clasificación:");
        clasificacion.setFont(new Font("Arial", Font.BOLD, 14));
        clasificacion.setBounds(430, 320, 120, 20);

        JTextField clasificacionTxt = new JTextField("B-15");
        clasificacionTxt.setHorizontalAlignment(SwingConstants.CENTER);
        clasificacionTxt.setBounds(430, 345, 140, 28);

        JLabel año = new JLabel("Lanzamiento:");
        año.setFont(new Font("Arial", Font.BOLD, 14));
        año.setBounds(430, 385, 140, 20);

        JTextField añoTxt = new JTextField("2025");
        añoTxt.setHorizontalAlignment(SwingConstants.CENTER);
        añoTxt.setBounds(430, 410, 140, 28);

        JLabel genero = new JLabel("Género:");
        genero.setFont(new Font("Arial", Font.BOLD, 14));
        genero.setBounds(430, 450, 120, 20);

        JTextField generoTxt = new JTextField("Acción");
        generoTxt.setHorizontalAlignment(SwingConstants.CENTER);
        generoTxt.setBounds(430, 475, 140, 28);

        // BOTONES ABAJO
        JButton editar = new JButton("Editar Pelicula");
        editar.setBackground(new Color(52, 73, 94));
        editar.setForeground(Color.WHITE);
        editar.setBounds(30, 485, 180, 35);

        JButton descargar = new JButton("Descargar PDF");
        descargar.setBackground(new Color(0, 170, 255));
        descargar.setForeground(Color.WHITE);
        descargar.setBounds(240, 485, 180, 35);

        // AGREGAR COMPONENTES
        add(barraLat);
        add(panel);

        panel.add(titulo);
        panel.add(atras);

        panel.add(nombre);
        panel.add(nombreTxt);

        panel.add(id);
        panel.add(idTxt);

        panel.add(tipo);
        panel.add(tipoTxt);

        panel.add(plataforma);
        panel.add(plataformaTxt);

        panel.add(venta);
        panel.add(ventaTxt);

        panel.add(renta);
        panel.add(rentaTxt);

        panel.add(descuento);
        panel.add(descuentoTxt);

        panel.add(stock);
        panel.add(ventaStock);
        panel.add(rentaStock);

        panel.add(producto);
        panel.add(imagen);

        panel.add(clasificacion);
        panel.add(clasificacionTxt);

        panel.add(año);
        panel.add(añoTxt);

        panel.add(genero);
        panel.add(generoTxt);

        panel.add(editar);
        panel.add(descargar);

        setLocationRelativeTo(null);
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

    private JLabel Menu(String texto, int y) {

        JLabel label = new JLabel(texto);

        label.setForeground(Color.CYAN);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBounds(25, y, 150, 30);

        return label;

    }

}
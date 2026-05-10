package view;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class reze extends JFrame {

    public reze() {

        setTitle("Información del producto");
        setSize(920, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        //icono esquina de ventana
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

       
        // BARRA LATERAL
      

        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 140, 680);
        barraLat.setLayout(null);

        // LOGO
        JPanel logo = new JPanel();
        logo.setBackground(new Color(0, 170, 255));
        logo.setBounds(40, 20, 60, 60);
        logo.setBorder(new LineBorder(Color.WHITE, 2, true));
        barraLat.add(logo);

        JLabel inicio = Menu("Inicio", 95);
        JLabel operacion = Menu("Operación", 210);
        JLabel clientes = Menu("Clientes", 330);
        JLabel videojuegos = Menu("Videojuegos", 450);
        JLabel peliculas = Menu("Películas", 570);

        barraLat.add(inicio);
        barraLat.add(operacion);
        barraLat.add(clientes);
        barraLat.add(videojuegos);
        barraLat.add(peliculas);

        
        // PANEL PRINCIPAL
     

        JPanel panel = new JPanel();
        panel.setBackground(new Color(220, 220, 220));
        panel.setBounds(160, 60, 730, 540);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.GRAY));

     
        // TITULO
        

        JLabel titulo = new JLabel("Información del producto");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(420, 15, 320, 30);

        
        // BOTON ATRAS
      

        JButton atras = new JButton("← Atrás");
        atras.setBounds(160, 15, 120, 35);
        atras.setFocusPainted(false);

        atras.addActionListener(e -> {

            dispose();

            // new principal();
        });

       
        // CAMPOS IZQUIERDA
       

        JLabel nombre = texto("Nombre del producto:", 25, 25);

        JTextField nombreTxt = campo(
                "Chainsaw Man - La película: Arco de Reze",
                25, 50, 220, 32
        );

        JLabel id = texto("ID del producto:", 25, 95);

        JTextField idTxt = campo(
                "PEL-10024",
                25, 120, 220, 32
        );

        JLabel tipo = texto("Tipo de producto:", 25, 165);

        JTextField tipoTxt = campo(
                "Película",
                25, 190, 220, 32
        );

        JLabel plataforma = texto("Plataforma:", 25, 235);

        JTextField plataformaTxt = campo(
                "Blu-Ray",
                25, 260, 220, 32
        );

        JLabel venta = texto("Precio de venta:", 25, 305);

        JTextField ventaTxt = campo(
                "$ 250.00",
                25, 330, 220, 32
        );

        JLabel renta = texto("Precio de renta:", 25, 375);

        JTextField rentaTxt = campo(
                "$ 100.00",
                25, 400, 220, 32
        );

        JLabel descuento = texto("Descuento:", 25, 445);

        JTextField descuentoTxt = campo(
                "0%",
                25, 470, 220, 32
        );

      
        // STOCK
   

        JLabel stock = texto("Stock", 285, 445);

        JTextField ventaStock = campo(
                "Venta: 50",
                285, 470, 90, 32
        );

        JTextField rentaStock = campo(
                "Renta: 20",
                390, 470, 90, 32
        );

       
        // PRODUCTO
        

        JLabel producto = texto("Producto:", 520, 25);

        ImageIcon portada = new ImageIcon(
                peliculamas_rentada.class.getResource("/img/71w58zkWnfL.jpg")
        );

        Image img = portada.getImage().getScaledInstance(
                180,
                250,
                Image.SCALE_SMOOTH
        );

        JLabel imagen = new JLabel(new ImageIcon(img));
        imagen.setBounds(500, 55, 180, 250);
        imagen.setBorder(new LineBorder(Color.BLACK));

      

        JLabel clasificacion = texto("Clasificación:", 500, 325);

        JTextField clasificacionTxt = campo(
                "B-15",
                500, 350, 180, 32
        );

        JLabel anio = texto("Lanzamiento:", 500, 395);

        JTextField anioTxt = campo(
                "2025",
                500, 420, 180, 32
        );

        JLabel genero = texto("Género:", 500, 465);

        JTextField generoTxt = campo(
                "Acción",
                500, 490, 180, 32
        );


        // BOTONES
     

        JButton editar = boton(
                "Editar Película",
                new Color(40, 40, 40)
        );

        editar.setBounds(250, 615, 170, 35);

        JButton descargar = boton(
                "Descargar PDF",
                new Color(0, 170, 255)
        );

        descargar.setBounds(450, 615, 170, 35);

       
   

        add(barraLat);

        add(titulo);
        add(atras);

        add(panel);

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

        panel.add(anio);
        panel.add(anioTxt);

        panel.add(genero);
        panel.add(generoTxt);

        add(editar);
        add(descargar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

  
    // TEXTOS
   

    private JLabel texto(String texto, int x, int y) {

        JLabel label = new JLabel(texto);

        label.setFont(new Font("Arial", Font.BOLD, 15));

        label.setBounds(x, y, 220, 20);

        return label;
    }

    
    // CAMPOS
   

    private JTextField campo(
            String texto,
            int x,
            int y,
            int w,
            int h
    ) {

        JTextField txt = new JTextField(texto);

        txt.setBounds(x, y, w, h);

        txt.setHorizontalAlignment(JTextField.CENTER);

        txt.setBorder(new LineBorder(Color.GRAY, 1, true));

        return txt;
    }


    // BOTONES


    private JButton boton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);

        boton.setForeground(Color.WHITE);

        boton.setFocusPainted(false);

        boton.setBorder(
                new LineBorder(color.darker(), 1, true)
        );

        return boton;
    }

 
    // MENU
 

    private JLabel Menu(String texto, int y) {

        JLabel label = new JLabel(texto);

        label.setForeground(new Color(0, 200, 255));

        label.setFont(new Font("Arial", Font.PLAIN, 20));

        label.setBounds(20, y, 150, 30);

        return label;
    }

    public static void main(String[] args) {

        new peliculamas_rentada();
    }
}
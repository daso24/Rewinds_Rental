package view;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class juegomas_rentado extends JFrame {
    
    public JButton btnAtras, btnEditar, btnDescargar;
    public JLabel btnInicio, btnOperacion, btnClientes, btnVideojuegos, btnPeliculas;

    public juegomas_rentado() {
        setTitle("Información del videojuego");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        Image icono = new ImageIcon(getClass().getResource("/img/logo3.png")).getImage();
        this.setIconImage(icono);

        JPanel barraLat = new JPanel();
        barraLat.setBackground(new Color(0, 51, 102));
        barraLat.setBounds(0, 0, 180, 650);
        barraLat.setLayout(null);

        btnInicio = crearItemMenu(barraLat, "Inicio", 100, "/img/gravity-ui_house-fill.png");
        btnOperacion = crearItemMenu(barraLat, "Operación", 180, "/img/ic_baseline-plus.png");
        btnClientes = crearItemMenu(barraLat, "Clientes", 290, "/img/material-symbols_person.png");
        btnVideojuegos = crearItemMenu(barraLat, "Videojuegos", 400, "/img/carbon_game-console.png");
        btnPeliculas = crearItemMenu(barraLat, "Peliculas", 510, "/img/fluent_movies-and-tv-16-filled.png");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setBounds(220, 40, 830, 530);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.GRAY));

        JLabel titulo = new JLabel("Información del videojuego");
        titulo.setFont(new Font("Inter", Font.BOLD, 22));
        titulo.setBounds(300, 20, 350, 30);

        btnAtras = new JButton("←  Atrás");
        btnAtras.setFont(new Font("Inter", Font.PLAIN, 13));
        btnAtras.setBounds(30, 20, 120, 30);
        btnAtras.setFocusPainted(false);

        JLabel nombre = new JLabel("Nombre del videojuego:");
        nombre.setFont(new Font("Inter", Font.BOLD, 15));
        nombre.setBounds(50, 80, 200, 20);

        JTextField nombreTxt = new JTextField("Resident evil requiem");
        nombreTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        nombreTxt.setBounds(50, 105, 250, 30);
        nombreTxt.setEditable(false);

        JLabel id = new JLabel("ID del producto:");
        id.setFont(new Font("Inter", Font.BOLD, 15));
        id.setBounds(50, 155, 200, 20);

        JTextField idTxt = new JTextField("VID-00892");
        idTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        idTxt.setBounds(50, 180, 250, 30);
        idTxt.setEditable(false);

        JLabel tipo = new JLabel("Tipo de producto:");
        tipo.setFont(new Font("Inter", Font.BOLD, 15));
        tipo.setBounds(50, 230, 200, 20);

        JTextField tipoTxt = new JTextField("Videojuego");
        tipoTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        tipoTxt.setBounds(50, 255, 250, 30);
        tipoTxt.setEditable(false);

        JLabel plataforma = new JLabel("Plataforma:");
        plataforma.setFont(new Font("Inter", Font.BOLD, 15));
        plataforma.setBounds(50, 305, 200, 20);

        JTextField plataformaTxt = new JTextField("PlayStation 4 / 5");
        plataformaTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        plataformaTxt.setBounds(50, 330, 250, 30);
        plataformaTxt.setEditable(false);

        JLabel venta = new JLabel("Precio Venta:");
        venta.setFont(new Font("Inter", Font.BOLD, 15));
        venta.setBounds(50, 385, 120, 20);

        JTextField ventaTxt = new JTextField("$ 1,100.00");
        ventaTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        ventaTxt.setBounds(50, 410, 110, 30);
        ventaTxt.setEditable(false);

        JLabel renta = new JLabel("Precio Renta:");
        renta.setFont(new Font("Inter", Font.BOLD, 15));
        renta.setBounds(190, 385, 120, 20);

        JTextField rentaTxt = new JTextField("$ 150.00");
        rentaTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        rentaTxt.setBounds(190, 410, 110, 30);
        rentaTxt.setEditable(false);

        JLabel stock = new JLabel("Stock Disponible (V/R):");
        stock.setFont(new Font("Inter", Font.BOLD, 15));
        stock.setBounds(340, 385, 200, 20);

        JTextField ventaStock = new JTextField("V: 5");
        ventaStock.setFont(new Font("Inter", Font.PLAIN, 14));
        ventaStock.setBounds(340, 410, 80, 30);
        ventaStock.setEditable(false);

        JTextField rentaStock = new JTextField("R: 3");
        rentaStock.setFont(new Font("Inter", Font.PLAIN, 14));
        rentaStock.setBounds(430, 410, 80, 30);
        rentaStock.setEditable(false);

        JLabel producto = new JLabel("Vista Previa:");
        producto.setFont(new Font("Inter", Font.BOLD, 15));
        producto.setBounds(550, 80, 100, 20);

        JLabel imagen = new JLabel();
        try {
            ImageIcon portada = new ImageIcon(getClass().getResource("/img/resident.jpg"));
            Image imgScale = portada.getImage().getScaledInstance(200, 280, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(imgScale));
        } catch(Exception e) {}
        imagen.setBounds(530, 105, 200, 280);

        JLabel clasificacion = new JLabel("Clasificación:");
        clasificacion.setFont(new Font("Inter", Font.BOLD, 14));
        clasificacion.setBounds(350, 80, 120, 20);

        JTextField clasificacionTxt = new JTextField("M (Mature)");
        clasificacionTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        clasificacionTxt.setHorizontalAlignment(SwingConstants.CENTER);
        clasificacionTxt.setBounds(350, 105, 130, 30);
        clasificacionTxt.setEditable(false);

        JLabel año = new JLabel("Lanzamiento:");
        año.setFont(new Font("Inter", Font.BOLD, 14));
        año.setBounds(350, 155, 120, 20);

        JTextField añoTxt = new JTextField("2020");
        añoTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        añoTxt.setHorizontalAlignment(SwingConstants.CENTER);
        añoTxt.setBounds(350, 180, 130, 30);
        añoTxt.setEditable(false);

        JLabel genero = new JLabel("Género:");
        genero.setFont(new Font("Inter", Font.BOLD, 14));
        genero.setBounds(350, 230, 120, 20);

        JTextField generoTxt = new JTextField("Aventura / Acción");
        generoTxt.setFont(new Font("Inter", Font.PLAIN, 14));
        generoTxt.setHorizontalAlignment(SwingConstants.CENTER);
        generoTxt.setBounds(350, 255, 130, 30);
        generoTxt.setEditable(false);

        btnEditar = new JButton("Editar videojuego");
        btnEditar.setFont(new Font("Inter", Font.BOLD, 14));
        btnEditar.setBackground(new Color(52, 73, 94));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBounds(50, 470, 200, 40);
        btnEditar.setFocusPainted(false);

        btnDescargar = new JButton("Descargar Reporte PDF");
        btnDescargar.setFont(new Font("Inter", Font.BOLD, 14));
        btnDescargar.setBackground(new Color(0, 170, 255));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setBounds(280, 470, 200, 40);
        btnDescargar.setFocusPainted(false);

        add(barraLat);
        add(panel);

        panel.add(titulo); panel.add(btnAtras);
        panel.add(nombre); panel.add(nombreTxt);
        panel.add(id); panel.add(idTxt);
        panel.add(tipo); panel.add(tipoTxt);
        panel.add(plataforma); panel.add(plataformaTxt);
        panel.add(venta); panel.add(ventaTxt);
        panel.add(renta); panel.add(rentaTxt);
        panel.add(stock); panel.add(ventaStock); panel.add(rentaStock);
        panel.add(producto); panel.add(imagen);
        panel.add(clasificacion); panel.add(clasificacionTxt);
        panel.add(año); panel.add(añoTxt);
        panel.add(genero); panel.add(generoTxt);
        panel.add(btnEditar); panel.add(btnDescargar);

        setLocationRelativeTo(null);
    }

    public void mostrarConfirmacion(String mensaje, ActionListener accionSi) {
        JDialog dialogo = new JDialog(this, "Confirmación", true);
        dialogo.setUndecorated(true);
        dialogo.setSize(380, 300);
        dialogo.setLocationRelativeTo(this);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(225, 225, 225));

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setOpaque(false);
        
        cuerpo.add(Box.createVerticalStrut(40));
        JLabel texto = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        texto.setFont(new Font("Inter", Font.BOLD, 16));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        cuerpo.add(texto);
        
        cuerpo.add(Box.createVerticalGlue());
        
        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 20));
        panelBtns.setOpaque(false);

        JButton btnSi = new JButton("Confirmar");
        btnSi.setFont(new Font("Inter", Font.BOLD, 14));
        btnSi.setPreferredSize(new Dimension(120, 38));
        btnSi.setBackground(new Color(0, 51, 102));
        btnSi.setForeground(Color.WHITE);
        btnSi.addActionListener(e -> { dialogo.dispose(); accionSi.actionPerformed(e); });

        JButton btnNo = new JButton("Cancelar");
        btnNo.setFont(new Font("Inter", Font.BOLD, 14));
        btnNo.setPreferredSize(new Dimension(120, 38));
        btnNo.setBackground(new Color(130, 130, 130));
        btnNo.setForeground(Color.WHITE);
        btnNo.addActionListener(e -> dialogo.dispose());

        panelBtns.add(btnSi); panelBtns.add(btnNo);
        contenedor.add(cuerpo, BorderLayout.CENTER);
        contenedor.add(panelBtns, BorderLayout.SOUTH);
        
        dialogo.add(contenedor);
        dialogo.setVisible(true);
    }

    private JLabel crearItemMenu(JPanel barra, String texto, int y, String iconPath) {
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(iconPath))
                    .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            JLabel lblIcon = new JLabel(icon);
            lblIcon.setBounds(25, y, 25, 30);
            barra.add(lblIcon);
        } catch(Exception e) {}

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 16));
        label.setBounds(65, y, 110, 30);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        barra.add(label);
        return label;
    }
}
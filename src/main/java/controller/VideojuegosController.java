package controller;

import models.VideojuegoModel;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VideojuegosController
{
    private videojuegos vista;
    private AgregarJuego vistaAdd;
    private VideojuegoModel modelo;

    public VideojuegosController(videojuegos vista)
    {
        this.vista = vista;
        this.modelo = new VideojuegoModel();
        initEvents();
        cargarTabla();
    }

    public VideojuegosController(AgregarJuego vistaAdd)
    {
        this.vistaAdd = vistaAdd;
        this.modelo = new VideojuegoModel();
        initEventsAdd();
    }

    public void cargarTabla()
    {
        if (vista != null)
        {
            List<Object[]> lista = modelo.obtenerVideojuegos();
            vista.cargarDatosTabla(lista);
        }
    }

    private void initEvents()
    {
        vista.btnAtras.addActionListener(e ->
        {
            vista.dispose();
            principal vPrin = new principal();
            new PrincipalController(vPrin);
            vPrin.setVisible(true);
        });

        configurarMenuLateral(vista.btnInicio, vista.btnOperacion, vista.btnClientes, vista.btnVideojuegos, vista.btnPeliculas, vista);

        vista.btnBuscar.addActionListener(e ->
        {
            String texto = vista.buscador.getText().trim();
            if (texto.isEmpty())
            {
                vista.sorter.setRowFilter(null);
            }
            else
            {
                vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        });

        vista.btnFiltrar.addActionListener(e ->
        {
            abrirDialogoFiltrar();
        });

        vista.btnEliminar.addActionListener(e ->
        {
            boolean haySeleccion = false;
            for (int i = 0; i < vista.tabla.getRowCount(); i++)
            {
                Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                if (isSelected != null && isSelected)
                {
                    haySeleccion = true;
                    break;
                }
            }

            if (haySeleccion)
            {
                vista.mostrarConfirmacionEliminar("¿Está seguro de borrar los<br>juegos seleccionados?", eSi ->
                {
                    for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--)
                    {
                        Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                        if (isSelected != null && isSelected)
                        {
                            int idVideojuego = (int) vista.tabla.getValueAt(i, 3);
                            modelo.eliminarVideojuego(idVideojuego);
                        }
                    }
                    cargarTabla();
                });
            }
            else
            {
                mostrarAvisoGris("Selecciona al menos un juego de la lista.");
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int filaVisual = vista.tabla.rowAtPoint(e.getPoint());
                int columnaVisual = vista.tabla.columnAtPoint(e.getPoint());

                if (filaVisual != -1 && vista.tabla.getValueAt(filaVisual, columnaVisual) != null)
                {
                    int columnaModelo = vista.tabla.convertColumnIndexToModel(columnaVisual);
                    Object valor = vista.tabla.getValueAt(filaVisual, columnaVisual);
                    String valorCelda = (valor instanceof Object[]) ? ((Object[]) valor)[1].toString() : valor.toString();

                    if (columnaModelo == 7 && valorCelda.equalsIgnoreCase("Ver info"))
                    {
                        int filaModelo = vista.tabla.convertRowIndexToModel(filaVisual);
                        int idVideojuego = (int) vista.modelo.getValueAt(filaModelo, 3);
                        Object[] datos = modelo.obtenerVideojuegoPorId(idVideojuego);

                        if (datos != null)
                        {
                            vista.dispose();
                            InfoJuego vInfo = new InfoJuego();
                            vInfo.cargarDatos(datos);
                           
                            new InfoJuegoInternalController(vInfo, idVideojuego, (String)datos[10]);
                            vInfo.setVisible(true);
                        }
                    }
                }
            }
        });

        vista.btnAgregar.addActionListener(e ->
        {
            vista.dispose();
            AgregarJuego vAdd = new AgregarJuego();
            new VideojuegosController(vAdd);
            vAdd.setVisible(true);
        });
    }

    private void initEventsAdd()
    {
        configurarMenuLateral(vistaAdd.btnInicio, vistaAdd.btnOperacion, vistaAdd.btnClientes, vistaAdd.btnVideojuegos, vistaAdd.btnPeliculas, vistaAdd);

        vistaAdd.btnAtras.addActionListener(e ->
        {
            vistaAdd.dispose();
            videojuegos vVid = new videojuegos();
            new VideojuegosController(vVid);
            vVid.setVisible(true);
        });

        vistaAdd.btnAgregar.addActionListener(e -> validarYMostrarPopUp());
    }

    private void mostrarAvisoGris(String mensaje)
    {
        JDialog dialogo = new JDialog(vista, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(350, 205);
        dialogo.setLocationRelativeTo(vista);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        contenedor.setBackground(new Color(209, 209, 209));
        dialogo.setContentPane(contenedor);

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(Box.createVerticalStrut(5));

        JLabel lblMsg = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Inter", Font.BOLD, 15));
        lblMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblMsg);
        panelContenido.add(Box.createVerticalGlue());

        try
        {
            JLabel iconoCentro = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/img/mingcute_warning-fill.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
            iconoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(iconoCentro);
        }
        catch (Exception e)
        {
        }
        panelContenido.add(Box.createVerticalGlue());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setOpaque(false);

        JButton btnOk = new JButton("OK")
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 51, 102));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnOk.setPreferredSize(new Dimension(110, 35));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Inter", Font.BOLD, 13));
        btnOk.setContentAreaFilled(false);
        btnOk.setBorderPainted(false);
        btnOk.setFocusPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialogo.dispose());

        panelBotones.add(btnOk);
        contenedor.add(panelContenido, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void abrirDialogoFiltrar()
    {
        JDialog dialogo = new JDialog(vista, "Filtro Videojuegos", true);
        dialogo.setSize(320, 200);
        dialogo.setLocationRelativeTo(vista);
        dialogo.setLayout(new BorderLayout(10, 10));

        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblColumna = new JLabel("Filtrar por columna:");
        String[] opcionesColumnas = {"Título", "Id", "Plataforma"};
        JComboBox<String> comboColumnas = new JComboBox<>(opcionesColumnas);

        JLabel lblCriterio = new JLabel("Texto a buscar:");
        JTextField txtCriterio = new JTextField();

        panelContenido.add(lblColumna);
        panelContenido.add(comboColumnas);
        panelContenido.add(lblCriterio);
        panelContenido.add(txtCriterio);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAplicar = new JButton("Aplicar");
        JButton btnLimpiar = new JButton("Quitar Filtros");

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnAplicar);

        btnAplicar.addActionListener(evt ->
        {
            String criterio = txtCriterio.getText().trim();
            int columnaSeleccionada = comboColumnas.getSelectedIndex();
            int indexColumnaTabla = 2;
            if (columnaSeleccionada == 1) indexColumnaTabla = 3;
            if (columnaSeleccionada == 2) indexColumnaTabla = 5;

            if (criterio.isEmpty())
            {
                vista.sorter.setRowFilter(null);
            }
            else
            {
                try
                {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + criterio, indexColumnaTabla));
                }
                catch (Exception ex)
                {
                    mostrarAvisoGris("Error en la expresión de filtrado.");
                }
            }
            dialogo.dispose();
        });

        btnLimpiar.addActionListener(evt ->
        {
            vista.sorter.setRowFilter(null);
            vista.buscador.setText("");
            dialogo.dispose();
        });

        dialogo.add(panelContenido, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void configurarMenuLateral(JComponent inicio, JComponent operacion, JComponent clientes, JComponent videojuegosLbl, JComponent peliculas, JFrame ventanaActual)
    {
        if (inicio != null)
        {
            inicio.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    principal v = new principal();
                    new PrincipalController(v);
                    v.setVisible(true);
                }
            });
        }
        if (operacion != null)
        {
            operacion.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    operaciones v = new operaciones();
                    new OperacionesController(v);
                    v.setVisible(true);
                }
            });
        }
        if (clientes != null)
        {
            clientes.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    clientes v = new clientes();
                    new ClienteController(v);
                    v.setVisible(true);
                }
            });
        }
        if (videojuegosLbl != null)
        {
            videojuegosLbl.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    if (!(ventanaActual instanceof videojuegos))
                    {
                        ventanaActual.dispose();
                        videojuegos v = new videojuegos();
                        new VideojuegosController(v);
                        v.setVisible(true);
                    }
                }
            });
        }
        if (peliculas != null)
        {
            peliculas.addMouseListener(new MouseAdapter()
            {
                @Override public void mouseClicked(MouseEvent e)
                {
                    ventanaActual.dispose();
                    peliculas v = new peliculas();
                    new PeliculasController(v);
                    v.setVisible(true);
                }
            });
        }
    }

    private void validarYMostrarPopUp()
    {
        if (vistaAdd == null || vistaAdd.txtNombre == null || vistaAdd.cbPlataforma == null) return;

        if (vistaAdd.txtNombre.getText().isEmpty() || vistaAdd.cbPlataforma.getSelectedIndex() == 0)
        {
            vistaAdd.mostrarError("Error: Debes ingresar el nombre<br>y seleccionar una plataforma.");
            return;
        }

        try
        {
            String titulo = vistaAdd.txtNombre.getText().trim();
            String plataforma = vistaAdd.cbPlataforma.getSelectedItem().toString();
            double pVenta = Double.parseDouble(vistaAdd.txtVenta.getText().replace("$", "").trim());
            double pRenta = Double.parseDouble(vistaAdd.txtRenta.getText().replace("$", "").trim());
            int desc = 0;
            if (!vistaAdd.txtDescuento.getText().isEmpty())
            {
                desc = Integer.parseInt(vistaAdd.txtDescuento.getText().replace("%", "").trim());
            }
            int sVenta = Integer.parseInt(vistaAdd.cbStockVenta.getSelectedItem().toString());
            int sRenta = Integer.parseInt(vistaAdd.cbStockRenta.getSelectedItem().toString());
            String clasif = vistaAdd.cbClasif.getSelectedItem().toString();
            int anio = Integer.parseInt(vistaAdd.cbAnio.getSelectedItem().toString());
            String foto = "";
            try { foto = vistaAdd.rutaFotoActual; } catch (Exception ignored) {}

            if (modelo.registrarVideojuego(titulo, plataforma, pVenta, pRenta, desc, sVenta, sRenta, clasif, anio, foto))
            {
                vistaAdd.mostrarExito("¡Videojuego registrado con éxito!");
                vistaAdd.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
            }
            else
            {
                vistaAdd.mostrarError("Error al registrar en la base de datos.");
            }
        }
        catch (NumberFormatException ex)
        {
            vistaAdd.mostrarError("Error: Los precios y stocks deben<br>ser valores numéricos válidos.");
        }
    }

    private class InfoJuegoInternalController
    {
        private InfoJuego v;
        private int idJuego;
        private String rutaFoto;

        public InfoJuegoInternalController(InfoJuego v, int idJuego, String rutaFoto)
        {
            this.v = v;
            this.idJuego = idJuego;
            this.rutaFoto = rutaFoto;

            configurarMenuLateral(v.btnInicio, v.btnOperacion, v.btnClientes, v.btnVideojuegos, v.btnPeliculas, v);

            v.btnAtras.addActionListener(e ->
            {
                v.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
            });

            v.btnEditar.addActionListener(e ->
            {
                if (!v.enModoEdicion)
                {
                    v.txtNombreProd.setEditable(true);
                    v.txtPlataforma.setEditable(true);
                    v.txtPrecioVenta.setEditable(true);
                    v.txtPrecioRenta.setEditable(true);
                    v.txtDescuento.setEditable(true);
                    v.txtStock.setEditable(true);
                    v.txtStockRenta.setEditable(true);
                    v.txtClasificacion.setEditable(true);
                    v.txtAnio.setEditable(true);
                    v.btnEditar.setText("Guardar cambios");
                    v.btnEditar.setBackground(new Color(50, 180, 50));
                    v.setModoEdicionActivo(true);
                }
                else
                {
                    try
                    {
                        String titulo = v.txtNombreProd.getText().trim();
                        String plataforma = v.txtPlataforma.getText().trim();
                        double pVenta = Double.parseDouble(v.txtPrecioVenta.getText().replace("$", "").trim());
                        double pRenta = Double.parseDouble(v.txtPrecioRenta.getText().replace("$", "").trim());
                        int desc = Integer.parseInt(v.txtDescuento.getText().replace("%", "").trim());
                        int sVenta = Integer.parseInt(v.txtStock.getText().trim());
                        int sRenta = Integer.parseInt(v.txtStockRenta.getText().trim());
                        String clasif = v.txtClasificacion.getText().trim();
                        int anio = Integer.parseInt(v.txtAnio.getText().trim());
                        String rutaFinal = v.rutaFotoNueva.isEmpty() ? this.rutaFoto : v.rutaFotoNueva;

                        if (modelo.actualizarVideojuego(idJuego, titulo, plataforma, pVenta, pRenta, desc, sVenta, sRenta, clasif, anio, rutaFinal))
                        {
                            v.mostrarConfirmacion("¡Datos actualizados correctamente!", e2 ->
                            {
                                v.dispose();
                                videojuegos vVid = new videojuegos();
                                new VideojuegosController(vVid);
                                vVid.setVisible(true);
                            });
                        }
                    }
                    catch (Exception ex)
                    {
                        v.mostrarConfirmacion("Error en parseo numérico.", e2 -> {});
                    }
                }
            });

            v.btnDescargar.addActionListener(e ->
            {
                String id = String.valueOf(this.idJuego);
                String titulo = v.txtNombreProd.getText();
                String plataforma = v.txtPlataforma.getText();
                String precio = v.txtPrecioVenta.getText();
                utils.PDFGenerator.generarFichaJuego(id, titulo, plataforma, precio);
            });
        }
    }
}
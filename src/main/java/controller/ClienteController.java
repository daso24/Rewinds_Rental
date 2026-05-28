package controller;

import models.ClientModel;
import models.OperacionModel;
import models.ArbolBinarioBusqueda;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private AñadirClientes vistaAgregar;
    private clientes vistaTabla;
    private ClientModel modelo;
    private ArbolBinarioBusqueda arbolClientes;

    public ClienteController(AñadirClientes vistaAgregar, ClientModel modelo) {
        this.vistaAgregar = vistaAgregar;
        this.modelo = modelo;
        initEventsAdd(); 
    }

    public ClienteController(clientes vistaTabla) {
        this.vistaTabla = vistaTabla;
        this.modelo = new ClientModel();
        this.arbolClientes = new ArbolBinarioBusqueda();
        
        cargarDatosEnArbol();
        initEvents();
    }

    private void cargarDatosEnArbol() {
        List<Object[]> listaClientes = modelo.obtenerClientes();
        this.arbolClientes = new ArbolBinarioBusqueda();
        for (Object[] cliente : listaClientes) {
            if (cliente.length > 1 && cliente[1] != null) {
                arbolClientes.insertar(cliente[1].toString(), cliente);
            }
        }
    }

    private void initEvents() {
        configurarMenuLateral(vistaTabla.btnInicio, vistaTabla.btnOperacion, vistaTabla.btnClientes, vistaTabla.btnVideojuegos, vistaTabla.btnPeliculas, vistaTabla);

        if (this.vistaTabla.btnAgregar != null) {
            this.vistaTabla.btnAgregar.addActionListener(e -> {
                vistaTabla.dispose();
                AñadirClientes addVista = new AñadirClientes();
                new ClienteController(addVista, new ClientModel());
                addVista.setVisible(true);
            });
        }
        
        if (this.vistaTabla.btnBuscar != null) {
            this.vistaTabla.btnBuscar.addActionListener(e -> {
                String texto = vistaTabla.buscador.getText().trim();
                if (texto.isEmpty()) {
                    vistaTabla.cargarTabla();
                } else {
                    List<Object[]> clientesEncontrados = arbolClientes.buscarParcial(texto);
                    vistaTabla.modelo.setRowCount(0); 
                    
                    if (!clientesEncontrados.isEmpty()) {
                        Object infoIcon = null;
                        try { infoIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/Vector.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)); } catch (Exception ex) {}

                        for (Object[] clienteEncontrado : clientesEncontrados) {
                            Object userIcon = null;
                            String rutaDB = (String) clienteEncontrado[2];
                            try {
                                ImageIcon icon;
                                if (rutaDB != null && rutaDB.startsWith("/")) icon = new ImageIcon(getClass().getResource(rutaDB));
                                else icon = new ImageIcon(rutaDB != null ? rutaDB : "/img/placeholder_usuario.png");
                                Image img = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                                userIcon = new ImageIcon(img);
                            } catch (Exception ex) {
                                try { userIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/placeholder_usuario.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)); } catch(Exception ignored){}
                            }
                            
                            Object[] filaEstetica = {
                                false,
                                new Object[]{userIcon, clienteEncontrado[1]},
                                String.valueOf(clienteEncontrado[0]),
                                "0",
                                "N/A",
                                "N/A",
                                new Object[]{infoIcon, "Ver info"}
                            };
                            vistaTabla.modelo.addRow(filaEstetica);
                        }
                    } else {
                        JOptionPane.showMessageDialog(vistaTabla, "No se encontró ningún cliente con ese nombre.");
                    }
                }
            });
        }
        
        if (this.vistaTabla.btnFiltrar != null) {
            this.vistaTabla.btnFiltrar.addActionListener(e -> abrirDialogoFiltrar());
        }

        if (this.vistaTabla.btnEliminar != null) {
            this.vistaTabla.btnEliminar.addActionListener(e -> {
                List<Integer> idsAEliminar = new ArrayList<>();
                List<String> nombresAEliminar = new ArrayList<>();

                for (int i = 0; i < vistaTabla.tabla.getRowCount(); i++) {
                    Boolean isChecked = (Boolean) vistaTabla.tabla.getValueAt(i, 0);
                    if (isChecked != null && isChecked) {
                        idsAEliminar.add(Integer.parseInt(vistaTabla.tabla.getValueAt(i, 2).toString()));
                        Object cellVal = vistaTabla.tabla.getValueAt(i, 1);
                        String nombre = (cellVal instanceof Object[]) ? ((Object[]) cellVal)[1].toString() : cellVal.toString();
                        nombresAEliminar.add(nombre);
                    }
                }
                
                if (!idsAEliminar.isEmpty()) {
                    vistaTabla.mostrarAdvertenciaEliminar(evt -> {
                        for (int i = 0; i < idsAEliminar.size(); i++) {
                            modelo.eliminarCliente(idsAEliminar.get(i));
                            arbolClientes.eliminar(nombresAEliminar.get(i));
                        }
                        vistaTabla.mostrarExitoEliminar();
                        vistaTabla.cargarTabla();
                        cargarDatosEnArbol();
                    });
                }
            });
        }
        
        if (this.vistaTabla.btnAtras != null) {
            this.vistaTabla.btnAtras.addActionListener(e -> {
                vistaTabla.dispose();
                view.principal vistaHome = new view.principal();
                new PrincipalController(vistaHome);
                vistaHome.setVisible(true);
            });
        }
        
        if (this.vistaTabla.tabla != null) {
            this.vistaTabla.tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = vistaTabla.tabla.columnAtPoint(e.getPoint());
                    int row = vistaTabla.tabla.rowAtPoint(e.getPoint());
                    if (col == 6) {
                        int id = Integer.parseInt(vistaTabla.tabla.getValueAt(row, 2).toString());
                        Object[] info = modelo.obtenerDetalleCliente(id);
                        if (info != null) {
                            InfoCliente vistaInfo = new InfoCliente();
                            String nombres = (String) info[0];
                            String apellidos = (String) info[1];
                            String tel = (String) info[2];
                            String foto = (String) info[3];
                            String fechaNac = (String) info[4];
                            
                            vistaInfo.setDatosCliente(nombres != null ? nombres : "", apellidos != null ? apellidos : "", String.valueOf(id), tel != null ? tel : "N/A", fechaNac != null ? fechaNac : "N/A");
                            vistaInfo.setImagenCliente(foto != null && !foto.isEmpty() ? foto : "/img/placeholder_usuario.png");
                            vistaInfo.btnAtras.addActionListener(eAtras -> {
                                vistaInfo.dispose();
                                clientes vistaCli = new clientes();
                                new ClienteController(vistaCli);
                                vistaCli.setVisible(true);
                            });
                            
                            vistaTabla.dispose();
                            
                            vistaInfo.btnHistoVentas.addActionListener(eHistoVentas -> {
                                try {
                                    view.HistorialVentas vistaVentas = new view.HistorialVentas();
                                    OperacionModel opModel = new OperacionModel();
                                    List<Object[]> datosVentas = opModel.obtenerHistorialVentas(id);
                                    for (Object[] fila : datosVentas) {
                                        vistaVentas.modelo.addRow(fila);
                                    }
                                    vistaVentas.btnAtras.addActionListener(eBack -> vistaVentas.dispose());
                                    vistaVentas.setVisible(true);
                                } catch (Exception ex) {}
                            });
                            
                            vistaInfo.btnHistoRentas.addActionListener(eHistoRentas -> {
                                try {
                                    view.HistorialRentas vistaRentas = new view.HistorialRentas();
                                    OperacionModel opModel = new OperacionModel();
                                    List<Object[]> datosRentas = opModel.obtenerHistorialRentas(id);
                                    for (Object[] fila : datosRentas) {
                                        vistaRentas.modelo.addRow(fila);
                                    }
                                    vistaRentas.btnAtras.addActionListener(eBack -> vistaRentas.dispose());
                                    vistaRentas.setVisible(true);
                                } catch (Exception ex) {}
                            });
                            
                            vistaInfo.btnEditar.addActionListener(eEditar -> {
                                if (!vistaInfo.modoEdicion) {
                                    vistaInfo.toggleEdicion();
                                } else {
                                    String nNombres = vistaInfo.txtNombres.getText().trim();
                                    String nApellidos = vistaInfo.txtApellidos.getText().trim();
                                    String nTel = vistaInfo.txtTelefono.getText().trim();
                                    String nFecha = vistaInfo.txtFecha.getText().trim();
                                    String nFoto = vistaInfo.rutaFotoActual;
                                    if (modelo.actualizarCliente(id, nNombres, nApellidos, nTel, nFecha, nFoto)) {
                                        vistaInfo.mostrarExito("Cliente actualizado correctamente.");
                                        vistaInfo.toggleEdicion();
                                        vistaTabla.cargarTabla();
                                        cargarDatosEnArbol();
                                    } else {
                                        vistaInfo.mostrarError("Error al actualizar el cliente.");
                                    }
                                }
                            });
                            
                            vistaInfo.btnDescargar.addActionListener(eDescargar -> {
                                utils.PDFGenerator.generarFicha(vistaInfo.txtId.getText(), vistaInfo.txtNombres.getText() + " " + vistaInfo.txtApellidos.getText(), "N/A", "N/A", vistaInfo.txtFecha.getText());
                            });
                            
                            vistaInfo.btnGenerar.addActionListener(eGenerar -> {
                                utils.PDFGenerator.generarFichaCliente(vistaInfo.txtId.getText(), vistaInfo.txtNombres.getText(), vistaInfo.txtApellidos.getText(), vistaInfo.txtTelefono.getText(), vistaInfo.txtFecha.getText());
                            });
                            vistaInfo.setVisible(true);
                        }
                    }
                }
            });
        }
    }

    private void initEventsAdd() {
    	configurarMenuLateral(vistaAgregar.lblInicio, vistaAgregar.lblOperacion, vistaAgregar.lblClientes, vistaAgregar.lblVideojuegos, vistaAgregar.lblPeliculas, vistaAgregar);
        
        if (vistaAgregar.btnAtras != null) {
            vistaAgregar.btnAtras.addActionListener(e -> {
                vistaAgregar.dispose();
                clientes vistaCli = new clientes();
                new ClienteController(vistaCli);
                vistaCli.setVisible(true);
            });
        }

        if (vistaAgregar.btnAgregarCliente != null) {
            vistaAgregar.btnAgregarCliente.addActionListener(e -> {
                String nombres = vistaAgregar.txtNombres.getText().trim();
                String apellidos = vistaAgregar.txtApellidos.getText().trim();
                String telefono = vistaAgregar.txtTelefono.getText().trim();
                String correo = vistaAgregar.txtCorreo.getText().trim();
                String ruta = vistaAgregar.rutaFoto;
                String fechaNac = vistaAgregar.txtFechaNac.getText().trim();
                
                System.out.println("Intentando guardar a: " + nombres); 

                if (modelo.registrarCliente(nombres, apellidos, telefono, correo, ruta, fechaNac)) {
                    JOptionPane.showMessageDialog(vistaAgregar, "¡Cliente guardado con éxito!");
                    vistaAgregar.dispose();
                    clientes vistaCli = new clientes();
                    new ClienteController(vistaCli);
                    vistaCli.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vistaAgregar, "Error al guardar. Verifica que llenaste todos los campos obligatorios y que el correo/teléfono no estén repetidos en la BD.", "Error de BD", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else {
            System.out.println("ADVERTENCIA: El botón btnAgregarCliente no está conectado correctamente.");
        }
    }

    private void configurarMenuLateral(JComponent inicio, JComponent operacion, JComponent clientesBtn, JComponent videojuegosLbl, JComponent peliculasLbl, JFrame ventanaActual) {
        if (inicio != null) { inicio.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); principal vPrin = new principal(); new PrincipalController(vPrin); vPrin.setVisible(true); } }); }
        if (operacion != null) { operacion.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); operaciones vOp = new operaciones(); new OperacionesController(vOp); vOp.setVisible(true); } }); }
        if (clientesBtn != null) { clientesBtn.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { if (!(ventanaActual instanceof clientes)) { ventanaActual.dispose(); clientes vCli = new clientes(); new ClienteController(vCli); vCli.setVisible(true); } } }); }
        if (videojuegosLbl != null) { videojuegosLbl.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); videojuegos vVid = new videojuegos(); new VideojuegosController(vVid); vVid.setVisible(true); } }); }
        if (peliculasLbl != null) { peliculasLbl.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { ventanaActual.dispose(); peliculas vPel = new peliculas(); new PeliculasController(vPel); vPel.setVisible(true); } }); }
    }
    
    private void abrirDialogoFiltrar() {
        JDialog dialogo = new JDialog(vistaTabla, "Filtro Clientes", true);
        dialogo.setSize(320, 200);
        dialogo.setLocationRelativeTo(vistaTabla);
        dialogo.setLayout(new BorderLayout(10, 10));

        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblColumna = new JLabel("Filtrar por columna:");
        String[] opcionesColumnas = {"Nombre", "ID"};
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

        btnAplicar.addActionListener(evt -> {
            String criterio = txtCriterio.getText().trim().toLowerCase();
            if (criterio.isEmpty()) {
                vistaTabla.cargarTabla();
            } else {
                int opcion = comboColumnas.getSelectedIndex();
                vistaTabla.modelo.setRowCount(0); 
                
                List<Object[]> clientesFiltrados = new java.util.ArrayList<>();

                if (opcion == 0) { 
                    List<Object[]> encontrados = arbolClientes.buscarParcial(criterio);
                    for (Object[] c : encontrados) {
                        if (String.valueOf(c[1]).toLowerCase().contains(criterio)) {
                            clientesFiltrados.add(c);
                        }
                    }
                } else { 
                    List<Object[]> todos = modelo.obtenerClientes();
                    for (Object[] c : todos) {
                        if (String.valueOf(c[0]).toLowerCase().equals(criterio)) {
                            clientesFiltrados.add(c);
                        }
                    }
                }

                Object infoIcon = null;
                try { infoIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/Vector.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)); } catch (Exception ex) {}

                for (Object[] clienteEncontrado : clientesFiltrados) {
                    Object userIcon = null;
                    String rutaDB = (String) clienteEncontrado[2];
                    try {
                        ImageIcon icon;
                        if (rutaDB != null && rutaDB.startsWith("/")) icon = new ImageIcon(getClass().getResource(rutaDB));
                        else icon = new ImageIcon(rutaDB != null ? rutaDB : "/img/placeholder_usuario.png");
                        userIcon = new ImageIcon(icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
                    } catch (Exception ex) {
                        try { userIcon = new ImageIcon(new ImageIcon(getClass().getResource("/img/placeholder_usuario.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)); } catch(Exception ignored){}
                    }
                    vistaTabla.modelo.addRow(new Object[]{ false, new Object[]{userIcon, clienteEncontrado[1]}, String.valueOf(clienteEncontrado[0]), "0", "N/A", "N/A", new Object[]{infoIcon, "Ver info"} });
                }
            }
            dialogo.dispose();
        });

        btnLimpiar.addActionListener(evt -> {
            vistaTabla.buscador.setText("");
            vistaTabla.cargarTabla();
            dialogo.dispose();
        });

        dialogo.add(panelContenido, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
}
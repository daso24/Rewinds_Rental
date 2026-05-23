package controller;

import models.ClientModel;
import view.AñadirClientes;
import view.clientes;
import view.InfoCliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ClienteController 
{
    private AñadirClientes vistaAgregar;
    private clientes vistaTabla;
    private ClientModel modelo;

    public ClienteController(AñadirClientes vistaAgregar, ClientModel modelo) 
    {
        this.vistaAgregar = vistaAgregar;
        this.modelo = modelo;

        this.vistaAgregar.btnAgregarCliente.addActionListener(e -> 
        {
            String nombres = vistaAgregar.txtNombres.getText().trim();
            String apellidos = vistaAgregar.txtApellidos.getText().trim();
            String telefono = vistaAgregar.txtTelefono.getText().trim();
            String correo = vistaAgregar.txtCorreo.getText().trim();
            String ruta = vistaAgregar.rutaFoto;
            String fechaNac = vistaAgregar.txtFechaNac.getText().trim();

            if (modelo.registrarCliente(nombres, apellidos, telefono, correo, ruta, fechaNac)) 
            {
                vistaAgregar.dispose();
                clientes vistaCli = new clientes();
                new ClienteController(vistaCli);
                vistaCli.setVisible(true);
            }
        });
    }

    public ClienteController(clientes vistaTabla) 
    {
        this.vistaTabla = vistaTabla;
        this.modelo = new ClientModel();

        if (this.vistaTabla.btnAgregar != null) 
        {
            this.vistaTabla.btnAgregar.addActionListener(e -> 
            {
                vistaTabla.dispose();
                AñadirClientes addVista = new AñadirClientes();
                new ClienteController(addVista, new ClientModel());
                addVista.setVisible(true);
            });
        }

        if (this.vistaTabla.btnEliminar != null) 
        {
            this.vistaTabla.btnEliminar.addActionListener(e -> 
            {
                List<Integer> idsAEliminar = new ArrayList<>();
                for (int i = 0; i < vistaTabla.tabla.getRowCount(); i++) 
                {
                    Boolean isChecked = (Boolean) vistaTabla.tabla.getValueAt(i, 0);
                    if (isChecked != null && isChecked) 
                    {
                        idsAEliminar.add(Integer.parseInt(vistaTabla.tabla.getValueAt(i, 2).toString()));
                    }
                }

                if (!idsAEliminar.isEmpty()) 
                {
                    vistaTabla.mostrarAdvertenciaEliminar(evt -> 
                    {
                        for (int id : idsAEliminar) 
                        {
                            modelo.eliminarCliente(id);
                        }
                        vistaTabla.mostrarExitoEliminar();
                        vistaTabla.cargarTabla();
                    });
                }
            });
        }

        if (this.vistaTabla.btnAtras != null) 
        {
            this.vistaTabla.btnAtras.addActionListener(e -> 
            {
                vistaTabla.dispose();
                view.principal vistaHome = new view.principal();
                new PrincipalController(vistaHome);
                vistaHome.setVisible(true);
            });
        }

        if (this.vistaTabla.tabla != null) 
        {
            this.vistaTabla.tabla.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    int col = vistaTabla.tabla.columnAtPoint(e.getPoint());
                    int row = vistaTabla.tabla.rowAtPoint(e.getPoint());
                    
                    if (col == 6) 
                    {
                        int id = Integer.parseInt(vistaTabla.tabla.getValueAt(row, 2).toString());
                        Object[] info = modelo.obtenerDetalleCliente(id);
                        
                        if (info != null) 
                        {
                            InfoCliente vistaInfo = new InfoCliente();
                            String nombres = (String) info[0];
                            String apellidos = (String) info[1];
                            String tel = (String) info[2];
                            String foto = (String) info[3];
                            String fechaNac = (String) info[4];

                            vistaInfo.setDatosCliente(nombres != null ? nombres : "", apellidos != null ? apellidos : "", String.valueOf(id), tel != null ? tel : "N/A", fechaNac != null ? fechaNac : "N/A");
                            vistaInfo.setImagenCliente(foto != null && !foto.isEmpty() ? foto : "/img/placeholder_usuario.png");

                            vistaInfo.btnAtras.addActionListener(eAtras -> vistaInfo.dispose());
                            
                            // Lógica de los nuevos botones de historial
                            vistaInfo.btnHistoVentas.addActionListener(eHistoVentas -> 
                            {
                                view.HistorialVentas vistaVentas = new view.HistorialVentas();
                                vistaVentas.btnAtras.addActionListener(eBack -> vistaVentas.dispose());
                                vistaVentas.setVisible(true);
                            });

                            vistaInfo.btnHistoRentas.addActionListener(eHistoRentas -> 
                            {
                                view.HistorialRentas vistaRentas = new view.HistorialRentas();
                                vistaRentas.btnAtras.addActionListener(eBack -> vistaRentas.dispose());
                                vistaRentas.setVisible(true);
                            });
                            
                            vistaInfo.btnEditar.addActionListener(eEditar -> 
                            {
                                if (!vistaInfo.modoEdicion)
                                {
                                    vistaInfo.toggleEdicion();
                                }
                                else
                                {
                                    String nNombres = vistaInfo.txtNombres.getText().trim();
                                    String nApellidos = vistaInfo.txtApellidos.getText().trim();
                                    String nTel = vistaInfo.txtTelefono.getText().trim();
                                    String nFecha = vistaInfo.txtFecha.getText().trim();
                                    String nFoto = vistaInfo.rutaFotoActual;

                                    if (modelo.actualizarCliente(id, nNombres, nApellidos, nTel, nFecha, nFoto))
                                    {
                                        vistaInfo.mostrarExito("Cliente actualizado correctamente.");
                                        vistaInfo.toggleEdicion();
                                        vistaTabla.cargarTabla();
                                    }
                                    else
                                    {
                                        vistaInfo.mostrarError("Error al actualizar el cliente.");
                                    }
                                }
                            });

                            vistaInfo.setVisible(true);
                        }
                    }
                }
            });
        }
    }
}
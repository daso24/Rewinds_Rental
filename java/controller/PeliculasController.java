package controller;

import view.*;
import javax.swing.*;
import java.awt.event.*;

public class PeliculasController {
    private peliculas vista;

    public PeliculasController(peliculas vista) {
        this.vista = vista;
        initEvents();
    }

    private void initEvents() {
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.rowAtPoint(e.getPoint());
                int columna = vista.tabla.columnAtPoint(e.getPoint());

                if (columna == 6 && fila != -1) {
                    abrirDetallesPelicula(fila);
                }
            }
        });

        vista.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                principal vPrin = new principal();
                new PrincipalController(vPrin); 
                vPrin.setVisible(true);
            }
        });

        vista.btnOperacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp); 
                vOp.setVisible(true);
            }
        });

        vista.btnClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli); 
                vCli.setVisible(true);
            }
        });

        vista.btnVideojuegos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
            }
        });

        vista.btnPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel); 
                vPel.setVisible(true);
            }
        });

        vista.btnBuscar.addActionListener(e -> {
            String texto = vista.buscador.getText().trim();
            if (texto.isEmpty()) {
                vista.sorter.setRowFilter(null);
            } else {
                try {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                } catch (java.util.regex.PatternSyntaxException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        vista.btnEliminar.addActionListener(e -> {
            vista.mostrarConfirmacionEliminar("¿Está seguro de borrar las<br>películas seleccionadas?", eSi -> {
                for (int i = vista.tabla.getRowCount() - 1; i >= 0; i--) {
                    Boolean isSelected = (Boolean) vista.tabla.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        int modelIndex = vista.tabla.convertRowIndexToModel(i);
                        vista.modelo.removeRow(modelIndex);
                    }
                }
            });
        });

        vista.btnAgregar.addActionListener(e -> {
            AñadirPelicula vAdd = new AñadirPelicula();
            new AñadirPeliculaController(vAdd); 
            vAdd.setVisible(true);
            vista.dispose();
        });
    }

    private void abrirDetallesPelicula(int fila) {
        Object objTitulo = vista.tabla.getValueAt(fila, 1);
        Object objFormato = vista.tabla.getValueAt(fila, 2);

        String titulo = (objTitulo != null) ? objTitulo.toString() : "";
        String formato = (objFormato != null) ? objFormato.toString() : "";

        InfoPelicula vInfo = new InfoPelicula();
        vInfo.txtNomProd.setText(titulo);
        vInfo.txtFormato.setText(formato);
        
        vInfo.btnAtras.addActionListener(e -> {
            vInfo.dispose();
            vista.setVisible(true);
        });

        vInfo.setVisible(true);
        vista.setVisible(false);
    }

    private class AñadirPeliculaController {
        private AñadirPelicula vAdd;

        public AñadirPeliculaController(AñadirPelicula vAdd) {
            this.vAdd = vAdd;
            initAddEvents();
        }

        private void initAddEvents() {
            vAdd.btnAtras.addActionListener(e -> regresarAPeliculas());
            vAdd.btnAgregar.addActionListener(e -> validarRegistro());

            vAdd.lblInicio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    principal p = new principal();
                    new PrincipalController(p);
                    p.setVisible(true);
                }
            });

            vAdd.lblVideojuegos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    videojuegos v = new videojuegos();
                    new VideojuegosController(v);
                    v.setVisible(true);
                }
            });
            
            vAdd.lblClientes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    vAdd.dispose();
                    clientes c = new clientes();
                    new ClienteController(c);
                    c.setVisible(true);
                }
            });
        }

        private void validarRegistro() {
            String nombre = vAdd.txtNombre.getText().trim();
            String id = vAdd.txtId.getText().trim();
            int indexPlat = vAdd.cbPlataforma.getSelectedIndex();

            if (nombre.isEmpty() || id.isEmpty() || indexPlat == 0) {
                vAdd.mostrarError("Error: Debes ingresar el nombre,<br>ID y seleccionar una plataforma.");
                return;
            }

            try {
                Double.parseDouble(vAdd.txtVenta.getText().replace("$", "").trim());
                Double.parseDouble(vAdd.txtRenta.getText().replace("$", "").trim());
                vAdd.mostrarExito("¡Película registrada con éxito!");
                regresarAPeliculas();
            } catch (NumberFormatException ex) {
                vAdd.mostrarError("Error: Los precios deben ser<br>valores numéricos.");
            }
        }

        private void regresarAPeliculas() {
            vAdd.dispose();
            peliculas p = new peliculas();
            new PeliculasController(p);
            p.setVisible(true);
        }
    }
}
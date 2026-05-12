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

        // Botón Inicio
        vista.btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                vista.dispose();
                principal vPrin = new principal();
                new PrincipalController(vPrin); 
                vPrin.setVisible(true);
            }
        });

        // Botón Operación
        vista.btnOperacion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                operaciones vOp = new operaciones();
                new OperacionesController(vOp); 
                vOp.setVisible(true);
            }
        });

        // Botón Clientes
        vista.btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                clientes vCli = new clientes();
                new ClienteController(vCli); 
                vCli.setVisible(true);
            }
        });

        // Botón Videojuegos
        vista.btnVideojuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                videojuegos vVid = new videojuegos();
                new VideojuegosController(vVid);
                vVid.setVisible(true);
            }
        });

        // Botón Películas 
        vista.btnPeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                peliculas vPel = new peliculas();
                new PeliculasController(vPel); 
                vPel.setVisible(true);
            }
        });

        // Lógica de Búsqueda
        vista.btnBuscar.addActionListener(e -> {
            String texto = vista.buscador.getText().trim();
            
            if (texto.isEmpty()) {
              vista.sorter.setRowFilter(null);
            } else {
                try {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                } catch (java.util.regex.PatternSyntaxException ex) {
                    System.err.println("Error en la búsqueda: " + ex.getMessage());
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
                System.out.println("Películas eliminadas con éxito.");
            });
        });
    }
}
package controller;

import models.AuthModel;
import view.login;
import view.principal; 
import view.registro;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class AuthController {
    
    private login vista;
    private AuthModel modelo;

    public AuthController(login vista, AuthModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.loginButton.addActionListener(e -> {
            String usuario = vista.userField.getText();
            String password = new String(vista.passField.getPassword());
            boolean hayError = false;

            if (usuario.isEmpty()) {
                vista.userField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.userField.setBorder(UIManager.getBorder("TextField.border"));
            }

            if (password.isEmpty()) {
                vista.passField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.passField.setBorder(UIManager.getBorder("TextField.border"));
            }

            if (hayError) {
                JOptionPane.showMessageDialog(vista, "Llene los campos marcados en rojo", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            if (modelo.validarUsuario(usuario, password)) {
                JOptionPane.showMessageDialog(vista, "Ha iniciado sesión con éxito", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
                
                vista.dispose(); 
                
                principal ventanaMenu = new principal();
                ventanaMenu.setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(vista, "Datos inválidos", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.vista.registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose(); 
                registro vistaReg = new registro(); 
                new RegistroController(vistaReg);  
                vistaReg.setVisible(true);          
            }
        });
    }
}
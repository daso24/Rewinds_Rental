package controller;

import models.AuthModel;
import view.login;
import view.principal; 
import view.registro;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                vista.mostrarMensajeError("Llene los campos marcados en rojo.");
                return; 
            }
            if (modelo.validarUsuario(usuario, password)) {
            
                vista.mostrarLoginExitoso("Ha iniciado sesión con éxito.");
                
             
                vista.dispose(); 
                new view.principal().setVisible(true); 
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
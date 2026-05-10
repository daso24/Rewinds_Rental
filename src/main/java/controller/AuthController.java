package controller;

import models.AuthModel;
import view.login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthController {
    
    private login vista;
    private AuthModel modelo;

    public AuthController(login vista, AuthModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // boton iniciar sesion
        this.vista.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modelo.validarUsuario(vista.getUsuario(), vista.getPassword())) {
                    vista.dispose(); 
                    System.out.println("Navegando a Ventana Principal...");
                    view.principal.main(null); 
                } else {
                    vista.popup.setVisible(true);
                }
            }
        });

        // enlace registro
        this.vista.registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                view.registro.main(null);
            }
        });
    }
}
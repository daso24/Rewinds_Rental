package controller;

import models.AuthModel;
import view.login;
import view.principal;
import view.registro;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class AuthController
{
    private login vista;
    private AuthModel modelo;

    public AuthController(login vista, AuthModel modelo)
    {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.loginButton.addActionListener(e -> {
            String correo = vista.userField.getText().trim();
            String pass = new String(vista.passField.getPassword());
            boolean hayError = false;

            if (correo.isEmpty()) {
                vista.userField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.userField.setBorder(UIManager.getBorder("TextField.border"));
            }

            if (pass.isEmpty()) {
                vista.passField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.passField.setBorder(UIManager.getBorder("TextField.border"));
            }

            if (hayError) {
                vista.mostrarMensajeError("Llene los campos marcados en rojo.");
                return;
            }

            String nombreUsuario = modelo.validarLogin(correo, pass);

            if (nombreUsuario != null) {
                vista.mostrarLoginExitoso("Ha iniciado sesión con éxito.");
                vista.dispose();

                principal vistaMenu = new principal();
                vistaMenu.setNombreUsuario(nombreUsuario); 
                new PrincipalController(vistaMenu);
                vistaMenu.setVisible(true);
            } else {
                vista.mostrarMensajeError("Usuario o contraseña incorrectos.");
            }
        });

        this.vista.registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.dispose();
                registro vistaReg = new registro();
                models.ClientModel modeloReg = new models.ClientModel();
                new RegisterController(vistaReg, modeloReg);
                vistaReg.setVisible(true);
            }
        });
    }
}
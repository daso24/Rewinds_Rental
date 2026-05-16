package controller;

import view.registro;
import view.login;
import models.AuthModel;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class RegistroController {
    private registro vista;

    public RegistroController(registro vista) {
        this.vista = vista;

        this.vista.registerBtn.addActionListener(e -> {
            boolean hayError = false;

            // Validar si el usuario está vacío
            if (vista.userField.getText().isEmpty()) {
                vista.userField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.userField.setBorder(new LineBorder(Color.GRAY, 1));
            }

            // Validar contraseñas
            String pass = new String(vista.passField.getPassword());
            String confirm = new String(vista.confirmField.getPassword());

            if (hayError) {
                vista.mostrarMensajeError("Por favor, llene los campos en rojo.");
            } else if (!pass.equals(confirm)) {
                vista.mostrarMensajeError("Las contraseñas no coinciden.");
            } else {
              
                vista.mostrarMensajeExito("¡Usuario registrado con éxito!");
                
                // Redirigir al login
                vista.dispose();
                login vLogin = new login();
                AuthModel mLogin = new AuthModel();
                new AuthController(vLogin, mLogin);
                vLogin.setVisible(true);
            }
        });

        // Acción botón volver al login
        this.vista.backBtn.addActionListener(e -> {
            vista.dispose();
            login vLogin = new login();
            AuthModel mLogin = new AuthModel();
            new AuthController(vLogin, mLogin);
            vLogin.setVisible(true);
        });
    }
}
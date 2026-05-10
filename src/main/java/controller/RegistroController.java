package controller;

import view.registro;
import view.login;
import models.AuthModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class RegistroController {
    private registro vista;

    public RegistroController(registro vista) {
        this.vista = vista;

        // boton atras
        this.vista.backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); 
                
                login vLogin = new login();
                AuthModel mLogin = new AuthModel();
                new AuthController(vLogin, mLogin); 
                vLogin.setVisible(true);
            }
        });

        // boton registrar
        this.vista.registerBtn.addActionListener(e -> {
            boolean hayError = false;

            if (vista.userField.getText().isEmpty()) {
                vista.userField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.userField.setBorder(new LineBorder(Color.GRAY, 1));
            }

            if (vista.phoneField.getText().isEmpty()) {
                vista.phoneField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.phoneField.setBorder(new LineBorder(Color.GRAY, 1));
            }

            if (vista.emailField.getText().isEmpty()) {
                vista.emailField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.emailField.setBorder(new LineBorder(Color.GRAY, 1));
            }

            String pass = new String(vista.passField.getPassword());
            String confirm = new String(vista.confirmField.getPassword());

            if (pass.isEmpty()) {
                vista.passField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.passField.setBorder(new LineBorder(Color.GRAY, 1));
            }

            if (confirm.isEmpty()) {
                vista.confirmField.setBorder(new LineBorder(Color.RED, 2));
                hayError = true;
            } else {
                vista.confirmField.setBorder(new LineBorder(Color.GRAY, 1));
            }

            if (hayError) {
                JOptionPane.showMessageDialog(vista, "Por favor, llene los campos en rojo", "Campos obligatorios", JOptionPane.WARNING_MESSAGE);
            } else if (!pass.equals(confirm)) {
                vista.passField.setBorder(new LineBorder(Color.RED, 2));
                vista.confirmField.setBorder(new LineBorder(Color.RED, 2));
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "Usuario registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
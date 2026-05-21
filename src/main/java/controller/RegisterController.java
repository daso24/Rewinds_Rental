package controller;

import models.ClientModel;
import view.registro;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class RegisterController
{
    private registro vista;
    private ClientModel modelo;

    public RegisterController(registro vista, ClientModel modelo)
    {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.registerBtn.addActionListener(e -> registrarUsuario());
        
        this.vista.backBtn.addActionListener(e -> {
            vista.dispose(); 
        });
    }

    private void registrarUsuario()
    {
        String usuario = vista.userField.getText().trim();
        String correo = vista.emailField.getText().trim();
        String pass = new String(vista.passField.getPassword());
        String confirmPass = new String(vista.confirmField.getPassword());
        String telefono = vista.phoneField.getText().trim();
        
        String genero = "";
        if (vista.masculino.isSelected()) genero = "Masculino";
        else if (vista.femenino.isSelected()) genero = "Femenino";
        else if (vista.otro.isSelected()) genero = "Otro";
        
        // Variable para acumular mensajes de error específicos
        String mensajeError = "";
        boolean camposVacios = false;

        // 1. Revisar campos vacíos
        if (usuario.isEmpty()) { vista.userField.setBorder(new LineBorder(Color.RED, 2)); camposVacios = true; }
        else { vista.userField.setBorder(UIManager.getBorder("TextField.border")); }

        if (correo.isEmpty()) { vista.emailField.setBorder(new LineBorder(Color.RED, 2)); camposVacios = true; }
        else { vista.emailField.setBorder(UIManager.getBorder("TextField.border")); }

        if (pass.isEmpty()) { vista.passField.setBorder(new LineBorder(Color.RED, 2)); camposVacios = true; }
        else { vista.passField.setBorder(UIManager.getBorder("TextField.border")); }
        
        if (confirmPass.isEmpty()) { vista.confirmField.setBorder(new LineBorder(Color.RED, 2)); camposVacios = true; }
        else { vista.confirmField.setBorder(UIManager.getBorder("TextField.border")); }

        if (telefono.isEmpty()) { vista.phoneField.setBorder(new LineBorder(Color.RED, 2)); camposVacios = true; }
        else { vista.phoneField.setBorder(UIManager.getBorder("TextField.border")); }

        if (camposVacios)
        {
            mensajeError += "Llene los campos marcados en rojo.<br>";
        }

        // 2. Revisar formato de correo (si no está vacío)
        if (!correo.isEmpty() && !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
        {
            vista.emailField.setBorder(new LineBorder(Color.RED, 2));
            mensajeError += "El formato del correo no es válido.<br>";
        }

        // 3. Revisar selección de género
        if (genero.isEmpty())
        {
            vista.masculino.setForeground(Color.RED);
            vista.femenino.setForeground(Color.RED);
            vista.otro.setForeground(Color.RED);
            mensajeError += "Seleccione un género.<br>";
        }
        else
        {
            vista.masculino.setForeground(Color.BLACK);
            vista.femenino.setForeground(Color.BLACK);
            vista.otro.setForeground(Color.BLACK);
        }

        // 4. Revisar que las contraseñas coincidan 
        if (!pass.isEmpty() && !confirmPass.isEmpty() && !pass.equals(confirmPass))
        {
            vista.passField.setBorder(new LineBorder(Color.RED, 2));
            vista.confirmField.setBorder(new LineBorder(Color.RED, 2));
            mensajeError += "Las contraseñas no coinciden.<br>";
        }

        if (!mensajeError.isEmpty())
        {
            vista.mostrarMensajeError(mensajeError);
            return;
        }

        // Envío de datos al modelo
        if (modelo.registrarCliente(correo, pass, usuario, telefono, genero))
        {
        	vista.mostrarMensajeExito("Usuario registrado con éxito.");
            
            vista.dispose();
            
            view.login vistaLogin = new view.login();
            models.AuthModel modeloAuth = new models.AuthModel();
            new AuthController(vistaLogin, modeloAuth);
            vistaLogin.setVisible(true);
        }
        else
        {
            vista.mostrarMensajeError("Error en el registro. El correo ya existe.");
        }
    }
}
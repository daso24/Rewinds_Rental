package Rewinds_Rental;

import view.login;
import models.AuthModel;
import controller.AuthController;

public class Main {
    public static void main(String[] args) {
        login vista = new login();
        AuthModel modelo = new AuthModel();
        
        new AuthController(vista, modelo);        
        vista.setVisible(true);
    }
}
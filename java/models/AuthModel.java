package models;

public class AuthModel {
	public boolean validarUsuario(String usuario, String password) {
        return usuario.equals("admin") && password.equals("1234");
    }

}

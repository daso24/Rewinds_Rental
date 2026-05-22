package models;

public class Sesion
{
    private static String nombreUsuario = "Usuario";

    public static void setNombreUsuario(String nombre)
    {
        nombreUsuario = nombre;
    }

    public static String getNombreUsuario()
    {
        return nombreUsuario;
    }
}
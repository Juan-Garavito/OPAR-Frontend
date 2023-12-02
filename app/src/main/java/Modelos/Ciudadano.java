package Modelos;


import java.io.Serializable;

/**
 * Clase que representa a un ciudadano con su informacion basica.
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class Ciudadano implements Serializable
{
    private int IdUsuario;
    private String Nombre;
    private String Cedula ;
    String NumeroContacto;
    /**
     * Constructor para el objeto Ciudadano
     * @param IdUsuario Identificador del ciudadano
     * @param Nombre El nombre completo asociado al ciudadano
     * @param Cedula Numero de documento del ciudadano
     * @param Cedula Numero de celular del ciudadano
     */
    public Ciudadano(int IdUsuario, String Nombre, String Cedula, String NumeroContacto)
    {
        // initialise instance variables
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.Cedula = Cedula;
        this.NumeroContacto = NumeroContacto;
    }

    public int GetIdUsuario(){
        return this.IdUsuario;
    }
}


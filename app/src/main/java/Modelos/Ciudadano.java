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
    private String numeroDocumento;
    private String nombreCompleto;
    private String usuario;
    private String contraseña;
    private String telefono;


    public Ciudadano(String numeroDocumento, String nombreCompleto, String usuario, String contraseña, String telefono) {
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "numeroDocumento='" + numeroDocumento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}


package Modelos;

import java.io.Serializable;

public class Imagen  implements Serializable {
    private Integer idImagen;
    private Inmueble idInmueble;
    private String url;

    public Imagen(Inmueble idInmueble, String url) {
        this.idInmueble = idInmueble;
        this.url = url;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Inmueble getInmueble() {
        return idInmueble;
    }

    public void setInmueble(Inmueble idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

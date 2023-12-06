package Modelos;

public class Imagen {
    private Integer idImagen;
    private Inmueble inmueble;
    private String url;

    public Imagen(Inmueble inmueble, String url) {
        this.inmueble = inmueble;
        this.url = url;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

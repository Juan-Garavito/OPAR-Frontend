package Modelos;

public class Tipo_InmuebleDTO {

    private Integer idTipoInmueble;
    private String tipoInmueble;

    public Tipo_InmuebleDTO(Integer idTipoInmueble, String tipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
        this.tipoInmueble = tipoInmueble;
    }

    public Integer getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(Integer idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    @Override
    public String toString() {
        return "Tipo_InmuebleDTO{" +
                "idTipoInmueble=" + idTipoInmueble +
                ", TipoInmueble='" + tipoInmueble + '\'' +
                '}';
    }
}

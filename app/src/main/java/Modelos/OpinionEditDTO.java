package Modelos;

public class OpinionEditDTO {

    private String numeroDocumento;
    private Integer idInmueble;

    public OpinionEditDTO(String numeroDocumento, Integer idInmueble) {
        this.numeroDocumento = numeroDocumento;
        this.idInmueble = idInmueble;
    }


    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }



}

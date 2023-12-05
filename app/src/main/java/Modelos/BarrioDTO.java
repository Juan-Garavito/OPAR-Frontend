package Modelos;

public class BarrioDTO {

    private Integer idBarrio;

    private String barrio;

    public BarrioDTO(Integer idBarrio, String barrio) {
        this.idBarrio = idBarrio;
        this.barrio = barrio;
    }

    public Integer getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(Integer idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}

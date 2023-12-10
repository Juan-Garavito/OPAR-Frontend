package Modelos;

/**
 * Clase que representa la opinion de un usuario sobre un inmueble
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class Opinion
{

    private int idOpinion;
    private Integer idInmueble;
    private Ciudadano numeroDocumento;
    private String comentario;
    private float calificacion;

    public Opinion(Integer  idInmueble, Ciudadano numeroDocumento, String comentario, float calificacion) {
        this.idInmueble = idInmueble;
        this.numeroDocumento = numeroDocumento;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }


    public int getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(int idOpinion) {
        this.idOpinion = idOpinion;
    }

    public Integer getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Ciudadano getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Ciudadano numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "idOpinion=" + idOpinion +
                ", idInmueble=" + idInmueble +
                ", numeroDocumento=" + numeroDocumento +
                ", comentario='" + comentario + '\'' +
                ", calificacion=" + calificacion +
                '}';
    }
}


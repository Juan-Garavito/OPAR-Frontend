package Modelos;

/**
 * Clase que representa la opinion de un usuario sobre un inmueble
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class OpinionInmueble
{

    private int IdOpinion;
    private String IdInmueble;
    private int IdUsuario;
    private String Comentario;
    private float Calificacion;

    /**
     * Constructor para el objeto OpinionInmueble
     * @param IdOpinion El identificador de la opinion
     * @param IdInmueble El identificador de el inmueble
     * @param IdUsuario El identificador de el ciudadano
     * @param Comentario El comentario de la opinion
     * @param Calificacion La calificacion de la opinion
     */
    public OpinionInmueble(int IdOpinion, String IdInmueble, int IdUsuario,
                           String Comentario, float Calificacion)
    {
        this.IdOpinion = IdOpinion;
        this.IdInmueble = IdInmueble;
        this.IdUsuario = IdUsuario;
        this.Comentario = Comentario;
        this.Calificacion = Calificacion;
    }

    /**
     * Muestra todos los datos de la opinion
     */
    public void mostrarDatos()
    {
        System.out.println("Id Opinion:" + this.IdOpinion);
        System.out.println("Id Usuario:" + this.IdUsuario);
        System.out.println("Comentario:" + this.Comentario);
        System.out.println("Calificacion:" + this.Calificacion);
    }

    public int GetIdOpinion(){
        return this.IdOpinion;
    }

    public String GetComentario(){
        return this.Comentario;
    }
    public String GetIdInmueble(){
        return this.IdInmueble;
    }

    public float GetCalificacion(){
        return this.Calificacion;
    }

    public void SetCalificacion(float Calificacion){
        this.Calificacion = Calificacion;
    }

    public void SetComentario(String Comentario){
        this.Comentario = Comentario;
    }

    public int GetIdUsuario(){
        return this.IdUsuario;
    }
}


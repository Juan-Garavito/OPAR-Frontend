package Modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Clase que representa al rol de Arrendatario.
 * Esta clase hereda de la clase Ciudadano.
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class Arrendatario extends Ciudadano
{

    ArrayList<Opinion> MisOpiniones = new  ArrayList<>();
    Catalogo catalogo = new Catalogo();

    /**
     * Constructor para el objeto Arrendatario
     */
    public Arrendatario(String numeroDocumento, String nombreCompleto, String usuario, String contraseña, String telefono)
    {
        super(numeroDocumento, nombreCompleto,  usuario,  contraseña,  telefono);
    }

    /**
     * Agregar una opinion al Inmueble
     * @param IdInmuble Es el id del inmueble que va recibir la opinion
     * @param IdUsuario Es el id del usuario que da la opinion
     * @param Comentario Es el comentario de tu opinion
     * @param Calificacion Es la calificacion de tu opinion
     * @return Una nueva Opinion del Inmueble
     */
    /*public void agregarOpinion(int IdOpinion, Integer IdInmuble, int IdUsuario,
                               String Comentario, float Calificacion)
    {
        if (Calificacion < 0 || Calificacion > 5) {
            System.out.println("Error: La calificación debe estar entre 0 y 5");
            return;
        }

        Opinion opinion = new Opinion(IdOpinion, IdInmuble, IdUsuario, Comentario, Calificacion);
        this.MisOpiniones.add(opinion);
        Inmueble inmuebleSeleccionado = catalogo.obtenerInmueblePorId(IdInmuble);
       //inmuebleSeleccionado.agregarOpinion(opinion);
        System.out.println("Opinion Agregada");
    }**/

    /**
     * Editar una opinion al Inmueble
     * @param IdOpinion Es el id de la opinion que va se va editar la opinion
     * @return Una nueva Opinion del Inmueble totalmente editada
     */
    /*public Opinion editarOpinion(int IdOpinion)
    {
        Scanner lecturaInt= new Scanner(System.in);
        Scanner lecturaString= new Scanner(System.in);
        int i;
        int opt;

        for(i=0;i<this.MisOpiniones.size(); i++){
            if(this.MisOpiniones.get(i).getIdOpinion() == IdOpinion){
                break;
            }
        }

        Opinion opinionEditada = this.MisOpiniones.get(i);

        do{
            System.out.println("¿Que deseas Editar?");
            System.out.println("1.Comentario");
            System.out.println("2.Calificacion");
            System.out.println("0.Salir");

            opt = lecturaInt.nextInt();
            if(opt == 1){
                System.out.println("Escribe tu nuevo Comentario");
                opinionEditada.setComentario(lecturaString.nextLine());
            }

            if(opt == 2){
                System.out.println("Escribe tu nueva Calificacion");
                opinionEditada.setCalificacion(lecturaInt.nextFloat());
            }



        }while(opt != 0);

        this.MisOpiniones.remove(i);
        this.MisOpiniones.add(opinionEditada);
        Inmueble inmueble = catalogo.obtenerInmueblePorId(opinionEditada.getIdInmueble());
        //inmueble.eliminarOpinion(IdOpinion);
        //inmueble.agregarOpinion(opinionEditada);

        return opinionEditada;
    }*/

    /**
     * Eliminar tu opinion del Inmueble
     * @param IdOpinion Es el id de la opinion que se desea eliminar
     */
    /*public void eliminarOpinion(int IdOpinion)
    {
        for(int i=0;i<this.MisOpiniones.size(); i++){
            if(this.MisOpiniones.get(i).getIdOpinion() == IdOpinion){
                this.MisOpiniones.remove(i);
                break;
            }
        }

        System.out.println("Opinion Eliminada");
    }*/


    /**
     * Obtenemos los inmuebles que cumplan con el atributo dado
     * @return Lista de Inmuebles que cumplan con ese atributo
     */
    public HashMap<String, Object> filtrarInmuebles(List<String> atributo, List<Object> valor)
    {
        HashMap<String, Object> misFiltros = new HashMap<>();

        for(int i=0; i<atributo.size(); i++){
            misFiltros.put(atributo.get(i),valor.get(i));
        }

        return misFiltros;
    }

}

package Modelos;

import java.util.ArrayList;
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

    ArrayList<OpinionInmueble> MisOpiniones = new  ArrayList<>();
    Catalogo catalogo = new Catalogo();

    /**
     * Constructor para el objeto Arrendatario
     * @param IdUsuario Identificador del ciudadano
     * @param Nombre El nombre completo asociado al ciudadano
     * @param Cedula Numero de documento del ciudadano
     */
    public Arrendatario(int IdUsuario, String Nombre, String Cedula, String NumeroContacto)
    {
        super(IdUsuario, Nombre, Cedula, NumeroContacto);
    }

    /**
     * Agregar una opinion al Inmueble
     * @param IdInmuble Es el id del inmueble que va recibir la opinion
     * @param IdUsuario Es el id del usuario que da la opinion
     * @param Comentario Es el comentario de tu opinion
     * @param Calificacion Es la calificacion de tu opinion
     * @return Una nueva Opinion del Inmueble
     */
    public void agregarOpinion(int IdOpinion, String IdInmuble, int IdUsuario,
                               String Comentario, float Calificacion)
    {
        if (Calificacion < 0 || Calificacion > 5) {
            System.out.println("Error: La calificación debe estar entre 0 y 5");
            return;
        }

        OpinionInmueble opinion = new OpinionInmueble(IdOpinion, IdInmuble, IdUsuario, Comentario, Calificacion);
        this.MisOpiniones.add(opinion);
        Inmueble inmuebleSeleccionado = catalogo.obtenerInmueblePorId(IdInmuble);
        inmuebleSeleccionado.agregarOpinion(opinion);
        System.out.println("Opinion Agregada");
    }

    /**
     * Editar una opinion al Inmueble
     * @param IdOpinion Es el id de la opinion que va se va editar la opinion
     * @return Una nueva Opinion del Inmueble totalmente editada
     */
    public OpinionInmueble editarOpinion(int IdOpinion)
    {
        Scanner lecturaInt= new Scanner(System.in);
        Scanner lecturaString= new Scanner(System.in);
        int i;
        int opt;

        for(i=0;i<this.MisOpiniones.size(); i++){
            if(this.MisOpiniones.get(i).GetIdOpinion() == IdOpinion){
                break;
            }
        }

        OpinionInmueble opinionEditada = this.MisOpiniones.get(i);

        do{
            System.out.println("¿Que deseas Editar?");
            System.out.println("1.Comentario");
            System.out.println("2.Calificacion");
            System.out.println("0.Salir");

            opt = lecturaInt.nextInt();
            if(opt == 1){
                System.out.println("Escribe tu nuevo Comentario");
                opinionEditada.SetComentario(lecturaString.nextLine());
            }

            if(opt == 2){
                System.out.println("Escribe tu nueva Calificacion");
                opinionEditada.SetCalificacion(lecturaInt.nextFloat());
            }



        }while(opt != 0);

        this.MisOpiniones.remove(i);
        this.MisOpiniones.add(opinionEditada);
        Inmueble inmueble = catalogo.obtenerInmueblePorId(opinionEditada.GetIdInmueble());
        inmueble.eliminarOpinion(IdOpinion);
        inmueble.agregarOpinion(opinionEditada);

        return opinionEditada;
    }

    /**
     * Eliminar tu opinion del Inmueble
     * @param IdOpinion Es el id de la opinion que se desea eliminar
     */
    public void eliminarOpinion(int IdOpinion)
    {
        for(int i=0;i<this.MisOpiniones.size(); i++){
            if(this.MisOpiniones.get(i).GetIdOpinion() == IdOpinion){
                this.MisOpiniones.remove(i);
                break;
            }
        }

        System.out.println("Opinion Eliminada");
    }

    /**
     * Obtener todos los Inmuebles del Catalogo
     * @return Lista de Inmuebles Obtenidos
     */

    public void verCatalogoInmubles()
    {
        Scanner lecturaString = new Scanner(System.in);
        Scanner lecturaInt= new Scanner(System.in);
        ArrayList<Inmueble> inmuebles =  catalogo.ObtenerInmuebles();
        int opt;
        for(int i=0;i<inmuebles.size(); i++){
            System.out.println("------------------------");
            inmuebles.get(i).mostrarDatos();
            System.out.println("------------------------");
        }

        do{
            System.out.println("¿Accion Desea realizar?");
            System.out.println("1.Seleccionar un Inmueble");
            System.out.println("2.Filtrar");
            System.out.println("0.Salir");
            opt = lecturaInt.nextInt();

            if(opt == 1){
                System.out.println("¿Que inmueble deseas selecionar?");
                System.out.println("(Digita el Id del Inmueble)");
                seleccionarInmueble(lecturaString.nextLine());
            }

            if(opt == 2){
                this.filtrarInmuebles();
            }

        }while(opt != 0);

    }

    /**
     * Obtenemos los inmuebles que cumplan con el atributo dado
     * @param Atributo Valor por el que se van a filtrar los inmuebles
     * @return Lista de Inmuebles que cumplan con ese atributo
     */
    public void filtrarInmuebles()
    {
        HashMap<String, Object> misFiltros = new HashMap<>();
        Scanner lecturaInt= new Scanner(System.in);
        Scanner lecturaString= new Scanner(System.in);
        Scanner lecturaBoolean= new Scanner(System.in);
        int opt;

        do{
            System.out.println("¿Deseas filtrar?");
            System.out.println("1.Precio");
            System.out.println("2.Barrio");
            System.out.println("3.Cantidad Habitaciones");
            System.out.println("4.Servicios Publicos");
            System.out.println("5.Area");
            System.out.println("6.Calificacion Promedio");
            System.out.println("7.Buscar");
            System.out.println("8.Limpiar Filtro");
            System.out.println("0.Salir");
            opt = lecturaInt.nextInt();

            if(opt == 1){
                System.out.println("Precio mayor o menor");
                System.out.println("(Escribe true si deseeas que busque precios mayores o false si deseeas que busque precios menores)");
                boolean mayorMenor = lecturaBoolean.nextBoolean();
                System.out.println("Cual es el Precio que deseas filtrar");
                float precio = lecturaInt.nextFloat();
                ArrayList<Object> elementos = new ArrayList<>();
                elementos.add(mayorMenor);
                elementos.add(precio);
                misFiltros.put("Precio",elementos);
            }

            if(opt == 2){
                System.out.println("¿Por cual Barrio Deseas buscar?");
                String barrio = lecturaString.nextLine();
                misFiltros.put("Barrio",barrio);
            }

            if(opt == 3){
                System.out.println("¿Cuantas Habitaciones deseas?");
                int habitaciones = lecturaInt.nextInt();
                misFiltros.put("Habitaciones",habitaciones);
            }

            if(opt == 4){
                System.out.println("¿Deseas que incluya servicios?");
                boolean servicios = lecturaBoolean.nextBoolean();
                misFiltros.put("Servicios",servicios);
            }

            if(opt == 5){
                System.out.println("¿Area mayor o menor?");
                System.out.println("(Escribe true si deseeas que busque areas mayores o false si deseeas que busque areas menores)");
                boolean mayorMenor = lecturaBoolean.nextBoolean();
                System.out.println("Cual es el area que deseas filtrar");
                float area = lecturaInt.nextFloat();
                ArrayList<Object> elementos = new ArrayList<>();
                elementos.add(mayorMenor);
                elementos.add(area);
                misFiltros.put("Area",area);
            }

            if(opt == 6){
                System.out.println("¿Calficacion mayor o menor?");
                System.out.println("(Escribe true si deseeas que busque calificaciones mayores o false si deseeas que busque calificaiones menores)");
                boolean mayorMenor = lecturaBoolean.nextBoolean();
                System.out.println("Cual es la calificacion que deseas filtrar");
                float calificacion = lecturaInt.nextFloat();
                ArrayList<Object> elementos = new ArrayList<>();
                elementos.add(mayorMenor);
                elementos.add(calificacion);
                misFiltros.put("Calificacion",elementos);
            }

            if(opt == 7){
                if(catalogo.GetFiltrado()){
                    catalogo.filtrar(misFiltros);
                }else{
                    verCatalogoInmubles();
                }

            }

            if(opt == 8){
                catalogo.limpiarFiltro();
            }

        }while(opt != 0);

    }

    /**
     * Selecciona un Inmueble elegido
     * @param IdInmueble Es el id del inmueble que va seleccionar el usuario
     * @return El inmueble seleccionado con todos sus atributos
     */

    public void seleccionarInmueble(String IdInmueble)
    {
        Scanner lecturaInt= new Scanner(System.in);
        Scanner lecturaString= new Scanner(System.in);
        Inmueble inmuebleSeleccionado = catalogo.obtenerInmueblePorId(IdInmueble);
        int opt;

        System.out.println("------------------------");
        System.out.println("Este es el Inmueble que se seleccionate");
        inmuebleSeleccionado.mostrarDatos();
        System.out.println("------------------------");

        do{
            System.out.println("¿Que deseas hacer?");
            System.out.println("1.Ver opiniones");
            System.out.println("2.Opinar");
            System.out.println("3.Contactar Arrendador");
            System.out.println("4.Editar Opinion");
            System.out.println("5.Eliminar Opinion");
            System.out.println("0.Salir");
            opt = lecturaInt.nextInt();

            if(opt == 1){
                inmuebleSeleccionado.mostarOpinones();
            }

            if(opt == 2){
                System.out.println("Ingrese la IdOpinion");
                int IdOpinion = lecturaInt.nextInt();
                System.out.println("Ingrese el Comentario deseado");
                String Comentario = lecturaString.nextLine();
                System.out.println("Ingrese la Calificacion deseada");
                float Calificacion = lecturaInt.nextFloat();
                int IdUsuario = this.GetIdUsuario();
                String IdInmuble = inmuebleSeleccionado.GetIdInmueble();

                this.agregarOpinion(IdOpinion, IdInmuble, IdUsuario, Comentario, Calificacion);
            }

            if(opt == 3){
                String numero = inmuebleSeleccionado.GetTelefonoArrendador();
                System.out.println("LLamando a....");
                System.out.println(numero);
            }

            if(opt == 4){
                ArrayList<OpinionInmueble> opiniones =  inmuebleSeleccionado.GetMisOpiniones();
                for(int i=0;i<opiniones.size(); i++){
                    if(opiniones.get(i).GetIdUsuario() == this.GetIdUsuario()){
                        this.editarOpinion(opiniones.get(i).GetIdOpinion());
                        break;
                    }
                }

            }

            if(opt == 5){
                ArrayList<OpinionInmueble> opiniones = inmuebleSeleccionado.GetMisOpiniones();
                for(int i=0;i<opiniones.size(); i++){
                    if(opiniones.get(i).GetIdUsuario() == this.GetIdUsuario()){
                        this.eliminarOpinion(opiniones.get(i).GetIdOpinion());
                        inmuebleSeleccionado.eliminarOpinion(opiniones.get(i).GetIdOpinion());
                        break;
                    }
                }
            }
        }while(opt != 0 && opt != 3);


    }
}

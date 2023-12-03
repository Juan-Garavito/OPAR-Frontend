package Modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Peticiones.ApiCliente;
import Peticiones.ApiInmueble;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Clase que representa el catalogo de los inmuebles
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class Catalogo
{
    public static Catalogo catalogo = new Catalogo();
    public  List<Inmueble> inmuebles = new ArrayList<>();
    List<Inmueble> inmueblesFiltrados = new ArrayList<>();
    boolean filtrado;
    /**
     * Constructor para el objeto Catalogo
     * Se inicializa el estado filtrado en false
     */
    public Catalogo()
    {
        this.filtrado = false;
        this.inmueblesFiltrados = this.ObtenerInmuebles();
    }

    /**
     * Guarda el inmueble en la lista global de inmuebles
     * @param inmueble El inmueble que se va guardar en la lista
     */
    public void guardarInmueble(Inmueble inmueble){
        this.inmuebles.add(inmueble);
    }

    public boolean GetFiltrado(){
        return this.filtrado;
    }

    /**
     * Elimina el inmueble de la lista global de inmuebles
     * @param IdInmueble El identificador del inmueble que se va eliminar de la lista
     */
    public void eliminarInmueble(String IdInmueble)
    {
        for(int i=0;i<this.inmuebles.size(); i++){
            if(this.inmuebles.get(i).GetIdInmueble().equals( IdInmueble)){
                this.inmuebles.remove(i);
            }
            System.out.println("Inmueble " + IdInmueble + " eliminado");
        }
    }

    /**
     * Devuelve la lista de inmuebles dependiendo del estado que se encuentra
     * @return Lista de inmuebles
     */
    public List<Inmueble> ObtenerInmuebles(){
        if(filtrado){
            return this.inmueblesFiltrados;
        }else{


            return Catalogo.catalogo.inmuebles;
        }
    }

    /**
     * Devuelve la lista de inmuebles por un identificador especifico
     * @return Imnueble que le corresponde el identificador
     */
    public Inmueble obtenerInmueblePorId(String IdInmueble)
    {
        int i;
        for(i=0;i<this.inmuebles.size(); i++){
            if(this.inmuebles.get(i).GetIdInmueble().equals(IdInmueble)){
                break;
            }
        }

        Inmueble inmueble = this.inmuebles.get(i);
        return inmueble;
    }

    /**
     * Filtra los inmuebles por las caraceristicas deseadas
     * @param filtro HashMap con los atributos y opciones de filtrado
     */
    public List<Inmueble> filtrar(HashMap<String, Object> filtro){
        this.filtrado = true;
        this.inmueblesFiltrados = this.ObtenerInmuebles();
        List<Inmueble> auxInmuebles = new ArrayList<>();

        if(filtro.containsKey("Precio")){
            boolean menorMayor = false;
            float precio = 0;
            List<Object> values = (ArrayList<Object>) filtro.get("Precio");
            for(Object value : values){
                if (value instanceof Boolean) {
                    menorMayor = (boolean) value;
                }

                if (value instanceof Float) {
                    precio = (float) value;
                }
            }

            if(menorMayor){
                for(Inmueble inmueble : this.inmueblesFiltrados){
                    if(inmueble.GetPrecio() >= precio){
                        auxInmuebles.add(inmueble);
                    }
                }
            }else{
                for(Inmueble inmueble : this.inmueblesFiltrados){
                    if(inmueble.GetPrecio() <= precio){
                        auxInmuebles.add(inmueble);
                    }
                }
            }

            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Barrio")){
            String barrio =  (String) filtro.get("Barrio");
            for(Inmueble inmueble : this.ObtenerInmuebles()){
                if(inmueble.GetBarrio().equals(barrio)){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Habitaciones")){
            int habitaciones = (int) filtro.get("Habitaciones");
            for(Inmueble inmueble : this.ObtenerInmuebles()){
                if(inmueble.GetCantidadHabitaciones() == habitaciones){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }


        if(filtro.containsKey("Servicios")){
            boolean servicios = (boolean) filtro.get("Servicios");
            for(Inmueble inmueble : this.ObtenerInmuebles()){
                if(inmueble.GetServiciosPublicos() == servicios){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Area")){
            boolean menorMayor = false;
            float area = 0;
            ArrayList<Object> values = (ArrayList<Object>) filtro.get("Area");
            for(Object value : values){
                if (value instanceof Boolean) {
                    menorMayor = (boolean) value;
                }

                if (value instanceof Float) {
                    area = (float) value;
                }
            }

            if(menorMayor){
                for(Inmueble inmueble : this.inmueblesFiltrados){
                    if(inmueble.GetArea() >= area){
                        auxInmuebles.add(inmueble);
                    }
                }
            }else{
                for(Inmueble inmueble : this.inmueblesFiltrados){
                    if(inmueble.GetArea() <= area){
                        auxInmuebles.add(inmueble);
                    }
                }
            }

            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Calificacion")){
            boolean menorMayor = false;
            float calificacion = 0;
            ArrayList<Object> values = (ArrayList<Object>) filtro.get("Calificacion");
            for(Object value : values){
                if (value instanceof Boolean) {
                    menorMayor = (boolean) value;
                }

                if (value instanceof Float) {
                    calificacion = (float) value;
                }
            }

            if(menorMayor){
                for(Inmueble inmueble : this.inmueblesFiltrados){
                    if(inmueble.GetCalificacionPromedio() >= calificacion){
                        auxInmuebles.add(inmueble);
                    }
                }
            }else{
                for(Inmueble inmueble : this.inmueblesFiltrados){
                    if(inmueble.GetCalificacionPromedio() <= calificacion){
                        auxInmuebles.add(inmueble);
                    }
                }
            }

            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Tipo")){
            String tipo = (String) filtro.get("Tipo");
            for(Inmueble inmueble : this.ObtenerInmuebles()){
                if(inmueble.getTipoInmueble().toLowerCase().equals(tipo.toLowerCase())){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        return this.inmueblesFiltrados;
    }

    /**
     * Cambia el estado de filtrado del Catalogo
     */
    public void limpiarFiltro(){
        this.filtrado = false;
    }
}

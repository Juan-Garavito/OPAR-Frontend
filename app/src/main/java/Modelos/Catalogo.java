package Modelos;

import android.util.Log;
import android.widget.Toast;

import com.opar.opar.ArrendatarioActivity;

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
    private static List<Inmueble> inmuebles = new ArrayList<>();
    private static List<Inmueble> inmueblesFiltrados = new ArrayList<>();
    private static boolean filtrado;

    /**
     * Constructor para el objeto Catalogo
     * Se inicializa el estado filtrado en false
     */
    public Catalogo()
    {
        this.filtrado = false;
    }

    public static List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public static void setInmuebles(List<Inmueble> inmuebles) {
        Catalogo.inmuebles = inmuebles;
    }

    public static List<Inmueble> getInmueblesFiltrados() {
        return inmueblesFiltrados;
    }

    public static void setInmueblesFiltrados(List<Inmueble> inmueblesFiltrados) {
        Catalogo.inmueblesFiltrados = inmueblesFiltrados;
    }

    public static boolean isFiltrado() {
        return filtrado;
    }

    public static void setFiltrado(boolean filtrado) {
        Catalogo.filtrado = filtrado;
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
    public void eliminarInmueble(Integer IdInmueble)
    {
        for(int i=0;i<this.inmuebles.size(); i++){
            if(this.inmuebles.get(i).getIdInmueble().equals( IdInmueble)){
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
        //if(filtrado){
            //return this.inmueblesFiltrados;
        //}else{
            return this.inmuebles;
        //}
    }

    /**
     * Devuelve la lista de inmuebles por un identificador especifico
     * @return Imnueble que le corresponde el identificador
     */
    public Inmueble obtenerInmueblePorId(Integer IdInmueble)
    {
        int i;
        for(i=0;i<this.inmuebles.size(); i++){
            if(this.inmuebles.get(i).getIdInmueble().equals(IdInmueble)){
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
            float precio = (float) filtro.get("Precio");
            for(Inmueble inmueble : this.inmueblesFiltrados){
                if(inmueble.getPrecio() <= precio){
                    auxInmuebles.add(inmueble);
                }
            }

            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Barrio")){
            String barrio =  (String) filtro.get("Barrio");
            for(Inmueble inmueble : this.inmueblesFiltrados){
                if(inmueble.getIdBarrio().getBarrio().toLowerCase().equals(barrio.toLowerCase())){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Habitaciones")){
            int habitaciones = (int) filtro.get("Habitaciones");
            for(Inmueble inmueble : this.inmueblesFiltrados){
                if(inmueble.getCantidadHabitaciones() == habitaciones){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }


        if(filtro.containsKey("Servicios")){
            Integer servicios = (Integer) filtro.get("Servicios");
            for(Inmueble inmueble : this.inmueblesFiltrados){
                if(inmueble.getServiciosPublicos() == servicios){
                    auxInmuebles.add(inmueble);
                }
            }
            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Area")){
            float area = (float) filtro.get("Area");
            for(Inmueble inmueble : this.inmueblesFiltrados){
                if(inmueble.getArea() <= area){
                    auxInmuebles.add(inmueble);
                }
            }

            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Calificacion")){
            float calificacion = (float) filtro.get("Calificacion");

            for(Inmueble inmueble : this.inmueblesFiltrados) {
                if (inmueble.getCalificacionPromedio() <= calificacion) {
                    auxInmuebles.add(inmueble);
                }
            }

            this.inmueblesFiltrados = new ArrayList<>(auxInmuebles);
            auxInmuebles.clear();
        }

        if(filtro.containsKey("Tipo")){
            String tipo = (String) filtro.get("Tipo");
            for(Inmueble inmueble : this.inmueblesFiltrados){
                if(inmueble.getIdTipoInmueble().getTipoInmueble().toLowerCase().equals(tipo.toLowerCase())){
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

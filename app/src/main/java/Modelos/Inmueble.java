package Modelos;

import java.io.Serializable;
import java.util.List;

import Modelos.Imagen;

public class Inmueble  implements Serializable {


    private Integer idInmueble;
    private Tipo_InmuebleDTO idTipoInmueble;
    private BarrioDTO idBarrio;
    private Ciudadano numeroDocumento;
    private Integer cantidadHabitaciones;
    private Integer serviciosPublicos;
    private float area;
    private String descripcion;
    private String direccion;
    private float precio;

    private float CalificacionPromedio = 0f;
    private List<Imagen> imagenes;


    public Inmueble(Integer idInmueble, Tipo_InmuebleDTO idTipoInmueble, BarrioDTO idBarrio, Ciudadano numeroDocumento, Integer cantidadHabitaciones, Integer serviciosPublicos, float area, String descripcion, String direccion, Integer precio, List<Imagen> imagenes) {
        this.idInmueble = idInmueble;
        this.idTipoInmueble = idTipoInmueble;
        this.idBarrio = idBarrio;
        this.numeroDocumento = numeroDocumento;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.serviciosPublicos = serviciosPublicos;
        this.area = area;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.precio = precio;
        this.imagenes = imagenes;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCalificacionPromedio() {
        return CalificacionPromedio;
    }

    public void setCalificacionPromedio(float calificacionPromedio) {
        CalificacionPromedio = calificacionPromedio;
    }



    public Integer getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Tipo_InmuebleDTO getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(Tipo_InmuebleDTO idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public BarrioDTO getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(BarrioDTO idBarrio) {
        this.idBarrio = idBarrio;
    }

    public Ciudadano getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Ciudadano numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public Integer getServiciosPublicos() {
        return serviciosPublicos;
    }

    public void setServiciosPublicos(Integer serviciosPublicos) {
        this.serviciosPublicos = serviciosPublicos;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }


    @Override
    public String toString() {
        return "Inmueble{" +
                "idInmueble=" + idInmueble +
                ", idTipoInmueble=" + idTipoInmueble.getTipoInmueble() +
                ", idBarrio=" + idBarrio.getBarrio() +
                ", numeroDocumento=" + numeroDocumento.getNumeroDocumento() +
                ", cantidadHabitaciones=" + cantidadHabitaciones +
                ", serviciosPublicos=" + serviciosPublicos +
                ", area=" + area +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

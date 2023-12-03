package Modelos;
import java.util.ArrayList;

/**
 * Clase que representa la inmueble con toda su informacion.
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class Inmueble
{

    private String IdInmueble;
    private String TipoInmueble;
    private int IdUsuario;
    private float Precio;
    private String Barrio;
    private int CantidadHabitaciones;
    private boolean ServiciosPublicos;
    private float area;
    private String Descripcion;
    private String Direccion;
    private String TelefonoArrendador;
    private float CalificacionPromedio;

    ArrayList<OpinionInmueble> MisOpiniones = new  ArrayList<>();

    /**
     * Constructor para el objeto Inmueble
     * @param IdInmueble El identificador del inmueble
     * @param IdUsuario El identificador del ciudadano dueño del inmueble
     * @param TipoInmueble El tipo del inmueble
     * @param Precio El precio al que se alquila el Arriendo
     * @param Barrio El barrio donde esta el inmueble
     * @param CantidadHabitaciones La cantidad de habitaciones del inmueble
     * @param ServiciosPublicos True si posee servicios publicos
     * @param area El area en metros cuadrados del inmueble
     * @param Descripcion La descripcion del inmueble
     * @param Direccion La direccion del inmueble
     * @param TelefonoArrendador El telefono del arrendador del inmueble
     */
    public Inmueble(String IdInmueble,int IdUsuario, String TipoInmueble, float Precio, String Barrio,
                    int CantidadHabitaciones, boolean ServiciosPublicos, float area,
                    String Descripcion, String Direccion, String TelefonoArrendador)
    {
        this.TipoInmueble = TipoInmueble;
        this.Precio = Precio;
        this.Barrio = Barrio;
        this.CantidadHabitaciones = CantidadHabitaciones;
        this.ServiciosPublicos = ServiciosPublicos;
        this.area = area;
        this.Descripcion = Descripcion;
        this.Direccion = Direccion;
        this.TelefonoArrendador = TelefonoArrendador;
        this.IdInmueble = IdInmueble;
        this.IdUsuario = IdUsuario;
    }

    /**
     * Calcula el promedio de las calificaciones de todas las opiniones del inmueble
     * @param Opiniones Lista de todas las opiniones del inmueble. Se utiliza
     * para calular el promedio de calificaciones
     * @return Devuelve el promedio de las calificaciones del inmueble
     */
    public float calcularCalificacionPromedio(ArrayList<OpinionInmueble> Opiniones)
    {
        float promedio = 0;
        float sumCalificacion = 0;
        int i;
        for(i=0;i<Opiniones.size(); i++){
            sumCalificacion = sumCalificacion + Opiniones.get(i).GetCalificacion();
        }
        promedio = sumCalificacion/i;
        return promedio;
    }

    /**
     * Agrega la opinion a la lista de tus opiniones
     * @param Opinion La opinion que se desea agregar a la lista
     */
    public void agregarOpinion(OpinionInmueble Opinion)
    {
        this.MisOpiniones.add(Opinion);
    }

    /**
     * Muestra la lista de opiniones que pertenecen al inmueble
     */
    public void mostarOpinones()
    {
        for(int i=0;i<this.MisOpiniones.size(); i++){
            System.out.println("------------------------");
            this.MisOpiniones.get(i).mostrarDatos();
            System.out.println("------------------------");
        }
    }

    /**
     * Elimina la opinion correspondiente de la lista de opiniones
     * @param IdOpinion El identificador de la opinion que deseas eliminar
     */
    public void eliminarOpinion(int IdOpinion){
        for(int i=0;i<this.MisOpiniones.size(); i++){
            if(this.MisOpiniones.get(i).GetIdOpinion() == IdOpinion){
                this.MisOpiniones.remove(i);
                break;
            }

        }
    }

    /**
     * Muestra toddos los datos del inmueble
     */
    public void mostrarDatos()
    {
        System.out.println("Id Inmueble:" + this.IdInmueble);
        System.out.println("Id Usuario:" + this.IdUsuario);
        System.out.println("Tipo Inmueble:" + this.TipoInmueble);
        System.out.println("Precio:" + this.Precio);
        System.out.println("Barrio:" + this.Barrio);
        System.out.println("ServiciosPublicos:" + this.ServiciosPublicos);
        System.out.println("Cantidad Habitaciones:" + this.CantidadHabitaciones);
        System.out.println("Area:" + this.area);
        System.out.println("Descripcion:" + this.Descripcion);
        System.out.println("Direccion:" + this.Direccion);
        System.out.println("Telefono Arrendador:" + this.TelefonoArrendador);
        this.CalificacionPromedio =  calcularCalificacionPromedio(this.MisOpiniones);
        System.out.println("Califcacion:" + this.CalificacionPromedio);
    }

    public void SetTipoInmueble(String TipoInmueble){
        this.TipoInmueble = TipoInmueble;
    }

    public void SetPrecio(float Precio){
        this.Precio = Precio;
    }

    public void SetBarrio(String Barrio){
        this.Barrio = Barrio;
    }

    public void SetCantidadHabitaciones(int CantidadHabitaciones){
        this.CantidadHabitaciones = CantidadHabitaciones;
    }

    public void Setarea(float area){
        this.area = area;
    }


    public void SetDescripcion(String Descripcion){
        this.Descripcion = Descripcion;
    }

    public void SetDireccion(String Direccion){
        this.Direccion = Direccion;
    }

    public void SetTelefonoArrendador(String TelefonoArrendador){
        this.TelefonoArrendador = TelefonoArrendador;
    }

    public void SetServiciosPublicos( boolean ServiciosPublicos){
        this.ServiciosPublicos = ServiciosPublicos;
    }

    public String GetIdInmueble(){
        return this.IdInmueble;
    }

    public String GetTipoInmueble(){
        return this.TipoInmueble;
    }
    public float GetPrecio(){
        return this.Precio;
    }

    public String GetBarrio(){
        return this.Barrio;
    }

    public float GetArea(){
        return this.area;
    }

    public int GetCantidadHabitaciones(){
        return this.CantidadHabitaciones;
    }

    public boolean GetServiciosPublicos(){
        return this.ServiciosPublicos;
    }

    public float GetCalificacionPromedio(){
        return this.CalificacionPromedio;
    }

    public String GetTelefonoArrendador(){
        return this.TelefonoArrendador;
    }

    public ArrayList<OpinionInmueble> GetMisOpiniones(){
        return MisOpiniones;
    }

    public String getTipoInmueble() {
        return TipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        TipoInmueble = tipoInmueble;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "IdInmueble='" + IdInmueble + '\'' +
                ", TipoInmueble='" + TipoInmueble + '\'' +
                ", IdUsuario=" + IdUsuario +
                ", Precio=" + Precio +
                ", Barrio='" + Barrio + '\'' +
                ", CantidadHabitaciones=" + CantidadHabitaciones +
                ", ServiciosPublicos=" + ServiciosPublicos +
                ", area=" + area +
                ", Descripcion='" + Descripcion + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", TelefonoArrendador='" + TelefonoArrendador + '\'' +
                '}';
    }
}

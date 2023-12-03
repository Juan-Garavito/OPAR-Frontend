package Modelos;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase que representa al rol de Arrendador.
 * Esta clase hereda de la clase Ciudadano.
 *
 * @author Juan Garavito -  Jerson Cañon
 * @version 13-11-2023
 */
public class Arrendador extends Ciudadano
{
    ArrayList<Inmueble> MisInmuebles = new ArrayList<>();
    Catalogo catalogo = new Catalogo();
    /**
     * Constructor for objects of class Arrendador
     */
    public Arrendador(int IdUsuario, String Nombre, String Cedula, String NumeroContacto)
    {
        super(IdUsuario, Nombre, Cedula, NumeroContacto);
    }

    /**
     * Agregar un nuevo Inmueble
     * @param TipoInmueble El tipo de inmueble que pertenece
     * @param Precio El precio de alquiler del inmueble
     * @param Barrio El barrio al que pertenece el inmueble
     * @param CantidadHabitaciones El numero de habitaciones del inmueble
     * @param ServiciosPublicos Si incluye servicios publicos el inmueble
     * @param Area Las dimensiones del inmueble
     * @param Descripcion Descripcion dada por el usuario
     * @param Direccion La direccion del inmueble
     * @return El inmueble nuevo
     */
    public void agregarInmueble(String IdInmueble, String TipoInmueble, float Precio,
                                String Barrio, int CantidadHabitaciones, boolean ServiciosPublicos,
                                float Area, String Descripcion,
                                String Direccion){
        if (IdInmueble.isEmpty()) {
            System.out.println("Error: IdInmueble no puede estar vacío");
            return;
        }
        if (Precio < 0) {
            System.out.println("Error: Precio no puede ser negativo");
            return;
        }
        if (CantidadHabitaciones < 0) {
            System.out.println("Error: CantidadHabitaciones no puede ser negativa");
            return;
        }
        if (Area < 0) {
            System.out.println("Error: Area no puede ser negativa");
            return;
        }
        Inmueble inmueble = new Inmueble(IdInmueble, this.GetIdUsuario(), TipoInmueble, Precio,
                Barrio, CantidadHabitaciones, ServiciosPublicos,
                Area, Descripcion,
                Direccion,  this.NumeroContacto);

        this.MisInmuebles.add(inmueble);
        catalogo.guardarInmueble(inmueble);
    }

    /**
     * Edita un Inmueble seleccionado
     * @return El inmueble totalmente editado
     */
    public Inmueble editarInmueble(String IdInmueble){
        int Option;
        int i = 0;
        Scanner lecturaInt = new Scanner(System.in);
        Scanner lecturaString = new Scanner(System.in);
        Scanner lecturaBoolean = new Scanner(System.in);

        for(i=0;i<this.MisInmuebles.size(); i++){
            if(this.MisInmuebles.get(i).GetIdInmueble().equals(IdInmueble)){
                break;
            }
        }
        if (i == this.MisInmuebles.size()) {
            System.out.println("Error: No se encontró ningún inmueble con el IdInmueble dado: " + IdInmueble);
            return null;
        }

        Inmueble editadoInmueble = this.MisInmuebles.get(i);

        do{
            System.out.println("Que atributo deseas editar?");
            System.out.println("1.Tipo de Inmueble");
            System.out.println("2.Precio");
            System.out.println("3.Barrio");
            System.out.println("4.Cantidad de Habitaciones");
            System.out.println("5.Servicios Publicos");
            System.out.println("6.Area");
            System.out.println("7.Imagenes");
            System.out.println("8.Descripcion");
            System.out.println("9.Direccion");
            System.out.println("10.Numero de Contacto");
            System.out.println("0.Salir");
            Option = lecturaInt.nextInt();

            if(Option == 1){
                System.out.println("Escribe el Nuevo Tipo de Inmueble");
                editadoInmueble.SetTipoInmueble(lecturaString.nextLine());
                System.out.println("Atributo Editado");
            }

            if(Option == 2){
                System.out.println("Escribe el Nuevo Precio");
                editadoInmueble.SetPrecio(lecturaInt.nextFloat());
                System.out.println("Atributo Editado");
            }

            if(Option == 3){
                System.out.println("Escribe el Nuevo Barrio");
                editadoInmueble.SetBarrio(lecturaString.nextLine());
                System.out.println("Atributo Editado");
            }

            if(Option == 4){
                System.out.println("Escribe la Nueva Cantidad de Habitaciones");
                editadoInmueble.SetCantidadHabitaciones(lecturaInt.nextInt());
                System.out.println("Atributo Editado");
            }

            if(Option == 5){
                System.out.println("Escribe si incluye Servicios Publicos");
                System.out.println("(Escribe true si incluye o false si no incluye)");
                editadoInmueble.SetServiciosPublicos(lecturaBoolean.nextBoolean());
                System.out.println("Atributo Editado");
            }

            if(Option == 6){
                System.out.println("Escribe el Nuevo Area");
                editadoInmueble.Setarea(lecturaInt.nextFloat());
                System.out.println("Atributo Editado");
            }


            if(Option == 8){
                System.out.println("Escribe la nueva descripcion");
                editadoInmueble.SetDescripcion(lecturaString.nextLine());
                System.out.println("Atributo Editado");
            }

            if(Option == 9){
                System.out.println("Escribe la nueva direccion");
                editadoInmueble.SetDireccion(lecturaString.nextLine());
                System.out.println("Atributo Editado");
            }

            if(Option == 10){
                System.out.println("Escribe el Nuevo Numero de Contacto");
                editadoInmueble.SetTelefonoArrendador(lecturaString.nextLine());
                System.out.println("Atributo Editado");
            }

        }while(Option != 0);

        this.MisInmuebles.remove(i);
        this.MisInmuebles.add(editadoInmueble);
        catalogo.eliminarInmueble(editadoInmueble.GetIdInmueble());
        catalogo.guardarInmueble(editadoInmueble);

        return editadoInmueble;
    }

    /**
     * Eliminar el Inmueble de la base de datos
     * @param IdInmueble El id del inmueble que se desea eliminar
     */
    public void eliminarInmueble(String IdInmueble)
    {

        for(int i=0;i<this.MisInmuebles.size(); i++){
            if(this.MisInmuebles.get(i).GetIdInmueble() == IdInmueble){
                catalogo.eliminarInmueble(IdInmueble);
                this.MisInmuebles.remove(i);
            }
            System.out.println("Inmueble " + IdInmueble + " eliminado");
        }
    }

    /**
     * Muestra todos los inmuebles pertenecientes al arrendador
     */
    public void mostrarInmuebles(){
        Scanner lectura = new Scanner(System.in);
        int opt;
        for(int i=0;i<this.MisInmuebles.size(); i++){
            System.out.println("-----------------------");
            this.MisInmuebles.get(i).mostrarDatos();
            System.out.println("-----------------------");
        }
        do{
            System.out.println("¿Qué deseas realizar?");
            System.out.println("1.Agregar inmueble");
            System.out.println("2.Editar inmueble");
            System.out.println("3.Eliminar inmueble");
            System.out.println("0.Salir");
            opt = lectura.nextInt();
            lectura.nextLine(); // consume the newline

            if(opt == 1){
                System.out.println("Por favor, introduce los detalles del inmueble:");
                System.out.print("IdInmueble: ");
                String IdInmueble = lectura.nextLine();

                System.out.print("TipoInmueble (Casa-Apartamento-Habitacion-Apartaestudio): ");
                String TipoInmueble = lectura.nextLine();

                System.out.print("Precio: ");
                float Precio = lectura.nextFloat();
                lectura.nextLine();

                System.out.print("Barrio: ");
                String Barrio = lectura.nextLine();

                System.out.print("CantidadHabitaciones: ");
                int CantidadHabitaciones = lectura.nextInt();
                lectura.nextLine();

                System.out.print("ServiciosPublicos (Si inlcuye:true/ Si no incluye: false): ");
                boolean ServiciosPublicos = lectura.nextBoolean();
                lectura.nextLine();

                System.out.print("Area: ");
                float Area = lectura.nextFloat();
                lectura.nextLine();

                System.out.print("Imagenes: ");
                String Imagenes = lectura.nextLine();

                System.out.print("Descripcion: ");
                String Descripcion = lectura.nextLine();

                System.out.print("Direccion: ");
                String Direccion = lectura.nextLine();

                agregarInmueble(IdInmueble, TipoInmueble, Precio, Barrio, CantidadHabitaciones, ServiciosPublicos, Area, Descripcion, Direccion);
                System.out.println("Inmueble agregado exitosamente.");
            }

            if (opt == 2){
                System.out.println("Por favor, introduce el id del inmueble");
                System.out.print("IdInmueble: ");
                String IdInmueble = lectura.nextLine();

                editarInmueble(IdInmueble);
            }

            if (opt == 3){
                System.out.println("Por favor, introduce el id del inmueble");
                System.out.print("IdInmueble: ");
                String IdInmueble = lectura.nextLine();

                eliminarInmueble(IdInmueble);
            }

        }while(opt != 0);
    }
}

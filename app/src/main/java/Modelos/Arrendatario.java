package Modelos;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.opar.opar.R;
import com.opar.opar.VisorActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

import Adapter.OpinionAdapter;
import Peticiones.ApiCliente;
import Peticiones.ApiOpinion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void agregarOpinion(Inmueble IdInmuble, Ciudadano IdUsuario,
                               String Comentario, float Calificacion, AlertDialog dialog, View view)
    {

        final Opinion[] opinion = {new Opinion(IdInmuble.getIdInmueble(), IdUsuario, Comentario, Calificacion)};
        Call<Opinion> call = ApiCliente.GetCliente().create(ApiOpinion.class).AgregarOpinion(opinion[0]);
        call.enqueue(new Callback<Opinion>() {
            @Override
            public void onResponse(Call<Opinion> call, Response<Opinion> response) {
                if(response.body() != null){
                    Log.e("OpinionCreada", response.body().toString());
                    Toast.makeText(dialog.getContext(), "Opinion Agregada", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    obtenerOpinionesInmueble(IdInmuble, IdUsuario, view);
                }

            }

            @Override
            public void onFailure(Call<Opinion> call, Throwable t) {

                Toast.makeText(dialog.getContext(), "Ocurrio un problema", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void obtenerOpinionesInmueble(Inmueble inmueble, Ciudadano ciudadano, View context){
        Call<List<Opinion>> call = ApiCliente.GetCliente().create(ApiOpinion.class).BuscarPorInmueble(inmueble.getIdInmueble());

        Button opinar = context.findViewById(R.id.idBtnOpinar);
        Button editar = context.findViewById(R.id.idBtnEditarOpinion);
        Button eliminar = context.findViewById(R.id.idBtnEliminarOpinion);
        call.enqueue(new Callback<List<Opinion>>() {
            @Override
            public void onResponse(Call<List<Opinion>> call, Response<List<Opinion>> response) {
                if(response.body() != null){
                    List<Opinion> opiniones = response.body();
                    initRecyclerView(opiniones, context );
                    for(Opinion opinion : opiniones){
                        if(opinion.getNumeroDocumento().getNumeroDocumento().equals(ciudadano.getNumeroDocumento())){
                            opinar.setVisibility(View.VISIBLE);
                            editar.setVisibility(View.VISIBLE);
                            eliminar.setVisibility(View.GONE);

                            break;
                        }
                        Log.e("Opinon", opinion.toString());
                    }
                }
                Log.e("Opinon", "No llego nada");
            }

            @Override
            public void onFailure(Call<List<Opinion>> call, Throwable t) {
                Log.e("Opinon", "Error de conexion");
            }
        });
    }

    public void initRecyclerView(List<Opinion> opiniones, View context ){
        RecyclerView recyclerView = context.findViewById(R.id.idOpinionesInmueble);
        recyclerView.setLayoutManager(new GridLayoutManager(context.getContext(), 1));
        recyclerView.setAdapter(new OpinionAdapter(opiniones));
    }

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

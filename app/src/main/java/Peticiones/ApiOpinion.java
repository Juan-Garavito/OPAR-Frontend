package Peticiones;

import java.util.List;

import Modelos.Inmueble;
import Modelos.Opinion;
import Modelos.OpinionEditDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiOpinion {

    @POST("api/opiniones/inmuebles")
    Call<List<Opinion>> BuscarPorInmueble(@Body Integer inmueble);

    @PUT("api/opiniones/agregar")
    Call<Opinion> AgregarOpinion(@Body Opinion opinion);

    @POST("api/opiniones/usuario")
    Call<Opinion> BuscarPorOpinion(@Body OpinionEditDTO opinion);

    @POST("api/opiniones/editar")
    Call<Opinion> EditarOpinion(@Body Opinion opinion);


    @DELETE("api/opiniones/eliminar/{id}")
    Call<Opinion> EliminarOpinion(@Path("id") int id);
}

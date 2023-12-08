package Peticiones;

import java.util.List;

import Modelos.Inmueble;
import Modelos.Opinion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiOpinion {

    @POST("api/opiniones/inmuebles")
    Call<List<Opinion>> BuscarPorInmueble(@Body Inmueble inmueble);
}

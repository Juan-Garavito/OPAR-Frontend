package Peticiones;

import retrofit2.Call;
import retrofit2.http.Body;
import Modelos.LoginRespuesta;
import retrofit2.http.POST;

public interface ApiCiudadano {

    @POST("api/clientes/login")
    Call<Integer> Login(@Body LoginRespuesta login);
}

package Peticiones;

import Modelos.Ciudadano;
import retrofit2.Call;
import retrofit2.http.Body;
import Modelos.LoginCiudadano;
import retrofit2.http.POST;

public interface ApiCiudadano {

    @POST("api/ciudadanos/login")
    Call<Ciudadano> Login(@Body LoginCiudadano login);
}

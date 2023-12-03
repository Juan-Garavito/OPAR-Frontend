package Peticiones;

import java.util.List;

import Modelos.Inmueble;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInmueble {

    @GET("/api/inmuebles/")
    Call<List<Inmueble>> ObtenerInmuebles();
}

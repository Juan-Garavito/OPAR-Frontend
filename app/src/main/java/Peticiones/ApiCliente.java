package Peticiones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

    public static Retrofit retrofit;

    public static Retrofit GetCliente(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://opar-backend-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
package Peticiones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

    public static Retrofit retrofit;

    public static Retrofit GetCliente(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.14.47.40:2020/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
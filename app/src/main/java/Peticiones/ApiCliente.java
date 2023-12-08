package Peticiones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

    public static Retrofit retrofit;

    public static Retrofit GetCliente(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.9:2020/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
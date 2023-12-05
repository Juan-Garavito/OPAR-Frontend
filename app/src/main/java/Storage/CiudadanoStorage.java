package Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import Modelos.Ciudadano;

public class CiudadanoStorage {

    private static final String PREF_NAME = "CiudadanoPreferences";
    private static final String KEY_CIUDADANO = "ciudadano";

    public static void saveCiudadano(Context context, Ciudadano ciudadano) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String ciudadanoJson = new Gson().toJson(ciudadano);
        editor.putString(KEY_CIUDADANO, ciudadanoJson);

        editor.apply();
    }


    public static Ciudadano getCiudadano(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);


        String ciudadanoJson = preferences.getString(KEY_CIUDADANO, "");


        return new Gson().fromJson(ciudadanoJson, Ciudadano.class);
    }
}


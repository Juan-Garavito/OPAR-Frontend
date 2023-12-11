package com.opar.opar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import Adapter.ImagenAdapter;
import Adapter.ImagenCatalogoAdapter;
import Adapter.OpinionAdapter;
import Modelos.Ciudadano;
import Modelos.Inmueble;
import Modelos.Opinion;
import Modelos.OpinionEditDTO;
import Peticiones.ApiCliente;
import Peticiones.ApiOpinion;
import Storage.CiudadanoStorage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisorArrendadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_arrendador);
        Ciudadano ciudadano = CiudadanoStorage.getCiudadano(this);

        Bundle inmueblesEnviados = getIntent().getExtras();
        Inmueble inmueble = (Inmueble) inmueblesEnviados.getSerializable("inmueble");
        obtenerOpinionesInmueble(inmueble, ciudadano);
        Log.e("Visor", inmueble.toString());
    }


    private void initRecyclerView(List<Opinion> opiniones) {
        RecyclerView recyclerView = findViewById(R.id.idOpinionesInmueble);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new OpinionAdapter(opiniones));
    }


    private void obtenerOpinionesInmueble(Inmueble inmueble, Ciudadano ciudadano) {
        Call<List<Opinion>> call = ApiCliente.GetCliente().create(ApiOpinion.class).BuscarPorInmueble(inmueble.getIdInmueble());


        final float[] calificaion = {0};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.card_eliminando_opinion, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView carga = view.findViewById(R.id.idTextCarga);
        carga.setText("Obteniendo los  Datos");

        call.enqueue(new Callback<List<Opinion>>() {
            @Override
            public void onResponse(Call<List<Opinion>> call, Response<List<Opinion>> response) {
                if (response.body() != null) {
                    List<Opinion> opiniones = response.body();
                    initRecyclerView(opiniones);
                    for(Opinion opinion : opiniones){


                        calificaion[0] = calificaion[0] + opinion.getCalificacion();
                    }

                    if(opiniones.size() > 0){
                        calificaion[0] = calificaion[0]/opiniones.size();
                    }

                    cargandoDatos(inmueble, calificaion[0]);

                    dialog.dismiss();
                }else{
                    cargandoDatos(inmueble, 0);
                    dialog.dismiss();
                }
                Log.e("Opinon", "No llego nada");
            }

            @Override
            public void onFailure(Call<List<Opinion>> call, Throwable t) {
                Log.e("Opinon", "Error de conexion");
            }
        });

    }

    private void cargandoDatos(Inmueble inmueble, float cali){
        TextView textView = findViewById(R.id.idTituloDireccion);
        SpannableString mitextoU = new SpannableString(inmueble.getDireccion());
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);


        ViewPager2 imagenes = findViewById(R.id.idBuscarInmueble);
        imagenes.setAdapter(new ImagenCatalogoAdapter(inmueble.getImagenes()));

        TextView barrio = findViewById(R.id.idBarrioInmueble);
        barrio.setText(inmueble.getIdBarrio().getBarrio());

        TextView precio = findViewById(R.id.idPrecioInmueble);
        precio.setText("$" + inmueble.getPrecio());

        TextView descripcion = findViewById(R.id.idDescripcionInmueble);
        descripcion.setText(String.valueOf(inmueble.getDescripcion()));

        RatingBar calificacion = findViewById(R.id.idCalificacionInmueble);
        calificacion.setRating(cali);
    }
}
package com.opar.opar;


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
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

import Adapter.ImagenAdapter;
import Adapter.InmuebleAdapter;
import Adapter.OpinionAdapter;
import Modelos.Ciudadano;
import Modelos.Inmueble;
import Modelos.Opinion;
import Peticiones.ApiCiudadano;
import Peticiones.ApiCliente;
import Peticiones.ApiOpinion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);



        Bundle inmueblesEnviados = getIntent().getExtras();
        Inmueble inmueble = (Inmueble) inmueblesEnviados.getSerializable("inmueble");

        TextView textView =  findViewById(R.id.idTituloDireccion);
        SpannableString mitextoU = new SpannableString(inmueble.getDireccion());
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);


        ViewPager2 imagenes = findViewById(R.id.idImagenesInmueble);
        imagenes.setAdapter(new ImagenAdapter(inmueble.getImagenes()));

        TextView barrio = findViewById(R.id.idBarrioInmueble);
        barrio.setText(inmueble.getIdBarrio().getBarrio());

        TextView precio = findViewById(R.id.idPrecioInmueble);
        precio.setText("$"+inmueble.getPrecio());

        TextView descripcion = findViewById(R.id.idDescripcionInmueble);
        descripcion.setText(String.valueOf(inmueble.getDescripcion()));

        TextView usuario = findViewById(R.id.idUsuarioInmueble);
        usuario.setText(inmueble.getNumeroDocumento().getUsuario());

        TextView telefono = findViewById(R.id.idTelefonoInmueble);
        telefono.setText(inmueble.getNumeroDocumento().getTelefono());

        RatingBar calificacion = findViewById(R.id.idCalificacionInmueble);
        calificacion.setRating(2.5f);

        Button llamar = findViewById(R.id.idBtnLlamar);


        Call<List<Opinion>> call = ApiCliente.GetCliente().create(ApiOpinion.class).BuscarPorInmueble(inmueble.getIdInmueble());

        call.enqueue(new Callback<List<Opinion>>() {
            @Override
            public void onResponse(Call<List<Opinion>> call, Response<List<Opinion>> response) {
                if(response.body() != null){
                    List<Opinion> opiniones = response.body();
                    initRecyclerView(opiniones);
                    for(Opinion opinion : opiniones){
                        Log.e("Opinon", opinion.toString());
                    }
                }
                Log.e("Opinon", "No llego nada");
            }

            @Override
            public void onFailure(Call<List<Opinion>> call, Throwable t) {
                Log.e("Opinon", "Error de conexion");
            }
        });

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+inmueble.getNumeroDocumento().getTelefono().toString()));
                startActivity(intent);
            }
        });

        Log.e("Visor", inmueble.toString());
    }


    private void initRecyclerView(List<Opinion> opiniones){
        RecyclerView recyclerView = findViewById(R.id.idOpinionesInmueble);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new OpinionAdapter(opiniones));
    }

}
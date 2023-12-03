package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import Modelos.Arrendatario;
import Modelos.Catalogo;
import Modelos.Inmueble;
import Peticiones.ApiCliente;
import Peticiones.ApiInmueble;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArrendatarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrendatario);

        Bundle arrendatarioEnviado = getIntent().getExtras();
        Arrendatario arrendatario = (Arrendatario) arrendatarioEnviado.getSerializable("arrendatario");

        ImageButton casa = findViewById(R.id.idCasa);
        ImageButton apartaestudio = findViewById(R.id.idApartaestudio);
        ImageButton habitacion = findViewById(R.id.idHabitacion);
        ImageButton apartamento = findViewById(R.id.idApartamento);
        Call<List<Inmueble>> call = ApiCliente.GetCliente().create(ApiInmueble.class).ObtenerInmuebles();

        call.enqueue(new Callback<List<Inmueble>>() {

            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.body() != null){
                    List<Inmueble>  inmuebles = response.body();
                    if(inmuebles != null){
                        for (Inmueble inmueble : inmuebles){
                            Log.e("Inmueble", inmueble.toString());
                        }
                    }else{
                        Log.e("Inmueble", "Problemas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(ArrendatarioActivity.this, "Fallo",Toast.LENGTH_LONG).show();
            }
        });




        casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Inmueble> inmuebles = arrendatario.filtrarInmuebles("Tipo", "Casa", null);
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles", (Serializable) inmuebles);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        apartaestudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Inmueble> inmuebles =  arrendatario.filtrarInmuebles("Tipo", "Apartaestudio", null);
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles", (Serializable) inmuebles);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        habitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Inmueble> inmuebles = arrendatario.filtrarInmuebles("Tipo", "Habitacion", null);
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles", (Serializable) inmuebles);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        apartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Inmueble> inmuebles = arrendatario.filtrarInmuebles("Tipo", "Apartamento", null);
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles", (Serializable) inmuebles);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
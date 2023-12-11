package com.opar.opar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.ImagenAdapter;
import Adapter.ImagenVisorAdapter;
import Modelos.Arrendatario;
import Modelos.Ciudadano;
import Modelos.Imagen;
import Modelos.Inmueble;
import Peticiones.ApiCliente;
import Peticiones.ApiInmueble;
import Storage.CiudadanoStorage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArrendatarioActivity extends AppCompatActivity {

    List<Inmueble> inmuebles = new ArrayList<>();
    List<Inmueble> allInmuebles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrendatario);

        Ciudadano ciudadano = CiudadanoStorage.getCiudadano(getApplicationContext());

        Arrendatario arrendatario = new Arrendatario(ciudadano.getNumeroDocumento(),
                ciudadano.getNombreCompleto(), ciudadano.getUsuario(),
                ciudadano.getContraseña(), ciudadano.getTelefono()) ;

        ImageButton casa = findViewById(R.id.idCasa);
        ImageButton apartaestudio = findViewById(R.id.idApartaestudio);
        ImageButton habitacion = findViewById(R.id.idHabitacion);
        ImageButton apartamento = findViewById(R.id.idApartamento);
        Button buscar = findViewById(R.id.idBuscarHoy);

        Call<List<Inmueble>> call = ApiCliente.GetCliente().create(ApiInmueble.class).ObtenerInmuebles();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.card_eliminando_opinion, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView carga = view.findViewById(R.id.idTextCarga);
        carga.setText("Cargando...");

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.body() != null){
                    allInmuebles = response.body();
                    for(int i=0;i<5;i++){
                        if(allInmuebles.get(i).getImagenes().get(0) != null){
                            inmuebles.add(allInmuebles.get(i));
                        }
                    }
                    ViewPager2 visorImagenes = findViewById(R.id.idBuscarInmueble);
                    visorImagenes.setAdapter(new ImagenVisorAdapter(inmuebles));
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {

            }
        });


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArrendatarioActivity.this, BuscarActivity.class);
                startActivity(intent);
            }
        });


        casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> atributos = new ArrayList<>();
                atributos.add("Tipo");
                List<Object> valor = new ArrayList<>();
                valor.add("Casa");
                HashMap<String, Object>  filtros = arrendatario.filtrarInmuebles(atributos, valor);
                Bundle bundle = new Bundle();
                bundle.putSerializable("filtros", (Serializable) filtros);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        apartaestudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> atributos = new ArrayList<>();
                atributos.add("Tipo");
                List<Object> valor = new ArrayList<>();
                valor.add("Apartaestudio");
                HashMap<String, Object>  filtros = arrendatario.filtrarInmuebles(atributos, valor);
                Bundle bundle = new Bundle();
                bundle.putSerializable("filtros", (Serializable) filtros);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        habitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> atributos = new ArrayList<>();
                atributos.add("Tipo");
                List<Object> valor = new ArrayList<>();
                valor.add("Habitacion");
                HashMap<String, Object>  filtros = arrendatario.filtrarInmuebles(atributos, valor);
                Bundle bundle = new Bundle();
                bundle.putSerializable("filtros", (Serializable) filtros);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        apartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> atributos = new ArrayList<>();
                atributos.add("Tipo");
                List<Object> valor = new ArrayList<>();
                valor.add("Apartamento");
                HashMap<String, Object>  filtros = arrendatario.filtrarInmuebles(atributos, valor);
                Bundle bundle = new Bundle();
                bundle.putSerializable("filtros", (Serializable) filtros);
                Intent intent = new Intent(ArrendatarioActivity.this, CatalogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
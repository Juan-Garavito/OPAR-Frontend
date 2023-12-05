package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Modelos.Arrendatario;
import Modelos.Ciudadano;
import Storage.CiudadanoStorage;

public class ArrendatarioActivity extends AppCompatActivity {

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
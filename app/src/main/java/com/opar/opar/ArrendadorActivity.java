package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import Modelos.Ciudadano;
import Modelos.Inmueble;
import Adapter.InmuebleAdapterArrendador;
import Storage.CiudadanoStorage;

public class ArrendadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrendador);

        Ciudadano ciudadano = CiudadanoStorage.getCiudadano(getApplicationContext());
        String numeroDoc = ciudadano.getNumeroDocumento();

        // Crear una cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.14.56.103:2020/api/inmuebles/arrendador/"+numeroDoc;

        // Solicitar un string de respuesta desde la URL proporcionada.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Arrendador-backend", "Respuesta: " + response);

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Inmueble>>(){}.getType();
                        List<Inmueble> inmuebles = gson.fromJson(response, listType);

                        // Configurar el RecyclerView
                        RecyclerView recyclerView = findViewById(R.id.RecyclerArrendador);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ArrendadorActivity.this));
                        recyclerView.setAdapter(new InmuebleAdapterArrendador(inmuebles));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Aquí manejas el error
                Log.d("Arrendador-backend", "Error: " + error.toString());
            }
        });

        // Agregar la solicitud a la cola de solicitudes
        queue.add(stringRequest);

        Button agregarInmueble = findViewById(R.id.agInmueble);

        agregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArrendadorActivity.this, AgregarInmuebleActivity.class);
                startActivity(intent);
            }
        });

    }
}

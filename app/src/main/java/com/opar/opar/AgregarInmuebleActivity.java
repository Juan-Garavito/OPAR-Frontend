package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import Modelos.BarrioDTO;
import okhttp3.*;

public class AgregarInmuebleActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_inmueble);

        spinner = (Spinner) findViewById(R.id.spinner);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://opar-backend-production.up.railway.app/api/barrios/list")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<BarrioDTO>>(){}.getType();
                    List<BarrioDTO> barrios = gson.fromJson(jsonData, listType);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<BarrioDTO> adapter = new ArrayAdapter<BarrioDTO>(AgregarInmuebleActivity.this, android.R.layout.simple_spinner_item, barrios){
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = super.getView(position, convertView, parent);
                                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                    textView.setText(barrios.get(position).getBarrio());
                                    return view;
                                }

                                @Override
                                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                    View view = super.getDropDownView(position, convertView, parent);
                                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                    textView.setText(barrios.get(position).getBarrio());
                                    return view;
                                }
                            };
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                        }
                    });
                }
            }
        });

        Button agregarImagenes = findViewById(R.id.btnAgImg);

        agregarImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarInmuebleActivity.this, AgregarImagenesActivity.class);
                startActivity(intent);
            }
        });
    }
}
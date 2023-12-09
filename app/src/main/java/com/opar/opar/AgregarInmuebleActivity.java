package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import Modelos.BarrioDTO;
import Modelos.Ciudadano;
import Storage.CiudadanoStorage;
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

                //Obtenemos lo parametros para luego crear un json

                //Tipo de inmueble
                HashMap<String, Integer> mapTipoInmueble = new HashMap<>();
                mapTipoInmueble.put("Casa", 1);
                mapTipoInmueble.put("Apartamento", 2);
                mapTipoInmueble.put("ApartaEstudio", 3);
                mapTipoInmueble.put("Habitación", 4);

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupTipoInmueble);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                String tipoInmueble = radioButton.getText().toString();
                Integer idTipoInmueble = mapTipoInmueble.get(tipoInmueble);

                //Direccion
                TextInputEditText textInputEditText = (TextInputEditText) findViewById(R.id.textInputEditText);
                String direccion = textInputEditText.getText().toString();

                //Barrio
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                BarrioDTO barrioSeleccionado = (BarrioDTO) spinner.getSelectedItem();
                Integer idBarrio = barrioSeleccionado.getIdBarrio();

                //Servicios publicos
                HashMap<String, Integer> mapServiciosPublicos = new HashMap<>();
                mapServiciosPublicos.put("Si", 1);
                mapServiciosPublicos.put("No", 0);

                RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup);
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);
                String servicios = radioButton2.getText().toString();
                Integer tieneServiciosPublicos = mapServiciosPublicos.get(servicios);

                //Cantidad de habitaciones
                EditText editTextCH = (EditText) findViewById(R.id.editTextNumber);
                String textCH = editTextCH.getText().toString();
                Integer cantidadHabitaciones = Integer.parseInt(textCH);

                //Área del inmueble
                EditText editTextAI = (EditText) findViewById(R.id.editTextNumberDecimal);
                String textAI = editTextAI.getText().toString();
                float area = Float.parseFloat(textAI);

                //Descripcion
                EditText editTextDes = (EditText) findViewById(R.id.editTextTextMultiLine);
                String descripcion = editTextDes.getText().toString();

                //Área del inmueble
                EditText editTextPI = (EditText) findViewById(R.id.editTextNumberDecimal2);
                String textPI = editTextPI.getText().toString();
                float precio = Float.parseFloat(textPI);

                //Documento del arrendador
                Ciudadano ciudadano = CiudadanoStorage.getCiudadano(getApplicationContext());
                String numeroDocumento = ciudadano.getNumeroDocumento();

                // Creamos el json con los datos
                JSONObject json = new JSONObject();
                try {
                    json.put("idTipoInmueble", idTipoInmueble);
                    json.put("idBarrio", idBarrio);
                    json.put("numeroDocumento", numeroDocumento);
                    json.put("cantidadHabitaciones", cantidadHabitaciones);
                    json.put("serviciosPublicos", tieneServiciosPublicos);
                    json.put("area", area);
                    json.put("descripcion", descripcion);
                    json.put("direccion", direccion);
                    json.put("precio", precio);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Enviamos la solicitud POST
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

                Request request = new Request.Builder()
                        .url("https://opar-backend-production.up.railway.app/api/inmuebles/agregar")
                        .post(body)
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
                            try {
                                JSONObject Jobject = new JSONObject(jsonData);
                                String idInmueble = Jobject.getString("idInmueble"); // Aquí es donde se obtiene el idInmueble

                                // Ahora puedes pasar este ID a la siguiente actividad
                                Intent intent = new Intent(AgregarInmuebleActivity.this, AgregarImagenesActivity.class);
                                intent.putExtra("ID_INMUEBLE", idInmueble);
                                startActivity(intent);
                                Log.d("pruba-envio-id", idInmueble);
                            } catch (JSONException e) {
                                Log.e("JSON Error", "Error al obtener la clave 'idInmueble': " + e.getMessage());
                            }
                        } else {
                            // Hubo un error.
                        }
                    }
                });
            }
        });
    }
}
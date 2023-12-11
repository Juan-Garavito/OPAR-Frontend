package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Modelos.Arrendatario;
import Modelos.BarrioDTO;
import Modelos.Ciudadano;
import Peticiones.ApiCiudadano;
import Peticiones.ApiCliente;
import Peticiones.ApiOpinion;
import Storage.CiudadanoStorage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        Ciudadano ciudadano = CiudadanoStorage.getCiudadano(getApplicationContext());
        Arrendatario arrendatario = new Arrendatario(ciudadano.getNumeroDocumento(),
                ciudadano.getNombreCompleto(),
                ciudadano.getUsuario(),
                ciudadano.getContraseña(),
                ciudadano.getTelefono());


        TextView textView =  findViewById(R.id.idTituloCaracteristicas);
        SpannableString mitextoU = new SpannableString("CARACTERISTICAS");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);

        TextView textView2 =  findViewById(R.id.idServiciostext);
        SpannableString mitextoU2 = new SpannableString("Servicios");
        mitextoU2.setSpan(new UnderlineSpan(), 0, mitextoU2.length(), 0);
        textView2.setText(mitextoU2);

        TextView textView3 =  findViewById(R.id.idBarriotext);
        SpannableString mitextoU3 = new SpannableString("Barrio");
        mitextoU3.setSpan(new UnderlineSpan(), 0, mitextoU3.length(), 0);
        textView3.setText(mitextoU3);

        TextView textView4 =  findViewById(R.id.idTipotext);
        SpannableString mitextoU4 = new SpannableString("Tipo");
        mitextoU4.setSpan(new UnderlineSpan(), 0, mitextoU4.length(), 0);
        textView4.setText(mitextoU4);

        List<String> barrios = new ArrayList<String>();
        barrios.add("");

        List<String> tipos = new ArrayList<String>();
        tipos.add("");
        tipos.add("Apartamento");
        tipos.add("Casa");
        tipos.add("Apartaestudio");
        tipos.add("Habitacion");

        Call<List<BarrioDTO>> call = ApiCliente.GetCliente().create(ApiOpinion.class).ObtenerBarrios();

        call.enqueue(new Callback<List<BarrioDTO>>() {
            @Override
            public void onResponse(Call<List<BarrioDTO>> call, Response<List<BarrioDTO>> response) {
                List<BarrioDTO> barriosDTO = response.body();
                for(BarrioDTO barrio : barriosDTO){
                    barrios.add(barrio.getBarrio());
                }
            }

            @Override
            public void onFailure(Call<List<BarrioDTO>> call, Throwable t) {

            }
        });

        Spinner barrio = findViewById(R.id.idBarrio);
        Spinner tipo = findViewById(R.id.idTipo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner,barrios);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.item_spinner,tipos);
        barrio.setAdapter(adapter);
        tipo.setAdapter(adapter2);



        Button btnFiltrar = findViewById(R.id.idBtnFiltrar);
        TextView precio = findViewById(R.id.idPrecio);
        TextView habitaciones = findViewById(R.id.idHabitaciones);
        RadioButton servicioSi = findViewById(R.id.idServiciosSi);
        RadioButton servicioNo = findViewById(R.id.idServiciosNo);
        TextView area = findViewById(R.id.idArea);




        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> atributos = new ArrayList<>();
                List<Object> valor = new ArrayList<>();

                if (precio.getText().toString().length() > 0 ){
                    atributos.add("Precio");
                    valor.add(Float.parseFloat(precio.getText().toString()));
                }

                if (habitaciones.getText().toString().length() > 0){
                    atributos.add("Habitaciones");
                    valor.add(Integer.parseInt(habitaciones.getText().toString()));
                }

                if (area.getText().toString().length() > 0){
                    atributos.add("Area");
                    valor.add(Float.parseFloat(area.getText().toString()));
                }

                if(servicioSi.isChecked()){
                    atributos.add("Servicios");
                    valor.add(1);
                }

                if(servicioNo.isChecked()){
                    atributos.add("Servicios");
                    valor.add(0);
                }

                if(barrio.getSelectedItem().toString().length() > 0){
                    atributos.add("Barrio");
                    valor.add(barrio.getSelectedItem().toString());
                }

                if(tipo.getSelectedItem().toString().length() > 0){
                    atributos.add("Tipo");
                    valor.add(tipo.getSelectedItem().toString());
                }


                Intent intent = new Intent(FiltroActivity.this, CatalogoActivity.class);
                if(atributos.size() > 0){
                    Log.e("Precio",valor.toString());
                    HashMap<String, Object> filtros = arrendatario.filtrarInmuebles(atributos, valor);
                    Log.e("Precio",filtros.toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("filtros", (Serializable) filtros);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
            }
        });

    }
}
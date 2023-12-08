package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import Adapter.InmuebleAdapter;
import Adapter.OnItemClickListener;
import Modelos.Catalogo;
import Modelos.Inmueble;
import Peticiones.ApiCliente;
import Peticiones.ApiInmueble;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogoActivity extends AppCompatActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        TextView textView =  findViewById(R.id.idTituloCatalogo);
        SpannableString mitextoU = new SpannableString("CATALOGO");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);

        HashMap<String, Object>  filtros;
        if(getIntent().getExtras() == null){
            Log.e("filtro", "getIntent().getExtras() == null");
            filtros = null;
        }else{
            Bundle filtrosEnviados = getIntent().getExtras();
            filtros = (HashMap<String, Object>) filtrosEnviados.getSerializable("filtros");
        }


        Call<List<Inmueble>> call = ApiCliente.GetCliente().create(ApiInmueble.class).ObtenerInmuebles();

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.body() != null){
                    Catalogo.setInmuebles(response.body());
                    Catalogo.setInmueblesFiltrados(response.body());
                    List<Inmueble>  inmuebles = null;
                    if(filtros == null){
                         Log.e("filtro","no hay filtro");
                         inmuebles = Catalogo.catalogo.ObtenerInmuebles();
                    }else{
                         inmuebles = Catalogo.catalogo.filtrar(filtros);
                    }

                    initRecyclerView(inmuebles);
                    if(inmuebles != null){
                        for (Inmueble inmueble : inmuebles){
                            Log.e("Inmueble", inmueble.getIdTipoInmueble().toString());
                        }
                    }else{
                        Log.e("Inmueble", "Problemas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.e("ErrorInmueble", "No conexion");
            }
        });
    }


    private void initRecyclerView(List<Inmueble> inmuebles){
        RecyclerView recyclerView = findViewById(R.id.recyclerCatalogo);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new InmuebleAdapter(inmuebles, this));
    }


    @Override
    public void onItemClick(Inmueble inmueble) {
        Log.e("Seleccionado", inmueble.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("inmueble", inmueble);
        Intent intent = new Intent(CatalogoActivity.this, VisorActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
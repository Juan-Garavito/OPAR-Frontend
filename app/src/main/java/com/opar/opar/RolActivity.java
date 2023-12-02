package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Modelos.Arrendador;
import Modelos.Arrendatario;
import Modelos.Ciudadano;
import ViewModel.CiudadanoViewModel;

public class RolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);

        CiudadanoViewModel ciudadanoViewModel = new ViewModelProvider(RolActivity.this).get(CiudadanoViewModel.class);
        Ciudadano ciudadano = ciudadanoViewModel.getCiudadano();
        Button btnArrendador = findViewById(R.id.idArrendador);
        Button btnArrendatario = findViewById(R.id.idArrendatario);

        btnArrendador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrendador arrendador = (Arrendador) ciudadano;
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrendador", arrendador);
                Intent intent = new Intent(RolActivity.this, ArrendadorActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnArrendatario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrendatario arrendatario = (Arrendatario) ciudadano;
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrendatario", arrendatario);
                Intent intent = new Intent(RolActivity.this, ArrendatarioActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
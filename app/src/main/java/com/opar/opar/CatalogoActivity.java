package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import Modelos.Arrendatario;
import Modelos.Inmueble;

public class CatalogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        Bundle inmueblesEnviados = getIntent().getExtras();
        List<Inmueble> inmuebles = (List<Inmueble>) inmueblesEnviados.getSerializable("inmuebles");


    }
}
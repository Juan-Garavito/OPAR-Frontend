package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import Modelos.Arrendatario;

public class ArrendatarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrendatario);

        Bundle arrendatarioEnviado = getIntent().getExtras();
        Arrendatario arrendatario = (Arrendatario) arrendatarioEnviado.getSerializable("arrendatario");

    }



}
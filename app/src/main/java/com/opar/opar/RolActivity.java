package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;



public class RolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);


        Button btnArrendador = findViewById(R.id.idArrendador);
        Button btnArrendatario = findViewById(R.id.idArrendatario);

        btnArrendador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnArrendatario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RolActivity.this, ArrendatarioActivity.class);
                startActivity(intent);
            }
        });
    }

}
package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BuscarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        GridLayout ver = findViewById(R.id.idVerCatalogo);
        GridLayout filtrar = findViewById(R.id.idFiltrar);

        TextView textView =  findViewById(R.id.idTituloBuscarHoy);
        SpannableString mitextoU = new SpannableString("¿QUE BUSCAS HOY?");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuscarActivity.this, CatalogoActivity.class);
                startActivity(intent);
            }
        });

        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuscarActivity.this, FiltroActivity.class);
                startActivity(intent);
            }
        });
    }


}
package com.opar.opar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Modelos.LoginRespuesta;
import Peticiones.ApiCiudadano;
import Peticiones.ApiCliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIngresar = findViewById(R.id.idIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {

            EditText textCorreo = findViewById(R.id.idCorreo);
            EditText textContraseña = findViewById(R.id.idContraseña);

            @Override
            public void onClick(View view) {
                String correo = textCorreo.getText().toString().trim();
                String contraseña = textContraseña.getText().toString().trim();
                LoginRespuesta login = new LoginRespuesta(correo, contraseña);
                Call<Integer> call = ApiCliente.GetCliente().create(ApiCiudadano.class).Login(login);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful() && response.body() != null){
                            Toast.makeText(MainActivity.this, response.body().toString(),Toast.LENGTH_LONG).show();
                            return;
                        }

                        Toast.makeText(MainActivity.this, "No Tenemos Nada",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Fallo",Toast.LENGTH_LONG).show();
                        Log.e("Error 1 ", "Esta fallando ",t);
                    }
                });
            }
        });


    }
}
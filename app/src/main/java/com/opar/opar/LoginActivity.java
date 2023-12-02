package com.opar.opar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import Modelos.Ciudadano;
import Modelos.LoginCiudadano;
import Peticiones.ApiCiudadano;
import Peticiones.ApiCliente;
import ViewModel.CiudadanoViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIngresar = findViewById(R.id.idIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {

            EditText textUsuario = findViewById(R.id.idUsuario);
            EditText textContraseña = findViewById(R.id.idContraseña);

            @Override
            public void onClick(View view) {
                String usuario = textUsuario.getText().toString().trim();
                String contraseña = textContraseña.getText().toString().trim();

                LoginCiudadano login = new LoginCiudadano(usuario, contraseña);
                Call<Ciudadano> call = ApiCliente.GetCliente().create(ApiCiudadano.class).Login(login);

                call.enqueue(new Callback<Ciudadano>() {
                    @Override
                    public void onResponse(Call<Ciudadano> call, Response<Ciudadano> response) {
                        if(response.body() != null){
                            Ciudadano ciudadano = response.body();
                            CiudadanoViewModel ciudadanoViewModel = new ViewModelProvider(LoginActivity.this).get(CiudadanoViewModel.class);
                            ciudadanoViewModel.setCiudadano(ciudadano);
                            Intent intent = new Intent(LoginActivity.this, RolActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Bienvenido",Toast.LENGTH_LONG).show();
                            return;
                        }

                        Toast.makeText(LoginActivity.this, "Revisa el usuario o contraseña",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Ciudadano> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Fallo",Toast.LENGTH_LONG).show();
                        Log.e("Error 1 ", "Esta fallando ",t);
                    }
                });
            }
        });


    }
}
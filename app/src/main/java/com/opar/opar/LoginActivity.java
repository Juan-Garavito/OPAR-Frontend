package com.opar.opar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import Modelos.Ciudadano;
import Modelos.LoginCiudadano;
import Peticiones.ApiCiudadano;
import Peticiones.ApiCliente;
import Storage.CiudadanoStorage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    CheckBox mostrarContraseña = null;
    EditText textUsuario = null;
    EditText textContraseña = null;
    Button btnIngresar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostrarContraseña = findViewById(R.id.idMostrar);
        textUsuario = findViewById(R.id.idUsuario);
        textContraseña = findViewById(R.id.idContraseña);
        btnIngresar = findViewById(R.id.idIngresar);

       mostrarContraseña.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   textContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
               }else{
                   textContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
               }
           }
       });



        btnIngresar.setOnClickListener(new View.OnClickListener() {
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
                            CiudadanoStorage.saveCiudadano(getApplicationContext(), ciudadano);
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
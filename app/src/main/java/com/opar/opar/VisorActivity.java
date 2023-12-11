package com.opar.opar;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import Adapter.ImagenAdapter;
import Adapter.ImagenCatalogoAdapter;
import Adapter.OpinionAdapter;
import Modelos.Ciudadano;
import Modelos.Inmueble;
import Modelos.Opinion;
import Modelos.OpinionEditDTO;
import Peticiones.ApiCliente;
import Peticiones.ApiOpinion;
import Storage.CiudadanoStorage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);

        Ciudadano ciudadano = CiudadanoStorage.getCiudadano(this);

        Bundle inmueblesEnviados = getIntent().getExtras();
        Inmueble inmueble = (Inmueble) inmueblesEnviados.getSerializable("inmueble");



        Button llamar = findViewById(R.id.idBtnLlamar);
        Button opinar = findViewById(R.id.idBtnOpinar);
        Button editar = findViewById(R.id.idBtnEditarOpinion);
        Button eliminar = findViewById(R.id.idBtnEliminarOpinion);


        obtenerOpinionesInmueble(inmueble, ciudadano);

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + inmueble.getNumeroDocumento().getTelefono().toString()));
                startActivity(intent);
            }
        });

        opinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarOpinion(inmueble, ciudadano);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarOpinion(inmueble, ciudadano );
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpinionEditDTO opinionEdit = new OpinionEditDTO(ciudadano.getNumeroDocumento().toString(), inmueble.getIdInmueble());
                Call<Opinion> call = ApiCliente.GetCliente().create(ApiOpinion.class).BuscarPorOpinion(opinionEdit);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.card_eliminando_opinion, null);
                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                call.enqueue(new Callback<Opinion>() {
                    @Override
                    public void onResponse(Call<Opinion> call, Response<Opinion> response) {
                        Opinion opinionEliminar = response.body();
                        opinionEliminar.setIdInmueble(inmueble.getIdInmueble());
                        eliminarOpionion(inmueble, ciudadano, opinionEliminar, dialog);
                    }

                    @Override
                    public void onFailure(Call<Opinion> call, Throwable t) {

                    }
                });


            }
        });

        Log.e("Visor", inmueble.toString());
    }


    private void initRecyclerView(List<Opinion> opiniones) {
        RecyclerView recyclerView = findViewById(R.id.idOpinionesInmueble);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new OpinionAdapter(opiniones));
    }

    private void agregarOpinion(Inmueble inmueble, Ciudadano ciudadano) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.card_opinion_two, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAgregar = view.findViewById(R.id.idBtnAgregarAgregar);
        Button btnCancelar = view.findViewById(R.id.idBtnAgregarCancelar);
        TextView usuario = view.findViewById(R.id.idAgregarUsuario);
        RatingBar calificacion = view.findViewById(R.id.idAgregarCalificacion);
        TextView comentario = view.findViewById(R.id.idAgregarComentario);

        usuario.setText(ciudadano.getUsuario());
        Log.e("InmuebleOpinion", inmueble.toString());
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calificacion.getRating() < 0 || calificacion.getRating() > 5) {
                    Toast.makeText(VisorActivity.this, "La calificacion debe estar entre 0 y 5 ", Toast.LENGTH_SHORT).show();
                    return;
                }

                dialog.dismiss();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.card_eliminando_opinion, null);
                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                TextView carga = view.findViewById(R.id.idTextCarga);
                carga.setText("Agregando tu Comentario");

                Opinion opinion = new Opinion(inmueble.getIdInmueble(), ciudadano, comentario.getText().toString(), calificacion.getRating());
                Call<Opinion> call = ApiCliente.GetCliente().create(ApiOpinion.class).AgregarOpinion(opinion);
                call.enqueue(new Callback<Opinion>() {
                    @Override
                    public void onResponse(Call<Opinion> call, Response<Opinion> response) {
                        if (response.body() != null) {
                            Log.e("OpinionCreada", response.body().toString());
                            Toast.makeText(dialog.getContext(), "Opinion Agregada", Toast.LENGTH_SHORT).show();
                            obtenerOpinionesInmueble(inmueble, ciudadano);
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<Opinion> call, Throwable t) {

                        Toast.makeText(dialog.getContext(), "Ocurrio un problema", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void obtenerOpinionesInmueble(Inmueble inmueble, Ciudadano ciudadano) {
        Call<List<Opinion>> call = ApiCliente.GetCliente().create(ApiOpinion.class).BuscarPorInmueble(inmueble.getIdInmueble());

        Button opinar = findViewById(R.id.idBtnOpinar);
        Button editar = findViewById(R.id.idBtnEditarOpinion);
        Button eliminar = findViewById(R.id.idBtnEliminarOpinion);
        Button llamar =  findViewById(R.id.idBtnLlamar);
        final float[] calificaion = {0};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.card_eliminando_opinion, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView carga = view.findViewById(R.id.idTextCarga);
        carga.setText("Obteniendo los  Datos");

        call.enqueue(new Callback<List<Opinion>>() {
            @Override
            public void onResponse(Call<List<Opinion>> call, Response<List<Opinion>> response) {
                if (response.body() != null) {
                    List<Opinion> opiniones = response.body();
                    initRecyclerView(opiniones);
                    for(Opinion opinion : opiniones){
                        if(opinion.getNumeroDocumento().getNumeroDocumento().equals(ciudadano.getNumeroDocumento())){
                            opinar.setVisibility(View.GONE);
                            editar.setVisibility(View.VISIBLE);
                            eliminar.setVisibility(View.VISIBLE);
                        }

                        calificaion[0] = calificaion[0] + opinion.getCalificacion();
                    }

                    if(opiniones.size() > 0){
                        calificaion[0] = calificaion[0]/opiniones.size();
                    }


                    if(editar.getVisibility() == View.GONE){
                        opinar.setVisibility(View.VISIBLE);
                    }

                    cargandoDatos(inmueble, calificaion[0]);
                    llamar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }else{
                    cargandoDatos(inmueble, 0);
                    dialog.dismiss();
                }
                Log.e("Opinon", "No llego nada");
            }

            @Override
            public void onFailure(Call<List<Opinion>> call, Throwable t) {
                Log.e("Opinon", "Error de conexion");
            }
        });

    }

    private void editarOpinion(Inmueble inmueble, Ciudadano ciudadano){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.card_opinion_two, null);
        builder.setView(view);



        Button btnAgregar = view.findViewById(R.id.idBtnAgregarAgregar);
        Button btnCancelar = view.findViewById(R.id.idBtnAgregarCancelar);
        TextView usuario = view.findViewById(R.id.idAgregarUsuario);
        RatingBar calificacion = view.findViewById(R.id.idAgregarCalificacion);
        TextView comentario = view.findViewById(R.id.idAgregarComentario);

        final AlertDialog dialog = builder.create();
        final Integer[] idOpinion = new Integer[1];

        OpinionEditDTO opinionEdit = new OpinionEditDTO(ciudadano.getNumeroDocumento().toString(), inmueble.getIdInmueble());
        Call<Opinion> call = ApiCliente.GetCliente().create(ApiOpinion.class).BuscarPorOpinion(opinionEdit);

        call.enqueue(new Callback<Opinion>() {
            @Override
            public void onResponse(Call<Opinion> call, Response<Opinion> response) {
                obtenerOpinionesInmueble(inmueble, ciudadano);
                usuario.setText(ciudadano.getUsuario());
                comentario.setText(response.body().getComentario());
                calificacion.setRating(response.body().getCalificacion());
                idOpinion[0] = response.body().getIdOpinion();
                Log.e("InmuebleOpinion", inmueble.toString());
                dialog.show();
            }

            @Override
            public void onFailure(Call<Opinion> call, Throwable t) {

            }
        });


        
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calificacion.getRating() < 0 || calificacion.getRating() > 5) {
                    Toast.makeText(VisorActivity.this, "La calificacion debe estar entre 0 y 5 ", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.card_eliminando_opinion, null);
                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                TextView carga = view.findViewById(R.id.idTextCarga);
                carga.setText("Editando tu Opinion");

                Opinion opinion = new Opinion(inmueble.getIdInmueble(), ciudadano, comentario.getText().toString(), calificacion.getRating());
                opinion.setIdOpinion(idOpinion[0]);

                Call<Opinion> call = ApiCliente.GetCliente().create(ApiOpinion.class).EditarOpinion(opinion);
                call.enqueue(new Callback<Opinion>() {
                    @Override
                    public void onResponse(Call<Opinion> call, Response<Opinion> response) {
                        if (response.body() != null) {
                            Log.e("OpinionEditada", response.body().toString());
                            Toast.makeText(dialog.getContext(), "Opinion Editada", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            obtenerOpinionesInmueble(inmueble, ciudadano);
                        }

                    }

                    @Override
                    public void onFailure(Call<Opinion> call, Throwable t) {

                        Toast.makeText(dialog.getContext(), "Ocurrio un problema", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void eliminarOpionion(Inmueble inmueble, Ciudadano ciudadano, Opinion opinion, AlertDialog dialog){
        Button editar = findViewById(R.id.idBtnEditarOpinion);
        Button eliminar = findViewById(R.id.idBtnEliminarOpinion);

        Call<Opinion> call = ApiCliente.GetCliente().create(ApiOpinion.class).EliminarOpinion(opinion.getIdOpinion());

        call.enqueue(new Callback<Opinion>() {
            @Override
            public void onResponse(Call<Opinion> call, Response<Opinion> response) {
                editar.setVisibility(View.GONE);
                eliminar.setVisibility(View.GONE);
                obtenerOpinionesInmueble(inmueble, ciudadano);
                Log.e("Eliminar opinon", response.body().toString());
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Opinion> call, Throwable t) {

            }
        });
    }

    private void cargandoDatos(Inmueble inmueble, float cali){
        TextView textView = findViewById(R.id.idTituloDireccion);
        SpannableString mitextoU = new SpannableString(inmueble.getDireccion());
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);


        ViewPager2 imagenes = findViewById(R.id.idBuscarInmueble);
        imagenes.setAdapter(new ImagenCatalogoAdapter(inmueble.getImagenes()));

        TextView barrio = findViewById(R.id.idBarrioInmueble);
        barrio.setText(inmueble.getIdBarrio().getBarrio());

        TextView precio = findViewById(R.id.idPrecioInmueble);
        precio.setText("$" + inmueble.getPrecio());

        TextView descripcion = findViewById(R.id.idDescripcionInmueble);
        descripcion.setText(String.valueOf(inmueble.getDescripcion()));

        TextView usuario = findViewById(R.id.idUsuarioInmueble);
        usuario.setText(inmueble.getNumeroDocumento().getUsuario());

        TextView telefono = findViewById(R.id.idTelefonoInmueble);
        telefono.setText(inmueble.getNumeroDocumento().getTelefono());

        RatingBar calificacion = findViewById(R.id.idCalificacionInmueble);
        calificacion.setRating(cali);
    }
}
package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.content.ClipData;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class AgregarImagenesActivity extends AppCompatActivity {

    private static final int PICK_IMAGES = 1;
    private List<Uri> imageUris = new ArrayList<>();
    private String idInmueble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_imagenes);

        Intent intent = getIntent();
        idInmueble = intent.getStringExtra("ID_INMUEBLE");

        initConfig();

        //Para acceder a la galeria

        Button abrir = findViewById(R.id.btnAbrirGaleria);
        abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGES);
            }
        });

        Button publicarInmueble = findViewById(R.id.btnPublicarInmueble);

        publicarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Uri imageUri : imageUris) {
                    MediaManager.get().upload(imageUri).callback(new UploadCallback() {
                        @Override
                        public void onStart(String requestId) {
                            // Puedes manejar el inicio de la carga aquí
                        }

                        @Override
                        public void onProgress(String requestId, long bytes, long totalBytes) {
                            // Puedes manejar el progreso de la carga aquí
                        }

                        @Override
                        public void onSuccess(String requestId, Map resultData) {
                            Log.e("Subido", resultData.get("url").toString());
                            String url = resultData.get("url").toString();
                            int inmueble = Integer.parseInt(idInmueble);
                            agregarImagen(url, inmueble);
                        }

                        @Override
                        public void onError(String requestId, ErrorInfo error) {
                            Toast.makeText(AgregarImagenesActivity.this, "Ocurrió un error " + error.getDescription(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onReschedule(String requestId, ErrorInfo error) {
                            Toast.makeText(AgregarImagenesActivity.this, "Ocurrió un error " + error.getDescription(), Toast.LENGTH_SHORT).show();
                        }
                    }).dispatch();
                }
                Toast.makeText(AgregarImagenesActivity.this, "Inmueble publicado", Toast.LENGTH_SHORT).show();

                // Nos dirgimos  a ArrendadorActivity
                Intent intent = new Intent(AgregarImagenesActivity.this, RolActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dfeegbmq0");
        config.put("api_key", "422432522231969");
        config.put("api_secret", "nfxJZbJ315KQ3MWbbbn_EOZzWtc");
        config.put("secure", "true"); // Cambiado a String
        MediaManager.init(this, config);
    }

    private void agregarImagen(String url, int inmueble) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String apiUrl = "https://opar-backend-production.up.railway.app/api/imagenes/agregar";

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("url", url);
            parameters.put("inmueble", inmueble);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, apiUrl, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        queue.add(jsonRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGES) {
            if (resultCode == RESULT_OK) {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    Log.d("DEBUG1", "Número de imágenes seleccionadas: " + mClipData.getItemCount());
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        Uri imageUri = mClipData.getItemAt(i).getUri();
                        imageUris.add(imageUri);
                    }
                } else if (data.getData() != null) {
                    Uri imageUri = data.getData();
                    imageUris.add(imageUri);
                }
            }
        }
    }
}

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Dropbox.DropboxUploader;

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

        //Para acceder a la galeria

        ImageView imageView = findViewById(R.id.imageView12);
        imageView.setOnClickListener(new View.OnClickListener() {
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
                    String mimeType = getContentResolver().getType(imageUri);
                    String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                    String path = "/" + imageUri.getLastPathSegment() + "." + extension;
                    Log.d("DEBUG2", "Subiendo imagen: " + imageUri.toString() + " a Dropbox: " + path);
                    new DropboxUploader(AgregarImagenesActivity.this) {
                        @Override
                        protected void onPostExecute(String url) {
                            // Después de subir la imagen a Dropbox, haz la solicitud HTTP POST a tu API
                            int inmueble = Integer.parseInt(idInmueble);
                            agregarImagen(url, inmueble);


                        }
                    }.execute(path, imageUri.toString());
                }
                Toast.makeText(AgregarImagenesActivity.this, "Inmueble publicado", Toast.LENGTH_SHORT).show();

                // Nos dirgimos  a ArrendadorActivity
                Intent intent = new Intent(AgregarImagenesActivity.this, RolActivity.class);
                startActivity(intent);
            }
        });
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

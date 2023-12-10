package com.opar.opar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.ImagenUrlAdapter;

public class RegistrarseActivity extends AppCompatActivity {

    private List<Uri> imageUrls;
    private Button subir;
    private Button subir2;
    ViewPager2 imagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        imagenes = findViewById(R.id.idMostarInmueble);

        ConstraintLayout la = findViewById(R.id.mostrarImagenes);

        subir = findViewById(R.id.idSubir);
        subir2 = findViewById(R.id.idSubir2);

        initConfig();

        imageUrls = new ArrayList<>();

        subir.setOnClickListener(v -> uploadImages());

        subir2.setOnClickListener(v -> requestPermissions());
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(RegistrarseActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            selectItems();
        } else {
            ActivityCompat.requestPermissions(RegistrarseActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }

    private void initConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dfeegbmq0");
        config.put("api_key", "422432522231969");
        config.put("api_secret", "nfxJZbJ315KQ3MWbbbn_EOZzWtc");
        config.put("secure", "true"); // Cambiado a String
        MediaManager.init(this, config);
    }

    private void selectItems() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        someActivityResultLauncher.launch(intent);
    }

    private void uploadImages() {
        for (Uri imageUrl : imageUrls) {
            MediaManager.get().upload(imageUrl).callback(new UploadCallback() {
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
                    Toast.makeText(RegistrarseActivity.this, "Imagen subida con éxito", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Toast.makeText(RegistrarseActivity.this, "Ocurrió un error " + error.getDescription(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {
                    Toast.makeText(RegistrarseActivity.this, "Ocurrió un error " + error.getDescription(), Toast.LENGTH_SHORT).show();
                }
            }).dispatch();
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri selectedImageUri = data.getData();
                        imageUrls.add(selectedImageUri);
                        imagenes.setAdapter(new ImagenUrlAdapter(imageUrls));
                    }
                }
            });
}

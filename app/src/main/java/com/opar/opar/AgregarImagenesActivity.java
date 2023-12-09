package com.opar.opar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.content.ClipData;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import Dropbox.DropboxUploader;

public class AgregarImagenesActivity extends AppCompatActivity {

    private static final int PICK_IMAGES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_imagenes);

        Intent intent = getIntent();
        String idInmueble = intent.getStringExtra("ID_INMUEBLE");

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
                        String mimeType = getContentResolver().getType(imageUri);
                        String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                        String path = "/" + imageUri.getLastPathSegment() + "." + extension;
                        Log.d("DEBUG2", "Subiendo imagen: " + imageUri.toString() + " a Dropbox: " + path);
                        new DropboxUploader(this).execute(path, imageUri.toString());
                    }
                } else if (data.getData() != null) {
                    Uri imageUri = data.getData();
                    String mimeType = getContentResolver().getType(imageUri);
                    String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                    String path = "/" + imageUri.getLastPathSegment() + "." + extension;
                    Log.d("DEBUG3", "Subiendo imagen: " + imageUri.toString() + " a Dropbox: " + path);
                    new DropboxUploader(this).execute(path, imageUri.toString());
                }
            }
        }
    }
}

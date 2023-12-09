package Dropbox;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import java.io.InputStream;

public class DropboxUploader extends AsyncTask<String, Void, Void> {

    private static final String ACCESS_TOKEN = "sl.BrZUBkSEQP2SPWtAjoH27gNrHyUdfN5dN7GBv5H_lcvwKrbsPD2ypJsMwu47S06lPg_aEr-ZtVfnML9l4PMZtiFZoF9VEeTYnpIpsmibwVHnhXaMIQtjZf6FJwfyCkX9NFzY13vJK-Icn24";
    private Context context;

    public DropboxUploader(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        String path = params[0];
        Uri uri = Uri.parse(params[1]);

        try {
            InputStream in = context.getContentResolver().openInputStream(uri);
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/inmuebles").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            client.files().uploadBuilder(path)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(in);
        } catch (Exception e) {
            Log.e("ERROR", "Error al subir la imagen a Dropbox", e);
        }

        return null;
    }
}

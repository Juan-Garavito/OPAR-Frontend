package Dropbox;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;

import java.io.InputStream;

public class DropboxUploader extends AsyncTask<String, Void, String> {

    private static final String ACCESS_TOKEN = "sl.BrfWas9K2-X1_TB8S1_LKmIRjgm2goWpmJsL0gVE9l8-ByKT6Q3x46Dlm5Fbl8JZ9x1sDiFG4e09hfFxYJRLGHl1CN3onH7LhZppj_2mBilVQR2wDbG8Db69aFh_zT8NsVscRSmp5Ap9ZgUifCSo";
    private Context context;

    public DropboxUploader(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String path = params[0];
        Uri uri = Uri.parse(params[1]);
        String url = "";

        try {
            InputStream in = context.getContentResolver().openInputStream(uri);
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/inmuebles").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            FileMetadata metadata = client.files().uploadBuilder(path)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(in);

            // Obtén la URL real del archivo en Dropbox
            SharedLinkMetadata slMetadata = client.sharing().createSharedLinkWithSettings(metadata.getPathLower());
            url = slMetadata.getUrl();
            url = url.replace("dl=0", "raw=1");
        } catch (Exception e) {
            Log.e("ERROR", "Error al subir la imagen a Dropbox", e);
        }

        Log.d("URL OBTENIDAs", "al subir la imagen a Dropbox" + url);

        return url;
    }
}

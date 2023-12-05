package Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.opar.opar.R;

import Modelos.Inmueble;

public class InmuebleViewHolder extends ViewHolder {

    public InmuebleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private ImageView vista = itemView.findViewById(R.id.imgInmueble);
    private TextView direccion = itemView.findViewById(R.id.direccionInmueble);

    public void Render(Inmueble inmueble){

        direccion.setText(inmueble.getDireccion());
    }
}

package Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opar.opar.CatalogoActivity;
import com.opar.opar.InmuebleArrendadorActivty;
import com.opar.opar.R;
import com.opar.opar.VisorActivity;

import java.util.List;

import Modelos.Imagen;
import Modelos.Inmueble;

public class ImagenVisorAdapter extends RecyclerView.Adapter<ImagenVisorAdapter.ImagenViewHolder> {

    private List<Inmueble> inmuebles;

    public ImagenVisorAdapter( List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_item, parent, false);
        return new ImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        Glide.with(holder.ivImagen.getContext())
                .load(inmueble.getImagenes().get(0).getUrl())
                .into(holder.ivImagen);


        holder.ivImagen.setOnClickListener(v -> {
            Log.e("Vista del Visor", inmueble.toString());
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            Intent intent = new Intent(v.getContext(), VisorActivity.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    static class ImagenViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagen;

        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
        }
    }
}

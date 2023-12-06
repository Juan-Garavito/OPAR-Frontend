package Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opar.opar.R;

import java.util.List;

import Modelos.Imagen;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ImagenViewHolder> {

    private List<Imagen> imagenes;


    public ImagenAdapter(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_item, parent, false);
        return new ImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) {
        Imagen url = imagenes.get(position);
        Log.d("imagen1423", "url" + url.getUrl());
        Glide.with(holder.ivImagen.getContext())
                .load(url.getUrl())
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }

    static class ImagenViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagen;

        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
        }
    }
}

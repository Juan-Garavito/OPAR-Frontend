package Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opar.opar.R;

import java.util.List;

public class ImagenUrlAdapter extends RecyclerView.Adapter<ImagenUrlAdapter.ImagenViewHolder> {

    private List<Uri> urls;


    public ImagenUrlAdapter(List<Uri> urls) {
        this.urls = urls;
    }

    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_item, parent, false);
        return new ImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) {
        Uri url = urls.get(position);
        Glide.with(holder.ivImagen.getContext())
                .load(url)
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    static class ImagenViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagen;

        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
        }
    }
}

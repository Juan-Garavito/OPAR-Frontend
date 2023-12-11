package Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.opar.opar.R;
import com.opar.opar.VisorActivity;
import com.opar.opar.VisorArrendadorActivity;

import Modelos.Inmueble;

import java.util.List;

public class InmuebleAdapterArrendador extends RecyclerView.Adapter<InmuebleAdapterArrendador.InmuebleViewHolder> {

    private List<Inmueble> inmuebles;

    public InmuebleAdapterArrendador(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        return new InmuebleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.tvDireccion.setText(inmueble.getDireccion());

        // Configurar el ViewPager2
        holder.viewPager.setAdapter(new ImagenAdapter(inmueble.getImagenes()));

        holder.itemView.setOnClickListener(v -> {
            Log.e("Vista del Visor", inmueble.toString());
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            Intent intent = new Intent(v.getContext(), VisorArrendadorActivity.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    static class InmuebleViewHolder extends RecyclerView.ViewHolder {

        ViewPager2 viewPager;
        TextView tvDireccion;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewPager2);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }
    }
}


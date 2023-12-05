package Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.opar.opar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import Modelos.Inmueble;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleViewHolder> {

    private List<Inmueble> inmuebles;
    private OnItemClickListener itemClickListener;

    public InmuebleAdapter(List<Inmueble> inmuebles, OnItemClickListener itemClickListener) {
        this.inmuebles = inmuebles;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new InmuebleViewHolder(layoutInflater.inflate(R.layout.card_inmueble, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.Render(inmueble);
        holder.itemView.setOnClickListener(v->{
            itemClickListener.onItemClick(inmueble);
        });


    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }
}

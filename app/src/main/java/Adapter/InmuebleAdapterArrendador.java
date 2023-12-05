package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.opar.opar.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new InmuebleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.tvDireccion.setText(inmueble.getDireccion());
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    static class InmuebleViewHolder extends RecyclerView.ViewHolder {

        TextView tvDireccion;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }
    }
}

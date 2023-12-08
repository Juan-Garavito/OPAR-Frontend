package Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.opar.opar.R;

import java.util.List;

import Modelos.Opinion;

public class OpinionAdapter extends RecyclerView.Adapter<OpinionViewHolder> {
    private List<Opinion> opiniones;

    public OpinionAdapter(List<Opinion> opiniones) {
        this.opiniones = opiniones;
    }

    @NonNull
    @Override
    public OpinionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new OpinionViewHolder(layoutInflater.inflate(R.layout.card_opinion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OpinionViewHolder holder, int position) {
        Opinion opinion = opiniones.get(position);
        holder.Render(opinion);
        }

    @Override
    public int getItemCount() {
        return opiniones.size();
    }
}

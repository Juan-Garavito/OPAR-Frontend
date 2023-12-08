package Adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.opar.opar.R;

import Modelos.Inmueble;
import Modelos.Opinion;

public class OpinionViewHolder extends ViewHolder {

    private RatingBar calificacion = itemView.findViewById(R.id.idCalificacionCard);
    private TextView comentario = itemView.findViewById(R.id.idComentarioCard);
    private TextView usuario = itemView.findViewById(R.id.idUsuarioCard);

    public OpinionViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void Render(Opinion opinion){
        calificacion.setRating(opinion.getCalificacion());
        comentario.setText(opinion.getComentario());
        usuario.setText(opinion.getNumeroDocumento().getUsuario());
    }
}

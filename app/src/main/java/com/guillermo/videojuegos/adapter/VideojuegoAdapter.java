package com.guillermo.videojuegos.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guillermo.videojuegos.R;
import com.guillermo.videojuegos.beans.Videojuego;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.view.ListaBaseFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class VideojuegoAdapter extends RecyclerView.Adapter<VideojuegoAdapter.JuegoViewHolder> {
    private final ArrayList<Videojuego> listaJuegos;
    private final Context context;

    public VideojuegoAdapter(ArrayList<Videojuego> juegos, Context context) {
        this.listaJuegos = juegos;
        this.context = context;
    }

    @NonNull
    @Override
    public JuegoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_videojuego, parent, false);

        return new JuegoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JuegoViewHolder holder, int position) {
        Videojuego videojuego = listaJuegos.get(position);
        holder.nombre.setText(videojuego.getName());
        Picasso.get().load(videojuego.getBackground_image()).into(holder.imagen);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListaBaseFragment.class);
                intent.putExtra("juego_id", videojuego.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    public static class JuegoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;

        public JuegoViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.imagenVideoJuego);
            nombre = v.findViewById(R.id.txtNombre);

        }
    }
}

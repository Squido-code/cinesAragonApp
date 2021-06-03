package com.guillermo.cinesAragonApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guillermo.cinesAragonApp.R;
import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.view.ListaBaseFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {
    private final ArrayList<Pelicula> listaPelicula;
    private final Context context;

    public PeliculaAdapter(ArrayList<Pelicula> juegos, Context context) {
        this.listaPelicula = juegos;
        this.context = context;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pelicula, parent, false);

        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = listaPelicula.get(position);
        holder.nombre.setText(pelicula.getTitulo());
        holder.precio.setText("Precio: " + pelicula.getPrecio() + " €");
        holder.sesiones.setText(pelicula.horariosToString());
        Picasso.get().load(pelicula.getCartel()).into(holder.imagen);
        holder.imagen.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListaBaseFragment.class);
            Bundle bundle = new Bundle();
            bundle.putString("nombre", pelicula.getTitulo());
            bundle.putString("sinopsis", pelicula.getSinopsis());
            bundle.putString("cartel", pelicula.getCartel());
            bundle.putString("duracion", pelicula.getDuracion());
            bundle.putString("fecha_Estreno", pelicula.getFecha_Estreno());
            bundle.putString("genero", pelicula.getGenero());
            bundle.putString("trailer", pelicula.getTrailer());
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPelicula.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;
        public TextView precio;
        public TextView sesiones;

        public PeliculaViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.imagenPelicula);
            nombre = v.findViewById(R.id.txtNombre);
            precio = v.findViewById(R.id.txtPrecio);
            sesiones = v.findViewById(R.id.txtSesiones);


        }
    }
}

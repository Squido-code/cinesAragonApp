package com.guillermo.cinesAragonApp.listaPeliculas.presenter;

import android.content.Context;
import android.util.Log;

import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.listaPeliculas.contract.ContratoListaPeliculas;
import com.guillermo.cinesAragonApp.listaPeliculas.model.ModelListaPeliculas;

import java.util.ArrayList;
import java.util.HashMap;

public class PresentadorListaPeliculas implements ContratoListaPeliculas.Presenter {
    private static final String TAG = PresentadorListaPeliculas.class.getSimpleName();
    private final ModelListaPeliculas modelListaVideojuegos;
    private final ContratoListaPeliculas.View vista;
    private String filtro;

    public PresentadorListaPeliculas(Context context, ContratoListaPeliculas.View vista) {
        this.vista = vista;
        modelListaVideojuegos = new ModelListaPeliculas(context);
    }

    @Override
    public void getPeliculas(Boolean isFiltrado) {
        Log.d(TAG, "[getPeliculas]");
        //creamos hasmap para connvertir el filtro en una id entendible para la API
        HashMap<String, String> filtroId = new HashMap<>();
        filtroId.put("acción", "1");
        filtroId.put("aventura", "2");
        filtroId.put("Terror", "3");
        filtroId.put("Ciencia ficcion", "4");
        if (isFiltrado) {
            Log.d(TAG, "[getPeliculas] isFiltrado");
            modelListaVideojuegos.getPeliculasfilterWS(new ContratoListaPeliculas.Model.OnLstJuegosListener() {
                @Override
                public void onResolve(ArrayList<Pelicula> juegos) {
                    Log.d(TAG, "[getJuegos] onResolve");
                    vista.success(juegos);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getJuegos] onResolve");
                    vista.error("Error al tratar los datos");
                }
            }, filtroId.get(filtro));
        } else {
            Log.d(TAG, "[getPeliculasWS]");
            modelListaVideojuegos.getPeliculasWS(new ContratoListaPeliculas.Model.OnLstJuegosListener() {

                @Override
                public void onResolve(ArrayList<Pelicula> juegos) {
                    Log.d(TAG, "[getPeliculasWS] onResolve");
                    vista.success(juegos);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getPeliculasWS] onResolve");
                    vista.error("Error al tratar los datos");
                }
            });
        }
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}

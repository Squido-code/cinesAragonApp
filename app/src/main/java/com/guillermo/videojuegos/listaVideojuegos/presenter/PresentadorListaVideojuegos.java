package com.guillermo.videojuegos.listaVideojuegos.presenter;

import android.content.Context;
import android.util.Log;

import com.guillermo.videojuegos.beans.Videojuego;
import com.guillermo.videojuegos.listaVideojuegos.contract.ContratoListaVideojuegos;
import com.guillermo.videojuegos.listaVideojuegos.model.ModelListaVideojuegos;

import java.util.ArrayList;
import java.util.HashMap;

public class PresentadorListaVideojuegos implements ContratoListaVideojuegos.Presenter {
    private static final String TAG = PresentadorListaVideojuegos.class.getSimpleName();
    private final ModelListaVideojuegos modelListaVideojuegos;
    private final ContratoListaVideojuegos.View vista;
    private String filtro;

    public PresentadorListaVideojuegos(Context context, ContratoListaVideojuegos.View vista) {
        this.vista = vista;
        modelListaVideojuegos = new ModelListaVideojuegos(context);
    }

    @Override
    public void getJuegos(Boolean isFiltrado) {
        Log.d(TAG, "[getJuegos]");
        //creamos hasmap para connvertir el filtro en una id entendible para la API
        HashMap<String, String> filtroId = new HashMap<>();
        filtroId.put("acción", "4");
        filtroId.put("aventura", "3");
        filtroId.put("RPG", "5");
        filtroId.put("Estrategia", "10");
        if (isFiltrado) {
            Log.d(TAG, "[getJuegos] isFiltrado");
            modelListaVideojuegos.getjuegosfilterWS(new ContratoListaVideojuegos.Model.OnLstJuegosListener() {
                @Override
                public void onResolve(ArrayList<Videojuego> juegos) {
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
            Log.d(TAG, "[getjuegosWS]");
            modelListaVideojuegos.getjuegosWS(new ContratoListaVideojuegos.Model.OnLstJuegosListener() {

                @Override
                public void onResolve(ArrayList<Videojuego> juegos) {
                    Log.d(TAG, "[getjuegosWS] onResolve");
                    vista.success(juegos);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getjuegosWS] onResolve");
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

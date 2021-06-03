package com.guillermo.cinesAragonApp.listaPeliculas.presenter;

import android.content.Context;
import android.util.Log;

import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.listaPeliculas.contract.ContratoListaPeliculas;
import com.guillermo.cinesAragonApp.listaPeliculas.model.ModelListaPeliculas;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;

@Data
public class PresentadorListaPeliculas implements ContratoListaPeliculas.Presenter {
    private static final String TAG = PresentadorListaPeliculas.class.getSimpleName();
    private final ModelListaPeliculas modelListaPeliculas;
    private final ContratoListaPeliculas.View vista;
    private String filtro;

    public PresentadorListaPeliculas(Context context, ContratoListaPeliculas.View vista) {
        this.vista = vista;
        modelListaPeliculas = new ModelListaPeliculas(context);
    }

    @Override
    public void getPeliculas(Boolean isFiltrado) {
        Log.d(TAG, "[getPeliculas]");
        //creamos hasmap para connvertir el filtro en una id entendible para la API
        HashMap<String, String> filtroId = new HashMap<>();
        filtroId.put("Acción", "1");
        filtroId.put("Aventura", "2");
        filtroId.put("Terror", "3");
        filtroId.put("Ciencia ficcion", "4");
        if (isFiltrado) {
            Log.d(TAG, "[getPeliculas] isFiltrado");
            modelListaPeliculas.getPeliculasfilterWS(new ContratoListaPeliculas.Model.OnLstPeliculasListener() {
                @Override
                public void onResolve(ArrayList<Pelicula> peliculas) {
                    Log.d(TAG, "[getJuegos] onResolve");
                    vista.success(peliculas);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getJuegos] onResolve");
                    vista.error("Error al tratar los datos");
                }
            }, filtroId.get(filtro));
        } else {
            Log.d(TAG, "[getPeliculasWS]");
            modelListaPeliculas.getPeliculasWS(new ContratoListaPeliculas.Model.OnLstPeliculasListener() {

                @Override
                public void onResolve(ArrayList<Pelicula> peliculas) {
                    Log.d(TAG, "[getPeliculasWS] onResolve");
                    vista.success(peliculas);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getPeliculasWS] onResolve");
                    vista.error("Error al tratar los datos");
                }
            });
        }
    }

    @Override
    public void getPeliculasOrdenVoto() {
        Log.d(TAG, "[getPeliculasOrdenVoto]");
        modelListaPeliculas.getPeliculasOrdenWS(new ContratoListaPeliculas.Model.OnLstPeliculasListener() {
            @Override
            public void onResolve(ArrayList<Pelicula> peliculas) {
                Log.d(TAG, "[getPeliculasOrdenVoto] onResolve");
                vista.success(peliculas);
            }

            @Override
            public void onReject(String error) {
                Log.d(TAG, "[getPeliculasOrdenVoto] onReject " + error);
                vista.error("Error al tratar los datos ");
            }
        });
    }

    @Override
    public void getPeliculasByTitulo(String titulo) {
        Log.d(TAG, "[getPeliculasByTituloWS]");
        if (titulo.isEmpty()) {
            Log.d(TAG, "[getPeliculasByTitulo] " + titulo.isEmpty());
            modelListaPeliculas.getPeliculasWS(new ContratoListaPeliculas.Model.OnLstPeliculasListener() {

                @Override
                public void onResolve(ArrayList<Pelicula> peliculas) {
                    Log.d(TAG, "[getPeliculasWS] onResolve");
                    vista.success(peliculas);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getPeliculasWS] onResolve");
                    vista.error("Error al tratar los datos");
                }
            });
        } else {
            Log.d(TAG, "[getPeliculasByTitulo] " + titulo.isEmpty());
            modelListaPeliculas.getPeliculasByTituloWS(new ContratoListaPeliculas.Model.OnLstPeliculasListener() {
                @Override
                public void onResolve(ArrayList<Pelicula> peliculas) {
                    Log.d(TAG, "[getPeliculasByTituloWS] onResolve");
                    vista.success(peliculas);
                }

                @Override
                public void onReject(String error) {
                    Log.d(TAG, "[getPeliculasByTituloWS] onReject " + error);
                    vista.error("Error al tratar los datos ");
                }
            }, titulo);
        }

    }

}

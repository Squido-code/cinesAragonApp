package com.guillermo.cinesAragonApp.listaPeliculas.model;

import android.content.Context;
import android.util.Log;

import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.listaPeliculas.contract.ContratoListaPeliculas;
import com.guillermo.cinesAragonApp.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AllArgsConstructor
public class ModelListaPeliculas
        implements ContratoListaPeliculas.Model {
    private static final String TAG = ModelListaPeliculas.class.getSimpleName();
    private final Context context;

    @Override
    public void getPeliculasWS(OnLstJuegosListener onLstJuegosListener) {
        Log.d(TAG, "[getjuegosWS]");
        ApiClient apiClient = new ApiClient(context);
        final Call<List<Pelicula>> batch = apiClient.getPeliculas();

        batch.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                Log.d(TAG, "[getPeliculasWS] onResponse");
                if (response != null && response.body() != null) {
                    onLstJuegosListener.onResolve(new ArrayList<Pelicula>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Log.d(TAG, "[getPeliculasWS] onFailure");
                t.printStackTrace();
                onLstJuegosListener.onReject(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getPeliculasfilterWS(OnLstJuegosListener onLstJuegosListener, String filtro) {
        Log.d(TAG, "[getPeliculasfilterWS]");
        ApiClient apiClient = new ApiClient(context);
        final Call<List<Pelicula>> batch = apiClient.getPeliculasFiltered(filtro);
        batch.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                Log.d(TAG, "[getPeliculasfilterWS] onResponse");
                onLstJuegosListener.onResolve(new ArrayList<Pelicula>(response.body()));
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Log.d(TAG, "[getPeliculasfilterWS] onFailure");
                onLstJuegosListener.onReject(t.getLocalizedMessage());
            }
        });
    }


}


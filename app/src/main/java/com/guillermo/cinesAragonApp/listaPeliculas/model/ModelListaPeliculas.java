package com.guillermo.cinesAragonApp.listaPeliculas.model;

import android.content.Context;
import android.util.Log;

import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.listaPeliculas.contract.ContratoListaPeliculas;
import com.guillermo.cinesAragonApp.retrofit.ApiClient;

import java.util.ArrayList;

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
    public void getjuegosWS(OnLstJuegosListener onLstJuegosListener) {
        Log.d(TAG, "[getjuegosWS]");
        ApiClient apiClient = new ApiClient(context);
        final Call<Pelicula> batch = apiClient.getVideojuegos();

        batch.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Log.d(TAG, "[getjuegosWS] onResponse");
                if (response != null && response.body() != null) {
                    onLstJuegosListener.onResolve(new ArrayList<Pelicula>(response.body().getResults()));
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.d(TAG, "[getjuegosWS] onFailure");
                t.printStackTrace();
                onLstJuegosListener.onReject(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getjuegosfilterWS(OnLstJuegosListener onLstJuegosListener, String filtro) {
        Log.d(TAG, "[getjuegosfilterWS]");
        ApiClient apiClient = new ApiClient(context);
        final Call<Pelicula> batch = apiClient.getVideojuegosFiltered(filtro);
        batch.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Log.d(TAG, "[getjuegosfilterWS] onResponse");
                onLstJuegosListener.onResolve(new ArrayList<Pelicula>(response.body().getResults()));
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.d(TAG, "[getjuegosfilterWS] onFailure");
                onLstJuegosListener.onReject(t.getLocalizedMessage());
            }
        });
    }


}


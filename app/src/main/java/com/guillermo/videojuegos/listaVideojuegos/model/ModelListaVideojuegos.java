package com.guillermo.videojuegos.listaVideojuegos.model;

import android.content.Context;
import android.util.Log;

import com.guillermo.videojuegos.beans.Videojuego;
import com.guillermo.videojuegos.beans.VideojuegoApiResult;
import com.guillermo.videojuegos.listaVideojuegos.contract.ContratoListaVideojuegos;
import com.guillermo.videojuegos.retrofit.ApiClient;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AllArgsConstructor
public class ModelListaVideojuegos
        implements ContratoListaVideojuegos.Model {
    private static final String TAG = ModelListaVideojuegos.class.getSimpleName();
    private final Context context;

    @Override
    public void getjuegosWS(OnLstJuegosListener onLstJuegosListener) {
        Log.d(TAG, "[getjuegosWS]");
        ApiClient apiClient = new ApiClient(context);
        final Call<VideojuegoApiResult> batch = apiClient.getVideojuegos();

        batch.enqueue(new Callback<VideojuegoApiResult>() {
            @Override
            public void onResponse(Call<VideojuegoApiResult> call, Response<VideojuegoApiResult> response) {
                Log.d(TAG, "[getjuegosWS] onResponse");
                if (response != null && response.body() != null) {
                    onLstJuegosListener.onResolve(new ArrayList<Videojuego>(response.body().getResults()));
                }
            }

            @Override
            public void onFailure(Call<VideojuegoApiResult> call, Throwable t) {
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
        final Call<VideojuegoApiResult> batch = apiClient.getVideojuegosFiltered(filtro);
        batch.enqueue(new Callback<VideojuegoApiResult>() {
            @Override
            public void onResponse(Call<VideojuegoApiResult> call, Response<VideojuegoApiResult> response) {
                Log.d(TAG, "[getjuegosfilterWS] onResponse");
                onLstJuegosListener.onResolve(new ArrayList<Videojuego>(response.body().getResults()));
            }

            @Override
            public void onFailure(Call<VideojuegoApiResult> call, Throwable t) {
                Log.d(TAG, "[getjuegosfilterWS] onFailure");
                onLstJuegosListener.onReject(t.getLocalizedMessage());
            }
        });
    }


}


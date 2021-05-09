package com.guillermo.videojuegos.retrofit;

import android.content.Context;

import com.guillermo.videojuegos.beans.Ficha;
import com.guillermo.videojuegos.beans.VideojuegoApiResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://api.rawg.io";
    private static final String API_KEY = "565d28a46ca8460ca91674388bd636be";
    private static final String PLATFORMS = "4";
    private final Retrofit retrofit;
    private final Context context;

    public ApiClient(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<VideojuegoApiResult> getVideojuegos() {
        VideojuegosInterface service = retrofit.create(VideojuegosInterface.class);
        return service.getVideogames(PLATFORMS, API_KEY);
    }

    public Call<VideojuegoApiResult> getVideojuegosFiltered(String genre) {
        VideojuegosInterface service = retrofit.create(VideojuegosInterface.class);
        return service.getVideogamesFiltered(PLATFORMS, API_KEY, genre);
    }

    public Call<Ficha> getFicha(String id) {
        VideojuegosInterface service = retrofit.create(VideojuegosInterface.class);
        return service.getficha(id, API_KEY);
    }
}

package com.guillermo.cinesAragonApp.retrofit;

import android.content.Context;

import com.guillermo.cinesAragonApp.beans.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080";
    private final Retrofit retrofit;
    private final Context context;
    PeliculasInterface service;

    public ApiClient(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PeliculasInterface.class);
    }

    public Call<List<Pelicula>> getVideojuegos() {
        return service.getPeliculas();
    }

    public Call<List<Pelicula>> getVideojuegosFiltered(String genre) {
        return service.getPeliculasByGenero(genre);
    }

//    public Call<Ficha> getFicha(String id) {
//        PeliculasInterface service = retrofit.create(PeliculasInterface.class);
//        return service.getficha(id, API_KEY);
//    }
}

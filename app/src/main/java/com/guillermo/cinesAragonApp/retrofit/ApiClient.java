package com.guillermo.cinesAragonApp.retrofit;

import android.content.Context;

import com.guillermo.cinesAragonApp.beans.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.guillermo.cinesAragonApp.utils.Constants.BASE_URL;

public class ApiClient {
    private final Retrofit retrofit;
    private final Context context;
    private final PeliculasInterface service;

    public ApiClient(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PeliculasInterface.class);
    }

    public Call<List<Pelicula>> getPeliculas() {
        return service.getPeliculas();
    }

    public Call<List<Pelicula>> getPeliculasFiltered(String genre) {
        return service.getPeliculasByGenero(genre);
    }

    public Call<List<Pelicula>> getPeliculasOrden() {
        return service.getPeliculasOrden();
    }

    public Call<List<Pelicula>> getPeliculasByTitle(String string) {
        return service.getPeliculasByTitulo(string);
    }
}

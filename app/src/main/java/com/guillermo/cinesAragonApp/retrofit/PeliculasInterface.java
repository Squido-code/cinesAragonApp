package com.guillermo.cinesAragonApp.retrofit;


import com.guillermo.cinesAragonApp.beans.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeliculasInterface {
    @GET("peliculas")
    Call<List<Pelicula>> getPeliculas();

    @GET("peliculas/{id}")
    Call<Pelicula> getPeliculabyId(@Path("id") String id);

    @GET("genero/{id}")
    Call<List<Pelicula>> getPeliculasByGenero(@Path("id") String id);

    @GET("pelicula")
    Call<List<Pelicula>> getPeliculasByTitulo(@Query("titulo") String titulo);

    @GET("orden")
    Call<List<Pelicula>> getPeliculasOrden();
}

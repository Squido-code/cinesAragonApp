package com.guillermo.cinesAragonApp.retrofit;


import com.guillermo.cinesAragonApp.beans.Horarios;
import com.guillermo.cinesAragonApp.beans.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeliculasInterface {
    @GET("http://localhost:8080/peliculas")
    Call<List<Pelicula>> getPeliculas();

    @GET("http://localhost:8080/peliculas/{id}")
    Call<Pelicula> getPeliculabyId(@Path("id") String id);

    @GET("http://localhost:8080/genero/{id}")
    Call<List<Pelicula>> getPeliculasByGenero(@Path("id") String id);

    @GET("http://localhost:8080/pelicula")
    Call<Pelicula> getPeliculasByTitulo(@Query("titulo") String titulo);

    @GET("http://localhost:8080/sesiones/{id}")
    Call<List<Horarios>> getSesiones(@Path("id") String id);

    @GET("http://localhost:8080/orden")
    Call<List<Pelicula>> getPeliculasOrden();
}

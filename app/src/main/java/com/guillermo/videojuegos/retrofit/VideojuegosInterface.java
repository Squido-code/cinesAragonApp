package com.guillermo.videojuegos.retrofit;

import com.guillermo.videojuegos.beans.Ficha;
import com.guillermo.videojuegos.beans.VideojuegoApiResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideojuegosInterface {
    @GET("api/games")
    Call<VideojuegoApiResult> getVideogames(@Query("platforms") String platforms, @Query("key") String apikey);

    @GET("api/games")
    Call<VideojuegoApiResult> getVideogamesFiltered(@Query("platforms") String platforms, @Query("key") String apikey, @Query("genres") String genres);

    @GET("https://api.rawg.io/api/games/{id}")
    Call<Ficha> getficha(@Path("id") String id, @Query("key") String apiKey);
}

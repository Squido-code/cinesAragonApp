package com.guillermo.cinesAragonApp.listaPeliculas.contract;

import com.guillermo.cinesAragonApp.beans.Pelicula;

import java.util.ArrayList;

public interface ContratoListaPeliculas {
    interface View {
        void success(ArrayList<Pelicula> peliculas);

        void error(String mensage);

    }

    interface Presenter {
        void getPeliculas(Boolean isFiltrado);

        void getPeliculasOrdenVoto();

        void getPeliculasByTitulo(String titulo);

    }

    interface Model {
        void getPeliculasWS(OnLstPeliculasListener onLstPeliculasListener);

        void getPeliculasfilterWS(OnLstPeliculasListener onLstPeliculasListener, String filtro);

        void getPeliculasByTituloWS(OnLstPeliculasListener onLstPeliculasListener, String titulo);

        void getPeliculasOrdenWS(OnLstPeliculasListener onLstPeliculasListener);

        /*Reactivo*/
        interface OnLstPeliculasListener {
            void onResolve(ArrayList<Pelicula> juegos);

            void onReject(String error);
        }
    }
}

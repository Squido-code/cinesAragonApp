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

    }

    interface Model {
        void getPeliculasWS(OnLstJuegosListener onLstJuegosListener);

        void getPeliculasfilterWS(OnLstJuegosListener onLstJuegosListener, String filtro);

        /*Reactivo*/
        interface OnLstJuegosListener {
            void onResolve(ArrayList<Pelicula> juegos);

            void onReject(String error);
        }
    }
}

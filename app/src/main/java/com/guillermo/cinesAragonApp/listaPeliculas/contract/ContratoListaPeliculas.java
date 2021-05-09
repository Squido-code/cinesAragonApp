package com.guillermo.cinesAragonApp.listaPeliculas.contract;

import com.guillermo.cinesAragonApp.beans.Pelicula;

import java.util.ArrayList;

public interface ContratoListaPeliculas {
    interface View {
        void success(ArrayList<Pelicula> juegos);

        void error(String mensage);

    }

    interface Presenter {
        void getJuegos(Boolean isFiltrado);

    }

    interface Model {
        void getjuegosWS(OnLstJuegosListener onLstJuegosListener);

        void getjuegosfilterWS(OnLstJuegosListener onLstJuegosListener, String filtro);

        /*Reactivo*/
        interface OnLstJuegosListener {
            void onResolve(ArrayList<Pelicula> juegos);

            void onReject(String error);
        }
    }
}
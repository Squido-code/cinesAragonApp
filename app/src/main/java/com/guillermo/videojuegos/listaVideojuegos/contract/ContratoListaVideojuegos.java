package com.guillermo.videojuegos.listaVideojuegos.contract;

import com.guillermo.videojuegos.beans.Videojuego;

import java.util.ArrayList;

public interface ContratoListaVideojuegos {
    interface View {
        void success(ArrayList<Videojuego> juegos);

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
            void onResolve(ArrayList<Videojuego> juegos);

            void onReject(String error);
        }
    }
}

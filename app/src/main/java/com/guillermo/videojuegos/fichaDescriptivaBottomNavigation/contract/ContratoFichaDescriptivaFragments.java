package com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.contract;

import com.guillermo.videojuegos.beans.Ficha;

public interface ContratoFichaDescriptivaFragments {
    interface View {
        void success(Ficha ficha);

        void error(String mensage);
    }

    interface Presenter {
        void getFicha(String idFicha);

    }

    interface Model {
        void getDescripcionWS(OnLstFichaListener onLstJuegosListener, String idFicha);

        /*Reactivo*/
        interface OnLstFichaListener {
            void onResolve(Ficha listaFicha);

            void onReject(String error);
        }
    }
}

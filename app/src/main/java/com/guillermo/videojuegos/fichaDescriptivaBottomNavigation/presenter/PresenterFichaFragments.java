package com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.presenter;

import android.content.Context;
import android.util.Log;

import com.guillermo.videojuegos.beans.Ficha;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.contract.ContratoFichaDescriptivaFragments;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.model.ModelFichaFragments;

public class PresenterFichaFragments implements ContratoFichaDescriptivaFragments.Presenter {
    private static final String TAG = PresenterFichaFragments.class.getSimpleName();
    private final ContratoFichaDescriptivaFragments.View viewFicha;
    private final ModelFichaFragments modelFichaDescriptiva;

    public PresenterFichaFragments(ContratoFichaDescriptivaFragments.View vistaFicha, Context context) {
        this.viewFicha = vistaFicha;
        this.modelFichaDescriptiva = new ModelFichaFragments(context);
    }


    @Override
    public void getFicha(String idFicha) {
        Log.d(TAG, "[getFicha]");
        modelFichaDescriptiva.getDescripcionWS(new ContratoFichaDescriptivaFragments.Model.OnLstFichaListener() {
            @Override
            public void onResolve(Ficha fichaCompleta) {
                viewFicha.success(fichaCompleta);
                Log.d(TAG, "[getFicha] onResolve");
            }

            @Override
            public void onReject(String error) {
                viewFicha.error("Error al acceder al los datos");
                Log.d(TAG, "[getFicha] onReject");
            }
        }, idFicha);
    }
}

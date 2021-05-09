package com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.model;

import android.content.Context;
import android.util.Log;

import com.guillermo.cinesAragonApp.beans.Ficha;
import com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.contract.ContratoFichaDescriptivaFragments;
import com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.view.ListaBaseFragment;
import com.guillermo.cinesAragonApp.retrofit.ApiClient;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AllArgsConstructor
public class ModelFichaFragments implements ContratoFichaDescriptivaFragments.Model {
    private static final String TAG = ListaBaseFragment.class.getSimpleName();
    private final Context context;

    @Override
    public void getDescripcionWS(OnLstFichaListener onLstFichaListener, String idFicha) {
        ApiClient apiClient = new ApiClient(context);
        Call<Ficha> batch = apiClient.getFicha(idFicha);


        batch.enqueue(new Callback<Ficha>() {
            @Override
            public void onResponse(Call<Ficha> call, Response<Ficha> response) {
                Log.d(TAG, "[onResponse]");
                if (response != null && response.body() != null) {
                    onLstFichaListener.onResolve(response.body());
                }
            }

            @Override
            public void onFailure(Call<Ficha> call, Throwable t) {
                Log.d(TAG, "[onFailure]");
                onLstFichaListener.onReject(t.getLocalizedMessage());
            }
        });
    }


}

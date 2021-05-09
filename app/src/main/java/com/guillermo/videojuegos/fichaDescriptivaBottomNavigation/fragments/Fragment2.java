package com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.guillermo.videojuegos.R;
import com.guillermo.videojuegos.beans.Ficha;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    private Ficha ficha;

    public Fragment2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(Ficha ficha) {
        Fragment2 fragment = new Fragment2();
        fragment.setFicha(ficha);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView rellenoDatos = getView().findViewById(R.id.datosTecnicos);

        String datos = "Nota Metacritics: " + ficha.getMetacritic() + "\n" +
                "Fecha de estreno: " + ficha.getReleased() + "\n" +
                "Website: " + ficha.getWebsite();
        rellenoDatos.setText(datos);
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}
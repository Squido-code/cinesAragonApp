package com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.guillermo.cinesAragonApp.R;
import com.guillermo.cinesAragonApp.beans.Ficha;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename and change types of parameters
    private Ficha ficha;


    public Fragment1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(Ficha ficha) {
        Fragment1 fragment = new Fragment1();
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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar tvNombreFicha = getView().findViewById(R.id.nombreFragment);
        tvNombreFicha.setTitle(ficha.getNombre());
        TextView tvDescripcionFicha = getView().findViewById(R.id.descriptionFragment);
        tvDescripcionFicha.setText(ficha.getSinopsis());
        ImageView ivFicha = getView().findViewById(R.id.imagenFragment);
        Picasso.get().load(ficha.getCartel()).into(ivFicha);
    }
}
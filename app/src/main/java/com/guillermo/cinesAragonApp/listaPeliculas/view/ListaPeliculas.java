package com.guillermo.cinesAragonApp.listaPeliculas.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.guillermo.cinesAragonApp.R;
import com.guillermo.cinesAragonApp.adapter.PeliculaAdapter;
import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.listaPeliculas.contract.ContratoListaPeliculas;
import com.guillermo.cinesAragonApp.listaPeliculas.presenter.PresentadorListaPeliculas;

import java.util.ArrayList;


public class ListaPeliculas extends AppCompatActivity implements ContratoListaPeliculas.View {
    private static final String TAG = ListaPeliculas.class.getSimpleName();
    private PresentadorListaPeliculas presentadorListaPeliculas;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.lista_peliculas2);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        presentadorListaPeliculas = new PresentadorListaPeliculas(this, this);
        presentadorListaPeliculas.getPeliculas(false);
//        filtrado();
    }


    @Override
    public void success(ArrayList<Pelicula> juegos) {
        Log.d(TAG, "[success]");
        recyclerView = findViewById(R.id.recyclerVideojuegos);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PeliculaAdapter adapter = new PeliculaAdapter(juegos, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error(String mensage) {
        Log.d(TAG, "[error]");
        Toast.makeText(this, "error al mostrar los datos", Toast.LENGTH_SHORT).show();
    }


    public void filtrado() {
        Log.d(TAG, "[filtrado]");

        final AutoCompleteTextView spinner = (AutoCompleteTextView) navigationView.getMenu().findItem(R.id.menu1);

        String[] generos = new String[]{"todos", "acción", "aventura", "Terror", "Ciencia ficcion"};
        ArrayAdapter<String> adapterFiltro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        adapterFiltro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterFiltro);


        spinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("todos".contentEquals(s)) {
                    presentadorListaPeliculas.getPeliculas(false);
                } else {
                    presentadorListaPeliculas.setFiltro(String.valueOf(s));
                    presentadorListaPeliculas.getPeliculas(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
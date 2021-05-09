package com.guillermo.cinesAragonApp.listaPeliculas.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guillermo.cinesAragonApp.R;
import com.guillermo.cinesAragonApp.adapter.PeliculaAdapter;
import com.guillermo.cinesAragonApp.beans.Pelicula;
import com.guillermo.cinesAragonApp.listaPeliculas.contract.ContratoListaPeliculas;
import com.guillermo.cinesAragonApp.listaPeliculas.presenter.PresentadorListaPeliculas;

import java.util.ArrayList;


public class ListaPeliculas extends AppCompatActivity implements ContratoListaPeliculas.View {
    private static final String TAG = ListaPeliculas.class.getSimpleName();
    private PresentadorListaPeliculas presentadorListaVideojuegos;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lista_pelicula);
        presentadorListaVideojuegos = new PresentadorListaPeliculas(this, this);
        presentadorListaVideojuegos.getJuegos(false);
        filtrado();
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
        final AutoCompleteTextView spinner = findViewById(R.id.spinnerTextView);

        String[] generos = new String[]{"todos", "acción", "aventura", "RPG", "Estrategia"};
        ArrayAdapter<String> adapterFiltro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        adapterFiltro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterFiltro);


        spinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("todos".equals(s)) {
                    presentadorListaVideojuegos.getJuegos(false);
                } else {
                    presentadorListaVideojuegos.setFiltro(String.valueOf(s));
                    presentadorListaVideojuegos.getJuegos(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                String selecteditem = adapter.getItemAtPosition(i).toString();
                String seleccion = selecteditem;
                switch (seleccion) {
                    case "todos":
                        presentadorListaVideojuegos.getJuegos(false);
                        break;
                    default:
                        presentadorListaVideojuegos.setFiltro(selecteditem);
                        presentadorListaVideojuegos.getJuegos(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
    }

}
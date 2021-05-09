package com.guillermo.videojuegos.listaVideojuegos.view;

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

import com.guillermo.videojuegos.R;
import com.guillermo.videojuegos.adapter.VideojuegoAdapter;
import com.guillermo.videojuegos.beans.Videojuego;
import com.guillermo.videojuegos.listaVideojuegos.contract.ContratoListaVideojuegos;
import com.guillermo.videojuegos.listaVideojuegos.presenter.PresentadorListaVideojuegos;

import java.util.ArrayList;


public class ListaVideojuegos extends AppCompatActivity implements ContratoListaVideojuegos.View {
    private static final String TAG = ListaVideojuegos.class.getSimpleName();
    private PresentadorListaVideojuegos presentadorListaVideojuegos;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lista_videojuegos);
        presentadorListaVideojuegos = new PresentadorListaVideojuegos(this, this);
        presentadorListaVideojuegos.getJuegos(false);
        filtrado();
    }


    @Override
    public void success(ArrayList<Videojuego> juegos) {
        Log.d(TAG, "[success]");
        recyclerView = findViewById(R.id.recyclerVideojuegos);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        VideojuegoAdapter adapter = new VideojuegoAdapter(juegos, this);
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
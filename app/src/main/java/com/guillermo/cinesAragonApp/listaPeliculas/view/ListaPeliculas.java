package com.guillermo.cinesAragonApp.listaPeliculas.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
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
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.lista_peliculas2);
        navigationView = findViewById(R.id.nav_view);
        presentadorListaPeliculas = new PresentadorListaPeliculas(this, this);
        presentadorListaPeliculas.getPeliculas(false);
        setToolbar();
        filtrado();
        ordenVotos();
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

        Spinner spinner = (Spinner) navigationView.getMenu().findItem(R.id.menu2).getActionView();
        String[] generos = new String[]{"Todos", "Acción", "Aventura", "Terror", "Ciencia ficcion"};
        ArrayAdapter<String> adapterFiltro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        adapterFiltro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterFiltro);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                Log.d(TAG, "[onItemSelected]");
                String selecteditem = adapter.getItemAtPosition(i).toString();
                String seleccion = selecteditem;
                switch (seleccion) {
                    case "Todos":
                        presentadorListaPeliculas.getPeliculas(false);
                        break;
                    default:
                        presentadorListaPeliculas.setFiltro(seleccion);
                        presentadorListaPeliculas.getPeliculas(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
    }

    public void filtradoPorPalabra() {
        EditText filtradoTexto = (EditText) navigationView.getMenu().findItem(R.id.menu3).getActionView();
        String texto = String.valueOf(filtradoTexto.getText());
        presentadorListaPeliculas.getPeliculasFiltroTexto(texto);

    }

    public void ordenVotos() {
//        CheckBox checkBox = (CheckBox) navigationView.getMenu().findItem(R.id.menu1).getActionView();
//        if(checkBox.isChecked()){
//            presentadorListaPeliculas.getPeliculasOrdenVoto();
//        }else{
//            presentadorListaPeliculas.getPeliculas(false);
//        }
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.menu1);
        CompoundButton compundButton = (CompoundButton) menuItem.getActionView();
        compundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    presentadorListaPeliculas.getPeliculasOrdenVoto();
                } else {
                    presentadorListaPeliculas.getPeliculas(false);
                }
            }
        });
    }

    private void setToolbar() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_opened, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBarDrawerToggle.syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBarDrawerToggle.syncState();

            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1:
                ordenVotos();
                return true;
            case R.id.menu3:
                filtradoPorPalabra();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
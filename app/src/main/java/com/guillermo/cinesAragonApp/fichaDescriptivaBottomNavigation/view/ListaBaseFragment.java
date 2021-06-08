package com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.guillermo.cinesAragonApp.R;
import com.guillermo.cinesAragonApp.beans.Ficha;
import com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.fragments.Fragment1;
import com.guillermo.cinesAragonApp.fichaDescriptivaBottomNavigation.fragments.Fragment2;


public class ListaBaseFragment extends AppCompatActivity {

    private static final String TAG = ListaBaseFragment.class.getSimpleName();
    private static final String ARG_EXTRAS_TITULO = "nombre";
    private static final String ARG_EXTRAS_SINOPSIS = "sinopsis";
    private static final String ARG_EXTRAS_CARTEL = "cartel";
    private static final String ARG_EXTRAS_DURACION = "duracion";
    private static final String ARG_EXTRAS_ESTRENO = "fecha_estreno";
    private static final String ARG_EXTRAS_GENERO = "genero";
    private static final String ARG_EXTRAS_TRAILER = "trailer";
    private static final String ARG_EXTRAS_VOTOS = "votos";

    private BottomNavigationView mBottomNavigationView;

    private FragmentManager fragmentManager;
    private Ficha ficha;

    private Fragment1 fragment1;
    private Fragment2 fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_base_fragment);
        getIncomingIntent();
        fragmentManager = getSupportFragmentManager();
        fragment1 = Fragment1.newInstance(ficha);
        fragment2 = Fragment2.newInstance(ficha);
        mBottomNavigationView = findViewById(R.id.navigationView);
        initBottomNavigation();
        showFragment1();
    }

    private void initBottomNavigation() {

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item1:
                    Log.d(TAG, "[onNavigationItemSelected] menu_nav_1");
                    showFragment1();
                    break;
                case R.id.item2:
                    Log.d(TAG, "[onNavigationItemSelected] menu_nav_2");
                    showFragment2();
                    break;
                default:
            }
            // Devolver true para que os seleccione el elemento Clickado
            return true;
        });
    }

    private void showFragment1() {
        Log.d(TAG, "[showFragment1]");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_lista_base, fragment1);
        transaction.commit();
    }

    private void showFragment2() {
        Log.d(TAG, "[showFragment2]");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_lista_base, fragment2);
        transaction.commit();
    }

    private void getIncomingIntent() {
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ficha = Ficha.builder().nombre(extras.getString(ARG_EXTRAS_TITULO))
                    .sinopsis(extras.getString(ARG_EXTRAS_SINOPSIS))
                    .cartel(extras.getString(ARG_EXTRAS_CARTEL))
                    .duracion(extras.getString(ARG_EXTRAS_DURACION))
                    .fecha_Estreno(extras.getString(ARG_EXTRAS_ESTRENO))
                    .genero(extras.getString(ARG_EXTRAS_GENERO))
                    .trailer(extras.getString(ARG_EXTRAS_TRAILER))
                    .votos(extras.getString(ARG_EXTRAS_VOTOS))
                    .build();
        }
    }
}
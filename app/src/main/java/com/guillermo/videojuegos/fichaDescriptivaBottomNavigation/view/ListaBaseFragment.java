package com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.guillermo.videojuegos.R;
import com.guillermo.videojuegos.beans.Ficha;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.contract.ContratoFichaDescriptivaFragments;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.fragments.Fragment1;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.fragments.Fragment2;
import com.guillermo.videojuegos.fichaDescriptivaBottomNavigation.presenter.PresenterFichaFragments;

public class ListaBaseFragment extends AppCompatActivity implements ContratoFichaDescriptivaFragments.View {

    private static final String TAG = ListaBaseFragment.class.getSimpleName();

    private BottomNavigationView mBottomNavigationView;

    private FragmentManager fragmentManager;

    private String fichaId;

    private Ficha ficha;

    private PresenterFichaFragments presenterFichaFragments;

    private Fragment1 fragment1;
    private Fragment2 fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_base_fragment);
        getIncomingIntent();
        fragmentManager = getSupportFragmentManager();
        presenterFichaFragments = new PresenterFichaFragments(this, this);
        presenterFichaFragments.getFicha(fichaId);
        mBottomNavigationView = findViewById(R.id.navigationView);
        initBottomNavigation();

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

    @Override
    public void success(Ficha ficha) {
        this.ficha = ficha;
        fragment1 = Fragment1.newInstance(ficha);
        fragment2 = Fragment2.newInstance(ficha);
        showFragment1();
    }

    @Override
    public void error(String mensage) {
        Toast.makeText(this, "error al mostrar los datos", Toast.LENGTH_SHORT).show();
    }

    private void getIncomingIntent() {
        boolean isComplete = getIntent().hasExtra("juego_id");
        if (isComplete) {
            fichaId = getIntent().getStringExtra("juego_id");
        }
    }
}
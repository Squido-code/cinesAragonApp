package com.guillermo.cinesAragonApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.guillermo.cinesAragonApp.listaPeliculas.view.ListaPeliculas;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();

        Intent navegar = new Intent(
                getBaseContext(), ListaPeliculas.class);
        startActivity(navegar);
//        handler.postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        // Cargar la 2ª pantalla
//                        Intent navegar = new Intent(
//                                getBaseContext(), ListaPeliculas.class);
//                        startActivity(navegar);
//                    }
//                }
//                , 4000
//        );
    }
}
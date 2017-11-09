package es.unican.g3.tus.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import es.unican.alejandro.tus_practica3.R;

public class ActivityInicialLogo extends AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private static final int DURACION_SPLASH = 2000; // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos de la actividad principal a paradas
                Intent intent = new Intent(ActivityInicialLogo.this, es.unican.g3.tus.views.MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}


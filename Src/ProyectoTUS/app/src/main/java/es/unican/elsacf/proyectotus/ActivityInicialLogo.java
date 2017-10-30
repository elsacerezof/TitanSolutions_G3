package es.unican.elsacf.proyectotus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Prueba de comentario (Javier) para Git

public class ActivityInicialLogo extends AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 2000; // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos de la actividad principal a paradas
                Intent intent = new Intent(ActivityInicialLogo.this, SeleccionNavigationDrawer.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}



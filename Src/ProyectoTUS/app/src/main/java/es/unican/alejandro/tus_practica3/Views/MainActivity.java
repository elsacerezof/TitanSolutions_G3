package es.unican.alejandro.tus_practica3.Views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import es.unican.alejandro.tus_practica3.R;

public class MainActivity extends AppCompatActivity implements DataCommunication {

    private int lineaIdentifier;
    private int paradaIdentifier;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParadasFragment fragmentParadas = new ParadasFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =  fm.beginTransaction();
        ft.add(R.id.frameLayoutElements,fragmentParadas);
        ft.commit();
        count++;
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }//onCreate


    @Override
    public int getLineaIdentifier() {
        return lineaIdentifier;
    }

    @Override
    public void setLineaIdentifier(int identifier) {
        this.lineaIdentifier=identifier;
    }

    @Override
    public int getParadaIdentifier() { return paradaIdentifier; }

    @Override
    public void setParadaIdentifier(int paradaIdentifier) { this.paradaIdentifier = paradaIdentifier; }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    if(count==0)
                    {
                        ParadasFragment fragmentParadas = new ParadasFragment();
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft =  fm.beginTransaction();
                        ft.add(R.id.frameLayoutElements,fragmentParadas);
                        ft.commit();
                        count++;
                    }

                    return true;
                case R.id.navigation_dashboard:


                    return true;
                case R.id.navigation_notifications:

                    return true;

                default:
                    break;
            }
            return false;
        }

    };

}// MainActivity

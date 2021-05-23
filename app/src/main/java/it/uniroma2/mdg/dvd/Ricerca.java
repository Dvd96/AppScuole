package it.uniroma2.mdg.dvd;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Ricerca extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Intent intent = getIntent();
        int messaggio=intent.getIntExtra("nome",1);
        Log.d("NOME", "onCreate: "+intent.getExtras());
        BottomNavigationView BnavigationView= findViewById(R.id.nav_view);
        if(messaggio==1){
            loadFragment(new Nome());
            BnavigationView.setSelectedItemId(R.id.navigation_nome);
        }else
            if (messaggio==2){
                loadFragment(new Indirizzo());
                BnavigationView.setSelectedItemId(R.id.navigation_indirizzo);
            }else
                if(messaggio==3){
                    loadFragment(new Caratteristiche());
                    BnavigationView.setSelectedItemId(R.id.navigation_caratteristiche);
                }else
                    if(messaggio==4){
                        loadFragment(new Preferiti());
                        BnavigationView.setSelectedItemId(R.id.navigation_preferiti);
                    }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_nome:
                fragment = new Nome();
                break;

            case R.id.navigation_indirizzo:
                fragment = new Indirizzo();
                break;

            case R.id.navigation_caratteristiche:
                fragment = new Caratteristiche();
                break;

            case R.id.navigation_preferiti:
                fragment = new Preferiti();
                break;


        }

        return loadFragment(fragment);
    }
}

package it.uniroma2.mdg.dvd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.mapbox.mapboxsdk.Mapbox;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, "pk.eyJ1IjoiZHZkOTYiLCJhIjoiY2swNnE3cTNnMDJ6YTNwazQ2cDV6NGF3ciJ9.nRo41x_t2WtycwRLBWFnnA");
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Preferiti.tinyDB1 = new TinyDB(this);
        Scuola.tinyDB = new TinyDB(this);
        Scuola.tinyDB2 = new TinyDB(this);
        Log.d("prova", "onCreate: " + Scuola.tinyDB2.getInt("numero") ) ;


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        /*
        AlphaAnimation animation = new AlphaAnimation(0f,1.0f);
        animation.setDuration(2000);
        animation.setStartOffset(500);
        animation.setFillAfter(true);
        prima.startAnimation(animation);
        AlphaAnimation animation2 = new AlphaAnimation(0f,1.0f);
        animation2.setDuration(2000);
        animation2.setStartOffset(2000);
        animation2.setFillAfter(true);
        seconda.startAnimation(animation2);
        AlphaAnimation animation3 = new AlphaAnimation(0f,1.0f);
        animation3.setDuration(2000);
        animation3.setStartOffset(4000);
        animation3.setFillAfter(true);
        terza.startAnimation(animation3);
        */


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent= new Intent(getBaseContext(),Ricerca.class);
        if (id == R.id.nome) {
            intent.putExtra("nome",1);
            Log.d("NOME", "onNavigationItemSelected: "+intent.getIntExtra("nome",0));
            startActivity(intent);
        } else if (id == R.id.indirizzo) {
            intent.putExtra("nome",2);
            startActivity(intent);
            Log.d("NOME", "onNavigationItemSelected: "+intent.getIntExtra("nome",0));
        } else if (id == R.id.caratteristiche) {
            intent.putExtra("nome",3);
            startActivity(intent);
            Log.d("NOME", "onNavigationItemSelected: "+intent.getIntExtra("nome",0));
        } else if (id == R.id.preferiti){
            intent.putExtra("nome",4);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

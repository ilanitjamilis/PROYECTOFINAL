package proyectofinal.helpme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActividadNavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public boolean isFirstStart;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        setContentView(R.layout.actividad_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setLogo(R.drawable.ic_logo);

        //android.app.ActionBar actionBar = getActionBar();

        /*Intent irWizard = new Intent(ActividadPrincipal.this, MyIntro.class);
        startActivity(irWizard);*/

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(ActividadNavigationDrawer.this, MyIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();

        String miCodigoPais = getCountryCode().toUpperCase();
        utilidades.miCodigoPais = miCodigoPais;
        getSupportActionBar().setSubtitle("Ubicación actual: "+miCodigoPais);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_menu1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_navigation_drawer, menu);
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

        if (id == R.id.nav_menu1) {
            Intent i = new Intent(ActividadNavigationDrawer.this, ActividadPrincipal.class);
            startActivity(i);
        } else if (id == R.id.nav_menu2) {

        } else if (id == R.id.nav_menu3) {

        } else if (id == R.id.nav_menu4) {

        } else if (id == R.id.nav_menu5) {

        } else if (id == R.id.nav_menu6) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String  getCountryCode(){
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String countryCode = tm.getNetworkCountryIso();
        return countryCode;
    }

    public void llamarPolicia(View vista){
        Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
        intentoLlamada.setData(Uri.parse("tel:101"));
        startActivity(intentoLlamada);
    }

    public void llamarAmbulancia(View vista){
        Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
        intentoLlamada.setData(Uri.parse("tel:107"));
        startActivity(intentoLlamada);
    }

    public void llamarBomberos(View vista){
        Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
        intentoLlamada.setData(Uri.parse("tel:100"));
        startActivity(intentoLlamada);
    }

    public void enviarMensajeEmergencia (View vista){
        String mensaje = "Funcionalidad en construcción";
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    public void irMapas (View vista){
        Intent intentoMapas = new Intent(android.content.Intent.ACTION_VIEW);
        startActivity(intentoMapas);
    }

}

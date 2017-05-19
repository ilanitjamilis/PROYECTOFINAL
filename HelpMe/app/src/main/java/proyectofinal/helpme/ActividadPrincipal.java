package proyectofinal.helpme;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ActividadPrincipal extends AppCompatActivity {

    public boolean isFirstStart;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.actividad_principal);


        //Toolbar miToolbar = (Toolbar)findViewById(R.id.toolbar);
        //miToolbar.setLogo(R.drawable.logo);

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
                    Intent i = new Intent(ActividadPrincipal.this, MyIntro.class);
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

        Toast.makeText(this,miCodigoPais,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logo, menu);
        return super.onCreateOptionsMenu(menu);
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
        /*double latitude = 40.714728;
        double longitude = -73.998672;
        String label = "I'm Here!";
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(mapIntent);*/

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        startActivity(intent);
    }
}

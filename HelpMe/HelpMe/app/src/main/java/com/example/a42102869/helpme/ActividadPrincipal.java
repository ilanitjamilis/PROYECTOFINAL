package com.example.a42102869.helpme;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        Log.d("ila","11");
        nDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        Log.d("ila","12");
        mToggle = new ActionBarDrawerToggle(this,nDrawerLayout,R.string.Open,R.string.Close);
        Log.d("ila","13");

        Log.d("ila","14");
        nDrawerLayout.addDrawerListener(mToggle);
        Log.d("ila","15");
        mToggle.syncState();
        Log.d("ila","16");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("ila","17");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void llamarPolicia(View vista){
        Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //va Action_CALL pero no anda, dice que faltan permisos
        intentoLlamada.setData(Uri.parse("tel:1149479396"));
        startActivity(intentoLlamada);
    }

    public void irMapas (View vista){

        /*Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);*/

        double latitude = 40.714728;
        double longitude = -73.998672;
        String label = "I'm Here!";
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(mapIntent);
    }
}

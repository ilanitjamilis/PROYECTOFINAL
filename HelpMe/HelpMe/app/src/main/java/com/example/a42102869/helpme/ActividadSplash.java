package com.example.a42102869.helpme;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class ActividadSplash extends Activity {
    //Activity o AppCompatActivity

    private static final long SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_splash);


        /*INTENTO 1:
        new Handler() .postDelayed (new Runnable(){
            @Override
            public void run (){
                Intent intent = new Intent (ActividadSplash.this, ActividadPrincipal.class);
                startActivity(intent);
            }
        });/*

        /* INTENTO 2:
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        getWindow().findViewById(android.R.id.content).requestLayout();


        // Set portrait orientation

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar


        setContentView(R.layout.actividad_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent(ActividadSplash.this, ActividadPrincipal.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_TIME_OUT);/*

        // INTENTO 3:
        Thread miThread = new Thread() {
            @Override
            public void run() {
                try {
                    Log.d("ila", "01");
                    sleep(3000);
                    Log.d("ila", "02");
                    Intent irActividadPrincipal = new Intent().setClass(ActividadSplash.this, ActividadPrincipal.class);
                    Log.d("ila", "03");

                    startActivity(irActividadPrincipal);
                    Log.d("ila", "04");
                    finish();
                    Log.d("ila", "05");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Log.d("ila", "06");
        miThread.start();
        Log.d("ila", "07");
    }

    /* INTENTO 4:
    Log.d("ila","01");
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    Log.d("ila","02");
    setContentView(R.layout.actividad_splash);

    Log.d("ila","03");
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Log.d("ila","04");
            Intent mainIntent = new Intent().setClass(ActividadSplash.this, ActividadPrincipal.class);
            Log.d("ila","05");
            startActivity(mainIntent);
            Log.d("ila","06");
            finish();
            Log.d("ila","07");
        }
    };

    Log.d("ila","08");
    Timer timer = new Timer();
    Log.d("ila","09");
    timer.schedule(task, SPLASH_TIME_OUT); //se rompe todo cuando terimina el timer, no anda el intent creo
    Log.d("ila","10");
    }*/
}

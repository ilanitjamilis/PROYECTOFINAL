package com.example.a42102869.helpme;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class ActividadSplash extends Activity {
    //Activity o AppCompatActivity

    //private static final long SPLASH_SCREEN_DELAY = 3000;
    //private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_splash);

        android.os.Handler hadler = new android.os.Handler();
        hadler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent i = new Intent(ActividadSplash.this, ActividadPrincipal.class);
                startActivity(i);
            }
        }, 3000);

    }

        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        getWindow().findViewById(android.R.id.content).requestLayout();


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.actividad_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(ActividadSplash.this, ActividadPrincipal.class);
                startActivity(mainIntent);

                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }*/

        /*Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent (getApplicationContext(), ActividadPrincipal.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();*/

        /*Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(ActividadSplash.this,ActividadPrincipal.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }*/
}

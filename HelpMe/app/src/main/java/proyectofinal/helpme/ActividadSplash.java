package proyectofinal.helpme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Window;

public class ActividadSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.actividad_splash);

        android.os.Handler hadler = new android.os.Handler();
        hadler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent i = new Intent(ActividadSplash.this, ActividadPrincipal.class);
                startActivity(i);
            }
        }, 1000);
    }
}

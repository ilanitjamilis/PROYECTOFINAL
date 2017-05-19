package proyectofinal.helpme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        String miCodigoPais = getCountryCode().toUpperCase();
        utilidades.miCodigoPais = miCodigoPais;
        getSupportActionBar().setSubtitle("Ubicación actual: "+miCodigoPais);
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
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        startActivity(intent);
    }
}

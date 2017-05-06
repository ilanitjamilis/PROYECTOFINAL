package proyectofinal.helpme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }

    public void llamarPolicia(View vista){
        Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
        intentoLlamada.setData(Uri.parse("tel:911"));
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
        Toast miMensajeAlerta = Toast.makeText(this,mensaje, Toast.LENGTH_SHORT);
        miMensajeAlerta.show();
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

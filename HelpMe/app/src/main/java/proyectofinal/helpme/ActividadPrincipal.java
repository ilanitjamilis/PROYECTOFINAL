package proyectofinal.helpme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActividadPrincipal extends Fragment implements View.OnClickListener {

    public ActividadPrincipal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_principal, container, false);

        ImageButton btnPolicia;
        btnPolicia = (ImageButton) vistaADevolver.findViewById(R.id.imgbtnPolicia);
        btnPolicia.setOnClickListener(this);

        ImageButton btnAmbulancia;
        btnAmbulancia = (ImageButton) vistaADevolver.findViewById(R.id.imgbtnAmbulancia);
        btnAmbulancia.setOnClickListener(this);

        ImageButton btnBomberos;
        btnBomberos = (ImageButton) vistaADevolver.findViewById(R.id.imgbtnBomberos);
        btnBomberos.setOnClickListener(this);

        ImageButton btnEmergencia;
        btnEmergencia = (ImageButton) vistaADevolver.findViewById(R.id.imgbtnMensajeEmergencia);
        btnEmergencia.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        switch(vista.getId()) {
            case R.id.imgbtnPolicia :
                ActividadNavigationDrawer.llamarPolicia(vista);
                break;
            case R.id.imgbtnAmbulancia :
                ActividadNavigationDrawer.llamarAmbulancia(vista);
                break;
            case R.id.imgbtnBomberos :
                ActividadNavigationDrawer.llamarBomberos(vista);
                break;
            case R.id.imgbtnMensajeEmergencia :
                ActividadNavigationDrawer.enviarMensajeEmergencia(vista);
                Log.d("mensaje", "toco enviar mensaje");
                break;
            case R.id.imgbtnUbicacion :
                ActividadNavigationDrawer.irMapas(vista);
                break;
        }
    }

}

package proyectofinal.helpme;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import proyectofinal.helpme.ActividadNavigationDrawer;
import proyectofinal.helpme.R;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class ActividadAlergia1 extends Fragment implements View.OnClickListener{
    Button btnLlame, btnSiguiente;
    ImageButton btnVolver;

    public ActividadAlergia1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.fragment_actividad_alergia1, container, false);
        btnLlame = (Button) vistaADevolver.findViewById(R.id.btnLlamarAmbulancia);
        btnLlame.setOnClickListener(this);
        btnSiguiente = (Button) vistaADevolver.findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);
        btnVolver = (ImageButton) vistaADevolver.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);
        return vistaADevolver;
    }
    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Log.d("prueba", "id: "+vista.getId());

        switch(vista.getId()) {
            case R.id.btnLlamarAmbulancia:
                ActividadNavigationDrawer.llamarAmbulancia(vista);
                break;
            case R.id.btnSiguiente:
                ActividadNavigationDrawer.irActividadAlergia2();
                break;
            case R.id.btnVolver:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
        }
    }
}




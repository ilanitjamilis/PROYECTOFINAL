package proyectofinal.helpme;

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


public class ActividadACV1 extends Fragment implements View.OnClickListener{
    Button btnLlame, btnNoIncon, btnSiIncon;
    ImageButton btnVolver;



    public ActividadACV1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.actividad_acv1, container, false);
        btnLlame = (Button) vistaADevolver.findViewById(R.id.btnLlamarAmbulancia);
        btnLlame.setOnClickListener(this);
        btnNoIncon = (Button) vistaADevolver.findViewById(R.id.btnNoIncon);
        btnNoIncon.setOnClickListener(this);
        btnSiIncon = (Button) vistaADevolver.findViewById(R.id.btnSiIncon);
        btnSiIncon.setOnClickListener(this);
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
            case R.id.btnNoIncon:
                ActividadNavigationDrawer.irActividadAcompañe();
                break;
            case R.id.btnSiIncon:
                ActividadNavigationDrawer.irActividadACV2();
                break;
            case R.id.btnVolver:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
        }
    }
}




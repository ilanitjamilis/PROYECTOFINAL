package proyectofinal.helpme;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class ActividadRCP3 extends Fragment implements View.OnClickListener{
    Button btnAcompañe;
    ImageButton btnVolver;
    public ActividadRCP3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.actividad_rcp3, container, false);
        btnAcompañe = (Button) vistaADevolver.findViewById(R.id.btnSiguiente);
        btnAcompañe.setOnClickListener(this);
        btnVolver = (ImageButton) vistaADevolver.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

        return vistaADevolver;
    }

    @Override
    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Log.d("prueba", "id: "+vista.getId());

        switch(vista.getId()) {
            case R.id.btnSiguiente:
                ActividadNavigationDrawer.irActividadAcompañe();
                break;
            case R.id.btnVolver:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
        }
    }
}

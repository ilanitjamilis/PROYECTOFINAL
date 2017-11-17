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


public class ActividadAlergia2 extends Fragment implements View.OnClickListener{
    Button btnSiguiente;
    ImageButton btnVolver;
    public ActividadAlergia2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.fragment_actividad_alergia2, container, false);
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
            case R.id.btnSiguiente:
                ActividadNavigationDrawer.irActividadAcompañe();
                break;
            case R.id.btnVolver:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
        }
    }
}

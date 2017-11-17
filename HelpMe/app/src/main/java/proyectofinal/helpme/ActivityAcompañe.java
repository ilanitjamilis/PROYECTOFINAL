package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityAcompañe extends Fragment implements View.OnClickListener {
    ImageButton btnVolver;
    Button btnVolveer;

    public ActivityAcompañe() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.activity_acompane, container, false);
        btnVolver = (ImageButton) vistaADevolver.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);
        btnVolveer = (Button) vistaADevolver.findViewById(R.id.btnVolveer);
        btnVolveer.setOnClickListener(this);
        return vistaADevolver;
    }
    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Log.d("prueba", "id: "+vista.getId());

        switch(vista.getId()) {

            case R.id.btnVolver:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
            case R.id.btnVolveer:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
        }
    }


}

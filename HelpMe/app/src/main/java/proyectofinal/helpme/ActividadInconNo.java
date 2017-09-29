package proyectofinal.helpme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ActividadInconNo extends Fragment implements View.OnClickListener {
    Button btnMAS;


    public ActividadInconNo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.fragment_actividad_incon_no, container, false);

        btnMAS = (Button) vistaADevolver.findViewById(R.id.btnMAS);
        btnMAS.setOnClickListener(this);

        return vistaADevolver;
    }
    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();
        Integer id = vista.getId();

        switch(vista.getId()) {
            case R.id.btnMAS:
                ActividadNavigationDrawer.irInconSi();
                break;
        }
    }
}

package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ActividadRCP extends Fragment implements View.OnClickListener {
    Button btnSI, btnNo;
    ImageButton btnVolver;
    public ActividadRCP() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.actividad_rc, container, false);
        btnSI = (Button) vistaADevolver.findViewById(R.id.btnSi);
        btnSI.setOnClickListener(this);
        btnNo = (Button) vistaADevolver.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(this);
        btnVolver = (ImageButton) vistaADevolver.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);


        return vistaADevolver;
    }
    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Log.d("prueba", "id: "+vista.getId());

        switch(vista.getId()) {
            case R.id.btnSi:
                ActividadNavigationDrawer.irActividadRCP2();
                break;
            case R.id.btnNo:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
            case R.id.btnVolver:
                ActividadNavigationDrawer.irAyudaAlOtro(vista);
                break;
        }
    }


}

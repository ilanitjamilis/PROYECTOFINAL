package proyectofinal.helpme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ActividadRCP extends Fragment implements View.OnClickListener{


    public ActividadRCP() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.fragment_actividad_rc, container, false);

        Button btnLlamarAmbulancia = btnLlamarAmbulancia = (Button) vistaADevolver.findViewById(R.id.btnLlamarAmbulancia);
        btnLlamarAmbulancia.setOnClickListener(this);

        Button inconSi = (Button)vistaADevolver.findViewById(R.id.btnInconSi);
        inconSi.setOnClickListener(this);

        Button inconNo = (Button)vistaADevolver.findViewById(R.id.btnInconNo);
        inconNo.setOnClickListener(this);

        return vistaADevolver;

    }

    @Override
    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        switch(vista.getId()) {
            case R.id.btnInconNo:
                ActividadNavigationDrawer.irInconNo();
                break;
            case R.id.btnInconSi:
                ActividadNavigationDrawer.irInconSi();
                break;
            case R.id.btnLlamarAmbulancia:
                ActividadNavigationDrawer.llamarAmbulancia(vista);
                break;
        }
    }
}

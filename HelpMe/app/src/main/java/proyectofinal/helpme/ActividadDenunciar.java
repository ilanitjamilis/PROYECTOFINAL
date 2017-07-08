package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ActividadDenunciar extends Fragment implements View.OnClickListener {

    public ActividadDenunciar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_denunciar, container, false);

        Button btnDenunciar;
        btnDenunciar = (Button) vistaADevolver.findViewById(R.id.btnDenunciar);
        btnDenunciar.setOnClickListener(this);

        Button btnVerDenuncias;
        btnVerDenuncias = (Button) vistaADevolver.findViewById(R.id.btnVerDenuncias);
        btnVerDenuncias.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Integer id = vista.getId();

        switch(vista.getId()) {
            case R.id.btnDenunciar:
                ActividadNavigationDrawer.irDenunciar();
                break;
            case R.id.btnVerDenuncias:
                ActividadNavigationDrawer.irVerDenuncias();
                break;
        }
    }

}

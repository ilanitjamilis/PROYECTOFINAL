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

public class ActividadConfiguracion extends Fragment implements View.OnClickListener {

    public ActividadConfiguracion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_configuracion, container, false);

        Button btnEditarDatos;
        btnEditarDatos = (Button) vistaADevolver.findViewById(R.id.btnEditarMisDatos);
        btnEditarDatos.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Integer id = vista.getId();
        Log.d("ila", "id: "+id);

        switch(vista.getId()) {
            case R.id.btnEditarMisDatos:
                Log.d("ila","entro al case editar mis datos");
                ActividadNavigationDrawer.irPinParaEditarDatos();
                break;
        }
    }

}
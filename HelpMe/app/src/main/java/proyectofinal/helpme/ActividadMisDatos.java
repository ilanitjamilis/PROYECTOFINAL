package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActividadMisDatos extends Fragment implements View.OnClickListener {

    public ActividadMisDatos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ActividadNavigationDrawer ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();
        String nombre = ActividadNavigationDrawer.tomarDatosUsuarioNombre();
        String apellido = ActividadNavigationDrawer.tomarDatosUsuarioApellido();

        View vistaADevolver = inflater.inflate(R.layout.actividad_mis_datos, container, false);

        //Setear nombre y apellido en TextView del layout de Mis Datos
        TextView TextViewNombre = (TextView)vistaADevolver.findViewById(R.id.nombreMisDatos);
        TextViewNombre.setText(nombre);
        TextView TextViewApellido = (TextView)vistaADevolver.findViewById(R.id.apellidoMisDatos);
        TextViewApellido.setText(apellido);

        return vistaADevolver;

    }

    public void onClick(View vista) {
        //ActividadNavigationDrawer ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();
        //ActividadNavigationDrawer.tomarDatosUsuario(vista); //Tiene que estar en el onCreate [no hay vista en el onCreate]
        //Como se edita el contenido de un textView con fragments??
    }

}

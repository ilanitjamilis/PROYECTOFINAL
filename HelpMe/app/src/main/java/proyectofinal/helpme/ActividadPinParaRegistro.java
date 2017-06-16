package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadPinParaRegistro extends Fragment implements View.OnClickListener {

    EditText pinIngresadoET;

    public ActividadPinParaRegistro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_pin_para_registro, container, false);

        pinIngresadoET = (EditText)vistaADevolver.findViewById(R.id.pinRegistracion);

        Button btnIngresoPin = (Button)vistaADevolver.findViewById(R.id.btnAceptarIngresoPin);
        btnIngresoPin.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        String pinUsuario = ActividadNavigationDrawer.PinUsuario().toString();
        Log.d("ila","pin usuario: "+pinUsuario);
        String pinIngresado = pinIngresadoET.getText().toString().trim();

        if(pinIngresado.compareTo("")==0){
            ActividadNavigationDrawer.MostrarMensaje("Ingrese pin");
        }
        else {
            if (pinUsuario.compareTo(pinIngresado) == 0) {
                //Ir editar datos
                ActividadNavigationDrawer.irEditarDatos();
            } else {
                //Pin incorrecto
                ActividadNavigationDrawer.MostrarMensaje("Pin incorrecto");
            }
        }
    }

}

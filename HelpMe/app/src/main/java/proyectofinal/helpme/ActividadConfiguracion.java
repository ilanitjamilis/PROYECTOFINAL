package proyectofinal.helpme;

import android.app.Notification;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class ActividadConfiguracion extends Fragment implements View.OnClickListener {

    Boolean checked = null;
    Switch SwitchNotf;

    public ActividadConfiguracion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View vistaADevolver = inflater.inflate(R.layout.actividad_configuracion, container, false);

        final ActividadNavigationDrawer miAdministrador = (ActividadNavigationDrawer) getActivity();

        SwitchNotf = (Switch) vistaADevolver.findViewById(R.id.switch1);
        SwitchNotf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Notification.Builder mBuilder = new Notification.Builder(vistaADevolver.getContext())
                        .setContentText("Hola " + miAdministrador.tomarDatosUsuarioNombre())
                        .setContentTitle("HelpMe!")
                        .setAutoCancel(false)
                        .setOngoing(true)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            }
        });

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
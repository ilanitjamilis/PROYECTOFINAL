package proyectofinal.helpme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

public class ActividadConfiguracion extends Fragment implements View.OnClickListener {

    Boolean checked = null;
    Switch SwitchNotf;
    TextView tvwConf;
    Boolean notificacionActivada;
    int mNotificationId = 001;


    public ActividadConfiguracion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View vistaADevolver = inflater.inflate(R.layout.actividad_configuracion, container, false);
        final ActividadNavigationDrawer miAdministrador = (ActividadNavigationDrawer) getActivity();
        SwitchNotf = (Switch) vistaADevolver.findViewById(R.id.switch1);
        notificacionActivada = miAdministrador.tomarDatosUsuarioNotificacion();
        if(notificacionActivada){
            SwitchNotf.setChecked(true);
        }
        else{
            SwitchNotf.setChecked(false);
        }
        SwitchNotf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    miAdministrador.setearNotificacionsuario(true);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getContext())
                                    .setContentTitle("HelpMe!")
                                    .setContentText("Desliza para ver...")
                                    .setSmallIcon(R.drawable.logo)
                                    .setAutoCancel(false)
                                    .setOngoing(true)
                                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Hola " + miAdministrador.tomarDatosUsuarioNombre() + " " + miAdministrador.tomarDatosUsuarioApellido() + " (DNI " + miAdministrador.tomarDatosUsuarioDni() + ") , tus números de emergencia son: " + miAdministrador.tomarDatosUsuarioContactoEmergencia1() + " y " + miAdministrador.tomarDatosUsuarioContactoEmergencia2()))
                                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

                    PendingIntent resultPendingIntent =
                            PendingIntent.getActivity(
                                    getContext(),
                                    0,
                                    new Intent(getContext(), ActividadNavigationDrawer.class),
                                    PendingIntent.FLAG_UPDATE_CURRENT);

                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotifyMgr = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());

                } else {
                    miAdministrador.setearNotificacionsuario(false);
                    ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancel(mNotificationId);
                }

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
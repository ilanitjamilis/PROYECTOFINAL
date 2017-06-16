package proyectofinal.helpme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ActividadEditarRegistro extends Fragment implements View.OnClickListener {

    public ActividadEditarRegistro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_editar_registro, container, false);

        Button aceptarEditarRegistro = (Button)vistaADevolver.findViewById(R.id.btnAceptarEditarMisDatos);
        aceptarEditarRegistro.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

        adb.setTitle("¿Está seguro que quiere editar sus datos?");
        //Deberian ir los datos para que los vea

        adb.setIcon(android.R.drawable.ic_dialog_alert);

        adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Editar mis datos
                dialog.cancel();
            } });


        adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            } });
        adb.show();
    }

}

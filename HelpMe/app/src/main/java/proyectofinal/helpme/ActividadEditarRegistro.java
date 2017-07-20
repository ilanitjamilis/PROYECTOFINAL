package proyectofinal.helpme;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActividadEditarRegistro extends Fragment implements View.OnClickListener {

    EditText nombre;
    EditText apellido;
    EditText nacimiento;
    EditText edad;
    EditText estatura;
    EditText peso;
    Spinner genero;
    Spinner grupoSanguineo;
    EditText obraSocial;
    EditText numEmergencia;
    EditText alergias;
    EditText medicProhib;
    EditText enfermedades;
    EditText contactEmergencia1;
    EditText contactEmergencia2;

    String nombreIngresado;
    String apellidoIngresado;
    String nacimientoIngresado;
    String edadIngresado;
    String estaturaIngresado;
    String pesoIngresado;
    String generoIngresado;
    String grupoSanguineoIngresado;
    String obraSocialIngresado;
    String numEmergenciaIngresado;
    String alergiasIngresado;
    String medicProhibIngresado;
    String enfermedadesIngresado;
    String contactEmergencia1Ingresado;
    String contactEmergencia2Ingresado;

    ArrayList<String> opcionesGrupoSanguineo;
    ArrayList<String> opcionesGenero;

    public ActividadEditarRegistro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_editar_registro, container, false);

        ActividadNavigationDrawer ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        ActividadNavigationDrawer.navigationView.setCheckedItem(R.id.nav_menu2);

        opcionesGrupoSanguineo = new ArrayList<>();
        opcionesGrupoSanguineo.add("-");
        opcionesGrupoSanguineo.add("A+");
        opcionesGrupoSanguineo.add("A-");
        opcionesGrupoSanguineo.add("B+");
        opcionesGrupoSanguineo.add("B-");
        opcionesGrupoSanguineo.add("AB+");
        opcionesGrupoSanguineo.add("AB-");
        opcionesGrupoSanguineo.add("0+");
        opcionesGrupoSanguineo.add("0-");

        opcionesGenero = new ArrayList<>();
        opcionesGenero.add("-");
        opcionesGenero.add("Femenino");
        opcionesGenero.add("Masculino");
        opcionesGenero.add("Otro");

        ArrayAdapter<String> adaptadorGS = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, opcionesGrupoSanguineo);
        adaptadorGS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adaptadorG = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, opcionesGenero);
        adaptadorG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nombre = (EditText)vistaADevolver.findViewById(R.id.nombreMisDatos);
        apellido = (EditText)vistaADevolver.findViewById(R.id.apellidoMisDatos);
        nacimiento = (EditText)vistaADevolver.findViewById(R.id.fechaNacimientoMisDatos);
        edad = (EditText)vistaADevolver.findViewById(R.id.edadMisDatos);
        estatura = (EditText)vistaADevolver.findViewById(R.id.estaturaMisDatos);
        peso = (EditText)vistaADevolver.findViewById(R.id.pesoMisDatos);
        genero = (Spinner) vistaADevolver.findViewById(R.id.generoMisDatos);
        genero.setAdapter(adaptadorG);
        grupoSanguineo = (Spinner)vistaADevolver.findViewById(R.id.grupoSanguineoMisDatos);
        grupoSanguineo.setAdapter(adaptadorGS);
        obraSocial = (EditText)vistaADevolver.findViewById(R.id.obraSocialMisDatos);
        numEmergencia = (EditText)vistaADevolver.findViewById(R.id.numeroEmergenciaMisDatos);
        alergias = (EditText)vistaADevolver.findViewById(R.id.alergiasMisDatos);
        medicProhib = (EditText)vistaADevolver.findViewById(R.id.medicamentosProhibidosMisDatos);
        enfermedades = (EditText)vistaADevolver.findViewById(R.id.enfermedadesCronicasMisDatos);
        contactEmergencia1 = (EditText)vistaADevolver.findViewById(R.id.contactoemergencia1MisDatos);
        contactEmergencia2 = (EditText)vistaADevolver.findViewById(R.id.contactoemergenci21MisDatos);

        String nombreGuardado = ActividadNavigationDrawer.tomarDatosUsuarioNombre();
        nombre.setText(nombreGuardado);
        String apellidoGuardado = ActividadNavigationDrawer.tomarDatosUsuarioApellido();
        apellido.setText(apellidoGuardado);
        String fechaNacimientoGuardado = ActividadNavigationDrawer.tomarDatosUsuarioFechaNacimiento();
        nacimiento.setText(fechaNacimientoGuardado);
        String edadGuardado = ActividadNavigationDrawer.tomarDatosUsuarioEdad();
        edad.setText(edadGuardado);
        String estaturaGuardado = ActividadNavigationDrawer.tomarDatosUsuarioEstatura();
        estatura.setText(estaturaGuardado);
        String pesoGuardado = ActividadNavigationDrawer.tomarDatosUsuarioPeso();
        peso.setText(pesoGuardado);
        String generoGuardado = ActividadNavigationDrawer.tomarDatosUsuarioGenero();
        if(generoGuardado.compareTo("Femenino")==0){
            genero.setSelection(1);
        }
        if(generoGuardado.compareTo("Masculino")==0){
            genero.setSelection(2);
        }
        if(generoGuardado.compareTo("Otro")==0){
            genero.setSelection(3);
        }
        String grupoSanguineoGuardado = ActividadNavigationDrawer.tomarDatosUsuarioGrupoSanguineo();
        if(grupoSanguineoGuardado.compareTo("A+")==0){
            grupoSanguineo.setSelection(1);
        }
        if(grupoSanguineoGuardado.compareTo("A-")==0){
            grupoSanguineo.setSelection(2);
        }
        if(grupoSanguineoGuardado.compareTo("B+")==0){
            grupoSanguineo.setSelection(3);
        }
        if(grupoSanguineoGuardado.compareTo("B-")==0){
            grupoSanguineo.setSelection(4);
        }
        if(grupoSanguineoGuardado.compareTo("AB+")==0){
            grupoSanguineo.setSelection(5);
        }
        if(grupoSanguineoGuardado.compareTo("AB-")==0){
            grupoSanguineo.setSelection(6);
        }
        if(grupoSanguineoGuardado.compareTo("0+")==0){
            grupoSanguineo.setSelection(7);
        }
        if(grupoSanguineoGuardado.compareTo("0-")==0){
            grupoSanguineo.setSelection(8);
        }
        String obraSocialGuardado = ActividadNavigationDrawer.tomarDatosUsuarioObraSocial();
        obraSocial.setText(obraSocialGuardado);
        String numeroEmergenciaObraSocialGuardado = ActividadNavigationDrawer.tomarDatosUsuarioNumeroEmergenciaObraSocial();
        numEmergencia.setText(numeroEmergenciaObraSocialGuardado);
        String alergiasGuardado = ActividadNavigationDrawer.tomarDatosUsuarioAlergias();
        alergias.setText(alergiasGuardado);
        String medicamentosProhibidosGuardado = ActividadNavigationDrawer.tomarDatosUsuarioMedicamentosProhibidos();
        medicProhib.setText(medicamentosProhibidosGuardado);
        String enfermedadesCronicasGuardado = ActividadNavigationDrawer.tomarDatosUsuarioEnfermedadesCronicas();
        enfermedades.setText(enfermedadesCronicasGuardado);
        String contactoEmergencia1Guardado = ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia1();
        contactEmergencia1.setText(contactoEmergencia1Guardado);
        String contactoEmergencia2Guardado = ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia2();
        contactEmergencia2.setText(contactoEmergencia2Guardado);

        Button aceptarEditarRegistro = (Button)vistaADevolver.findViewById(R.id.btnAceptarEditarMisDatos);
        aceptarEditarRegistro.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        final ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        nombreIngresado = nombre.getText().toString().trim();
        apellidoIngresado = apellido.getText().toString().trim();
        nacimientoIngresado = nacimiento.getText().toString().trim();
        edadIngresado = edad.getText().toString().trim();
        estaturaIngresado = estatura.getText().toString().trim();
        pesoIngresado = peso.getText().toString().trim();
        int posicionG = genero.getSelectedItemPosition();
        generoIngresado = genero.getItemAtPosition(posicionG).toString().trim();
        int posicionGS = grupoSanguineo.getSelectedItemPosition();
        grupoSanguineoIngresado = grupoSanguineo.getItemAtPosition(posicionGS).toString().trim();
        obraSocialIngresado = obraSocial.getText().toString().trim();
        numEmergenciaIngresado = numEmergencia.getText().toString().trim();
        alergiasIngresado = alergias.getText().toString().trim();
        medicProhibIngresado = medicProhib.getText().toString().trim();
        enfermedadesIngresado = enfermedades.getText().toString().trim();
        contactEmergencia1Ingresado = contactEmergencia1.getText().toString().trim();
        contactEmergencia2Ingresado = contactEmergencia2.getText().toString().trim();

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

        adb.setTitle("¿Está seguro que quiere editar sus datos?");
        //Deberian ir los datos para que los vea

        adb.setIcon(android.R.drawable.ic_dialog_alert);

        adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Editar mis datos
                if(nombreIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearNombreUsuario(nombreIngresado);
                }
                if(apellidoIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearApellidoUsuario(apellidoIngresado);
                }
                if(nombreIngresado.compareTo("")==0||apellidoIngresado.compareTo("")==0){
                    ActividadNavigationDrawer.MostrarMensaje("El nombre y el apellido son obligatorios");
                }
                if(nacimientoIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearFechaNacimientoUsuario(nacimientoIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearFechaNacimientoUsuario("-");
                }
                if(edadIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearEdadUsuario(edadIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearEdadUsuario("-");
                }
                if(estaturaIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearEstaturaUsuario(estaturaIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearEstaturaUsuario("-");
                }
                if(pesoIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearPesoUsuario(pesoIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearPesoUsuario("-");
                }
                if(generoIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearGeneroUsuario(generoIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearGeneroUsuario("-");
                }
                if(grupoSanguineoIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearGrupoSanguineoUsuario(grupoSanguineoIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearGrupoSanguineoUsuario("-");
                }
                if(obraSocialIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearObraSocialUsuario(obraSocialIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearObraSocialUsuario("-");
                }
                if(numEmergenciaIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearNumeroEmergenciaObraSocialUsuario(numEmergenciaIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearNumeroEmergenciaObraSocialUsuario("-");
                }
                if(alergiasIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearAlergiasUsuario(alergiasIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearAlergiasUsuario("-");
                }
                if(medicProhibIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearMedicamentosProhibidosUsuario(medicProhibIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearMedicamentosProhibidosUsuario("-");
                }
                if(enfermedadesIngresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearEnfermedadesCronicasUsuario(enfermedadesIngresado);
                }
                else{
                    ActividadNavigationDrawer.setearEnfermedadesCronicasUsuario("-");
                }
                if(contactEmergencia1Ingresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearContactoEmergencia1Usuario(contactEmergencia1Ingresado);
                }
                else{
                    ActividadNavigationDrawer.setearContactoEmergencia1Usuario("-");
                }
                if(contactEmergencia2Ingresado.compareTo("")!=0){
                    ActividadNavigationDrawer.setearContactoEmergencia2Usuario(contactEmergencia2Ingresado);
                }
                else{
                    ActividadNavigationDrawer.setearContactoEmergencia2Usuario("-");
                }

                boolean notificacionActivada = ActividadNavigationDrawer.tomarDatosUsuarioNotificacion();
                if(notificacionActivada){
                    ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancel(001);

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getContext())
                                    .setContentTitle("HelpMe!")
                                    .setContentText("Desliza para ver...")
                                    .setSmallIcon(R.drawable.logo)
                                    .setAutoCancel(false)
                                    .setOngoing(true)
                                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Hola " + ActividadNavigationDrawer.tomarDatosUsuarioNombre() + " " + ActividadNavigationDrawer.tomarDatosUsuarioApellido() + ", tus números de emergencia son: " + ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia1() + " y " + ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia2()))
                                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

                    PendingIntent resultPendingIntent =
                            PendingIntent.getActivity(
                                    getContext(),
                                    0,
                                    new Intent(getContext(), ActividadNavigationDrawer.class),
                                    PendingIntent.FLAG_UPDATE_CURRENT);

                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotifyMgr = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotifyMgr.notify(001, mBuilder.build());
                }

                ActividadNavigationDrawer.MostrarMensaje("Datos guardados correctamente");
                ActividadNavigationDrawer.irMisDatos();

            } });


        adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            } });
        adb.show();
    }

}

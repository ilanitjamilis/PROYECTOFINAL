package proyectofinal.helpme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActividadMisDatos extends Fragment implements View.OnClickListener {

    ActividadNavigationDrawer ActividadNavigationDrawer;

    public ActividadMisDatos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        String nombre = ActividadNavigationDrawer.tomarDatosUsuarioNombre();
        String apellido = ActividadNavigationDrawer.tomarDatosUsuarioApellido();
        String fechaNacimiento = ActividadNavigationDrawer.tomarDatosUsuarioFechaNacimiento();
        String edad = ActividadNavigationDrawer.tomarDatosUsuarioEdad();
        String estatura = ActividadNavigationDrawer.tomarDatosUsuarioEstatura();
        String peso = ActividadNavigationDrawer.tomarDatosUsuarioPeso();
        String genero = ActividadNavigationDrawer.tomarDatosUsuarioGenero();
        String grupoSanguineo = ActividadNavigationDrawer.tomarDatosUsuarioGrupoSanguineo();
        String obraSocial = ActividadNavigationDrawer.tomarDatosUsuarioObraSocial();
        String numeroEmergenciaObraSocial = ActividadNavigationDrawer.tomarDatosUsuarioNumeroEmergenciaObraSocial();
        String alergias = ActividadNavigationDrawer.tomarDatosUsuarioAlergias();
        String medicamentosProhibidos = ActividadNavigationDrawer.tomarDatosUsuarioMedicamentosProhibidos();
        String enfermedadesCronicas = ActividadNavigationDrawer.tomarDatosUsuarioEnfermedadesCronicas();
        String contactoEmergencia1 = ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia1();
        String contactoEmergencia2 = ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia2();

        View vistaADevolver = inflater.inflate(R.layout.actividad_mis_datos, container, false);

        Button btnEditarMisDatos = (Button)vistaADevolver.findViewById(R.id.btnEditarMisDatos);
        btnEditarMisDatos.setOnClickListener(this);

        TextView TextViewNombre = (TextView)vistaADevolver.findViewById(R.id.nombreMisDatos);
        TextViewNombre.setText(nombre);
        if(nombre.compareTo("-")!=0){
            TextViewNombre.setTextColor(Color.BLUE);
        }

        TextView TextViewApellido = (TextView)vistaADevolver.findViewById(R.id.apellidoMisDatos);
        TextViewApellido.setText(apellido);
        if(apellido.compareTo("-")!=0){
            TextViewApellido.setTextColor(Color.BLUE);
        }

        TextView TextViewFechaNacimiento = (TextView)vistaADevolver.findViewById(R.id.fechaNacimientoMisDatos);
        TextViewFechaNacimiento.setText(fechaNacimiento);
        if(fechaNacimiento.compareTo("-")!=0){
            TextViewFechaNacimiento.setTextColor(Color.BLUE);
        }

        TextView TextViewEdad = (TextView)vistaADevolver.findViewById(R.id.edadMisDatos);
        TextViewEdad.setText(edad);
        if(edad.compareTo("-")!=0){
            TextViewEdad.setTextColor(Color.BLUE);
        }

        TextView TextViewEstatura = (TextView)vistaADevolver.findViewById(R.id.estaturaMisDatos);
        if(estatura.compareTo("-")!=0) {
            TextViewEstatura.setText(estatura + " cm");
            TextViewFechaNacimiento.setTextColor(Color.BLUE);
        }
        else{
            TextViewEstatura.setText(estatura);
        }

        TextView TextViewPeso = (TextView)vistaADevolver.findViewById(R.id.pesoMisDatos);
        if(peso.compareTo("-")!=0) {
            TextViewPeso.setText(peso + " kg");
            TextViewFechaNacimiento.setTextColor(Color.BLUE);
        }
        else{
            TextViewPeso.setText(peso);
        }

        TextView TextViewGenero = (TextView)vistaADevolver.findViewById(R.id.generoMisDatos);
        TextViewGenero.setText(genero);
        if(genero.compareTo("-")!=0){
            TextViewGenero.setTextColor(Color.BLUE);
        }

        TextView TextViewGrupoSanguineo = (TextView)vistaADevolver.findViewById(R.id.grupoSanguineoMisDatos);
        TextViewGrupoSanguineo.setText(grupoSanguineo);
        if(grupoSanguineo.compareTo("-")!=0){
            TextViewGrupoSanguineo.setTextColor(Color.BLUE);
        }

        TextView TextViewObraSocial = (TextView)vistaADevolver.findViewById(R.id.obraSocialMisDatos);
        TextViewObraSocial.setText(obraSocial);
        if(obraSocial.compareTo("-")!=0){
            TextViewObraSocial.setTextColor(Color.BLUE);
        }

        TextView TextViewNumeroEmergenciaObraSocial = (TextView)vistaADevolver.findViewById(R.id.numeroEmergenciaMisDatos);
        TextViewNumeroEmergenciaObraSocial.setText(numeroEmergenciaObraSocial);
        if(numeroEmergenciaObraSocial.compareTo("-")!=0){
            TextViewNumeroEmergenciaObraSocial.setTextColor(Color.BLUE);
        }

        TextView TextViewAlergias = (TextView)vistaADevolver.findViewById(R.id.alergiasMisDatos);
        TextViewAlergias.setText(alergias);
        if(alergias.compareTo("-")!=0){
            TextViewAlergias.setTextColor(Color.BLUE);
        }

        TextView TextViewMedicamentosProhibidos = (TextView)vistaADevolver.findViewById(R.id.medicamentosProhibidosMisDatos);
        TextViewMedicamentosProhibidos.setText(medicamentosProhibidos);
        if(medicamentosProhibidos.compareTo("-")!=0){
            TextViewMedicamentosProhibidos.setTextColor(Color.BLUE);
        }

        TextView TextViewEnfermedadesCronicas = (TextView)vistaADevolver.findViewById(R.id.enfermedadesCronicasMisDatos);
        TextViewEnfermedadesCronicas.setText(enfermedadesCronicas);
        if(enfermedadesCronicas.compareTo("-")!=0){
            TextViewEnfermedadesCronicas.setTextColor(Color.BLUE);
        }

        TextView TextViewContactoEmergencia1 = (TextView)vistaADevolver.findViewById(R.id.contactoemergencia1MisDatos);
        TextViewContactoEmergencia1.setText(contactoEmergencia1);
        if(contactoEmergencia1.compareTo("-")!=0){
            TextViewContactoEmergencia1.setTextColor(Color.BLUE);
        }

        TextView TextViewContactoEmergencia2 = (TextView)vistaADevolver.findViewById(R.id.contactoemergenci21MisDatos);
        TextViewContactoEmergencia2.setText(contactoEmergencia2);
        if(contactoEmergencia2.compareTo("-")!=0){
            TextViewContactoEmergencia2.setTextColor(Color.BLUE);
        }

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
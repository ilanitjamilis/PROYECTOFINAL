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

    String dni;
    String nombre;
    String apellido;
    String fechaNacimiento;
    String edad;
    String estatura;
    String peso;
    String genero;
    String grupoSanguineo;
    String obraSocial;
    String numeroEmergenciaObraSocial;
    String alergias;
    String medicamentosProhibidos;
    String enfermedadesCronicas;
    String contactoEmergencia1;
    String contactoEmergencia2;

    public ActividadMisDatos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        dni = ActividadNavigationDrawer.tomarDatosUsuarioDni();
        nombre = ActividadNavigationDrawer.tomarDatosUsuarioNombre();
        apellido = ActividadNavigationDrawer.tomarDatosUsuarioApellido();
        fechaNacimiento = ActividadNavigationDrawer.tomarDatosUsuarioFechaNacimiento();
        edad = ActividadNavigationDrawer.tomarDatosUsuarioEdad();
        estatura = ActividadNavigationDrawer.tomarDatosUsuarioEstatura();
        peso = ActividadNavigationDrawer.tomarDatosUsuarioPeso();
        genero = ActividadNavigationDrawer.tomarDatosUsuarioGenero();
        grupoSanguineo = ActividadNavigationDrawer.tomarDatosUsuarioGrupoSanguineo();
        obraSocial = ActividadNavigationDrawer.tomarDatosUsuarioObraSocial();
        numeroEmergenciaObraSocial = ActividadNavigationDrawer.tomarDatosUsuarioNumeroEmergenciaObraSocial();
        alergias = ActividadNavigationDrawer.tomarDatosUsuarioAlergias();
        medicamentosProhibidos = ActividadNavigationDrawer.tomarDatosUsuarioMedicamentosProhibidos();
        enfermedadesCronicas = ActividadNavigationDrawer.tomarDatosUsuarioEnfermedadesCronicas();
        contactoEmergencia1 = ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia1();
        contactoEmergencia2 = ActividadNavigationDrawer.tomarDatosUsuarioContactoEmergencia2();

        View vistaADevolver = inflater.inflate(R.layout.actividad_mis_datos, container, false);

        Button btnEditarMisDatos = (Button)vistaADevolver.findViewById(R.id.btnEditarMisDatos);
        btnEditarMisDatos.setOnClickListener(this);
        ImageButton btnllamarContacto1 = (ImageButton)vistaADevolver.findViewById(R.id.btnLlamarContacto1);
        btnllamarContacto1.setOnClickListener(this);
        ImageButton btnllamarContacto2 = (ImageButton) vistaADevolver.findViewById(R.id.btnLlamarContacto2);
        btnllamarContacto2.setOnClickListener(this);


        TextView TextViewDni = (TextView)vistaADevolver.findViewById(R.id.dniMisDatos);
        TextViewDni.setText(dni);
        if(dni.compareTo("-")!=0){
            TextViewDni.setTextColor(Color.BLUE);
        }

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
        if(edad.compareTo("-")!=0){
            if(edad.compareTo("1")==0){
                TextViewEdad.setText(edad + " año");
            }
            else {
                TextViewEdad.setText(edad + " años");
            }
            TextViewEdad.setTextColor(Color.BLUE);
        }
        else{
            TextViewEdad.setText(edad);
        }

        TextView TextViewEstatura = (TextView)vistaADevolver.findViewById(R.id.estaturaMisDatos);
        if(estatura.compareTo("-")!=0) {
            TextViewEstatura.setText(estatura + " cm");
            TextViewEstatura.setTextColor(Color.BLUE);
        }
        else{
            TextViewEstatura.setText(estatura);
        }

        TextView TextViewPeso = (TextView)vistaADevolver.findViewById(R.id.pesoMisDatos);
        if(peso.compareTo("-")!=0) {
            TextViewPeso.setText(peso + " kg");
            TextViewPeso.setTextColor(Color.BLUE);
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
        else{
            btnllamarContacto1.setVisibility(View.GONE);
        }

        TextView TextViewContactoEmergencia2 = (TextView)vistaADevolver.findViewById(R.id.contactoemergenci21MisDatos);
        TextViewContactoEmergencia2.setText(contactoEmergencia2);
        if(contactoEmergencia2.compareTo("-")!=0){
            TextViewContactoEmergencia2.setTextColor(Color.BLUE);
        }
        else{
            btnllamarContacto2.setVisibility(View.GONE);
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
                ActividadNavigationDrawer.irPinParaEditarDatos();
                break;
            case R.id.btnLlamarContacto1:
                ActividadNavigationDrawer.llamarContactoEmergencia(contactoEmergencia1);
                break;
            case R.id.btnLlamarContacto2:
                ActividadNavigationDrawer.llamarContactoEmergencia(contactoEmergencia2);
                break;
        }
    }

}
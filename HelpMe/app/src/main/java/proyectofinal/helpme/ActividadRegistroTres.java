package proyectofinal.helpme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadRegistroTres extends AppCompatActivity {

    Integer pinRegistracion;

    FragmentManager AdministradorDeFragments = getSupportFragmentManager();
    FragmentTransaction TransaccionDeFragment;

    EditText pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_tres);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        pin = (EditText)findViewById(R.id.pinRegistracion);

        pin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registrarUsuario2();
                }
                return false;
            }
        });

    }

    public void registrarUsuario(View vista){
        Bundle datosRecibidos = this.getIntent().getExtras();
        final String dni = datosRecibidos.getString("dni");
        final String nombre = datosRecibidos.getString("nombre");
        final String apellido = datosRecibidos.getString("apellido");

        String error = "";

        String pinR = pin.getText().toString().trim();
        pinRegistracion = Integer.valueOf(pinR);

        //Recibo nombre y apellido en bundle
        //Ponerlos en los strings que ya declaré

        if(pinR.compareTo("")==0){
            error="Ingrese un pin";
        }
        if(pinR.length()!=4){
            error="Pin inválido, deben ser 4 números";
        }
        if(error.compareTo("")!=0){
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
        else{
            utilidades.sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = utilidades.sharedPref.edit();

            editor.putBoolean("firstStart2", false);
            editor.apply();


            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("¡REGISTRO EXITOSO! // COMPLETAR MIS DATOS");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setPositiveButton("AHORA", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Poner el fragment de editar Mis Datos
                    Intent irActividadPrincipal = new Intent (ActividadRegistroTres.this, ActividadNavigationDrawer.class);
                    Bundle misDatos = new Bundle();
                    misDatos.putString("ir","editarDatos");
                    misDatos.putString("anterior","registro");
                    misDatos.putString("dni",dni);
                    misDatos.putString("nombre",nombre);
                    misDatos.putString("apellido",apellido);
                    misDatos.putInt("pin",pinRegistracion);
                    misDatos.putString("anterior","registro");
                    irActividadPrincipal.putExtras(misDatos);
                    startActivity(irActividadPrincipal);
                } });


            adb.setNegativeButton("MÁS TARDE", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent irActividadPrincipal = new Intent (ActividadRegistroTres.this, ActividadNavigationDrawer.class);
                    Bundle misDatos = new Bundle();
                    misDatos.putString("ir","principal");
                    misDatos.putString("anterior","registro");
                    misDatos.putString("dni",dni);
                    misDatos.putString("nombre",nombre);
                    misDatos.putString("apellido",apellido);
                    misDatos.putInt("pin",pinRegistracion);
                    misDatos.putString("anterior","registro");
                    irActividadPrincipal.putExtras(misDatos);
                    startActivity(irActividadPrincipal);
                } });
            adb.show();
        }

    }

    public void registrarUsuario2(){
        Bundle datosRecibidos = this.getIntent().getExtras();
        final String dni = datosRecibidos.getString("dni");
        final String nombre = datosRecibidos.getString("nombre");
        final String apellido = datosRecibidos.getString("apellido");

        String error = "";

        String pinR = pin.getText().toString().trim();
        pinRegistracion = Integer.valueOf(pinR);

        //Recibo nombre y apellido en bundle
        //Ponerlos en los strings que ya declaré

        if(pinR.compareTo("")==0){
            error="Ingrese un pin";
        }
        if(pinR.length()!=4){
            error="Pin inválido, deben ser 4 números";
        }
        if(error.compareTo("")!=0){
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
        else{
            utilidades.sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = utilidades.sharedPref.edit();

            editor.putBoolean("firstStart2", false);
            editor.apply();


            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("¡REGISTRO EXITOSO! // COMPLETAR MIS DATOS");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setPositiveButton("AHORA", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Poner el fragment de editar Mis Datos
                    Intent irActividadPrincipal = new Intent (ActividadRegistroTres.this, ActividadNavigationDrawer.class);
                    Bundle misDatos = new Bundle();
                    misDatos.putString("ir","editarDatos");
                    misDatos.putString("anterior","registro");
                    misDatos.putString("dni",dni);
                    misDatos.putString("nombre",nombre);
                    misDatos.putString("apellido",apellido);
                    misDatos.putInt("pin",pinRegistracion);
                    misDatos.putString("anterior","registro");
                    irActividadPrincipal.putExtras(misDatos);
                    startActivity(irActividadPrincipal);
                } });


            adb.setNegativeButton("MÁS TARDE", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent irActividadPrincipal = new Intent (ActividadRegistroTres.this, ActividadNavigationDrawer.class);
                    Bundle misDatos = new Bundle();
                    misDatos.putString("ir","principal");
                    misDatos.putString("anterior","registro");
                    misDatos.putString("dni",dni);
                    misDatos.putString("nombre",nombre);
                    misDatos.putString("apellido",apellido);
                    misDatos.putInt("pin",pinRegistracion);
                    misDatos.putString("anterior","registro");
                    irActividadPrincipal.putExtras(misDatos);
                    startActivity(irActividadPrincipal);
                } });
            adb.show();
        }

    }
}

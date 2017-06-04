package proyectofinal.helpme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadRegistroTres extends AppCompatActivity {

    SharedPreferences sharedPref;
    Integer pinRegistracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_tres);
    }

    public void registrarUsuario(View vista){

        String error = "";

        EditText pin = (EditText)findViewById(R.id.pinRegistracion);
        String pinR = pin.getText().toString().trim();
        pinRegistracion = Integer.valueOf(pinR);

        //Recibo nombre y apellido en bundle
        //Ponerlos en los strings que ya declaré

        if(pinR.compareTo("")==0){
            error="Ingrese un pin";
        }
        if(pinR.length()!=4){
            error="Pin inválido";
        }
        if(error.compareTo("")!=0){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("PIN", pinRegistracion);
            editor.commit();

            editor.putBoolean("firstStart", false);
            editor.apply();


            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("¡REGISTRO EXITOSO! // COMPLETAR MIS DATOS");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setPositiveButton("AHORA", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent irEditarDatos = new Intent (ActividadRegistroTres.this, ActividadEditarRegistro.class);
                    startActivity(irEditarDatos);

                } });


            adb.setNegativeButton("MÁS TARDE", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent irActividadPrincipal = new Intent (ActividadRegistroTres.this, ActividadNavigationDrawer.class);
                    startActivity(irActividadPrincipal);
                } });
            adb.show();
        }

    }
}

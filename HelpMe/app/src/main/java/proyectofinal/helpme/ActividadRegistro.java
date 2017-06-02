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

public class ActividadRegistro extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro);
    }

    public void registrarUsuario(View vista){

        Boolean error = false;
        String errores = "";

        EditText pin = (EditText)findViewById(R.id.pinRegistracion);
        String pinR = pin.getText().toString().trim();
        Integer pinRegistracion = Integer.valueOf(pinR);

        EditText nombre = (EditText)findViewById(R.id.nombreRegistracion);
        String nombreRegistracion = nombre.getText().toString().trim();

        EditText apellido = (EditText)findViewById(R.id.apellidoRegistracion);
        String apellidoRegistracion = apellido.getText().toString().trim();

        if(pinR.compareTo("")==0 ){
            error = true;
            errores+="Ingrese un pin";
        }
        if(pinR.length()!=4){
            error = true;
            errores+="Pin inválido";
        }
        if(nombreRegistracion.compareTo("")==0){
            error = true;
            errores+="Ingrese su nombre";
        }
        if(apellidoRegistracion.compareTo("")==0){
            error = true;
            errores+="Ingrese su apellido";
        }
        if(error){
            Toast.makeText(this, errores, Toast.LENGTH_LONG).show();
        }
        else{
            sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("PIN", pinRegistracion);
            editor.commit();

            editor.putString("nombreUsuario", nombreRegistracion);
            editor.commit();

            editor.putString("apellidoUsuario", apellidoRegistracion);
            editor.commit();


            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("¡REGISTRO EXITOSO! // COMPLETAR MIS DATOS");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setPositiveButton("AHORA", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent irRegistro = new Intent (ActividadRegistro.this, ActividadEditarRegistro.class);
                    startActivity(irRegistro);

                } });


            adb.setNegativeButton("MÁS TARDE", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                } });
            adb.show();
        }


    }
}

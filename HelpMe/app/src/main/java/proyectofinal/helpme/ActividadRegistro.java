package proyectofinal.helpme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public void ingresoNombre(View vista){
        EditText nombreIngresado = (EditText)findViewById(R.id.nombreRegistracion);
        String nombre = nombreIngresado.getText().toString().trim();

        if(nombre.compareTo("")!=0){
            sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nombreUsuario", nombre);
            editor.commit();

            String nombreUsuarioGuardado = sharedPref.getString("nombreUsuario", "NONE");
            Log.d("ila", "nombre usuario guardado: "+nombreUsuarioGuardado);
            //NO SE GUARDA BIEN O NO SE RECIBE BIEN

            Intent irIngresarApellido = new Intent (ActividadRegistro.this, ActividadRegistroDos.class);
            startActivity(irIngresarApellido);
        }
        else{
            //Toast
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_LONG).show();
        }
    }
}

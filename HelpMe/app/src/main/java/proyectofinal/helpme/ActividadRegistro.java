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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void ingresoNombre(View vista){
        EditText nombreIngresado = (EditText)findViewById(R.id.nombreRegistracion);
        String nombre = nombreIngresado.getText().toString().trim();

        if(nombre.compareTo("")!=0){
            Intent irIngresarApellido = new Intent (ActividadRegistro.this, ActividadRegistroDos.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("nombre",nombre);
            irIngresarApellido.putExtras(misDatos);
            startActivity(irIngresarApellido);
        }
        else{
            //Toast
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_LONG).show();
        }
    }
}

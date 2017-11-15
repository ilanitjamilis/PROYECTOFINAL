package proyectofinal.helpme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadRegistro extends AppCompatActivity {

    EditText nombreIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        nombreIngresado = (EditText)findViewById(R.id.nombreRegistracion);

        nombreIngresado.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ingresoNombre2();
                }
                return false;
            }
        });
    }

    public void ingresoNombre(View vista){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String dni = datosRecibidos.getString("dni");
        Log.d("dni", "dni registro 1: "+dni);

        String nombre = nombreIngresado.getText().toString().trim();

        if(nombre.compareTo("")!=0){
            Intent irIngresarApellido = new Intent (ActividadRegistro.this, ActividadRegistroDos.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("dni",dni);
            misDatos.putString("nombre",nombre);
            irIngresarApellido.putExtras(misDatos);
            startActivity(irIngresarApellido);
        }
        else{
            //Toast
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_LONG).show();
        }
    }

    public void ingresoNombre2(){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String dni = datosRecibidos.getString("dni");
        Log.d("dni", "dni registro 1: "+dni);

        String nombre = nombreIngresado.getText().toString().trim();

        if(nombre.compareTo("")!=0){
            Intent irIngresarApellido = new Intent (ActividadRegistro.this, ActividadRegistroDos.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("dni",dni);
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

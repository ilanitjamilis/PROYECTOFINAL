package proyectofinal.helpme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadRegistroCero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_cero);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void ingresoDni(View vista){
        EditText dniIngresado = (EditText)findViewById(R.id.dniRegistracion);
        String dni = dniIngresado.getText().toString().trim();

        if(dni.compareTo("")!=0){
            Intent irIngresarNombre = new Intent (ActividadRegistroCero.this, ActividadRegistro.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("dni",dni);
            irIngresarNombre.putExtras(misDatos);
            startActivity(irIngresarNombre);
        }
        else{
            //Toast
            Toast.makeText(this, "Ingrese su DNI", Toast.LENGTH_LONG).show();
        }
    }
}

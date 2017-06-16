package proyectofinal.helpme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadRegistroDos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_dos);
    }

    public void ingresoApellido(View vista){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String nombre = datosRecibidos.getString("nombre");

        EditText apellidoIngresado = (EditText)findViewById(R.id.apellidoRegistracion);
        String apellido = apellidoIngresado.getText().toString().trim();

        if(apellido.compareTo("")!=0){
            Intent irIngresarPin = new Intent (ActividadRegistroDos.this, ActividadRegistroTres.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("nombre",nombre);
            misDatos.putString("apellido",apellido);
            irIngresarPin.putExtras(misDatos);
            startActivity(irIngresarPin);
        }
        else{
            //Toast
            Toast.makeText(this, "Ingrese su apellido", Toast.LENGTH_LONG).show();
        }
    }
}

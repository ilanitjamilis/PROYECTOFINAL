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

    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_dos);
    }

    public void ingresoApellido(View vista){
        EditText apellidoIngresado = (EditText)findViewById(R.id.apellidoRegistracion);
        String apellido = apellidoIngresado.getText().toString().trim();

        if(apellido.compareTo("")!=0){
            sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("apellidoUsuario", apellido);
            editor.commit();

            Intent irIngresarPin = new Intent (ActividadRegistroDos.this, ActividadRegistroTres.class);
            startActivity(irIngresarPin);
        }
        else{
            //Toast
            Toast.makeText(this, "Ingrese su apellido", Toast.LENGTH_LONG).show();
        }
    }
}

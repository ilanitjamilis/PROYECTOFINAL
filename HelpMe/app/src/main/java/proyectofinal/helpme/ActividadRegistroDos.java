package proyectofinal.helpme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadRegistroDos extends AppCompatActivity {

    EditText apellidoIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_dos);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        apellidoIngresado = (EditText)findViewById(R.id.apellidoRegistracion);

        apellidoIngresado.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ingresoApellido2();
                }
                return false;
            }
        });
    }

    public void ingresoApellido(View vista){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String dni = datosRecibidos.getString("dni");
        String nombre = datosRecibidos.getString("nombre");

        String apellido = apellidoIngresado.getText().toString().trim();

        if(apellido.compareTo("")!=0){
            Intent irIngresarPin = new Intent (ActividadRegistroDos.this, ActividadRegistroTres.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("dni",dni);
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

    public void ingresoApellido2(){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String dni = datosRecibidos.getString("dni");
        String nombre = datosRecibidos.getString("nombre");

        String apellido = apellidoIngresado.getText().toString().trim();

        if(apellido.compareTo("")!=0){
            Intent irIngresarPin = new Intent (ActividadRegistroDos.this, ActividadRegistroTres.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("dni",dni);
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

package proyectofinal.helpme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadRegistroCero extends AppCompatActivity {

    EditText dniIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro_cero);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dniIngresado = (EditText)findViewById(R.id.dniRegistracion);

        dniIngresado.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ingresoDNI2();
                }
                return false;
            }
        });
    }

    public void ingresoDNI(View vista){
        String dni = dniIngresado.getText().toString().trim();

        if(dni.compareTo("")!=0){
            Intent irIngresarNombre = new Intent (ActividadRegistroCero.this, ActividadRegistro.class);
            Bundle misDatos = new Bundle();
            misDatos.putString("dni",dni);
            irIngresarNombre.putExtras(misDatos);
            startActivity(irIngresarNombre);
        }
        else{
            Toast.makeText(this, "Ingrese su DNI", Toast.LENGTH_LONG).show();
        }
    }

    public void ingresoDNI2(){
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

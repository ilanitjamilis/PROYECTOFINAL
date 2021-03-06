package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ActividadAyudaAlOtro extends Fragment implements View.OnClickListener {

    ImageButton btnAtaqueCardiaco, btnAlergia, btnSangrado, btnACV, btnAsma, btnHipotermina, btnEnvenenamiento, btnPicaduras;

    public ActividadAyudaAlOtro() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vistaADevolver = inflater.inflate(R.layout.actividad_ayuda_al_otro, container, false);

        btnAtaqueCardiaco = (ImageButton) vistaADevolver.findViewById(R.id.btnAtaqueCardiaco);
        btnAtaqueCardiaco.setOnClickListener(this);

        btnAlergia = (ImageButton) vistaADevolver.findViewById(R.id.btnAlergia);
        btnAlergia.setOnClickListener(this);

        btnACV = (ImageButton) vistaADevolver.findViewById(R.id.btnACV);
        btnACV.setOnClickListener(this);

        btnSangrado = (ImageButton) vistaADevolver.findViewById(R.id.btnSangrado);
        btnSangrado.setOnClickListener(this);

        btnAsma= (ImageButton) vistaADevolver.findViewById(R.id.btnAsma);
        btnAsma.setOnClickListener(this);

        btnHipotermina = (ImageButton) vistaADevolver.findViewById(R.id.btnHipotermia);
        btnHipotermina.setOnClickListener(this);

        btnEnvenenamiento = (ImageButton) vistaADevolver.findViewById(R.id.btnEnvenenamiento);
        btnEnvenenamiento.setOnClickListener(this);

        btnPicaduras = (ImageButton) vistaADevolver.findViewById(R.id.btnPicaduras);
        btnPicaduras.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View vista) {
        ActividadNavigationDrawer ActividadNavigationDrawer;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();

        Log.d("prueba", "id: "+vista.getId());

        switch(vista.getId()) {
            case R.id.btnAtaqueCardiaco:
                ActividadNavigationDrawer.irActividadRCP();
                break;
            case R.id.btnAlergia:
                ActividadNavigationDrawer.irActividadAlergia();
                break;
            case R.id.btnACV:
                ActividadNavigationDrawer.irActividadACV();
                break;
            case R.id.btnSangrado:
                ActividadNavigationDrawer.irActividadSangrado();
                break;
            case R.id.btnAsma:
                ActividadNavigationDrawer.MostrarMensaje("Proximamente");
                break;
            case R.id.btnHipotermia:
                ActividadNavigationDrawer.MostrarMensaje("Proximamente");
                break;
            case R.id.btnEnvenenamiento:
                ActividadNavigationDrawer.MostrarMensaje("Proximamente");
                break;
            case R.id.btnPicaduras:
                ActividadNavigationDrawer.MostrarMensaje("Proximamente");
                break;
        }
    }

}

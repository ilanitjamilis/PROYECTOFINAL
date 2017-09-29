package proyectofinal.helpme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class ActividadAyudaAlOtro extends Fragment implements View.OnClickListener {
    Button btnAtaqueCardiaco;

    public ActividadAyudaAlOtro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaADevolver = inflater.inflate(R.layout.actividad_ayuda_al_otro, container, false);
        btnAtaqueCardiaco = (Button) vistaADevolver.findViewById(R.id.btnAtaqueCardiaco);
        btnAtaqueCardiaco.setOnClickListener(this);
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
        }
    }


}

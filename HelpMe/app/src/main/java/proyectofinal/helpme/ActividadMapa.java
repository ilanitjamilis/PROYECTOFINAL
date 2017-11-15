package proyectofinal.helpme;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class ActividadMapa extends Fragment implements OnMapReadyCallback {

    ActividadNavigationDrawer miAdministrador;
    private GoogleMap mMap;

    public ActividadMapa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("mapa", "entra al onCreateView");

        miAdministrador = (ActividadNavigationDrawer) getActivity();

        // Inflate the layout for this fragment

        View vistaADevolver = inflater.inflate(R.layout.actividad_mapa, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));

        mapFragment.getMapAsync(ActividadMapa.this);

        return vistaADevolver;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.d("mapa", "entra al onMapReady");

        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

            GPSTracker miGPSTracker = new GPSTracker(miAdministrador.getApplicationContext());
            Location location = miGPSTracker.getLocation();
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        }else{
            ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 123);

            AlertDialog.Builder adb = new AlertDialog.Builder(miAdministrador.getApplicationContext());

            adb.setTitle("Su ubicación no ha podido ser detectada");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    MostrarMensaje("Intente nuevamente");
                } });
            adb.show();
        }
    }

    public void MostrarMensaje(String mensaje){
        Toast.makeText((Activity) getActivity().getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }
}

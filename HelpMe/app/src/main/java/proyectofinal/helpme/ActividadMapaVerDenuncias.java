package proyectofinal.helpme;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActividadMapaVerDenuncias extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitud;
    Double longitud;
    Boolean detectoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        detectoUbicacion = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_mapa_ver_denuncias);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                double latitiud = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitiud, longitude);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));
            }
        }else{
            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("Su ubicación no ha podido ser detectada");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                } });
            adb.show();
        }
    }
}

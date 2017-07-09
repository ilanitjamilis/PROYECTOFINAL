package proyectofinal.helpme;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
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
    String latitud;
    String longitud;
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

        latitud = "-34.608056";
        longitud = "-58.370278";


        /*if (ContextCompat.checkSelfPermission( ActividadMapaVerDenuncias.this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( ActividadMapaVerDenuncias.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Log.d("ila","hay permisos");

            LocationManager locationManager = (LocationManager) ActividadMapaVerDenuncias.this
                    .getSystemService(Context.LOCATION_SERVICE);

            String locationProvider = LocationManager.NETWORK_PROVIDER;
            Location lastlocation = locationManager.getLastKnownLocation(locationProvider);

            latitud = String.valueOf(lastlocation.getLatitude());
            longitud = String.valueOf(lastlocation.getLongitude());

            detectoUbicacion = true;
        }*/
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Double latitudBien = Double.parseDouble(latitud);
        Double longitudBien = Double.parseDouble(longitud);
        Log.d("ila", "Latitud: "+latitudBien);
        Log.d("ila", "Longitud: "+longitudBien);

        LatLng ubicacionActual = new LatLng(latitudBien, longitudBien);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("ila", "tiene permisos");
            mMap.setMyLocationEnabled(true);
            if (mMap != null) {
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location arg0) {
                        //mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("Ubicación Actual"));
                        final double milatitud = arg0.getLatitude();
                        final double milongitud = arg0.getLongitude();
                        Log.d("ila", "latitud: "+milatitud);
                        Log.d("ila", "longitud: "+milongitud);
                        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()),18));
                    }
                });
            }
        }

        /*if(detectoUbicacion) {
            mMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Ubicación Actual"));
        }
        else{
            mMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Ubicación Actual No Detectada"));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual,18));*/
    }
}

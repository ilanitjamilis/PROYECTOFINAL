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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ActividadMapaVerDenuncias extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitud;
    Double longitud;
    Boolean detectoUbicacion;
    ArrayList<Denuncia> miListaDenuncias;

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

        miListaDenuncias = LlenarListaDenuncias();
        for(int i=0; i<miListaDenuncias.size(); i++){
            Denuncia unaDenuncia = miListaDenuncias.get(i);
            ponerMarcador(unaDenuncia);
        }

    }

    public ArrayList<Denuncia> LlenarListaDenuncias(){
        ArrayList<Denuncia> miLista = new ArrayList<>();

        Denuncia unaDenuncia;

        unaDenuncia = new Denuncia();
        unaDenuncia.latitud = -34.604853;
        unaDenuncia.longitud = -58.420865;
        unaDenuncia.descripcion = "Me robaron 2 tipos armados";
        unaDenuncia.tipo = "Robo";
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia();
        unaDenuncia.latitud = -34.597762;
        unaDenuncia.longitud = -58.419844;
        unaDenuncia.descripcion = "Fui víctima de una violación por un señor viejo";
        unaDenuncia.tipo = "Violacion";
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia();
        unaDenuncia.latitud = -34.602675;
        unaDenuncia.longitud = -58.412077;
        unaDenuncia.descripcion = "Obreros gritando desde un techo";
        unaDenuncia.tipo = "Acoso";
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia();
        unaDenuncia.latitud = -34.598200;
        unaDenuncia.longitud = -58.431705;
        unaDenuncia.descripcion = "Chocó un taxi con una moto";
        unaDenuncia.tipo = "Accidente de tránsito";
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia();
        unaDenuncia.latitud = -34.599484;
        unaDenuncia.longitud = -58.408493;
        unaDenuncia.descripcion = "Me robaron 2 tipos armados";
        unaDenuncia.tipo = "Zona oscura";
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia();
        unaDenuncia.latitud = -34.605987;
        unaDenuncia.longitud = -58.419641;
        unaDenuncia.descripcion = "Señora loca gritándole a la gente";
        unaDenuncia.tipo = "Otro";
        miLista.add(unaDenuncia);

        return miLista;
    }

    private void ponerMarcador(Denuncia unaDenuncia) {
        Marker marcador;
        MarkerOptions unMarcador = new MarkerOptions()
                .title(unaDenuncia.descripcion)
                .position(new LatLng(unaDenuncia.latitud, unaDenuncia.longitud))
                .snippet("Tipo: "+unaDenuncia.tipo);

        switch(unaDenuncia.tipo){
            case "Robo":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                break;
            case "Violacion":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                break;
            case "Acoso":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                break;
            case "Accidente de tránsito":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                break;
            case "Zona oscura":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                break;
            case "Otro":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                break;
        }
        marcador = mMap.addMarker(unMarcador);
    }
}

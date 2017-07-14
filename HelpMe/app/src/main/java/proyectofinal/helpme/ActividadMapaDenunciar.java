package proyectofinal.helpme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActividadMapaDenunciar extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marcador;
    EditText denuncia;
    RadioGroup radioGroupTipoDenuncia;
    String denunciaTexto;
    String tipoDenunciaTexto;
    Double latitudActual;
    Double longitudActual;
    Boolean error;
    String miError;
    Boolean detectoMarcador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_mapa_denunciar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        detectoMarcador = false;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        denuncia = (EditText) findViewById(R.id.textoDenuncia);
        radioGroupTipoDenuncia = (RadioGroup) findViewById(R.id.tipoDenuncia);

    }

    private void ponerMarcador(Double lat, Double lng) {
        if(marcador!=null){
            marcador.remove();
        }
        MarkerOptions unMarcador = new MarkerOptions()
                .title("Denunciar Aquí")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                .position(new LatLng(lat, lng));
        //snipet("") es para mas info
        marcador = mMap.addMarker(unMarcador);
        marcador.showInfoWindow();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));
            }
        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                latitudActual = point.latitude;
                longitudActual = point.longitude;
                detectoMarcador = true;
                ponerMarcador(latitudActual, longitudActual);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marcador.getPosition(),18));
            }
        });
    }


    public void hacerDenuncia (View vista){
        Log.d("ila", "entra a hacer denuncia");
        if(radioGroupTipoDenuncia.getCheckedRadioButtonId()!=-1){
            int id = radioGroupTipoDenuncia.getCheckedRadioButtonId();
            View radioButton = radioGroupTipoDenuncia.findViewById(id);
            int radioId = radioGroupTipoDenuncia.indexOfChild(radioButton);
            RadioButton btn = (RadioButton)radioGroupTipoDenuncia.getChildAt(radioId);
            tipoDenunciaTexto = (String) btn.getText();
        }
        else{
            tipoDenunciaTexto = "";
        }
        denunciaTexto = denuncia.getText().toString().trim();
        Log.d("ila","denuncia: "+denunciaTexto);
        Log.d("ila", "tipo denuncia: "+tipoDenunciaTexto);

        error = false;
        miError = "";

        if(!detectoMarcador){
            error = true;
            MostrarMensaje("Marque el lugar");
        }

        if(denunciaTexto.compareTo("")==0){
            error = true;
            MostrarMensaje("Ingrese denuncia");
        }
        if(tipoDenunciaTexto.compareTo("")==0){
            error = true;
            MostrarMensaje("Seleccione tipo denuncia");
        }

        if(!error) {
            //Conectar con api guardar datos en db
            //Mostrar mensaje de denuncia realizada con exito
            //Alert Dialog con opcion de ir ver denuncias / cancelar (form de hacer denuncia)


            MostrarMensaje("Latitud: " + latitudActual + " // Longitud: " + longitudActual);
            MostrarMensaje("Denuncia: " + denunciaTexto);
            MostrarMensaje("Tipo: " + tipoDenunciaTexto);
            detectoMarcador = false;
            marcador.remove();
            denuncia.setText("");
            radioGroupTipoDenuncia.clearCheck();
        }
    }

    public void MostrarMensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

}

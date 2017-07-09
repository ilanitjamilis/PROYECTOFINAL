package proyectofinal.helpme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActividadMapaDenunciar extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String latitud;
    String longitud;
    Boolean detectoUbicacion;
    EditText denuncia;
    RadioGroup radioGroupTipoDenuncia;
    String denunciaTexto;
    String tipoDenunciaTexto;

    private LocationManager locationManager;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_mapa_denunciar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        detectoUbicacion = false;
        latitud = "-34.608056";
        longitud = "-58.370278";

        //Sacar latitud y longitud actual y poner detectoUbicacion=true

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        denuncia = (EditText) findViewById(R.id.textoDenuncia);
        radioGroupTipoDenuncia = (RadioGroup) findViewById(R.id.tipoDenuncia);
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
        Log.d("ila", "Latitud: " + latitudBien);
        Log.d("ila", "Longitud: " + longitudBien);

        LatLng ubicacionActual = new LatLng(latitudBien, longitudBien);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("ila", "tiene permisos");
            mMap.setMyLocationEnabled(true);
            if (mMap != null) {
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                    @Override
                    public void onMyLocationChange(Location arg0) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("Ubicación Actual"));
                        final double milatitud = arg0.getLatitude();
                        final double milongitud = arg0.getLongitude();
                        Log.d("ila", "latitud: "+milatitud);
                        Log.d("ila", "longitud: "+milongitud);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()),18));
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
        /*int radioButtonID = radioGroupTipoDenuncia.getCheckedRadioButtonId();
        Log.d("ila","radiobuttonselectedid: "+radioButtonID);
        switch (radioButtonID) {
            case R.id.tipoDenunciaRobo:
                //Seleccionado: Robo
                tipoDenunciaTexto = "Robo";
                Log.d("ila", "opcion seleccionada: robo");
                break;
            case R.id.tipoDenunciaViolacion:
                //Seleccionado: Violacion
                tipoDenunciaTexto = "Violacion";
                Log.d("ila", "opcion seleccionada: violacion");
                break;
            case R.id.tipoDenunciaAcoso:
                //Seleccionado: Acoso
                tipoDenunciaTexto = "Acoso";
                Log.d("ila", "opcion seleccionada: acoso");
                break;
            case R.id.tipoDenunciaAccidenteTransito:
                //Seleccionado: Accidente de Transito
                tipoDenunciaTexto = "Accidente de Transito";
                Log.d("ila", "opcion seleccionada: accidente");
                break;
            case R.id.tipoDenunciaZonaOscura:
                //Seleccionado: Zona Oscura
                tipoDenunciaTexto = "Zona Oscura";
                Log.d("ila", "opcion seleccionada: zona oscura");
                break;
            case R.id.tipoDenunciaOtro:
                //Seleccionado: Otro
                tipoDenunciaTexto = "Otro";
                Log.d("ila", "opcion seleccionada: otro");
                break;
            default:
                //Nada seleccionado
                tipoDenunciaTexto = "";
                Log.d("ila", "opcion seleccionada: ninguna");
                break;
        }*/
        denunciaTexto = denuncia.getText().toString();
        Log.d("ila","denuncia: "+denunciaTexto);
        Log.d("ila", "tipo denuncia: "+tipoDenunciaTexto);
    }

}

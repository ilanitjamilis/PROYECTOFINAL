package proyectofinal.helpme;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

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
            Denuncia denunciaRealizar = new Denuncia(latitudActual, longitudActual, denunciaTexto, tipoDenunciaTexto);
            //new InsertarDenuncia().execute(denunciaRealizar);

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

    public void MostrarMensajeLargo(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }


    private class InsertarDenuncia extends AsyncTask<Denuncia, Void, String> {

        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            if(resultado=="funciono"){
                MostrarMensaje("Denuncia realizada correctamente");
            }
            else{
                MostrarMensajeLargo("Hubo un error, intente nuevamente");
            }
        }

        @Override
        protected String doInBackground(Denuncia... parametros) {
            String miURL = "http://ort.edu.ar/serviciox"; //url insertar mi denuncia

            Denuncia miDenuncia = parametros[0];

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject miJSONDenuncia = new JSONObject();

            try {
                miJSONDenuncia.put("latitud", miDenuncia.latitud);
                miJSONDenuncia.put("longiutd", miDenuncia.longitud);
                miJSONDenuncia.put("descripcion", miDenuncia.descripcion);
                miJSONDenuncia.put("tipo", miDenuncia.tipo);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("ila", "JSON exception");
            }
            RequestBody body = RequestBody.create(JSON, miJSONDenuncia.toString());
            Request request = new Request.Builder()
                    .url(miURL)
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                return resultado;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}

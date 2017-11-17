package proyectofinal.helpme;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActividadMapaDenunciar extends Fragment implements View.OnClickListener, OnMapReadyCallback {

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

    Calendar myCalendar;
    EditText etFecha;
    DatePickerDialog.OnDateSetListener date;
    String fechaString;
    String fechaParaSQL;
    Button btnDenunciar;

    ActividadNavigationDrawer miAdministrador;

    public ActividadMapaDenunciar(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        miAdministrador = (ActividadNavigationDrawer) getActivity();

        View vistaADevolver = inflater.inflate(R.layout.actividad_mapa_denunciar, container, false);

        btnDenunciar = (Button)vistaADevolver.findViewById(R.id.btnDenunciar);

        etFecha = (EditText) vistaADevolver.findViewById(R.id.fechaDenuncia);
        etFecha.setOnClickListener(this);
        Log.d("fecha", "declaro fecha");

        detectoMarcador = false;
        fechaString = "";

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(ActividadMapaDenunciar.this);

        radioGroupTipoDenuncia = (RadioGroup) vistaADevolver.findViewById(R.id.tipoDenuncia);

        myCalendar = Calendar.getInstance();
        Log.d("fecha", "agarro instancia del calendario");

        Log.d("fecha", "antes de tocar fecha");
        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                new DatePickerDialog(miAdministrador.getApplicationContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d("fecha", "adentro de onDateSet");
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                Log.d("fecha", "antes de actualizar label");
                updateLabel();
                Log.d("fecha", "actualizo label");
            }

        };

        return vistaADevolver;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etFecha.setText(sdf.format(myCalendar.getTime()));
        fechaString = etFecha.getText().toString();

        myFormat = "yyyy-MM-dd";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        fechaParaSQL = sdf.format(myCalendar.getTime());
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

        if (ActivityCompat.checkSelfPermission(miAdministrador, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(miAdministrador, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

            ubicarmeEnMapa();

        }else{
            ActivityCompat.requestPermissions(miAdministrador, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            ActivityCompat.requestPermissions(miAdministrador, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 123);

            AlertDialog.Builder adb = new AlertDialog.Builder(miAdministrador);

            adb.setTitle("Su ubicación no ha podido ser detectada");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    MostrarMensaje("Intente nuevamente");
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

    public void ubicarmeEnMapa(){
        GPSTracker miGPSTracker = new GPSTracker(miAdministrador.getApplicationContext());
        Location location = miGPSTracker.getLocation();
        if (location != null) {
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        }
        else{
            AlertDialog.Builder adb = new AlertDialog.Builder(miAdministrador);

            adb.setTitle("Su ubicación no ha podido ser detectada");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Double lat = -34.6037345;
                    Double lng = -58.3837591;
                    LatLng latLng = new LatLng(lat, lng);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                } });
            adb.show();
        }
    }

    public void hacerDenuncia (){
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

        error = false;
        miError = "";

        if(!detectoMarcador){
            error = true;
            MostrarMensaje("Marque el lugar");
        }
        if(fechaString.compareTo("")==0){
            error = true;
            MostrarMensaje("Elija fecha");
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
            Denuncia denunciaRealizar = new Denuncia(latitudActual, longitudActual, denunciaTexto, tipoDenunciaTexto, fechaParaSQL);
            new InsertarDenuncia().execute(denunciaRealizar);

        }
    }

    public void MostrarMensaje(String mensaje){
        Toast.makeText(miAdministrador,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void MostrarMensajeLargo(String mensaje){
        Toast.makeText(miAdministrador,mensaje,Toast.LENGTH_LONG).show();
    }

    public class InsertarDenuncia extends AsyncTask<Denuncia, Void, String> {

        public void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            if(resultado.compareTo("funciono")==0){
                detectoMarcador = false;
                marcador.remove();
                denuncia.setText("");
                radioGroupTipoDenuncia.clearCheck();
                etFecha.setText("");
                ubicarmeEnMapa();

                AlertDialog.Builder adb = new AlertDialog.Builder(miAdministrador);

                adb.setTitle("Denuncia realizada correctamente");

                adb.setIcon(android.R.drawable.ic_dialog_alert);

                adb.setPositiveButton("VER DENUNCIAS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent irVerDenuncias = new Intent (miAdministrador, ActividadMapaVerDenuncias.class);
                        startActivity(irVerDenuncias);
                    } });
                adb.setNegativeButton("REALIZAR OTRA DENUNCIA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    } });
                adb.show();
            }
            else{
                MostrarMensajeLargo("Hubo un error, intente nuevamente");
            }
        }

        @Override
        public String doInBackground(Denuncia... parametros) {
            String miURL = "http://helpmeayudame.azurewebsites.net/insertarDenuncia2.php"; //url insertar mi denuncia
            String resultado;

            Denuncia miDenuncia = parametros[0];

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject miJSONDenuncia = new JSONObject();

            try {
                miJSONDenuncia.put("latitud", miDenuncia.latitud);
                miJSONDenuncia.put("longitud", miDenuncia.longitud);
                miJSONDenuncia.put("descripcion", miDenuncia.descripcion);
                miJSONDenuncia.put("tipo", miDenuncia.tipo);
                miJSONDenuncia.put("fecha", miDenuncia.fecha);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(JSON, miJSONDenuncia.toString());

            /*RequestBody body = new MultipartBody().Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("lat", miDenuncia.latitud)
                    .addFormDataPart("lon", miDenuncia.longitud)
                    .addFormDataPart("tip", miDenuncia.tipo)
                    .addFormDataPart("des", miDenuncia.descripcion)
                    .build();*/

            Request request = new Request.Builder()
                    .url(miURL)
                    //.method("POST", RequestBody.create(null, new byte[0]))
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                resultado = response.body().string();
                return resultado;

            } catch (IOException e) {
                resultado = "error";
                return resultado;
            }
        }
    }

    public void onClick(View vista) {
        switch(vista.getId()) {
            case R.id.btnDenunciar:
                hacerDenuncia();
                break;
        }
    }

}
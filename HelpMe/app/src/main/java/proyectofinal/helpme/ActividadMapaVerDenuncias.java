package proyectofinal.helpme;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
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
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActividadMapaVerDenuncias extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    Double latitud;
    Double longitud;
    Boolean detectoUbicacion;
    ArrayList<Denuncia> miListaDenuncias;
    Double latitudCentral;
    Double longitudCentral;
    Integer cantDenunciasRadar;
    ActividadNavigationDrawer ActividadNavigationDrawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        detectoUbicacion = false;
        ActividadNavigationDrawer = (ActividadNavigationDrawer) getActivity();
        View vistaADevolver = inflater.inflate(R.layout.actividad_mis_datos, container, false);

        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) ActividadNavigationDrawer.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return vistaADevolver;

    }

    public ActividadMapaVerDenuncias() {
        // Required empty public constructor
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnCameraIdleListener(this);
        mMap.setOnCameraMoveStartedListener(this);
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraMoveCanceledListener(this);
        //mMap.setOnInfoWindowClickListener(this);

        mMap.getUiSettings().setZoomControlsEnabled(false);

        if (ActivityCompat.checkSelfPermission(ActividadNavigationDrawer, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(ActividadNavigationDrawer, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

            GPSTracker miGPSTracker = new GPSTracker(ActividadNavigationDrawer.getApplicationContext());
            Location location = miGPSTracker.getLocation();
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        }else{
            ActivityCompat.requestPermissions(ActividadNavigationDrawer, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            ActivityCompat.requestPermissions(ActividadNavigationDrawer, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 123);

            AlertDialog.Builder adb = new AlertDialog.Builder(ActividadNavigationDrawer);

            adb.setTitle("Su ubicación no ha podido ser detectada");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    MostrarMensaje("Intente nuevamente");
                } });
            adb.show();
        }

        /*miListaDenuncias = LlenarListaDenuncias();
        for(int i=0; i<miListaDenuncias.size(); i++){
            Denuncia unaDenuncia = miListaDenuncias.get(i);
            PonerMarcador(unaDenuncia);
        }*/

    }

    @Override
    public void onCameraMoveStarted(int reason) {

        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            //Toast.makeText(this, "The user gestured on the map.", Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_API_ANIMATION) {
            //Toast.makeText(this, "The user tapped something on the map.", Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_DEVELOPER_ANIMATION) {
            //Toast.makeText(this, "The app moved the camera.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraMove() {
        //Toast.makeText(this, "The camera is moving.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraMoveCanceled() {
        // Toast.makeText(this, "Camera movement canceled.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraIdle() {
        //Traer marcadores en esta zona
        LatLng latLng = mMap.getCameraPosition().target;
        latitudCentral = latLng.latitude;
        longitudCentral = latLng.longitude;

        //Toast.makeText(this, "Lat: "+latLng.latitude+"// Lng: "+latLng.longitude, Toast.LENGTH_SHORT).show();

        String url = "http://helpmeayudame.azurewebsites.net/traerDenunciasRadar.php"; //url traer mis denuncias
        url = "http://helpmeayudame.azurewebsites.net/traerDenunciasRadar.php";

        new BuscarDatosDenuncias().execute(url);

        //Toast.makeText(this, "The camera has stopped moving.", Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public void onInfoWindowClick(Marker marker) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(marker.getTitle());
        adb.setMessage(marker.getSnippet());
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            } });
        adb.show();
    }*/

    public ArrayList<Denuncia> LlenarListaDenuncias(){
        ArrayList<Denuncia> miLista = new ArrayList<>();
        Denuncia unaDenuncia;

        unaDenuncia = new Denuncia(-34.604853, -58.420865, "Me robaron 2 tipos armados", "Robo");
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia(-34.597762, -58.419844, "Fui víctima de una violación por un señor de unos 45 años", "Violacion");
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia(-34.602675, -58.412077, "Obreros gritando desde un techo", "Acoso");
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia(-34.598200, -58.431705, "Chocó un taxi con una moto", "Accidente de tránsito");
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia(-34.599484, -58.408493, "No hay nada de iluminación en la zona", "Zona oscura");
        miLista.add(unaDenuncia);

        unaDenuncia = new Denuncia(-34.605987, -58.419641, "Señora loca gritándole a la gente", "Otro");
        miLista.add(unaDenuncia);

        return miLista;
    }

    private void PonerMarcador(Denuncia unaDenuncia) throws ParseException {

        String strCurrentDate = unaDenuncia.fecha;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = format.parse(strCurrentDate);

        format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(newDate);

        unaDenuncia.fecha = date;

        Marker marcador;
        MarkerOptions unMarcador = new MarkerOptions()
                .title(unaDenuncia.descripcion)
                .position(new LatLng(unaDenuncia.latitud, unaDenuncia.longitud))
                .snippet("Fecha: "+unaDenuncia.fecha);

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
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                break;
            case "Otro":
                unMarcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                break;
        }
        marcador = mMap.addMarker(unMarcador);
        marcador.showInfoWindow();
    }

    private class BuscarDatosDenuncias extends AsyncTask<String, Void, ArrayList<Denuncia>> {

        String resultado;

        protected void onPostExecute(ArrayList<Denuncia> listaDenuncias) {
            super.onPostExecute(listaDenuncias);
            if(listaDenuncias!=null) {
                if(resultado.compareTo("error")!=0) {
                    mMap.clear();
                    for (int i = 0; i < listaDenuncias.size(); i++) {
                        cantDenunciasRadar++;
                        Denuncia unaDenuncia = listaDenuncias.get(i);
                        try {
                            PonerMarcador(unaDenuncia);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if(cantDenunciasRadar>100){
                        MostrarMensaje("ZONA PELIGROSA");
                    }
                }
                else{
                    MostrarMensaje("Hubo un error");
                }
            }
            else{
                MostrarMensaje("Hubo un error, intente nuevamente");
            }
        }

        @Override
        protected ArrayList<Denuncia> doInBackground(String... parametros) {

            cantDenunciasRadar = 0;

            String miURL = parametros[0];

            resultado = "";

            ArrayList<Denuncia> misDenuncias = new ArrayList<>();

            if(miURL.compareTo("http://helpmeayudame.azurewebsites.net/traerDenunciasRadar.php")!=0) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(miURL)
                        .build();
                try {
                    Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                    resultado = response.body().string();
                    if (resultado.compareTo("error") != 0) {
                        misDenuncias = ParsearResultado(resultado);
                        return misDenuncias;
                    } else {
                        return null;
                    }

                } catch (IOException e) {
                    return null;
                } catch (JSONException e) {
                    return null;
                }
            }
            else{
                Log.d("denuncias", "entra bien");
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JSONObject miJSONDenuncia = new JSONObject();

                try {
                    miJSONDenuncia.put("miLat", latitudCentral);
                    miJSONDenuncia.put("miLng", longitudCentral);
                    Log.d("denuncias", "miJSONDenuncia--->   "+miJSONDenuncia);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(JSON, miJSONDenuncia.toString());

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(miURL)
                        .post(body)
                        .build();

                try {
                    Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                    resultado = response.body().string();
                    Log.d("denuncias", "resultado---->  "+resultado);
                    if (resultado.compareTo("error") != 0) {
                        misDenuncias = ParsearResultado(resultado);
                        return misDenuncias;
                    } else {
                        return null;
                    }

                } catch (IOException e) {
                    return null;
                } catch (JSONException e) {
                    return null;
                }
            }
        }

        ArrayList<Denuncia> ParsearResultado(String result) throws JSONException {
            ArrayList<Denuncia> denuncias = new ArrayList<>();
            JSONArray jsonDenuncias = new JSONArray(result);
            for (int i = 0; i < jsonDenuncias.length(); i++) {
                JSONObject jsonResultado = jsonDenuncias.getJSONObject(i);


                double latitudD = jsonResultado.getDouble("latitud");
                double longitudD = jsonResultado.getDouble("longitud");
                String descripcionD = jsonResultado.getString("descripcion");
                String tipoD = jsonResultado.getString("tipo");
                String fechaD = jsonResultado.getString("fecha");

                Denuncia d = new Denuncia(latitudD, longitudD, descripcionD, tipoD, fechaD);
                denuncias.add(d);
            }
            return denuncias;
        }
    }

    public void MostrarMensaje(String mensaje){
        Toast.makeText(ActividadNavigationDrawer,mensaje,Toast.LENGTH_SHORT).show();
    }
}
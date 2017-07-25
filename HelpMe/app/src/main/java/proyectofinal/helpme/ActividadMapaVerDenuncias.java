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
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        /*String url = "http://ort.edu.ar/serviciox"; //url traer mis denuncias
        new BuscarDatosDenuncias().execute(url);*/
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
            PonerMarcador(unaDenuncia);
        }

    }

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

    private void PonerMarcador(Denuncia unaDenuncia) {
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

    private class BuscarDatosDenuncias extends AsyncTask<String, Void, ArrayList<Denuncia>> {

        protected void onPostExecute(ArrayList<Denuncia> listaDenuncias) {
            super.onPostExecute(listaDenuncias);
            for(int i=0; i<listaDenuncias.size(); i++){
                Denuncia unaDenuncia = listaDenuncias.get(i);
                PonerMarcador(unaDenuncia);
            }
        }

        @Override
        protected ArrayList<Denuncia> doInBackground(String... parametros) {
            String miURL = parametros[0];
            ArrayList<Denuncia> misDenuncias = new ArrayList<>();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(miURL)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                misDenuncias = ParsearResultado(resultado);
                return misDenuncias;

            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }
        }

        ArrayList<Denuncia> ParsearResultado(String result) throws JSONException {
            ArrayList<Denuncia> denuncias = new ArrayList<>();
            JSONArray jsonDenuncias = new JSONArray(result);
            for (int i = 0; i < jsonDenuncias.length(); i++) {
                JSONObject jsonResultado = jsonDenuncias.getJSONObject(i);

                int idD = jsonResultado.getInt("id");
                double latitudD = jsonResultado.getDouble("latitud");
                double longitudD = jsonResultado.getDouble("longitud");
                String descripcionD = jsonResultado.getString("descripcion");
                String tipoD = jsonResultado.getString("tipo");

                Denuncia d = new Denuncia(idD, latitudD, longitudD, descripcionD, tipoD);
                denuncias.add(d);
            }
            return denuncias;
        }
    }
}

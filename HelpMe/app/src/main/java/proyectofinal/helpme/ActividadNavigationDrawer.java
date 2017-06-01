package proyectofinal.helpme;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.security.CodeSigner;

public class ActividadNavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public boolean isFirstStart;
    Context mcontext;

    boolean miBaseDatosAbierta;

    FragmentManager AdministradorDeFragments=getSupportFragmentManager();
    FragmentTransaction TransaccionDeFragment;

    SharedPreferences sharedPref;
    String ultimoCodigoDetectado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        setContentView(R.layout.actividad_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setLogo(R.drawable.ic_logo);

        //android.app.ActionBar actionBar = getActionBar();

        /*Intent irWizard = new Intent(ActividadPrincipal.this, MyIntro.class);
        startActivity(irWizard);*/

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(ActividadNavigationDrawer.this, MyIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();

        Fragment miFragmentIngreso = new ActividadPrincipal();
        TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
        TransaccionDeFragment.replace(R.id.AlojadorFragment, miFragmentIngreso);
        TransaccionDeFragment.commit();



        if(AveriguarSiHayRegistros() == false){
            // Agregar registros

            AgregarRegistro("AR", 101, 107, 100);
            AgregarRegistro("BO", 110, 118, 119);
            AgregarRegistro("BR", 190, 192, 193);
            AgregarRegistro("CL", 133, 131, 132);
            AgregarRegistro("CO", 112, 125, 119);
            AgregarRegistro("EC", 101, 131, 102);
            AgregarRegistro("FK", 112, 112, 112);
            AgregarRegistro("GF", 17, 15, 18);
            AgregarRegistro("GY", 911, 913, 912);
            AgregarRegistro("PY", 912, 141, 132);
            AgregarRegistro("PE", 105, 116, 116);
            AgregarRegistro("GS", 999, 999, 999);
            AgregarRegistro("SR", 115, 113, 110);
            AgregarRegistro("UY", 109, 115, 104);
            AgregarRegistro("VE", 911, 911, 911);
            AgregarRegistro("AG", 911, 911, 911);
            AgregarRegistro("AI", 911, 911, 911);
            AgregarRegistro("AW", 911, 911, 911);
            AgregarRegistro("BZ", 911, 911, 911);
            AgregarRegistro("BM", 911, 911, 911);
            AgregarRegistro("CA", 911, 911, 911);
            AgregarRegistro("CU", 106, 104, 105);
            AgregarRegistro("DM", 999, 999, 999);
            AgregarRegistro("GD", 911, 911, 911);
            AgregarRegistro("GP", 17, 15, 18);
            AgregarRegistro("MQ", 17, 15, 18);
            AgregarRegistro("MX", 066, 066, 066);
            AgregarRegistro("MS", 911, 911, 911);
            AgregarRegistro("KN", 911, 911, 911);
            AgregarRegistro("LC", 911, 911, 911);
            AgregarRegistro("PM", 17, 15, 18);
            AgregarRegistro("VC", 911, 911, 911);
            AgregarRegistro("US", 911, 911, 911);
            AgregarRegistro("BB", 211, 511, 311);
            AgregarRegistro("BS", 112, 112, 112);
            AgregarRegistro("KY", 911, 911, 911);
            AgregarRegistro("CR", 112, 112, 112);
            AgregarRegistro("DO", 911, 911, 911);
            AgregarRegistro("GT", 110, 128, 122);
            AgregarRegistro("SV", 911, 132, 913);
            AgregarRegistro("HT", 114, 116, 115);
            AgregarRegistro("HN", 112, 195, 198);
            AgregarRegistro("JM", 119, 110, 110);
            AgregarRegistro("NI", 118, 128, 115);
            AgregarRegistro("PA", 104, 911, 103);
            AgregarRegistro("PR", 911, 911, 911);
            AgregarRegistro("TT", 999, 990, 990);
            AgregarRegistro("AS", 911, 911, 911);
            AgregarRegistro("AU", 000, 000, 000);
            AgregarRegistro("CK", 999, 998, 996);
            AgregarRegistro("FJ", 000, 000, 000);
            AgregarRegistro("PF", 17, 15, 18);
            AgregarRegistro("GU", 911, 911, 911);
            AgregarRegistro("KI", 192, 194, 193);
            AgregarRegistro("MH", 911, 911, 911);
            AgregarRegistro("FM", 911, 911, 911);
            AgregarRegistro("NR", 110, 111, 112);
            AgregarRegistro("NC", 17, 15, 18);
            AgregarRegistro("NZ", 111, 111, 111);
            AgregarRegistro("PW", 911, 911, 911);
            AgregarRegistro("PG", 112, 111, 110);
            AgregarRegistro("WS", 995, 996, 994);
            AgregarRegistro("SB", 911, 911, 911);
            AgregarRegistro("TO", 922, 933, 999);
            AgregarRegistro("TV", 911, 911, 911);
            AgregarRegistro("VU", 112, 112, 112);
            AgregarRegistro("AX", 112, 112, 112);
            AgregarRegistro("AL", 129, 127, 128);
            AgregarRegistro("AD", 110, 118, 118);
            AgregarRegistro("AM", 102, 103, 101);
            AgregarRegistro("AT", 133, 144, 122);
            AgregarRegistro("AZ", 102, 103, 101);
            AgregarRegistro("BY", 102, 103, 101);
            AgregarRegistro("BE", 101, 100, 100);
            AgregarRegistro("BA", 122, 124, 123);
            AgregarRegistro("BG", 112, 112, 112);
            AgregarRegistro("HR", 192, 194, 193);
            AgregarRegistro("CY", 112, 112, 1407);
            AgregarRegistro("CZ", 158, 155, 150);
            AgregarRegistro("DK", 112, 114, 114);
            AgregarRegistro("EE", 112, 112, 112);
            AgregarRegistro("FO", 112, 114, 114);
            AgregarRegistro("FI", 112, 112, 112);
            AgregarRegistro("FR", 17, 15, 18);
            AgregarRegistro("GE", 112, 112, 112);
            AgregarRegistro("DE", 110, 112, 112);
            AgregarRegistro("GI", 199, 190, 190);
            AgregarRegistro("GR", 100, 166, 199);
            AgregarRegistro("GL", 112, 112, 112);
            AgregarRegistro("HU", 107, 104, 105);
            AgregarRegistro("IS", 112, 112, 112);
            AgregarRegistro("IE", 112, 112, 112);
            AgregarRegistro("IT", 113, 118, 115);
            AgregarRegistro("LV", 110, 113, 114);
            AgregarRegistro("LT", 112, 112, 112);
            AgregarRegistro("LI", 117, 144, 118);
            AgregarRegistro("LU", 113, 112, 112);
            AgregarRegistro("EE", 112, 112, 112);
            AgregarRegistro("MK", 192, 194, 193);
            AgregarRegistro("MT", 112, 112, 112);
            AgregarRegistro("MD", 902, 903, 901);
            AgregarRegistro("MC", 17, 15, 18);
            AgregarRegistro("CS", 192, 194, 192);
            AgregarRegistro("NL", 112, 112, 112);
            AgregarRegistro("NO", 112, 113, 110);
            AgregarRegistro("PL", 997, 999, 998);
            AgregarRegistro("PT", 112, 112, 112);
            AgregarRegistro("RO", 112, 112, 112);
            AgregarRegistro("RU", 102, 103, 101);
            AgregarRegistro("SM", 113, 118, 115);
            AgregarRegistro("SK", 158, 155, 150);
            AgregarRegistro("SI", 113, 112, 112);
            AgregarRegistro("ES", 112, 112, 112);
            AgregarRegistro("SE", 112, 112, 112);
            AgregarRegistro("CH", 117, 144, 118);
            AgregarRegistro("TR", 155, 112, 110);
            AgregarRegistro("UA", 102, 103, 101);
            AgregarRegistro("GB", 112, 112, 112);
            AgregarRegistro("VA", 113, 118, 115);
            AgregarRegistro("AF", 119, 112, 119);
            AgregarRegistro("BH", 112, 112, 112);
            AgregarRegistro("BD", 999, 199, 199);
            AgregarRegistro("BT", 113, 112, 110);
            AgregarRegistro("IO", 112, 112, 112);
            AgregarRegistro("BN", 993, 991, 995);
            AgregarRegistro("KH", 117, 119, 118);
            AgregarRegistro("CN", 110, 120, 119);
            AgregarRegistro("CX", 000, 000, 000);
            AgregarRegistro("CC", 000, 000, 000);
            AgregarRegistro("HK", 112, 112, 112);
            AgregarRegistro("IN", 100, 102, 101);
            AgregarRegistro("ID", 110, 118, 113);
            AgregarRegistro("IR", 110, 115, 125);
            AgregarRegistro("IQ", 104, 122, 115);
            AgregarRegistro("IS", 100, 101, 102);
            AgregarRegistro("JP", 110, 119, 119);
            AgregarRegistro("JO", 112, 112, 112);
            AgregarRegistro("KZ", 102, 103, 101);
            AgregarRegistro("KG", 102, 103, 101);
            AgregarRegistro("KP", 112, 112, 112);
            AgregarRegistro("KR", 112, 119, 119);
            AgregarRegistro("LB", 160, 140, 175);
            AgregarRegistro("MO", 112, 112, 112);
            AgregarRegistro("MV", 119, 102, 118);
            AgregarRegistro("MY", 999, 999, 999);
            AgregarRegistro("MN", 102, 103, 101);
            AgregarRegistro("NP", 100, 102, 101);
            AgregarRegistro("OM", 112, 112, 112);
            AgregarRegistro("PK", 15, 1122, 16);
            AgregarRegistro("PH", 911, 911, 911);
            AgregarRegistro("QA", 112, 112, 112);
            AgregarRegistro("SA", 999, 997, 998);
            AgregarRegistro("SG", 999, 995, 995);
            AgregarRegistro("LK", 119, 110, 110);
            AgregarRegistro("SY", 112, 110, 113);
            AgregarRegistro("TJ", 102, 103, 101);
            AgregarRegistro("TH", 191, 1669, 199);
            AgregarRegistro("TM", 102, 103, 101);
            AgregarRegistro("AE", 999, 998, 997);
            AgregarRegistro("UZ", 102, 103, 101);
            AgregarRegistro("VN", 113, 115, 114);
            AgregarRegistro("YE", 194, 191, 191);
            AgregarRegistro("AQ", 911, 911, 911);

            utilidades.baseDatos.close();

            //Falta África
            //Números: https://en.wikipedia.org/wiki/List_of_emergency_telephone_numbers
            //Códigos ISO: http://kirste.userpage.fu-berlin.de/diverse/doc/ISO_3166.html


            sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("CÓDIGO", "NONE");
        }

        TomarUbicacionActual();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_menu1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Fragment frgMostrar;
            frgMostrar = new ActividadConfiguracion();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_menu5);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu1) {

            Fragment frgMostrar;
            frgMostrar = new ActividadPrincipal();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();

        } else if (id == R.id.nav_menu2) {

            Fragment frgMostrar;
            frgMostrar = new ActividadMisDatos();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();

        } else if (id == R.id.nav_menu3) {

            Fragment frgMostrar;
            frgMostrar = new ActividadAyudaAlOtro();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();

        } else if (id == R.id.nav_menu4) {

            Fragment frgMostrar;
            frgMostrar = new ActividadDenunciar();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();

        } else if (id == R.id.nav_menu5) {

            Fragment frgMostrar;
            frgMostrar = new ActividadConfiguracion();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();

        } else if (id == R.id.nav_menu6) {

            Fragment frgMostrar;
            frgMostrar = new ActividadCerrarSesion();
            TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
            TransaccionDeFragment.replace(R.id.AlojadorFragment, frgMostrar);
            TransaccionDeFragment.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Boolean AveriguarSiHayRegistros(){
        Boolean resultado = false;
        miBaseDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select nombrePais, telPolicia, telAmbulancia, telBomberos from paisestelefonos", null);
            if (registros.moveToFirst() == true) {
                resultado = true;
            }
        }
        //utilidades.baseDatos.close();
        return resultado;
    }


    public Boolean AveriguarPaisActual(String miCodPais){
        Boolean paisEncontrado = false;
        miBaseDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select nombrePais, telPolicia, telAmbulancia, telBomberos from paisestelefonos", null);
            if (registros.moveToFirst() == true) {
                do {
                    String codigoPais = registros.getString(0);
                    Integer numTelefonoPolicia = registros.getInt(1);
                    Integer numTelefonoAmbulancia = registros.getInt(2);
                    Integer numTelefonoBomberos = registros.getInt(3);

                    Pais unPais = new Pais();
                    unPais.codigoP = codigoPais;
                    unPais.numPoliciaP = numTelefonoPolicia;
                    unPais.numAmbulanciaP = numTelefonoAmbulancia;
                    unPais.numBomberosP= numTelefonoBomberos;

                    if(unPais.codigoP.compareTo(miCodPais)==0){
                        paisEncontrado = true;
                        utilidades.paisActual = unPais;
                    }

                } while (registros.moveToNext() == true);
            }
        }
        utilidades.baseDatos.close();

        return paisEncontrado;
    }

    public void AgregarRegistro(String codPais, int numPolicia, int numAmbulancia, int numBomberos){
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombrePais", codPais);
        nuevoRegistro.put("telPolicia", numPolicia);
        nuevoRegistro.put("telAmbulancia", numAmbulancia);
        nuevoRegistro.put("telBomberos", numBomberos);
        utilidades.baseDatos.insert("paisestelefonos", null, nuevoRegistro);
    }

    public void BorrarRegistrosBasePaisesTelefonos(){
        boolean miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDeDatosAbierta){
            utilidades.baseDatos.delete("paisestelefonos","",null);
        }
        utilidades.baseDatos.close();
    }



    public String getCountryCode(){
        String countryCode;

        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        countryCode = tm.getNetworkCountryIso();

        if(countryCode==""){
            countryCode="NOTFOUND";
        }

        Log.d("ila","countryCode: "+countryCode);

        return countryCode;
    }

    public void llamarPolicia(View vista){
        if(utilidades.paisActual.codigoP.compareTo("NOT FOUND")!= 0) {
            Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
            String numPolicia = utilidades.paisActual.numPoliciaP.toString();
            intentoLlamada.setData(Uri.parse("tel:" + numPolicia));
            startActivity(intentoLlamada);

            /*String mensaje = "Funcionalidad en construcción";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();*/
        }
        else{
            String mensaje = "ERROR";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void llamarAmbulancia(View vista){
        if(utilidades.paisActual.codigoP.compareTo("NOT FOUND")!= 0) {
            Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
            String numAmbulancia = utilidades.paisActual.numAmbulanciaP.toString();
            intentoLlamada.setData(Uri.parse("tel:" + numAmbulancia));
            startActivity(intentoLlamada);

            /*String mensaje = "Funcionalidad en construcción";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();*/
        }
        else{
            String mensaje = "ERROR";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void llamarBomberos(View vista){
        if(utilidades.paisActual.codigoP.compareTo("NOT FOUND")!= 0) {
            Intent intentoLlamada = new Intent(Intent.ACTION_DIAL); //ACTION_CALL dice que faltan permisos, por eso ACTION_DIAL
            String numBomberos = utilidades.paisActual.numBomberosP.toString();
            intentoLlamada.setData(Uri.parse("tel:" + numBomberos));
            startActivity(intentoLlamada);

            /*String mensaje = "Funcionalidad en construcción";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();*/
        }
        else{
            String mensaje = "ERROR";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void enviarMensajeEmergencia (View vista){
        String mensaje = "Funcionalidad en construcción";
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    public void irMapas (View vista){
        Intent intentoMapas = new Intent(android.content.Intent.ACTION_VIEW);
        startActivity(intentoMapas);
    }

    public void TomarUbicacionActual(){
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        ultimoCodigoDetectado = sharedPref.getString("CÓDIGO", "NONE");

        Log.d("ila","último código detectado: "+ ultimoCodigoDetectado);

        String codPais = getCountryCode().toUpperCase();
        Log.d("ila","mi codPais: "+codPais);
        if(codPais != null){
            Log.d("ila","entro acá1");
            if(codPais=="NOTFOUND"){
                if(ultimoCodigoDetectado.compareTo("NONE")==0) {
                    codPais = "NOT FOUND";
                    utilidades.paisActual = new Pais();
                    utilidades.paisActual.codigoP = codPais;
                    getSupportActionBar().setSubtitle("Ubicación Actual: " + utilidades.paisActual.codigoP);
                }
                else{
                    AveriguarPaisActual(ultimoCodigoDetectado);
                    getSupportActionBar().setSubtitle("Última Ubicación: " + utilidades.paisActual.codigoP);
                }
            }
            else {
                boolean codigoEncontrado = AveriguarPaisActual(codPais);
                if(codigoEncontrado){
                    if(codPais.compareTo(ultimoCodigoDetectado)!=0) {

                        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("CÓDIGO", codPais);

                        Log.d("ila","codPais: "+codPais);

                        editor.commit();

                        ultimoCodigoDetectado = sharedPref.getString("CÓDIGO", "NONE");

                        Log.d("ila","mi último código detectado ahora es: "+ultimoCodigoDetectado);
                    }
                }
                else{
                    if(ultimoCodigoDetectado.compareTo("NONE")==0) {
                        codPais = "NOT FOUND";
                        utilidades.paisActual = new Pais();
                        utilidades.paisActual.codigoP = codPais;
                    }
                    else{
                        AveriguarPaisActual(ultimoCodigoDetectado);
                        getSupportActionBar().setSubtitle("Última Ubicación: " + utilidades.paisActual.codigoP);
                    }
                }
                getSupportActionBar().setSubtitle("Ubicación Actual: " + utilidades.paisActual.codigoP);
            }
        }
        else{
            if(ultimoCodigoDetectado.compareTo("NONE")!=0){
                AveriguarPaisActual(ultimoCodigoDetectado);
                getSupportActionBar().setSubtitle("Última Ubicación: " + utilidades.paisActual.codigoP);
                Log.d("ila","entro acá");
            }
            else{
                codPais = "NOT FOUND";
                utilidades.paisActual = new Pais();
                utilidades.paisActual.codigoP = codPais;
                getSupportActionBar().setSubtitle("Ubicación Actual: " + utilidades.paisActual.codigoP);
            }
        }
    }

}

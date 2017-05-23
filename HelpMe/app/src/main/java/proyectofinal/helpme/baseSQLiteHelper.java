package proyectofinal.helpme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class baseSQLiteHelper extends SQLiteOpenHelper {

    public baseSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version){
        super (contexto, nombre, fabrica, version);
    }

    @Override
    public void onCreate (SQLiteDatabase baseDeDatos){
        String sqlCrearTablaTelefonos;
        sqlCrearTablaTelefonos = "create table paisestelefonos (nombrePais text, telPolicia int, telAmbulancia int, telBomberos int)";
        baseDeDatos.execSQL(sqlCrearTablaTelefonos);
    }

    @Override
    public void onUpgrade (SQLiteDatabase baseDeDatos, int versionAnterior, int versionNueva){

    }
}

package proyectofinal.helpme;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;


public class utilidades {

    public static baseSQLiteHelper accesoBase;
    public static SQLiteDatabase baseDatos;

    public static Pais paisActual = new Pais();

    public static boolean baseDeDatosAbierta(Context vista){

        boolean responder;

        //MiBase
        //MiBaseDeDatos
        //BaseDeDatos
        //Base
        //BaseDatos
        //BaseDatosHelpMe

        //Probe hasta la versión 6

        accesoBase = new baseSQLiteHelper (vista, "BaseDatosHelpMe", null, 1);
        baseDatos = accesoBase.getWritableDatabase();

        if(baseDatos != null){
            responder = true;
        }
        else{
            responder = false;
        }

        return responder;
    }
}

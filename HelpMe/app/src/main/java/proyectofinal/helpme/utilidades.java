package proyectofinal.helpme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class utilidades {

    public static baseSQLiteHelper accesoBase;
    public static SQLiteDatabase baseDatos;

    public static Pais paisActual = new Pais();

    public static boolean baseDeDatosAbierta(Context vista){

        boolean responder;

        accesoBase = new baseSQLiteHelper (vista, "miBase", null, 1);
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

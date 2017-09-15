package proyectofinal.helpme;

/**
 * Created by Ilanit Jamilis on 14/7/2017.
 */

public class Denuncia {

    Denuncia(int idD, double latD, double longD, String descripcionD, String tipoD, String fechaD){
        this.id = idD;
        this.latitud = latD;
        this.longitud = longD;
        this.descripcion = descripcionD;
        this.tipo = tipoD;
        this.fecha = fechaD;
    }


    Denuncia(int idD, double latD, double longD, String descripcionD, String tipoD){
        this.id = idD;
        this.latitud = latD;
        this.longitud = longD;
        this.descripcion = descripcionD;
        this.tipo = tipoD;
    }

    Denuncia(double latD, double longD, String descripcionD, String tipoD, String fechaD){
        this.latitud = latD;
        this.longitud = longD;
        this.descripcion = descripcionD;
        this.tipo = tipoD;
        this.fecha = fechaD;
    }

    Denuncia(double latD, double longD, String descripcionD, String tipoD){
        this.latitud = latD;
        this.longitud = longD;
        this.descripcion = descripcionD;
        this.tipo = tipoD;
    }

    int id;
    double latitud;
    double longitud;
    String descripcion;
    String tipo;
    String fecha;
}

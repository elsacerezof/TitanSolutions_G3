package es.unican.alejandro.tus_practica3.Model;

import android.support.annotation.NonNull;

/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */

public class Parada implements Comparable{

    private String name;
    private String numero;
    private int identifier;

    public Parada(String name, String numero, int identifier){
        this.name = name;
        this.numero = numero;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public int compareTo(@NonNull Object o) {

        Parada parada=(Parada)o;
        String nombreP1=this.getName();
        String nombreP2=parada.getName();

        int compare=nombreP1.compareTo(nombreP2);
        if (compare<0){
            //nombre P1 va antes que nombre P2
        }else if(compare>0){
            //nombre P2 va antes que nombreP1
        }else{
            //son iguales
        }
        return compare;
    }
}

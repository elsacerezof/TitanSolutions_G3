package es.unican.g3.tus.model;

import android.support.annotation.NonNull;

/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */

public class Parada implements Comparable{

    private String name;
    private String alias="";
    private String notas="";
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

    public String getAlias(){ return alias; }

    public void setAlias(String alias){ this.alias=alias;}

    public String getNotas(){ return notas;}

    public void setNotas(String notas){ this.notas=notas;}

    @Override
    public int compareTo(@NonNull Object o) {

        Parada parada=(Parada)o;
        String nombreP1=this.getName();
        String nombreP2=parada.getName();

        //si es menor que 0, nombre p1 va antes que nombre p2
        //si mayor que 0, lo contrario
        //si es 0, son iguales
        return(nombreP1.compareTo(nombreP2));
    }

    public boolean equals(Object o) {
        Parada parada = (Parada) o;
        return this.identifier == parada.getIdentifier();
    }
}

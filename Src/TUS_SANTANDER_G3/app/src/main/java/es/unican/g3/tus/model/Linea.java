package es.unican.g3.tus.model;


import android.support.annotation.NonNull;

/**
 * Linea
 */

public class Linea{
    private String name;
    private String numero;
    private int identifier;

    public Linea(String name, String numero, int identifier){
        this.name = name;
        this.numero = numero;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }


    public String getNumero() {
        return numero;
    }


    public int getIdentifier() {
        return identifier;
    }

    public boolean equals(Object o) {
        Linea linea = (Linea) o;
        return this.identifier == linea.getIdentifier();
    }



}

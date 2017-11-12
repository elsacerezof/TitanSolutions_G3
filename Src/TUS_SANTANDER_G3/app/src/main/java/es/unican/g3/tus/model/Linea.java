package es.unican.g3.tus.model;


import android.util.Log;

/**
 * Linea
 */

public class Linea implements Comparable{
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

    @Override
    public int compareTo(Object o) {
        Linea linea = (Linea) o;

        // Número base
        String numeroL1 = this.getNumero();

        // Número a comparar
        String numeroL2 = linea.getNumero();

        // si es menor que 0, número L1 va antes que número L2
        // si mayor que 0, lo contrario
        // si es 0, son iguales
        if(!Character.isDigit(numeroL2.charAt(0))) {
            // Líneas especiales (NX, EX) al final
            return -1;
        } else if(!Character.isDigit(numeroL1.charAt(0))) {
            // Líneas especiales (NX, EX) al final
            return 1;
        } else {
            // Se eliminan los códigos de parada (C1, C2...) para comparar
            int numeroLimpioL1 = Integer.parseInt(numeroL1.replaceAll("[A-Z,a-z]{1}[0-9]", ""));
            int numeroLimpioL2 = Integer.parseInt(numeroL2.replaceAll("[A-Z,a-z]{1}[0-9]", ""));
            return (numeroLimpioL1 - numeroLimpioL2);
        }
    }

}

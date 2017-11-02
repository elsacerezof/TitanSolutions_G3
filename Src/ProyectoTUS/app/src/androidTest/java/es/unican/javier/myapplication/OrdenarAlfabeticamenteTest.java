package es.unican.javier.myapplication;


import org.junit.Test;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.unican.alejandro.tus_practica3.Model.Parada;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * Created by LuisOsle on 30/10/17.
 */

public class OrdenarAlfabeticamenteTest {

    /**
     * Test para comprobar la que el sistema de ordenación de paradas, funciona correctamente.
     * @throws Exception
     */

    @Test
    public void ordenarAlfabeticamentePU1() throws Exception {

        List<Parada> lista = new LinkedList<Parada>();
        Parada p1= new Parada("Camarreal Penacastillo","499", 42063);
        Parada p2= new Parada("Ortega y Gasset.28","500", 42064);
        Parada p3= new Parada("Avenida de Cantabria.35","505", 50693);
        Parada p4= new Parada("Nuevo Parque","307", 100);

        lista.add(p4);
        lista.add(p2);
        lista.add(p3);
        lista.add(p1);

        //Lo ordeno

        Collections.sort(lista);

        //primera parada
        assertEquals("Avenida de Cantabria.35", lista.get(0).getName());
        assertEquals("505", lista.get(0).getNumero());
        assertEquals(50693,lista.get(0).getIdentifier());

        //Segunda parada
        assertEquals("Camarreal Penacastillo", lista.get(1).getName());
        assertEquals("499", lista.get(1).getNumero());
        assertEquals(42063,lista.get(1).getIdentifier());

        //Tercera parada
        assertEquals("Nuevo Parque", lista.get(2).getName());
        assertEquals("307", lista.get(2).getNumero());
        assertEquals(100,lista.get(2).getIdentifier());

        //Cuarta parada
        assertEquals("Ortega y Gasset.28", lista.get(3).getName());
        assertEquals("500", lista.get(3).getNumero());
        assertEquals(42064,lista.get(3).getIdentifier());

    }// tesParadas

    @Test
    public void ordenarAlfabeticamentePU2() throws Exception {

        List<Parada> lista = new LinkedList<Parada>();
        Parada p1= new Parada("Camarreal Peñacastillo","499", 42063);
        Parada p2= new Parada("Ortega y Gasset.28","500", 42064);
        Parada p3= new Parada("Avenida de Cantabria.35","505", 50693);
        Parada p4= new Parada("Nuevo Parque","307", 100);

        //Lo ordeno

        Collections.sort(lista);

        //Si le paso una lista vacia

        assertTrue(lista.size()==0);
    }
}


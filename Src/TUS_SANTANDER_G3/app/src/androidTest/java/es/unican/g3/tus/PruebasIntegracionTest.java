package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.ParserJSON;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;

import static junit.framework.Assert.assertEquals;

public class PruebasIntegracionTest {

    /**
     * Test que implementa la prueba de integración PI1
     * @throws Exception excepcion
     */
    @Test
    public void pruebaIntegracion1() throws Exception {

        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus);

        List<Parada> lista = ParserJSON.readArrayParadasBus(is);

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



    }


    /**
     * Test que implementa la prueba de integración PI2
     * @throws Exception excepcion
     */
    @Test
    public void pruebaIntegracion2() throws Exception {

        RemoteFetch remoteFetch = new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_PARADAS_BUS);
        InputStream is = remoteFetch.getBufferedData();
        List<Parada> lista = ParserJSON.readArrayParadasBus(is);

        // Se ordena
        Collections.sort(lista);

        // Primera parada
        assertEquals("Calle Arriba Fte . 103", lista.get(0).getName());
        assertEquals("471", lista.get(0).getNumero());
        assertEquals(29520,lista.get(0).getIdentifier());

        // Segunda parada
        assertEquals("Ricardo Leon", lista.get(1).getName());
        assertEquals("473", lista.get(1).getNumero());
        assertEquals(29521,lista.get(1).getIdentifier());

        // Tercera parada
        assertEquals("Marques de Hazas - 1", lista.get(2).getName());
        assertEquals("479", lista.get(2).getNumero());
        assertEquals(30589,lista.get(2).getIdentifier());

        // Cuarta parada
        assertEquals("Marques de Hazas - 2", lista.get(3).getName());
        assertEquals("480", lista.get(3).getNumero());
        assertEquals(30590,lista.get(3).getIdentifier());

        // Quinta parada
        assertEquals("Jeronimo Sainz de la Maza ( Mercado Mexico )", lista.get(4).getName());
        assertEquals("481", lista.get(4).getNumero());
        assertEquals(30597,lista.get(4).getIdentifier());

    }

}

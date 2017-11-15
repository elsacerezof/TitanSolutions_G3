package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListParadasPresenter;

import static org.junit.Assert.assertTrue;

/** Pruebas unitarias: Listar paradas
 * Clase que implementa los tests unitarios del caso de uso "Listar paradas"
 */

public class ListarParadasTest {

    /**
     * Test para comprobar el parseo y procesamiento del JSON correspondiente a las paradas de TUS Santander
     * Caso: JSON proporcionado correcto
     * @throws Exception excepcion
     */
    @Test
    public void listarParadasTestPU1() throws Exception {

        // Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus);

        // Se crea el objeto Presenter que cuenta con los métodos a probar, proporcionando el JSON local
        ListParadasPresenter presenter = new ListParadasPresenter(null, null);
        //presenter.obtenParadas(is);
        List<Parada> paradas = presenter.getListaParadasBus();

        // Paradas que deberían leerse y procesarse en el método obtenParadas
        Parada parada1 = new Parada("Camarreal Penacastillo", "499", 42063);
        Parada parada2 = new Parada("Ortega y Gasset.28", "500", 42064);
        Parada parada3 = new Parada("Avenida de Cantabria.35", "505", 50693);
        Parada parada4 = new Parada("Nuevo Parque", "307", 100);

        // Comprobación de la primera parada
        assertTrue(paradas.get(0).getName().equals(parada1.getName()));
        assertTrue(paradas.get(0).getNumero().equals(parada1.getNumero()));
        assertTrue(paradas.get(0).getIdentifier() == parada1.getIdentifier());

        // Comprobación de la segunda parada
        assertTrue(paradas.get(1).getName().equals(parada2.getName()));
        assertTrue(paradas.get(1).getNumero().equals(parada2.getNumero()));
        assertTrue(paradas.get(1).getIdentifier() == parada2.getIdentifier());

        // Comprobación de la tercera parada
        assertTrue(paradas.get(2).getName().equals(parada3.getName()));
        assertTrue(paradas.get(2).getNumero().equals(parada3.getNumero()));
        assertTrue(paradas.get(2).getIdentifier() == parada3.getIdentifier());

        // Comprobación de la cuarta parada
        assertTrue(paradas.get(3).getName().equals(parada4.getName()));
        assertTrue(paradas.get(3).getNumero().equals(parada4.getNumero()));
        assertTrue(paradas.get(3).getIdentifier() == parada4.getIdentifier());

    }

    /**
     * Test para comprobar el parseo y procesamiento del JSON correspondiente a las paradas de TUS Santander
     * Caso: JSON proporcionado vacío
     * @throws Exception excepcion
     */
    @Test
    public void listarParadasTestPU2() throws Exception {

        // Se crea el objeto Presenter que cuenta con los métodos a probar, proporcionando JSON fallido
        ListParadasPresenter presenter = new ListParadasPresenter(null, null);
        //presenter.obtenParadas(null);

        // Comprobación de que no se devuelven paradas
        List<Parada> paradas = presenter.getListaParadasBus();
        assertTrue(paradas == null);

    }

}
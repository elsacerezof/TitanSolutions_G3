package es.unican.g3.tus.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.ParserJSON;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;

/** Clase para obtener y sincronizar asincronamente los datos proporcionados por el
 *  Ayuntamiento de Santander sobre el servicio TUS.
 *
 *  Una vez realizado se invoca la actividad principal.
 *
 * Created by fernando on 9/11/17.
 */

public class LoadDataAsync extends AsyncTask<Object, Boolean, Boolean> {

    private List<Parada> listaParadasBus;
    private RemoteFetch remoteFetchParadas;
    private List<Linea> listaLineasBus;
    private RemoteFetch remoteFetchLineas;
    private Context contextLlamador;
    private Activity activityLlamadora;

    public LoadDataAsync(Activity activity, Context context) {
        this.remoteFetchParadas = new RemoteFetch();
        this.remoteFetchLineas = new RemoteFetch();
        this.contextLlamador = context;
        this.activityLlamadora = activity;

    }

    /**
     * Descarga las paradas del servicio remoto externo.
     *
     * @return InputStream
     */
    private InputStream descargaParadas()
    {
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_PARADAS_BUS);
            return remoteFetchParadas.getBufferedData();
        } catch (IOException e) {
            Log.w("Error", e);
            return null;
        }
    }
    /**
     * Descarga las lineas del servicio remoto externo.
     *
     * @return InputStream
     */
    private InputStream descargaLineas()
    {
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
            return remoteFetchLineas.getBufferedData();
        } catch (IOException e) {
            Log.w("Error", e);
            return null;
        }
    }

    /**
     * Método a través del cual se almacenan las paradas de buses en el atributo listaParadasBus
     * de esta clase. Para ello se parsea el JSON recibido por argumento.
     * @param i stream que contiene el JSON
     * @return boolean
     */
    public boolean obtenParadas(InputStream i) {
        try {
            if(i != null) {
                listaParadasBus = ParserJSON.readArrayParadasBus(i);
                Log.d("ENTRA", "Obten paradas: " + listaParadasBus.size());
                return true;
            }else{
                Log.e("ERROR", "Input obtenparadas nulo");
                return false;
            }
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las paradas de bus: "+e.getMessage());
            Log.w("", e);
            return false;
        }//try
    }//obtenParadas

    public boolean obtenLineas(InputStream i) {
        try {
            if(i != null) {
                listaLineasBus = ParserJSON.readArrayLineasBus(i);
                Log.d("ENTRA", "Obten lineas: " + listaLineasBus.size());
                return true;
            }else{
                Log.e("ERROR", "Input obten lineas nulo");
                return false;
            }
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las lineas de bus: "+e.getMessage());
            Log.w("", e);
            return false;
        }//try
    }//obtenParadas


    public List<Parada> getListaParadasBus() {
        return listaParadasBus;
    }//getListaParadasBus
    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaParadasBus

    @Override
    protected Boolean doInBackground(Object... objects) {
        // Se cargan "de fondo" las paradas
        obtenParadas(descargaParadas());
        obtenLineas(descargaLineas());
        return true;
    }

    /**
     * Acciones a ejecutar una vez se ha realizado la carga de datos.
     *
     * @param bool estado
     */
    @Override
    protected void onPostExecute(Boolean bool) {
        // Se accede a la aplicación
       // Intent intent = new Intent(contextLlamador, es.unican.g3.tus.views.MainActivity.class);
        //contextLlamador.startActivity(intent);
       // activityLlamadora.finish();

        // Se muestra mensaje de error o correcto
        if(getListaParadasBus() == null || getListaLineasBus()==null) {
            // Mensaje de error
            Toast.makeText(contextLlamador, R.string.app_fallo_conexion, Toast.LENGTH_SHORT).show();
        } else {
            // Mensaje correcto
            Toast.makeText(contextLlamador, R.string.app_carga_datos_ok, Toast.LENGTH_SHORT).show();
            // Sincronización de datos remotos con locales
            Database db = new Database(contextLlamador);
            db.sincronizarParadas(getListaParadasBus());
            db.sincronizarLineas(getListaLineasBus());
        }
    }

}
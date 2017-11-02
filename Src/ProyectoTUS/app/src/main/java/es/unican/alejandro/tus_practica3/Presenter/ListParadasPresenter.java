package es.unican.alejandro.tus_practica3.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.alejandro.tus_practica3.Model.DataLoaders.ParserJSON;
import es.unican.alejandro.tus_practica3.Model.DataLoaders.RemoteFetch;
import es.unican.alejandro.tus_practica3.Model.Parada;
import es.unican.alejandro.tus_practica3.R;
import es.unican.alejandro.tus_practica3.Views.IListParadasView;

/**
 * Created by alejandro on 11/10/17.
 */

public class ListParadasPresenter  {
    private IListParadasView listParadasView;
    private List<Parada> listaParadasBus;
    private RemoteFetch remoteFetchParadas;
    private Context context;

    public ListParadasPresenter(Context context, IListParadasView listParadasView){
        this.listParadasView = listParadasView;
        this.remoteFetchParadas = new RemoteFetch();
        this.context = context;
    }// ListParadasPresenter

    public void start(){
        // Se muestra mensaje de carga
        listParadasView.showProgress(true);
        // Se llama a la tarea asíncrona que ejecuta obtenParadas() en un thread en vez de directamente al método
        new obtenerParadasAsync().execute();
    }// start


    /**
     * Método a través del cual se almacenan las Paradas de buses en el atributo listaParadasBus
     * de esta clase. Para realizar esto internamente realiza una llamada a la función
     * getJSON (RemoteFetch) para seguidamente parsear el JSON obtenido con la llamada
     * a readArrayParadasBus (ParserJSON)
     * @return boolean
     */
    public boolean obtenParadas(InputStream i){
        try {

            listaParadasBus = ParserJSON.readArrayParadasBus(i);
            Log.d("ENTRA", "Obten paradas:"+listaParadasBus.size());
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las paradas de bus: "+e.getMessage());
            Log.w("", e);
            return false;
        }//try
    }//obtenParadas


    public List<Parada> getListaParadasBus() {
        return listaParadasBus;
    }//getListaParadasBus


    /**
     * Método para obtener un cadena de texto con todas las paradas. En esta cadena
     * se muestra unicamente el nombre de la parada
     *  @return String con todas las gasolineras separadas por un doble salto de línea
     */
    public String getTextoParadas(){
        String textoParadas="";
        if(listaParadasBus!=null){
            for (int i=0; i<listaParadasBus.size(); i++){
                textoParadas=textoParadas+listaParadasBus.get(i).getNumero()+"\n\n";
            }//for
        }else{
            textoParadas="Sin paradas";
        }//if
        return textoParadas;
    }//getTextoParadas
    public InputStream getDescargaParadas()
    {

        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_PARADAS_BUS);
            return remoteFetchParadas.getBufferedData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public class obtenerParadasAsync extends AsyncTask<Object, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(Object... objects) {
            // Se cargan "de fondo" las líneas en el hilo destinado a la tarea
            return obtenParadas(getDescargaParadas());
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            // Una vez ejecutado, se muestra el listado y el mensaje de carga completa, desactivando el de espera
            if(getListaParadasBus()==null)
            {
                Toast.makeText(context,R.string.app_fallo_conexion, Toast.LENGTH_SHORT).show();
                listParadasView.showList(getListaParadasBus());
            }
            else {
                listParadasView.showList(getListaParadasBus());
                listParadasView.showProgress(false);
                Toast.makeText(context, R.string.app_carga_datos_ok, Toast.LENGTH_SHORT).show();
            }
        }
    }
}// ListParadasPresenter

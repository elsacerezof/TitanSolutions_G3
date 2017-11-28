package es.unican.g3.tus.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.ParserJSON;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;
import es.unican.g3.tus.views.EstimacionesFragment;
import es.unican.g3.tus.views.IListEstimacionesView;
import es.unican.g3.tus.views.IListParadasView;
import es.unican.g3.tus.views.ParadasFragment;

public class ListEstimacionesPresenter  {
    private static final String ERROR ="error" ;
    private IListEstimacionesView listEstimacionesView;
    private List<Estimacion> listaEstimacionesBus;
    private Context context;
    private RemoteFetch remoteFetchEstimaciones;
    private Database db;

    public ListEstimacionesPresenter(Context context, EstimacionesFragment listEstimacionesView){
        this.context = context;
        this.listEstimacionesView = listEstimacionesView;

        db = new Database(context);
        //this.remoteFetchEstimaciones = new RemoteFetch();
        //this.doInBackground();
        this.listaEstimacionesBus = db.recuperarEstimaciones();
        listEstimacionesView.showList(getListaEstimacionesBus(), false);

        //db.reiniciar();

    }// ListEstimacionesPresenter

    public List<Estimacion> getListaEstimacionesBus() {
        return listaEstimacionesBus;
    }

    public IListEstimacionesView getListEstimacionesView(){
        return listEstimacionesView;
    }

    public Context getContext(){
        return context;
    }




}// ListParadasPresenter

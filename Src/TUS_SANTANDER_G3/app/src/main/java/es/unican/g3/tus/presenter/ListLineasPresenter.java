package es.unican.g3.tus.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;
import es.unican.g3.tus.views.IListLineasView;
import es.unican.g3.tus.views.IListParadasView;
import es.unican.g3.tus.views.LineasFragment;
import es.unican.g3.tus.views.ParadasFragment;

/**
 * Created by jsmr on 10/11/17.
 */

public class ListLineasPresenter {
    private IListLineasView listLineasView;
    private List<Linea> listaLineasBus;
    private List<Linea> listaAux= new ArrayList<Linea>();
    private RemoteFetch remoteFetchLineas;
    private Context context;

    public ListLineasPresenter(Context context, LineasFragment listLineasView){
        this.listLineasView = listLineasView;
        Database db = new Database(context);
        this.listaLineasBus = db.recuperarLineas();
        for(int p=0;p<1000;p++)
        {
            for(int i=0;i<listaLineasBus.size();i++)
            {

                if(listaLineasBus.get(i).getIdentifier()==p)
                {
                    listaAux.add(listaLineasBus.get(i));
                }
            }
        }
        listaLineasBus=listaAux;
        listLineasView.showList(listaLineasBus, false);
    }// ListParadasPresenter

    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus
}

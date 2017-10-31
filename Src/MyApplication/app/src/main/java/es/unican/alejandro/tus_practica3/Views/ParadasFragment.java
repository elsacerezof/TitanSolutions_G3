package es.unican.alejandro.tus_practica3.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import es.unican.alejandro.tus_practica3.Model.Parada;
import es.unican.alejandro.tus_practica3.Presenter.ListParadasPresenter;
import es.unican.alejandro.tus_practica3.R;

/**
 * A fragment representing a list of Items.
 */
public class ParadasFragment extends ListFragment implements IListParadasView {

    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private ListParadasPresenter listLineasPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paradas_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listLineasPresenter = new ListParadasPresenter(getContext(),this);
        this.dialog = new ProgressDialog(getContext());
        this.listLineasPresenter.start();
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+position);
        //Haciendo uso de la interfaz DataCommunication podemos enviar los datos entre fragmentos
        //Ejemplo:
        //dataCommunication = (DataCommunication) getContext();
        //dataCommunication.setLineaIdentifier(datosBuses.getListaLineasBus().get(position).getIdentifier());
    }

    @Override
    public void showList(List<Parada> lineaList) {
        if(lineaList!=null) {
            ListParadasAdapter listLineasAdapter = new ListParadasAdapter(getContext(), lineaList);
            getListView().setAdapter(listLineasAdapter);
        }
        else{
            //Toast.makeText(context,"error", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }

    /**
     * Este método cuando es llamado se encarga de mostrar un progressDialog
     * @param state si es true pone el progressDialog en la interfaz, si es false lo cancela
     */
    @Override
    public void showProgress (boolean state){
        if(state){
            dialog.setMessage(getContext().getString(R.string.app_carga_datos_enproceso));
            dialog.show();
        }else{
            dialog.dismiss();
        }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu,menu);

    }
}

package es.unican.g3.tus.views;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.alejandro.tus_practica3.R;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListParadasPresenter;

/**
 * A fragment representing a list of Items.
 */
public class ParadasFragment extends ListFragment implements IListParadasView{

    private ProgressDialog dialog;
    private ListParadasPresenter listLineasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_paradas_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listLineasPresenter = new ListParadasPresenter(getContext(),this);
        listLineasPresenter.getListaParadasBus().get(3).setAlias("mi casita");
        listLineasPresenter.getListaParadasBus().get(5).setNotas("domino");
        this.dialog = new ProgressDialog(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+Integer.toString(position));
        //Haciendo uso de la interfaz DataCommunzication podemos enviar los datos entre fragmentos
    }

    @Override
    public void showList(List<Parada> lineaList, boolean ordenadas) {
        if(lineaList!=null) {
            ListParadasAdapter listParadasAdapter;
            if(ordenadas) {
                listParadasAdapter = new ListParadasAdapter(getContext(), lineaList, true);
                getListView().setAdapter(listParadasAdapter);
            }else{
                listParadasAdapter = new ListParadasAdapter(getContext(), lineaList, false);
                getListView().setAdapter(listParadasAdapter);
            }
        }
        else{
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


        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();


        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                List<Parada> paradaList = new ArrayList<Parada>();
                for(int i=0;i<listLineasPresenter.getListaParadasBus().size();i++)
                {
                    if(listLineasPresenter.getListaParadasBus().get(i).getName().indexOf(newText)!=-1 ||
                            listLineasPresenter.getListaParadasBus().get(i).getAlias().indexOf(newText)!=-1 ||
                            listLineasPresenter.getListaParadasBus().get(i).getNumero().indexOf(newText)!=-1 ||
                            listLineasPresenter.getListaParadasBus().get(i).getNotas().indexOf(newText)!=-1)
                    {
                        paradaList.add(listLineasPresenter.getListaParadasBus().get(i));
                    }
                }
                showList(paradaList,false);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){



        if(item.getItemId()==R.id.action_search)
        {


                return true;
        }
        else {
            List<Parada> paradaList = listLineasPresenter.getListaParadasBus();
            if (paradaList != null) {
                Collections.sort(paradaList);
            }
            showList(paradaList, true);
            return true;
        }

    }
}

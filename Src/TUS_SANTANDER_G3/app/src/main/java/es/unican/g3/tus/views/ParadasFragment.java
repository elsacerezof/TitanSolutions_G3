package es.unican.g3.tus.views;

import android.app.ProgressDialog;
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

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListParadasPresenter;

/**
 * A fragment representing a list of Items.
 */
public class ParadasFragment extends ListFragment implements IListParadasView{

    private ProgressDialog dialog;
    private ListParadasPresenter listParadasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_paradas_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listParadasPresenter = new ListParadasPresenter(getContext(),this);
        listParadasPresenter.getListaParadasBus().get(3).setAlias("mi casita");
        listParadasPresenter.getListaParadasBus().get(5).setNotas("domino");
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

    /**
     * Este método se encarga de devolver un listado con todas las paradas que tienen alguna
     * coincidencia en alguno de sus campos con el texto indicado
     *
     * @param filterText texto con el que filtrar los resultados
     * @return
     */
    public List<Parada> searchFilterList (List<Parada> paradas, String filterText) {

        // Almacén de paradas coincidentes con el término de búsqueda
        List<Parada> paradaFiltradas = new ArrayList<Parada>();

        // Se recorren las paradas, almacenando aquellas que muestran coincidencias
        for(int i = 0; i < paradas.size(); i++)
        {
            if(paradas.get(i).getName().indexOf(filterText)!=-1 ||
                    paradas.get(i).getAlias().indexOf(filterText)!=-1 ||
                    paradas.get(i).getNumero().indexOf(filterText)!=-1 ||
                    paradas.get(i).getNotas().indexOf(filterText)!=-1)
            {
                paradaFiltradas.add(paradas.get(i));
            }
        }

        return paradaFiltradas;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu,menu);

        // Interfaz gráfica búsqueda
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.search_placeholder));

        // Atención de las consultas de búsqueda
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public List<Parada> paradas = listParadasPresenter.getListaParadasBus();

            public boolean onQueryTextChange(String filterText) {
                showList(searchFilterList(paradas, filterText), false);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        boolean resultado= false;

        if(item.getItemId()==R.id.action_search) {
            resultado= true;
        } else {
            List<Parada> paradaList = listParadasPresenter.getListaParadasBus();
            if (paradaList != null) {
                Collections.sort(paradaList);
            }
            showList(paradaList, true);
            resultado= true;
        }

        return resultado;

    }
}

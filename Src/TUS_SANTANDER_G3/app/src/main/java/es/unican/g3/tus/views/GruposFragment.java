package es.unican.g3.tus.views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListGruposPresenter;
import es.unican.g3.tus.presenter.LoadDataAsync;

/**
 * A fragment representing a list of Items.
 */
public class GruposFragment extends ListFragment implements IListGruposView {

    private ListGruposPresenter listGruposPresenter;
    private List<Object> listAux=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return(inflater.inflate(R.layout.fragment_paradas_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listGruposPresenter = new ListGruposPresenter(getContext(),this);
        setHasOptionsMenu(false);

        // Detectar y actuar ante un click largo
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listGruposPresenter.eliminaParadaColor(position);

                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+Integer.toString(position));
        //Haciendo uso de la interfaz DataCommunzication podemos enviar los datos entre fragmentos
        List<Parada> listaP=new ArrayList<>();
        int i=0;
        for (Object p:listAux)
        {
            if(p.toString().contains("Parada"))
            {
                listaP.add((Parada)p);
            }
            else
            {
                listaP.add(null);
            }
            i++;
        }
        EstimacionesFragment fragmentEstimaciones = new EstimacionesFragment();
        fragmentEstimaciones.añadeParada(listaP.get(position).getNumero());
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutElements, fragmentEstimaciones);
        ft.commit();
    }

    @Override
    public void showList(List<Object> listadoGruposConParadas) {
        if(listadoGruposConParadas != null) {
            listAux=listadoGruposConParadas;
            ListGruposAdapter listGruposAdapter = new ListGruposAdapter(getContext(), listadoGruposConParadas);
            getListView().setAdapter(listGruposAdapter);
        }
    }

    public void refreshView() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutElements, new GruposFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }
}

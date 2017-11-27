package es.unican.g3.tus.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Grupo;
import es.unican.g3.tus.model.Parada;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListGruposAdapter extends ArrayAdapter {
    private List<Object> listado;
    private Context context;

    private static final int TIPO_PARADA = 0;
    private static final int TIPO_GRUPO = 1;

    public ListGruposAdapter(Context context, List<Object> listadoGruposConParadas){
        super(context, R.layout.custom_list_paradas_layout, listadoGruposConParadas);
        this.context = context;
        this.listado = listadoGruposConParadas;
    }// ListGruposAdapter

    @Override
    public Object getItem(int position) {
        return listado.get(position);
    }

    @Override
    public int getViewTypeCount() {
        // Número de tipos de visualización que tiene la lista
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Parada) {
            return TIPO_PARADA;
        }
        return TIPO_GRUPO;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position) == TIPO_PARADA);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TIPO_PARADA:
                    convertView = layoutInflater.inflate(R.layout.custom_list_paradas_layout, parent, false);
                    break;
                case TIPO_GRUPO:
                    convertView = layoutInflater.inflate(R.layout.custom_list_grupocabecera_layout, parent, false);
                    break;
            }
        }

        switch (type) {
            case TIPO_PARADA:
                Parada parada = (Parada)getItem(position);
                TextView textViewName = convertView.findViewById(R.id.textViewName);
                TextView textViewNumero =  convertView.findViewById(R.id.textViewNumero);
                textViewName.setText(parada.getName().trim());
                textViewNumero.setText(parada.getNumero().trim());

                break;
            case TIPO_GRUPO:
                Grupo grupo = (Grupo)getItem(position);
                TextView title = (TextView)convertView.findViewById(R.id.groupTitle);
                LinearLayout grupoCabecera = (LinearLayout)convertView.findViewById(R.id.linearLayoutGrupoCabecera);
                title.setText(grupo.getNombre());
                grupoCabecera.setBackgroundColor(Color.parseColor(grupo.getColor()));
                break;
        }

        return convertView;
    }

}// ListParadasAdapter

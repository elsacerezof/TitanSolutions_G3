package es.unican.alejandro.tus_practica3.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;

import java.util.List;


import es.unican.alejandro.tus_practica3.Model.Parada;
import es.unican.alejandro.tus_practica3.R;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListParadasAdapterSorted extends ArrayAdapter {
    private List<Parada> paradasBus;
    private Context context;

    public ListParadasAdapterSorted (Context context, List<Parada> paradasBus){
        super(context, R.layout.custom_list_paradas_layout,paradasBus);
        Collections.sort(paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;
    }// ListparadasAdapter



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_paradas_layout,null,true);
        TextView textViewName = viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = viewRow.findViewById(R.id.textViewNumero);
        textViewName.setText(paradasBus.get(position).getName().trim());
        textViewNumero.setText(paradasBus.get(position).getNumero().trim());

        return viewRow;
    }

}// ListParadasAdapter

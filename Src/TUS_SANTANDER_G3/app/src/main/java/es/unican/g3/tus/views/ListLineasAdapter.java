package es.unican.g3.tus.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import es.unican.alejandro.tus_practica3.R;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListLineasAdapter extends ArrayAdapter {
    private List<Linea> lineasBus;
    private Context context;

    public ListLineasAdapter(Context context, List<Linea> lineasBus, boolean ordenadas){
        super(context, R.layout.custom_list_lineas_layout,lineasBus);
        //if(ordenadas){
        //    Collections.sort(lineasBus);
        //}
        this.context = context;
        this.lineasBus = lineasBus;
    }// ListparadasAdapter



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewNumero =  viewRow.findViewById(R.id.textViewNumero);
        TextView textViewName = viewRow.findViewById(R.id.textViewName);
        switch (lineasBus.get(position).getName())
        {
            case "1":
                textViewName.setTextColor(Color.parseColor("#ff0000"));

                break;
            case "2":
                textViewName.setTextColor(Color.parseColor("#a800d0"));

                break;
            case "3":
                textViewName.setTextColor(Color.parseColor("#ffcd00"));

                break;
            case "4":
                textViewName.setTextColor(Color.parseColor("#25b3d8"));

                break;
            case "5":
                textViewName.setTextColor(Color.parseColor("#969696"));

                break;
            case "6":
                textViewName.setTextColor(Color.parseColor("#008032"));

                break;
            case "7":
                textViewName.setTextColor(Color.parseColor("#f66210"));

                break;
            case "11":
                textViewName.setTextColor(Color.parseColor("#01125c"));

                break;
            case "12":
                textViewName.setTextColor(Color.parseColor("#a2d65c"));

                break;
            case "13":
                textViewName.setTextColor(Color.parseColor("#9080b0"));

                break;
            case "14":
                textViewName.setTextColor(Color.parseColor("#0066b0"));

                break;
            case "16":
                textViewName.setTextColor(Color.parseColor("#631637"));

                break;
            case "17":
                textViewName.setTextColor(Color.parseColor("#f98083"));

                break;
            case "19":
                textViewName.setTextColor(Color.parseColor("#038495"));

                break;
            case "18":
                textViewName.setTextColor(Color.parseColor("#b0e8df"));

                break;
            case "20":
                textViewName.setTextColor(Color.parseColor("#78fa9e"));

                break;
            case "21":
                textViewName.setTextColor(Color.parseColor("#a1d55d"));

                break;
            case "23":
                textViewName.setTextColor(Color.parseColor("#cacaca"));

                break;
            case "N1":
                textViewName.setTextColor(Color.parseColor("#000000"));

                break;
            case "N2":
                textViewName.setTextColor(Color.parseColor("#000000"));

                break;
            case "N3":
                textViewName.setTextColor(Color.parseColor("#000000"));

                break;
        }
        textViewNumero.setText(lineasBus.get(position).getNumero().trim());
        textViewName.setText(lineasBus.get(position).getName().trim());


        return viewRow;
    }

}// ListParadasAdapter

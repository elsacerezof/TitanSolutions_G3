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

import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Parada;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListEstimacionesAdapter extends ArrayAdapter {
    private List<Estimacion> estimacionesBus;
    private Context context;

    public ListEstimacionesAdapter(Context context, List<Estimacion> estimacionesBus){
        super(context, R.layout.custom_list_estimaciones_layout,estimacionesBus);
        this.context = context;
        this.estimacionesBus = estimacionesBus;
    }// ListparadasAdapter




    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_estimaciones_layout,null,true);
        View viewRow1 = layoutInflater.inflate(R.layout.fragment_estimaciones_list,null,true);
        TextView textViewTitle = viewRow1.findViewById(R.id.textViewTitle);
        TextView textViewDistancia = viewRow.findViewById(R.id.textViewDistancia);
        textViewTitle.setText("Estimaciones");
        TextView textViewTiempo =  viewRow.findViewById(R.id.textViewTiempo);
        TextView textViewNumero = viewRow.findViewById(R.id.textViewLineaa);
        textViewNumero.setText(estimacionesBus.get(position).getLinea());
        //textViewParadaId.setText(String.valueOf(estimacionesBus.get(position).getParadaId()));
        textViewDistancia.setText(String.valueOf("Distancia: "+estimacionesBus.get(position).getDistancia())+" m");

        textViewTiempo.setText(String.valueOf("Tiempo: "+Math.round(estimacionesBus.get(position).getTiempo()/60))+" min");
        switch (estimacionesBus.get(position).getLinea())
        {
            case "1":
                textViewNumero.setTextColor(Color.parseColor("#ff0000"));

                break;
            case "2":
                textViewNumero.setTextColor(Color.parseColor("#a800d0"));

                break;
            case "3":
                textViewNumero.setTextColor(Color.parseColor("#ffcd00"));

                break;
            case "4":
                textViewNumero.setTextColor(Color.parseColor("#25b3d8"));

                break;
            case "5C1":
            case "5C2":
                textViewNumero.setTextColor(Color.parseColor("#969696"));

                break;
            case "6C1":
            case "6C2":
                textViewNumero.setTextColor(Color.parseColor("#008032"));

                break;
            case "7C1":
            case "7C2":
                textViewNumero.setTextColor(Color.parseColor("#f66210"));

                break;
            case "11":
                textViewNumero.setTextColor(Color.parseColor("#01125c"));

                break;
            case "12":
                textViewNumero.setTextColor(Color.parseColor("#a2d65c"));

                break;
            case "13":
                textViewNumero.setTextColor(Color.parseColor("#9080b0"));

                break;
            case "14":
                textViewNumero.setTextColor(Color.parseColor("#0066b0"));

                break;
            case "16":
                textViewNumero.setTextColor(Color.parseColor("#631637"));

                break;
            case "17":
                textViewNumero.setTextColor(Color.parseColor("#f98083"));

                break;
            case "19":
                textViewNumero.setTextColor(Color.parseColor("#038495"));

                break;
            case "18":
                textViewNumero.setTextColor(Color.parseColor("#b0e8df"));

                break;
            case "20":
                textViewNumero.setTextColor(Color.parseColor("#78fa9e"));

                break;
            case "21":
                textViewNumero.setTextColor(Color.parseColor("#a1d55d"));

                break;
            case "23":
                textViewNumero.setTextColor(Color.parseColor("#cacaca"));

                break;
            case "N1":
            case "N2":
            case "N3":
            default:
                textViewNumero.setTextColor(Color.parseColor("#000000"));

                break;
        }

        return viewRow;
    }

}// ListParadasAdapter
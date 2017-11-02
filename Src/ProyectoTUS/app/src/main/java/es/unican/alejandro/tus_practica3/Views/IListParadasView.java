package es.unican.alejandro.tus_practica3.Views;

import java.util.List;

import es.unican.alejandro.tus_practica3.Model.Parada;

/**
 * Created by alejandro on 11/10/17.
 */

public interface IListParadasView {
    void showList(List<Parada> paradaList);
    void showProgress(boolean state);
}//IListLineasView

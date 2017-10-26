package es.unican.elsacf.proyectotus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParadasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ParadasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParadasFragment extends Fragment {
    // TODO: Declarar los argumentos de los parametros de inicializacion
    // parametros de inicializacion del fragmento
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Declarar parametros
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ParadasFragment() {
        // Requerido un constructor publico vacio
    }

    /**
     * Utilizar este metodo para crear una nueva instancia
     * de este fragmento utilizando los parametros proporcionadoste
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ParadasFragment.
     */
    // TODO: cambiar los parametros que procedan
    public static ParadasFragment newInstance(String param1) {
        ParadasFragment fragment = new ParadasFragment();
        Bundle args = new Bundle();
        //args.putX(...) siendo X el tipo de argumento
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paradas, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

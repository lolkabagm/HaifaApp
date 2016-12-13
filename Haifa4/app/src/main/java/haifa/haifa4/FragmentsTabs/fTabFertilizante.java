package haifa.haifa4.FragmentsTabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import haifa.haifa4.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class fTabFertilizante extends Fragment {
    View view;

    Boolean flag_PermitirContinuar;
    ValidacionListenerTabFertilizante validacionListener;

    Spinner spinnerFertilizante;
    TextView textViewFormula;

    final String[] datosSpinner = {"Elem1","Elem2","Elem3","Elem4"};
final  String[]  FormulasProducto ={"11-2-0+36","85-24+26+1","12+45-36-18","37+58-95-14"};
    public fTabFertilizante() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ftabfertilizante, container, false);

        spinnerFertilizante = (Spinner) view.findViewById(R.id.spinnerFertilizante);

        textViewFormula =(TextView) view.findViewById(R.id.txtViewLabelFormula) ;

        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,datosSpinner);

        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFertilizante.setAdapter(adaptadorSpinner);
        spinnerFertilizante.setSelection(0);



        spinnerFertilizante.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        textViewFormula.setText(FormulasProducto[position]);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        textViewFormula.setText("");
                    }
                });




        return view;
    }






    public interface ValidacionListenerTabFertilizante {

        void onFragmentFertilizanteValidado(Boolean validado);
    }

    public void setValidacionListenerFertilizante(ValidacionListenerTabFertilizante listener) {
        this.validacionListener=listener;
    }

    public boolean ValidarFragmentTabFertilizante () {
        Log.d("ERR", "PERFECTO");

        return flag_PermitirContinuar;
    }
}

package haifa.haifa4.FragmentsTabs;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import haifa.haifa4.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class fTabSistema extends Fragment {
    final String Mensaje = "Este campo es obligatorio";
    View view;

    Boolean flag_PermitirContinuar =false;

    Boolean Flag_ValidarVolTanque =false    ;
    Boolean Flag_ValidarInyeccion =false ;
    Boolean Flag_ValidarDesIrrigacion =false ;

    ValidacionListenerTabSistema validacionListener;


    TextInputLayout InputtxtVolumenTanque;
    TextInputLayout InputtxtDescargaIrrigacion;
    TextInputLayout InputtxtInyeccion;

    EditText txtVolumenTanque;
    EditText txtInyeccion ;
    EditText txtDescargaIrrigacion;

    Button btnSiguiente ;

    List<Integer> listNoValidado = new ArrayList<>();
    List<Integer> listValidado = new ArrayList<>();

    public fTabSistema() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fsistema, container, false);

          InputtxtVolumenTanque = (TextInputLayout) view.findViewById(R.id.InputtxtVolumenTanque);
          InputtxtDescargaIrrigacion = (TextInputLayout) view.findViewById(R.id.InputtxtDescargaIrrigacion);
          InputtxtInyeccion = (TextInputLayout) view.findViewById(R.id.InputtxtInyeccion);

          txtVolumenTanque = (EditText) view.findViewById(R.id.txtVolumenTanque);
          txtInyeccion = (EditText) view.findViewById(R.id.txtInyeccion);
          txtDescargaIrrigacion = (EditText) view.findViewById(R.id.txtDescargaIrrigacion);

          btnSiguiente = (Button) view.findViewById(R.id.btnSiguiente);

       // listNoValidado.add("");


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //   view.requestFocus();

               // btnSiguiente.requestFocus();

                    validarPagina(); //VALIDO TEXTOS


                if ( ! flag_PermitirContinuar ) {
                    Log.d("ERR", "ERROR");
                    if (validacionListener!=null) {
                        validacionListener.onFragmentSistemaValidado(false  );
                    }

                }else {
                    Log.d("ERR", "PERFECTO");
                    if (validacionListener!=null) {
                        validacionListener.onFragmentSistemaValidado(true  );
                    }

                }

            }
        });


        txtVolumenTanque.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (txtVolumenTanque.getText().toString().trim().length() == 0) {

                        InputtxtVolumenTanque.setError(Mensaje);
                        flag_PermitirContinuar= false;
                    } else {
                        InputtxtVolumenTanque.setErrorEnabled(false);
                        flag_PermitirContinuar= true;               }
                }
            }
        });


        txtInyeccion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (txtInyeccion.getText().toString().trim().length() == 0) {

                        InputtxtInyeccion.setError(Mensaje);
                        flag_PermitirContinuar= false;

                    } else {
                        InputtxtInyeccion.setErrorEnabled(false);
                        flag_PermitirContinuar= true;
                    }
                }
            }
        });


        txtDescargaIrrigacion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (txtDescargaIrrigacion.getText().toString().trim().length() == 0) {

                        InputtxtDescargaIrrigacion.setError(Mensaje);
                        flag_PermitirContinuar= false;

                    } else {
                        InputtxtDescargaIrrigacion.setErrorEnabled(false);
                        flag_PermitirContinuar= true   ;

                    }
                }
            }
        });


        return view;
    }


    public interface ValidacionListenerTabSistema {

        void onFragmentSistemaValidado(Boolean validado);
    }

   public void setValidacionListenerSistema(ValidacionListenerTabSistema listener) {
        this.validacionListener=listener;
    }

    public boolean ValidarFragmentTabSistema() {


        return validarPagina();
    }



    public boolean validarPagina(){


        if (txtVolumenTanque.getText().toString().trim().length() == 0) {

            InputtxtVolumenTanque.setError(Mensaje);
            Flag_ValidarVolTanque= false;
        }  else {
            InputtxtVolumenTanque.setErrorEnabled(false);
            Flag_ValidarVolTanque= true;
        }


        if (txtInyeccion.getText().toString().trim().length() == 0) {

            InputtxtInyeccion.setError(Mensaje);

            Flag_ValidarInyeccion= false;
        } else{
            InputtxtInyeccion.setErrorEnabled(false);
            Flag_ValidarInyeccion= true;
        }


        if (txtDescargaIrrigacion.getText().toString().trim().length() == 0) {

            InputtxtDescargaIrrigacion.setError(Mensaje);

            Flag_ValidarDesIrrigacion= false;
        }else{
            InputtxtDescargaIrrigacion.setErrorEnabled(false);
            Flag_ValidarDesIrrigacion= true;
        }

        if (Flag_ValidarVolTanque && Flag_ValidarInyeccion && Flag_ValidarDesIrrigacion) {
            flag_PermitirContinuar = true;
            return true;

        } else{
            flag_PermitirContinuar = false;
            return false;

        }
    }
}

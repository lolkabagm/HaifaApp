package haifa.haifa4.FragmentsTabs;


import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import haifa.haifa4.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class fTabElemento extends Fragment implements View.OnClickListener{
    final String Mensaje = "Este campo es obligatorio";

    View view;
    ValidacionListenerTabElemento validacionListener;
     Boolean  flag_PermitirContinuar =Boolean.FALSE;
    Boolean Flag_ValidarBtonElementos=false;
    Boolean Flag_ValidarConcentracion=false;


    TextView textViewConcentracionObjetivo;
    TextInputLayout txtIntLayoutConcentracionObjetivo;
    TextView  txtErrorElementos;

    Button btnK20 ;
    Button btnP205 ;
    Button btnN ;
    Button btnSO3 ;
    Button btnMGO ;
    Button btnCAO ;

    Resources res;
    Drawable drawableBtn;
    Drawable drawableBtnSel;



    static List<Button> listButtons;

    String BotonElementoSeleccionado;
    public fTabElemento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ftabelemento, container, false);


        listButtons = new ArrayList<>();

         res = getResources();
         drawableBtn = res.getDrawable(R.drawable.button);
        drawableBtnSel = res.getDrawable(R.drawable.button_sel);

         Button btnSiguiente = (Button) view.findViewById(R.id.button6);

        txtErrorElementos = (TextView) view.findViewById(R.id.lblErrorElementoRef);
        textViewConcentracionObjetivo = (TextView) view.findViewById(R.id.txtConcentracionObjetivo);
      //  textViewConcentracionObjetivo.requestFocus();
        txtIntLayoutConcentracionObjetivo =(TextInputLayout) view.findViewById(R.id.InputtxtConcentracionObjetivo) ;


        GestionBotones();


       btnSiguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //   view.requestFocus();

                validarPagina();


                if ( ! flag_PermitirContinuar ) {
                    Log.d("ERR", "ERROR");
                    if (validacionListener!=null) {
                        validacionListener.onFragmentElementoValidado(false);
                    }

                }else {
                    Log.d("ERR", "PERFECTO");
                    if (validacionListener!=null) {
                        validacionListener.onFragmentElementoValidado(true);
                    }

                }
            }

        });


        textViewConcentracionObjetivo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (textViewConcentracionObjetivo.getText().toString().trim().length() == 0) {

                        txtIntLayoutConcentracionObjetivo.setError(Mensaje);
                        flag_PermitirContinuar= false;

                    } else {
                        txtIntLayoutConcentracionObjetivo.setErrorEnabled(false);
                        flag_PermitirContinuar= true;
                    }
                }
            }
        });







/*
        btnK20.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {



                btnK20.setBackground(drawableBtnSel);
                btnK20.setTextColor(Color.WHITE);

                btnP205.setBackground(drawableBtn);
                btnN.setBackground(drawableBtn);
                btnSO3.setBackground(drawableBtn);
                btnMGO.setBackground(drawableBtn);
                btnCAO.setBackground(drawableBtn);
            }
        });*/

        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {

        for (Button button : listButtons) {
            if (button==v) {
                button.setBackground(drawableBtnSel);
                button.setTextColor(Color.WHITE);
             BotonElementoSeleccionado = (String) button.getTag();
            Log.d("ClickBton",  (String) button.getTag());
                txtErrorElementos.setVisibility(View.INVISIBLE);
            }
            else {
                button.setBackground(drawableBtn);
                button.setTextColor(view.getResources().getColor(R.color.colorPrimaryDark));
            }
        }






    }

    public interface ValidacionListenerTabElemento {

        void onFragmentElementoValidado(Boolean validado);
    }

    public void setValidacionListenerElemento(ValidacionListenerTabElemento listener) {
        this.validacionListener=listener;
    }

    public boolean ValidarFragmentTabElemento () {
        Log.d("ERR", "PERFECTO");

        return validarPagina();
    }


    public boolean validarPagina(){


        if (BotonElementoSeleccionado =="" || BotonElementoSeleccionado ==null){
            txtErrorElementos.setVisibility(View.VISIBLE);
            Flag_ValidarBtonElementos =false;
        }else
        {
            txtErrorElementos.setVisibility(View.INVISIBLE);
            Flag_ValidarBtonElementos =true;
        }


        if (textViewConcentracionObjetivo.getText().toString().trim().length() == 0) {

            txtIntLayoutConcentracionObjetivo.setError(Mensaje);
            Flag_ValidarConcentracion= false;

        } else {
            txtIntLayoutConcentracionObjetivo.setErrorEnabled(false);
            Flag_ValidarConcentracion= true;
        }


if ( Flag_ValidarConcentracion &&  Flag_ValidarBtonElementos  ){
    flag_PermitirContinuar =true;

    return true;
}else {
    flag_PermitirContinuar =false;

    return false;
}


   }




    private void GestionBotones () {
        btnK20 = (Button) view.findViewById(R.id.btnK2O);
        btnP205 = (Button) view.findViewById(R.id.btnP2O5);
        btnN = (Button) view.findViewById(R.id.btnN);
        btnSO3 = (Button) view.findViewById(R.id.btnSO3);
        btnMGO = (Button) view.findViewById(R.id.btnMGO);
        btnCAO = (Button) view.findViewById(R.id.btnCAO);


        btnK20.setOnClickListener(this);
        btnP205.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnSO3.setOnClickListener(this);
        btnMGO.setOnClickListener(this);
        btnCAO.setOnClickListener(this);

        btnK20.setText(Html.fromHtml("K<sub><small>2</small></sub>O"));
        btnP205.setText(Html.fromHtml("P<sub><small>2</small></sub>O<sub><small>5</small></sub>"));
        btnN.setText(Html.fromHtml("N"));
        btnSO3.setText(Html.fromHtml("SO<sub><small>3</small></sub>"));
        btnMGO.setText(Html.fromHtml("M<sub><small>g</small></sub>O"));
        btnCAO.setText(Html.fromHtml("C<sub><small>a</small></sub>O"));





        listButtons.add(btnK20);
        listButtons.add(btnP205);
        listButtons.add(btnN);
        listButtons.add(btnSO3);
        listButtons.add(btnMGO);
        listButtons.add(btnCAO);



    }

    public static List<Button> listaBotonesElementos(){
        return listButtons;
    }


}

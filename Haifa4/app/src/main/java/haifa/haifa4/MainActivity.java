package haifa.haifa4;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import haifa.haifa4.FragmentsTabs.fTabElemento;
import haifa.haifa4.FragmentsTabs.fTabFertilizante;
import haifa.haifa4.FragmentsTabs.fTabSistema;

import static haifa.haifa4.MainActivity.viewPager;

public class MainActivity extends AppCompatActivity {


    final String Mensaje = "Este campo es obligatorio";
    static MyViewPager viewPager;
    static TabLayout tabLayout;


    EditText txtEmail;
    TextInputLayout InputEmail;
    AlertDialog dialog ;

    static Integer PaginaActual_Tab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (MyViewPager) findViewById(R.id.viewpager1);

        PaginaActual_Tab = 0;

        viewPager = (MyViewPager) findViewById(R.id.viewpager1);
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager()));


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setEnabled(false);





    }

    private void a(){
        BackgroundMail.newBuilder(this)
                .withUsername("thecrazyghostt@gmail.com")
                .withPassword("camazutra")
                .withMailto("uruguayok7@gmail.com")
                                .withSubject("this is the subject")
                .withBody("this is the body")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                         Log.e("dialog", "PERFECTO EMAIL");

                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                        Log.e("dialog", "A FALLADO EMAIL");

                    }
                })
                .send();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            crearDialogEmail();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public void crearDialogEmail() {



         AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_email, null);

        builder.setView(v);

        dialog =builder.create();
        Button btnEnviarEmail = (Button) v.findViewById(R.id.btn_EnviarEmail);
        Button btnCancelar = (Button) v.findViewById(R.id.btn_CancelarEmail);
        txtEmail = (EditText) v.findViewById(R.id.txtEmail);
        InputEmail = (TextInputLayout) v.findViewById(R.id.InputTxtEmail);



        btnEnviarEmail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear Cuenta...
                        Log.d("dialog", "Enviar");


                        if (0 == txtEmail.getText().toString().trim().length()) {

                            InputEmail.setError(Mensaje);


                        }  else {
                            InputEmail.setErrorEnabled(false);
                            a();

                            dialog.dismiss();

                        }



                    }
                }
        );

        btnCancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("dialog", "cancelar");
                        dialog.dismiss();
                    }
                }

        );




        dialog.show();

    }


}

class MiFragmentPagerAdapter extends FragmentPagerAdapter implements fTabSistema.ValidacionListenerTabSistema, fTabElemento.ValidacionListenerTabElemento, fTabFertilizante.ValidacionListenerTabFertilizante{
    final int PAGE_COUNT = 3;
    private String tabTitles[] =
            new String[] { "SISTEMA", "ELEMENTO", "FERTILIZANTE"};

    public MiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {

       Fragment f = null;

        switch(position) {
            case 0:

                fTabSistema   fTabSistema1 = new fTabSistema();
                fTabSistema1.setValidacionListenerSistema(this);
                return fTabSistema1;

            case 1:

                fTabElemento   fTabElemento1 = new fTabElemento();
                fTabElemento1.setValidacionListenerElemento(this);
                return  fTabElemento1;

            case 2:
                fTabFertilizante   fTabFertilizante1 = new fTabFertilizante();
                fTabFertilizante1.setValidacionListenerFertilizante(this);
                return  fTabFertilizante1;



        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }



    //Escucho el evento que generará el Fragment TabsSistema
    @Override
    public void onFragmentSistemaValidado(Boolean validado) {

        if (validado) {
            viewPager.setCurrentItemPersonalizado(viewPager.getCurrentItem() +1);
        }
    }

    @Override
    public void onFragmentElementoValidado(Boolean validado) {
        if (validado) {
            viewPager.setCurrentItemPersonalizado(viewPager.getCurrentItem() +1);
        }
    }


    @Override
    public void onFragmentFertilizanteValidado(Boolean validado) {
        if (validado) {
          //  MainActivity.viewPager.setCurrentItem(MainActivity.viewPager.getCurrentItem() +1);
        }
    }
}



 class MyViewPager extends ViewPager
{
    private boolean cancelarEvento = true  ;

    public MyViewPager(Context context)
    {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public void setCurrentItem(int item) {


        if (item < MainActivity.viewPager.getCurrentItem()) {

            //if (!cancelarEvento ){
            super.setCurrentItem(item);
            //}
        }



    }


    public void CancelarEventoPasarPagina (Boolean cancelarEvento){

        this.cancelarEvento = cancelarEvento;
}

    public void setCurrentItemPersonalizado(int item){
        super.setCurrentItem(item);
    }
}


































//    tabLayout.setTabMode(TabLayout.MODE_FIXED);

//    tabLayout.setCurrentTab(0);


//        tab.select();

/*tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {


            position = tab.getPosition();

            switch (position){

                case 0:
                    tabLayout.setScrollPosition(position,0f,true);
                    viewPager.setCurrentItemPersonalizado(position);
                    PaginaActual_Tab =position;

                case 1:
                    if( PaginaActual_Tab==0) {


                        //   if (MainActivity.viewPager.getCurrentItem() == 1) {

                        fTabSistema frag1 = (fTabSistema) viewPager.getAdapter().instantiateItem(viewPager, PaginaActual_Tab);

                        if (frag1.ValidarFragmentTabSistema()) {
                            tabLayout.setScrollPosition(position,0f,true);
                            viewPager.setCurrentItemPersonalizado(position);
                            PaginaActual_Tab = position;

                        }else{
                            tabLayout.setScrollPosition(PaginaActual_Tab,0f,true);
                            viewPager.setCurrentItemPersonalizado(PaginaActual_Tab);

                        }
                    } else {
                        tabLayout.setScrollPosition(position,0f,true);
                        viewPager.setCurrentItemPersonalizado(position);
                        PaginaActual_Tab = position;
                    }

                case 2:
                    if (PaginaActual_Tab == 1){

                        fTabElemento fTabElemento1 = (fTabElemento)  viewPager.getAdapter().instantiateItem(viewPager, PaginaActual_Tab);
                        if (fTabElemento1.ValidarFragmentTabElemento()) {
                            tabLayout.setScrollPosition(position,0f,true);
                            viewPager.setCurrentItemPersonalizado(position);
                            PaginaActual_Tab =position;

                        }else
                        {
                            tabLayout.setScrollPosition(PaginaActual_Tab,0f,true);
                            viewPager.setCurrentItemPersonalizado(PaginaActual_Tab);

                        }
                    } else{
                        tabLayout.setScrollPosition(position,0f,true);
                        viewPager.setCurrentItemPersonalizado(position);
                        PaginaActual_Tab =position;
                    }
            }






        }


                                       @Override
                                       public void onTabUnselected(TabLayout.Tab tab) {

                                       }

                                       @Override
                                       public void onTabReselected(TabLayout.Tab tab) {

                                       }
                                   });
*/

    /*    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                PaginaActual_Tab =  getSupportFragmentManager().getBackStackEntryCount();

          if (position ==1) {
            if( PaginaActual_Tab==0) {


           //   if (MainActivity.viewPager.getCurrentItem() == 1) {

                  fTabSistema frag1 = (fTabSistema) viewPager.getAdapter().instantiateItem(viewPager, PaginaActual_Tab);

                  if (frag1.ValidarFragmentTabSistema()) {

                     viewPager.setCurrentItemPersonalizado(position);
                 }
              }
          }

                if (position ==2){
                        if (viewPager.getCurrentItem() == 1){

                            fTabElemento fTabElemento1 = (fTabElemento)  viewPager.getAdapter().instantiateItem(viewPager, viewPager.getCurrentItem());
                            if (fTabElemento1.ValidarFragmentTabElemento()) {

                                viewPager.setCurrentItem(position);

                            }
                        }
                }


                if (position ==0){

                       if (viewPager.getCurrentItem() == 1){

                  fTabElemento fTabElemento1 = (fTabElemento)  viewPager.getAdapter().instantiateItem(viewPager, viewPager.getCurrentItem());
                  if (fTabElemento1.ValidarFragmentTabElemento()) {

                      viewPager.setCurrentItem(position);

                  }
              }

          }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

*/




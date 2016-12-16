package br.com.caelum.listaaluno;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by android6523 on 14/12/16.
 */

public class ProvasActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(isTablet()) {

            transaction.replace(R.id.provas_lista, new ListaProvaFragment());

            transaction.replace(R.id.provas_detalhe, new DetalhesProvaFragment());
        }else{
            transaction.replace(R.id.provas_view, new ListaProvaFragment());
        }
        transaction.commit();
    }
    private boolean isTablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }
    public void selecionaProva(Prova prova){

        FragmentManager manager = getSupportFragmentManager();
        if(isTablet()){
            DetalhesProvaFragment detalhes = (DetalhesProvaFragment) manager.findFragmentById(
                    R.id.provas_detalhe);
            detalhes.populaCampos(prova);

        }else{
            Bundle bundle = new Bundle();

            bundle.putSerializable("prova",prova);
            DetalhesProvaFragment detalhes= new DetalhesProvaFragment();
            detalhes.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view,detalhes);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}

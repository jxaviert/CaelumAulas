package br.com.caelum.listaaluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by android6523 on 15/12/16.
 */

public class DetalhesProvaFragment extends Fragment{
    private TextView materia;
    private TextView data;
    private ListView topicos;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data){
        Bundle b = getArguments();
        View view = inflater.inflate(R.layout.provas_detalhe_fragment,container,false);
        buscaComponentes(view);
        if(b!=null){
            Prova prova = (Prova)b.getSerializable("prova");

            populaCampos(prova);
        }


        return view;

    }

    public void populaCampos(Prova prova){
        if(prova != null){
            this.materia.setText(prova.getMateria());
            this.data.setText(prova.getData());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_1,prova.getTopicos());
            this.topicos.setAdapter(adapter);


        }

        //TODO Implementar o popula campos com setText; e find view by id
        //TODO PRECISA INICIALIZAR OS CAMPOS
    }

    public void buscaComponentes(View layout){
        this.materia    = (TextView)layout.findViewById(R.id.detalhe_prova_materia);
        this.data       = (TextView)layout.findViewById(R.id.detalhe_prova_data);
        this.topicos    = (ListView)layout.findViewById(R.id.detalhe_prova_topicos);

    }
}

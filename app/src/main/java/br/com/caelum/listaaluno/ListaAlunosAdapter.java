package br.com.caelum.listaaluno;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android6523 on 14/12/16.
 */

public class ListaAlunosAdapter extends BaseAdapter{

    private final List<Aluno> alunos;
    private final Activity activity;

    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos){
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent){
        View view = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        Aluno aluno = alunos.get(posicao);

        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());

        return view;
        }
    @Override
    public long getItemId(int posicao){
        return alunos.get(posicao).getId();
    }

    @Override
    public Object getItem(int posicao){
        return alunos.get(posicao);
    }

    public int getCount(){
        return alunos.size();
    }

   /* TextView nome = (TextView) view.findViewById(R.id.item_nome);
    nome.setText(aluno.getNome());*/

}

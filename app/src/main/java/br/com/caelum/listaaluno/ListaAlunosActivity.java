package br.com.caelum.listaaluno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String [] alunos = {"Chaves","Quico","Chiquinha"};

        final ListView lista = (ListView) findViewById(R.id.lista_alunos);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,

                        alunos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View view,int posicao, long id){

                Toast.makeText(ListaAlunosActivity.this, "Posição clicada: "+posicao, Toast.LENGTH_SHORT).show();

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {



                String aluno = lista.getItemAtPosition(posicao).toString();
                Toast.makeText(ListaAlunosActivity.this, "Aluno clicado: "+aluno, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }
}

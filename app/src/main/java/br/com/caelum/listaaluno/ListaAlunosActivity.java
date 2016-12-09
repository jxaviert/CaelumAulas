package br.com.caelum.listaaluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView lista;

    private void carregaLista(){
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(
        this,android.R.layout.simple_list_item_1,alunos);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String [] alunos = {"Chaves","Quico","Chiquinha"};

        lista = (ListView) findViewById(R.id.lista_alunos);

     //   ArrayAdapter<String> adapter =
       //         new ArrayAdapter<String>(this,
      //                  android.R.layout.simple_list_item_1,

      //                  alunos);
      //  lista.setAdapter(adapter);

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

        Button bt = (Button)findViewById(R.id.lista_alunos_floating_button);

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Toast.makeText(ListaAlunosActivity.this, "Clicou", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAlunosActivity.this,
                        FormularioActivity.class);
                startActivity(intent);

            }

        });

    }

    @Override
    public void onResume(){
       super.onResume();
        carregaLista();

    }
}

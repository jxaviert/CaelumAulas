package br.com.caelum.listaaluno;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final int REQUEST_LIGAR = 1;
    private ListView lista;
    private Aluno alunoSelecionado;

    private void carregaLista(){
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

    //    ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(
    //            this,android.R.layout.simple_list_item_1,alunos);
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this, alunos);

        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu_enviar_notas:
                AlunoDAO dao = new AlunoDAO(this);
                List<Aluno> alunos = dao.getLista();
                dao.close();

                String json = new AlunoConverter().toJson(alunos);
                Toast.makeText(this,json, Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_receber_provas:
                Intent prov = new Intent(this, ProvasActivity.class);
                startActivity(prov);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        final String [] alunos = {"Chaves","Quico","Chiquinha"};

        lista = (ListView) findViewById(R.id.lista_alunos);


        registerForContextMenu(lista);

        //   ArrayAdapter<String> adapter =
        //         new ArrayAdapter<String>(this,
        //                  android.R.layout.simple_list_item_1,

        //                  alunos);
        //  lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View view,int posicao, long id){

                Aluno aluno = (Aluno)lista.getItemAtPosition(posicao);

                Intent editar = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                editar.putExtra("aluno",aluno);

                startActivity(editar);



                // Toast.makeText(ListaAlunosActivity.this, "Posição clicada: "+posicao, Toast.LENGTH_SHORT).show();

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {



                String aluno = lista.getItemAtPosition(posicao).toString();
                Toast.makeText(ListaAlunosActivity.this, "Aluno clicado: "+aluno, Toast.LENGTH_SHORT).show();
                return false;
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
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        alunoSelecionado = (Aluno) lista.getAdapter().getItem(info.position);

        MenuItem deletar = menu.add("Excluir");

        MenuItem abrirMapa = menu.add("Abrir mapa");
        Intent mapa = new Intent(Intent.ACTION_VIEW);
        mapa.setData(Uri.parse("geo:0,0?z=14&q=" + Uri.encode(alunoSelecionado.getEndereco())));
        abrirMapa.setIntent(mapa);

        MenuItem menuSms = menu.add("SMS");
        Intent enviarSMS = new Intent(Intent.ACTION_VIEW);
        enviarSMS.setData(Uri.parse("sms: "+ alunoSelecionado.getTelefone()));
        enviarSMS.putExtra("sms_body","Olá mundo");
        menuSms.setIntent(enviarSMS);

        MenuItem menuSite = menu.add("Abrir site");
        Intent site = new Intent(Intent.ACTION_VIEW);
        site.setData(Uri.parse("http://"+ alunoSelecionado.getSite()));
        menuSite.setIntent(site);

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(alunoSelecionado);
                dao.close();
                carregaLista();

                return false;
            }
        });

        MenuItem menuLigar = menu.add("Ligar");
        menuLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String permissaoLigar = Manifest.permission.CALL_PHONE;
                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, permissaoLigar)
                        == PackageManager.PERMISSION_GRANTED) {
                    fazerLigacao();
                } else {
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{permissaoLigar}, REQUEST_LIGAR);
                }
                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissoes,int[]resultados) {
        if (requestCode == REQUEST_LIGAR) {
            if (resultados[0] == PackageManager.PERMISSION_GRANTED) {
                fazerLigacao();
            } else {
                Toast.makeText(this, "Não tem permissão", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @SuppressWarnings({"MissingPermission"})
    private void fazerLigacao(){
        Intent ligar = new Intent(Intent.ACTION_CALL);
        ligar.setData(Uri.parse("tel: "+alunoSelecionado.getTelefone()));

        startActivity(ligar);
    }


        /*Intent ligar = new Intent(Intent.ACTION_CALL);
        ligar.setData(Uri.parse("tel: "+ alunoSelecionado.getTelefone()));
        menuLigar.setIntent(ligar);*/






       /* menuLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Intent ligar = new Intent(Intent.ACTION_CALL);
                ligar.setData(Uri.parse("tel: "+ alunoSelecionado.getTelefone()));
                return false;
            }

        });*/


    @Override
    public void onResume(){
        super.onResume();
        carregaLista();

    }
}

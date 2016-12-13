package br.com.caelum.listaaluno;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;
    private String caminhoFoto;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_ok) {

            Aluno aluno = helper.pegaAlunoDoFormulario();

            AlunoDAO dao = new AlunoDAO(this);
            if(aluno.getId()==null){
                dao.inserir(aluno);
            }else{
                dao.altera(aluno);
            }

            dao.close();
            finish();
            //Toast.makeText(this,"Aluno: "+aluno.getNome(),Toast.LENGTH_LONG).show();
           // finish();
            //Toast.makeText(this,dao.ler(),Toast.LENGTH_LONG).show();

            dao.close();
        }
        return true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        helper = new FormularioHelper(this);

        Intent intent = getIntent();

        Aluno aluno = (Aluno)intent.getSerializableExtra("aluno");
        if(aluno != null) {

            helper.colocaAlunoNoFormulario(aluno);
        }

        helper.getBotaoFoto().setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                caminhoFoto = getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".jpg";
                Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uriFoto = Uri.fromFile(new File(caminhoFoto));
                tirarFoto.putExtra(MediaStore.EXTRA_OUTPUT,uriFoto);
                startActivityForResult(tirarFoto,1);
            }


        });

       /* Button Salvar = (Button) findViewById(R.id.botao_salvar);

        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/



    }
    @Override //Isto é um caller que será chamado quando a câmera terminar de tirar a foto
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        if(requestCode==1){
            if(resultCode==RESULT_OK){

                helper.carregaImagem(caminhoFoto);
            }else{
                caminhoFoto=null;
            }

        }

    }



}

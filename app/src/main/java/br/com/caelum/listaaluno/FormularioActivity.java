package br.com.caelum.listaaluno;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_ok) {

            Aluno aluno = helper.pegaAlunoDoFormulario();
            Toast.makeText(this,"Aluno: "+aluno.getNome(),Toast.LENGTH_LONG).show();
            finish();
        }
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        helper = new FormularioHelper(this);

       /* Button Salvar = (Button) findViewById(R.id.botao_salvar);

        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/



    }



}

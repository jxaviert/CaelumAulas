package br.com.caelum.listaaluno;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by android6523 on 14/12/16.
 */

public class EnviaAlunosTask extends AsyncTask <Object, Void,String>{
    private final Context contexto;
    private ProgressDialog pd;
    public EnviaAlunosTask(Context ctx){
        this.contexto = ctx;
    }




    public void onPreExecute(){

        pd = ProgressDialog.show(contexto,"Enviando alunos","Carregando...",true,false);

    }

    @Override
    protected String doInBackground(Object...args) {
        AlunoDAO dao = new AlunoDAO(contexto);
        List<Aluno> alunos = dao.getLista(); dao.close();
        String json = new AlunoConverter().toJson(alunos);

            return new WebClient().post(json);

        //return
    }
    public void onPostExecute(String resposta){
        pd.dismiss();
        Toast.makeText(contexto, resposta, Toast.LENGTH_SHORT).show();
    }

}

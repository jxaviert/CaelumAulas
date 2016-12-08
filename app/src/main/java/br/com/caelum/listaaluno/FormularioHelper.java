package br.com.caelum.listaaluno;

import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android6523 on 07/12/16.
 */

public class FormularioHelper {
    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site    = (EditText) activity.findViewById(R.id.site);
        nota = (RatingBar) activity.findViewById(R.id.nota);

        aluno = new Aluno();

    }
    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;

    }
}

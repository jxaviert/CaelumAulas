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

    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site    = (EditText) activity.findViewById(R.id.site);
        nota = (RatingBar) activity.findViewById(R.id.nota);

    }
}

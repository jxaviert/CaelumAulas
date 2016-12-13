package br.com.caelum.listaaluno;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView foto;
    private Button botaoFoto;



    private Aluno aluno;


    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site    = (EditText) activity.findViewById(R.id.site);
        nota = (RatingBar) activity.findViewById(R.id.nota);

        botaoFoto   = (Button) activity.findViewById(R.id.formulario_foto_button);
        foto        = (ImageView) activity.findViewById(R.id.foto);

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

    public void colocaAlunoNoFormulario(Aluno _aluno){
        aluno = _aluno;
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());
        if(aluno.getCaminhoFoto()!= null){
            carregaImagem(aluno.getCaminhoFoto());
        }
        //TODO COLOCAR AQUI O CÓDIGO
    }


    public Button getBotaoFoto(){
        //TODO COLOCAR O CÓDIGO DO BOTÃO

        return botaoFoto;

    }

    public void carregaImagem(String caminhoFoto){
        Bitmap bmFoto = BitmapFactory.decodeFile(caminhoFoto);
        Bitmap bmFotoReduzida = Bitmap.createScaledBitmap(bmFoto, bmFoto.getWidth(),300,true);

        foto.setImageBitmap(bmFotoReduzida);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        foto.setTag(caminhoFoto);

    }
}

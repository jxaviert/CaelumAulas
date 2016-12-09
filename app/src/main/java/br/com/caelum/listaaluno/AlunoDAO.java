package br.com.caelum.listaaluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android6523 on 08/12/16.
 */

public class AlunoDAO extends SQLiteOpenHelper {

        private static final String tabela = "Aluno";

    public AlunoDAO(Context contexto){
        super(contexto, "CadastroAluno",null,1);

    }

    public void onCreate(SQLiteDatabase db){
        String sql="Create Table "+tabela
                  +"(id Integer Primary Key, "
                  +"nome text not null, "
                  +"telefone text, "
                  +"endereco text, "
                  +"site text, "
                  +"nota real);";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova){
        String sql="Drop table if exists"+tabela+";";
        db.execSQL(sql);

        onCreate(db);
    }
    public void inserir(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put("Nome",aluno.getNome());
        values.put("Endereco",aluno.getEndereco());
        values.put("Telefone",aluno.getTelefone());
        values.put("Site",aluno.getSite());
        values.put("Nota",aluno.getNota());

        getWritableDatabase().insert(tabela,null,values);

    }

    public void deletar(Aluno aluno){

        String[] args = {aluno.getId().toString()};

        getWritableDatabase().delete(tabela,"id=?",args);

    }

    public void altera(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put("nome",aluno.getNome());
        values.put("endereco",aluno.getEndereco());
        values.put("telefone",aluno.getTelefone());
        values.put("site",aluno.getSite());
        values.put("nota",aluno.getNota());


        getWritableDatabase().update(tabela,values,"id=?",new String[]{aluno.getId().toString()});



    }

    public List<Aluno> getLista(){
        List<Aluno> alunos = new ArrayList<Aluno>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * from "+tabela+";",null);

        while(c.moveToNext()){

            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            alunos.add(aluno);

        }
        c.close();
        return alunos;

    }

}

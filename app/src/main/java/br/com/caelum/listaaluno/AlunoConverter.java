package br.com.caelum.listaaluno;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by android6523 on 14/12/16.
 */

public class AlunoConverter {
    public String toJson(List<Aluno> alunos){
        Gson gson = new Gson();

        String json = gson.toJson(alunos);

        json = "{list:[{aluno:"+json+"}]}";

        return json;


        //[ NO Json é uma lista
        //{ no Json é um objeto

    }
}

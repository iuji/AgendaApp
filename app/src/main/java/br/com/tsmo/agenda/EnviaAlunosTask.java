package br.com.tsmo.agenda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.tsmo.agenda.converter.AlunoConverter;
import br.com.tsmo.agenda.dao.AlunoDAO;
import br.com.tsmo.agenda.modelo.Aluno;

/**
 * Created by iuji_ on 26/02/2018.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {
    private Context context;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... objects) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
    }
}

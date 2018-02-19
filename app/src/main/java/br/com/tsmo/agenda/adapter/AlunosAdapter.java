package br.com.tsmo.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.tsmo.agenda.ListaAlunosActivity;
import br.com.tsmo.agenda.R;
import br.com.tsmo.agenda.modelo.Aluno;

/**
 * Created by iuji_ on 06/02/2018.
 */

public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Aluno aluno = alunos.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = view;
        if(item == null){
            item = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        TextView campoNome = (TextView) item.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = (TextView) item.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        ImageView campoFoto = (ImageView) item.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null){
            Bitmap bitmapFoto = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapFotoReduzido = Bitmap.createScaledBitmap(bitmapFoto, 100, 100, true);
            campoFoto.setImageBitmap(bitmapFotoReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return item;
    }
}

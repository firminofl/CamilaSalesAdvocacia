package camilasales.camilasalesadvocacia.control.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

public class PessoaFisicaAdapter extends ArrayAdapter<PessoaFisica> {

    private ArrayList<PessoaFisica> pessoaFísica;
    private Context context;

    public PessoaFisicaAdapter(Context c,ArrayList<PessoaFisica> objectsPF) {
        super(c,0 , objectsPF);
        this.pessoaFísica = objectsPF;
        this.context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if(pessoaFísica != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_fisica, parent, true);
        }
        return view;
    }
}

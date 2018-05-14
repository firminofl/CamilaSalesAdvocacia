package camilasales.camilasalesadvocacia.control.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

public class PessoaFisicaAdapter extends ArrayAdapter<PessoaFisica> {

    private ArrayList<PessoaFisica> pessoaFísica;
    private Context context;

    public PessoaFisicaAdapter(Context c, ArrayList<PessoaFisica> objectsPF) {
        super(c, 0, objectsPF);
        this.pessoaFísica = objectsPF;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (pessoaFísica != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            view = inflater.inflate(R.layout.lista_completa_pessoa_fisica, parent, false);

            instanciaESetaOsValores(view, position);
        }
        return view;
    }

    private void instanciaESetaOsValores(View view, int position) {

        TextView txtViewNome = (TextView) view.findViewById(R.id.txtViewNome);
        TextView txtViewCpf = (TextView) view.findViewById(R.id.txtViewCpf);
        TextView txtViewRg = (TextView) view.findViewById(R.id.txtViewRg);
        TextView txtViewCnh = (TextView) view.findViewById(R.id.txtViewCnh);
        TextView txtViewSexo = (TextView) view.findViewById(R.id.txtViewSexo);
        TextView txtViewDataNasc = (TextView) view.findViewById(R.id.txtViewDataNasc);
        TextView txtViewTelefone = (TextView) view.findViewById(R.id.txtViewTelefone);
        TextView txtViewEndereco = (TextView) view.findViewById(R.id.txtViewEndereco);
        TextView txtViewNumero = (TextView) view.findViewById(R.id.txtViewNumero);
        TextView txtViewCidade = (TextView) view.findViewById(R.id.txtViewCidade);
        TextView txtViewEstado = (TextView) view.findViewById(R.id.txtViewEstado);
        TextView txtViewBairro = (TextView) view.findViewById(R.id.txtViewBairro);
        TextView txtViewCep = (TextView) view.findViewById(R.id.txtViewCep);
        TextView txtViewEmail = (TextView) view.findViewById(R.id.txtViewEmail);
        TextView txtViewProfissao = (TextView) view.findViewById(R.id.txtViewProfissao);

        PessoaFisica pessoaFisica2 = pessoaFísica.get(position);

        txtViewNome.setText(pessoaFisica2.getNome());//Nome da pessoa fisica
        txtViewCpf.setText(pessoaFisica2.getCpf());//Cpf da pessoa fisica
        txtViewRg.setText(pessoaFisica2.getRg());//Rg da pessoa fisica
        txtViewCnh.setText(pessoaFisica2.getRegistro_cnh());//Cnh da pessoa fisica
        txtViewSexo.setText(pessoaFisica2.getSexo());//Sexo da pessoa fisica
        txtViewDataNasc.setText(pessoaFisica2.getData_nasc());//Data Nascimento da pessoa fisica
        txtViewTelefone.setText(pessoaFisica2.getTelefone());//Telefone da pessoa fisica
        txtViewEndereco.setText(pessoaFisica2.getEndereco());//Endereco da pessoa fisica
        txtViewNumero.setText(pessoaFisica2.getNumero());//Numero da pessoa fisica
        txtViewCidade.setText(pessoaFisica2.getCidade());//Cidade da pessoa fisica
        txtViewEstado.setText(pessoaFisica2.getEstado());//Estado da pessoa fisica
        txtViewBairro.setText(pessoaFisica2.getBairro());//Bairro da pessoa fisica
        txtViewCep.setText(pessoaFisica2.getCep());//CEP da pessoa fisica
        txtViewEmail.setText(pessoaFisica2.getEmail());//Email da pessoa fisica
        txtViewProfissao.setText(pessoaFisica2.getProfissao());//Profissao da pessoa fisica
    }
}

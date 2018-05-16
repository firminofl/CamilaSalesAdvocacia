package camilasales.camilasalesadvocacia.control.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewResumida = null;

        if (pessoaFísica != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            viewResumida = inflater.inflate(R.layout.lista_resumida_pessoa_fisica, parent, false);

            instanciaESetaOsValoresResumidos(viewResumida, position);
        }
        return viewResumida;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void instanciaESetaOsValoresResumidos(View viewRS, int position) {
        ImageView ivGeneroSexual = (ImageView) viewRS.findViewById(R.id.ivListaResumida);
        TextView txtViewNome = (TextView) viewRS.findViewById(R.id.txtViewNomeLR);
        TextView txtViewCpf = (TextView) viewRS.findViewById(R.id.txtViewCpfLR);
        TextView txtViewDataNasc = (TextView) viewRS.findViewById(R.id.txtViewDataNascLR);

        PessoaFisica pessoaFisica2 = pessoaFísica.get(position);

        if (pessoaFisica2.getSexo().equals("Masculino")) {
            ivGeneroSexual.setImageResource(R.drawable.masculino_50px);
        } else {
            ivGeneroSexual.setImageResource(R.drawable.feminino_50px);
        }
        txtViewNome.setText(pessoaFisica2.getNome());//Nome da pessoa fisica
        txtViewCpf.setText(pessoaFisica2.getCpf());//Cpf da pessoa fisica
        txtViewDataNasc.setText(pessoaFisica2.getData_nasc());//Data Nascimento da pessoa fisica
    }

    private void instanciaESetaOsValoresCompletos(View view, int position) {

        TextView txtViewNome = (TextView) view.findViewById(R.id.txtViewNomeLC);
        TextView txtViewCpf = (TextView) view.findViewById(R.id.txtViewCpfLC);
        TextView txtViewRg = (TextView) view.findViewById(R.id.txtViewRgLC);
        TextView txtViewCnh = (TextView) view.findViewById(R.id.txtViewCnhLC);
        TextView txtViewSexo = (TextView) view.findViewById(R.id.txtViewSexoLC);
        TextView txtViewDataNasc = (TextView) view.findViewById(R.id.txtViewDataNascLC);
        TextView txtViewTelefone = (TextView) view.findViewById(R.id.txtViewTelefoneLC);
        TextView txtViewEndereco = (TextView) view.findViewById(R.id.txtViewEnderecoLC);
        TextView txtViewNumero = (TextView) view.findViewById(R.id.txtViewNumeroLC);
        TextView txtViewCidade = (TextView) view.findViewById(R.id.txtViewCidadeLC);
        TextView txtViewEstado = (TextView) view.findViewById(R.id.txtViewEstadoLC);
        TextView txtViewBairro = (TextView) view.findViewById(R.id.txtViewBairroLC);
        TextView txtViewCep = (TextView) view.findViewById(R.id.txtViewCepLC);
        TextView txtViewEmail = (TextView) view.findViewById(R.id.txtViewEmailLC);
        TextView txtViewProfissao = (TextView) view.findViewById(R.id.txtViewProfissaoLC);

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

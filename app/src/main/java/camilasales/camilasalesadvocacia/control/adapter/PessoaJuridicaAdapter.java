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
import camilasales.camilasalesadvocacia.model.PessoaJuridica;

public class PessoaJuridicaAdapter extends ArrayAdapter<PessoaJuridica> {

    private ArrayList<PessoaJuridica> pessoaJuridica;
    private Context context;

    public PessoaJuridicaAdapter(Context c, ArrayList<PessoaJuridica> objectsPJ) {
        super(c, 0, objectsPJ);
        this.pessoaJuridica = objectsPJ;
        this.context = c;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewResumida = null;

        if (pessoaJuridica != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            viewResumida = inflater.inflate(R.layout.lista_resumida_pessoa_juridica, parent, false);

            instanciaESetaOsValoresResumidosPJ(viewResumida, position);
        }
        return viewResumida;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void instanciaESetaOsValoresResumidosPJ(View viewRS, int position) {
        //ImageView ivEmpresa = (ImageView) viewRS.findViewById(R.id.ivListaResumidaPJ);
        TextView txtViewNome = (TextView) viewRS.findViewById(R.id.txtViewNomeLRPJ);
        TextView txtViewCnpj = (TextView) viewRS.findViewById(R.id.txtViewCpfLRPJ);
        TextView txtViewTelefone = (TextView) viewRS.findViewById(R.id.txtViewTelefoneLRPJ);

        PessoaJuridica pessoaJuridica2 = pessoaJuridica.get(position);

        txtViewNome.setText(pessoaJuridica2.getNome_razao_social());//Nome da pessoa juridica
        txtViewCnpj.setText(pessoaJuridica2.getCnpj());//CNPJ da pessoa fisica
        txtViewTelefone.setText(pessoaJuridica2.getTelefone());//Telefone da pessoa Juridica
    }

}

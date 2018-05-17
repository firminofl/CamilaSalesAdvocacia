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
import camilasales.camilasalesadvocacia.model.PessoaFisica;

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
        TextView txtViewTelefone = (TextView) viewRS.findViewById(R.id.txtViewTelefoneLR);

        PessoaFisica pessoaFisica2 = pessoaFísica.get(position);

        if (pessoaFisica2.getSexo().equals("Masculino")) {
            ivGeneroSexual.setImageResource(R.drawable.masculino_50px);
        } else {
            ivGeneroSexual.setImageResource(R.drawable.feminino_50px);
        }
        txtViewNome.setText(pessoaFisica2.getNome());//Nome da pessoa fisica
        txtViewCpf.setText(pessoaFisica2.getCpf());//Cpf da pessoa fisica
        txtViewTelefone.setText(pessoaFisica2.getTelefone());//Data Nascimento da pessoa fisica
    }
}

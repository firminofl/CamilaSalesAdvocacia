package camilasales.camilasalesadvocacia.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarJuridicaFragment extends Fragment {


    private Context context;
    private View view;
    private EditText edtNomePJ;
    private EditText edtCnpjPJ;
    private EditText edtEmailPJ;
    private EditText edtEnderecoPJ;
    private EditText edtNumeroPJ;
    private EditText edtCidadePJ;
    private Spinner spEstadoPJ;
    private EditText edtBairroPJ;
    private EditText edtCepPJ;

    public CadastroEditarJuridicaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_juridica, container, false);
        context = view.getContext();

        return view;
    }

    //Voltar tela inicial, mas sem ser Override
    public void onBackPressed() {
        startActivity(new Intent(context, PrincipalActivity.class));
        getActivity().finish();
        super.getActivity().onBackPressed();
    }

    private void pegaInformacoesPessoaFisica() {
        edtNomePJ = (EditText) view.findViewById(R.id.edtNomePessoaJuridica);//Nome pessoa juridica
        edtCnpjPJ = (EditText) view.findViewById(R.id.edtCPNJPessoaJuridica);//CNPJ pessoa juridica
        edtEmailPJ = (EditText) view.findViewById(R.id.edtEmailPessoaJuridica);//Email pessoa juridica
        edtEnderecoPJ = (EditText) view.findViewById(R.id.edtEnderecoPessoaJuridica);//Endereco pessoa juridica
        edtNumeroPJ = (EditText) view.findViewById(R.id.edtNumeroPessoaJuridica);//Numero pessoa juridica
        edtCidadePJ = (EditText) view.findViewById(R.id.edtCidadePessoaJuridica);//Cidade pessoa juridica
        spEstadoPJ = (Spinner) view.findViewById(R.id.spEstadoPessoaJuridica);//Estado pessoa juridica
        edtBairroPJ = (EditText) view.findViewById(R.id.edtBairroPessoaJuridica);//Bairro pessoa juridica
        edtCepPJ = (EditText) view.findViewById(R.id.edtCEPPessoaJuridica);//CEP pessoa juridica
    }
}

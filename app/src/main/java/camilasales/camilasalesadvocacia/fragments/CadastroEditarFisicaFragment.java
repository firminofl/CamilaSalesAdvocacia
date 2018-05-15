package camilasales.camilasalesadvocacia.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.CadastroEditarActivity;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;
import static camilasales.camilasalesadvocacia.control.activity.CadastroEditarActivity.bundle;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarFisicaFragment extends Fragment {


    public CadastroEditarFisicaFragment() {
        // Required empty public constructor
    }

    private Context context;
    private  View view;

    private  EditText edtNomePF;
    private  EditText edtCpfPF;
    private  EditText edtRgPF;
    private  EditText edtCnhPF;
    private  RadioButton rbSexoFemininoPF;
    private  RadioButton rbSexoMasculinoPF;
    private  EditText edtDataNascPF;
    private  EditText edtTelefonePF;
    private  EditText edtEnderecoPF;
    private  EditText edtNumeroPF;
    private  EditText edtCidadePF;
    private  Spinner spEstadoPF;
    private  EditText edtBairroPF;
    private  EditText edtCepPF;
    private  EditText edtEmailPF;
    private  EditText edtProfissaoPF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_fisica, container, false);
        context = view.getContext();
        return view;
    }


    private void pegaInformacoesPessoaFisica() {
        edtNomePF = (EditText) view.findViewById(R.id.edtNomePessoaFisica);//Nome pessoa fisica
        edtCpfPF = (EditText) view.findViewById(R.id.edtCpfPessoaFisica);//CPF pessoa fisica
        edtRgPF = (EditText) view.findViewById(R.id.edtRgPessoaFisica);//RG pessoa fisica
        edtCnhPF = (EditText) view.findViewById(R.id.edtCnhPessoaFisica);//CNH pessoa fisica
        rbSexoFemininoPF = (RadioButton) view.findViewById(R.id.rbFemininoPessoaFisica);//Sexo Feminino pessoa fisica
        rbSexoMasculinoPF = (RadioButton) view.findViewById(R.id.rbMasculinoPessoaFisica);//Sexo Feminino pessoa fisica
        edtDataNascPF = (EditText) view.findViewById(R.id.edtDataNascPessoaFisica);//Data Nascimento pessoa fisica
        edtTelefonePF = (EditText) view.findViewById(R.id.edtTelefonePessoaFisica);//Telefone pessoa fisica
        edtEnderecoPF = (EditText) view.findViewById(R.id.edtEnderecoPessoaFisica);//Endereco pessoa fisica
        edtNumeroPF = (EditText) view.findViewById(R.id.edtNumeroPessoaFisica);//Numero pessoa fisica
        edtCidadePF = (EditText) view.findViewById(R.id.edtCidadePessoaFisica);//Cidade pessoa fisica
        spEstadoPF = (Spinner) view.findViewById(R.id.spEstadosPessoaFisica);//Estado pessoa fisica
        edtBairroPF = (EditText) view.findViewById(R.id.edtBairroPessoaFisica);//Bairro pessoa fisica
        edtCepPF = (EditText) view.findViewById(R.id.edtCepPessoaFisica);//CEP pessoa fisica
        edtEmailPF = (EditText) view.findViewById(R.id.edtEmailPessoaFisica);//Email pessoa fisica
        edtProfissaoPF = (EditText) view.findViewById(R.id.edtProfissaoPessoaFisica);//Profissao pessoa fisica
    }

    public void atualizaCamposPessoaFisica(PessoaFisica objPFlista){
        pegaInformacoesPessoaFisica();
        edtNomePF.setText(objPFlista.getNome());//Nome pessoa fisica
        edtCpfPF.setText(objPFlista.getCpf());//CPF pessoa fisica
        edtRgPF.setText(objPFlista.getRg());//RG pessoa fisica
        edtCnhPF.setText(objPFlista.getRegistro_cnh());//CNH pessoa fisica
        if(objPFlista.getSexo().equals("Feminino")){
            rbSexoFemininoPF.setChecked(true);//Sexo Feminino pessoa fisica
        }else{
            rbSexoMasculinoPF.setChecked(true);//Sexo Feminino pessoa fisica
        }
        edtDataNascPF.setText(objPFlista.getData_nasc());//Data Nascimento pessoa fisica
        edtTelefonePF.setText(objPFlista.getTelefone());//Telefone pessoa fisica
        edtEnderecoPF.setText(objPFlista.getEndereco());//Endereco pessoa fisica
        edtNumeroPF.setText(objPFlista.getNumero());//Numero pessoa fisica
        edtCidadePF.setText(objPFlista.getCidade());//Cidade pessoa fisica
        spEstadoPF.setSelection(0);//Estado pessoa fisica
        edtBairroPF.setText(objPFlista.getBairro());//Bairro pessoa fisica
        edtCepPF.setText(objPFlista.getCep());//CEP pessoa fisica
        edtEmailPF.setText(objPFlista.getEmail());//Email pessoa fisica
        edtProfissaoPF.setText(objPFlista.getProfissao());//Profissao pessoa fisica
    }
}

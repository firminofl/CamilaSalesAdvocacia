package camilasales.camilasalesadvocacia.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;
import camilasales.camilasalesadvocacia.model.PessoaJuridica;

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
    private EditText edtTelefonePJ;

    public CadastroEditarJuridicaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_juridica, container, false);
        context = view.getContext();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.botao_salvar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Botão adicional na ToolBar

        switch (item.getItemId()) {
            case android.R.id.home: //ID do botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                return true;

            case R.id.menu_botao_salvar:
                cadastraPessoaJuridica();
                /*if (telaEditarCadastra == 1) {
                    cadastrarPessoaFisicaJuridica();
                } else {
                    editarPessoaFisica();
                }*/
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Voltar tela inicial, mas sem ser Override
    public void onBackPressed() {
        startActivity(new Intent(context, PrincipalActivity.class));
        getActivity().finish();
        super.getActivity().onBackPressed();
    }

    private void cadastraPessoaJuridica() {
        pegaInformacoesPessoaFisica();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        if (!edtNomePJ.getText().toString().isEmpty() &&
                !edtCnpjPJ.getText().toString().isEmpty() &&
                !edtTelefonePJ.getText().toString().isEmpty()) {

            pessoaJuridica.setUid(UUID.randomUUID().toString());
            informacaosComumCadastraEdita(pessoaJuridica);

            salvarPessoaJuridica(pessoaJuridica);
            onBackPressed();
        } else {
            Toast.makeText(context, "Campos obrigatórios em falta!", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        try {
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase().child("PessoaJuridica");
            firebase.child(pessoaJuridica.getUid()).setValue(pessoaJuridica);
            Toast.makeText(context, "Pessoa jurídica adicionada com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void informacaosComumCadastraEdita(PessoaJuridica pessoaJuridica) {
        pessoaJuridica.setNome_razao_social(edtNomePJ.getText().toString());
        pessoaJuridica.setCnpj(edtCnpjPJ.getText().toString());
        pessoaJuridica.setTelefone(edtTelefonePJ.getText().toString());

        //Atributos que não dependem de obrigatoriedade//

        //EMAIL
        if (edtEmailPJ.getText().toString().isEmpty()) {
            pessoaJuridica.setEmail(null);//Email pessoa fisica
        } else {
            pessoaJuridica.setEmail(edtEmailPJ.getText().toString());//Email pessoa fisica
        }

        //ENDERECO
        if (edtEnderecoPJ.getText().toString().isEmpty()) {
            pessoaJuridica.setEndereco(null);//Endereco pessoa juridica
        } else {
            pessoaJuridica.setEndereco(edtEnderecoPJ.getText().toString());//Endereco pessoa juridica
        }

        //NUMERO
        if (edtNumeroPJ.getText().toString().isEmpty()) {
            pessoaJuridica.setNumero(null);//Numero pessoa juridica
        } else {
            pessoaJuridica.setNumero(edtNumeroPJ.getText().toString());//Numero pessoa juridica
        }

        //CIDADE
        if (edtCidadePJ.getText().toString().isEmpty()) {
            pessoaJuridica.setCidade(null);//Cidade pessoa juridica
        } else {
            pessoaJuridica.setCidade(edtCidadePJ.getText().toString());//Cidade pessoa juridica
        }

        pessoaJuridica.setEstado(spEstadoPJ.getSelectedItem().toString());//Estado pessoa juridica

        //BAIRRO
        if (edtBairroPJ.getText().toString().isEmpty()) {
            pessoaJuridica.setBairro(null);//Bairro pessoa juridica
        } else {
            pessoaJuridica.setBairro(edtBairroPJ.getText().toString());//Bairro pessoa juridica
        }

        //CEP
        if (edtCepPJ.getText().toString().isEmpty()) {
            pessoaJuridica.setCep(null);//CEP pessoa juridica
        } else {
            pessoaJuridica.setCep(edtCepPJ.getText().toString());//CEP pessoa juridica
        }
    }

    private void pegaInformacoesPessoaFisica() {
        edtNomePJ = (EditText) view.findViewById(R.id.edtNomePessoaJuridica);//Nome pessoa juridica
        edtCnpjPJ = (EditText) view.findViewById(R.id.edtCPNJPessoaJuridica);//CNPJ pessoa juridica
        edtTelefonePJ = (EditText) view.findViewById(R.id.edtTelefonePessoaJuridica);//Telefone pessoa juridica
        edtEmailPJ = (EditText) view.findViewById(R.id.edtEmailPessoaJuridica);//Email pessoa juridica
        edtEnderecoPJ = (EditText) view.findViewById(R.id.edtEnderecoPessoaJuridica);//Endereco pessoa juridica
        edtNumeroPJ = (EditText) view.findViewById(R.id.edtNumeroPessoaJuridica);//Numero pessoa juridica
        edtCidadePJ = (EditText) view.findViewById(R.id.edtCidadePessoaJuridica);//Cidade pessoa juridica
        spEstadoPJ = (Spinner) view.findViewById(R.id.spEstadoPessoaJuridica);//Estado pessoa juridica
        edtBairroPJ = (EditText) view.findViewById(R.id.edtBairroPessoaJuridica);//Bairro pessoa juridica
        edtCepPJ = (EditText) view.findViewById(R.id.edtCEPPessoaJuridica);//CEP pessoa juridica
    }
}

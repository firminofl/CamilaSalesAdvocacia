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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;
import camilasales.camilasalesadvocacia.model.PessoaFisica;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarFisicaFragment extends Fragment {


    public CadastroEditarFisicaFragment() {
        // Required empty public constructor
    }

    private Context context;
    private View view;

    private EditText edtNomePF;
    private EditText edtCpfPF;
    private EditText edtRgPF;
    private EditText edtCnhPF;
    private RadioButton rbSexoFemininoPF;
    private RadioButton rbSexoMasculinoPF;
    private EditText edtDataNascPF;
    private EditText edtTelefonePF;
    private EditText edtEnderecoPF;
    private EditText edtNumeroPF;
    private EditText edtCidadePF;
    private Spinner spEstadoPF;
    private EditText edtBairroPF;
    private EditText edtCepPF;
    private EditText edtEmailPF;
    private EditText edtProfissaoPF;
    private int telaEditarCadastra;
    private PessoaFisica pessoaFisicaEditar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_fisica, container, false);
        context = view.getContext();

        Bundle args = getArguments();
        telaEditarCadastra = args.getInt("abrirEdicaoCadastro");

        pessoaFisicaEditar = (PessoaFisica) args.getSerializable("editaPF");

        if (telaEditarCadastra == 2) {
            atualizaCamposPessoaFisica(pessoaFisicaEditar);
        }
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
            case android.R.id.home: //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                return true;

            case R.id.menu_botao_salvar:
                if (telaEditarCadastra == 1) {
                    cadastrarPessoaFisica();
                } else {
                    editarPessoaFisica();
                }
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

    //CADASTRAR PESSOA FISICA
    private void cadastrarPessoaFisica() {
        pegaInformacoesPessoaFisica();
        PessoaFisica pessoaFisica = new PessoaFisica();

        if (!edtNomePF.getText().toString().isEmpty() &&
                !edtCpfPF.getText().toString().isEmpty() &&
                !edtRgPF.getText().toString().isEmpty() &&
                !edtTelefonePF.getText().toString().isEmpty() &&
                (!rbSexoFemininoPF.isChecked() || !rbSexoMasculinoPF.isChecked()) &&
                !spEstadoPF.getSelectedItem().equals("Escolha um estado")) {

            pessoaFisica.setUid(UUID.randomUUID().toString());
            infoComumEntreCadastraEditar(pessoaFisica);

            salvarPessoaFisica(pessoaFisica);
            onBackPressed();

        } else {
            Toast.makeText(context, "Campos obrigatórios em falta!", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarPessoaFisica(PessoaFisica pessoaFisica) {
        try {
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase().child("PessoaFisica");
            firebase.child(pessoaFisica.getUid()).setValue(pessoaFisica);
            Toast.makeText(context, "Pessoa física adicionada com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EDITAR PESSOA FISICA
    public void atualizaCamposPessoaFisica(PessoaFisica objPFlista) {
        pegaInformacoesPessoaFisica();
        ArrayAdapter adapterEstados;

        edtNomePF.setText(objPFlista.getNome());//Nome pessoa fisica
        edtCpfPF.setText(objPFlista.getCpf());//CPF pessoa fisica
        edtRgPF.setText(objPFlista.getRg());//RG pessoa fisica
        edtCnhPF.setText(objPFlista.getRegistro_cnh());//CNH pessoa fisica
        if (objPFlista.getSexo().equals("Feminino")) {
            rbSexoFemininoPF.setChecked(true);//Sexo Feminino pessoa fisica
        } else {
            rbSexoMasculinoPF.setChecked(true);//Sexo Feminino pessoa fisica
        }
        edtDataNascPF.setText(objPFlista.getData_nasc());//Data Nascimento pessoa fisica
        edtTelefonePF.setText(objPFlista.getTelefone());//Telefone pessoa fisica
        edtEnderecoPF.setText(objPFlista.getEndereco());//Endereco pessoa fisica
        edtNumeroPF.setText(objPFlista.getNumero());//Numero pessoa fisica
        edtCidadePF.setText(objPFlista.getCidade());//Cidade pessoa fisica

        adapterEstados = (ArrayAdapter) spEstadoPF.getAdapter();
        for (int i = 0; i <= adapterEstados.getCount() - 1; i++) {
            if (adapterEstados.getItem(i).equals(objPFlista.getEstado())) {
                spEstadoPF.setSelection(i);//Estado pessoa fisica
                break;
            }
        }
        edtBairroPF.setText(objPFlista.getBairro());//Bairro pessoa fisica
        edtCepPF.setText(objPFlista.getCep());//CEP pessoa fisica
        edtEmailPF.setText(objPFlista.getEmail());//Email pessoa fisica
        edtProfissaoPF.setText(objPFlista.getProfissao());//Profissao pessoa fisica
    }

    private void editarPessoaFisica() {
        pegaInformacoesPessoaFisica();
        PessoaFisica pessoaFisica = new PessoaFisica();

        if (!edtNomePF.getText().toString().isEmpty() &&
                !edtCpfPF.getText().toString().isEmpty() &&
                !edtRgPF.getText().toString().isEmpty() &&
                !edtTelefonePF.getText().toString().isEmpty() &&
                (!rbSexoFemininoPF.isChecked() || !rbSexoMasculinoPF.isChecked()) &&
                !spEstadoPF.getSelectedItem().equals("Escolha um estado")) {

            pessoaFisica.setUid(pessoaFisicaEditar.getUid());
            infoComumEntreCadastraEditar(pessoaFisica);

            //ATUALIZA INFORMAÇÕES NO FIREBASE
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
            firebase.child("PessoaFisica").child(pessoaFisica.getUid()).setValue(pessoaFisica);
            onBackPressed();

        } else {
            Toast.makeText(context, "Campos obrigatórios em falta!", Toast.LENGTH_SHORT).show();
        }
    }

    //Informações em comum entre cadastrar e atualizar
    private void infoComumEntreCadastraEditar(PessoaFisica pessoaFisica) {
        pessoaFisica.setNome(edtNomePF.getText().toString());//Nome pessoa fisica
        pessoaFisica.setCpf(edtCpfPF.getText().toString());//CPF pessoa fisica
        pessoaFisica.setRg(edtRgPF.getText().toString());//RG pessoa fisica
        pessoaFisica.setTelefone(edtTelefonePF.getText().toString());//Telefone pessoa fisica

        if (rbSexoFemininoPF.isChecked()) {
            pessoaFisica.setSexo(rbSexoFemininoPF.getText().toString());
        } else {
            pessoaFisica.setSexo(rbSexoMasculinoPF.getText().toString());
        }

        //Atributos que não dependem de obrigatoriedade//

        //CNH
        if (edtCnhPF.getText().toString().isEmpty()) {
            pessoaFisica.setRegistro_cnh(null);//CNH pessoa fisica
        } else {
            pessoaFisica.setRegistro_cnh(edtCnhPF.getText().toString());//CNH pessoa fisica
        }

        //DATA NASCIMENTO
        if (edtDataNascPF.getText().toString().isEmpty()) {
            pessoaFisica.setData_nasc(null);//Data Nascimento pessoa fisica
        } else {
            pessoaFisica.setData_nasc(edtDataNascPF.getText().toString());//Data Nascimento pessoa fisica
        }

        //ENDERECO
        if (edtEnderecoPF.getText().toString().isEmpty()) {
            pessoaFisica.setEndereco(null);//Endereco pessoa fisica
        } else {
            pessoaFisica.setEndereco(edtEnderecoPF.getText().toString());//Endereco pessoa fisica
        }

        //NUMERO
        if (edtNumeroPF.getText().toString().isEmpty()) {
            pessoaFisica.setNumero(null);//Numero pessoa fisica
        } else {
            pessoaFisica.setNumero(edtNumeroPF.getText().toString());//Numero pessoa fisica
        }

        //CIDADE
        if (edtCidadePF.getText().toString().isEmpty()) {
            pessoaFisica.setCidade(null);//Cidade pessoa fisica
        } else {
            pessoaFisica.setCidade(edtCidadePF.getText().toString());//Cidade pessoa fisica
        }

        pessoaFisica.setEstado(spEstadoPF.getSelectedItem().toString());//Estado pessoa fisica

        //BAIRRO
        if (edtBairroPF.getText().toString().isEmpty()) {
            pessoaFisica.setBairro(null);//Bairro pessoa fisica
        } else {
            pessoaFisica.setBairro(edtBairroPF.getText().toString());//Bairro pessoa fisica
        }

        //CEP
        if (edtCepPF.getText().toString().isEmpty()) {
            pessoaFisica.setCep(null);//CEP pessoa fisica
        } else {
            pessoaFisica.setCep(edtCepPF.getText().toString());//CEP pessoa fisica
        }

        //EMAIL
        if (edtEmailPF.getText().toString().isEmpty()) {
            pessoaFisica.setEmail(null);//Email pessoa fisica
        } else {
            pessoaFisica.setEmail(edtEmailPF.getText().toString());//Email pessoa fisica
        }

        //PROFISSAO
        if (edtProfissaoPF.getText().toString().isEmpty()) {
            pessoaFisica.setProfissao(null);//Profissão pessoa fisica
        } else {
            pessoaFisica.setProfissao(edtProfissaoPF.getText().toString());//Profissão pessoa fisica
        }
    }
}

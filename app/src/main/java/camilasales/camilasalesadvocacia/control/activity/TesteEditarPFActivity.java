package camilasales.camilasalesadvocacia.control.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.PessoaFisica;

public class TesteEditarPFActivity extends AppCompatActivity {

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

    private PessoaFisica editaPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_editar_pf);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        pegaInformacoesPessoaFisica();

        Bundle receceEdicao = getIntent().getExtras();
        editaPF = (PessoaFisica) receceEdicao.getSerializable("editaPF");
        atualizaCamposPessoaFisica(editaPF);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_botao_salvar:
                salvarAlteracoes();
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.botao_salvar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
        super.onBackPressed();
    }

    private void salvarAlteracoes() {
        pegaInformacoesPessoaFisica();
        PessoaFisica pessoaFisica = new PessoaFisica();

        if (!edtNomePF.getText().toString().isEmpty() &&
                !edtCpfPF.getText().toString().isEmpty() &&
                !edtRgPF.getText().toString().isEmpty() &&
                !edtTelefonePF.getText().toString().isEmpty() &&
                (!rbSexoFemininoPF.isChecked() || !rbSexoMasculinoPF.isChecked()) &&
                !spEstadoPF.getSelectedItem().equals("Escolha um estado")) {

            pessoaFisica.setUid(editaPF.getUid());
            pessoaFisica.setNome(edtNomePF.getText().toString().trim());//Nome pessoa fisica
            pessoaFisica.setCpf(edtCpfPF.getText().toString().trim());//CPF pessoa fisica
            pessoaFisica.setRg(edtRgPF.getText().toString().trim());//RG pessoa fisica
            pessoaFisica.setTelefone(edtTelefonePF.getText().toString().trim());//Telefone pessoa fisica

            if (rbSexoFemininoPF.isChecked()) {
                pessoaFisica.setSexo(rbSexoFemininoPF.getText().toString().trim());
            } else {
                pessoaFisica.setSexo(rbSexoMasculinoPF.getText().toString().trim());
            }

            //Atributos que não dependem de obrigatoriedade//

            //CNH
            if (edtCnhPF.getText().toString().isEmpty()) {
                pessoaFisica.setRegistro_cnh(null);//CNH pessoa fisica
            } else {
                pessoaFisica.setRegistro_cnh(edtCnhPF.getText().toString().trim());//CNH pessoa fisica
            }

            //DATA NASCIMENTO
            if (edtDataNascPF.getText().toString().isEmpty()) {
                pessoaFisica.setData_nasc(null);//Data Nascimento pessoa fisica
            } else {
                pessoaFisica.setData_nasc(edtDataNascPF.getText().toString().trim());//Data Nascimento pessoa fisica
            }

            //ENDERECO
            if (edtEnderecoPF.getText().toString().isEmpty()) {
                pessoaFisica.setEndereco(null);//Endereco pessoa fisica
            } else {
                pessoaFisica.setEndereco(edtEnderecoPF.getText().toString().trim());//Endereco pessoa fisica
            }

            //NUMERO
            if (edtNumeroPF.getText().toString().isEmpty()) {
                pessoaFisica.setNumero(null);//Numero pessoa fisica
            } else {
                pessoaFisica.setNumero(edtNumeroPF.getText().toString().trim());//Numero pessoa fisica
            }

            //CIDADE
            if (edtCidadePF.getText().toString().isEmpty()) {
                pessoaFisica.setCidade(null);//Cidade pessoa fisica
            } else {
                pessoaFisica.setCidade(edtCidadePF.getText().toString().trim());//Cidade pessoa fisica
            }

            pessoaFisica.setEstado(spEstadoPF.getSelectedItem().toString().trim());//Estado pessoa fisica

            //BAIRRO
            if (edtBairroPF.getText().toString().isEmpty()) {
                pessoaFisica.setBairro(null);//Bairro pessoa fisica
            } else {
                pessoaFisica.setBairro(edtBairroPF.getText().toString().trim());//Bairro pessoa fisica
            }

            //CEP
            if (edtCepPF.getText().toString().isEmpty()) {
                pessoaFisica.setCep(null);//CEP pessoa fisica
            } else {
                pessoaFisica.setCep(edtCepPF.getText().toString().trim());//CEP pessoa fisica
            }

            //EMAIL
            if (edtEmailPF.getText().toString().isEmpty()) {
                pessoaFisica.setEmail(null);//Email pessoa fisica
            } else {
                pessoaFisica.setEmail(edtEmailPF.getText().toString().trim());//Email pessoa fisica
            }

            //PROFISSAO
            if (edtProfissaoPF.getText().toString().isEmpty()) {
                pessoaFisica.setProfissao(null);//Profissão pessoa fisica
            } else {
                pessoaFisica.setProfissao(edtProfissaoPF.getText().toString().trim());//Profissão pessoa fisica
            }

            //ATUALIZA INFORMAÇÕES NO FIREBASE
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
            firebase.child("PessoaFisica").child(pessoaFisica.getUid()).setValue(pessoaFisica);
            onBackPressed();

        } else {
            Toast.makeText(getApplicationContext(), "Campos obrigatórios em falta!", Toast.LENGTH_SHORT).show();
        }
    }

    private void pegaInformacoesPessoaFisica() {
        edtNomePF = (EditText) findViewById(R.id.edtNomePessoaFisica2);//Nome pessoa fisica
        edtCpfPF = (EditText) findViewById(R.id.edtCpfPessoaFisica2);//CPF pessoa fisica
        edtRgPF = (EditText) findViewById(R.id.edtRgPessoaFisica2);//RG pessoa fisica
        edtCnhPF = (EditText) findViewById(R.id.edtCnhPessoaFisica2);//CNH pessoa fisica
        rbSexoFemininoPF = (RadioButton) findViewById(R.id.rbFemininoPessoaFisica2);//Sexo Feminino pessoa fisica
        rbSexoMasculinoPF = (RadioButton) findViewById(R.id.rbMasculinoPessoaFisica2);//Sexo Feminino pessoa fisica
        edtDataNascPF = (EditText) findViewById(R.id.edtDataNascPessoaFisica2);//Data Nascimento pessoa fisica
        edtTelefonePF = (EditText) findViewById(R.id.edtTelefonePessoaFisica2);//Telefone pessoa fisica
        edtEnderecoPF = (EditText) findViewById(R.id.edtEnderecoPessoaFisica2);//Endereco pessoa fisica
        edtNumeroPF = (EditText) findViewById(R.id.edtNumeroPessoaFisica2);//Numero pessoa fisica
        edtCidadePF = (EditText) findViewById(R.id.edtCidadePessoaFisica2);//Cidade pessoa fisica
        spEstadoPF = (Spinner) findViewById(R.id.spEstadosPessoaFisica2);//Estado pessoa fisica
        edtBairroPF = (EditText) findViewById(R.id.edtBairroPessoaFisica2);//Bairro pessoa fisica
        edtCepPF = (EditText) findViewById(R.id.edtCepPessoaFisica2);//CEP pessoa fisica
        edtEmailPF = (EditText) findViewById(R.id.edtEmailPessoaFisica2);//Email pessoa fisica
        edtProfissaoPF = (EditText) findViewById(R.id.edtProfissaoPessoaFisica2);//Profissao pessoa fisica
    }

    public void atualizaCamposPessoaFisica(PessoaFisica objPFlista) {
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
        spEstadoPF.setSelection(0);//Estado pessoa fisica
        edtBairroPF.setText(objPFlista.getBairro());//Bairro pessoa fisica
        edtCepPF.setText(objPFlista.getCep());//CEP pessoa fisica
        edtEmailPF.setText(objPFlista.getEmail());//Email pessoa fisica
        edtProfissaoPF.setText(objPFlista.getProfissao());//Profissao pessoa fisica
    }
}

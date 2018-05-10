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
import camilasales.camilasalesadvocacia.fragments.CadastroEditarAudienciaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarFisicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarJuridicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarProcessoFragment;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

public class CadastroEditarActivity extends AppCompatActivity {

    private Bundle bundle;
    private int telaAbrir;
    private int estadoDeTela;

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
    private PessoaFisica pessoaFisica;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_editar);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        bundle = getIntent().getExtras();
        telaAbrir = bundle.getInt("TelaCadastroOpcoes");

        switch (telaAbrir) {
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarFisicaFragment())
                        .commit();
                estadoDeTela = 1;
                break;

            case 2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarJuridicaFragment())
                        .commit();
                estadoDeTela = 2;
                break;
            case 3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarProcessoFragment())
                        .commit();
                estadoDeTela = 3;
                break;
            case 4:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarAudienciaFragment())
                        .commit();
                estadoDeTela = 4;
                break;


            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.botao_salvar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Botão adicional na ToolBar

        switch (item.getItemId()) {
            case android.R.id.home: //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                break;

            case R.id.menu_botao_salvar:
                if (estadoDeTela == 1) {
                    cadastraPessoaFisica();
                } else if (estadoDeTela == 2) {
                    Toast.makeText(CadastroEditarActivity.this, "Tô na tela 2", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastroEditarActivity.this, PrincipalActivity.class));
        finish();
        super.onBackPressed();
    }

    //Cadastro de pessoa fisica
    private void cadastraPessoaFisica() {
        pessoaFisica = new PessoaFisica();
        pegaInformacoesPessoaFisica();

        if (!edtNomePF.getText().toString().isEmpty() &&
                !edtCpfPF.getText().toString().isEmpty() &&
                !edtRgPF.getText().toString().isEmpty() &&
                !edtTelefonePF.getText().toString().isEmpty() &&
                (!rbSexoFemininoPF.isChecked() || !rbSexoMasculinoPF.isChecked()) &&
                !spEstadoPF.getSelectedItem().equals("Escolha um estado")) {

            pessoaFisica.setNome(edtNomePF.getText().toString());//Nome pessoa fisica
            pessoaFisica.setCpf(Integer.valueOf(edtCpfPF.getText().toString()));//CPF pessoa fisica
            pessoaFisica.setRg(Integer.valueOf(edtRgPF.getText().toString()));//RG pessoa fisica
            pessoaFisica.setTelefone(Integer.parseInt(edtTelefonePF.getText().toString()));//Telefone pessoa fisica

            if (rbSexoFemininoPF.isChecked()) {
                pessoaFisica.setSexo(rbSexoFemininoPF.getText().toString());
            } else {
                pessoaFisica.setSexo(rbSexoMasculinoPF.getText().toString());
            }

            //Atributos que não dependem de obrigatoriedade//

            //CNH
            if (edtCnhPF.getText().toString().isEmpty()) {
                pessoaFisica.setRegistro_cnh(0);//CNH pessoa fisica
            } else {
                pessoaFisica.setRegistro_cnh(Integer.valueOf(edtCnhPF.getText().toString()));//CNH pessoa fisica
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
                pessoaFisica.setNumero(0);//Numero pessoa fisica
            } else {
                pessoaFisica.setNumero(Integer.parseInt(edtNumeroPF.getText().toString()));//Numero pessoa fisica
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
                pessoaFisica.setCep(0);//CEP pessoa fisica
            } else {
                pessoaFisica.setCep(Integer.parseInt(edtCepPF.getText().toString()));//CEP pessoa fisica
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

            salvarPessoaFisica(pessoaFisica);

        } else {
            Toast.makeText(CadastroEditarActivity.this, "Campos obrigatórios em falta!", Toast.LENGTH_LONG).show();
        }
    }

    private void pegaInformacoesPessoaFisica() {
        edtNomePF = (EditText) findViewById(R.id.edtNomePessoaFisica);//Nome pessoa fisica
        edtCpfPF = (EditText) findViewById(R.id.edtCpfPessoaFisica);//CPF pessoa fisica
        edtRgPF = (EditText) findViewById(R.id.edtRgPessoaFisica);//RG pessoa fisica
        edtCnhPF = (EditText) findViewById(R.id.edtCnhPessoaFisica);//CNH pessoa fisica
        rbSexoFemininoPF = (RadioButton) findViewById(R.id.rbFemininoPessoaFisica);//Sexo Feminino pessoa fisica
        rbSexoMasculinoPF = (RadioButton) findViewById(R.id.rbMasculinoPessoaFisica);//Sexo Feminino pessoa fisica
        edtDataNascPF = (EditText) findViewById(R.id.edtDataNascPessoaFisica);//Data Nascimento pessoa fisica
        edtTelefonePF = (EditText) findViewById(R.id.edtTelefonePessoaFisica);//Telefone pessoa fisica
        edtEnderecoPF = (EditText) findViewById(R.id.edtEnderecoPessoaFisica);//Endereco pessoa fisica
        edtNumeroPF = (EditText) findViewById(R.id.edtNumeroPessoaFisica);//Numero pessoa fisica
        edtCidadePF = (EditText) findViewById(R.id.edtCidadePessoaFisica);//Cidade pessoa fisica
        spEstadoPF = (Spinner) findViewById(R.id.spEstadosPessoaFisica);//Estado pessoa fisica
        edtBairroPF = (EditText) findViewById(R.id.edtBairroPessoaFisica);//Bairro pessoa fisica
        edtCepPF = (EditText) findViewById(R.id.edtCepPessoaFisica);//CEP pessoa fisica
        edtEmailPF = (EditText) findViewById(R.id.edtEmailPessoaFisica);//Email pessoa fisica
        edtProfissaoPF = (EditText) findViewById(R.id.edtProfissaoPessoaFisica);//Profissao pessoa fisica
    }

    private Boolean salvarPessoaFisica(PessoaFisica pessoaFisica) {
        try {
            firebase = ConfiguracaoFirebase.getFirebase().child("addPessoaFisica");
            firebase.child(pessoaFisica.getNome()).setValue(pessoaFisica);
            Toast.makeText(CadastroEditarActivity.this, "Pessoa física adicionada com sucesso!", Toast.LENGTH_LONG).show();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

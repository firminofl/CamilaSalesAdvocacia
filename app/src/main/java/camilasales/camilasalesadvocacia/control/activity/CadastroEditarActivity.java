package camilasales.camilasalesadvocacia.control.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarAudienciaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarFisicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarJuridicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarProcessoFragment;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

public class CadastroEditarActivity extends AppCompatActivity {

    private int telaAbrir;

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
    public static Bundle bundle;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_editar);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);      //Ativar o botão

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        telaAbrir = bundle.getInt("TelaCadastroOpcoes");


        switch (telaAbrir) {
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarFisicaFragment())
                        .commit();
                break;

            case 2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarJuridicaFragment())
                        .commit();
                break;

            case 3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarProcessoFragment())
                        .commit();
                break;

            case 4:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarAudienciaFragment())
                        .commit();
                break;

            case 5:
                Bundle receceEdicao = getIntent().getExtras();
                PessoaFisica editaPF = (PessoaFisica) receceEdicao.getSerializable("editaPF");

                Bundle bundlePFEdit = new Bundle();
                bundlePFEdit.putSerializable("editaPF", editaPF);

                Intent abrirFragmentPF = new Intent(this, CadastroEditarFisicaFragment.class);

                abrirFragmentPF.putExtra("editarPF", 1);
                abrirFragmentPF.putExtras(bundlePFEdit);

                CadastroEditarFisicaFragment fragment2 = new CadastroEditarFisicaFragment();

                android.support.v4.app.FragmentManager cadastroEditarFisicaFragment = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = cadastroEditarFisicaFragment.beginTransaction();
                transaction.replace(R.id.TelaCadastroEditar, fragment2); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                //startActivity(abrirFragmentPF);
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
                if (telaAbrir == 1) {
                    cadastrarPessoaFisica();
                } else if (telaAbrir == 2) {
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

    private void cadastrarPessoaFisica() {
        pegaInformacoesPessoaFisica();
        PessoaFisica pessoaFisica = new PessoaFisica();

        if (!edtNomePF.getText().toString().isEmpty() &&
                !edtCpfPF.getText().toString().isEmpty() &&
                !edtRgPF.getText().toString().isEmpty() &&
                !edtTelefonePF.getText().toString().isEmpty() &&
                (!rbSexoFemininoPF.isChecked() || !rbSexoMasculinoPF.isChecked()) &&
                !spEstadoPF.getSelectedItem().equals("Escolha um estado")) {

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

            salvarPessoaFisica(pessoaFisica);
            //limpaCamposPessoaFisica();
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

    private void salvarPessoaFisica(PessoaFisica pessoaFisica) {
        try {
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase().child("addPessoaFisica");
            firebase.child(pessoaFisica.getNome()).setValue(pessoaFisica);
            Toast.makeText(getApplicationContext(), "Pessoa física adicionada com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

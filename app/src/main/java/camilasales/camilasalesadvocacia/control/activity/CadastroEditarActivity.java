    package camilasales.camilasalesadvocacia.control.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        EditText edtNomePF;
        EditText edtCpfPF;
        EditText edtRgPF;
        EditText edtCnhPF;
        RadioButton rbSexoFemininoPF;
        RadioButton rbSexoMasculinoPF;
        EditText edtDataNascPF;
        EditText edtTelefonePF;
        EditText edtEnderecoPF;
        EditText edtNumeroPF;
        EditText edtCidadePF;
        Spinner spEstadoPF;
        EditText edtBairroPF;
        EditText edtCepPF;
        EditText edtEmailPF;
        EditText edtProfissaoPF;
        PessoaFisica pessoaFisica;
        DatabaseReference firebase;

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

        switch (telaAbrir){
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

            switch (item.getItemId()){
                case android.R.id.home: //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                    onBackPressed();
                break;

                case R.id.menu_botao_salvar:
                    if(estadoDeTela == 1) {
                        pessoaFisica = new PessoaFisica();
                        pegaInformacoes();
                        pessoaFisica.setNome(edtNomePF.getText().toString());//Nome pessoa fisica
                        Toast.makeText(CadastroEditarActivity.this, pessoaFisica.getNome().toString(), Toast.LENGTH_LONG).show();
                        pessoaFisica.setCpf(Integer.valueOf(edtCpfPF.getText().toString()));//CPF pessoa fisica
                        pessoaFisica.setRg(Integer.valueOf(edtRgPF.getText().toString()));//RG pessoa fisica
                        pessoaFisica.setRegistro_cnh(Integer.valueOf(edtCnhPF.getText().toString()));//CNH pessoa fisica

                        if (rbSexoFemininoPF.isChecked()) {//Sexo pessoa fisica
                            pessoaFisica.setSexo(rbSexoFemininoPF.getText().toString());
                        } else {
                            pessoaFisica.setSexo(rbSexoMasculinoPF.getText().toString());
                        }

                        pessoaFisica.setData_nasc(edtDataNascPF.getText().toString());//Data Nascimento pessoa fisica
                        pessoaFisica.setTelefone(Integer.parseInt(edtTelefonePF.getText().toString()));//Telefone pessoa fisica
                        pessoaFisica.setEndereco(edtEnderecoPF.getText().toString());//Endereco pessoa fisica
                        pessoaFisica.setNumero(Integer.parseInt(edtNumeroPF.getText().toString()));//Numero pessoa fisica
                        pessoaFisica.setCidade(edtCidadePF.getText().toString());//Cidade pessoa fisica
                        pessoaFisica.setEstado(spEstadoPF.getSelectedItem().toString());//Estado pessoa fisica
                        pessoaFisica.setBairro(edtBairroPF.getText().toString());//Bairro pessoa fisica
                        pessoaFisica.setCep(Integer.parseInt(edtCepPF.getText().toString()));//CEP pessoa fisica
                        pessoaFisica.setEmail(edtEmailPF.getText().toString());//Email pessoa fisica
                        pessoaFisica.setProfissao(edtProfissaoPF.getText().toString());//Profissão pessoa fisica

                        salvarPessoaFisica(pessoaFisica);
                        Toast.makeText(CadastroEditarActivity.this, "Pessoa física adicionada com sucesso!", Toast.LENGTH_LONG).show();

                    }else if( estadoDeTela == 2){
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
            startActivity(new Intent(CadastroEditarActivity.this,PrincipalActivity.class));
            finish();
            super.onBackPressed();
        }

        public void pegaInformacoes() {
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

        private Boolean salvarPessoaFisica(PessoaFisica pessoaFisica){
            try {
                firebase = ConfiguracaoFirebase.getFirebase().child("addPessoaFisica");
                firebase.child(pessoaFisica.getNome()).setValue(pessoaFisica);

                return true;

            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

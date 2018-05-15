package camilasales.camilasalesadvocacia.control.activity;

import android.app.ActionBar;
import android.app.Activity;
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

import java.util.Objects;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

public class TesteEditarPFActivity extends AppCompatActivity {

    private EditText edtNomePF;
    private  EditText edtCpfPF;
    private  EditText edtRgPF;
    private  EditText edtCnhPF;
    private RadioButton rbSexoFemininoPF;
    private  RadioButton rbSexoMasculinoPF;
    private  EditText edtDataNascPF;
    private  EditText edtTelefonePF;
    private  EditText edtEnderecoPF;
    private  EditText edtNumeroPF;
    private  EditText edtCidadePF;
    private Spinner spEstadoPF;
    private  EditText edtBairroPF;
    private  EditText edtCepPF;
    private  EditText edtEmailPF;
    private  EditText edtProfissaoPF;

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
        PessoaFisica editaPF = (PessoaFisica)receceEdicao.getSerializable("editaPF");
        atualizaCamposPessoaFisica(editaPF);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_botao_salvar:

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

    public void atualizaCamposPessoaFisica(PessoaFisica objPFlista){
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

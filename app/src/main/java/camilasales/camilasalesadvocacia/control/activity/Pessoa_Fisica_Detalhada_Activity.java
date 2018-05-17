package camilasales.camilasalesadvocacia.control.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.PessoaFisica;

public class Pessoa_Fisica_Detalhada_Activity extends AppCompatActivity {

    private PessoaFisica pessoaFisicaCompleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_completa_pessoa_fisica);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        pessoaFisicaCompleta = (PessoaFisica) bundle.getSerializable("listaPFCompleto");
        instanciaESetaOsValoresCompletos();
    }

    @SuppressLint("SetTextI18n")
    private void instanciaESetaOsValoresCompletos() {

        TextView txtViewNome = (TextView) findViewById(R.id.txtViewNomeLC);
        TextView txtViewCpf = (TextView) findViewById(R.id.txtViewCpfLC);
        TextView txtViewRg = (TextView) findViewById(R.id.txtViewRgLC);
        TextView txtViewCnh = (TextView) findViewById(R.id.txtViewCnhLC);
        TextView txtViewSexo = (TextView) findViewById(R.id.txtViewSexoLC);
        TextView txtViewDataNasc = (TextView) findViewById(R.id.txtViewDataNascLC);
        TextView txtViewTelefone = (TextView) findViewById(R.id.txtViewTelefoneLC);
        TextView txtViewEndereco = (TextView) findViewById(R.id.txtViewEnderecoLC);
        //TextView txtViewNumero = (TextView) findViewById(R.id.txtViewNumeroLC);
        TextView txtViewCidade = (TextView) findViewById(R.id.txtViewCidadeLC);
        //TextView txtViewEstado = (TextView) findViewById(R.id.txtViewEstadoLC);
        TextView txtViewBairro = (TextView) findViewById(R.id.txtViewBairroLC);
        TextView txtViewCep = (TextView) findViewById(R.id.txtViewCepLC);
        TextView txtViewEmail = (TextView) findViewById(R.id.txtViewEmailLC);
        TextView txtViewProfissao = (TextView) findViewById(R.id.txtViewProfissaoLC);

        txtViewNome.setText("Nome:\n" + pessoaFisicaCompleta.getNome());//Nome da pessoa fisica
        txtViewCpf.setText("CPF:\n" + pessoaFisicaCompleta.getCpf());//Cpf da pessoa fisica
        txtViewRg.setText("RG:\n" + pessoaFisicaCompleta.getRg());//Rg da pessoa fisica
        txtViewCnh.setText("Registro CNH:\n" + pessoaFisicaCompleta.getRegistro_cnh());//Cnh da pessoa fisica
        txtViewSexo.setText("Sexo:\n" + pessoaFisicaCompleta.getSexo());//Sexo da pessoa fisica
        txtViewDataNasc.setText("Data de nascimento:\n" + pessoaFisicaCompleta.getData_nasc());//Data Nascimento da pessoa fisica
        txtViewTelefone.setText("Telefone:\n" + pessoaFisicaCompleta.getTelefone());//Telefone da pessoa fisica
        txtViewEndereco.setText("Endereço:\n" + pessoaFisicaCompleta.getEndereco() + ", " + pessoaFisicaCompleta.getNumero());//Endereco da pessoa fisica
        //txtViewNumero.setText(pessoaFisicaCompleta.getNumero());//Numero da pessoa fisica
        txtViewCidade.setText("Cidade:\n" + pessoaFisicaCompleta.getCidade() + " - " + pessoaFisicaCompleta.getEstado());//Cidade da pessoa fisica
        //txtViewEstado.setText(pessoaFisicaCompleta.getEstado());//Estado da pessoa fisica
        txtViewBairro.setText("Bairro:\n" + pessoaFisicaCompleta.getBairro());//Bairro da pessoa fisica
        txtViewCep.setText("CEP:\n" + pessoaFisicaCompleta.getCep());//CEP da pessoa fisica
        txtViewEmail.setText("Email:\n" + pessoaFisicaCompleta.getEmail());//Email da pessoa fisica
        txtViewProfissao.setText("Profissão:\n" + pessoaFisicaCompleta.getProfissao());//Profissao da pessoa fisica
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Pessoa_Fisica_Detalhada_Activity.this, PrincipalActivity.class));
        finish();
        super.onBackPressed();
    }
}

package camilasales.camilasalesadvocacia.control.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.Objects;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarAudienciaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarFisicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarJuridicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarProcessoFragment;
import camilasales.camilasalesadvocacia.model.Audiencia;
import camilasales.camilasalesadvocacia.model.PessoaFisica;
import camilasales.camilasalesadvocacia.model.PessoaJuridica;

public class CadastroEditarActivity extends AppCompatActivity {

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
        int telaAbrir = bundle.getInt("TelaCadastroOpcoes");

        switch (telaAbrir) {
            case 1:
                //Serializable da pessoa fisica
                bundle = new Bundle();
                bundle.putInt("abrirEdicaoCadastro", 1);

                CadastroEditarFisicaFragment cadastroEditarFisicaFragment = new CadastroEditarFisicaFragment();
                cadastroEditarFisicaFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, cadastroEditarFisicaFragment)
                        .commit();
                break;

            case 2:
                //Serializable da pessoa fisica
                bundle = new Bundle();
                bundle.putInt("abrirEdicaoCadastro", 1);

                CadastroEditarJuridicaFragment cadastroEditarJuridicaFragment = new CadastroEditarJuridicaFragment();
                cadastroEditarJuridicaFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, cadastroEditarJuridicaFragment)
                        .commit();
                break;

            case 3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, new CadastroEditarProcessoFragment())
                        .commit();
                break;

            case 4:
                //Serializable da pessoa fisica
                bundle = new Bundle();
                bundle.putInt("abrirEdicaoCadastro", 1);

                CadastroEditarAudienciaFragment cadastroEditarAudienciaFragment = new CadastroEditarAudienciaFragment();
                cadastroEditarAudienciaFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, cadastroEditarAudienciaFragment)
                        .commit();
                break;

            case 5:
                PessoaFisica pessoaFisicaEditar = (PessoaFisica) bundle.getSerializable("editaPF");

                //Serializable da pessoa fisica
                bundle = new Bundle();
                bundle.putSerializable("editaPF", pessoaFisicaEditar);
                bundle.putInt("abrirEdicaoCadastro", 2);

                cadastroEditarFisicaFragment = new CadastroEditarFisicaFragment();
                cadastroEditarFisicaFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, cadastroEditarFisicaFragment)
                        .commit();
                break;

            case 6:
                PessoaJuridica pessoaJuridicaEditar = (PessoaJuridica) bundle.getSerializable("editaPJ");

                //Serializable da pessoa juridica
                bundle = new Bundle();
                bundle.putSerializable("editaPJ", pessoaJuridicaEditar);
                bundle.putInt("abrirEdicaoCadastro", 2);

                cadastroEditarJuridicaFragment = new CadastroEditarJuridicaFragment();
                cadastroEditarJuridicaFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, cadastroEditarJuridicaFragment)
                        .commit();
                break;

            case 8:
                Audiencia audienciaEditar = (Audiencia) bundle.getSerializable("editaAudiencia");

                //Serializable da pessoa juridica
                bundle = new Bundle();
                bundle.putSerializable("editaAudiencia", audienciaEditar);
                bundle.putInt("abrirEdicaoCadastro", 2);

                cadastroEditarAudienciaFragment = new CadastroEditarAudienciaFragment();
                cadastroEditarAudienciaFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.TelaCadastroEditar, cadastroEditarAudienciaFragment)
                        .commit();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastroEditarActivity.this, PrincipalActivity.class));
        finish();
        super.onBackPressed();
    }
}

    package camilasales.camilasalesadvocacia.control.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarAudienciaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarFisicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarJuridicaFragment;
import camilasales.camilasalesadvocacia.fragments.CadastroEditarProcessoFragment;

    public class CadastroEditarActivity extends AppCompatActivity {

        private Bundle bundle;
        private int telaAbrir;
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
    }

    package camilasales.camilasalesadvocacia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import camilasales.camilasalesadvocacia.fragments.CadastroEditarFisicaFragment;

    public class CadastroEditarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_editar);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.TelaCadastroEditar, new CadastroEditarFisicaFragment())
                    .commit();
        }
    }
}

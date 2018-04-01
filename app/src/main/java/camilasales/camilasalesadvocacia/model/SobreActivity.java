package camilasales.camilasalesadvocacia.model;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
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
        startActivity(new Intent(SobreActivity.this,PrincipalActivity.class));
        finish();
        super.onBackPressed();
    }
}

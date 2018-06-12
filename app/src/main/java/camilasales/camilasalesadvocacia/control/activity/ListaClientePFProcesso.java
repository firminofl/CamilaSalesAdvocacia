package camilasales.camilasalesadvocacia.control.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.adapter.PessoaFisicaAdapter;
import camilasales.camilasalesadvocacia.model.PessoaFisica;

public class ListaClientePFProcesso extends AppCompatActivity {

    private ListView listView;

    private ArrayList<PessoaFisica> pessoaFisica;
    private ArrayAdapter<PessoaFisica> pessoaFisicaAdapter;

    private ValueEventListener valueEventListenerPF;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_clientes_pf_pj_processo);

        listView = findViewById(R.id.lista_cliente_pf_processo);
        carregaDadosPessoaFisica();
    }

    private void carregaDadosPessoaFisica() {
        pessoaFisica = new ArrayList<>();

        pessoaFisicaAdapter = new PessoaFisicaAdapter(ListaClientePFProcesso.this, pessoaFisica);
        listView.setAdapter(pessoaFisicaAdapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("PessoaFisica");

        valueEventListenerPF = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pessoaFisica.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    PessoaFisica pessoaFisicaNova = dados.getValue(PessoaFisica.class);

                    pessoaFisica.add(pessoaFisicaNova);
                }
                pessoaFisicaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ListaClientePFProcesso.this, "Não foi possível encontrar as informações!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerPF);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPF);
    }
}

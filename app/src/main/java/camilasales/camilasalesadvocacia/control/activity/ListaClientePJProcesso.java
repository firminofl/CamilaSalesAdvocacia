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
import camilasales.camilasalesadvocacia.control.adapter.PessoaJuridicaAdapter;
import camilasales.camilasalesadvocacia.model.PessoaJuridica;

public class ListaClientePJProcesso  extends AppCompatActivity {
    private ListView listView;

    private ArrayList<PessoaJuridica> pessoaJuridica;
    private ArrayAdapter<PessoaJuridica> pessoaJuridicaAdapter;

    private ValueEventListener valueEventListenerPJ;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_clientes_pf_pj_processo);

        listView = findViewById(R.id.lista_cliente_pf_processo);
        carregaDadosPessoaJuridica();
    }

    private void carregaDadosPessoaJuridica(){
        pessoaJuridica = new ArrayList<>();

        pessoaJuridicaAdapter = new PessoaJuridicaAdapter(ListaClientePJProcesso.this, pessoaJuridica);
        listView.setAdapter(pessoaJuridicaAdapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("PessoaJuridica");

        valueEventListenerPJ = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pessoaJuridica.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    PessoaJuridica pessoaJuridicaNova = dados.getValue(PessoaJuridica.class);

                    pessoaJuridica.add(pessoaJuridicaNova);
                }
                pessoaJuridicaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ListaClientePJProcesso.this, "Não foi possível encontrar as informações!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerPJ);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPJ);
    }
}

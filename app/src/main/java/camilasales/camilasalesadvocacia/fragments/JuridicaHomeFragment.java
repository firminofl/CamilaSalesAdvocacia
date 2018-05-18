package camilasales.camilasalesadvocacia.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.CadastroEditarActivity;
import camilasales.camilasalesadvocacia.control.adapter.PessoaJuridicaAdapter;
import camilasales.camilasalesadvocacia.model.PessoaJuridica;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuridicaHomeFragment extends Fragment {

    private Context context;
    private ArrayList<PessoaJuridica> pessoaJuridica;
    private ArrayAdapter<PessoaJuridica> pessoaJuridicaAdapter;
    private ValueEventListener valueEventListenerPJ;
    private DatabaseReference firebase;

    public JuridicaHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_juridica, container, false);
        context = view.getContext();

        Button botaoCadastrarJuridica = (Button) view.findViewById(R.id.btnAddJuridica);

        botaoCadastrarJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroJuridica = new Intent(context, CadastroEditarActivity.class);//abre a tela de cadastro de pessoa juridica
                abrirTelaCadastroJuridica.putExtra("TelaCadastroOpcoes", 2);
                startActivity(abrirTelaCadastroJuridica);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });

        pessoaJuridica = new ArrayList<>();

        ListView listView = (ListView) view.findViewById(R.id.listViewPessoaJuridica);
        pessoaJuridicaAdapter = new PessoaJuridicaAdapter(context, pessoaJuridica);
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

            }
        };
        return view;
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

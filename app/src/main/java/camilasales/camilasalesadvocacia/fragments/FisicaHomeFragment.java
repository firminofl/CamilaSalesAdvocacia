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
import camilasales.camilasalesadvocacia.control.activity.CadastroEditarActivity;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.adapter.PessoaFisicaAdapter;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

/**
 * A simple {@link Fragment} subclass.
 */
public class FisicaHomeFragment extends Fragment {


    private Button botaoCadastrarFisica;
    private ListView listView;
    private ArrayAdapter<PessoaFisica> pessoaFisicaAdapter;
    private ArrayList<PessoaFisica> pessoaFisica;
    private ValueEventListener valueEventListenerPF;
    private DatabaseReference firebase;
    private Context context;

    public FisicaHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fisica, container, false);
        botaoCadastrarFisica = (Button) view.findViewById(R.id.btnAddFisica);//floating button (+) na tab Física, para cadastrar pessoa física

        botaoCadastrarFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroFisica = new Intent(getContext(), CadastroEditarActivity.class);//abre a tela de cadastro de pessoa juridica
                abrirTelaCadastroFisica.putExtra("TelaCadastroOpcoes", 1);
                startActivity(abrirTelaCadastroFisica);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });

        pessoaFisica = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listViewPessoaFisica);
        pessoaFisicaAdapter = new PessoaFisicaAdapter(getActivity().getApplicationContext(), pessoaFisica);
        listView.setAdapter(pessoaFisicaAdapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("addPessoaFisica");

        valueEventListenerPF = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pessoaFisica.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    PessoaFisica pessoaFisicaNova = dados.getValue(PessoaFisica.class);

                    pessoaFisica.add(pessoaFisicaNova);
                }
                pessoaFisicaAdapter.notifyDataSetChanged();
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
        firebase.removeEventListener(valueEventListenerPF);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPF);
    }
}

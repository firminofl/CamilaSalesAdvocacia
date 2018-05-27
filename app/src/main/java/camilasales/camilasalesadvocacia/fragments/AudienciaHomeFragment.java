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
import camilasales.camilasalesadvocacia.control.adapter.AudienciaAdapter;
import camilasales.camilasalesadvocacia.model.Audiencia;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudienciaHomeFragment extends Fragment {


    private Button botaoCadastrarAudiencia;
    private ArrayList<Audiencia> audiencia;
    private ArrayAdapter<Audiencia> audienciaAdapter;
    private ListView listView;
    private Context context;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerAudiencia;

    public AudienciaHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audiencia, container, false);

        botaoCadastrarAudiencia = (Button) view.findViewById(R.id.btnAddAudiencia);//floating button (+) na tab Audiencia, para cadastrar audiencia
        context = view.getContext();

        botaoCadastrarAudiencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroAudiencia = new Intent(getContext(), CadastroEditarActivity.class);//abre a tela de cadastro de audiencia
                abrirTelaCadastroAudiencia.putExtra("TelaCadastroOpcoes", 4);
                startActivity(abrirTelaCadastroAudiencia);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });

        audiencia = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.listAudiencia);
        audienciaAdapter = new AudienciaAdapter(context, audiencia);
        listView.setAdapter(audienciaAdapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("Audiencia");

        valueEventListenerAudiencia = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                audiencia.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Audiencia AudienciaNova = dados.getValue(Audiencia.class);

                    audiencia.add(AudienciaNova);
                }
                audienciaAdapter.notifyDataSetChanged();
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
        firebase.removeEventListener(valueEventListenerAudiencia);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerAudiencia);
    }

}

package camilasales.camilasalesadvocacia.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import camilasales.camilasalesadvocacia.model.PessoaFisica;

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
    private Audiencia audienciaEditar;
    private Audiencia audienciaExcluir;

    public AudienciaHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.opcoes_lista_audiencia, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.menu_editar_audiencia:
                //Pega o objeto selecionado na lista
                audienciaEditar = (Audiencia) listView.getItemAtPosition(info.position);

                //Serializable da pessoa fisica
                Bundle bundle = new Bundle();
                bundle.putSerializable("editaAudiencia", audienciaEditar);

                Intent abrirCadastroEditar = new Intent(context, CadastroEditarActivity.class);

                //Anexa o serializable no intent para levar para a classe de CadastroEditarActivity
                abrirCadastroEditar.putExtra("TelaCadastroOpcoes", 8);
                abrirCadastroEditar.putExtras(bundle);

                //Inicia a classe e envia o objeto para ela
                startActivity(abrirCadastroEditar);
                getActivity().finish();

                return true;

            case R.id.menu_excluir_audiencia:
                excluirAudiencia(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
                Toast.makeText(context, "Não foi possível encontrar as informações!", Toast.LENGTH_SHORT).show();
            }
        };

        registerForContextMenu(listView);
        return view;
    }

    private void excluirAudiencia(int position) {
        audienciaExcluir = audienciaAdapter.getItem(position);

        //ALERT DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Titulo do Alert Dialog
        builder.setTitle("Excluir");

        //Mensagem do Alert Dialog
        builder.setMessage("Quer mesmo excluir?\n\n" + "Data: " + audienciaExcluir.getData() + "\n" + "Horário: " + audienciaExcluir.getLocal());

        //Botão SIM do Alert Dialog
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebase = ConfiguracaoFirebase.getFirebase().child("Audiencia");
                firebase.child(audienciaExcluir.getUid()).removeValue();

                Toast.makeText(context, "Item excluído", Toast.LENGTH_SHORT).show();
            }
        });

        //Botão NAO do Alert Dialog
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Item mantido", Toast.LENGTH_SHORT).show();
            }
        });

        //Criar Alert Dialog
        AlertDialog alerta = builder.create();

        //Exibir Alert Dialog
        alerta.show();

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

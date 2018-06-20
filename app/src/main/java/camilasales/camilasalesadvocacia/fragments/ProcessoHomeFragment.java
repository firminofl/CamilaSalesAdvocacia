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
import camilasales.camilasalesadvocacia.control.adapter.ProcessoAdapter;
import camilasales.camilasalesadvocacia.model.Processo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessoHomeFragment extends Fragment {

    private Context context;
    private View view;

    private ArrayList<Processo> processos;
    private ListView listView;
    private ArrayAdapter<Processo> processoAdapter;

    private ValueEventListener valueEventListenerPF;
    private DatabaseReference firebase;

    private Button botaoCadastraProcesso;

    public ProcessoHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.opcoes_lista_processo, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_editar_processo:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                //Titulo do Alert Dialog
                builder.setTitle("Ops :(");

                //Mensagem do Alert Dialog
                builder.setMessage("Funcionalidade disponível na próxima Sprint.");

                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                //Criar Alert Dialog
                AlertDialog alerta = builder.create();

                //Exibir Alert Dialog
                alerta.show();

            /*    //Pega o objeto selecionado na lista
                editarPessoaFisica = (PessoaFisica) listView.getItemAtPosition(info.position);

                //Serializable da pessoa fisica
                Bundle bundle = new Bundle();
                bundle.putSerializable("editaPF", editarPessoaFisica);

                Intent abrirCadastroEditar = new Intent(context, CadastroEditarActivity.class);

                //Anexa o serializable no intent para levar para a classe de CadastroEditarActivity
                abrirCadastroEditar.putExtra("TelaCadastroOpcoes", 5);
                abrirCadastroEditar.putExtras(bundle);

                //Inicia a classe e envia o objeto para ela
                startActivity(abrirCadastroEditar);
                getActivity().finish();
            */
                return true;
            case R.id.menu_excluir_processo:
                excluirProcesso(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_processo, container, false);
        context = view.getContext();

        botaoCadastraProcesso = (Button) view.findViewById(R.id.btnAddProcesso);//floating button (+) na tab Física, para cadastrar processo


        botaoCadastraProcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroProcesso = new Intent(getContext(), CadastroEditarActivity.class);//abre a tela de cadastro de processo
                abrirTelaCadastroProcesso.putExtra("TelaCadastroOpcoes", 3);
                startActivity(abrirTelaCadastroProcesso);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });

        processos = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.listProcesso);
        processoAdapter = new ProcessoAdapter(context, processos);
        listView.setAdapter(processoAdapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("Processo");

        valueEventListenerPF = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                processos.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Processo processoNova = dados.getValue(Processo.class);

                    processos.add(processoNova);
                }
                processoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "Não foi possível encontrar as informações!", Toast.LENGTH_SHORT).show();
            }
        };

        registerForContextMenu(listView);
        return view;
    }

    private void excluirProcesso(int position) {
        Processo excluirProcesso = processoAdapter.getItem(position);

        //ALERT DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Titulo do Alert Dialog
        builder.setTitle("Excluir");

        //Mensagem do Alert Dialog
        assert excluirProcesso != null;
        builder.setMessage("Quer mesmo excluir?\n\n" + excluirProcesso.getTipoAcao());

        //Botão SIM do Alert Dialog
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebase = ConfiguracaoFirebase.getFirebase().child("Processo");
                firebase.child(excluirProcesso.getUid()).removeValue();

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
        firebase.removeEventListener(valueEventListenerPF);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPF);
    }

}

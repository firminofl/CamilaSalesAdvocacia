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
    private PessoaJuridica excluirPessoaJuridica;
    private ListView listView;
    private static PessoaJuridica editarPessoaJuridica;

    public JuridicaHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.opcoes_lista_pj, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_editar_pj:
                //Pega o objeto selecionado na lista
                editarPessoaJuridica = (PessoaJuridica) listView.getItemAtPosition(info.position);

                //Serializable da pessoa fisica
                Bundle bundle = new Bundle();
                bundle.putSerializable("editaPJ", editarPessoaJuridica);

                Intent abrirCadastroEditar = new Intent(context, CadastroEditarActivity.class);

                //Anexa o serializable no intent para levar para a classe de CadastroEditarActivity
                abrirCadastroEditar.putExtra("TelaCadastroOpcoes", 6);
                abrirCadastroEditar.putExtras(bundle);

                //Inicia a classe e envia o objeto para ela
                startActivity(abrirCadastroEditar);
                getActivity().finish();

                return true;
            case R.id.menu_excluir_pj:
                excluirPessoaJuridica(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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

        listView = (ListView) view.findViewById(R.id.listViewPessoaJuridica);
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
        registerForContextMenu(listView);
        return view;
    }

    //EXCLUIR COM O ALERT DIALOG
    private void excluirPessoaJuridica(int position) {
        excluirPessoaJuridica = pessoaJuridicaAdapter.getItem(position);

        //ALERT DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Titulo do Alert Dialog
        builder.setTitle("Excluir");

        //Mensagem do Alert Dialog
        builder.setMessage("Quer mesmo excluir?\n\n" + excluirPessoaJuridica.getNome_razao_social());

        //Botão SIM do Alert Dialog
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebase = ConfiguracaoFirebase.getFirebase().child("PessoaJuridica");
                firebase.child(excluirPessoaJuridica.getUid()).removeValue();

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
        firebase.removeEventListener(valueEventListenerPJ);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPJ);
    }
}

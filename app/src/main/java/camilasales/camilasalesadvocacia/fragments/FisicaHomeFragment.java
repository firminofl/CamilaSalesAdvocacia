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

import java.io.Serializable;
import java.util.ArrayList;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.CadastroEditarActivity;
import camilasales.camilasalesadvocacia.control.activity.TesteEditarPFActivity;
import camilasales.camilasalesadvocacia.control.adapter.PessoaFisicaAdapter;
import camilasales.camilasalesadvocacia.model.Entidades.PessoaFisica;

/**
 * A simple {@link Fragment} subclass.
 */
public class FisicaHomeFragment extends Fragment implements Serializable {


    private Button botaoCadastrarFisica;
    private ListView listView;
    private ArrayAdapter<PessoaFisica> pessoaFisicaAdapter;
    private ArrayList<PessoaFisica> pessoaFisica;
    private ValueEventListener valueEventListenerPF;
    private DatabaseReference firebase;
    private Context context;
    private AlertDialog alerta;
    private PessoaFisica excluirPessoaFisica;
    public static PessoaFisica editarPessoaFisica;
    private View view;
    private PessoaFisica listarDetalhesPessoaFisica;

    public FisicaHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.opcoes_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_editar:
                editarPessoaFisica = (PessoaFisica) listView.getItemAtPosition(info.position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("editaPF", editarPessoaFisica);

                Intent abrirEditTest = new Intent(context, TesteEditarPFActivity.class);

                abrirEditTest.putExtras(bundle);

                startActivity(abrirEditTest);
                getActivity().finish();

                return true;
            case R.id.menu_excluir:
                excluirPessoaFisica(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fisica, container, false);

        botaoCadastrarFisica = (Button) view.findViewById(R.id.btnAddFisica);//floating button (+) na tab Física, para cadastrar pessoa física
        context = view.getContext();

        botaoCadastrarFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroFisica = new Intent(context, CadastroEditarActivity.class);//abre a tela de cadastro de pessoa juridica
                abrirTelaCadastroFisica.putExtra("TelaCadastroOpcoes", 1);
                startActivity(abrirTelaCadastroFisica);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });

        pessoaFisica = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.listViewPessoaFisica);
        pessoaFisicaAdapter = new PessoaFisicaAdapter(context, pessoaFisica);
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

            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                listarDetalhesPessoaFisica = (PessoaFisica) parent.getItemAtPosition(position);
                Toast.makeText(context, "Pessoa: " + listarDetalhesPessoaFisica.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(listView);
        return view;
    }

    //EXCLUIR COM O ALERT DIALOG
    private void excluirPessoaFisica(int position) {
        excluirPessoaFisica = pessoaFisicaAdapter.getItem(position);

        //ALERT DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Titulo do Alert Dialog
        builder.setTitle("Excluir");

        //Mensagem do Alert Dialog
        builder.setMessage("Quer mesmo excluir: " + excluirPessoaFisica.getNome() + " ?");

        //Botão SIM do Alert Dialog
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebase = ConfiguracaoFirebase.getFirebase().child("PessoaFisica");
                firebase.child(excluirPessoaFisica.getUid()).removeValue();

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
        alerta = builder.create();

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
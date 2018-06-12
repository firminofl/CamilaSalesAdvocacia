package camilasales.camilasalesadvocacia.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.activity.ListaClientePFProcesso;
import camilasales.camilasalesadvocacia.control.activity.ListaClientePJProcesso;
import camilasales.camilasalesadvocacia.model.PessoaFisica;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarProcessoFragment extends Fragment {

    private View view;
    private Context context;

    private RadioButton rbPFProcesso;
    private RadioButton rbPJProcesso;

    private ArrayList<PessoaFisica> pessoaFisica;
    private ArrayAdapter<PessoaFisica> pessoaFisicaAdapter;

    private ValueEventListener valueEventListenerPF;
    private DatabaseReference firebase;

    public CadastroEditarProcessoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_processo, container, false);
        context = view.getContext();

        rbPFProcesso = view.findViewById(R.id.rbPFProcesso);
        rbPJProcesso = view.findViewById(R.id.rbPJProcesso);

        escolhaDeCliente();

        return view;
    }

    private void escolhaDeCliente() {
        rbPFProcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbPFProcesso.isChecked()) {
                    startActivity(new Intent(getActivity(), ListaClientePFProcesso.class));
                }
            }
        });

        rbPJProcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbPJProcesso.isChecked()) {
                    startActivity(new Intent(getActivity(), ListaClientePJProcesso.class));
                }
            }
        });
    }

    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Set a title to the dialog
        builder.setTitle("Cliente");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.lista_clientes_pf_pj_processo, null))
                // Add action buttons
                .setPositiveButton("Selecionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        //Criar Alert Dialog
        AlertDialog alerta = builder.create();

        //Exibir Alert Dialog
        alerta.show();
    }
}

package camilasales.camilasalesadvocacia.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import camilasales.camilasalesadvocacia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarProcessoFragment extends Fragment {

    private View view;

    private ListView listView;
    private RadioButton rbPFProcesso;

    public CadastroEditarProcessoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_cadastro_editar_processo, container, false);

        listView = view.findViewById(R.id.lista_clientes_processo);
        rbPFProcesso = view.findViewById(R.id.rbPFProcesso);

        escolhaDeCliente();
        return view;
    }

    private void escolhaDeCliente(){
        rbPFProcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbPFProcesso.isChecked()){
                    mostrarDialogo();
                }
            }
        });
    }

    private void mostrarDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Set a title to the dialog
        builder.setTitle("Cliente");
        
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.lista_clientes_processo, null))
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

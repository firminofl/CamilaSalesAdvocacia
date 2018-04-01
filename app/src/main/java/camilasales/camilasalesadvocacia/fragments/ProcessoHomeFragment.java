package camilasales.camilasalesadvocacia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.CadastroEditarActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessoHomeFragment extends Fragment {


    private Button botaoCadastraProcesso;

    public ProcessoHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_processo, container, false);

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
        return view;
    }

}

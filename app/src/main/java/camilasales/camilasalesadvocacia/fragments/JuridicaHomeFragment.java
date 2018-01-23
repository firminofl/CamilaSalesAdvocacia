package camilasales.camilasalesadvocacia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import camilasales.camilasalesadvocacia.CadastroEditarActivity;
import camilasales.camilasalesadvocacia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuridicaHomeFragment extends Fragment {

    private Button botaoCadastrarJuridica;

    public JuridicaHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_juridica, container, false);

        botaoCadastrarJuridica = (Button) view.findViewById(R.id.btnAddJuridica);//floating button (+) na tab Física, para cadastrar pessoa física

        botaoCadastrarJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroJuridica = new Intent(getContext(), CadastroEditarActivity.class);//abre a tela de cadastro de pessoa juridica
                abrirTelaCadastroJuridica.putExtra("TelaCadastroOpcoes", 2);
                startActivity(abrirTelaCadastroJuridica);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });



        return view;
    }

}

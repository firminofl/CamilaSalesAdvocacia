package camilasales.camilasalesadvocacia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import camilasales.camilasalesadvocacia.control.activity.CadastroEditarActivity;
import camilasales.camilasalesadvocacia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudienciaHomeFragment extends Fragment {


    private Button botaoCadastrarAudiencia;
    public AudienciaHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audiencia, container, false);

        botaoCadastrarAudiencia = (Button) view.findViewById(R.id.btnAddAudiencia);//floating button (+) na tab Audiencia, para cadastrar audiencia

        botaoCadastrarAudiencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTelaCadastroAudiencia = new Intent(getContext(), CadastroEditarActivity.class);//abre a tela de cadastro de pessoa juridica
                abrirTelaCadastroAudiencia.putExtra("TelaCadastroOpcoes", 4);
                startActivity(abrirTelaCadastroAudiencia);
                getActivity().finish();//finaliza a activity para não coloca-la na pilha de execução
            }
        });



        return view;
    }

}

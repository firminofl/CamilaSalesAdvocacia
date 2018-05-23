package camilasales.camilasalesadvocacia.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.Mask;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarAudienciaFragment extends Fragment {

    private View view;
    private Context context;
    private int telaEditarCadastra;

    private EditText edtDataAudiencia;
    private EditText edtHorarioAudiencia;
    private EditText edtLocalAudiencia;
    private EditText edtVaraAudiencia;


    public CadastroEditarAudienciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_audiencia, container, false);
        context = view.getContext();

        pegaInformacoesAudiencia();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.botao_salvar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Botão adicional na ToolBar

        switch (item.getItemId()) {
            case android.R.id.home: //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                return true;

            case R.id.menu_botao_salvar:
                if (telaEditarCadastra == 1) {
                    //cadastrarAudiencia();
                } else {
                    //editarAudiencia();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Voltar tela inicial, mas sem ser Override
    public void onBackPressed() {
        startActivity(new Intent(context, PrincipalActivity.class));
        getActivity().finish();
        super.getActivity().onBackPressed();
    }

    private void pegaInformacoesAudiencia() {
        edtDataAudiencia = (EditText) view.findViewById(R.id.edtDataAudiencia);
        edtDataAudiencia.addTextChangedListener(Mask.insert("##/##/####", edtDataAudiencia));
        edtHorarioAudiencia = (EditText) view.findViewById(R.id.edtHorarioAudiencia);
        edtHorarioAudiencia.addTextChangedListener(Mask.insert("##:##", edtHorarioAudiencia));
        edtLocalAudiencia = (EditText) view.findViewById(R.id.edtLocalAudiencia);
        edtVaraAudiencia = (EditText) view.findViewById(R.id.edtVaraAudiencia);
    }
}

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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.Mask;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;
import camilasales.camilasalesadvocacia.model.Audiencia;

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
    private Audiencia audienciaEditar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_audiencia, container, false);
        context = view.getContext();

        Bundle args = getArguments();
        telaEditarCadastra = args.getInt("abrirEdicaoCadastro");

        audienciaEditar = (Audiencia) args.getSerializable("editaAudiencia");

        if (telaEditarCadastra == 2) {
            assert audienciaEditar != null;
            atualizaCamposAudiencia(audienciaEditar);
        }
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
                    cadastrarAudiencia();
                } else {
                    editarAudiencia();
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

    private void informacoesComumEntreCadastraEdita(Audiencia audiencia) {
        //DATA DA AUDIENCIA
        if (!edtDataAudiencia.getText().toString().isEmpty()) {
            audiencia.setData(edtDataAudiencia.getText().toString());
        } else {
            audiencia.setData(null);
        }

        //HORARIO DA AUDIENCIA
        if (!edtHorarioAudiencia.getText().toString().isEmpty()) {
            audiencia.setHorario(edtHorarioAudiencia.getText().toString());
        } else {
            audiencia.setHorario(null);
        }

        //LOCAL DA AUDIENCIA
        if (!edtLocalAudiencia.getText().toString().isEmpty()) {
            audiencia.setLocal(edtLocalAudiencia.getText().toString());
        } else {
            audiencia.setLocal(null);
        }

        //VARA DA AUDIENCIA
        if (!edtVaraAudiencia.getText().toString().isEmpty()) {
            audiencia.setVara(edtVaraAudiencia.getText().toString());
        } else {
            audiencia.setVara(null);
        }
    }

    private void cadastrarAudiencia() {
        pegaInformacoesAudiencia();
        Audiencia audiencia = new Audiencia();

        audiencia.setUid(UUID.randomUUID().toString());
        informacoesComumEntreCadastraEdita(audiencia);

        salvarAudiencia(audiencia);
        onBackPressed();
    }

    private void salvarAudiencia(Audiencia audiencia) {
        try {
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase().child("Audiencia");
            firebase.child(audiencia.getUid()).setValue(audiencia);
            Toast.makeText(context, "Audiência adicionada com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Falha ao editar!", Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizaCamposAudiencia(Audiencia audienciaEditar) {
        pegaInformacoesAudiencia();

        /*Na hora de atualizar campos com mascara a aplicação está travando
        para solucionar: salvar somente os numeros e então setar eles sem o / ou :
         */
        edtDataAudiencia.setText(audienciaEditar.getData());//Data da audiencia
        edtHorarioAudiencia.setText(audienciaEditar.getHorario());//Horario da audiencia
        edtLocalAudiencia.setText(audienciaEditar.getLocal());//Local da audiencia
        edtVaraAudiencia.setText(audienciaEditar.getVara());//Vara da audiencia
    }

    private void editarAudiencia() {
        pegaInformacoesAudiencia();
        Audiencia audiencia = new Audiencia();

        audiencia.setUid(audienciaEditar.getUid());
        informacoesComumEntreCadastraEdita(audiencia);

        //ATUALIZA INFORMAÇÕES NO FIREBASE
        try {
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
            firebase.child("Audiencia").child(audiencia.getUid()).setValue(audiencia);
            Toast.makeText(context, "Audiência alterada com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Falha ao editar!", Toast.LENGTH_SHORT).show();
        }
        onBackPressed();
    }
}

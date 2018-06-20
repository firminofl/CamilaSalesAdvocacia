package camilasales.camilasalesadvocacia.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

import camilasales.camilasalesadvocacia.DAO.ConfiguracaoFirebase;
import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.Mask;
import camilasales.camilasalesadvocacia.control.activity.PrincipalActivity;
import camilasales.camilasalesadvocacia.model.PessoaFisica;
import camilasales.camilasalesadvocacia.model.Processo;

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

    private EditText tipoAcao;
    private EditText numeroAcao;
    private RadioButton formaPagamentoAVista;
    private RadioButton formaPagamentoCartao;
    private EditText valorAcao;
    private Spinner parcelas;
    private EditText dataVencimento;
    private EditText prazoPagamento;
    private TextInputLayout textInputLayoutVencimento;
    private TextInputLayout textInputLayoutPrazo;

    public CadastroEditarProcessoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_editar_processo, container, false);
        context = view.getContext();

        //rbPFProcesso = view.findViewById(R.id.rbPFProcesso);
        //rbPJProcesso = view.findViewById(R.id.rbPJProcesso);

        pegaInformacoesProcesso();
        escolhaDeClienteEPegamento();

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
                //if (telaEditarCadastra == 1) {
                cadastrarProcesso();
                //} else {
                //editarPessoaFisica();
                //}
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

    private void escolhaDeClienteEPegamento() {
        /*rbPFProcesso.setOnClickListener(new View.OnClickListener() {
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
        */
        formaPagamentoAVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parcelas.setVisibility(View.INVISIBLE);
                textInputLayoutVencimento.setVisibility(View.INVISIBLE);
                textInputLayoutPrazo.setVisibility(View.INVISIBLE);
            }
        });

        formaPagamentoCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parcelas.setVisibility(View.VISIBLE);
                textInputLayoutVencimento.setVisibility(View.VISIBLE);
                textInputLayoutPrazo.setVisibility(View.VISIBLE);
            }
        });
    }

    private void cadastrarProcesso() {
        pegaInformacoesProcesso();
        Processo processo = new Processo();

        if (!tipoAcao.getText().toString().isEmpty() &&
                !numeroAcao.getText().toString().isEmpty() &&
                !valorAcao.getText().toString().isEmpty()) {

            processo.setUid(UUID.randomUUID().toString());
            processo.setTipoAcao(tipoAcao.getText().toString());
            processo.setNumeroAcao(numeroAcao.getText().toString());

            if (formaPagamentoAVista.isChecked()) {
                processo.setFormaPagamento(formaPagamentoAVista.getText().toString());
                processo.setValorAcao(valorAcao.getText().toString());
                salvarProcesso(processo);
                onBackPressed();

            } else if (formaPagamentoCartao.isChecked()) {

                if (!parcelas.getSelectedItem().toString().equals("Quantidade de parcelas") &&
                        !dataVencimento.getText().toString().isEmpty() &&
                        !prazoPagamento.getText().toString().isEmpty()) {

                    processo.setValorAcao(valorAcao.getText().toString());
                    processo.setQtParcelasAcao(parcelas.getSelectedItem().toString());
                    processo.setDataVencimentoAcao(dataVencimento.getText().toString());
                    processo.setPrazoPagamentoAcao(prazoPagamento.getText().toString());
                    salvarProcesso(processo);
                    onBackPressed();

                } else {
                    Toast.makeText(context, "Campos obrigatórios em falta!", Toast.LENGTH_SHORT).show();
                    tipoAcao.setFocusable(true);
                }
            }

        } else {
            Toast.makeText(context, "Campos obrigatórios em falta!", Toast.LENGTH_SHORT).show();
            tipoAcao.setFocusable(true);
        }
    }

    private void salvarProcesso(Processo processo) {
        try {
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebase().child("Processo");
            firebase.child(processo.getUid()).setValue(processo);
            Toast.makeText(context, "Processo adicionado com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pegaInformacoesProcesso() {
        tipoAcao = view.findViewById(R.id.edtTipoAcaoProcesso);
        numeroAcao = view.findViewById(R.id.edtNumeroProcesso);
        formaPagamentoAVista = view.findViewById(R.id.rbAVistaProcesso);
        formaPagamentoCartao = view.findViewById(R.id.rbCartaoProcesso);
        valorAcao = view.findViewById(R.id.edtValorAcaoProcesso);
        parcelas = view.findViewById(R.id.spnParcelasProcesso);
        dataVencimento = view.findViewById(R.id.edtDataVencimentoProcesso);
        dataVencimento.addTextChangedListener(Mask.insert("##/##/####", dataVencimento));
        prazoPagamento = view.findViewById(R.id.edtDataPrazoProcesso);
        prazoPagamento.addTextChangedListener(Mask.insert("##/##/####", prazoPagamento));

        textInputLayoutVencimento = view.findViewById(R.id.input_layout_Data_Vencimento_Processo);
        textInputLayoutPrazo = view.findViewById(R.id.input_layout_Data_Prazo_Processo);
    }
}

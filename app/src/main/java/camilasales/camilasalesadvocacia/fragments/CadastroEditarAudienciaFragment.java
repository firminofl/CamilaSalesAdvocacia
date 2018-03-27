package camilasales.camilasalesadvocacia.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import camilasales.camilasalesadvocacia.Mask;
import camilasales.camilasalesadvocacia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroEditarAudienciaFragment extends Fragment {

    EditText edtDataAudiencia;
    EditText edtHorarioAudiencia;

    public CadastroEditarAudienciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_editar_audiencia, container, false);

        edtDataAudiencia = (EditText) view.findViewById(R.id.edtDataAudiencia);
        edtHorarioAudiencia = (EditText) view.findViewById(R.id.edtHorarioAudiencia);
        edtDataAudiencia.addTextChangedListener(Mask.insert("##/##/####", edtDataAudiencia));
        edtHorarioAudiencia.addTextChangedListener(Mask.insert("##:##", edtHorarioAudiencia));

        return view;
    }

}

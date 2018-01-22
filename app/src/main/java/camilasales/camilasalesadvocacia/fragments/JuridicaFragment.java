package camilasales.camilasalesadvocacia.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import camilasales.camilasalesadvocacia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuridicaFragment extends Fragment {


    public JuridicaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_juridica, container, false);
    }

}

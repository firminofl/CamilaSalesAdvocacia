package camilasales.camilasalesadvocacia.control.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.Processo;

public class ProcessoAdapter extends ArrayAdapter<Processo> {

    private ArrayList<Processo> processo;
    private Context context;

    public ProcessoAdapter(Context c, ArrayList<Processo> objectsProcesso) {
        super(c, 0, objectsProcesso);
        this.processo = objectsProcesso;
        this.context = c;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewResumida = null;

        if (processo != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            viewResumida = inflater.inflate(R.layout.lista_resumida_processo, parent, false);

            instanciaESetaOsValoresResumidosProcesso(viewResumida, position);
        }
        return viewResumida;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void instanciaESetaOsValoresResumidosProcesso(View viewRS, int position) {
        TextView txtViewTipoAcao = (TextView) viewRS.findViewById(R.id.txtViewTipoAcaoLRProcesso);
        TextView txtViewNumeroProcesso = (TextView) viewRS.findViewById(R.id.txtViewNumeroLRProcesso);

        Processo processo2 = processo.get(position);

        txtViewTipoAcao.setText(processo2.getTipoAcao());//Tipo da ação do processo
        txtViewNumeroProcesso.setText(processo2.getNumeroAcao());//Numero do processo
    }
}

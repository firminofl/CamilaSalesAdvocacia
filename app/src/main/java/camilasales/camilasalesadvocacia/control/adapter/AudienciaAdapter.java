package camilasales.camilasalesadvocacia.control.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.model.Audiencia;

public class AudienciaAdapter extends ArrayAdapter<Audiencia> {

    private ArrayList<Audiencia> audiencia;
    private Context context;

    public AudienciaAdapter(Context c, ArrayList<Audiencia> objectsAudiencia) {
        super(c, 0, objectsAudiencia);
        this.audiencia = objectsAudiencia;
        this.context = c;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewResumida = null;

        if (audiencia != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            viewResumida = inflater.inflate(R.layout.lista_resumida_audiencia, parent, false);

            instanciaESetaOsValoresResumidosAudiencia(viewResumida, position);
        }
        return viewResumida;
    }

    private void instanciaESetaOsValoresResumidosAudiencia(View viewRS, int position) {
        TextView txtDataLRAudiencia = (TextView) viewRS.findViewById(R.id.txtDataLRAudiencia);
        TextView txtHorarioLRAudiencia = (TextView) viewRS.findViewById(R.id.txtHorarioLRAudiencia);
        TextView txtLocalLRAudiencia = (TextView) viewRS.findViewById(R.id.txtLocalLRAudiencia);

        Audiencia audiencia2 = audiencia.get(position);

        txtDataLRAudiencia.setText(audiencia2.getData());//Data da audiencia
        txtHorarioLRAudiencia.setText(audiencia2.getHorario());//Horario da audiencia
        txtLocalLRAudiencia.setText(audiencia2.getLocal());// Local da audiencia
    }
}

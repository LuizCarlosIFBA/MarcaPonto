package br.com.rlimanogueira.marcaponto;

import android.widget.EditText;

import br.com.rlimanogueira.marcaponto.Model.Dados;

public class FormularioHelper {
    private EditText horaEntrada, saidaIntervalo, voltaIntervalo, horaSaida, data;
    private Dados dados;

    public FormularioHelper(PontoActivity activity){
        data = activity.findViewById(R.id.data);
        horaEntrada = activity.findViewById(R.id.edt_HoraEntrada);
        saidaIntervalo = activity.findViewById(R.id.edt_SaidaIntervalo);
        voltaIntervalo = activity.findViewById(R.id.edt_VoltaIntervalo);
        horaSaida = activity.findViewById(R.id.edt_HoraSaida);

        dados = new Dados();
    }

    public Dados pegaData() {
        dados.setData(data.getText().toString());
        dados.setHoraEntrada(horaEntrada.getText().toString());
        dados.setsaidaIntervalo(saidaIntervalo.getText().toString());
        dados.setVoltaIntervalo(voltaIntervalo.getText().toString());
        dados.setHoraSaida(horaSaida.getText().toString());
    return dados;
    }

    public void preencherFormulario(Dados dados) {
        data.setText(dados.getData());
        horaEntrada.setText(dados.getHoraEntrada());
        saidaIntervalo.setText(dados.getsaidaIntervalo());
        voltaIntervalo.setText(dados.getvoltaIntervalo());
        horaSaida.setText(dados.getHoraSaida());

        this.dados = dados;
    }
}

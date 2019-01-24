package br.com.rlimanogueira.marcaponto;

import android.widget.EditText;

import br.com.rlimanogueira.marcaponto.Model.Horario;

public class FormularioHelper {
    private EditText horaEntrada, saidaIntervalo, voltaIntervalo, horaSaida, data;
    private Horario horario;

    public FormularioHelper(PontoActivity activity){
        data = activity.findViewById(R.id.data);
        horaEntrada = activity.findViewById(R.id.edt_HoraEntrada);
        saidaIntervalo = activity.findViewById(R.id.edt_SaidaIntervalo);
        voltaIntervalo = activity.findViewById(R.id.edt_VoltaIntervalo);
        horaSaida = activity.findViewById(R.id.edt_HoraSaida);

        horario = new Horario();
    }

    public Horario pegaData() {
        horario.setData(data.getText().toString());
        horario.setHoraEntrada(horaEntrada.getText().toString());
        horario.setsaidaIntervalo(saidaIntervalo.getText().toString());
        horario.setVoltaIntervalo(voltaIntervalo.getText().toString());
        horario.setHoraSaida(horaSaida.getText().toString());
    return horario;
    }

    public void preencherFormulario(Horario horario) {
        data.setText(horario.getData());
        horaEntrada.setText(horario.getHoraEntrada());
        saidaIntervalo.setText(horario.getsaidaIntervalo());
        voltaIntervalo.setText(horario.getvoltaIntervalo());
        horaSaida.setText(horario.getHoraSaida());

        this.horario = horario;
    }
}

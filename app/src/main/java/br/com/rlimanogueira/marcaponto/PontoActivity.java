package br.com.rlimanogueira.marcaponto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.rlimanogueira.marcaponto.DAO.DadosDAO;
import br.com.rlimanogueira.marcaponto.Model.Dados;

public class PontoActivity extends AppCompatActivity {

    private EditText data, horaEntrada, horaSaida, saidaIntervalo, voltaIntervalo;
    private DatePickerDialog datePickerDialogData;
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponto);

        setTitle("Marca Ponto");

        helper = new FormularioHelper(this);
        carregaObjetos();

        carregaHoraEntrada();
        carregaCalendario();

        mostraHora();
        carregaDados();

    }

   private void carregaDados() {
        Intent intent = getIntent();
        Dados dados = (Dados) intent.getSerializableExtra("dados");
        if(dados !=null){
            helper.preencherFormulario(dados);
            data.setEnabled(false);
            horaEntrada.setEnabled(false);
            if (dados.getsaidaIntervalo().isEmpty()) {
               carregaHoraSaidaIntervalo();
               voltaIntervalo.setEnabled(false);
               horaSaida.setEnabled(false);
            }else if(dados.getvoltaIntervalo().isEmpty()){
                carregaHoraVoltaIntervalo();
                saidaIntervalo.setEnabled(false);
                horaSaida.setEnabled(false);
            }else if(dados.getHoraSaida().isEmpty()){
                carregaHoraSaida();
                voltaIntervalo.setEnabled(false);
                saidaIntervalo.setEnabled(false);
            }
            data.setEnabled(false);
            horaEntrada.setEnabled(false);
            saidaIntervalo.setEnabled(false);
            voltaIntervalo.setEnabled(false);
            horaSaida.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Dados dados = helper.pegaData();
                DadosDAO dao = new DadosDAO(this);
                if (data.getText().toString().equals("")) {
                    Toast.makeText(PontoActivity.this, "Preencha o campo DATA", Toast.LENGTH_SHORT).show();
                } else {
                    if (dados.getId() != null) {
                        dao.altera(dados);
                    } else {
                        dao.insere(dados);
                    }
                    dao.close();
                    finish();
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaObjetos() {
        data = findViewById(R.id.data);
        horaEntrada = findViewById(R.id.edt_HoraEntrada);
        horaSaida = findViewById(R.id.edt_HoraSaida);
        saidaIntervalo = findViewById(R.id.edt_SaidaIntervalo);
        voltaIntervalo = findViewById(R.id.edt_VoltaIntervalo);
    }

    private void mostraHora() {
        data = findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogData.show();
            }
        });
    }

    private void carregaCalendario() {
        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual   = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual   = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual   = calendarDataAtual.get(Calendar.DAY_OF_MONTH);
        datePickerDialogData = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
                String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ? "0" + (mesSelecionado + 1 ): String.valueOf(mesSelecionado));
                data = findViewById(R.id.data);
                data.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);
            }

        }, anoAtual, mesAtual, diaAtual);
    }

    private void carregaHoraEntrada() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date hora = Calendar.getInstance().getTime();
        String horaFormatada = sdf.format(hora);
        horaEntrada.setText(horaFormatada);
    }

    private void carregaHoraSaida() {
        SimpleDateFormat sdfs = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date horaSaid = Calendar.getInstance().getTime();
        String horaFormatada = sdfs.format(horaSaid);
        horaSaida.setText(horaFormatada);
    }

    private void carregaHoraSaidaIntervalo() {
        SimpleDateFormat sdfs = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date horaSaid = Calendar.getInstance().getTime();
        String horaFormatada = sdfs.format(horaSaid);
        saidaIntervalo.setText(horaFormatada);
    }

    private void carregaHoraVoltaIntervalo() {
        SimpleDateFormat sdfs = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date horaSaid = Calendar.getInstance().getTime();
        String horaFormatada = sdfs.format(horaSaid);
        voltaIntervalo.setText(horaFormatada);
    }
}

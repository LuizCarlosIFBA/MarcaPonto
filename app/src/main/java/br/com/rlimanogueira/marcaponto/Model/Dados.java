package br.com.rlimanogueira.marcaponto.Model;

import java.io.Serializable;
import java.util.Date;

public class Dados implements Serializable {
    private Long id;
    private String data;
    private String horaEntrada;
    private String saidaIntervalo;
    private String voltaIntervalo;
    private String horaSaida;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    @Override
    public String toString() {
        return getData();
    }

    public String getsaidaIntervalo() {
        return saidaIntervalo;
    }

    public void setsaidaIntervalo(String saidaAlmoco) {
        this.saidaIntervalo = saidaAlmoco;
    }

    public String getvoltaIntervalo() {
        return voltaIntervalo;
    }

    public void setVoltaIntervalo(String voltaAlmoco) {
        this.voltaIntervalo = voltaAlmoco;
    }
}

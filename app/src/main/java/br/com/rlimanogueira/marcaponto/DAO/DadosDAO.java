package br.com.rlimanogueira.marcaponto.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rlimanogueira.marcaponto.Model.Horario;
public class DadosDAO implements Closeable {


    private SQLiteDAO dao;
    private Context context;

    public DadosDAO (Context context) {
        this.dao = new SQLiteDAO(context);
        this.context = context;
    }

    public void insere(Horario horario) {
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDados(horario);

        db.insert("Dados",null, dados);
    }

    private ContentValues pegaDados(Horario horario) {
        ContentValues dados = new ContentValues();
        dados.put("id_dados", horario.getId());
        dados.put("data", horario.getData());
        dados.put("horaEntrada", horario.getHoraEntrada());
        dados.put("saidaIntervalo", horario.getsaidaIntervalo());
        dados.put("voltaIntervalo", horario.getvoltaIntervalo());
        dados.put("horaSaida", horario.getHoraSaida());
        return dados;
    }

    public List<Horario> buscaDatas() {
        String sql = "SELECT * FROM Dados;";
        SQLiteDatabase db = dao.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Horario> datas = new ArrayList<>();
        while (c.moveToNext()){
            Horario data = new Horario();
            data.setId(c.getLong(c.getColumnIndex("id_dados")));
            data.setData(c.getString(c.getColumnIndex("data")));
            data.setHoraEntrada(c.getString(c.getColumnIndex("horaEntrada")));
            data.setsaidaIntervalo(c.getString(c.getColumnIndex("saidaIntervalo")));
            data.setVoltaIntervalo(c.getString(c.getColumnIndex("voltaIntervalo")));
            data.setHoraSaida(c.getString(c.getColumnIndex("horaSaida")));

            datas.add(data);
        }
        c.close();

        return datas;
    }

    public void altera(Horario horario) {
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDados(horario);

        String [] params = {horario.getId().toString()};
        db.update("Dados", dados, "id_dados=?",params);
    }

    public void deleta(Horario horario) {
        SQLiteDatabase db = dao.getWritableDatabase();
        String[]params = {String.valueOf(horario.getId())};
        db.delete("Dados","id_dados=?",params);
    }

    @Override
    public void close()  {
        dao.close();
    }
}

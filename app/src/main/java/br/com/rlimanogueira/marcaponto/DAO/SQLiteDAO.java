package br.com.rlimanogueira.marcaponto.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.rlimanogueira.marcaponto.Model.Horario;

public class SQLiteDAO extends SQLiteOpenHelper {


    public SQLiteDAO(Context context) {
        super(context, "Ponto", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Horario (id INTEGER PRIMARY KEY, data TEXT NOT NULL, horaEntrada TEXT, saidaIntervalo TEXT, voltaIntervalo TEXT, horaSaida TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Horario;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Horario horario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDados(horario);

        db.insert("Horario",null, dados);
    }

    private ContentValues pegaDados(Horario horario) {
        ContentValues dados = new ContentValues();
            dados.put("id", horario.getId());
            dados.put("data", horario.getData());
            dados.put("horaEntrada", horario.getHoraEntrada());
            dados.put("saidaIntervalo", horario.getsaidaIntervalo());
            dados.put("voltaIntervalo", horario.getvoltaIntervalo());
            dados.put("horaSaida", horario.getHoraSaida());
        return dados;
    }

    public List<Horario> buscaDatas() {
        String sql = "SELECT * FROM Horario;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Horario> datas = new ArrayList<>();
        while (c.moveToNext()){
            Horario data = new Horario();
            data.setId(c.getLong(c.getColumnIndex("id")));
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
        SQLiteDatabase db = getWritableDatabase();
            ContentValues dados = pegaDados(horario);

         String [] params = {horario.getId().toString()};
         db.update("Horario", dados, "id=?",params);
    }

    public void deleta(Horario horario) {
        SQLiteDatabase db = getWritableDatabase();
            String[]params = {String.valueOf(horario.getId())};
        db.delete("Horario","id=?",params);
    }

}

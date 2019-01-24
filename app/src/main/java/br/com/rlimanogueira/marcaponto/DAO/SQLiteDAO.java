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
        super(context, "Ponto", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlEmpresa = "CREATE TABLE Empresa (id_emp PRIMARY KEY, emp_nome TEXT NOT NULL, emp_cnpj TEXT);";

        String sqlUsuario = "CREATE TABLE Usuario (id_user PRIMARY KEY, user_nome TEXT NOT NULL, " +
                "user_setor TEXT, idEmpresa TEXT NOT NULL, FOREIGN KEY (idEmpresa) REFERENCES Empresa (id_emp));";

        String sqlDados = "CREATE TABLE Dados (id_dados INTEGER PRIMARY KEY, data TEXT NOT NULL, horaEntrada TEXT, saidaIntervalo TEXT, " +
                "voltaIntervalo TEXT, horaSaida TEXT, idUsuario TEXT NOT NULL, FOREIGN KEY (idUsuario) REFERENCES id_user);";

        db.execSQL(sqlEmpresa);
        db.execSQL(sqlUsuario);
        db.execSQL(sqlDados);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlEmpresa = "DROP TABLE IF EXISTS Empresa;";
        String sqlUsuario = "DROP TABLE IF EXISTS Usuario;";
        String sqlDados = "DROP TABLE IF EXISTS Dados;";
        db.execSQL(sqlDados);
        onCreate(db);
    }



}

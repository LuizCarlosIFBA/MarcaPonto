package br.com.rlimanogueira.marcaponto;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.rlimanogueira.marcaponto.DAO.SQLiteDAO;
import br.com.rlimanogueira.marcaponto.Model.Horario;

public class ListaDatasActivity extends AppCompatActivity {

    private Button botao;
    private ListView listaDatas;
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_datas);

        setTitle("Marca Ponto");

        vaiParaData();
        carregaLista();

        cliqueItemDaLista();

        registerForContextMenu(listaDatas);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    Horario horario = (Horario) listaDatas.getItemAtPosition(info.position);
                    SQLiteDAO dao = new SQLiteDAO(ListaDatasActivity.this);
                    dao.deleta(horario);
                    dao.close();
                carregaLista();
            return false;
            }
        });
        MenuItem visualizar = menu.add("Visualizar");
        visualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Horario horario = (Horario) listaDatas.getItemAtPosition(info.position);

                Intent vaiParaPonto = new Intent(ListaDatasActivity.this, PontoActivity.class);
                vaiParaPonto.putExtra("horario", horario);
                startActivity(vaiParaPonto);

                return false;
            }
        });
    }

    private void cliqueItemDaLista() {
        listaDatas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Horario horario = (Horario) listaDatas.getItemAtPosition(position);

                    Intent vaiParaPonto = new Intent(ListaDatasActivity.this, PontoActivity.class);
                        vaiParaPonto.putExtra("horario", horario);
                        startActivity(vaiParaPonto);

            }
        });
    }

    private void carregaLista() {
        SQLiteDAO dao = new SQLiteDAO(this);
        List<Horario> datas = dao.buscaDatas();
        dao.close();

        listaDatas = findViewById(R.id.listaDatas);
        ArrayAdapter<Horario> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listaDatas.setAdapter(adapter);
    }

    private void vaiParaData() {
        botao = findViewById(R.id.btn_novo);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaPonto = new Intent(ListaDatasActivity.this, PontoActivity.class);
                startActivity(irParaPonto);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }


}

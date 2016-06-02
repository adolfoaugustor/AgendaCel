package com.example.swr2d2.agendacel;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.content.*;

import android.database.sqlite.*;
import android.database.*;

import com.example.swr2d2.agendacel.database.DataBase;
import com.example.swr2d2.agendacel.dominio.Entidades.Contato;
import com.example.swr2d2.agendacel.dominio.RepositorioContato;


public class ActContato extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ImageButton btnAdcionar;
    private EditText edtPesquisa;
    private ListView lstContatos;
    private ArrayAdapter<Contato> adpContato;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.act_contato);

        btnAdcionar = (ImageButton)findViewById(R.id.btnAdicionar);
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);

        btnAdcionar.setOnClickListener(this);

        try{
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            adpContato = repositorioContato.BuscaContatos(this);

            lstContatos.setAdapter(adpContato);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar Banco!" + ex.getMessage() );
            dlg.setNeutralButton("Ok!", null);
            dlg.show();
        }

    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActCadContatos.class);
        startActivityForResult(it, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        adpContato = repositorioContato.BuscaContatos(this);

        lstContatos.setAdapter(adpContato);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contato contato = adpContato.getItem(position);

        Intent it = new Intent(this, ActCadContatos.class);

        it.putExtra("CONTATO", contato);
        startActivityForResult(it, 0);
    }
}
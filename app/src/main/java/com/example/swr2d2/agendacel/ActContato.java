package com.example.swr2d2.agendacel;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import android.content.*;

import android.database.sqlite.*;
import android.database.*;

import com.example.swr2d2.agendacel.app.MessageBox;
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
    private FiltraDados filtraDados;

    public static final String PAR_CONTATO = "CONTATO";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.act_contato);

        btnAdcionar = (ImageButton)findViewById(R.id.btnAdicionar);
        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
        lstContatos = (ListView)findViewById(R.id.lstContatos);

        btnAdcionar.setOnClickListener(this);
        lstContatos.setOnItemClickListener(this);

        try{
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            repositorioContato = new RepositorioContato(conn);

            adpContato = repositorioContato.BuscaContatos(this);

            lstContatos.setAdapter(adpContato);

            filtraDados = new FiltraDados(adpContato);
            edtPesquisa.addTextChangedListener(filtraDados);

        }catch(SQLException ex)
        {
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage() );
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null){
            conn.close();
        }
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActCadContatos.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpContato = repositorioContato.BuscaContatos(this);
        filtraDados.setArrayAdapter(adpContato);

        lstContatos.setAdapter(adpContato);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contato contato = adpContato.getItem(position);

        Intent it = new Intent(this, ActCadContatos.class);

        it.putExtra(PAR_CONTATO, contato);

        startActivityForResult(it, 0);
    }

    private class FiltraDados implements TextWatcher
    {

        private ArrayAdapter<Contato> arrayAdapter;

        private FiltraDados(ArrayAdapter<Contato> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }

        public void setArrayAdapter(ArrayAdapter<Contato> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}


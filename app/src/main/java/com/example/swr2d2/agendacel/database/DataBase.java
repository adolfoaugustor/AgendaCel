package com.example.swr2d2.agendacel.database;

import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context)
    {
        super(context, "AGENDA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateContato());
    }

    //Responsavel pela atalização das tabelas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

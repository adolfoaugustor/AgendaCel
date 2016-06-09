package com.example.swr2d2.agendacel.database;

public class ScriptSQL {

    public static String getCreateContato() {


        // StringBuilder usar para juntar uma string muito grande

        StringBuilder sqlbuilder = new StringBuilder();
        sqlbuilder.append(" CREATE TABLE IF NOT EXISTS CONTATO ( ");
        sqlbuilder.append(" _id                INTEGER       NOT NULL ");
        sqlbuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlbuilder.append(" NOME                 VARCHAR(200), ");
        sqlbuilder.append(" TELEFONE           VARCHAR (14), ");
        sqlbuilder.append(" TIPOTELEFONE       VARCHAR (1), ");
        sqlbuilder.append(" EMAIL              VARCHAR (255), ");
        sqlbuilder.append(" TIPOEMAIL          VARCHAR (1), ");
        sqlbuilder.append(" ENDERECO           VARCHAR (255), ");
        sqlbuilder.append(" TIPOENDERECO       VARCHAR (1), ");
        sqlbuilder.append(" DATASESPECIAIS     DATE, ");
        sqlbuilder.append(" TIPODATASESPECIAIS VARCHAR (1), ");
        sqlbuilder.append(" GRUPOS             VARCHAR (255) ");
        sqlbuilder.append(");");

        return sqlbuilder.toString();
    }
}


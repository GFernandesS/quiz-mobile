package com.example.projetoquiz.database;

public class ScriptDLL {


    public static String getCreateTableJogador(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS JOGADOR(");
        sql.append("CD_JOGADOR INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sql.append("USUARIO VARCHAR(250) NOT NULL , ");
        sql.append("PONTUACAO VARCHAR(250) NOT NULL  )");

        return sql.toString();

    }
}

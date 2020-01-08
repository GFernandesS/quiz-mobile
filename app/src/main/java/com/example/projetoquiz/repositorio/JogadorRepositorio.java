package com.example.projetoquiz.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projetoquiz.dominio.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorRepositorio {

    private SQLiteDatabase conexao;

    public JogadorRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Jogador jogador){
        ContentValues contentValues = new ContentValues();

        contentValues.put("USUARIO", jogador.usuario);
        contentValues.put("PONTUACAO", jogador.pontuacao);

        conexao.insertOrThrow("JOGADOR", null, contentValues);



    }

    public List<Jogador> buscarTodos() {

        List<Jogador> jogadores = new ArrayList<Jogador>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT USUARIO, PONTUACAO FROM JOGADOR ORDER BY USUARIO");

        Cursor cursor = conexao.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();


            do {

                Jogador jogador = new Jogador();

                jogador.usuario = cursor.getString(cursor.getColumnIndexOrThrow("USUARIO"));

                jogador.pontuacao = cursor.getString(cursor.getColumnIndexOrThrow("PONTUACAO"));

                jogadores.add(jogador);

                return jogadores;

            } while (cursor.moveToNext());


        }


        return jogadores;
    }
}

package com.example.projetoquiz;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.projetoquiz.database.DadosOpenHelper;
import com.example.projetoquiz.dominio.Jogador;
import com.example.projetoquiz.repositorio.JogadorRepositorio;

import java.util.List;

public class mostrarjogadorestodos extends AppCompatActivity {

    public RecyclerView recyclerView;

    public DadosOpenHelper dadosOpenHelper;

    public SQLiteDatabase conexao;

    public JogadorRepositorio jogadorRepositorio;

    public JogadorAdapter jogadorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarjogadorestodos);

        criarConexao();

        recyclerView = findViewById(R.id.rcJogadores);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        jogadorRepositorio = new JogadorRepositorio(conexao);

        List<Jogador> dados = jogadorRepositorio.buscarTodos();

        jogadorAdapter = new JogadorAdapter(dados);

        recyclerView.setAdapter(jogadorAdapter);








    }



    private void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();

            jogadorRepositorio = new JogadorRepositorio(conexao);


        } catch (Exception ex) {
            Log.d("ERRODECONEXAO", String.valueOf(ex));

        }
    }
}

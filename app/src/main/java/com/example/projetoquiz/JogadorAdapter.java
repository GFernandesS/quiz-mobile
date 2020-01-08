package com.example.projetoquiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoquiz.dominio.Jogador;

import org.w3c.dom.Text;

import java.util.List;

public class JogadorAdapter extends RecyclerView.Adapter<JogadorAdapter.ViewHolderJogador> {





    private List<Jogador> dados;

    public JogadorAdapter(List<Jogador> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public JogadorAdapter.ViewHolderJogador onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.lista_jogadores, viewGroup, false);

        ViewHolderJogador hold = new ViewHolderJogador(view);


        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull JogadorAdapter.ViewHolderJogador viewHolder, int i) {


        if(dados != null && dados.size() > 0) {

            Jogador jogador = dados.get(i);

            viewHolder.txtUsuario.setText(jogador.usuario);
            viewHolder.txtPontuacao.setText(jogador.pontuacao);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }


    public class ViewHolderJogador extends  RecyclerView.ViewHolder{


        public TextView txtUsuario;
        public TextView txtPontuacao;

        public ViewHolderJogador(@NonNull View itemView) {
            super(itemView);

            txtUsuario = (TextView) itemView.findViewById(R.id.txtNome);
            txtPontuacao = (TextView) itemView.findViewById(R.id.txtPontos);
        }
    }
}

package com.example.projetoquiz;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Dialog;

import com.example.projetoquiz.perguntas.perguntas.pergunta1_mat;

import java.util.ArrayList;

public class telaNovoJogo extends AppCompatActivity{

public AlertDialog.Builder builder;





private Button btnContinuar;

private EditText txtJogador;

public tocadorDeMusica tocaMusica = new tocadorDeMusica();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_novo_jogo);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setBackgroundResource(R.drawable.customizado);
    }


    public void onClickContinuar(View v) {
        final EditText jogador = findViewById(R.id.txtJogador);
        AlertDialog.Builder error = new AlertDialog.Builder(this);
        error.setTitle("ERRO!");
        error.setMessage("Você não definiu um nome para o jogador!");
        error.setIcon(R.drawable.imgerrordialog);
        error.setNeutralButton("OK", null);
        if (jogador.getText().toString().equals("")) {
            error.show();
        } else {



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.caixadedialogoinicio, null));

            final Dialog dialog = builder.show();
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();

                        Bundle parametro = new Bundle();
                        parametro.putString("JOGADOR", jogador.getText().toString());
                        Intent intent = new Intent(getApplicationContext(), pergunta1_mat.class);
                        intent.putExtras(parametro);
                        btnContinuar.setBackgroundResource(R.drawable.customizadocarregamento);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }


                }
            };

            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    handler.removeCallbacks(runnable);
                }
            });

            handler.postDelayed(runnable, 5000);


        }

    }


    public void onClickCancelar(View v){
        txtJogador = findViewById(R.id.txtJogador);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setBackgroundResource(R.drawable.customizadocarregamento);
        txtJogador.setText("");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }



}

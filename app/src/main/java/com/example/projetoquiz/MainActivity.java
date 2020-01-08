package com.example.projetoquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    public int[] contErro = new int[11];

    public int[] contTimer = new int[11];

    public Button btnIniciar;

    public tocadorDeMusica tocador = new tocadorDeMusica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setBackgroundResource(R.drawable.customizado);

        tocador.tocaMusica(getApplicationContext());




    }


    public void onClickIniciar(View v)  {

        Intent proximaTela = new Intent(getApplicationContext(), telaNovoJogo.class);


        btnIniciar.setBackgroundResource(R.drawable.customizadocarregamento);

        tocador.paraMusica();

        startActivity(proximaTela);
        finish();




    }


    public void onClickPontuacoes(View v){
        Intent intent = new Intent(getApplicationContext(), mostrarjogadorestodos.class);
        startActivity(intent);
    }
}

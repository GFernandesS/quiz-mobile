package com.example.projetoquiz.perguntas.perguntas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.projetoquiz.MainActivity;
import com.example.projetoquiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class pergunta1_mat extends AppCompatActivity {


    private Button btnEquilatero;
    private Button btnIsosceles;
    private Button btnEscaleno;
    private Chronometer cronometro;
    private MediaPlayer mediaPlayerFundo;
    private MediaPlayer mediaPlayer;

    private String jogador;

    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    Random random = new Random();
    public int i;
    public int contadora;
    private Intent it;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1_mat);

        Intent telaRecebedora = getIntent();
        Bundle parametroRecebedor = telaRecebedora.getExtras();

        if(parametroRecebedor != null){
            jogador = parametroRecebedor.getString("JOGADOR");
            Log.d("jogador", jogador);
        }

        contadora = 8;

        lista.add(0, "pergunta1_art");
        lista.add(1, "pergunta1_hist");
        lista.add(2, "pergunta1_quimica");
        lista.add(3, "pergunta2_art");
        lista.add(4, "pergunta2_bio");
        lista.add(5, "pergunta2_hist");
        lista.add(6, "pergunta2_mat");
        lista.add(7, "pergunta2_quimica");



        i = random.nextInt(contadora);
        contadora = contadora - 1;

        Log.d("Contador", String.valueOf(listaRespondidos.size()));

        try {
            it = new Intent(this, Class.forName("com.example.projetoquiz.perguntas.perguntas." + lista.get(i)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        btnEquilatero = findViewById(R.id.btnR1Mat1);
        btnEscaleno = findViewById(R.id.btnR3Mat1);
        btnIsosceles = findViewById(R.id.btnR2Mat1);

        cronometro = findViewById(R.id.contador);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();


        mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musmat);
        mediaPlayerFundo.start();







        btnEscaleno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnEscaleno.setBackgroundResource(R.drawable.customizadocarregamento);
                btnEquilatero.setEnabled(false);
                btnIsosceles.setEnabled(false);
                btnEscaleno.setClickable(false);
                btnEquilatero.setEnabled(false);


                cronometro.stop();

                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                mediaPlayer.start();




                Handler handler = new Handler();
                long delay = 3000; // tempo de delay em millisegundos
                handler.postDelayed(new Runnable() {
                    public void run() {

                        listaRespondidos.add("Matemática");

                        listaAcertos.add(1);

                        listaCronometros.add(cronometro.getText().toString());

                        Bundle parametro = new Bundle();
                        parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                        parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                        parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                        parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                        parametro.putString("JOGADOR", jogador);
                        parametro.putInt("CONTADORA", contadora);

                        it.putExtras(parametro);



                        mediaPlayerFundo.release();
                        startActivity(it);

                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                        finish();

                    }
                }, delay);






            }
        });

        btnEquilatero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEquilatero.setBackgroundResource(R.drawable.customizadoerrado);
                btnEscaleno.setBackgroundResource(R.drawable.customizadocarregamento);
                btnIsosceles.setEnabled(false);
                btnEscaleno.setClickable(false);
                btnEquilatero.setClickable(false);



                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                mediaPlayer.start();


                cronometro.stop();

                Handler handler = new Handler();
                long delay = 3000; // tempo de delay em millisegundos
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mediaPlayerFundo.release();

                        listaRespondidos.add("Matemática");

                        listaAcertos.add(0);

                        listaCronometros.add(cronometro.getText().toString());

                        Bundle parametro = new Bundle();
                        parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                        parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                        parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                        parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                        parametro.putString("JOGADOR", jogador);
                        parametro.putInt("CONTADORA", contadora);

                        it.putExtras(parametro);


                        startActivity(it);

                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();

                    }
                }, delay);






            }
        });

        btnIsosceles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnIsosceles.setBackgroundResource(R.drawable.customizadoerrado);
                btnEscaleno.setBackgroundResource(R.drawable.customizadocarregamento);
                btnEquilatero.setEnabled(false);
                btnEscaleno.setClickable(false);
                btnIsosceles.setClickable(false);




                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                mediaPlayer.start();


                cronometro.stop();



                Handler handler = new Handler();
                long delay = 3000; // tempo de delay em millisegundos
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mediaPlayerFundo.release();

                        listaRespondidos.add("Matemática");

                        listaAcertos.add(0);

                        listaCronometros.add(cronometro.getText().toString());

                        Log.d("ERRO", cronometro.getText().toString());



                        Bundle parametro = new Bundle();
                        parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                        parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                        parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                        parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                        parametro.putString("JOGADOR", jogador);
                        parametro.putInt("CONTADORA", contadora);

                        it.putExtras(parametro);

                        startActivity(it);

                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();


                    }
                }, delay);



            }
        });


    }

    }


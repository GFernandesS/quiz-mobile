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

import com.example.projetoquiz.MainActivity;
import com.example.projetoquiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class pergunta2_bio extends AppCompatActivity {

    private Button btnAG;
    private Button btnGT;
    private Button btnTC;
    private Chronometer cronometro;

    private MediaPlayer mediaPlayer;


    private Intent it;

    private int i;

    public List<String> lista = new ArrayList<>();

    public String jogador;

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    public int contadora;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2_bio);


        Intent telaRecebedora = getIntent();
        Bundle parametroRecebedor = telaRecebedora.getExtras();

        if(parametroRecebedor != null){
            lista = parametroRecebedor.getStringArrayList("LISTAJAPASSADOS");
            listaRespondidos = parametroRecebedor.getStringArrayList("RESPONDIDOS");
            listaCronometros = parametroRecebedor.getStringArrayList("CRONOMETRO");
            listaAcertos = parametroRecebedor.getIntegerArrayList("ACERTO");
            contadora = parametroRecebedor.getInt("CONTADORA");
            jogador = parametroRecebedor.getString("JOGADOR");

        }

        Log.d("jogador", jogador);

        Log.d("Contador", String.valueOf(listaRespondidos.size()));

        if(contadora > 1) {

            Random random = new Random();
            lista.remove("pergunta2_bio");

            contadora = contadora - 1;

            i = random.nextInt(contadora);


            btnGT = findViewById(R.id.btnR1Bio2);
            btnAG = findViewById(R.id.btnR2Bio2);
            btnTC = findViewById(R.id.btnR3Bio2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnAG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnAG.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnGT.setEnabled(false);
                    btnTC.setEnabled(false);
                    btnAG.setClickable(false);

                    telaPrincipal.contErro[5] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaAcertos.add(1);
                            listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("Biologia");


                            try {
                                it = new Intent(getApplicationContext(), Class.forName("com.example.projetoquiz.perguntas.perguntas." + lista.get(i)));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            Bundle parametro = new Bundle();
                            parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                            parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                            parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                            parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                            parametro.putInt("CONTADORA", contadora);
                            parametro.putString("JOGADOR", jogador);

                            it.putExtras(parametro);

                            startActivity(it);

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            finish();
                        }
                    }, delay);


                }
            });

            btnTC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnTC.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAG.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnGT.setEnabled(false);
                    btnAG.setClickable(false);
                    btnTC.setClickable(false);


                    telaPrincipal.contErro[5] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("Biologia");


                            try {
                                it = new Intent(getApplicationContext(), Class.forName("com.example.projetoquiz.perguntas.perguntas." + lista.get(i)));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            Bundle parametro = new Bundle();
                            parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                            parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                            parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                            parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                            parametro.putInt("CONTADORA", contadora);
                            parametro.putString("JOGADOR", jogador);


                            it.putExtras(parametro);

                            startActivity(it);

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            finish();
                        }
                    }, delay);


                }
            });

            btnGT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnGT.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAG.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnTC.setEnabled(false);
                    btnGT.setClickable(false);
                    btnAG.setClickable(false);

                    telaPrincipal.contErro[5] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Biologia");


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {


                            try {
                                it = new Intent(getApplicationContext(), Class.forName("com.example.projetoquiz.perguntas.perguntas." + lista.get(i)));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            Bundle parametro = new Bundle();
                            parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                            parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                            parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                            parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                            parametro.putInt("CONTADORA", contadora);
                            parametro.putString("JOGADOR", jogador);


                            it.putExtras(parametro);
                            startActivity(it);

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


                            finish();
                        }
                    }, delay);


                }
            });
        }
        else{


            btnGT = findViewById(R.id.btnR1Bio2);
            btnAG = findViewById(R.id.btnR2Bio2);
            btnTC = findViewById(R.id.btnR3Bio2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnAG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnAG.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnGT.setEnabled(false);
                    btnTC.setEnabled(false);
                    btnAG.setClickable(false);

                    telaPrincipal.contErro[5] = 1;

                    cronometro.stop();

                    listaAcertos.add(1);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Biologia");

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Log.d("ERRO", String.valueOf(listaRespondidos.size()));



                                it = new Intent(getApplicationContext(), com.example.projetoquiz.resultado.class);

                            Bundle parametro = new Bundle();
                            parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                            parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                            parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                            parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                            parametro.putInt("CONTADORA", contadora);
                            parametro.putString("JOGADOR", jogador);


                            it.putExtras(parametro);

                            startActivity(it);

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            finish();
                        }
                    }, delay);


                }
            });

            btnTC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnTC.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAG.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnGT.setEnabled(false);
                    btnAG.setClickable(false);
                    btnTC.setClickable(false);


                    telaPrincipal.contErro[5] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Biologia");

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            Log.d("ERRO", String.valueOf(listaRespondidos.size()));



                                it = new Intent(getApplicationContext(), com.example.projetoquiz.resultado.class);

                            Bundle parametro = new Bundle();
                            parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                            parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                            parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                            parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                            parametro.putInt("CONTADORA", contadora);
                            parametro.putString("JOGADOR", jogador);


                            it.putExtras(parametro);

                            startActivity(it);

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            finish();
                        }
                    }, delay);


                }
            });

            btnGT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnGT.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAG.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnTC.setEnabled(false);
                    btnGT.setClickable(false);
                    btnAG.setClickable(false);

                    telaPrincipal.contErro[5] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();
                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Biologia");

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Log.d("ERRO", String.valueOf(listaRespondidos.size()));



                                it = new Intent(getApplicationContext(), com.example.projetoquiz.resultado.class);

                            Bundle parametro = new Bundle();
                            parametro.putIntegerArrayList("ACERTO", (ArrayList<Integer>) listaAcertos);
                            parametro.putStringArrayList("RESPONDIDOS", (ArrayList<String>) listaRespondidos);
                            parametro.putStringArrayList("CRONOMETRO", (ArrayList<String>) listaCronometros);
                            parametro.putStringArrayList("LISTAJAPASSADOS",(ArrayList<String>) lista);
                            parametro.putInt("CONTADORA", contadora);
                            parametro.putString("JOGADOR", jogador);


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

}

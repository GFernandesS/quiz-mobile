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

public class pergunta1_hist extends AppCompatActivity {

    private Button btn1972;
    private Button btn1968;
    private Button btn1969;
    private Chronometer cronometro;

    private Intent it;

    private int i;


    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    public String jogador;

    public MediaPlayer mediaPlayerFundo;

    public int contadora;



    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1_hist);


        Intent telaRecebedora = getIntent();
        Bundle parametroRecebedor = telaRecebedora.getExtras();

        if(parametroRecebedor != null){
            lista = parametroRecebedor.getStringArrayList("LISTAJAPASSADOS");
            listaRespondidos = parametroRecebedor.getStringArrayList("RESPONDIDOS");
            listaCronometros = parametroRecebedor.getStringArrayList("CRONOMETRO");
            listaAcertos = parametroRecebedor.getIntegerArrayList("ACERTO");
            contadora = parametroRecebedor.getInt("CONTADORA");
            jogador = parametroRecebedor.getString("JOGADOR");
            Log.d("jogador", jogador);

        }



        Log.d("Contador", String.valueOf(listaRespondidos.size()));

        if (contadora > 1) {

            Random random = new Random();
            lista.remove("pergunta1_hist");

            contadora = contadora - 1;

            i = random.nextInt(contadora);

            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicahistoria);
            mediaPlayerFundo.start();


            btn1972 = findViewById(R.id.btnR1Hist1);
            btn1968 = findViewById(R.id.btnR2Hist1);
            btn1969 = findViewById(R.id.btnR3Hist1);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btn1969.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btn1969.setBackgroundResource(R.drawable.customizadocarregamento);
                    btn1968.setEnabled(false);
                    btn1972.setEnabled(false);
                    btn1969.setClickable(false);


                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaRespondidos.add("História");
                            listaAcertos.add(1);
                            listaCronometros.add(cronometro.getText().toString());
                            mediaPlayerFundo.release();


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

            btn1968.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn1968.setBackgroundResource(R.drawable.customizadoerrado);
                    btn1969.setBackgroundResource(R.drawable.customizadocarregamento);
                    btn1972.setEnabled(false);
                    btn1969.setClickable(false);
                    btn1968.setClickable(false);


                    telaPrincipal.contErro[8] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaRespondidos.add("História");
                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            mediaPlayerFundo.release();

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

            btn1972.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btn1972.setBackgroundResource(R.drawable.customizadoerrado);
                    btn1969.setBackgroundResource(R.drawable.customizadocarregamento);
                    btn1968.setEnabled(false);
                    btn1972.setClickable(false);
                    btn1969.setClickable(false);

                    telaPrincipal.contErro[8] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            listaRespondidos.add("História");
                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            mediaPlayerFundo.release();

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
            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicahistoria);
            mediaPlayerFundo.start();


                btn1972 = findViewById(R.id.btnR1Hist1);
                btn1968 = findViewById(R.id.btnR2Hist1);
                btn1969 = findViewById(R.id.btnR3Hist1);

                cronometro = findViewById(R.id.contador);
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.start();


                final MainActivity telaPrincipal = new MainActivity();

                btn1969.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        btn1969.setBackgroundResource(R.drawable.customizadocarregamento);
                        btn1968.setEnabled(false);
                        btn1972.setEnabled(false);
                        btn1969.setClickable(false);


                        cronometro.stop();

                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                        mediaPlayer.start();
                        listaRespondidos.add("História");
                        listaAcertos.add(1);
                        listaCronometros.add(cronometro.getText().toString());

                        Handler handler = new Handler();
                        long delay = 3000; // tempo de delay em millisegundos
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                mediaPlayerFundo.release();

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

                btn1968.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn1968.setBackgroundResource(R.drawable.customizadoerrado);
                        btn1969.setBackgroundResource(R.drawable.customizadocarregamento);
                        btn1972.setEnabled(false);
                        btn1969.setClickable(false);
                        btn1968.setClickable(false);


                        telaPrincipal.contErro[8] = 0;

                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                        mediaPlayer.start();



                        cronometro.stop();

                        listaRespondidos.add("História");
                        listaAcertos.add(0);
                        listaCronometros.add(cronometro.getText().toString());

                        Handler handler = new Handler();
                        long delay = 3000; // tempo de delay em millisegundos
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                mediaPlayerFundo.release();

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

                btn1972.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        btn1972.setBackgroundResource(R.drawable.customizadoerrado);
                        btn1969.setBackgroundResource(R.drawable.customizadocarregamento);
                        btn1968.setEnabled(false);
                        btn1972.setClickable(false);
                        btn1969.setClickable(false);

                        telaPrincipal.contErro[8] = 0;


                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                        mediaPlayer.start();


                        cronometro.stop();

                        listaRespondidos.add("História");
                        listaAcertos.add(0);
                        listaCronometros.add(cronometro.getText().toString());


                        Handler handler = new Handler();
                        long delay = 3000; // tempo de delay em millisegundos
                        handler.postDelayed(new Runnable() {
                            public void run() {


                                mediaPlayerFundo.release();

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

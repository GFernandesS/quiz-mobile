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

public class pergunta2_quimica extends AppCompatActivity {

    private Button btnAlcalino;
    private Button btnAcido;
    private Button btnSalinico;
    private Chronometer cronometro;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerFundo;


    private Intent it;

    private int i;

    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    private String jogador;

    public int contadora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2_quimica);



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
            lista.remove("pergunta2_quimica");

            contadora = contadora - 1;

            i = random.nextInt(contadora);


            btnAlcalino = findViewById(R.id.btnR1Qui2);
            btnAcido = findViewById(R.id.btnR2Qui2);
            btnSalinico = findViewById(R.id.btnR3Qui2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musquim);
            mediaPlayerFundo.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnAlcalino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnAlcalino.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnAcido.setEnabled(false);
                    btnSalinico.setEnabled(false);
                    btnAlcalino.setClickable(false);

                    telaPrincipal.contErro[3] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            mediaPlayerFundo.release();

                           listaAcertos.add(1);
                           listaCronometros.add(cronometro.getText().toString());
                           listaRespondidos.add("Química");


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

            btnAcido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnAcido.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAlcalino.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnSalinico.setEnabled(false);
                    btnAcido.setClickable(false);
                    btnAlcalino.setClickable(false);


                    telaPrincipal.contErro[3] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            mediaPlayerFundo.release();

                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("Química");


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

            btnSalinico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnSalinico.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAlcalino.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnAcido.setEnabled(false);
                    btnAlcalino.setClickable(false);
                    btnSalinico.setClickable(false);

                    telaPrincipal.contErro[3] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            mediaPlayerFundo.release();

                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("Química");


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

            btnAlcalino = findViewById(R.id.btnR1Qui2);
            btnAcido = findViewById(R.id.btnR2Qui2);
            btnSalinico = findViewById(R.id.btnR3Qui2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musquim);
            mediaPlayerFundo.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnAlcalino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    btnAlcalino.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnAcido.setEnabled(false);
                    btnSalinico.setEnabled(false);
                    btnAlcalino.setClickable(false);

                    telaPrincipal.contErro[3] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();

                    listaRespondidos.add("Química");
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

            btnAcido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnAcido.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAlcalino.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnSalinico.setEnabled(false);
                    btnAcido.setClickable(false);
                    btnAlcalino.setClickable(false);


                    telaPrincipal.contErro[3] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Química");

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

            btnSalinico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnSalinico.setBackgroundResource(R.drawable.customizadoerrado);
                    btnAlcalino.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnAcido.setEnabled(false);
                    btnAlcalino.setClickable(false);
                    btnSalinico.setClickable(false);

                    telaPrincipal.contErro[3] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaRespondidos.add("Química");
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

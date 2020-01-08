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

public class pergunta2_hist extends AppCompatActivity {


    private Button btnP1;
    private Button btnP2;
    private Button btnP3;
    private Chronometer cronometro;

    private MediaPlayer mediaPlayer;


    private Intent it;

    private int i;


    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();

    public String jogador;

    public List<Integer> listaAcertos = new ArrayList<Integer>();

    public int contadora;

    public MediaPlayer mediaPlayerFundo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2_hist);


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

        if(contadora > 1) {


            Random random = new Random();
           lista.remove("pergunta2_hist");

           contadora = contadora - 1;

            i = random.nextInt(contadora);

            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicahistoria);
            mediaPlayerFundo.start();


            btnP1 = findViewById(R.id.btnR1Hist2);
            btnP2 = findViewById(R.id.btnR2Hist2);
            btnP3 = findViewById(R.id.btnR3Hist2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnP2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnP2.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnP1.setEnabled(false);
                    btnP3.setEnabled(false);
                    btnP2.setClickable(false);

                    telaPrincipal.contErro[9] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();




                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaAcertos.add(1);
                            listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("História");

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

            btnP1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnP1.setBackgroundResource(R.drawable.customizadoerrado);
                    btnP2.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnP3.setEnabled(false);
                    btnP2.setClickable(false);
                    btnP1.setClickable(false);


                    telaPrincipal.contErro[9] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            mediaPlayerFundo.release();
                            listaRespondidos.add("História");

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

            btnP3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnP3.setBackgroundResource(R.drawable.customizadoerrado);
                    btnP2.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnP1.setEnabled(false);
                    btnP2.setClickable(false);
                    btnP3.setClickable(false);

                    telaPrincipal.contErro[8] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaAcertos.add(0);
                            listaCronometros.add(cronometro.getText().toString());
                            mediaPlayerFundo.release();
                            listaRespondidos.add("História");

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

            btnP1 = findViewById(R.id.btnR1Hist2);
            btnP2 = findViewById(R.id.btnR2Hist2);
            btnP3 = findViewById(R.id.btnR3Hist2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnP2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnP2.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnP1.setEnabled(false);
                    btnP3.setEnabled(false);
                    btnP2.setClickable(false);

                    telaPrincipal.contErro[9] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();

                    listaAcertos.add(1);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("História");


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

            btnP1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnP1.setBackgroundResource(R.drawable.customizadoerrado);
                    btnP2.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnP3.setEnabled(false);
                    btnP2.setClickable(false);
                    btnP1.setClickable(false);


                    telaPrincipal.contErro[9] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();








                    cronometro.stop();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("História");

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

            btnP3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnP3.setBackgroundResource(R.drawable.customizadoerrado);
                    btnP2.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnP1.setEnabled(false);
                    btnP2.setClickable(false);
                    btnP3.setClickable(false);

                    telaPrincipal.contErro[8] = 0;



                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("História");


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {


                            mediaPlayerFundo.release();

                            Log.d("jogador", jogador);

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

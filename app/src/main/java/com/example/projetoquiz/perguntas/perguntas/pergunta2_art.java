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

public class pergunta2_art extends AppCompatActivity {

    private Button btnVeermer;
    private Button btnRembrandt;
    private Button btnDelacroix;
    private Chronometer cronometro;

    private MediaPlayer mediaPlayer;

    private Intent it;

    private int i;

    public List<String> lista = new ArrayList<>();

     private String jogador;

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<>();

    private MediaPlayer mediaPlayerFundo;

    public int contadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2_art);


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


        Log.d("CRONOMETRO", String.valueOf(listaCronometros.size()));

        Log.d("Contador", String.valueOf(listaRespondidos.size()));
        if(contadora > 1) {

            Random random = new Random();
            lista.remove("pergunta2_art");

           contadora = contadora - 1;

            i = random.nextInt(contadora);


            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicaartes);
            mediaPlayerFundo.start();

            btnVeermer = findViewById(R.id.btnR1Art2);
            btnRembrandt = findViewById(R.id.btnR2Art2);
            btnDelacroix = findViewById(R.id.btnR3Art2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnRembrandt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnRembrandt.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnDelacroix.setEnabled(false);
                    btnVeermer.setEnabled(false);
                    btnRembrandt.setClickable(false);

                    telaPrincipal.contErro[7] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaAcertos.add(1);
                            listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("Artes");
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

            btnDelacroix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnDelacroix.setBackgroundResource(R.drawable.customizadoerrado);
                    btnRembrandt.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnVeermer.setEnabled(false);
                    btnDelacroix.setClickable(false);
                    btnRembrandt.setClickable(false);


                    telaPrincipal.contErro[7] = 0;

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
                            listaRespondidos.add("Artes");

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

            btnVeermer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnVeermer.setBackgroundResource(R.drawable.customizadoerrado);
                    btnRembrandt.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnDelacroix.setEnabled(false);
                    btnVeermer.setClickable(false);
                    btnRembrandt.setClickable(false);

                    telaPrincipal.contErro[7] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                           listaAcertos.add(0);
                           listaCronometros.add(cronometro.getText().toString());
                            listaRespondidos.add("Artes");
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

            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicaartes);
            mediaPlayerFundo.start();


            btnVeermer = findViewById(R.id.btnR1Art2);
            btnRembrandt = findViewById(R.id.btnR2Art2);
            btnDelacroix = findViewById(R.id.btnR3Art2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnRembrandt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnRembrandt.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnDelacroix.setEnabled(false);
                    btnVeermer.setEnabled(false);
                    btnRembrandt.setClickable(false);

                    telaPrincipal.contErro[7] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();

                    listaAcertos.add(1);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Artes");


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

            btnDelacroix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnDelacroix.setBackgroundResource(R.drawable.customizadoerrado);
                    btnRembrandt.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnVeermer.setEnabled(false);
                    btnDelacroix.setClickable(false);
                    btnRembrandt.setClickable(false);


                    telaPrincipal.contErro[7] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Artes");

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

            btnVeermer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnVeermer.setBackgroundResource(R.drawable.customizadoerrado);
                    btnRembrandt.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnDelacroix.setEnabled(false);
                    btnVeermer.setClickable(false);
                    btnRembrandt.setClickable(false);

                    telaPrincipal.contErro[7] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Artes");


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

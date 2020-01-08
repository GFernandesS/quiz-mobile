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

public class pergunta1_art extends AppCompatActivity {

    private Button btnExpressionismo;
    private Button btnSurrealismo;
    private Button btnFuturismo;
    private Chronometer cronometro;

    private MediaPlayer mediaPlayer;

    private Intent it;

    private int i;


    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    private String jogador;

    MediaPlayer mediaPlayerFundo;

    public int contadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1_art);




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

        mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicaartes);
        mediaPlayerFundo.start();
        if (contadora > 1) {

            Random random = new Random();
            lista.remove("pergunta1_art");

            contadora = contadora - 1;

            i = random.nextInt(contadora);





            btnExpressionismo = findViewById(R.id.btnR1Art1);
            btnSurrealismo = findViewById(R.id.btnR2Art1);
            btnFuturismo = findViewById(R.id.btnR3Art1);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnExpressionismo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnExpressionismo.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnFuturismo.setEnabled(false);
                    btnSurrealismo.setEnabled(false);
                    btnExpressionismo.setClickable(false);


                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaRespondidos.add("Artes");
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

            btnSurrealismo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSurrealismo.setBackgroundResource(R.drawable.customizadoerrado);
                    btnExpressionismo.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnFuturismo.setEnabled(false);
                    btnExpressionismo.setClickable(false);
                    btnSurrealismo.setClickable(false);


                    telaPrincipal.contErro[6] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaRespondidos.add("Artes");
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

            btnFuturismo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnFuturismo.setBackgroundResource(R.drawable.customizadoerrado);
                    btnExpressionismo.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnSurrealismo.setEnabled(false);
                    btnFuturismo.setClickable(false);
                    btnExpressionismo.setClickable(false);

                    telaPrincipal.contErro[6] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            listaRespondidos.add("Artes");
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
        else{

            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musicaartes);
            mediaPlayerFundo.start();

            btnExpressionismo = findViewById(R.id.btnR1Art1);
            btnSurrealismo = findViewById(R.id.btnR2Art1);
            btnFuturismo = findViewById(R.id.btnR3Art1);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnExpressionismo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnExpressionismo.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnFuturismo.setEnabled(false);
                    btnSurrealismo.setEnabled(false);
                    btnExpressionismo.setClickable(false);

                    telaPrincipal.contErro[6] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();

                    listaRespondidos.add("Artes");
                    listaAcertos.add(1);
                    listaCronometros.add(cronometro.getText().toString());


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            Log.d("ERRO", String.valueOf(listaRespondidos.size()));



                            mediaPlayerFundo.release();

                             it = new Intent(getApplicationContext(), com.example.projetoquiz.resultado.class);

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

            btnSurrealismo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSurrealismo.setBackgroundResource(R.drawable.customizadoerrado);
                    btnExpressionismo.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnFuturismo.setEnabled(false);
                    btnExpressionismo.setClickable(false);
                    btnSurrealismo.setClickable(false);



                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaRespondidos.add("Artes");
                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {


                            Log.d("ERRO", String.valueOf(listaRespondidos.size()));


                            mediaPlayerFundo.release();
                            it = new Intent(getApplicationContext(), com.example.projetoquiz.resultado.class);


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

            btnFuturismo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnFuturismo.setBackgroundResource(R.drawable.customizadoerrado);
                    btnExpressionismo.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnSurrealismo.setEnabled(false);
                    btnFuturismo.setClickable(false);
                    btnExpressionismo.setClickable(false);


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaRespondidos.add("Artes");
                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());


                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {


                            Log.d("ERRO", String.valueOf(listaRespondidos.size()));


                            mediaPlayerFundo.release();

                            it = new Intent(getApplicationContext(), com.example.projetoquiz.resultado.class);


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
}

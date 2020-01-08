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

import java.sql.ParameterMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class pergunta2_mat extends AppCompatActivity {

    private Button btnCentoOitenta;
    private Button btnSetentaQuatro;
    private Button btnCinquenta;
    private Chronometer cronometro;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerFundo;


    private Intent it;

    private String jogador;

    private int i;

    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    public int contadora;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2_mat);


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

        if (contadora > 1) {


            Random random = new Random();
            lista.remove("pergunta2_mat");

            contadora =  contadora - 1;

            i = random.nextInt(contadora);


            btnCentoOitenta = findViewById(R.id.btnR1Mat2);
            btnSetentaQuatro = findViewById(R.id.btnR2Mat2);
            btnCinquenta = findViewById(R.id.btnR3Mat2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musmat);
            mediaPlayerFundo.start();

            final MainActivity telaPrincipal = new MainActivity();

            btnSetentaQuatro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnSetentaQuatro.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnCentoOitenta.setEnabled(false);
                    btnCinquenta.setEnabled(false);
                    btnSetentaQuatro.setClickable(false);

                    telaPrincipal.contErro[1] = 1;

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
                            listaRespondidos.add("Matemática");


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


            btnCentoOitenta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnCentoOitenta.setBackgroundResource(R.drawable.customizadoerrado);
                    btnSetentaQuatro.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnCinquenta.setEnabled(false);
                    btnSetentaQuatro.setClickable(false);
                    btnCentoOitenta.setClickable(false);


                    telaPrincipal.contErro[1] = 0;

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
                            listaRespondidos.add("Matemática");


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

            btnCinquenta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnCinquenta.setBackgroundResource(R.drawable.customizadoerrado);
                    btnSetentaQuatro.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnCentoOitenta.setEnabled(false);
                    btnSetentaQuatro.setClickable(false);
                    btnCinquenta.setClickable(false);

                    telaPrincipal.contErro[1] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();


                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Matemática");

                    Handler handler = new Handler();
                    long delay = 3000; // tempo de delay em millisegundos
                    handler.postDelayed(new Runnable() {
                        public void run() {
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


            btnCentoOitenta = findViewById(R.id.btnR1Mat2);
            btnSetentaQuatro = findViewById(R.id.btnR2Mat2);
            btnCinquenta = findViewById(R.id.btnR3Mat2);

            cronometro = findViewById(R.id.contador);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();


            mediaPlayerFundo = MediaPlayer.create(getApplicationContext(), R.raw.musmat);
            mediaPlayerFundo.start();


            final MainActivity telaPrincipal = new MainActivity();

            btnSetentaQuatro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnSetentaQuatro.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnCentoOitenta.setEnabled(false);
                    btnCinquenta.setEnabled(false);
                    btnSetentaQuatro.setClickable(false);

                    telaPrincipal.contErro[1] = 1;

                    cronometro.stop();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.efeitosucesso);
                    mediaPlayer.start();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Matemática");

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


            btnCentoOitenta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnCentoOitenta.setBackgroundResource(R.drawable.customizadoerrado);
                    btnSetentaQuatro.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnCinquenta.setEnabled(false);
                    btnSetentaQuatro.setClickable(false);
                    btnCentoOitenta.setClickable(false);

                    telaPrincipal.contErro[1] = 0;

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaAcertos.add(0);
                    listaCronometros.add(cronometro.getText().toString());
                    listaRespondidos.add("Matemática");

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

            btnCinquenta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnCinquenta.setBackgroundResource(R.drawable.customizadoerrado);
                    btnSetentaQuatro.setBackgroundResource(R.drawable.customizadocarregamento);
                    btnCentoOitenta.setEnabled(false);
                    btnSetentaQuatro.setClickable(false);
                    btnCinquenta.setClickable(false);


                    telaPrincipal.contErro[1] = 0;


                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mediaPlayer.start();


                    cronometro.stop();

                    listaAcertos.add(0);

                    listaCronometros.add(cronometro.getText().toString());

                    listaRespondidos.add("Matemática");


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

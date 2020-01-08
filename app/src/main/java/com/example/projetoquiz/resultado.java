package com.example.projetoquiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetoquiz.database.DadosOpenHelper;
import com.example.projetoquiz.dominio.Jogador;
import com.example.projetoquiz.perguntas.perguntas.pergunta1_mat;
import com.example.projetoquiz.repositorio.JogadorRepositorio;

import java.util.ArrayList;
import java.util.List;

public class resultado extends AppCompatActivity {


    public List<String> lista = new ArrayList<>();

    public List<String> listaRespondidos = new ArrayList<>();
    public List<String> listaCronometros = new ArrayList<>();
    public List<Integer> listaAcertos = new ArrayList<Integer>();

    private ImageView imgMat1;
    private ImageView imgCertResp1;
    private TextView txtcro1;

    private ImageView imgMat2;
    private ImageView imgCertResp2;
    private TextView txtcro2;


    private ImageView imgMat3;
    private ImageView imgCertResp3;
    private TextView txtcro3;


    private ImageView imgMat4;
    private ImageView imgCertResp4;
    private TextView txtcro4;


    private ImageView imgMat5;
    private ImageView imgCertResp5;
    private TextView txtcro5;


    private ImageView imgMat6;
    private ImageView imgCertResp6;
    private TextView txtcro6;


    private ImageView imgMat7;
    private ImageView imgCertResp7;
    private TextView txtcro7;


    private ImageView imgMat8;
    private ImageView imgCertResp8;
    private TextView txtcro8;


    private ImageView imgMat9;
    private ImageView imgCertResp9;
    private TextView txtcro9;


    private ImageView imgMat10;
    private ImageView imgCertResp10;
    private TextView txtcro10;

    private String jogador2 = null;

    public String usuario;

    private int contadora = 0;


    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    private JogadorRepositorio jogadorRepositorio;



    private Jogador jogador = new Jogador();

    public MediaPlayer mediaPlayerFundo;

    private TextView txtPontuacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.trovoada);
        mediaPlayer.start();

        Intent telaRecebedora = getIntent();
        Bundle parametroRecebedor = telaRecebedora.getExtras();

        imgMat1 = findViewById(R.id.imgMatResp1);
        imgMat2 = findViewById(R.id.imgMatResp2);
        imgMat3 = findViewById(R.id.imgMatResp3);
        imgMat4 = findViewById(R.id.imgMatResp4);
        imgMat5 = findViewById(R.id.imgMatResp5);
        imgMat6 = findViewById(R.id.imgMatResp6);
        imgMat7 = findViewById(R.id.imgMatResp7);
        imgMat8 = findViewById(R.id.imgMatResp8);
        imgMat9 = findViewById(R.id.imgMatResp9);


        imgCertResp1 = findViewById(R.id.imgCertResp1);
        imgCertResp2 = findViewById(R.id.imgCertResp2);
        imgCertResp3 = findViewById(R.id.imgCertResp3);
        imgCertResp4 = findViewById(R.id.imgCertResp4);
        imgCertResp5 = findViewById(R.id.imgCertResp5);
        imgCertResp6 = findViewById(R.id.imgCertResp6);
        imgCertResp7 = findViewById(R.id.imgCertResp7);
        imgCertResp8 = findViewById(R.id.imgCertResp8);
        imgCertResp9 = findViewById(R.id.imgCertResp9);


        txtcro1 = findViewById(R.id.txtcro1);
        txtcro2 = findViewById(R.id.txtcro2);
        txtcro3 = findViewById(R.id.txtcro3);
        txtcro4 = findViewById(R.id.txtcro4);
        txtcro5 = findViewById(R.id.txtcro5);
        txtcro6 = findViewById(R.id.txtcro6);
        txtcro7 = findViewById(R.id.txtcro7);
        txtcro8 = findViewById(R.id.txtcro8);
        txtcro9 = findViewById(R.id.txtcro9);

        txtPontuacao = findViewById(R.id.txtPontuacao);

        criarConexao();





        if (parametroRecebedor != null) {
            listaRespondidos = parametroRecebedor.getStringArrayList("RESPONDIDOS");
            listaCronometros = parametroRecebedor.getStringArrayList("CRONOMETRO");
            listaAcertos = parametroRecebedor.getIntegerArrayList("ACERTO");
            contadora = parametroRecebedor.getInt("CONTADORA");
            jogador2 = parametroRecebedor.getString("JOGADOR");
        }

        usuario = jogador2;

        Log.d("Contador", String.valueOf(listaRespondidos.size()));

        if (listaRespondidos.get(0).equals("Matemática")) {
            imgMat1.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(0).equals("Artes")) {
                imgMat1.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(0).equals("Biologia")) {
                    imgMat1.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(0).equals("História")) {
                        imgMat1.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(0).equals("Química")) {
                            imgMat1.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }


        txtcro1.setText(listaCronometros.get(0));
        txtcro2.setText(listaCronometros.get(1));
        txtcro3.setText(listaCronometros.get(2));
        txtcro4.setText(listaCronometros.get(3));
        txtcro5.setText(listaCronometros.get(4));
        txtcro6.setText(listaCronometros.get(5));
        txtcro7.setText(listaCronometros.get(6));
        txtcro8.setText(listaCronometros.get(7));

        if (listaRespondidos.get(1).equals("Matemática")) {
            imgMat2.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(1).equals("Artes")) {
                imgMat2.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(1).equals("Biologia")) {
                    imgMat2.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(1).equals("História")) {
                        imgMat2.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(1).equals("Química")) {
                            imgMat2.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }

        if (listaRespondidos.get(2).equals("Matemática")) {
            imgMat3.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(2).equals("Artes")) {
                imgMat3.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(2).equals("Biologia")) {
                    imgMat3.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(2).equals("História")) {
                        imgMat3.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(2).equals("Química")) {
                            imgMat3.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }

        if (listaRespondidos.get(3).equals("Matemática")) {
            imgMat4.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(3).equals("Artes")) {
                imgMat4.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(3).equals("Biologia")) {
                    imgMat4.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(3).equals("História")) {
                        imgMat4.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(3).equals("Química")) {
                            imgMat4.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }

        if (listaRespondidos.get(4).equals("Matemática")) {
            imgMat5.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(4).equals("Artes")) {
                imgMat5.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(4).equals("Biologia")) {
                    imgMat5.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(4).equals("História")) {
                        imgMat5.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(4).equals("Química")) {
                            imgMat5.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }

        if (listaRespondidos.get(5).equals("Matemática")) {
            imgMat6.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(5).equals("Artes")) {
                imgMat6.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(5).equals("Biologia")) {
                    imgMat6.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(5).equals("História")) {
                        imgMat6.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(5).equals("Química")) {
                            imgMat6.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }

        if (listaRespondidos.get(6).equals("Matemática")) {
            imgMat7.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(6).equals("Artes")) {
                imgMat7.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(6).equals("Biologia")) {
                    imgMat7.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(6).equals("História")) {
                        imgMat7.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(6).equals("Química")) {
                            imgMat7.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }


        if (listaRespondidos.get(7).equals("Matemática")) {
            imgMat8.setImageResource(R.drawable.iconcormat);
        } else {
            if (listaRespondidos.get(7).equals("Artes")) {
                imgMat8.setImageResource(R.drawable.iconarteresultado);
            } else {
                if (listaRespondidos.get(7).equals("Biologia")) {
                    imgMat8.setImageResource(R.drawable.iconcorbio);
                } else {
                    if (listaRespondidos.get(7).equals("História")) {
                        imgMat8.setImageResource(R.drawable.iconcorhist);
                    } else {
                        if (listaRespondidos.get(7).equals("Química")) {
                            imgMat8.setImageResource(R.drawable.iconquiresultado);
                        }
                    }
                }
            }


        }









        contadora = 0;


        if(listaAcertos.get(0) == 0){
            imgCertResp1.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp1.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(1) == 0){
            imgCertResp2.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp2.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(2) == 0){
            imgCertResp3.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp3.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(3) == 0){
            imgCertResp4.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp4.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(4) == 0){
            imgCertResp5.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp5.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(5) == 0){
            imgCertResp6.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp6.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(6) == 0){
            imgCertResp7.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp7.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }
        if(listaAcertos.get(7) == 0){
            imgCertResp8.setImageResource(R.drawable.iconcorerro);
        }
        else{
            imgCertResp8.setImageResource(R.drawable.iconcerto);
            contadora = contadora + 1;
        }




        txtPontuacao.setText(contadora + " pontos");










    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();

            jogadorRepositorio = new JogadorRepositorio(conexao);


        }catch(Exception ex){
            Log.d("ERRODECONEXAO", String.valueOf(ex));

        }
    }

    public void onClickInicio(View v) {

        Log.d("JOGADOR", jogador2);



        jogador.usuario = jogador2;
        jogador.pontuacao = txtPontuacao.getText().toString();

        jogadorRepositorio.inserir(jogador);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


        Log.d("Cadastro", "Funcionou!");
    }




}

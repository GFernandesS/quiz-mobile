package com.example.projetoquiz;

import android.content.Context;
import android.media.MediaPlayer;

public class tocadorDeMusica {


    public MediaPlayer mediaPlayer;

    public void tocaMusica(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.musicafundo);
        mediaPlayer.start();
    }

    public void paraMusica(){
        mediaPlayer.release();
    }
}

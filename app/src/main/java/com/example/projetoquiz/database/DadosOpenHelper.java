package com.example.projetoquiz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Script;
import android.support.annotation.Nullable;

public class DadosOpenHelper extends SQLiteOpenHelper {
    public DadosOpenHelper(@Nullable Context context) {
        super(context, "QUIZ", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDLL.getCreateTableJogador());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS CONTA";
        if (i > i1)
            sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL(ScriptDLL.getCreateTableJogador());
    }
    }



package com.example.myapplication.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

public class SqLiteDataHelper extends SQLiteOpenHelper {

    public SqLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO (CODIGO INTEGER, CLIENTE VARCHAR(100), PRODUTO VARCHAR(100), QUANTIDADE INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE LOGIN (NOME VARCHAR(50), USUARIO VARCHAR(50), SENHA VARCHAR(50))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

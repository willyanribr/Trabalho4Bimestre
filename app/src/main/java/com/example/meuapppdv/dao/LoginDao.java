package com.example.meuapppdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.meuapppdv.helper.SqLiteDataHelper;
import com.example.meuapppdv.model.Login;

import java.util.ArrayList;

public class LoginDao implements GenericDao<Login> {

    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[] colunas = {"NOME", "USUARIO", "SENHA"};

    //nome da tabela
    private String tabela = "LOGIN";

    //Contexto (view)
    private Context context;

    private static LoginDao instancia;


    public static LoginDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new LoginDao(context);
        } else {
            return instancia;
        }
    }

    private LoginDao(Context context) {
        this.context = context;

        openHelper = new SqLiteDataHelper(this.context, "SPEED_SHOP", null, 1);

        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Login obj) {
        try {
            ContentValues values = new ContentValues();
            values.put(colunas[0], obj.getNome());
            values.put(colunas[1], obj.getUsuario());
            values.put(colunas[2], obj.getSenha());

            return baseDados.insert(tabela, null, values);
        } catch (android.database.SQLException ex) {
            Log.e("Login", "ERRO: LogiDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Login obj) {
        return 0;
    }

    @Override
    public long delete(Login obj) {
        try {
            String[] identificador = {String.valueOf(obj.getUsuario())};

            return baseDados.delete(tabela, colunas[0] + "= ?", identificador);
        } catch (android.database.SQLException ex) {
            Log.e("LOGIN", "Erro: LoginDao.delete()" + ex.getMessage());
        }
        return 0;
    }


    @Override
    public ArrayList<Login> getAll() {
        ArrayList<Login> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Login login = new Login();
                    login.setUsuario(cursor.getString(0));
                    login.setSenha(cursor.getString(1));

                    lista.add(login);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.getAll() "+ex.getMessage());
        }

        return lista;
    }
    @Override
    public Login getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Login login = new Login();
                login.setUsuario(cursor.getString(1));
                login.setSenha(cursor.getString(2));

                return login;
            }

        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.getById() "+ex.getMessage());
        }
        return null;
    }

    public Login getByUser(String usuario) {
        try{
            String[]identificador = {usuario};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[1]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Login login = new Login();
                login.setUsuario(cursor.getString(1));
                login.setSenha(cursor.getString(2));

                return login;
            }

        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.getById() "+ex.getMessage());
        }
        return null;
    }
}

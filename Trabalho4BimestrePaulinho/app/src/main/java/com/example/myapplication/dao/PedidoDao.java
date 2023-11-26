package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.helper.SqLiteDataHelper;
import com.example.myapplication.model.Login;
import com.example.myapplication.model.Pedido;

import java.util.ArrayList;


public class PedidoDao implements GenericDao<Pedido> {

    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[] colunas = {"CODIGO", "CLIENTE", "PRODUTO", "QUANTIDADE"};

    //nome da tabela
    private String tabela = "PEDIDO";

    //Contexto (view)
    private Context context;

    private static PedidoDao instancia;


    public static PedidoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new PedidoDao(context);
        } else {
            return instancia;
        }
    }

    private PedidoDao(Context context) {
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SqLiteDataHelper(this.context,
                "PDV", null, 1);

        //instanciando a base de dados
        baseDados = openHelper.getWritableDatabase();

    }

    @Override
    public long insert(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getNomeCliente());
            valores.put(colunas[2], obj.getNomeProduto());
            valores.put(colunas[3], obj.getQuantidade());

            return baseDados.insert(tabela, null, valores);

        } catch (android.database.SQLException ex) {
            Log.e("PEDIDO", "ERRO: PedidoDao.insert() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNomeCliente());
            valores.put(colunas[2], obj.getNomeProduto());
            String[] identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.update(tabela, valores,
                    colunas[0] + "= ?", identificador);

        } catch (android.database.SQLException ex) {
            Log.e("PEDIDO", "ERRO: PedidoDao.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pedido obj) {
        try {
            String[] identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.delete(tabela,
                    colunas[0] + "= ?", identificador);
        } catch (android.database.SQLException ex) {
            Log.e("PEDIDO", "ERRO: PedidoDao.delete() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas, null, null, null,
                    null, colunas[0] + " desc");

            if (cursor.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido();
                    pedido.setCodigo(cursor.getInt(0));
                    pedido.setNomeCliente(cursor.getString(1));
                    pedido.setNomeProduto(cursor.getString(2));
                    pedido.setQuantidade(cursor.getInt(3));

                    lista.add(pedido);

                } while (cursor.moveToNext());
            }

        } catch (android.database.SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Pedido getById(int id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas, colunas[0] + "= ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {

                Pedido pedido = new Pedido();

                pedido.setCodigo(cursor.getInt(0));
                pedido.setNomeCliente(cursor.getString(1));
                pedido.setNomeProduto(cursor.getString(2));
                pedido.setQuantidade(cursor.getInt(3));

                return pedido;
            }

        } catch (android.database.SQLException ex) {
            Log.e("PEDIDO", "ERRO: PedidoDao.getById() " + ex.getMessage());
        }
        return null;
    }
}

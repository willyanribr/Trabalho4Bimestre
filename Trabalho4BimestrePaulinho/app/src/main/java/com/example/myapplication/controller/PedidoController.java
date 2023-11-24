package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.dao.PedidoDao;
import com.example.myapplication.model.Pedido;
import com.example.myapplication.view.PedidoActivity;

import java.util.ArrayList;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public String salvarPedido(String codigo, String nome, String produto, String quantidade) {

            if (codigo.equals("") || nome.isEmpty()) {
                return "Informe o nome do Cliente!";
            }
            if (nome.equals("") || nome.isEmpty()) {
                return "Informe o nome do Produto!";
            }
            if (produto.equals("") || nome.isEmpty()) {
                return "Informe o nome do Produto!";
            }
            if (quantidade.equals("") || nome.isEmpty()) {
                return "Informe o nome do Produto!";
            }

            Pedido pedido = new Pedido();

            if (pedido.equals(null)) {
                return "O Código (" + codigo + ") já está cadastrado!";
            } else {
                pedido = new Pedido();
                pedido.setCodigo(Integer.parseInt(codigo));
                pedido.setNomeCliente(nome);
                pedido.setNomeProduto(produto);
                pedido.setQuantidade(Integer.parseInt(quantidade));

                PedidoDao.getInstancia(context).insert(pedido);

            }

        return null;
    }

    public ArrayList<Pedido> retornarTodosPedidos() {
        return PedidoDao.getInstancia(context).getAll();
    }
}

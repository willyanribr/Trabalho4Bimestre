package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.dao.PedidoDao;
import com.example.myapplication.model.Pedido;

import java.util.ArrayList;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public String salvarPedido(String nome, String produto, String quantidade, String valorUnitario, String formaPagamento) {

        if (nome.equals("") || nome.isEmpty()) {
            return "Informe o nome do Produto!";
        }
        if (produto.equals("") || nome.isEmpty()) {
            return "Informe o nome do Produto!";
        }
        if (quantidade.equals("") || nome.isEmpty()) {
            return "Informe o nome do Produto!";
        }
        if (valorUnitario.equals("") || nome.isEmpty()) {
            return "Informe o valor unitário do produto!";
        }

        double valorUnitario1 = Double.parseDouble(String.valueOf(valorUnitario));
        double valorTotal1;
        int quantidade1 = Integer.parseInt(String.valueOf(quantidade));

        valorTotal1 = quantidade1 * valorUnitario1;

        Pedido pedido = new Pedido();

        if (pedido.equals(null)) {
            return "O Código já está cadastrado!";
        } else {
            pedido = new Pedido();

            pedido.setNomeCliente(nome);
            pedido.setNomeProduto(produto);
            pedido.setQuantidade(Integer.parseInt(quantidade));
            pedido.setValorUnitario(Double.parseDouble(valorUnitario));
            pedido.setValorTotal(valorTotal1);
            pedido.setFormaPagamento(formaPagamento);

            PedidoDao.getInstancia(context).insert(pedido);
        }
        return null;
    }

    public ArrayList<Pedido> retornarRelatorio() {
        return PedidoDao.getInstancia(context).getAllRelatorio();
    }
    public ArrayList<Pedido> retornarTodosPedidos() {
        return PedidoDao.getInstancia(context).getAll();
    }
}

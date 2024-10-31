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

        double valorUnitario1 = Double.parseDouble(String.valueOf(valorUnitario));
        double valorTotal1;
        int quantidade1 = Integer.parseInt(String.valueOf(quantidade));

        valorTotal1 = quantidade1 * valorUnitario1;

        Pedido pedido = new Pedido();

        if (pedido.equals(null)) {
            return "Pedido inválido!";
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
    public String checkDadosInformados(String nome, String produto, String quantidade, String valorUnitario, String formaPagamento) {
        String msgErro = "";

        if (nome.equals("") || nome.isEmpty()) {
            msgErro = "O nome do cliente é obrigatório!";
        } else if (produto.equals("") || produto.isEmpty()){
            msgErro = "O nome do produto é obrigatório!";
        } else if (quantidade.equals("") || quantidade.isEmpty()){
            msgErro = "A quantidade é obrigatório!";
        } else if (valorUnitario.equals("") || valorUnitario.isEmpty()){
            msgErro = "O valor unitário do produto é obrigatório!";
        } else if (formaPagamento.equals("") || formaPagamento.isEmpty()){
            msgErro = "A forma de pagamento do produto é obrigatório!";
        }

        return msgErro;
    }
}

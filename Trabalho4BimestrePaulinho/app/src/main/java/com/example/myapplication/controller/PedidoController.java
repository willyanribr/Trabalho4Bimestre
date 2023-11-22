package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.model.Pedido;
import com.example.myapplication.view.PedidoActivity;

import java.util.ArrayList;

public class PedidoController {

    private Context context;
    public PedidoController(Context context) {this.context = context;}

        public String salvarPedido (String nome, String produto, int quantidade){

            try {
                if (nome.equals("") || nome.isEmpty()) {
                    return "Informe o nome do Cliente!";
                }
                if (nome.equals("") || nome.isEmpty()) {
                    return "Informe o nome do Produto!";
                }

                Pedido pedido = PedidoDao.getInstancia(context);

                    pedido = new Pedido();
                    pedido.setNomeCliente(pedido.getNomeCliente());
                    pedido.setNomeProduto(pedido.getNomeProduto());
                    pedido.setQuantidade(quantidade);

                    AlunoDao.getInstancia(context).insert(aluno);

            } catch (Exception ex) {
                return "Erro ao Gravar Aluno.";
            }
            return null;
        }

        /**
         * Retorna todos os alunos cadastrados no banco
         * @return
         */
        public ArrayList<Aluno> retornarTodosAlunos () {
            return AlunoDao.getInstancia(context).getAll();
        }
    }
}

package com.example.myapplication.model;

public class Pedido{
    private String nomeCliente;
    private String nomeProduto;

    private int quantidade;

    public Pedido() {
    }

    public Pedido(String nomeCliente, String nomeProduto, int quantidade) {
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

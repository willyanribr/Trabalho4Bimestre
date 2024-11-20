package com.example.meuapppdv.model;

public class Pedido{
    private int codigo;
    private String nomeCliente;
    private String nomeProduto;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private String formaPagamento;

    public Pedido() {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public Double getValorUnitario() {return valorUnitario;}
    public void setValorUnitario(double valorUnitario) {this.valorUnitario = valorUnitario;}

    public Double getValorTotal() {return valorTotal;}

    public void setValorTotal(double valorTotal) {this.valorTotal = valorTotal;}

    public String getFormaPagamento() {return formaPagamento;}

    public void setFormaPagamento(String formaPagamento) {this.formaPagamento = formaPagamento;}
}

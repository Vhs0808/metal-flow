package com.metalflow.model;

public class EstoqueSetor {
    private int id;
    private int ordemId;
    private int setorId;
    private String codigoProduto;
    private int quantidade;

    public EstoqueSetor(){}

    public EstoqueSetor(int id, int ordemId, int setorId, String codigoProduto, int quantidade) {
        this.id = id;
        this.ordemId = ordemId;
        this.setorId = setorId;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getOrdemId() {return ordemId;}

    public void setOrdemId(int ordemId) {this.ordemId = ordemId;}

    public int getSetorId() {return setorId;}

    public void setSetorId(int setorId) {this.setorId = setorId;}

    public String getCodigoProduto() {return codigoProduto;}

    public void setCodigoProduto(String codigoProduto) {this.codigoProduto = codigoProduto;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}

package com.metalflow.model;

public class EstoquesSetor {
    private int id;
    private int ordem_id;
    private int setor_id;
    private String codigo_produto;
    private int quantidade;

    public EstoquesSetor(){}

    public EstoquesSetor(int id, int ordem_id, int setor_id, String codigo_produto, int quantidade) {
        this.id = id;
        this.ordem_id = ordem_id;
        this.setor_id = setor_id;
        this.codigo_produto = codigo_produto;
        this.quantidade = quantidade;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getOrdem_id() {return ordem_id;}

    public void setOrdem_id(int ordem_id) {this.ordem_id = ordem_id;}

    public int getSetor_id() {return setor_id;}

    public void setSetor_id(int setor_id) {this.setor_id = setor_id;}

    public String getCodigo_produto() {return codigo_produto;}

    public void setCodigo_produto(String codigo_produto) {this.codigo_produto = codigo_produto;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}

package com.metalflow.model;

public class Setores {
    private int id;
    private String codigo;
    private String nome;
    private boolean ativo;

    public Setores(){}

    public Setores(int id, String codigo, String nome, boolean ativo) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.ativo = ativo;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getCodigo() {return codigo;}

    public void setCodigo(String codigo) {this.codigo = codigo;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public boolean getAtivo() {return ativo;}

    public void setAtivo(boolean ativo) {this.ativo = ativo;}
}

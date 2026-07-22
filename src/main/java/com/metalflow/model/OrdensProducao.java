package com.metalflow.model;

import com.metalflow.enums.StatusOP;

import java.time.LocalDateTime;

public class OrdensProducao {
    private int id;
    private String numero_op;
    private String codigo_produto;
    private String descricao_produto;
    private int quantidade_planejada;
    private int saldo_op;
    private StatusOP  status_op;
    private LocalDateTime criado_em;

    public OrdensProducao(){}

    public OrdensProducao(int id, String numero_op, String codigo_produto, String descricao_produto, int quantidade_planejada, int saldo_op, StatusOP status_op, LocalDateTime criado_em) {
        this.id = id;
        this.numero_op = numero_op;
        this.codigo_produto = codigo_produto;
        this.descricao_produto = descricao_produto;
        this.quantidade_planejada = quantidade_planejada;
        this.saldo_op = saldo_op;
        this.status_op = status_op;
        this.criado_em = criado_em;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNumero_op() {return numero_op;}

    public void setNumero_op(String numero_op) {this.numero_op = numero_op;}

    public String getCodigo_produto() {return codigo_produto;}

    public void setCodigo_produto(String codigo_produto) {this.codigo_produto = codigo_produto;}

    public String getDescricao_produto() {return descricao_produto;}

    public void setDescricao_produto(String descricao_produto) {this.descricao_produto = descricao_produto;}

    public int getQuantidade_planejada() {return quantidade_planejada;}

    public void setQuantidade_planejada(int quantidade_planejada) {this.quantidade_planejada = quantidade_planejada;}

    public int getSaldo_op() {return saldo_op;}

    public void setSaldo_op(int saldo_op) {this.saldo_op = saldo_op;}

    public StatusOP getStatus_op() {return status_op;}

    public void setStatus_op(StatusOP status_op) {this.status_op = status_op;}

    public LocalDateTime getCriado_em() {return criado_em;}

    public void setCriado_em(LocalDateTime criado_em) {this.criado_em = criado_em;}
}

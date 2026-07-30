package com.metalflow.model;

import com.metalflow.enums.StatusOP;

import java.time.LocalDateTime;

public class OrdemProducao {
    private int id;
    private String numeroOp;
    private String codigoProduto;
    private String descricaoProduto;
    private int quantidadePlanejada;
    private int saldoOp;
    private StatusOP statusOp;
    private LocalDateTime criadoEm;

    public OrdemProducao(){}

    public OrdemProducao(int id, String numero_op, String codigoProduto, String descricaoProduto, int quantidadePlanejada, int saldoOp, StatusOP statusOp, LocalDateTime criadoEm) {
        this.id = id;
        this.numeroOp = numero_op;
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidadePlanejada = quantidadePlanejada;
        this.saldoOp = saldoOp;
        this.statusOp = statusOp;
        this.criadoEm = criadoEm;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNumeroOp() {return numeroOp;}

    public void setNumeroOp(String numeroOp) {this.numeroOp = numeroOp;}

    public String getCodigoProduto() {return codigoProduto;}

    public void setCodigoProduto(String codigoProduto) {this.codigoProduto = codigoProduto;}

    public String getDescricaoProduto() {return descricaoProduto;}

    public void setDescricaoProduto(String descricaoProduto) {this.descricaoProduto = descricaoProduto;}

    public int getQuantidadePlanejada() {return quantidadePlanejada;}

    public void setQuantidadePlanejada(int quantidadePlanejada) {this.quantidadePlanejada = quantidadePlanejada;}

    public int getSaldoOp() {return saldoOp;}

    public void setSaldoOp(int saldoOp) {this.saldoOp = saldoOp;}

    public StatusOP getStatusOp() {return statusOp;}

    public void setStatusOp(StatusOP statusOp) {this.statusOp = statusOp;}

    public LocalDateTime getCriadoEm() {return criadoEm;}

    public void setCriadoEm(LocalDateTime criadoEm) {this.criadoEm = criadoEm;}

}

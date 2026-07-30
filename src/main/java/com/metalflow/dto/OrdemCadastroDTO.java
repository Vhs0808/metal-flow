package com.metalflow.dto;

import com.google.gson.annotations.SerializedName;

public class OrdemCadastroDTO {
    @SerializedName("numero_OP")
    private String numeroOp;
    @SerializedName("codigo_produto")
    private String codigoProduto;
    @SerializedName("descricao_produto")
    private String descricaoProduto;
    @SerializedName("quantidade_planejada")
    private int quantidadePlanejada;

    public OrdemCadastroDTO(){}

    public String getNumeroOp() {
        return numeroOp;
    }

    public void setNumeroOp(String numeroOp) {
        this.numeroOp = numeroOp;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getQuantidadePlanejada() {
        return quantidadePlanejada;
    }

    public void setQuantidadePlanejada(int quantidadePlanejada) {
        this.quantidadePlanejada = quantidadePlanejada;
    }
}

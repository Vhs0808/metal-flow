package com.metalflow.dto;


import com.google.gson.annotations.SerializedName;

public class ApontamentoDTO {

    @SerializedName("codigo_setor")
    private String codigoSetor;
    @SerializedName("quantidade_apontada")
    private int quantidadeApontada;

    public ApontamentoDTO(){}

    public String getCodigoSetor() {
        return codigoSetor;
    }

    public void setCodigoSetor(String codigoSetor) {
        this.codigoSetor = codigoSetor;
    }

    public int getQuantidadeApontada() {
        return quantidadeApontada;
    }

    public void setQuantidadeApontada(int quantidadeApontada) {
        this.quantidadeApontada = quantidadeApontada;
    }
}

package com.metalflow.dto;


public class ApontamentoDTO {

    private String codigoSetor;
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

package com.metalflow.model;

import java.time.LocalTime;

public class Apontamento {
    private int id;
    private int ordemId;
    private int setorId;
    private int quantidadeApontada;
    private LocalTime dataApontamento;

    public Apontamento(){}

    public Apontamento(int id, int ordemId, int setorId, int quantidadeApontada, LocalTime dataApontamento) {
        this.id = id;
        this.ordemId = ordemId;
        this.setorId = setorId;
        this.quantidadeApontada = quantidadeApontada;
        this.dataApontamento = dataApontamento;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getOrdemId() {return ordemId;}

    public void setOrdemId(int ordemId) {this.ordemId = ordemId;}

    public int getSetorId() {return setorId;}

    public void setSetorId(int setorId) {this.setorId = setorId;}

    public int getQuantidadeApontada() {return quantidadeApontada;}

    public void setQuantidadeApontada(int quantidadeApontada) {this.quantidadeApontada = quantidadeApontada;}

    public LocalTime getDataApontamento() {return dataApontamento;}

    public void setDataApontamento(LocalTime dataApontamento) {this.dataApontamento = dataApontamento;}
}

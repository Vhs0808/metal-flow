package com.metalflow.model;

import java.time.LocalTime;

public class Apontamentos {
    private int id;
    private int ordem_id;
    private int setor_id;
    private int quantidade_apontada;
    private LocalTime data_apontamento;

    public Apontamentos(){}

    public Apontamentos(int id, int ordem_id, int setor_id, int quantidade_apontada, LocalTime data_apontamento) {
        this.id = id;
        this.ordem_id = ordem_id;
        this.setor_id = setor_id;
        this.quantidade_apontada = quantidade_apontada;
        this.data_apontamento = data_apontamento;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getOrdem_id() {return ordem_id;}

    public void setOrdem_id(int ordem_id) {this.ordem_id = ordem_id;}

    public int getSetor_id() {return setor_id;}

    public void setSetor_id(int setor_id) {this.setor_id = setor_id;}

    public int getQuantidade_apontada() {return quantidade_apontada;}

    public void setQuantidade_apontada(int quantidade_apontada) {this.quantidade_apontada = quantidade_apontada;}

    public LocalTime getData_apontamento() {return data_apontamento;}

    public void setData_apontamento(LocalTime data_apontamento) {this.data_apontamento = data_apontamento;}
}

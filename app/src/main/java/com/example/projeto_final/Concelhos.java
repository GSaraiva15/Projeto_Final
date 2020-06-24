package com.example.projeto_final;

public class Concelhos {
    private long id = -1;
    private String nome_concelho;
    private String nr_infetados;
    private String nr_recuperados;
    private String nr_obitos;
    private Integer nr_Habitante = 0;

    public Integer getNr_Habitante() {
        return nr_Habitante;
    }

    public void setNr_Habitante(Integer nr_Habitante) {
        this.nr_Habitante = nr_Habitante;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_concelho() {
        return nome_concelho;
    }

    public void setNome_concelho(String nome_concelho) {
        this.nome_concelho = nome_concelho;
    }

    public String getNr_infetados() {
        return nr_infetados;
    }

    public void setNr_infetados(String nr_infetados) {
        this.nr_infetados = nr_infetados;
    }

    public String getNr_recuperados() {
        return nr_recuperados;
    }

    public void setNr_recuperados(String nr_recuperados) {
        this.nr_recuperados = nr_recuperados;
    }

    public String getNr_obitos() {
        return nr_obitos;
    }

    public void setNr_obitos(String nr_obitos) {
        this.nr_obitos = nr_obitos;
    }
}
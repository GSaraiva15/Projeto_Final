package com.example.projeto_final;

public class Doentes {
    private long id = -1;
    private String nome_doente;
    private String nascimento_doente;
    private String telemovel_doente;
    private Integer id_concelho;
    private String sexo_doente;
    private String cronico_doente;
    private String estado_doente;
    private String data_estado;

    public String getData_estado() {
        return data_estado;
    }

    public void setData_estado(String data_estado) {
        this.data_estado = data_estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_doente() {
        return nome_doente;
    }

    public void setNome_doente(String nome_doente) {
        this.nome_doente = nome_doente;
    }

    public String getNascimento_doente() {
        return nascimento_doente;
    }

    public void setNascimento_doente(String nascimento_doente) {
        this.nascimento_doente = nascimento_doente;
    }

    public String getTelemovel_doente() {
        return telemovel_doente;
    }

    public void setTelemovel_doente(String telemovel_doente) {
        this.telemovel_doente = telemovel_doente;
    }

    public Integer getId_concelho() {
        return id_concelho;
    }

    public void setId_concelho(Integer id_concelho) {
        this.id_concelho = id_concelho;
    }

    public String getSexo_doente() {
        return sexo_doente;
    }

    public void setSexo_doente(String sexo_doente) {
        this.sexo_doente = sexo_doente;
    }

    public String getCronico_doente() {
        return cronico_doente;
    }

    public void setCronico_doente(String cronico_doente) {
        this.cronico_doente = cronico_doente;
    }

    public String getEstado_doente() {
        return estado_doente;
    }

    public void setEstado_doente(String estado_doente) {
        this.estado_doente = estado_doente;
    }
}

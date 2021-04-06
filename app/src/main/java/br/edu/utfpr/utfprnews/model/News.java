package br.edu.utfpr.utfprnews.model;

import java.io.Serializable;

public class News implements Serializable {
    private String nome;
    private String sigla;
    private String regiao;

    public News(String nome, String sigla, String regiao) {
        this.nome = nome;
        this.sigla = sigla;
        this.regiao = regiao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}


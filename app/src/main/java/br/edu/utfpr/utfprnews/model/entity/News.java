package br.edu.utfpr.utfprnews.model.entity;

import java.io.Serializable;

public class News implements Serializable {

    private long id;
    private String titulo;
    private String sigla;
    private String regiao;
    private String descricao;

    public News(String titulo, String sigla, String regiao, String descricao) {
        this.titulo = titulo;
        this.sigla = sigla;
        this.regiao = regiao;
        this.descricao = descricao;

    }

    // criado este construtor vazio para o while do lista na classe NewsDAO

    public News() {

    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


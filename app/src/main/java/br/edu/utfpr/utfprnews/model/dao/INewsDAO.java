package br.edu.utfpr.utfprnews.model.dao;

import java.util.List;

import br.edu.utfpr.utfprnews.model.entity.News;

public interface INewsDAO {
    public boolean salvar(News c);
    public boolean atualizar(News c);
    public boolean remover(News c);
    public List<News> listar();
}

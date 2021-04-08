package br.edu.utfpr.utfprnews.model.contract;

import android.provider.BaseColumns;

public final class NewsContract {
    // Para evitar que alguém instancie acidentalmente a classe,
    // tornar o construtor privado
    private NewsContract(){}

    /* Classe interna que define o conteúdo da tabela */
    public static class NewsEntry implements BaseColumns{
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NEWS_TITULO = "titulo";
        public static final String COLUMN_NEWS_SIGLA = "sigla";
        public static final String COLUMN_NEWS_REGIAO = "regiao";
        public static final String COLUMN_NEWS_DESCRICAO = "descricao";
    }
}

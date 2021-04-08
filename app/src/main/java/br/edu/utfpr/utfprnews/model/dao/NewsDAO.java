package br.edu.utfpr.utfprnews.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.utfprnews.helpers.DbHelper;
import br.edu.utfpr.utfprnews.model.contract.NewsContract;
import br.edu.utfpr.utfprnews.model.entity.News;

//Classe responsavel por implementar a interface INewsDAO
public class NewsDAO implements INewsDAO {

    //executa função para escrita no banco de dados
    SQLiteDatabase writer;
    SQLiteDatabase reader;

    public NewsDAO(Context context) {
        //instancia DBHelper
        DbHelper db = new DbHelper(context);
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(News c) {
        //cria objeto para escriata no banco de dados
        ContentValues cv = new ContentValues();
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_TITULO, c.getTitulo());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_SIGLA, c.getSigla());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_REGIAO, c.getRegiao());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_DESCRICAO, c.getDescricao());

        //envia o contentvalues para o objeto writer para que ele submeta os dados para o banco de dados
        try {
            writer.insert(NewsContract.NewsEntry.TABLE_NAME, null, cv);
            Log.i("DB INFO", "Iformacao salva no banco sucesso!!");
        } catch (Exception e){
            Log.i("DB ERROR", "Erro ao salvar informacao no banco");
            return false;
        }
        return false;
    }

    @Override
    public boolean atualizar(News c) {

        ContentValues cv = new ContentValues();
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_TITULO, c.getTitulo());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_SIGLA, c.getSigla());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_REGIAO, c.getRegiao());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_DESCRICAO, c.getDescricao());

        //envia o contentvalues para o objeto writer para que ele submeta os dados para o banco de dados
        try {
            //vetor de argumentos
            String[] args = {c.getId().toString()};
            writer.update(NewsContract.NewsEntry.TABLE_NAME, cv,NewsContract.NewsEntry._ID+"=?", args );
            Log.i("DB INFO", "Noticia atualizados com sucesso!!");
        } catch (Exception e){
            Log.i("DB ERROR", "Erro ao atualizar Noticia  no banco");
            return false;
        }
        return true;
    }

    @Override
    public boolean remover(News c) {
        try {
            String[] args = {c.getId().toString()};
            writer.delete(NewsContract.NewsEntry.TABLE_NAME, NewsContract.NewsEntry._ID+"=?", args);
            Log.i("DB INFO", "Noticia deletados com sucesso");
        }catch (Exception e){
            Log.e("DB ERROR", "Erro ao deletar Noticia" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<News> listar() {
        //lista campos do banco
        List<News> news = new ArrayList<>();

        String sql = "SELECT * FROM " + NewsContract.NewsEntry.TABLE_NAME + " ;";
        //objeto cursor recebe resultado da execução do sql
        Cursor cursor = reader.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex(NewsContract.NewsEntry._ID));
            String titulo = cursor.getString(cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_TITULO));
            String sigla = cursor.getString(cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_SIGLA));
            String regiao = cursor.getString(cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_REGIAO));
            String descricao = cursor.getString(cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_DESCRICAO));

            News c = new News(titulo, sigla, regiao, descricao);
            c.setId(id);

            news.add(c);
            Log.i("NewsDAO", c.getTitulo());
        }

        // retorna lista de campos criada no while
        return news;
    }
}

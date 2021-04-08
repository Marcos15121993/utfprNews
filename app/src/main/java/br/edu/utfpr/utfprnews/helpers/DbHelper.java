package br.edu.utfpr.utfprnews.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.edu.utfpr.utfprnews.model.contract.NewsContract;

public class DbHelper  extends SQLiteOpenHelper {
    // Se você alterar o esquema do banco de dados, deverá incrementar a versão do banco de dados.
    public static final int DATABASE_VERSION = 1;
    public static final  String DATABASE_NAME = "News.db";

    // pega uma string e cria uma tabela no banco de dados, passando os atributos
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + "(" +
                    NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY," +
                    NewsContract.NewsEntry.COLUMN_NEWS_TITULO + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NEWS_SIGLA + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NEWS_REGIAO + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NEWS_DESCRICAO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NAME ;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  void onCreate(SQLiteDatabase db){
        try{
            Log.i("DB INFO", "Sucesso ao criar tabela #DEU_BOM");
            db.execSQL(SQL_CREATE_ENTRIES);
        } catch (Exception e){
            Log.i("DB INFO", "Erro ao criar tabela #FODEU");
        }

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // este banco de dados é apenas um cache para dados online, então sua política de atualização é
        // para simplesmente descartar os dados e começar de novo
        try{
            Log.i("DB INFO", "Sucesso ao atualizar tabela #IHHHHAAAAA");
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        } catch (Exception e){
            Log.i("DB INFO", "Erro ao atualizar tabela #BAH");
        }

    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}

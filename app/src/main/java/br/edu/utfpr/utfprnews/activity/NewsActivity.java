package br.edu.utfpr.utfprnews.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.edu.utfpr.utfprnews.R;
import br.edu.utfpr.utfprnews.model.News;

public class NewsActivity extends AppCompatActivity {

    private TextView nomeNews;
    private TextView siglaNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        nomeNews = findViewById(R.id.textViewNome);
        siglaNews = findViewById(R.id.textViewSiglaNews);

        //capturar dados enviados da Welcome activity - pega os dados que foram transferidos de uma activity para outra
        Bundle dados = getIntent().getExtras();

        News news = (News) dados.getSerializable("nome");

        //setar valores dos componentes anterior
       nomeNews.setText(news.getNome());
        siglaNews.setText(news.getSigla());

    }
}
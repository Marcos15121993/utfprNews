package br.edu.utfpr.utfprnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.utfprnews.activity.FormularioActivity;
import br.edu.utfpr.utfprnews.activity.NewsActivity;
import br.edu.utfpr.utfprnews.adapter.AdapterNews;
import br.edu.utfpr.utfprnews.listener.RecyclerViewClickListener;
import br.edu.utfpr.utfprnews.listener.RecyclerViewTouchListener;
import br.edu.utfpr.utfprnews.model.News;

public class Welcome extends AppCompatActivity {

    TextView txtWelcomeMessage;

    private RecyclerView listNews;
    private List<News> lista = new ArrayList<>();
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);

        Intent intent = getIntent();
        String userdatavalue = intent.getStringExtra("userdata");
        txtWelcomeMessage.setText("Bem vindo " + userdatavalue);

        Toast.makeText(this, userdatavalue, Toast.LENGTH_SHORT).show();

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        listNews = findViewById(R.id.recyclerNews);

        fab = findViewById(R.id.floatingActionButton);

        //chama lista e passa no config do adapter
        geraLista();


        //config do adapter
        AdapterNews adapter = new AdapterNews(lista);
        //config do recycler view

        //Layout manager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        listNews.setLayoutManager(manager);

        //adapter
        listNews.setAdapter(adapter);
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //evento adicionando listener para on click
        listNews.addOnItemTouchListener(new RecyclerViewTouchListener(
                getApplicationContext(), listNews, new RecyclerViewClickListener() {

            @Override
            public void onClick(View view, int position) {
                //faz a chamada de outra activit com base no click
                //Toast.makeText(getApplicationContext(), lista.get(position).getNome(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);

                //passa dados para dentro da activity
               // intent.putExtra("nome", lista.get(position).getNome());
                //intent.putExtra("sigla", lista.get(position).getSigla());

                Intent intent1 = new Intent(getApplicationContext(), FormularioActivity.class);
                intent.putExtra("news", lista.get(position));

                //Inicializa activity
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), lista.get(position).getNome()+" - "+lista.get(position).getSigla(),Toast.LENGTH_SHORT).show();

            }
        }
    ));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), FormularioActivity.class);
                intent1.putExtra("news", String.valueOf(new News("","","")));
                startActivity(intent1);

            }
        });
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    }
    //metodo gera lista
    public void geraLista(){
        lista.add(new News("Festival da Canção", "DV", "Teatro"));
        lista.add(new News("Auxilio moradia", "DV", "ASCOM"));
        lista.add(new News("Intercursos", "FB", "QP"));
        lista.add(new News("Feijoada", "PT", "RU"));
        lista.add(new News("Festival da Canção", "FB", "Teatro"));

    }

}
package br.edu.utfpr.utfprnews;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.utfprnews.activity.FormularioActivity;
import br.edu.utfpr.utfprnews.activity.NewsActivity;
import br.edu.utfpr.utfprnews.adapter.AdapterNews;
import br.edu.utfpr.utfprnews.helpers.DbHelper;
import br.edu.utfpr.utfprnews.listener.RecyclerViewClickListener;
import br.edu.utfpr.utfprnews.listener.RecyclerViewTouchListener;
import br.edu.utfpr.utfprnews.model.contract.NewsContract;
import br.edu.utfpr.utfprnews.model.dao.NewsDAO;
import br.edu.utfpr.utfprnews.model.entity.News;
import br.edu.utfpr.utfprnews.register.Users;

public class Welcome extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    TextView txtWelcomeMessage;

    private RecyclerView listNews;
    private List<News> lista = new ArrayList<>();
    private FloatingActionButton fab;
    AdapterNews adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //firebase  --------------------------------------------------------------------------------------------------------------------------

        Users uso = new Users("Jao", "teste", "abc1243", "jao@jao.com.br");
        DatabaseReference users = reference.child("usuarios");
        users.child("004").setValue(uso);


        //------------------------------------------------  --------------------------------------------------------------------------------------------------------------------------
        txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);

        Intent intent = getIntent();
        String userdatavalue = intent.getStringExtra("userdata");
        txtWelcomeMessage.setText("Bem vindo " + userdatavalue);

        Toast.makeText(this, userdatavalue, Toast.LENGTH_SHORT).show();


        listNews = findViewById(R.id.recyclerNews);

        fab = findViewById(R.id.floatingActionButton);

        //evento adicionando listener para on click  --------------------------------------------------------------------------------------------------------------------------
        listNews.addOnItemTouchListener(new RecyclerViewTouchListener(
                getApplicationContext(), listNews, new RecyclerViewClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), FormularioActivity.class);
                intent.putExtra("news", lista.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //cria alerta na tela ao tentar deletar
                AlertDialog.Builder alerta = new AlertDialog.Builder(Welcome.this);
                alerta.setTitle("Aviso");
                alerta
                        .setIcon(R.mipmap.ic_aviso)
                        .setMessage("Deseja remover o item noticia?")
                        .setCancelable(true)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Cancelar jao", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "ok escolhido", Toast.LENGTH_SHORT).show();
                                NewsDAO dao = new NewsDAO(getApplicationContext());
                                if(dao.remover(lista.get(position))) {


                                    Toast.makeText(getApplicationContext(), lista.get(position).getTitulo() + " removido com sucesso!  ", Toast.LENGTH_SHORT).show();
                                    geraLista();
                                } else{
                                    Toast.makeText(getApplicationContext(),  " Erro ao tentar remover noticia !  " , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();

            }
        }
    ));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormularioActivity.class);
                //intent1.putExtra("news", String.valueOf(new News("","","")));
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onStart(){
        //chama lista e passa no config do adapter
        geraLista();
        super.onStart();
    }
   //metodo gera lista---------------------------------------------------------------------------------------------------------------------------------------------
    public void geraLista(){
        NewsDAO dao = new NewsDAO(getApplicationContext());
        lista = dao.listar();

        //config do adapter
        adapter = new AdapterNews(lista);
        //config do recycler view

        //Layout manager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        listNews.setLayoutManager(manager);

        //adapter
        listNews.setAdapter(adapter);
    }

}
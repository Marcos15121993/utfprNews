package br.edu.utfpr.utfprnews.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.utfpr.utfprnews.R;
import br.edu.utfpr.utfprnews.Welcome;
import br.edu.utfpr.utfprnews.model.dao.NewsDAO;
import br.edu.utfpr.utfprnews.model.entity.News;

public class FormularioActivity extends AppCompatActivity {

    private TextInputEditText inputTitulo;
    private TextInputEditText inputSigla;
    private TextInputEditText inputRegiao;
    private TextInputEditText inputDescricao;
    private News newsSelectionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        inputTitulo = findViewById(R.id.textInputTitulo);
        inputSigla = findViewById(R.id.textInputSigla);
        inputRegiao = findViewById(R.id.textInputRegiao);
        inputDescricao = findViewById(R.id.textInputDescricao);


        Bundle extra = getIntent().getExtras();
        try {
            newsSelectionado = (News) extra.getSerializable("news");
        } catch (NullPointerException e) {
            newsSelectionado = null;
        }

        if (newsSelectionado != null) {
            inputTitulo.setText(newsSelectionado.getTitulo());
            inputSigla.setText(newsSelectionado.getSigla());
            inputRegiao.setText(newsSelectionado.getRegiao());
            inputDescricao.setText(newsSelectionado.getDescricao());
        }

        Button button = (Button) findViewById(R.id.btnSalvarFormulario);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validaCampos()) {
                    NewsDAO dao = new NewsDAO(getApplicationContext());
                    News c = new News(inputTitulo.getText().toString(),
                            inputSigla.getText().toString(),
                            inputRegiao.getText().toString(),
                            inputDescricao.getText().toString());
                    //valida se é uma atualização, seta o id e salva
                    if(newsSelectionado != null) {
                        c.setId(newsSelectionado.getId());
                        dao.atualizar(c);
                    }else {
                        //do contrario salva uma nova noticia
                        dao.salvar(c);
                        Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG).show();
                }
                finish();
                // return true;
            }
    });
}
/*
    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar_news, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/

/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_salvar_news) {
            if (validaCampos()) {
                NewsDAO dao = new NewsDAO(getApplicationContext());
                News c = new News(inputTitulo.getText().toString(),
                        inputSigla.getText().toString(),
                        inputRegiao.getText().toString(),
                        inputDescricao.getText().toString());
                //valida se é uma atualização, seta o id e salva
                if(newsSelectionado != null) {
                    c.setId(newsSelectionado.getId());
                    dao.atualizar(c);
                }else {
                    //do contrario salva uma nova noticia
                    dao.salvar(c);
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG).show();
            }
            finish();
           // return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public boolean validaCampos(){
        //metodo que valida se todos os campos estao preenchidos
        if (inputSigla.getText().toString().equals("") ||
                inputRegiao.getText().toString().equals("") ||
                inputTitulo.getText().toString().equals("")){
            return false;
        }
        return  true;
    }

}
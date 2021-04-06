package br.edu.utfpr.utfprnews.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.utfpr.utfprnews.R;
import br.edu.utfpr.utfprnews.model.News;

public class FormularioActivity extends AppCompatActivity {

    private TextInputEditText imputTitulo;
    private TextInputEditText imputSigla;
    private TextInputEditText imputregiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        imputTitulo = findViewById(R.id.textImputTitulo);
        imputSigla = findViewById(R.id.textImputSigla);
        imputregiao = findViewById(R.id.textImputRegiao);

        News c;
        Bundle extra = getIntent().getExtras();
        try {
            c = (News) extra.getSerializable("Titulo");
        } catch (NullPointerException e) {
            c = null;
        }
        if (c != null) {
            imputTitulo.setText(c.getNome());
            imputSigla.setText(c.getSigla());
            imputregiao.setText(c.getRegiao());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar_news, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_salvar_news){
            Toast.makeText(getApplicationContext(),"salvar noticia", Toast.LENGTH_LONG).show();
            return true;
        }
            return super.onOptionsItemSelected(item);
    }
}
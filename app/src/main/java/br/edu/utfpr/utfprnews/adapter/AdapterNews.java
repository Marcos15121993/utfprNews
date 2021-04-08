package br.edu.utfpr.utfprnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.utfpr.utfprnews.R;
import br.edu.utfpr.utfprnews.model.entity.News;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

    //construtor que vai receber uma lista
    private List<News> lista;

    public AdapterNews(List<News> lista){
        this.lista = lista;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitulo;
        private TextView textSigla;
        private TextView textRegiao;

        public ViewHolder(View view){
            super(view);
            // Define o ouvinte de cliques para a visão do ViewHolder
            textTitulo = view.findViewById(R.id.textViewTituloNews);
            textRegiao = view.findViewById(R.id.textViewRegiao);
            textSigla = view.findViewById(R.id.textViewSigla);

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //pega o layput criado, infla ele e transforma em uma view, passa a view como parametro no viewholder e dentro dele instancia
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    //cria os elementos
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //acessa os elementos do viewholder e com base no que é passado como parametro é setado os valores dos elementos
        News c = lista.get(position);

        holder.textTitulo.setText(c.getTitulo());
        holder.textSigla.setText(c.getSigla());
        holder.textRegiao.setText(c.getRegiao());
    }

    @Override
    public int getItemCount() {
        //tamanho da nossa lista, mostra quantos elementos vao ser exibidos
        return lista.size();
    }
}

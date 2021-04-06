package br.edu.utfpr.utfprnews.listener;

import android.view.View;
//classe responsavel por criar metodos para usar o onclik ou selecionar(ficar segurado a tela)

public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

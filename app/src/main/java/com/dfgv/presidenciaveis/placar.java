package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class placar extends Activity {
    private ViewGroup mensagens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);
        mensagens = (ViewGroup) findViewById(R.id.container);
        addItem("Título 1", 2, 1);
        addItem("Título 2", 2, 1);
        addItem("Título 3", 2, 1);
        addItem("Título 4", 2, 1);
        addItem("Título 5", 2, 1);

    }
    private void addItem(String nome, int pontuacaopartida, int pontuacaojogo) {
        CardView cardView = (CardView) LayoutInflater.from(this)
                .inflate(R.layout.card, mensagens, false);
        TextView nomes = (TextView) cardView.findViewById(R.id.nome);
        TextView pontuacaorodadas = (TextView) cardView.findViewById(R.id.pontrodada);
        TextView ponuacaojogos = (TextView) cardView.findViewById(R.id.pontjogo);

        nomes.setText(nome);
        pontuacaorodadas.setText(""+pontuacaopartida);
        ponuacaojogos.setText(""+pontuacaojogo);
        mensagens.addView(cardView);
    }
}

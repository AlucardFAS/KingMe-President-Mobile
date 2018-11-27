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
        addItem("Título 1", "Exemplo de mensagem.");
        addItem("Título 2", "Exemplo de mensagem.");
        addItem("Título 3", "Exemplo de mensagem.");
        addItem("Título 4", "Exemplo de mensagem.");
        addItem("Título 5", "Exemplo de mensagem.");

    }
    private void addItem(String textoDoTitulo, String textoDaMensagem) {
        CardView cardView= (CardView) LayoutInflater.from(this).inflate(R.layout.card, mensagens, false);
        TextView titulo = (TextView) cardView.findViewById(R.id.titulo);
        TextView mensagem = (TextView) cardView.findViewById(R.id.mensagem);
        titulo.setText("Nome: "+textoDoTitulo);
        mensagem.setText("Pontuação: "+textoDaMensagem);
        mensagens.addView(cardView);

    }
}

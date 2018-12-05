package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class home extends Activity {
    private ViewGroup mensagens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mensagens = (ViewGroup) findViewById(R.id.container);
        addItem("Título 1");
        addItem("Título 2");
        addItem("Título 3");
        addItem("Título 4");
        addItem("Título 5");
        addItem("Título 6");


    }
    private void addItem(String nome) {
        LinearLayout cardView = (LinearLayout) LayoutInflater.from(this)
                .inflate(R.layout.cardhome, mensagens, false);
        TextView nomes = (TextView) cardView.findViewById(R.id.nome);

        nomes.setText(nome);

        mensagens.addView(cardView);
    }
}

package com.dfgv.presidenciaveis;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dfgv.presidenciaveis.api.APIPartida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Placar extends Activity {

    Retrofit retrofit;
    APIPartida api;
    Long jogadorId;
    List<TextView> txtJogadores;
    List<TextView> txtPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_placar);

        txtJogadores = new ArrayList<>();
        txtPontos = new ArrayList<>();

        TextView txt = findViewById(R.id.jogador1);
        txtJogadores.add(txt);
        txt = findViewById(R.id.jogador2);
        txtJogadores.add(txt);
        txt = findViewById(R.id.jogador3);
        txtJogadores.add(txt);
        txt = findViewById(R.id.jogador4);
        txtJogadores.add(txt);
        txt = findViewById(R.id.jogador5);
        txtJogadores.add(txt);
        txt = findViewById(R.id.jogador6);
        txtJogadores.add(txt);

        txt = findViewById(R.id.pontos1);
        txtPontos.add(txt);
        txt = findViewById(R.id.pontos2);
        txtPontos.add(txt);
        txt = findViewById(R.id.pontos3);
        txtPontos.add(txt);
        txt = findViewById(R.id.pontos4);
        txtPontos.add(txt);
        txt = findViewById(R.id.pontos5);
        txtPontos.add(txt);
        txt = findViewById(R.id.pontos6);
        txtPontos.add(txt);


        Intent i = getIntent();
        jogadorId = i.getLongExtra("jogadorId", 0);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://mepresidenta.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APIPartida.class);

        TextView sairText = findViewById(R.id.txtVoltar);

        sairText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        carregarPlacar();
    }

    void carregarPlacar() {

        Call<List<Jogador>> call = api.getJogadoresNaPartida(jogadorId);

        Callback<List<Jogador>> callback =
                new Callback<List<Jogador>>() {
                    @Override
                    public void onResponse(Call<List<Jogador>> call,
                                           Response<List<Jogador>> response) {

                        List<Jogador> res = response.body();

                        if(response.isSuccessful()) {

                            mostrarPlacar(res);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Jogador>> call,
                                          Throwable t) {

                        carregarPlacar();
                    }
                };

        call.enqueue(callback);
    }

    @TargetApi(Build.VERSION_CODES.N)
    void mostrarPlacar(List<Jogador> jogadores) {

        Collections.sort(jogadores, new Comparator<Jogador>() {
            @Override
            public int compare(Jogador o1, Jogador o2) {
                return (int) (o1.getPontuacao() - o2.getPontuacao());
            }
        });

        for(int x = 0; x < jogadores.size(); x++) {

            Jogador jog = jogadores.get(jogadores.size() - x - 1);

            String nome = " " + String.valueOf(x+1) + ". " + jog.getNome();
            Long pontos = jog.getPontuacao();

            txtJogadores.get(x).setText(nome);
            txtPontos.get(x).setText(" " + pontos.toString());

            txtJogadores.get(x).setVisibility(View.VISIBLE);
            txtPontos.get(x).setVisibility(View.VISIBLE);
        }

    }
 }

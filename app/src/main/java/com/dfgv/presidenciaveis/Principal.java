package com.dfgv.presidenciaveis;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfgv.presidenciaveis.api.APIPartida;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal extends Activity {
    private ViewGroup mensagens;

    //region declarations
    LinearLayout container;

    ImageView imgViewMinis1;
    ImageView imgViewMinis2;
    ImageView imgViewMinis3;
    ImageView imgViewMinis4;

    TextView txtMinis1;
    TextView txtMinis2;
    TextView txtMinis3;
    TextView txtMinis4;

    Jogador jogador;
    Jogo partida;
    Retrofit retrofit;
    APIPartida api;

    // Button b1,b2,b3,b4,b
    List<List<ImageView>> imageViews;

    //endregion




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_principal);



        SharedPreferences pref = getApplicationContext().getSharedPreferences("jogo", 0); // 0 - for private mode

        String idJogador = pref.getString("idJogador","");
        String nomeJogador = pref.getString("nomeJogador","");
        String senhaJogador = pref.getString("senhaJogador","");
        String idJogo = pref.getString("idJogo","");
        String nomeJogo = pref.getString("nomeJogo","");
        String senhaJogo = pref.getString("senhaJogo","");

        //Para fins de teste
        idJogador = "254";
        senhaJogador = "5235B";

        idJogo = "144";
        senhaJogo = "123456";

        jogador = new Jogador();
        jogador.setId(Long.valueOf(idJogador));
        jogador.setNome(nomeJogador);
        jogador.setSenha(senhaJogador);

        partida = new Jogo();
        partida.setId(Long.valueOf(idJogo));
        partida.setNome(nomeJogo);
        partida.setSenha(senhaJogo);


        //region FindView
        //container = findViewById(R.id.containerPrincipal);

        imgViewMinis1 = findViewById(R.id.imgMinis1);
        imgViewMinis2 = findViewById(R.id.imgMinis2);
        imgViewMinis3 = findViewById(R.id.imgMinis3);
        imgViewMinis4 = findViewById(R.id.imgMinis4);

        txtMinis1 = findViewById(R.id.txtMinis1);
        txtMinis2 = findViewById(R.id.txtMinis2);
        txtMinis3 = findViewById(R.id.txtMinis3);
        txtMinis4 = findViewById(R.id.txtMinis4);



        imgViewMinis1.setVisibility(imgViewMinis1.INVISIBLE);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://mepresidenta.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APIPartida.class);

        getFavorites();
        getTabuleiro();
        getProximoJogador();

    }


    void posicionarPersonagem(Long setor, String personagem) {

        Call<List<Setor>> call = api.posicionarPersonagem(setor, personagem, jogador);

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {


                        }

                    }

                    @Override
                    public void onFailure(Call<List<Setor>> call,
                                          Throwable t) {

                        //showDialog("Falha ao carregar as informações", "ERRO 666");
                    }
                };

        call.enqueue(callback);

    }

    void promoverPersonagem() {

        Call<List<Setor>> call = api.promoverPersonagem("A",jogador);

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {


                        }

                    }

                    @Override
                    public void onFailure(Call<List<Setor>> call,
                                          Throwable t) {

                        //showDialog("Falha ao carregar as informações", "ERRO 666");
                    }
                };

        call.enqueue(callback);
    }

    @TargetApi(Build.VERSION_CODES.N)
    void getProximoJogador() {

        Call<Jogador> call = api.getJogadorDaVez(jogador.getId());

        Callback<Jogador> callback =
                new Callback<Jogador>() {
                    @Override
                    public void onResponse(Call<Jogador> call,
                                           Response<Jogador> response) {

                        Jogador res = response.body();

                        if(response.isSuccessful()) {


                        }

                    }

                    @Override
                    public void onFailure(Call<Jogador> call,
                                          Throwable t) {

                        //showDialog("Falha ao carregar as informações", "ERRO 666");
                    }
                };

        call.enqueue(callback);

    }

    void getTabuleiro() {

        Call<List<Setor>> call = api.getTabuleiro(partida.getId());

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {


                        }

                    }

                    @Override
                    public void onFailure(Call<List<Setor>> call,
                                          Throwable t) {

                        //showDialog("Falha ao carregar as informações", "ERRO 666");
                    }
                };

        call.enqueue(callback);

    }

    void getFavorites() {

        Call<List<String>> call = api.getFavoritosDoJogador(jogador);

        Callback<List<String>> callback =
                new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call,
                                           Response<List<String>> response) {

                        List<String> res = response.body();

                        if(response.isSuccessful()) {


                        }

                    }

                    @Override
                    public void onFailure(Call<List<String>> call,
                                          Throwable t) {

                        //showDialog("Falha ao carregar as informações", "ERRO 666");
                    }
                };

        call.enqueue(callback);

    }
}
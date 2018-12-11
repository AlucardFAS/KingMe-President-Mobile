package com.dfgv.presidenciaveis;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.media.Image;
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

    //LISTAS DE IMAGEVIEWS E TEXTVIEWS
    List<List<ImageView>> imageViews;
    List<List<TextView>> textViews;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_principal);

        //region declaration list
        imageViews = new ArrayList<List<ImageView>>();
        textViews = new ArrayList<List<TextView>>();

        for(int x =0; x <7; x++)
        {
            imageViews.add(new ArrayList<ImageView>());
        }

        ImageView view = findViewById(R.id.imgPovo1);
        imageViews.get(0).add(view);
        view = findViewById(R.id.imgPovo2);
        imageViews.get(0).add(view);
        view = findViewById(R.id.imgPovo3);
        imageViews.get(0).add(view);
        view = findViewById(R.id.imgPovo4);
        imageViews.get(0).add(view);

        view = findViewById(R.id.imgVereador1);
        imageViews.get(1).add(view);
        view = findViewById(R.id.imgVereador2);
        imageViews.get(1).add(view);
        view = findViewById(R.id.imgVereador3);
        imageViews.get(1).add(view);
        view = findViewById(R.id.imgVereador4);
        imageViews.get(1).add(view);

        view = findViewById(R.id.imgPrefeito1);
        imageViews.get(2).add(view);
        view = findViewById(R.id.imgPrefeito2);
        imageViews.get(2).add(view);
        view = findViewById(R.id.imgPrefeito3);
        imageViews.get(2).add(view);
        view = findViewById(R.id.imgPrefeito4);
        imageViews.get(2).add(view);

        view = findViewById(R.id.imgGovernador1);
        imageViews.get(3).add(view);
        view = findViewById(R.id.imgGovernador2);
        imageViews.get(3).add(view);
        view = findViewById(R.id.imgGovernador3);
        imageViews.get(3).add(view);
        view = findViewById(R.id.imgGovernador4);
        imageViews.get(3).add(view);

        view = findViewById(R.id.imgSenador1);
        imageViews.get(4).add(view);
        view = findViewById(R.id.imgSenador2);
        imageViews.get(4).add(view);
        view = findViewById(R.id.imgSenador3);
        imageViews.get(4).add(view);
        view = findViewById(R.id.imgSenador4);
        imageViews.get(4).add(view);

        view = findViewById(R.id.imgMinis1);
        imageViews.get(5).add(view);
        view = findViewById(R.id.imgMinis2);
        imageViews.get(5).add(view);
        view = findViewById(R.id.imgMinis3);
        imageViews.get(5).add(view);
        view = findViewById(R.id.imgMinis4);
        imageViews.get(5).add(view);

        view = findViewById(R.id.imgPresidente);
        imageViews.get(6).add(view);

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        for(int x =0; x <7; x++)
        {
            textViews.add(new ArrayList<TextView>());
        }

        TextView viewText = findViewById(R.id.txtPovo1);
        textViews.get(0).add(viewText);
        viewText = findViewById(R.id.txtPovo2);
        textViews.get(0).add(viewText);
        viewText = findViewById(R.id.txtPovo3);
        textViews.get(0).add(viewText);
        viewText = findViewById(R.id.txtPovo4);
        textViews.get(0).add(viewText);

        viewText = findViewById(R.id.txtVereador1);
        textViews.get(1).add(viewText);
        viewText = findViewById(R.id.txtVereador2);
        textViews.get(1).add(viewText);
        viewText = findViewById(R.id.txtVereador3);
        textViews.get(1).add(viewText);
        viewText = findViewById(R.id.txtVereador4);
        textViews.get(1).add(viewText);

        viewText = findViewById(R.id.txtPrefeito1);
        textViews.get(2).add(viewText);
        viewText = findViewById(R.id.txtPrefeito2);
        textViews.get(2).add(viewText);
        viewText = findViewById(R.id.txtPrefeito3);
        textViews.get(2).add(viewText);
        viewText = findViewById(R.id.txtPrefeito4);
        textViews.get(2).add(viewText);

        viewText = findViewById(R.id.txtGovernador1);
        textViews.get(3).add(viewText);
        viewText = findViewById(R.id.txtGovernador2);
        textViews.get(3).add(viewText);
        viewText = findViewById(R.id.txtGovernador3);
        textViews.get(3).add(viewText);
        viewText = findViewById(R.id.txtGovernador4);
        textViews.get(3).add(viewText);

        viewText = findViewById(R.id.txtSenador1);
        textViews.get(4).add(viewText);
        viewText = findViewById(R.id.txtSenador2);
        textViews.get(4).add(viewText);
        viewText = findViewById(R.id.txtSenador3);
        textViews.get(4).add(viewText);
        viewText = findViewById(R.id.txtSenador4);
        textViews.get(4).add(viewText);

        viewText = findViewById(R.id.txtMinis1);
        textViews.get(5).add(viewText);
        viewText = findViewById(R.id.txtMinis2);
        textViews.get(5).add(viewText);
        viewText = findViewById(R.id.txtMinis3);
        textViews.get(5).add(viewText);
        viewText = findViewById(R.id.txtMinis4);
        textViews.get(5).add(viewText);

        viewText = findViewById(R.id.txtPresidente);
        textViews.get(6).add(viewText);

        //endregion


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


        retrofit = new Retrofit.Builder()
                .baseUrl("http://viacep.com.br/ws/")
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

        Integer value = Math.toIntExact(jogador.getId());
        Call<Jogador> call = api.getJogadorDaVez(value);

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

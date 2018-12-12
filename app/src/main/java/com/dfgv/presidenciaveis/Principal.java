package com.dfgv.presidenciaveis;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dfgv.presidenciaveis.api.APIPartida;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dfgv.presidenciaveis.R.drawable.pop_selecionar;

public class Principal extends Activity {
    private ViewGroup mensagens;

    //region declarations
    private Handler refresher = null;
    private Runnable refresherRunner;

    private Boolean turnoDoJogador = false;

    LinearLayout container;

    //DE QUANTO EM QAUNTO TEMPO EXECUTARÁ O CODIGO
    private int taxaAtualizacaoEmSegundos = 15;

    private boolean carregouPersonagens = false;
    private boolean carregouFavoritos = false;
    private boolean carregouSetores = false;

    private String statusDaRodada = "S";

    List<String> favoritos;
    List<Setor> tabuleiro;
    List<String> personagens;

    Jogador jogador, jogadorDaVez;
    Jogo partida;
    Retrofit retrofit;
    APIPartida api;

    List<Button> buttons;
    List<List<ImageView>> imageViews;
    List<List<TextView>> textViews;

    TextView partidaEstado;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_principal);

        //region declaration list
        buttons = new ArrayList<>();
        imageViews = new ArrayList<List<ImageView>>();
        textViews = new ArrayList<List<TextView>>();

        Button btn = findViewById(R.id.btnCandidato1);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato2);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato3);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato4);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato5);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato6);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato7);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato8);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato9);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato10);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato11);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato12);
        buttons.add(btn);
        btn = findViewById(R.id.btnCandidato13);
        buttons.add(btn);

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

        //view = findViewById(R.id.imgPresidente);
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



        Runnable r = new Runnable() {

            public void run() {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("jogo", 0); // 0 - for private mode

                String idJogador = pref.getString("idJogador","");
                String nomeJogador = pref.getString("nomeJogador","");
                String senhaJogador = pref.getString("senhaJogador","");
                String idJogo = pref.getString("idJogo","");
                String nomeJogo = pref.getString("nomeJogo","");
                String senhaJogo = pref.getString("senhaJogo","");

                //Para fins de teste
                idJogador = "351";
                senhaJogador = "B343C";

                idJogo = "197";
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
                        .baseUrl("https://mepresidenta.azurewebsites.net/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                api = retrofit.create(APIPartida.class);

                getStatusPartida();
                getPersonagens();
                getFavorites();
                getSetores();


                startRefresher(10);
            }
        };

        //inicializa os atualizadores
        refresher = new Handler();
        refresherRunner = new Runnable() {
            @Override
            public void run() {
                final Runnable rThis = this;

                if(!carregouFavoritos) {
                    getFavorites();
                }

                if(!carregouPersonagens) {
                    getPersonagens();
                }

                if(!carregouSetores) {
                    getSetores();
                }

                getStatusPartida();
                getTabuleiro();

                //agenda a chamada da proxima atualização
                refresher.postDelayed(rThis,taxaAtualizacaoEmSegundos * 1000);
            }
        };

        partidaEstado = findViewById(R.id.txtStatusJogador);
        partidaEstado.setText("ESPERANDO SUA VEZ");

        new Thread(r).start();




    }

    //VEZ E ESTADOS

    void iniciarAVez() {

        partidaEstado.setText("SUA VEZ!");
        turnoDoJogador = true;

        //POSICIONAR
        if(statusDaRodada.equals("S")) {

        }

        //PROMOVER
        else if(statusDaRodada.equals("J")) {

        }

        //VOTAR
        else if(statusDaRodada.equals("V")) {
            mostrarPopUpDeVotacao();
        }

        stopRefresher();
    }

    void terminarAVez() {

        partidaEstado.setText("ESPERANDO SUA VEZ...");
        turnoDoJogador = false;

        //POSICIONAR
        if(statusDaRodada == "S") {

        }

        //PROMOVER
        else if(statusDaRodada == "J") {

        }

        //VOTAR
        else if(statusDaRodada == "V") {
            mostrarPopUpDeVotacao();
        }

        startRefresher(0);
    }

    void atualizarEstadoDaPartida(Jogo partida) {

        //FIM DA PARTIDA
        if(partida.getStatus() == "E") {
            //TODO: MOSTRAR O PLACAR E DEPOIS VOLTAR A TELA INICIAL
        }

        //POSICIONAR
        else if(statusDaRodada == "S") {

        }

        //PROMOVER
        else if(statusDaRodada == "J") {
            HorizontalScrollView scroll = findViewById(R.id.horizontalScrollView);
            scroll.setVisibility(View.INVISIBLE);
        }

        //VOTAR
        else if(statusDaRodada == "V") {
            if(turnoDoJogador) mostrarPopUpDeVotacao();
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    void atualizarTabuleiroGrafico(List<Setor> setores) {

        for(Setor novoSetor : setores) {

            for(Setor setorTabuleiro : tabuleiro) {

                if(setorTabuleiro.getId().equals(novoSetor.getId())) {

                    if (!listEqualsIgnoreOrder(novoSetor.getPersonagens(), setorTabuleiro.getPersonagens())) {

                        Integer x = Math.toIntExact(novoSetor.getId());

                        if(x > 5) x = 6;

                        List<String> personagensNoSetor = novoSetor.getPersonagens();

                        for(int y = 0; y < 4; y++) {

                            if(y+1 > personagensNoSetor.size()) {

                                textViews.get(x).get(y).setVisibility(View.INVISIBLE);
                                imageViews.get(x).get(y).setVisibility(View.INVISIBLE);
                            }

                            else {

                                String primeira = personagensNoSetor.get(y);

                                for(String nome : personagens) {

                                    if(nome.substring(0,1).equals(primeira)) {
                                        textViews.get(x).get(y).setText(nome.toUpperCase());
                                        textViews.get(x).get(y).setVisibility(View.VISIBLE);

                                        break;
                                    }
                                }

                                imageViews.get(x).get(y).setVisibility(View.VISIBLE);

                                if(favoritos.contains(personagens.get(y).substring(0,1)))
                                    imageViews.get(x).get(y).setImageResource(R.mipmap.charicon_fav);

                                else
                                    imageViews.get(x).get(y).setImageResource(R.mipmap.charicon);
                            }

                        }

                    }

                }

            }

        }

    }


    //CHECANDO STATUS

    void getStatusPartida() {

        Call<Jogo> call = api.getPartidaStatus(jogador.getId());

        Callback<Jogo> callback =
                new Callback<Jogo>() {
                    @Override
                    public void onResponse(Call<Jogo> call,
                                           Response<Jogo> response) {

                        Jogo res = response.body();

                        if(response.isSuccessful()) {

                            if(!statusDaRodada.equals(res.getStatusRodado())) {
                                statusDaRodada = res.getStatusRodado();
                                atualizarEstadoDaPartida(res);
                            }

                            getProximoJogador();
                        }

                    }

                    @Override
                    public void onFailure(Call<Jogo> call,
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

                            if(jogadorDaVez != null) {
                                if(!jogadorDaVez.getId().equals(res.getId())) {
                                    getTabuleiro();
                                }
                            }

                            jogadorDaVez = res;

                            if(jogadorDaVez.getId().equals(jogador.getId())) {
                                if(!turnoDoJogador) iniciarAVez();
                            }
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

    void ajustarFavoritos() {

        for(Button btn : buttons) {

            String candidato = btn.getText().toString().substring(0,1);

            if(favoritos.contains(candidato)) {
                btn.setBackgroundResource(R.drawable.pop_selecionar);
                btn.setTextColor(Color.parseColor("#F5ECD9"));

            }
        }

    }

    void getTabuleiro() {

        Call<List<Setor>> call = api.getTabuleiro(jogador.getId());

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {

                            atualizarTabuleiroGrafico(res);

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

    //JOGADAS

    void posicionarPersonagem(final Button btn, Long setor, String personagem) {

        Call<List<Setor>> call = api.posicionarPersonagem(setor, personagem, jogador);

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {

                            btn.setAlpha(0.45f);
                            btn.setOnClickListener(null);

                            atualizarTabuleiroGrafico(res);
                            terminarAVez();
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

    void promoverPersonagem(String personagem) {

        Call<List<Setor>> call = api.promoverPersonagem("A",jogador);

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {

                            atualizarTabuleiroGrafico(res);
                            terminarAVez();

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

    void votarEmPersonagem(String voto) {

        Call<List<Setor>> call = api.votarNoPresidente (voto,jogador);

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {

                            if(res.isEmpty()) {
                                //TODO: TEMOS UM PRESIDENTE!! O QUE FAZER MESMO??
                            }

                            else {
                                atualizarTabuleiroGrafico(res);
                                terminarAVez();
                            }

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

    //CARREGAMENTO INICIAL

    void getFavorites() {

        Call<List<String>> call = api.getFavoritosDoJogador(jogador);

        Callback<List<String>> callback =
                new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call,
                                           Response<List<String>> response) {

                        List<String> res = response.body();

                        if(response.isSuccessful()) {

                            carregouFavoritos = true;
                            favoritos = res;

                            if(carregouPersonagens) {
                                ajustarFavoritos();
                            }
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

    void getPersonagens() {

        Call<List<String>> call = api.getPersonagens();

        Callback<List<String>> callback =
                new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call,
                                           Response<List<String>> response) {

                        List<String> res = response.body();

                        if(response.isSuccessful()) {

                            carregouPersonagens = true;
                            personagens = res;

                            if(carregouFavoritos) {
                                ajustarFavoritos();
                            }

                            for(int x = 0; x < res.size(); x++) {

                                View.OnClickListener listener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Button btn = (Button) v;

                                        mostrarPopUpDeSelecionar(btn);

                                    }
                                };

                                buttons.get(x).setText(res.get(x));
                                buttons.get(x).setOnClickListener(listener);
                            }
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

    void getSetores() {

        Call<List<Setor>> call = api.getSetores();

        Callback<List<Setor>> callback =
                new Callback<List<Setor>>() {
                    @Override
                    public void onResponse(Call<List<Setor>> call,
                                           Response<List<Setor>> response) {

                        List<Setor> res = response.body();

                        if(response.isSuccessful()) {

                            carregouSetores = true;
                            tabuleiro = res;

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

    //POPUP


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {

            Boolean eleger = data.getBooleanExtra("eleger", false);

            if(eleger) {
                String personagem = data.getStringExtra("nome");
                promoverPersonagem(personagem.substring(0, 1));
            }
        }

        else if(requestCode == 2) {

        }

        else if(requestCode == 3) {

            Long setor = data.getLongExtra("setor", -1);
            Integer index = data.getIntExtra("buttonIndex", 0);
            Button btn = buttons.get(index);

            if(setor >= 0)
                posicionarPersonagem(btn, setor, btn.getText().toString().substring(0, 1));
        }
    }

    void mostrarPopUpPromover(String personagem) {

        if(!turnoDoJogador) return;
        Intent intent = new Intent(Principal.this, PopUpEleger.class);
        intent.putExtra("nome", personagem);

        startActivityForResult(intent, 1);

    }

    void mostrarPopUpDeVotacao() {

        if(!turnoDoJogador) return;

    }

    void mostrarPopUpDeSelecionar(Button btn) {

        if(!turnoDoJogador) return;

        Intent intent = new Intent(Principal.this, PopUpSelecionar.class);
        intent.putExtra("nome", btn.getText().toString());
        intent.putExtra("buttonIndex", buttons.indexOf(btn));

        startActivityForResult(intent, 3);

    }

    //REFRESHER

    //inicializa o atualizador
    public void startRefresher(long delay) {
        if (refresher != null)
            refresher.postDelayed(refresherRunner, delay);
    }

    //desliga o atualizador
    public void stopRefresher () {
        if (refresher != null)
            refresher.removeCallbacks(refresherRunner);
    }


    //LIST COMPARE

    public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
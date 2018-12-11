package com.dfgv.presidenciaveis.api;

import com.dfgv.presidenciaveis.Jogador;
import com.dfgv.presidenciaveis.Jogo;
import com.dfgv.presidenciaveis.Setor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIPartida {


    @GET("/kingme/rest/v1/jogo/")
    Call<List<Jogo>> getPartidas();

    @GET("/kingme/rest/v1/jogador/{id}")
    Call<List<Jogador>> getJogadoresNaPartida(@Path("id") Integer id);

    @PUT("/kingme/rest/v1/jogo/")
    Call<Jogador> iniciarPartidaERetornarJogador(@Body Jogo jogo);

    @POST("/kingme/rest/v1/personagem/")
    Call<List<String>> getFavoritosDoJogador(@Body Jogador jogador);

    @GET("/kingme/rest/v1/setor/")
    Call<List<Setor>> getListaDeSetores();

    @POST("/kingme/rest/v1/personagem/{setorId}/{personagem}/")
    Call<List<Setor>> posicionarPersonagem(@Path("setorId") Long id, @Path("personagem") String personagem, @Body Jogador jogador);

    @GET("/kingme/rest/v1/jogador/{vez}/")
    Call<Jogador> getJogadorDaVez(@Path("vez") Integer vez);

    @PUT("/kingme/rest/v1/personagem/{personagem}")
    Call<List<Setor>> promoverPersonagem(@Path("personagem") String personagem, @Body Jogador jogador);

    @POST("/kingme/rest/v1/voto/{voto}")
    Call<List<Setor>> votarNoPresidente(@Path("voto") String voto, @Body Jogador jogador);

    @GET("/kingme/rest/v1/jogo/tabuleiro/{idJogador}/")
    Call<List<Setor>> getTabuleiro(@Path("idJogador") Long id);



}

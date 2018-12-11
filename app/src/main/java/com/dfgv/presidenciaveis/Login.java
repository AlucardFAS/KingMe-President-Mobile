package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.senac.pdm.mepresidenta.lobby.CriarJogoActivity;
import br.com.senac.pdm.mepresidenta.lobby.EscolherJogoActivity;


public class Login extends AppCompatActivity {

    Button entrarjogo;
    Button criarsala;

    EditText nome, sala, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        entrarjogo = findViewById(R.id.entrarjogo);
        criarsala = findViewById(R.id.entrarsala);
        nome = findViewById(R.id.seunome);
        sala = findViewById(R.id.suasala);
        senha = findViewById(R.id.suasenha);

        entrarjogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nome.getText().toString().isEmpty()) {
                    showDialog("Insira o seu nome.", "Erro:");
                }

                else {

                    Intent intent = new Intent(Login.this, EscolherJogoActivity.class);
                    intent.putExtra("nomeJogador", nome.getText().toString());
                    intent.putExtra("atividadeJogo", "com.dfgv.presidenciaveis.home");
                    intent.putExtra("criar", false);

                    startActivity(intent);

                }

            }
        });


        criarsala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nome.getText().toString().isEmpty()) {
                    showDialog("Insira o seu nome.", "Erro:");
                }

                else if(sala.getText().toString().isEmpty()) {
                    showDialog("Insira o nome da sua sala.", "Erro:");
                }

                else if(senha.getText().toString().isEmpty()) {
                    showDialog("Insira uma senha para a sua sala.", "Erro:");
                }

                else {

                    Intent intent = new Intent(Login.this, CriarJogoActivity.class);
                    intent.putExtra("nomeJogador", nome.getText().toString());
                    intent.putExtra("nomeJogo", sala.getText().toString());
                    intent.putExtra("senhaJogo", senha.getText().toString());
                    intent.putExtra("criar", true);
                    intent.putExtra("atividadeJogo", "com.dfgv.presidenciaveis.home");

                    startActivity(intent);
                }
            }
        });


    }

    private void showDialog(String val, String title) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(Login.this);
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

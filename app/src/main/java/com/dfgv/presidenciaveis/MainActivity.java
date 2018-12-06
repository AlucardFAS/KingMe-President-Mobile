package com.dfgv.presidenciaveis;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {


    Button jogar;
    ImageView iv;
    TextView regras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        jogar = findViewById(R.id.button);
        iv = findViewById(R.id.icone);
        regras = findViewById(R.id.regrajogo);
        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this, JogoActivity.class);
//                intent.putExtra("nomeJogador","nome do jogador vai aqui");
//                intent.putExtra("atividadeJogo","com.dfgv.presidenciaveis.home");
//                        intent.putExtra("criar",false);
//
//                        startActivity(intent);

                Intent i = new Intent(MainActivity.this, PopUpVotar.class);
                startActivity(i);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), sobre.class);
                startActivity(ii);
            }
        });

        regras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), regras.class);
                startActivity(iii);
            }
        });

    }

}

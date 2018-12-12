package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PopUpSelecionar extends Activity {

    //region componentes
    Button btnSenador;
    Button btnGovernador;
    Button btnPrefeito;
    Button btnVereador;
    Button btnCancelar;
    TextView txtCandidato1;
    TextView txtCandidato2;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_pop_up_selecionar);

        //region declarações
        txtCandidato1 = findViewById(R.id.txtCandidatoSelecionar1);
        txtCandidato2 = findViewById(R.id.txtCandidatoSelecionar2);
        btnSenador = findViewById(R.id.btnSenador);
        btnCancelar = findViewById(R.id.btnCancelarSelecionar);
        btnVereador = findViewById(R.id.btnVereador);
        btnPrefeito = findViewById(R.id.btnPrefeito);
        btnGovernador = findViewById(R.id.btnGovernador);
        //endregion

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(params);
        Intent i = getIntent();

        String nomeCandidato = i.getStringExtra("nome");

        txtCandidato1.setText(nomeCandidato);
        txtCandidato2.setText(nomeCandidato);

        //region buttons
        btnSenador.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent retorno = getIntent();
                retorno.putExtra("setor", 4);
                setResult(RESULT_OK, retorno);
            }
        });
        btnGovernador.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent retorno = getIntent();
                retorno.putExtra("setor", 3);
                setResult(RESULT_OK, retorno);
            }
        });
        btnPrefeito.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent retorno = getIntent();
                retorno.putExtra("setor", 2);
                setResult(RESULT_OK, retorno);
            }
        });
        btnVereador.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent retorno = getIntent();
                retorno.putExtra("setor", 1);
                setResult(RESULT_OK, retorno);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //endregion
    }
}

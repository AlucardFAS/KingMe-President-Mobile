package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PopUpVotar extends Activity {

    //region componentes
    Button btnClose;
    Button btnYes;
    TextView txtCandidato1;
    TextView txtCandidato2;
    String nome;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_pop_up_votar);

        //region declarações
        btnClose = findViewById(R.id.btnCancelarVotar);
        btnYes = findViewById(R.id.btnSimVotar);
        txtCandidato1 = findViewById(R.id.txtCandidatoVotar);
        txtCandidato2 = findViewById(R.id.txtCandidatoVotar2);
        //endregion

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Aqui tira os esqueminhas de borda
        getWindow().setAttributes(params);

        Intent i = getIntent();

        nome = i.getStringExtra("nome");

        txtCandidato1.setText(nome);
        txtCandidato2.setText(nome);

        //region buttons
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retorno = new Intent();// getIntent();
                retorno.putExtra("voto", 1);//1 para sim
                retorno.putExtra("nome", nome);
                setResult(RESULT_OK, retorno);
                finish();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //endregion
    }
}

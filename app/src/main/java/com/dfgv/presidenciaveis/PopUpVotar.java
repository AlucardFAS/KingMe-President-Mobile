package com.dfgv.presidenciaveis;

import android.app.Activity;
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

    Button btnClose;
    Button btnYes;
    TextView candidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp

        setContentView(R.layout.activity_pop_up_votar);


        candidato = findViewById(R.id.txtCandidatoVotar);
        btnClose = findViewById(R.id.btnCancelarVotar);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnYes = findViewById(R.id.btnSimEleger);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retornar true
            }
        });

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Aqui tira os esqueminhas de borda
        getWindow().setAttributes(params);
    }
}

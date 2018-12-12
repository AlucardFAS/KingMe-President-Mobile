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

public class PopUpEleger extends Activity {

    //region componentes
    Button btnEleger;
    Button btnVetar;
    TextView txtCandidato1;
    TextView txtCandidato2;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_pop_up_eleger);

        //region declarações
        btnEleger = findViewById(R.id.btnSimEleger);
        btnVetar = findViewById(R.id.btnVetar);
        txtCandidato1 = findViewById(R.id.txtCandidatoEleger);
        txtCandidato2 = findViewById(R.id.txtCandidatoEleger2);
        //endregion

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(params);
        Intent i = getIntent();

        String nomeCandidato = i.getStringExtra("nome");
        Integer vetos = i.getIntExtra("vetos", 0);

        txtCandidato1.setText(nomeCandidato);
        txtCandidato2.setText(nomeCandidato);

        String text = "VETAR " + "(" + vetos.toString() + ")";
        btnVetar.setText(text);


        //region buttons
        btnEleger.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent retorno = new Intent();
                retorno.putExtra("eleger", 1);//1 para sim
                setResult(RESULT_OK, retorno);
                finish();
            }
        });

        if(vetos > 0) {

            btnVetar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent retorno = getIntent();
                    retorno.putExtra("eleger", 0);//0 para não | veto
                    setResult(RESULT_OK, retorno);
                    finish();
                }
            });

        }
        //endregion
    }
}

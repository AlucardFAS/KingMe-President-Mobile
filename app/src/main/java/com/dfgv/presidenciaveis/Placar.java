package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class Placar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//aqui apaga as parada de título da janela do PopUp
        setContentView(R.layout.activity_placar);

    }
}

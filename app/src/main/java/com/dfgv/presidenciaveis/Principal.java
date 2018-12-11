package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //region FindView
        //container = findViewById(R.id.containerPrincipal);

        imgViewMinis1 = findViewById(R.id.imgMinis1);
        imgViewMinis2 = findViewById(R.id.imgMinis2);
        imgViewMinis3 = findViewById(R.id.imgMinis3);
        imgViewMinis4 = findViewById(R.id.imgMinis4);

        txtMinis1 = findViewById(R.id.txtMinis1);
        txtMinis2 = findViewById(R.id.txtMinis2);
        txtMinis3 = findViewById(R.id.txtMinis3);
        txtMinis4 = findViewById(R.id.txtMinis4);


        imgViewMinis1.setVisibility(imgViewMinis1.INVISIBLE);

        //endregion

    }
}

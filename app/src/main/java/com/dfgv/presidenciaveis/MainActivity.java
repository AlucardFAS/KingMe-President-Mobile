package com.dfgv.presidenciaveis;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


//        int orientation = getResources().getConfiguration().orientation;
//
//        if (orientation == Configuration.ORIENTATION_PORTRAIT){
//            Intent i1 = new Intent(this, Main2Activity.class);
//            startActivity(i1);
//        }
//            else{
//            // TODO Auto-generated method stub
//            super.onRestart();
//            Intent i = new Intent(MainActivity.this, MainActivity.class);  //your class
//            startActivity(i);
//            finish();
//        }

    }

}

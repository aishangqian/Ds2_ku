package com.example.y700_15.ds2_zk2.view.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.y700_15.ds2_zk2.R;

public class Main2Activity extends AppCompatActivity {
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myView = findViewById(R.id.myView);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(myView,"rotation",0,360);
        rotation.setDuration(3000);
        rotation.start();
    }
}

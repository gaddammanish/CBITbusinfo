package com.example.gadda_000.newproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash_screen);
        Thread t=new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    Intent intent=new Intent(splash_screen.this, MainActivity.class);
                  //  overridePendingTransition(R.drawable.animation1,R.drawable.animation2);
                    startActivity(intent);
                }
            }

        };
        t.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }



}

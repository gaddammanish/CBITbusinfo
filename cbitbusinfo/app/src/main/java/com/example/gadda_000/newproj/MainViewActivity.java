package com.example.gadda_000.newproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainViewActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String data21 = null;
        tv=(TextView)findViewById(R.id.data);
        try {
            DBcontent info = new DBcontent(this);
            info.open();
            data21=info.getdata();
            info.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        tv.setText(data21);
    }

}

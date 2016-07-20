package com.example.gadda_000.newproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.sql.SQLException;

public class Main3Activity extends AppCompatActivity {
    Bundle totalData=new Bundle();
    String typedArea;
    TextView tv1,tv2,tv4,tv5,areainp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        areainp=(TextView)findViewById(R.id.areainp);

        totalData=getIntent().getExtras();
        typedArea=totalData.getString("typedArea");



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       display(typedArea);

    }




    public void display(String places)
    {



        tv1 = (TextView) findViewById(R.id.sno123);
        tv2=(TextView)findViewById(R.id.busno123);

        tv4=(TextView)findViewById(R.id.landmark123);

        tv5=(TextView)findViewById(R.id.tim123);

        String[][] result1=new String[200][5];
        DBcontent db=new DBcontent(this);
        try {
            db.open();
            result1=db.getcaldata(places);
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String unite1="Sno\n\n",unite2="Busno\n\n",unite3="Area\n\n",unite4="Landmark\n\n",unite5="time\n\n";
    String sp3;
        for(int i=0;i<result1.length;i++)
        {
            if(result1[i][0]!=null) {
                unite1 = unite1 + i + "\n\n\n\n";
                unite2 = unite2 + result1[i][1] + "\n\n\n\n";
               // unite3 = unite3 + result1[i][2] + "\n\n";
               if(result1[i][3].length()<=23)
                   sp3="\n\n\n\n";
                else
               sp3="\n\n\n";
                unite4 = unite4 + result1[i][3] + sp3;
                unite5 = unite5 + result1[i][4] + "\n\n\n\n";

            }
            else {
                break;
            }
        }

        areainp.setText("The Buses Pass Through "+typedArea+" are");
        tv1.setText(unite1);
        tv2.setText(unite2);
        //tv3.setText(unite3);
        tv4.setText(unite4);
        tv5.setText(unite5);


    }



}

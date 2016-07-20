package com.example.gadda_000.newproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class MainBusnoActivity extends AppCompatActivity {
    String nos;
    TextView innum,tv11,tv12,tv13,tv14;
    EditText noText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_busno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        noText=(EditText)findViewById(R.id.editno);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    public void busnumber(View view)
    {
    nos=noText.getText().toString();
        noText.setText("");

        tv11=(TextView)findViewById(R.id.Sno1234);
        tv12=(TextView)findViewById(R.id.area1234);
        tv13=(TextView)findViewById(R.id.landmark1234);
        tv14=(TextView)findViewById(R.id.time1234);

        String[][] result1=new String[200][4];
        DBcontent entry21=new DBcontent(this);
        try {
            entry21.open();
            result1=entry21.getbusdata(nos);
            entry21.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        String unite1="Sno\n\n",unite3="Area\n\n",unite4="Landmark\n\n",unite5="time\n\n";
        String sp1="\n\n\n";
        String sp2="\n\n\n";
        for(int i=0;i<result1.length;i++)
        {
            if(result1[i][1]!=null) {
                unite1 = unite1 + i + "\n\n\n\n";
                if(result1[i][1].length()<=18)
                    sp1="\n\n\n\n";
                else
                sp1="\n\n\n";

                if(result1[i][2].length()<=24)
                    sp2="\n\n\n\n";
                else
                sp2="\n\n\n";
                unite3 = unite3 + result1[i][1] + sp1;
                unite4 = unite4 + result1[i][2] + sp2;
                unite5 = unite5 + result1[i][3] + "\n\n\n\n";

            }
            else {
                break;
            }
        }


        innum=(TextView)findViewById(R.id.inpnum);
        innum.setText("The Route Map Of Bus Number "+nos);

        tv11.setText(unite1);
        tv12.setText(unite3);
        tv13.setText(unite4);
        tv14.setText(unite5);




    }


}

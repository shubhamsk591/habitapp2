package com.example.habitapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MotivationInsight extends AppCompatActivity {
    public static TextView data;
    ImageButton buttonnext;
    ImageButton buttonback;
    int count=0;
    int total;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.motivationinsight);

        buttonnext=findViewById(R.id.nextmotivationbutton);
        buttonback=findViewById(R.id.backmotivationbutton);
        data=findViewById(R.id.motivationi);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Fetchdataformotivation dataintakr=new Fetchdataformotivation();
            decreasevalue();
            dataintakr.setvalue(total);
            dataintakr.execute();
            }
        });
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fetchdataformotivation dataintakr=new Fetchdataformotivation();
                increasevalue();
                dataintakr.setvalue(total);
                dataintakr.execute();
            }
        });
    }
    private void decreasevalue(){
        if(count<=0){
            count=0;
        }
        else{
            count=count-1;
        }
        total= count;
    }
    private void increasevalue() {
        if(count>=100){
            count=100;
        }
        else{
            count=count+1;

        }
        total= count;
        Log.d("value in function","dsf"+total);
    }
}

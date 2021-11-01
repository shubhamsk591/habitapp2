package com.example.habitapp;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Popup extends AppCompatActivity {
    public static TextView data;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);


        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int with=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(with*0.5),(int)(height*0.5));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=0;
        getWindow().setAttributes(params);

        data=findViewById(R.id.window_motivation);
        Motivationpopdata dataintakr=new Motivationpopdata();
        rand();
        dataintakr.setvalue(i);
        dataintakr.execute();

        Log.d("Tag","hiuiu "+i);
        ImageButton buttonnext= findViewById(R.id.nextmotivationbuttonpop);
        ImageButton buttonback= findViewById(R.id.backmotivationbuttonpop);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Motivationpopdata dataintakr1=new Motivationpopdata();
                decreasevalue();
                dataintakr1.setvalue(i);
                dataintakr1.execute();
            }
        });
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Motivationpopdata dataintakr=new Motivationpopdata();
                increasevalue();
                dataintakr.setvalue(i);
                dataintakr.execute();
            }
        });
    }
    private void rand(){
        Random r=new Random();
        i= r.nextInt(100);
    }
    private void decreasevalue(){
        if(i<=0){
            i=0;
        }
        else{
            i=i-1;
        }
    }
    private void increasevalue() {
        if(i>=100){
            i=100;
        }
        else{
            i=i+1;
        }
        Log.d("value in function","dsf"+i);
    }
    }


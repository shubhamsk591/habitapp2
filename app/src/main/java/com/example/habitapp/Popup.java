package com.example.habitapp;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Popup extends AppCompatActivity {
    public static TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);


        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int with=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(with*0.8),(int)(height*0.5));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);

        data=findViewById(R.id.window_motivation);
        Motivationpopdata dataintakr=new Motivationpopdata();
        dataintakr.execute();

    }
}

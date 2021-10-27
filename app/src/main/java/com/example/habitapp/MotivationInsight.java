package com.example.habitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MotivationInsight extends AppCompatActivity {
    public static TextView data;
    Button button;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motivationinsight);

        button=findViewById(R.id.cl);
        data=findViewById(R.id.motivationi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Fetchdataformotivation dataintakr=new Fetchdataformotivation();
            dataintakr.execute();
            }
        });
    }
}

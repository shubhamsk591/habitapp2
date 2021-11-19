package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Progress extends AppCompatActivity {
    private int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        Button bt=findViewById(R.id.detailbutton);
        Intent up=getIntent();
        String name=up.getStringExtra("Name");
        int id=up.getIntExtra("Id",-1);
        progress=progressvalue(id,name);
        update();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),DetailsAchieved.class);
                in.putExtra("Id",id);
                in.putExtra("Name",name);
                startActivity(in);
            }
        });
    }

    private int progressvalue(int id,String name) {

        TextView txt=findViewById(R.id.habittitle);
        txt.setText(name);
        DataBaseTracker dbt=new DataBaseTracker(getApplicationContext());
        int count=dbt.getcomCount(id,name);
        int pr=dbt.getCompletecount(id,name);
        int val=(int)((pr/count)*100);
        return val;
    }

    private void update() {
        ProgressBar pr=findViewById(R.id.progress_bar);
        pr.setProgress(progress);

    }
}
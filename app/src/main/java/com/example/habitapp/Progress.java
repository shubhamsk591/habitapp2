package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Progress extends AppCompatActivity {
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        Button bt=findViewById(R.id.detailbutton);
        Intent up=getIntent();
        String name=up.getStringExtra("Name");
        int id=up.getIntExtra("Id",-1);
        update(id,name);
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
        Log.d("Value","j"+count);
        int pr=dbt.getCompletecount(id,name);
        Log.d("Value","j"+pr);
        return (int)((pr*100)/count);
    }

    private void update(int id,String name) {
        tx=findViewById(R.id.perctext);
        int prog=progressvalue(id,name);
        String message=prog+"%";
        tx.setText(message);
        ProgressBar pr=findViewById(R.id.progress_bar);
        pr.setProgress(prog);

    }
}
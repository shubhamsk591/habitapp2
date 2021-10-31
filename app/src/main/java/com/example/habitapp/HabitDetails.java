package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HabitDetails extends AppCompatActivity {
    private TextView ueditname, uedunit, uedquestion, uednotes, uedtarget,ureminder,ucomplete;

    private int id = -1;
    Databasehelper dmhelper = new Databasehelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habitdetails);

        ueditname = findViewById(R.id.duname_input);
        uedquestion = findViewById(R.id.duquestion_input);
        uedunit = findViewById(R.id.duinput_unit);
        uedtarget = findViewById(R.id.duinput_target);
        uednotes = findViewById(R.id.dunotes_input);
        uedunit = findViewById(R.id.duinput_unit);
        ureminder = findViewById(R.id.dureminder_input);
        ucomplete=findViewById(R.id.ducomplete_input);
        fetchvalueinsertedup();
    }

//getvalue
    private void fetchvalueinsertedup() {
        Intent nowupdate = getIntent();
        id = nowupdate.getIntExtra("Id", -1);//default
        int urm=0,complete=0;
        Habits har= dmhelper.getvalueup(id);
        ueditname.setText(har.getName());
        uedquestion.setText(har.getQuestion());
        uedunit.setText(har.getUnit());
        uedtarget.setText(har.getTarget());
        uednotes.setText(har.getNotes());
        urm=har.getReminder();
        Log.d("vuy","hvyu"+urm);
        complete=har.getCompleted();
        if(urm == 0){
            ureminder.setText("Off");
        }
        else{
            ureminder.setText("On");
        }
        if(complete == 0){
            ucomplete.setText("No");
        }
        else{
            ucomplete.setText("Yes");
        }

    }

}

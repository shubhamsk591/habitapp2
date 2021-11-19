package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HabitDetails extends AppCompatActivity {
    private TextView ueditname;
    private TextView uedunit;
    private TextView uedquestion;
    private TextView uednotes;
    private TextView uedtarget;
    private TextView ureminder;

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

        fetchvalueinsertedup();
    }

//getvalue
    private void fetchvalueinsertedup() {
        Intent nowupdate = getIntent();
        int id = nowupdate.getIntExtra("Id", -1);//default
        int urm;
        Habits har= dmhelper.getvalueup(id);
        ueditname.setText(har.getName());
        uedquestion.setText(har.getQuestion());
        uedunit.setText(har.getUnit());
        uedtarget.setText(har.getTarget());
        uednotes.setText(har.getNotes());
        urm=har.getReminder();
        Log.d("vuy","hvyu"+urm);
        if(urm == 0){
            ureminder.setText("Off");
        }
        else{
            ureminder.setText("On");
        }


    }

}

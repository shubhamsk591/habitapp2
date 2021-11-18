package com.example.habitapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //one time run
        ontime();
        //always
        //check date
        timeupdate();

    }

    private void timeupdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ", Locale.US);
        String currentDateandTime = sdf.format(new Date());
        Log.d("Time", "hjh " + currentDateandTime);
        DataBaseDateUpdate dataBaseDateUpdate = new DataBaseDateUpdate(getApplicationContext());
        Cursor stdate = dataBaseDateUpdate.getdate();
        while(stdate.moveToNext()){
            String pre=stdate.getString(1);
            Log.d("Time", "hjh " + pre);
            if (pre.equals(currentDateandTime)) {
                starthome();
            }

            else{
                openPopUpWindow();
                boolean a = dataBaseDateUpdate.updatedate(currentDateandTime);
                if (a) {
                    addnewentryfor(currentDateandTime);
                }
            }
        }}

    private void starthome() {
        Intent in=new Intent(getApplicationContext(),Home.class);
        startActivity(in);
    }

    private void addnewentryfor(String time) {
        DataBaseTracker dataBaseTracker=new DataBaseTracker(getApplicationContext());
        Databasehelper databasehelper=new Databasehelper(getApplicationContext());
        Cursor cdata = databasehelper.getdata();
        while (cdata.moveToNext()) {
            int id=cdata.getInt(0);
            String name=cdata.getString(1);
            dataBaseTracker.addDatabaseitemtracker(id,name,time,0);
            databasehelper.setCompleted(id,name,0);
        }}


    private void getLogin() {
        Intent in=new Intent(getApplicationContext(),Loginform.class);
        startActivity(in);
    }
    private void ontime() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getBoolean("IS_FIRST_TIME", true)) {
            //show your dialog here
            openPopUpWindow();
            DataBaseDateUpdate dataBaseDateUpdate=new DataBaseDateUpdate(getApplicationContext());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ", Locale.US);
            String currentDate = sdf.format(new Date());
            dataBaseDateUpdate.updatedate(currentDate);
            getLogin();
            //change the value of your sharedPreferences
            sharedPreferences.edit().putBoolean("IS_FIRST_TIME", false).apply();
        }
    }

    private void openPopUpWindow() {
        Intent popupw=new Intent(getApplicationContext(),Popup.class);
        startActivity(popupw);
    }

}
package com.example.habitapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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
        getLogin();
    }




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
package com.example.habitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailsAchieved extends AppCompatActivity {
    ArrayList<String> arrayList;
    ListView listView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsacheved);
        listView=findViewById(R.id.listitemdatecom);
    populate();
    }

    private void populate() {
        Intent getupdate=getIntent();
        String name=getupdate.getStringExtra("Name");
        int id=getupdate.getIntExtra("Id",-1);
        if(id>0){
            String message;
            DataBaseTracker dbt=new DataBaseTracker(getApplicationContext());
            Cursor cr=dbt.getdetail(id,name);
            Log.d("Getcount","value "+cr.getCount());
            while(cr.moveToNext()){
                String date=cr.getString(3);
                Log.d("ghh","hj"+date);
                int com=cr.getInt(4);
                message=date.toString()+"\t"+"'"+com+"'";
                arrayList.add(date);
            }
            ArrayAdapter ad=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(ad);
        }
    }
}
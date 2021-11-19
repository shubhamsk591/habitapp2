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

    ListView listView,listView1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsacheved);
        listView=findViewById(R.id.listitemdatea);
        listView1=findViewById(R.id.listitemcoma);
    populate();
    }

    private void populate() {
        Intent getupdate=getIntent();
        String name=getupdate.getStringExtra("Name");
        int id=getupdate.getIntExtra("Id",-1);
        if(id>0){

            ArrayList<String> arrayList=new ArrayList<>();
            ArrayList<String> arrayList1=new ArrayList<>();
            DataBaseTracker dbt=new DataBaseTracker(getApplicationContext());
            Cursor cr=dbt.getdetail(id,name);
            if(cr.getCount()>0){
                arrayList.add("Dates");
                arrayList1.add("Complete");
            while(cr.moveToNext()){
                String date=cr.getString(0);
                Log.d("ghh","hj"+date);
                int com=cr.getInt(1);
                arrayList.add(date);
                if(com==1){
                arrayList1.add("Yes");}
                else{
                    arrayList1.add("No");
                }
            }
            ArrayAdapter<String> ad=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(ad);
                ArrayAdapter<String> ad1=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList1);
                listView1.setAdapter(ad1);
            }
        }
    }
}
package com.example.habitapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;




import java.util.ArrayList;
import java.util.Arrays;

public class Habitform extends AppCompatActivity {
    private EditText editTxt;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    public void Additemlist(){
        editTxt = (EditText) findViewById(R.id.name_input);
        String value=editTxt.getText().toString();
        Log.d("Cat","value:"+value);
        arrayList = new ArrayList<String>();
        arrayList.add(value);
        Log.d("Cat","arraylist:"+arrayList);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        Log.d("Cat","adapter:"+adapter);
        list = (ListView) findViewById(R.id.listView_item);
        Log.d("Cat","list:"+list);
        list.setAdapter(adapter);
        Log.d("cat","list after"+list);

        adapter.notifyDataSetChanged();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habitform);


        Button cancelbutton = (Button) findViewById(R.id.cancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(), MainActivity.class);
                startActivity(myintent);
            }
        });


        Button okbutton = (Button) findViewById(R.id.ok_button);


        okbutton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick (View v){
                Additemlist();
            }
        });
    }
}

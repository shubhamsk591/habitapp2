package com.example.habitapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Habitform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editname,edunit,edquestion,ednotes,edtarget;
    private boolean completed=false;
    private String remindertxt,nametxt,questiontxt,notestxt,unittxt,targettxt;
    private boolean rm=false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habitform);

        editname = findViewById(R.id.name_input);
        edquestion = findViewById(R.id.question_input);
        edunit = findViewById(R.id.input_unit);
        edtarget = findViewById(R.id.input_target);
        ednotes = findViewById(R.id.notes_input);
        edunit = findViewById(R.id.input_unit);
        Spinner reminder = findViewById(R.id.reminder_input);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Habitform.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Reminderoption));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminder.setAdapter(arrayAdapter);
        reminder.setOnItemSelectedListener(this);
        nametxt = editname.getText().toString();
        questiontxt = edquestion.getText().toString();
        unittxt = edunit.getText().toString();
        targettxt = edtarget.getText().toString();
        notestxt = ednotes.getText().toString();

        Databasehelper dmhelper = new Databasehelper(this);

        Button cancelbutton = (Button) findViewById(R.id.cancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(v.getContext(), MainActivity.class);
                startActivity(myintent);
            }
        });


        Button okbutton = (Button) findViewById(R.id.ok_button);


        okbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (nametxt != null && questiontxt != null && targettxt != null && remindertxt != null) {
                    AddData(nametxt, questiontxt, unittxt, targettxt, rm, notestxt, completed);
                    editname.setText("");
                    edquestion.setText("");
                    edunit.setText("");
                    edtarget.setText("");
                    ednotes.setText("");
                }
            }
            public void AddData(String nametxt, String questiontxt, String unittxt, String targettxt,boolean rm, String notestxt,boolean completed) {
                boolean insert=dmhelper.addDatabaseitem(nametxt,questiontxt,unittxt,targettxt,rm,notestxt,completed);
                if(insert==true){
                    Log.d("string ","gyg: "+insert);
                }
                else{
                    Log.d("string","gy"+insert);
                }}
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            remindertxt = adapterView.getItemAtPosition(i).toString();
            if(remindertxt == "Off");
            {rm=false; }
            if(remindertxt=="On"){
                rm=true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
/* private ListView list;
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

        adapter.notifyDataSetChanged();*/
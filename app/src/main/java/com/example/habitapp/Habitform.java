package com.example.habitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Habitform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editname,edunit,edquestion,ednotes,edtarget;
    private Spinner reminder;
    private int completed=0;
    private String remindertxt,nametxt="",questiontxt="",notestxt="",unittxt="",targettxt="";
    private int rm=0;

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
        reminder = findViewById(R.id.reminder_input);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Habitform.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Reminderoption));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminder.setAdapter(arrayAdapter);


        Databasehelper dmhelper = new Databasehelper(this);

        Button cancelbutton = (Button) findViewById(R.id.cancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(v.getContext(), Home.class);
                startActivity(myintent);
            }
        });


        Button okbutton = (Button) findViewById(R.id.ok_button);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("you","are");
                fetchvalueinserted();
                Log.d("you","are value inserted ");
                if (nametxt.length() != 0 && questiontxt.length() != 0 && targettxt.length() != 0) {
                    Log.d("you","are after test");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ", Locale.US);
                    String date = sdf.format(new Date());
                    AddData(nametxt, questiontxt, unittxt, targettxt, rm, notestxt, completed,date);
                    Log.d("you","are add data after");
                    editname.setText("");
                    edquestion.setText("");
                    edunit.setText("");
                    edtarget.setText("");
                    ednotes.setText("");
                    Intent in=new Intent(getApplicationContext(),Home.class);
                    startActivity(in);

                }
                else
                { editname.setText("");
                    edquestion.setText("");
                    edunit.setText("");
                    edtarget.setText("");
                    ednotes.setText("");
                    ToastMessage("Fill form details");
                }
            }

            public void AddData(String nametxt, String questiontxt, String unittxt, String targettxt,int rm, String notestxt,int completed,String date) {
                boolean insert=dmhelper.addDatabaseitem(nametxt,questiontxt,unittxt,targettxt,rm,notestxt,completed,date);
                Log.d("you","are checking insert");
                if(insert){
                    ToastMessage("Details filled inserted ");
                    Cursor cr=dmhelper.getItemID(nametxt);
                    cr.moveToFirst();
                    int id=cr.getInt(0);
                    DataBaseTracker dbt=new DataBaseTracker(getApplicationContext());
                   dbt.addDatabaseitemtracker(id,nametxt,date,0);

                }
                else{
                    ToastMessage("Details filled not inserted ");
                }}
        });
    }
    public void fetchvalueinserted(){
        nametxt = editname.getText().toString();
        Log.d("name in form ","hsj "+nametxt);
        questiontxt = edquestion.getText().toString();
        Log.d("name in form ","hsj "+questiontxt);
        unittxt = edunit.getText().toString();
        Log.d("name in form ","hsj "+unittxt);
        targettxt = edtarget.getText().toString();
        Log.d("name in form ","hsj "+targettxt);
        notestxt = ednotes.getText().toString();
        Log.d("name in form ","hsj "+notestxt);
        reminder.setOnItemSelectedListener(this);
        Log.d("name in form ","hsj "+rm);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            remindertxt = adapterView.getItemAtPosition(i).toString();
            if(remindertxt.equals("On")){
            rm=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void ToastMessage(String fill_form_details) {
        Toast.makeText(this,fill_form_details,Toast.LENGTH_SHORT).show();
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
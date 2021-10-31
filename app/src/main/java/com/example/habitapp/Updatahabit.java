package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Updatahabit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText ueditname,uedunit,uedquestion,uednotes,uedtarget;
    private Spinner ureminder;
    private int completed=0;
    private String old="",remindertxt="",unametxt="",uquestiontxt="",unotestxt="",uunittxt="",utargettxt="";
    private int urm=0,id=-1;
    Databasehelper dmhelper = new Databasehelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatehabit);

        ueditname = findViewById(R.id.uname_input);
        uedquestion = findViewById(R.id.uquestion_input);
        uedunit = findViewById(R.id.uinput_unit);
        uedtarget = findViewById(R.id.uinput_target);
        uednotes = findViewById(R.id.unotes_input);
        uedunit = findViewById(R.id.uinput_unit);
        ureminder = findViewById(R.id.ureminder_input);

        fetchvalueinsertedup();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Updatahabit.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Reminderoption));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ureminder.setAdapter(arrayAdapter);

        Button savebutton = (Button) findViewById(R.id.save_button);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchvalueinserted();
                Log.d("you","are value inserted ");
                if (unametxt.length() != 0 && uquestiontxt.length() != 0 && utargettxt.length() != 0) {
                    dmhelper.update(id,old,unametxt,uquestiontxt,uunittxt,utargettxt,urm,unotestxt,completed);
                }
                else
                {
                    ToastMessage("Incomplete filled");
                }
        }

        });
        Button deletebutton = (Button) findViewById(R.id.delete_button);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dmhelper.delete(id,old);
            }
            });

}
    private void ToastMessage(String fill_form_details) {
        Toast.makeText(this,fill_form_details,Toast.LENGTH_SHORT).show();
    }
    private void fetchvalueinserted() {
        unametxt = ueditname.getText().toString();
        Log.d("name in form ","hsj "+unametxt);
        uquestiontxt = uedquestion.getText().toString();
        Log.d("name in form ","hsj "+uquestiontxt);
        uunittxt = uedunit.getText().toString();
        Log.d("name in form ","hsj "+uunittxt);
        utargettxt = uedtarget.getText().toString();
        Log.d("name in form ","hsj "+utargettxt);
        unotestxt = uednotes.getText().toString();
        Log.d("name in form ","hsj "+unotestxt);
        ureminder.setOnItemSelectedListener(this);
        Log.d("name in form ","hsj "+urm);
    }

    private void fetchvalueinsertedup() {
        Intent nowupdate=getIntent();
        id=nowupdate.getIntExtra("Id",-1);//default
        old=nowupdate.getStringExtra("name");
        Habits har1 =dmhelper.getvalueup(id);
        ueditname.setText(unametxt);
        uedquestion.setText(uquestiontxt);
        uedunit.setText(uunittxt);
        uedtarget.setText(utargettxt);
        uednotes.setText(unotestxt);
        if(urm == 1){
            ureminder.setSelection(1);}
        else {
            ureminder.setSelection(0);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        remindertxt = adapterView.getItemAtPosition(i).toString();
        if(remindertxt.equals("On")){
            urm=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Updatahabit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText ueditname,uedunit,uedquestion,uednotes,uedtarget;
    private Spinner ureminder;
    private CheckBox ucomplete;
    private int completed=0;
    private String old="",unametxt="",uquestiontxt="",unotestxt="",uunittxt="",utargettxt="";
    private int urm=0,id=-1;
    Databasehelper dmhelper = new Databasehelper(this);
    ArrayAdapter<String> arrayAdapter;
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
        ucomplete=findViewById(R.id.input_completed);
        fetchvalueinsertedup();
        arrayAdapter = new ArrayAdapter<>(Updatahabit.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Reminderoption));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ureminder.setAdapter(arrayAdapter);
        ureminder.setOnItemSelectedListener(this);

        Button savebutton = (Button) findViewById(R.id.save_button);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchvalueinserted();
                Log.d("you","are value inserted ");
                if (unametxt.length() != 0 && uquestiontxt.length() != 0 && utargettxt.length() != 0) {
                    boolean up=dmhelper.updatedata(id,old,unametxt,uquestiontxt,uunittxt,utargettxt,urm,unotestxt,completed);
                    if(up){
                        ToastMessage("Sucessfully updated ");
                        Intent ab=new Intent(Updatahabit.this,Home.class);
                        startActivity(ab);
                    }
                    else{
                        ToastMessage("Failed to update");
                    }
                }
                else
                {
                    ToastMessage("Form not filled ");
                }
        }

        });
        Button deletebutton = (Button) findViewById(R.id.delete_button);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean del=dmhelper.deletedata(id,old);
                if(del){
                    ToastMessage("Sucessfully deleted ");
                    Intent ab=new Intent(Updatahabit.this,Home.class);
                    startActivity(ab);
                }
                else{
                    ToastMessage("Failed to delete ");
                    Intent ab=new Intent(Updatahabit.this,Home.class);
                    startActivity(ab);
                }
            }
            });

}
    private void ToastMessage(String a) {
        Toast.makeText(this, a,Toast.LENGTH_SHORT).show();
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
        Log.d("name in form ","hsj "+urm);
        completed=value();
    }
    private void fetchvalueinsertedup() {
        Intent nowupdate = getIntent();
        id = nowupdate.getIntExtra("Id", -1);//default
        old=nowupdate.getStringExtra("Name");
        Habits har= dmhelper.getvalueup(id);
        ueditname.setText(har.getName());
        uedquestion.setText(har.getQuestion());
        uedunit.setText(har.getUnit());
        uedtarget.setText(har.getTarget());
        uednotes.setText(har.getNotes());
        urm=har.getReminder();
        Log.d("vuy","hvyu"+urm);
        completed=har.getCompleted();
        if(urm == 1){
            ureminder.setSelection(1);}
        else {
            ureminder.setSelection(0);
        }
        if(completed == 1){
            ucomplete.setChecked(true);
        }
        else{
            ucomplete.setChecked(false);
        }

    }
    public int value(){
        if(ucomplete.isChecked()){

            completed=1;

        }
        else {
            completed=0;
        }
        return completed;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String remindertxt=adapterView.getItemAtPosition(i).toString();
        if(remindertxt.equals("On")){
            urm=1;
        }
        else{
            urm=0;
        }}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

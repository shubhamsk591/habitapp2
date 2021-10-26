package com.example.habitapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;




import java.util.ArrayList;

public class Habitform extends AppCompatActivity {

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
        AlertDialog.Builder build = new AlertDialog.Builder(this);

        okbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){

                build.setMessage(R.string.alertmessage).setTitle(R.string.alerttitle);
                build.setMessage(R.string.questionforsureaddhabit).setCancelable(false)
                        .setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){
                                finish();

                            }
                        })
                        .setNegativeButton(R.string.Cancel,new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), R.string.AddhabitclickCancel, Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = build.create();
                alert.setTitle(R.string.alerttitle);
                alert.show();
            }
        });
    }


}

package com.example.habitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Loginform extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.loginform);

        EditText username=findViewById(R.id.username_input);
        EditText password=findViewById(R.id.password_input);
        DatabaseLoginhelper Dblogin=new DatabaseLoginhelper(this);
        Button loginbutton = (Button) findViewById(R.id.Login_button);
        loginbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.isEmpty() ||pass.isEmpty()){
                    Toastmessage("Required Fields Empty!");
                }
                else if(Dblogin.checkusername(user)){
                    if(Dblogin.checkuserandpass(user,pass)){
                        Toastmessage("Welcome "+user);
                        Intent in=new Intent(getApplicationContext(),Home.class);
                        startActivity(in);
                    }
                    else{
                        Toastmessage("Incorrect Credentials!");
                    }
                }
                else{
                    Toastmessage("Username Doesn't Exist!");
                    Intent in=new Intent(getApplicationContext(),Registernew.class);
                    startActivity(in);
                }
            }
        });
        Button cancelbutton = (Button) findViewById(R.id.cancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(), Home.class);
                startActivity(myintent);
            }
        });
        Button registerbutton = (Button) findViewById(R.id.register_button);
        registerbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(),Registernew.class);
                startActivity(myintent);
            }
        });
    }

    private void Toastmessage(String st) {
        Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
    }


}

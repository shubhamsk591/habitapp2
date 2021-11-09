package com.example.habitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Registernew extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);

        EditText username=(EditText)findViewById(R.id.rname_input);
        EditText password=(EditText)findViewById(R.id.rpassword_input);
        DatabaseLoginhelper Dblogin=new DatabaseLoginhelper(this);
        Button cancelbutton = (Button) findViewById(R.id.rcancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(), MainActivity.class);
                startActivity(myintent);
            }
        });
        Button registerbutton = (Button) findViewById(R.id.rregister_button);
        registerbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.isEmpty() ||pass.isEmpty()){
                    Toastmessage("Please complete fill details");
                }
                else {
                    if(Dblogin.checkusername(user)){
                        Toastmessage("Username exist"); }
                    else{
                        if(Dblogin.addDatabaselogin(user,pass)){
                            Toastmessage("Sucessfully inserted");
                            Intent in=new Intent(getApplicationContext(),Loginform.class);
                            startActivity(in);
                        }
                        else{
                            Toastmessage("Unsucessful to insert data ");
                        }
                    }
                }

            }
        });
        Button loginbutton=(Button) findViewById(R.id.rLogin_button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent =new Intent(view.getContext(), Loginform.class);
                startActivity(myintent);
            }
        });
}

    private void Toastmessage(String st) {
        Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
    }
}

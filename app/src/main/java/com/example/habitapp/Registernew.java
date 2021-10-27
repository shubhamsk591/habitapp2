package com.example.habitapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registernew extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);

        EditText username=(EditText)findViewById(R.id.rname_input);
        EditText password=(EditText)findViewById(R.id.rpassword_input);
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
                String msg="Username : "+username.getText().toString()+"\nPassword : "+password.getText().toString();
                Toast.makeText(Registernew.this,"Welcome to habit and motivation app \n"+msg, Toast.LENGTH_LONG).show();
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
}

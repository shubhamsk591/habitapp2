package com.example.habitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Loginform extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        EditText username=findViewById(R.id.username_input);
        EditText password=findViewById(R.id.password_input);

        Button loginbutton = (Button) findViewById(R.id.Login_button);
        loginbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                String msg="Username : "+username.getText().toString()+"\nPassword : "+password.getText().toString();
                msg=msg+"\n"+"Welcome After Login";
                Toast.makeText(Loginform.this,
                        msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button cancelbutton = (Button) findViewById(R.id.cancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(), MainActivity.class);
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


}

package com.example.habitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Loginform extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        Button loginbutton = (Button) findViewById(R.id.loginfinal);
        loginbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Toast.makeText(Loginform.this,
                        R.string.WelcomeLogin,
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button cancelbutton = (Button) findViewById(R.id.cancel_button);
        cancelbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(), Fragment1.class);
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

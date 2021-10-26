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

        EditText username=(EditText)findViewById(R.id.username);
        EditText password=(EditText)findViewById(R.id.password);
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
                Toast.makeText(Registernew.this, "R.string.welcome_text"+username, Toast.LENGTH_LONG).show();
            }
        });
}
}

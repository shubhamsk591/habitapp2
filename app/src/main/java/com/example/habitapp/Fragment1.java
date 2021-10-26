package com.example.habitapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.habitadd, container, false);
        Button loginbutton = (Button) v.findViewById(R.id.login);
        loginbutton.setOnClickListener(this);
        Button registerbutton = (Button) v.findViewById(R.id.register_button);
        registerbutton.setOnClickListener(this);
        return v;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent myintent = new Intent(view.getContext(), Loginform.class);
                startActivity(myintent);
                break;
            case R.id.register_button:

                Intent myintent1 = new Intent(view.getContext(), Registernew.class);
                startActivity(myintent1);
        }

    }
}

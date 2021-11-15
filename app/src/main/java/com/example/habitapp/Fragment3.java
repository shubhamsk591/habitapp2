package com.example.habitapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;



public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.achievement,container,false);
        EditText searchView=v.findViewById(R.id.SearchBar);
        ImageButton imageButton=v.findViewById(R.id.searchbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= searchView.getText().toString();
                Databasehelper databasehelper=new Databasehelper(getContext());
                Cursor item=databasehelper.getItemID(name);
                int itemid=-1;
                while(item.moveToNext()){
                    itemid=item.getInt(0);
                    if(itemid>-1){
                        Cursor t=new DataBaseTracker(getContext()).getdetail(itemid,name);
                        updateCalender();
                    }
                    else{
                        ToastMessage("Name not found ");
                    }
                }

            }

            private void updateCalender() {
                //fetch calender
            }

            private void ToastMessage(String st) {
                Toast.makeText(getContext(),st,Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}


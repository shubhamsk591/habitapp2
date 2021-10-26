package com.example.habitapp;

import android.app.LauncherActivity;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.habitapp.R;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    public ArrayAdapter<String> arrayAdapter;
    public ArrayList<String> arrayList;
    public EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.habitlist, container, false);

        return v;


    }
}
/*
 ListView listView=v.findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem);
        listView.setAdapter(arrayAdapter);
        editText=v.findViewById(R.id.name_input);
 AlertDialog.Builder build = new AlertDialog.Builder(this);
 build.setMessage(R.string.alertmessage).setTitle(R.string.alerttitle);
                build.setMessage(R.string.questionforsureaddhabit).setCancelable(false)
                        .setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){
                                String newItem=editText.getText().toString();
                                arrayList.add(newItem);
                                arrayAdapter.notifyDataSetChanged();

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

 */
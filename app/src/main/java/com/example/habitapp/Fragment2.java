package com.example.habitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    Databasehelper databasehelper;
    ListView listview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.habitlist, container, false);
        listview = v.findViewById(R.id.listView_item);
        databasehelper = new Databasehelper(getActivity());
        populatelist();
        FloatingActionButton addhabit = v.findViewById(R.id.floatingActionButton);
        addhabit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent myintent =new Intent(v.getContext(),Habitform.class);
                startActivity(myintent);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=adapterView.getItemAtPosition(i).toString();
                Cursor crdata=databasehelper.getItemID(name);
                int itemid;
                while (crdata.moveToNext()){
                    itemid=crdata.getInt(0);
                    if(itemid > -1){
                        Intent update=new Intent(getActivity(),HabitDetails.class);
                        update.putExtra("Id",itemid);
                        update.putExtra("Name",name);
                        Log.d("uhiua","hdaui "+itemid);
                        startActivity(update);
                    }
                    else
                    {
                        Toastmessage("wrong id");
                    }
                }
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=adapterView.getItemAtPosition(i).toString();
                updatepage(name);
                return false;
            }
        });
        return v;


    }

    public void updatepage(String name) {
        Cursor crdata1=databasehelper.getItemID(name);
        int itemid;
        while (crdata1.moveToNext()){
            itemid=crdata1.getInt(0);
            if(itemid >-1){
                Intent updel=new Intent(getActivity(),Updatahabit.class);
                updel.putExtra("Id",itemid);
                updel.putExtra("Name",name);
                startActivity(updel);}
            else
            {
                Toastmessage("wrong name ");
            }
        }
    }

    private void populatelist() {
        Cursor cdata = databasehelper.getdata();
        ArrayList<String> arrayList = new ArrayList<>();
        while (cdata.moveToNext()) {
            arrayList.add(cdata.getString(1));
        }
        Listitemadapter adapter = new Listitemadapter(getContext(),arrayList);
        listview.setAdapter(adapter);
    }
    private void Toastmessage(String st){
        Toast.makeText(getActivity(),st,Toast.LENGTH_SHORT).show();
    }
}
/*

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
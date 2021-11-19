package com.example.habitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    Databasehelper databasehelper;
    ListView listview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.achievement, container, false);
        listview = v.findViewById(R.id.listitemdate);
        databasehelper = new Databasehelper(getActivity());
        populatelist();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=adapterView.getItemAtPosition(i).toString();
                Cursor crdata=databasehelper.getItemID(name);
                int itemid=-1;
                while (crdata.moveToNext()){
                    itemid=crdata.getInt(0);
                    if(itemid > -1){
                        Intent update=new Intent(getActivity(),DetailsAchieved.class);
                        update.putExtra("Id",itemid);
                        update.putExtra("Name",name);
                        startActivity(update);
                    }
                    else
                    {
                        Toastmessage("wrong id");
                    }
                }
            }
        });
        return v;


    }


    private void populatelist() {
        Cursor cdata = databasehelper.getdata();
        ArrayList<String> arrayList = new ArrayList<>();
        while (cdata.moveToNext()) {
            arrayList.add(cdata.getString(1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listview.setAdapter(adapter);
    }
    private void Toastmessage(String st){
        Toast.makeText(getActivity(),st,Toast.LENGTH_SHORT).show();
    }
}

package com.example.habitapp;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment1 extends Fragment {
    Databasehelper databasehelper;
    ListView listview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.habitadd, container, false);
        databasehelper = new Databasehelper(getActivity());
        listview=v.findViewById(R.id.listViewhome_item);

        FloatingActionButton fab = v.findViewById(R.id.modify_frag1);
        fab.setOnClickListener(v1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new Fragment2()).addToBackStack(null).commit();
        });

        populatelist();
        return v;
    }
    private void populatelist() {
        Cursor cdata = databasehelper.getdata();
        ArrayList<String> arrayList = new ArrayList<>();
        while (cdata.moveToNext()) {
            arrayList.add(cdata.getString(1));
        }
        Homelistitemadapter adapter = new Homelistitemadapter(getContext(),arrayList);
        listview.setAdapter(adapter);
    }

}
